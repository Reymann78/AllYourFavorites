import React, { useContext, useState } from 'react';
import styled from 'styled-components/macro';
import { CgRemoveR } from 'react-icons/cg';
import FavoriteContext from '../contexts/FavoriteContext';
import Table from './Table'
import PositionList from "../homePage/PositionList";
import MatchDayList from "../homePage/MatchDayList";
import formatDate from "../utils/DateUtil";

export default function Favorite({ favorite, className }) {
  const { deleteFavorite, getLeagueTable, getMatchDayTableByMatchDay } = useContext(FavoriteContext);
  const [isOpen, setIsOpen] = useState(false);
  const [requestedTable, setRequestedTable] = useState('');

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
        <div className="dayTable">
          <RequestButton onClick={() => { handleMatchDayTableRequest(); setIsOpen(true) }}>Spieltag</RequestButton>
        </div>
        <div className="leagueTable">
          <RequestButton onClick={() => { handleLeagueTableRequest(); setIsOpen(true) }}>Tabelle</RequestButton>
        </div>
          <Table open={isOpen} onClose={() => setIsOpen(false)} >
            {requestedTable === 'leagueTable' ? <PositionList /> : <MatchDayList />}
          </Table>
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

  function handleLeagueTableRequest() {
    setRequestedTable('leagueTable');
    getLeagueTable(favorite.currentMatch.competitionId, favorite.currentMatch.matchDay, favorite.currentMatch.groupName, "TOTAL");
  }

  function handleMatchDayTableRequest() {
    setRequestedTable('matchDayTable');
    getMatchDayTableByMatchDay(favorite.currentMatch.competitionId, favorite.currentMatch.matchDay);
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
  grid-template-rows: 1fr 1fr 1fr 1fr;
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
  
  .dayTable {
  grid-column: 0/1;
  text-align: center;
  }
  
  .leagueTable {
  grid-column: 3/4;
  text-align: center;
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
    color: var(--blue-25);
  }
`;

const RequestButton = styled.button`
  --button-size: calc(var(--nav-size) * 1.6);
  width: var(--button-size);
  height: calc(var(--button-size) / 3);
  font-size: var(--size-m);
  background-color: var(--blue-75);
  border-radius: var(--size-s);  

  &:hover {
    color: var(--blue-25);
  }
`;

