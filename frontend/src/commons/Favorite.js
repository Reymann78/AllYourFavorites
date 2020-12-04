import React from 'react';
import styled from 'styled-components/macro';
import { RiDeleteBin6Line } from 'react-icons/ri';

export default function Favorite({ favorite, className }) {
  return (
    <FavoriteStyled className={className}>
      <FavoriteHeader>
        <LogoStyled src={favorite.crestUrl} alt="Team Logo" />
        {favorite.name}
        <RemoveButtonStyled>{<RiDeleteBin6Line />}</RemoveButtonStyled>
      </FavoriteHeader>
      <MatchStyled>
        <div class="date">Sa 28.11.2020 15:30 Uhr</div>
        <div>{favorite.lastMatch.homeTeam}</div>
        <div>
          {favorite.lastMatch.homeTeamGoals} :{' '}
          {favorite.lastMatch.awayTeamGoals}
        </div>
        <div>{favorite.lastMatch.awayTeam}</div>
      </MatchStyled>
      <MatchStyled>
        <div className="date">Mi 02.12.2020 21:00 Uhr</div>
        <div>{favorite.currentMatch.homeTeam}</div>
        <div>
          {favorite.currentMatch.homeTeamGoals} :{' '}
          {favorite.currentMatch.awayTeamGoals}
        </div>
        <div>{favorite.currentMatch.awayTeam}</div>
      </MatchStyled>
      <MatchStyled>
        <div className="date">Sa 05.12.2020 15:30 Uhr</div>
        <div>{favorite.nextMatch.homeTeam}</div>
        <div>
          {favorite.nextMatch.homeTeamGoals} :{' '}
          {favorite.nextMatch.awayTeamGoals}
        </div>
        <div>{favorite.nextMatch.awayTeam}</div>
      </MatchStyled>
    </FavoriteStyled>
  );
}

const FavoriteStyled = styled.section`
  padding: var(--size-s);
  box-shadow: var(--blue-shadow);
  border: var(--blue-border);
  border-radius: var(--size-s);
  background: #e0e6f8;
`;

const FavoriteHeader = styled.header`
  display: grid;
  grid-template-columns: 1fr 6fr 1fr;
  align-items: center;
  padding: var(--size-xs);
  color: var(--blue-main);
  text-align: center;
  font-weight: bold;
  font-size: 18px;

  .logo {
    justify-items: start;
  }
`;

const LogoStyled = styled.img`
  height: 2rem;
  padding-right: var(--size-xs);
  justify-self: end;
`;

const MatchStyled = styled.div`
  display: grid;
  grid-template-columns: 2fr 1fr 2fr;
  grid-template-rows: 1fr 3fr;
  align-items: center;
  border-bottom: var(--size-xs) solid #e0e6f8;
  padding: var(--size-s);
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));

  .date {
    text-align: center;
    grid-column: 1/4;
`;

const RemoveButtonStyled = styled.button`
  --button-size: calc(var(--nav-size) * 0.7);
  width: var(--button-size);
  height: var(--button-size);
  background-color: var(--white);
  color: firebrick;
  border-radius: var(--border-radius);
  border-color: firebrick;
  justify-self: end;

  &:hover {
    background-color: lightgrey;
  }
`;
