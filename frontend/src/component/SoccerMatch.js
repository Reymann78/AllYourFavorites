import React, {useContext, useState} from 'react';
import formatDate from "../utils/DateUtil";
import Table from "../commons/Table";
import PositionList from "../lists/PositionList";
import MatchDayList from "../lists/MatchDayList";
import styled from "styled-components/macro";
import FavoriteContext from "../contexts/FavoriteContext";
import RequestButton from "../button/RequestButton";

export default function SoccerMatch({ match }) {
    const { getLeagueTable, getMatchDayTableByMatchDay } = useContext(FavoriteContext);
    const [requestedTable, setRequestedTable] = useState('');
    const [isOpen, setIsOpen] = useState(false);

    return (
        <Match>
            <div className="competition">
                {match.competitionName}
            </div>
            <div className="round">Spieltag: {match.matchDay}</div>
            <time className="date">
                {formatDate(match.matchDate)}
            </time>
            <div>{match.homeTeam.name}</div>
            <div className={match.status === "IN_PLAY" || match.status === "PAUSED" ? "live" : "result"}>
                {`${match.homeTeamGoals} : ${match.awayTeamGoals}`}
            </div>
            <div>{match.awayTeam.name}</div>
            <div className="dayTable">
                <RequestButton onClick={() => {
                    handleMatchDayTableRequest();
                    setIsOpen(true)
                }}>Spieltag</RequestButton>
            </div>
            <div className="leagueTable">
                <RequestButton onClick={() => {
                    handleLeagueTableRequest();
                    setIsOpen(true)
                }}>Tabelle</RequestButton>
            </div>
            <Table open={isOpen} onClose={() => setIsOpen(false)}>
                {requestedTable === 'leagueTable' ? <PositionList/> : <MatchDayList/>}
            </Table>
        </Match>
    )

    function handleLeagueTableRequest() {
        setRequestedTable('leagueTable');
        getLeagueTable(match.competitionId, match.matchDay, match.groupName, "TOTAL");
    }

    function handleMatchDayTableRequest() {
        setRequestedTable('matchDayTable');
        getMatchDayTableByMatchDay(match.competitionId, match.matchDay);
    }
}

const Match = styled.section`
  color: var(--blue-main);
  display: grid;
  grid-template-columns: 3fr 1fr 3fr;
  grid-template-rows: auto auto 2fr auto;
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
    padding: var(--size-xs);
    font-weight: 600;
  }
  
  .live {
    color: red;
    padding: var(--size-xs);
    font-weight: 600;
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