import { useNavigate } from 'react-router-dom';
import UserService from '../services/UserService';
import '../style/LoginForm.css'
import React, { useState } from 'react'

const LoginForm = () => {

    const [auth, setAuth] = useState({
        email: "",
        password: ""
    }); 

    const [error, setError] = useState(null);

    const navigate = useNavigate();

    let token;

    const handleChange = (e) => {
        const value = e.target.value;
        setAuth({...auth, [e.target.name]: value})
    }

    const signIn = (e) => {
        e.preventDefault();
        UserService.authenticateUser(auth)
        .then((response) => {
            console.log(response);
            navigate("/teamList", {state: { token: response.jwt}})
        }).catch((error) => {
            console.log(error);
            setError("Błędne dane logowania. Spróbuj ponownie");
        })
    }

    return (
        <div>
            <form>
                <label for="email">E-mail:</label>
                <input type="email" id="email" name="email" required
                value={auth.email}
                onChange={(e) => handleChange(e)} />

                <label for="password">Hasło:</label>
                <input type="password" id="password" name="password" required 
                value={auth.password}
                onChange={(e) => handleChange(e)}/>

                <button type="submit"
                onClick={signIn}>Zaloguj się</button>

                {error && <p className="error-message">{error}</p>}

                <a href="#" class="register-link">Nie masz konta? Zarejestruj się</a>
            </form>
        </div>
    )
}

export default LoginForm;