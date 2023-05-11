import { useState, useEffect } from "react";
import Person from "../models/Person";
import { AxiosResponse } from "axios";
import axios from 'axios';
import styled from "styled-components";
import { Link } from "react-router-dom";

function ListView(){

    const [people, setPeople] = useState<Person[]>([]);
    const [loadingComplete, setLoadingComplete] = useState<boolean>(false);

    const [search, setSearch] = useState({term: ""})

    useEffect(()=>{

        axios("http://localhost:8080/all").then((response: AxiosResponse<Person[]> ) => {
            setPeople(response.data);
        }).catch((error)=>{
            alert(error);
        }).finally(()=>{
            setLoadingComplete(true);
        })

    },[])

    const deleteClick = (e: React.MouseEvent<HTMLButtonElement>) =>{
        const uid = e.currentTarget.id
        axios.delete(`http://localhost:8080/${uid}`).then((res: AxiosResponse<String>)=>{
            alert(res.data)
            setPeople(people.filter(p=>p.id?.toString() !== uid))
            
        }).catch((err)=>{
            alert(err)
        })
    }

    const onChange = (e: React.ChangeEvent<HTMLInputElement>)=>{
        setSearch((prev)=>({
            ...prev,
            [e.target.name]:e.target.value
        }))
    }
    
    const searchClick = (e: React.MouseEvent<HTMLButtonElement>)=>{
        e.preventDefault()
        axios.get(`http://localhost:8080/search?term=${search.term}`).then((res)=>{
            setPeople(res.data)
        }).catch((err)=>{
            console.log(err)
        })
    }

    return <> 
        <h1>List of all People</h1>
        <div>
            {/* 12. search */}
            <input placeholder="Enter First Name" type="text" onChange={onChange} value={search.term} name="term"/>
            <button onClick={searchClick}>search</button><br/><br/>
        </div>
        {
        loadingComplete &&
        <div>
            <Table>
                <THead>
                    <PersonRow><Th>First Name</Th><Th>Last Name</Th><Th>Actions</Th></PersonRow>
                </THead>
                <TBody>          
                    {
                        people.map((person: Person)=>{
                            return <PersonRow key={person.id}>
                                        <PersonCell>{person.firstName}</PersonCell>
                                        <PersonCell>{person.lastName}</PersonCell>
                                        <PersonCell>
                                            {/* delete */}
                                            <Button><Link to={`/edit/${person.id}`}>Edit</Link></Button>
                                            <Button id={person.id?.toString()} onClick={deleteClick}>Delete</Button>
                                        </PersonCell>
                                    </PersonRow>
                        })
                    }
                </TBody>
            </Table>
        </div>
        }
        <Link to={"/add"}>Add Person</Link>
    </>
}

export default ListView;

const PersonRow = styled.tr`

`;

const PersonCell = styled.td`
    padding: 0.5rem;
`;

const Table = styled.table``;
const THead = styled.thead``;
const TBody = styled.tbody``;
const Th = styled.th``;
const Button = styled.button`
    text-decoration: none
`;