import React from 'react';
import styled from 'styled-components/macro';
import SoccerMatch from "../component/SoccerMatch";

export default function SoccerMatchList( {favorite} ) {

    return (
        <MatchList>
            {favorite.matches && favorite.matches.map((match) => (
                <li key={match.id}>
                    <SoccerMatch match={match} />
                </li>
            ))}
        </MatchList>
    );
}

const MatchList = styled.ul`
 list-style: none;
`;