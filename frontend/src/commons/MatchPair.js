import React from 'react';
import styled from 'styled-components/macro';
import formatDate from "../utils/DateUtil";

export default function MatchPair( { pair }) {

    return (
        <>
            <MatchPairStyled>
                <div className="dateOfMatch">{formatDate(pair.utcDate)}</div>
                <div className="homeTeam">{pair.homeTeam.name}.</div>
                <div className="resultOfMAtch">{`${pair.score.fullTime.homeTeam} : ${pair.score.fullTime.awayTeam}`}</div>
                <div className="awayTeam">{pair.awayTeam.name}</div>
            </MatchPairStyled>
        </>
    )
}

const MatchPairStyled = styled.div`
  display: grid;
  grid-template-rows: 1fr 1fr;
  grid-template-columns: 4fr 1fr 4fr;
  grid-gap: var(--size-xs);  
  align-items: center;
  color: var(--blue-main);
  text-align: start;
  font-size: .5rem;
  padding: .4rem;

  .dateOfMatch {
    padding: 0;
    text-align: center;
    font-weight: 600;
    grid-column: 1/4;
  }
`;