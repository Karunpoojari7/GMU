const API_ENDPOINTS = {
  AUTH: {
    LOGIN: "/api/leads/auth/login",
    LOGOUT: "/api/leads/auth/logout",
    REFRESH: "/api/leads/auth/refresh",
    ME: "/api/leads/auth/me",
  },

  LEADS: {
    GET_ALL: "/api/leads",
    CREATE: "/api/leads",
    UPDATE: "/api/leads/:id",
    DELETE: "/api/leads/:id",
  },
};

export default API_ENDPOINTS;