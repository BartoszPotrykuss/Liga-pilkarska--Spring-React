import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8080";

class UserService {
    authenticateUser(authenticationRequest)  {
        return axios.post(USER_API_BASE_URL + "/authenticate", authenticationRequest
        )
    }
}

export default new UserService();