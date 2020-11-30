import axios from 'axios';

const header = (token) => ({
  headers: {
    Authorization: `Bearer ${token}`,
  },
});

export const getSoccerTeams = (token) =>
  axios
    .get('/api/favorites/soccerTeams', header(token))
    .then((response) => response.data);
