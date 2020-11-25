import React from 'react';
import styled from 'styled-components/macro';

export default function SoccerTeam({ soccerTeam }) {
  return <SoccerTeamStyled>{soccerTeam.name}</SoccerTeamStyled>;
}

const SoccerTeamStyled = styled.section`
  height: 50px;
  align-items: center;
  display: grid;
  grid-gap: 2px;
  border-radius: var(--border-radius);
  transition: background var(--speed);
  padding: 0.2px;
  color: var(--blue-50);
  font-size: var(--size-l);
  text-decoration: none;
  margin-right: 2px;

  &:hover {
    background-color: var(--blue-25);
  }
`;
