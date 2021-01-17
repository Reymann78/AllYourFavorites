import React, {useContext} from 'react';
import styled from 'styled-components/macro';
import MatchPair from "../commons/MatchPair";
import FavoriteContext from "../contexts/FavoriteContext";

export default function MatchDayList() {
    const { matchDayTable } = useContext(FavoriteContext);

    return (
        <MatchDayListStyled>
            <TableHead>
                <div>{matchDayTable.competitionName}</div>
                <div>{matchDayTable.tableMatchDay}. Spieltag</div>
            </TableHead>
            {matchDayTable.matches && matchDayTable.matches.map((pair) => (
                <li key={pair.id}>
                    <MatchPair pair={pair} />
                </li>
            ))}
        </MatchDayListStyled>
    );
}

const TableHead = styled.div`
  display: grid;
  grid-template-columns: 1fr;
  align-items: center;
  color: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  text-align: center;
  font-weight: 600;
  font-size: .6rem;
  padding: 0.2rem;
  border-bottom: 2px solid var(--blue-main);
`;

const MatchDayListStyled = styled.ul`
  overflow: scroll;
  height: 50vh;
  width: 35vh;
  margin: 0;
  padding: var(--size-s);
  list-style: none;

  li:last-child:after {
    content: '';
    display: block;
    height: 4px;
  }
`;