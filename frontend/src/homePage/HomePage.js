import React from 'react';
import Header from '../component/Header';
import FavoriteList from '../lists/FavoriteList';

export default function HomePage() {
  return (
    <>
      <Header title="All your Favorites" />
      <FavoriteList />
    </>
  );
}
