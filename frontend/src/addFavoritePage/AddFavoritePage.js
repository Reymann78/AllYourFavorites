import React, { useEffect, useState } from 'react';
import Header from '../commons/Header';
import axios from 'axios';
import SoccerTeamList from '../component/SoccerTeamList';

export default function AddFavoritePage() {
  const [soccerTeams, setSoccerTeams] = useState([]);

  useEffect(() => {
    axios
      .get('/favorites/soccerTeams')
      .then((response) => response.data)
      .then((data) => setSoccerTeams(data))
      .catch((error) => console.log(error));
  }, []);

  return (
    <>
      <Header title="Add favorite" />
      <SoccerTeamList soccerTeams={soccerTeams} />
    </>
  );
}
