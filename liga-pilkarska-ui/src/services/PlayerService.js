import axios from "axios";

const PLAYER_API_BASE_URL = "http://localhost:8080/api/player";

class PlayerService {

    getPlayersByTeamId(teamId) {
        return axios.get(PLAYER_API_BASE_URL + "/team/id/" + teamId);
    }
}

export default new PlayerService();