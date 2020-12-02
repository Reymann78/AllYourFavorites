import FavoriteContext from './FavoriteContext';
import React, { useContext, useState } from 'react';
import { addFavorite } from '../service/FavoriteService';
import UserContext from './UserContext';

export default function FavoriteContextProvider({ children }) {
  const [favorites, setFavorites] = useState([]);
  const { token } = useContext(UserContext);

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
