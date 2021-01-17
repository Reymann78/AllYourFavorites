import React, { useContext } from 'react';
import SoccerTeamContext from '../contexts/SoccerTeamContext';
import styled from 'styled-components/macro';
import SoccerTeam from '../component/SoccerTeam';

export default function SoccerTeamList({ leagueName }) {
  const { soccerTeams } = useContext(SoccerTeamContext);

  return (
    <ul>
      {soccerTeams
        .filter((soccerTeam) => soccerTeam.competitionName === leagueName)
        .map((soccerTeam) => (
          <SoccerTeamListStyled key={soccerTeam.name}>
            <SoccerTeam soccerTeam={soccerTeam} />
          </SoccerTeamListStyled>
        ))}
      ;
    </ul>
  );
}

const SoccerTeamListStyled = styled.li`
  padding: var(--size-xs);
  display: flex;
  justify-content: flex-start;
  align-content: center;
`;
