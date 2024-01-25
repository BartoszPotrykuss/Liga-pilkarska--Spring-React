import React from 'react'
import '../style/TeamList.css'

const TeamList = () => {
    return (
        <div className='TeamList'>
<table>
            <thead>
                <tr>
                    <th>Nazwa drużyny</th>
                    <th>Kraj</th>
                    <th>Trener</th>
                    <th>Mecze</th>
                    <th>Zawodnicy</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>FC Piłkarski Klub</td>
                    <td>Polska</td>
                    <td>Jan Kowalski</td>
                    <td><a href="#">mecze</a></td>
                    <td><a href="#">zawodnicy</a></td>
                </tr>
                <tr>
                    <td>Real Futbol Club</td>
                    <td>Hiszpania</td>
                    <td>Maria Garcia</td>
                    <td><a href="#">mecze</a></td>
                    <td><a href="#">zawodnicy</a></td>
                </tr>
            </tbody>
        </table>
        </div>
    )
}

export default TeamList