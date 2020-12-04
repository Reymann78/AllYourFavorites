import axios from 'axios';

const header = (token) => ({
  headers: {
    Authorization: `Bearer ${token}`,
  },
});

export const getFavorites = (token) =>
  axios.get('/api/favorites', header(token)).then((response) => response.data);

export const addFavorite = (teamId, token) =>
  axios
    .post('/api/favorites', { teamId }, header(token))
    .then((response) => response.data);
