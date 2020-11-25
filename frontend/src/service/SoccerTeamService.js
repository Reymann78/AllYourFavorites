import axios from 'axios';

export const getSoccerTeams = () =>
  axios.get('/favorites/soccerTeams').then((response) => response.data);
