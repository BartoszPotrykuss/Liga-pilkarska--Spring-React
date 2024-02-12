import React, { useEffect, useState } from 'react'
import '../style/TeamList.css'
import { Navigate, useLocation, useNavigate } from 'react-router-dom'
import TeamService from '../services/TeamService';
import { getCountryCode} from 'countries-list'

const TeamList = () => {

    const location = useLocation();
    const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYXJ0b3N6LnBvdHJ5a3VzLmZAZ21haWwuY29tIiwiZXhwIjoxNzA3MjcyODUxLCJpYXQiOjE3MDcyMzY4NTF9.Gm1vF774TdrBBIt-ZRTDR6poOY9NRhAXjQI4Q8TnmTk";
    const navigate = useNavigate();

        let lp = 1;
        const [teams, setTeams] = useState(null);
        const [loading, setLoading] = useState(true);

        useEffect(() => {

            console.log('Token inside useEffect:', token);
            const fetchData = async () => {
                setLoading(true);
                try {
                    const response = await TeamService.getTeams(token);
                    setTeams(response.data);
                } catch (error) {
                    console.log(error);
                }
                setLoading(false);
            }
            fetchData();
        }, [token]);


        //useEffect jest zawsze przed renderem komponentu
        // tworzymy funkcje fetchData ktora ustawia ladowanie na true
        // próbujemy pobrać do stałej response rezultat getTeams()
        // ustawiamy hook teams jako data naszego respons
        // jesli sie nie uda to error
        // loading jako false
        // wywolanie fetchData po zainicjowaniu komponentu
        // pusta tablica zależności oznacza, że useEffect ma zostać uruchominy tylko raz

        return (
            <div className='TeamList'>
                <table>
                    <thead>
                        <tr>
                            <th>Lp.</th>
                            <th>Nazwa drużyny</th>
                            <th>Kraj</th>
                            <th>Trener</th>
                            <th>Mecze</th>
                            <th>Zawodnicy</th>
                        </tr>
                    </thead>
                    {!loading && (
                    <tbody>
                        {teams.map((team) => { 
                            const countryFlagImg = `https://flagsapi.com/${getCountryCode(team.country)}/flat/48.png`;
                                return (
                                    <tr key={team.id}>
                                        <td>{lp++}</td>
                                        <td>{team.name}</td>
                                        <td> <img src= {countryFlagImg} /> </td>
                                        <td>
                                            {team.coach ? (
                                                `${team.coach.firstName} ${team.coach.lastName}`
                                            ) : (
                                                "Brak trenera"
                                            )
                                            }
                                        </td>
                                        <td><button onClick={() => navigate(`/match/team/id/${team.id}`)}>mecze</button></td>
                                        <td><button onClick={() => navigate(`/player/team/id/${team.id}`, team.name
                                        )}>zawodnicy</button></td>
                                    </tr>
                        )})}
                    </tbody>)}
                </table>
            </div>
        )
}

export default TeamList