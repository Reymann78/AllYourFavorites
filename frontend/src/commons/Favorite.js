import React, { useContext } from 'react';
import styled from 'styled-components/macro';
import { CgRemoveR } from 'react-icons/cg';
import FavoriteContext from '../contexts/FavoriteContext';

export default function Favorite({ favorite, className }) {
  const { deleteFavorite } = useContext(FavoriteContext);
  console.log(favorite);
  return (
    <FavoriteWrapper className={className}>
      <FavoriteHeader>
        <Logo src={favorite.crestUrl} alt="Team Logo" />
        {favorite.name}
        <RemoveButton onClick={handleRemove}>{<CgRemoveR />}</RemoveButton>
      </FavoriteHeader>
      <Match>
        <div className="date">
          {new Date(favorite.lastMatch.matchDate).toLocaleString('de-De')}
        </div>
        <div>{favorite.lastMatch.homeTeam.name}</div>
        <div className="result">
          {`${favorite.lastMatch.homeTeamGoals} : ${favorite.lastMatch.awayTeamGoals}`}
        </div>
        <div>{favorite.lastMatch.awayTeam.name}</div>
      </Match>
      <Match>
        <time className="date">
          {new Date(favorite.currentMatch.matchDate).toLocaleString('de-De')}
        </time>
        <div>{favorite.currentMatch.homeTeam.name}</div>
        <div className="result">
          {`${favorite.currentMatch.homeTeamGoals} : ${favorite.currentMatch.awayTeamGoals}`}
        </div>
        <div>{favorite.currentMatch.awayTeam.name}</div>
      </Match>
      <Match>
        <time className="date">
          {new Date(favorite.nextMatch.matchDate).toLocaleString('de-De')}
        </time>
        <div>{favorite.nextMatch.homeTeam.name}</div>
        <div className="result">
          {`${favorite.nextMatch.homeTeamGoals} : ${favorite.nextMatch.awayTeamGoals}`}
        </div>
        <div>{favorite.nextMatch.awayTeam.name}</div>
      </Match>
    </FavoriteWrapper>
  );

  function handleRemove() {
    deleteFavorite(favorite.teamId);
  }
}

const FavoriteWrapper = styled.section`
  padding: var(--size-s);
  box-shadow: 2px 2px 2px var(--blue-75);
  border: var(--blue-border);
  border-radius: var(--size-s);
  background: #eff8fb;
`;

const FavoriteHeader = styled.header`
  display: grid;
  grid-template-columns: 1fr 6fr 1fr;
  align-items: center;
  padding-bottom: var(--size-s);
  color: var(--blue-main);
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

const Match = styled.section`
  display: grid;
  grid-template-columns: 2fr 1fr 2fr;
  grid-template-rows: 1fr 3fr;
  align-items: center;
  border-bottom: var(--size-xs) solid #eff8fb;
  padding: var(--size-s);
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));

  .date {
    text-align: center;
    font-weight: 600;
    grid-column: 1/4;
  }

  .result {
    padding: var(--size-s);
  }
`;

const RemoveButton = styled.button`
  --button-size: calc(var(--nav-size) * 0.6);
  width: var(--button-size);
  height: var(--button-size);
  color: #cc0033;
  justify-self: end;
  font-size: var(--size-xl);
  background-color: #eff8fb;
  padding-bottom: 10px;
  padding-left: 10px;

  &:hover {
    color: darkgrey;
  }
`;
