import React, { useContext } from 'react';
import styled from 'styled-components/macro';
import FavoriteContext from '../contexts/FavoriteContext';
import Favorite from '../commons/Favorite';

export default function FavoriteList() {
  const { favorites } = useContext(FavoriteContext);

  return (
    <FavoriteListStyled>
      {favorites.map((favorite) => (
        <li key={favorite.teamId}>
          <Favorite favorite={favorite} />
        </li>
      ))}
    </FavoriteListStyled>
  );
}

const FavoriteListStyled = styled.ul`
  overflow: scroll;
  margin: 0;
  padding: var(--size-m);

  list-style: none;

  display: grid;
  grid-auto-rows: min-content;
  grid-gap: var(--size-l);

  li:last-child:after {
    content: '';
    display: block;
    height: 4px;
  }
`;
