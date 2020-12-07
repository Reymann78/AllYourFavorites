import FavoriteContext from './FavoriteContext';
import React, { useContext, useEffect, useState } from 'react';
import {
  addFavorite,
  getFavorites,
  removeFavorite,
} from '../service/FavoriteService';
import UserContext from './UserContext';

export default function FavoriteContextProvider({ children }) {
  const [favorites, setFavorites] = useState([]);
  const { token, tokenIsValid } = useContext(UserContext);

  useEffect(() => {
    tokenIsValid() && getFavorites(token).then(setFavorites).catch(console.log);
  }, [token, tokenIsValid]);

  const createFavorite = (teamId) =>
    addFavorite(teamId, token)
      .then(() => token && getFavorites(token).then(setFavorites))
      .catch(console.log);

  const deleteFavorite = (teamId) =>
    removeFavorite(teamId, token)
      .then(() =>
        setFavorites(favorites.filter((favorite) => favorite.teamId !== teamId))
      )
      .catch(console.log);

  return (
    <FavoriteContext.Provider
      value={{ favorites, createFavorite, deleteFavorite }}
    >
      {children}
    </FavoriteContext.Provider>
  );
}
