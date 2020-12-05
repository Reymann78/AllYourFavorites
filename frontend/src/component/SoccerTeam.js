import React, { useContext } from 'react';
import styled from 'styled-components/macro';
import FavoriteContext from '../contexts/FavoriteContext';
import OpenMenuContext from '../contexts/OpenMenuContext';

export default function SoccerTeam({ soccerTeam }) {
  const { createFavorite } = useContext(FavoriteContext);
  const { open, setOpen } = useContext(OpenMenuContext);

  return (
    <SoccerTeamStyled onClick={() => handleClick(soccerTeam.teamId)}>
      <LogoStyled src={soccerTeam.crestUrl} alt="Team Logo" />
      {soccerTeam.name}
    </SoccerTeamStyled>
  );

  function handleClick(teamId) {
    createFavorite(teamId);
    setOpen(!open);
  }
}

const LogoStyled = styled.img`
  height: 1.2rem;
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
  color: var(--blue-main);
  font-size: var(--size-l);
  margin-right: 2px;

  &:hover {
    background-color: lightgrey;
  }
`;
