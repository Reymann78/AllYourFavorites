import React, { useContext } from 'react';
import styled from 'styled-components/macro';
import { CgRemoveR } from 'react-icons/cg';
import FavoriteContext from '../contexts/FavoriteContext';

export default function Favorite({ favorite, className }) {
  const { deleteFavorite } = useContext(FavoriteContext);
  return (
    <FavoriteWrapper className={className}>
      <FavoriteHeader>
        <Logo src={favorite.crestUrl} alt="Team Logo" />
        {favorite.name}
        <RemoveButton onClick={handleRemove}>{<CgRemoveR />}</RemoveButton>
      </FavoriteHeader>
      <Match>
        <div className="competition">{favorite.lastMatch.competitionName}</div>
        <div className="round">Spieltag: {favorite.lastMatch.matchDay}</div>
        <time className="date">{formatDate(favorite.lastMatch.matchDate)}</time>
        <div>{favorite.lastMatch.homeTeam.name}</div>
        <div className="result">
          {`${favorite.lastMatch.homeTeamGoals} : ${favorite.lastMatch.awayTeamGoals}`}
        </div>
        <div>{favorite.lastMatch.awayTeam.name}</div>
      </Match>
      <Match>
        <div className="competition">
          {favorite.currentMatch.competitionName}
        </div>
        <div className="round">Spieltag: {favorite.currentMatch.matchDay}</div>
        <time className="date">
          {formatDate(favorite.currentMatch.matchDate)}
        </time>
        <div>{favorite.currentMatch.homeTeam.name}</div>
        <div className="result">
          {`${favorite.currentMatch.homeTeamGoals} : ${favorite.currentMatch.awayTeamGoals}`}
        </div>
        <div>{favorite.currentMatch.awayTeam.name}</div>
      </Match>
      <Match>
        <div className="competition">{favorite.nextMatch.competitionName}</div>
        <div className="round">Spieltag: {favorite.nextMatch.matchDay}</div>
        <time className="date">{formatDate(favorite.nextMatch.matchDate)}</time>
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

  function formatDate(date) {
    let d = new Date(date);
    const weekday = new Array(7);
    weekday[0] = 'Sonntag';
    weekday[1] = 'Montag';
    weekday[2] = 'Dienstag';
    weekday[3] = 'Mittwoch';
    weekday[4] = 'Donnerstag';
    weekday[5] = 'Freitag';
    weekday[6] = 'Samstag';

    let formattedWeekday = weekday[d.getDay()] + ' ',
      day = d.getDate() + '.',
      month = d.getMonth() + 1 + '.',
      year = d.getFullYear() + ' ',
      hour = d.getHours() + ':',
      min = d.getMinutes() + ':',
      sec = '00';

    if (month.length < 3) month = '0' + month;
    if (day.length < 3) day = '0' + day;
    if (min.length < 3) min = '0' + min;

    return [formattedWeekday, day, month, year, hour, min, sec];
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

const Match = styled.section`
  color: var(--blue-main);
  display: grid;
  grid-template-columns: 3fr 1fr 3fr;
  grid-template-rows: 1fr 1fr 2fr;
  grid-gap: 2px;
  align-items: center;
  border-bottom: var(--size-xs) solid var(--blue-75);
  padding: var(--size-s);
  background: var(--white);
  font-size: 0.8em;

  .competition {
    text-align: left;
    font-size: 0.7em;
    grid-column: 1/3;
  }

  .round {
    text-align: right;
    font-size: 0.7em;
    grid-column: 0/4;
  }

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
  color: var(--white);
  justify-self: end;
  font-size: var(--size-xl);
  background-color: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  padding-bottom: 10px;
  padding-left: 10px;

  &:hover {
    color: darkgrey;
  }
`;
