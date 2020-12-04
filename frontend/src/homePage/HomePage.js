import React from 'react';
import Header from '../commons/Header';
import FavoriteList from './FavoriteList';

export default function HomePage() {
  return (
    <>
      <Header title="All your Favorites" />
      <FavoriteList />
    </>
  );
}
