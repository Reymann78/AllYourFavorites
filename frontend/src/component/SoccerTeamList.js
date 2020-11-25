import React, { useContext } from 'react';
import SoccerTeamContext from '../contexts/SoccerTeamContext';
import SoccerTeam from './SoccerTeam';

export default function SoccerTeamList() {
  const { soccerTeams } = useContext(SoccerTeamContext);

  return (
    <ul>
      {soccerTeams?.map((soccerTeam) => (
        <li key={soccerTeam.name}>
          <SoccerTeam soccerTeam={soccerTeam} />
        </li>
      ))}
    </ul>
  );
}
