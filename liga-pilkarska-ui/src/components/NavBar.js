import React from 'react'
import '../style/NavBar.css'

const NavBar = () => {
    return (
        <nav>
            <a href="/addTeam">Dodaj drużynę</a>
            <a href="/teamList">Lista drużyn</a>
        </nav>
    )
}

export default NavBar