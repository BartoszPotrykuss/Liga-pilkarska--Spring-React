import React from 'react'
import '../style/Header.css'

const Header = (props) => {
    return (
        <header>
            <h1>{props.title}</h1>
            
        </header>
    )
}

export default Header