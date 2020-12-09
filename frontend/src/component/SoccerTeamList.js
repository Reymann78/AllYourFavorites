import React, { useContext } from 'react';
import SoccerTeamContext from '../contexts/SoccerTeamContext';
import SoccerTeam from './SoccerTeam';

export default function SoccerTeamList({ leagueName }) {
  const { soccerTeams } = useContext(SoccerTeamContext);

  return (
    <ul>
      {soccerTeams
        .filter((soccerTeam) => soccerTeam.competitionName === leagueName)
        .sort()
        .map((soccerTeam) => (
          <li key={soccerTeam.id}>
            <SoccerTeam soccerTeam={soccerTeam} />
          </li>
        ))}
      ;
    </ul>
  );
}
