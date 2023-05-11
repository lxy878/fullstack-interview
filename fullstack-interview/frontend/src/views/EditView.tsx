import { useState, useEffect } from "react";
import Person from "../models/Person"
import { useParams, Link } from "react-router-dom";
import axios from "axios";

// 9. edit
export default function EditView(){
    const [person, setPerson] = useState<Person>({
        id:-1,
        firstName:"",
        lastName:""
    })
    const [loadingComplete, setLoadingComplete] = useState<boolean>(false)
    
    const {id} =  useParams<IdParams>()

    useEffect(()=>{
        axios.get(`http://localhost:8080/${id}`).then((res)=>{
            setPerson(res.data)
        }).catch(err=>{
            console.log(err)
        }).finally(()=>{
            setLoadingComplete(true)
        })
    }, [])

    const onChange = (e: React.ChangeEvent<HTMLInputElement>)=>{
        setPerson((prev)=>({
            ...prev,
            [e.target.name]:e.target.value
        }))    
    }

    const onClick = (e: React.MouseEvent<HTMLButtonElement>)=>{
        e.preventDefault()
        axios.patch(`http://localhost:8080/edit/${id}`, person).then(res=>{
            console.log(res)
        }).catch(err=>{
            console.log(err)
        })
    }

    return <>
        <h1>Edit Person No. {person.id}</h1>{
            loadingComplete && <div>
                Fist Name: <input type="text" name="firstName" value={person.firstName} onChange={onChange} /><br/>
                Last Name: <input type="text" name="lastName" value={person.lastName} onChange={onChange}/><br/>
                <button onClick={onClick}>Update New Information</button><br/>
            </div>
        }
        <Link to={"/"}>List of People</Link>
    </>
}

type IdParams = { id:string}