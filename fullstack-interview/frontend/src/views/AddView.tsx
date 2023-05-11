import {useState} from "react"
import Person from "../models/Person"
import styled from "styled-components";
import {Link} from "react-router-dom"
import axios from "axios";

function AddView(){

    const [person, setPerson] = useState<Person>({
        firstName: "",
        lastName: ""
    })

    const inputChange = (e: React.ChangeEvent<HTMLInputElement>)=>{
        setPerson((prev)=>({
            ...prev,
            [e.target.name]: e.target.value
        }))
        
    }
    const addClick = (e: React.MouseEvent<HTMLButtonElement>)=>{
        e.preventDefault()

        axios.post('http://localhost:8080/add', person).then(res=>{
            console.log(res.data)
            setPerson({
                firstName: "",
                lastName: ""
            })
            
        }).catch(err=>{
            alert(err)
        })
    }

    return <>
            <h1>Add Person</h1>
            Fist Name: <Input type="text" name="firstName" value={person.firstName} onChange={inputChange}/><br/>
            Last Name: <Input type="text" name="lastName" value={person.lastName} onChange={inputChange}/><br/>
            <SubmitButton onClick={addClick}>Add person</SubmitButton><br/>
            <Link to={"/"}>List of People</Link>
    </>;
}

export default AddView;

const Input = styled.input`

`;

const SubmitButton = styled.button`
`;