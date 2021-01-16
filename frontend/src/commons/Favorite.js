import React, { useContext } from 'react';
import styled from 'styled-components/macro';
import { CgRemoveR } from 'react-icons/cg';
import FavoriteContext from '../contexts/FavoriteContext';
import SoccerMatchList from "../homePage/SoccerMatchList";

export default function Favorite({ favorite, className }) {
  const {deleteFavorite} = useContext(FavoriteContext);

  return (
      <FavoriteWrapper className={className}>
        <FavoriteHeader>
          <Logo src={favorite.crestUrl && favorite.crestUrl} alt="Team Logo"/>
          {favorite.name && favorite.name}
          <RemoveButton onClick={handleRemove}>{<CgRemoveR/>}</RemoveButton>
        </FavoriteHeader>
        <SoccerMatchList favorite={favorite} />
      </FavoriteWrapper>
  );

  function handleRemove() {
    deleteFavorite(favorite.teamId);
  }
}

const FavoriteWrapper = styled.section`
  padding: var(--size-xs);
  box-shadow: 3px 3px 3px var(--blue-50);
  border: var(--blue-border);
  border-radius: var(--size-s);
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));
`;

const FavoriteHeader = styled.header`
  display: grid;
  grid-template-columns: 1fr 6fr 1fr;
  align-items: center;
  padding-bottom: var(--size-s);
  color: var(--white);
  text-align: center;
  font-weight: 600;
  font-size: 1.2rem;
`;

const Logo = styled.img`
  height: 2rem;
  padding-right: var(--size-xs);
  justify-self: end;
  font-weight: 600;
`;

const RemoveButton = styled.button`
  --button-size: calc(var(--nav-size) * 0.6);
  width: var(--button-size);
  height: var(--button-size);
  color: var(--white);
  justify-self: end;
  font-size: var(--size-xl);
  background-color: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  padding-bottom: 10px;
  padding-left: 10px;

  &:hover {
    color: var(--blue-25);
  }
`;

