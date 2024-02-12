import React, { useState } from 'react'
import '../style/AddTeam.css'
import TeamService from '../services/TeamService'
import { useNavigate } from 'react-router-dom'

const AddTeam = () => {

    const [team, setTeam] = useState({
        id: "",
        country: "",
        name: "",
    })

    const navigate = useNavigate();

    const handleChange = (e) => {
        const value = e.target.value;
        setTeam({...team, [e.target.name]: value})
    }

    const saveTeam = (e) => {
        e.preventDefault();
        TeamService.saveTeam(team)
        .then((response) => {
            console.log(response);
            navigate("/teamList")
        }).catch((error) => {
            console.log(error)
        })
    }

    return (
        <form>
            <label htmlFor="name">Nazwa drużyny:</label>
            <input 
                type="text" id="name" name="name" required
                value={team.name}
                onChange={(e) => handleChange(e)} />

            <label htmlFor="country">Kraj:</label>
            <input 
                type="text" id="country" name="country" required
                value={team.country}
                onChange={(e) => handleChange(e)} />

            <button type="submit"
                onClick={saveTeam}>
                    Zatwierdź
            </button>
        </form>
    )
}

export default AddTeam