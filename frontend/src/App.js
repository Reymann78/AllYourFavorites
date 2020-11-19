import React from 'react';
import styled from 'styled-components/macro';
import HomePage from './homePage/HomePage';
import MenuPage from './menuPage/MenuPage';
import { Switch, Route } from 'react-router-dom';
import AddFavoritePage from './addFavoritePage/AddFavoritePage';
import RemoveFavoritePage from './removeFavoritePage/RemoveFavoritePage';

function App() {
  return (
    <>
      <PageLayout>
        <Switch>
          <Route exact path={['/', '/home']}>
            <HomePage />
          </Route>
          <Route path="/menu" component={MenuPage} />
          <Route path="/add" component={AddFavoritePage} />
          <Route path="/remove" component={RemoveFavoritePage} />
        </Switch>
      </PageLayout>
    </>
  );
}

export default App;

const PageLayout = styled.div`
  display: grid;
  grid-template-rows: min-content 1fr;
  height: 100vh;
`;
