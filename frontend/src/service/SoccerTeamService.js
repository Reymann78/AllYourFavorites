import axios from 'axios';

export const getSoccerTeams = () =>
  axios.get('/api/favorites/soccerTeams').then((response) => response.data);
