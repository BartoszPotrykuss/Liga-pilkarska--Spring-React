import React from 'react'
import '../style/AddTeam.css'

const AddTeam = () => {
    return (
        <form>
            <label for="nazwa">Nazwa drużyny:</label>
            <input type="text" id="nazwa" name="nazwa" required />

            <label for="kraj">Kraj:</label>
            <input type="text" id="kraj" name="kraj" required />

            <button type="submit">Zatwierdź</button>
            <button type="reset" class="reset">Resetuj</button>
        </form>
    )
}

export default AddTeam