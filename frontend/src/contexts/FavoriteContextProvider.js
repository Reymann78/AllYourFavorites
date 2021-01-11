import FavoriteContext from './FavoriteContext';
import React, { useContext, useEffect, useState } from 'react';
import {
  addFavorite,
  getFavorites,
  removeFavorite,
  getStanding
} from '../service/favoriteService';
import UserContext from './UserContext';

export default function FavoriteContextProvider({ children }) {
  const [favorites, setFavorites] = useState([]);
  const [leagueTable, setLeagueTable] = useState([])
  const { token, tokenIsValid } = useContext(UserContext);

  useEffect(() => {
    tokenIsValid() && getFavorites(token).then(setFavorites).catch(console.log);
  }, [token, tokenIsValid]);

  const createFavorite = (teamId) =>
    addFavorite(teamId, token)
      .then(() => token && getFavorites(token)
      .then(setFavorites))
      .catch(console.log);

  const deleteFavorite = (teamId) =>
    removeFavorite(teamId, token)
      .then(() =>
        setFavorites(favorites.filter((favorite) => favorite.teamId !== teamId))
      )
      .catch(console.log);

  const getLeagueTable = (competitionId, matchDay, groupName, tableType) =>
      getStanding(competitionId, matchDay, groupName, tableType, token)
          .then(setLeagueTable)
          .catch(console.log);

  return (
    <FavoriteContext.Provider
      value={{ favorites, leagueTable, getLeagueTable, createFavorite, deleteFavorite }}
    >
      {children}
    </FavoriteContext.Provider>
  );
}
