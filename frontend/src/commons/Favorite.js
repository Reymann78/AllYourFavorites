import React from 'react';
import styled from 'styled-components/macro';
import { CgRemoveR } from 'react-icons/cg';

export default function Favorite({ favorite, className }) {
  return (
    <FavoriteWrapper className={className}>
      <Header>
        <Logo src={favorite.crestUrl} alt="Team Logo" />
        {favorite.name}
        <RemoveButton>{<CgRemoveR />}</RemoveButton>
      </Header>
      <Match>
        <time class="date">Sa 28.11.2020 15:30 Uhr</time>
        <div>{favorite.lastMatch.homeTeam}</div>
        <div class="result">
          {favorite.lastMatch.homeTeamGoals} :{' '}
          {favorite.lastMatch.awayTeamGoals}
        </div>
        <div>{favorite.lastMatch.awayTeam}</div>
      </Match>
      <Match>
        <time className="date">Mi 02.12.2020 21:00 Uhr</time>
        <div>{favorite.currentMatch.homeTeam}</div>
        <div class="result">
          {favorite.currentMatch.homeTeamGoals} :{' '}
          {favorite.currentMatch.awayTeamGoals}
        </div>
        <div>{favorite.currentMatch.awayTeam}</div>
      </Match>
      <Match>
        <time className="date">Sa 05.12.2020 15:30 Uhr</time>
        <div>{favorite.nextMatch.homeTeam}</div>
        <div class="result">
          {favorite.nextMatch.homeTeamGoals} :{' '}
          {favorite.nextMatch.awayTeamGoals}
        </div>
        <div>{favorite.nextMatch.awayTeam}</div>
      </Match>
    </FavoriteWrapper>
  );
}

const FavoriteWrapper = styled.section`
  padding: var(--size-s);
  box-shadow: 2px 2px 2px var(--blue-75);
  border: var(--blue-border);
  border-radius: var(--size-s);
  background: #eff8fb;
`;

const Header = styled.header`
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
  font-weight: bold;
  font-size: var(--size-xl);
  background-color: #eff8fb;
  padding: 0;
  border: 0;

  &:hover {
    color: darkgrey;
  }
`;
