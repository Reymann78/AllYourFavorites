import FavoriteContext from './FavoriteContext';
import React, { useContext, useEffect, useState } from 'react';
import { addFavorite, getFavorites } from '../service/FavoriteService';
import UserContext from './UserContext';

export default function FavoriteContextProvider({ children }) {
  const [favorites, setFavorites] = useState([]);
  const { token } = useContext(UserContext);

  useEffect(() => {
    token && getFavorites(token).then(setFavorites).catch(console.log);
  }, [token]);

  const createFavorite = (teamId) =>
    addFavorite(teamId, token)
      .then((newFavorite) => setFavorites([...favorites, newFavorite]))
      .catch(console.log);

  return (
    <FavoriteContext.Provider value={{ favorites, createFavorite }}>
      {children}
    </FavoriteContext.Provider>
  );
}
