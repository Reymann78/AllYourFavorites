import React, { useContext } from 'react';
import styled from 'styled-components/macro';
import FavoriteContext from '../contexts/FavoriteContext';

export default function SoccerTeam({ soccerTeam }) {
  const { createFavorite } = useContext(FavoriteContext);
  console.log({ soccerTeam });

  return (
    <SoccerTeamStyled onClick={() => handleClick(soccerTeam.teamId)}>
      <LogoStyled src={soccerTeam.crestUrl} alt="Team Logo" />
      {soccerTeam.name}
    </SoccerTeamStyled>
  );

  function handleClick(teamId) {
    createFavorite(teamId);
  }
}

const LogoStyled = styled.img`
  height: 1rem;
  padding-right: 8px;
`;

const SoccerTeamStyled = styled.button`
  height: 30px;
  align-items: center;
  justify-content: space-between;
  border: none;
  background: none;
  border-radius: var(--border-radius);
  transition: background var(--speed);
  padding-left: 2px;
  color: var(--blue-50);
  font-size: var(--size-m);
  text-decoration: none;
  margin-right: 2px;

  &:hover {
    background-color: var(--blue-25);
  }
`;
