import React from 'react';
import styled from 'styled-components/macro';

export default function SoccerTeam({ soccerTeam }) {
  return <SoccerTeamStyled>{soccerTeam.name}</SoccerTeamStyled>;
}

const SoccerTeamStyled = styled.span`
  height: 50px;
  display: flex;
  align-items: center;
  border-radius: var(--border-radius);
  transition: background var(--speed);
  padding: 0.2rem;
  color: var(--blue-50);
  font-size: var(--size-l);
  text-decoration: none;
  margin-right: 2px;

  &:hover {
    background-color: var(--blue-25);
  }
`;
