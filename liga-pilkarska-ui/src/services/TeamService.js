import axios from "axios";

const TEAM_API_BASE_URL = "http://localhost:8080/api/team";

class TeamService {

    saveTeam(team) {
        return axios.post(TEAM_API_BASE_URL, team);
    }

    getTeams(token) {
        return axios.get(TEAM_API_BASE_URL, {
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json',
            },
        });
    }
}


export default new TeamService();