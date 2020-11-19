import React from 'react';
import styled from 'styled-components/macro';
import HomePage from './homePage/HomePage';
import MenuPage from './menuPage/MenuPage';

import AddFavoritePage from './addFavoritePage/AddFavoritePage';
import RemoveFavoritePage from './removeFavoritePage/RemoveFavoritePage';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

function App() {
  return (
    <>
      <PageLayout>
        <Router>
          <Switch>
            <Route exact path={['/', '/home']}>
              <HomePage />
            </Route>
            <Route path="/menu" component={MenuPage} />
            <Route path="/add" component={AddFavoritePage} />
            <Route path="/remove" component={RemoveFavoritePage} />
          </Switch>
        </Router>
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
