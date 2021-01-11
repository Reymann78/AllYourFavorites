import React from 'react';
import styled from 'styled-components/macro';

export default function Position( { position }) {

    return (
        <>
            <PositionStyled>
                <div>{position.position}.</div>
                <div><Logo src={position.crestUrl} alt="TeamLogo" /></div>
                <div>{position.name}</div>
                <div>{position.playedGames}</div>
                <div>{`${position.goalsFor} : ${position.goalsAgainst}`}</div>
                <div>{position.goalDifference}</div>
                <div>{position.points}</div>
            </PositionStyled>
        </>
    )
}

const PositionStyled = styled.div`
   display: grid;
   grid-template-columns: 1fr 1fr 6fr 1fr 2fr 1fr 1fr;
   align-items: center;
   color: var(--blue-main);
   text-align: start;
   font-weight: 600;
   font-size: .6rem;
   padding: 0.4rem   
`;

const Logo = styled.img`
  height: 1.2rem;
  padding-right: var(--size-xs);
  // justify-self: end;
  font-weight: 600;
`;