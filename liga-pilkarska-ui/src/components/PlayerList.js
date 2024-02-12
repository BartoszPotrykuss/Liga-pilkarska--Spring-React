import React, { useEffect, useState } from 'react'
import PlayerService from '../services/PlayerService';
import { useParams } from 'react-router-dom';

const PlayerList = () => {

    let lp = 1;

    const { teamId } = useParams();
    const [players, setPlayers] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const response = await PlayerService.getPlayersByTeamId(teamId);
                setPlayers(response.data);

            }
            catch (error) {
                console.log(error);
            }
            setLoading(false);
        }
        fetchData();
    },[]);

    return (
        <section>
            <h2>Zawodnicy</h2>

            <table>
                <thead>
                    <tr>
                        <th>Lp.</th>
                        <th>Imię i Nazwisko</th>
                        <th>Pozycja</th>
                        <th>Drużyna</th>
                    </tr>
                </thead>
                {!loading && (
                <tbody>
                    {players.map((player) => (
                        <tr key={player.id}>
                            <td>{lp++}</td>
                            <td>{player.name}</td>
                            <td>{player.position}</td>
                            <td>{player.team.name}</td>
                        </tr>
                    ))}
                </tbody>)}
            </table>
        </section>
    )
}

export default PlayerList


/*
                {!loading && (
                <tbody>
                    {players.map((player) => (
                        <tr key={player.id}>
                            <td>{lp++}</td>
                            <td>{player.name}</td>
                            <td>{player.position}</td>
                        </tr>
                    ))}
                </tbody>)}
*/