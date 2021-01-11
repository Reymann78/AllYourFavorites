import React, {useContext} from 'react';
import styled from 'styled-components/macro';
import Position from '../commons/Position';
import FavoriteContext from "../contexts/FavoriteContext";

export default function PositionList() {
    const { leagueTable } = useContext(FavoriteContext);

    return (
        <PositionListStyled>
            <TableHead>
                <div>Platz</div>
                <div></div>
                <div>Team</div>
                <div>Sp.</div>
                <div>Tore</div>
                <div>Diff.</div>
                <div>Pkt.</div>
            </TableHead>
                {leagueTable.positions && leagueTable.positions.map((pos) => (
                    <li key={pos.position}>
                        <Position position={pos} />
                    </li>
            ))}
        </PositionListStyled>
    );
}

const TableHead = styled.div`
   display: grid;
   grid-template-columns: 1fr 1fr 6fr 1fr 2fr 1fr 1fr;
   align-items: center;
   color: var(--blue-main);
   text-align: start;
   font-weight: 600;
   font-size: .6rem;
   padding: 0.2rem;
   border-bottom: 2px solid var(--blue-main);
`;

const PositionListStyled = styled.ul`
  overflow: scroll;
  margin: 0;
  padding: var(--size-s);
  list-style: none;

  li:last-child:after {
    content: '';
    display: block;
    height: 4px;
  }
`;