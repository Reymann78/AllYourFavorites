import axios from 'axios';

const header = (token) => ({
  headers: {
    Authorization: `Bearer ${token}`,
  },
});

export const getFavorites = (token) =>
  axios.get('/api/favorites', header(token))
    .then((response) => response.data);

export const addFavorite = (teamId, token) =>
  axios
    .post('/api/favorites', { teamId }, header(token))
    .then((response) => response.data);

export const removeFavorite = (teamId, token) =>
  axios.delete('/api/favorites/' + teamId, header(token));

export const getStanding = (competitionId, matchDay, groupName, tableType, token) =>
  axios
    .post('/api/favorites/standings', { competitionId, matchDay, groupName, tableType }, header(token))
    .then((response) => response.data);

export const getMatchDayTable = (competitionId, matchDay, token) =>
  axios
    .post('api/favorites/matchDayTable', { competitionId, matchDay}, header(token))
    .then((response) => response.data);