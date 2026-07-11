import axiosInstance from "@/lib/axios";
import API_ENDPOINTS from "@/utils/apiEndpoints";

const authService = {
  /**
   * Login User
   * @param {Object} credentials
   * @param {string} credentials.username
   * @param {string} credentials.password
   */
  login: async (credentials) => {
    console.log("Login Payload:", credentials);

    const response = await axiosInstance.post(
      API_ENDPOINTS.AUTH.LOGIN,
      credentials
    );

    console.log("Login Response:", response.data);

    return response.data;
  },
};

export default authService;