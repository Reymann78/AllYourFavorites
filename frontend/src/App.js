import React from 'react';
import HomePage from './homePage/HomePage';
import MenuPage from './menuPage/MenuPage';
import { Switch, Route } from 'react-router-dom';
import AddFavoritePage from './addFavoritePage/AddFavoritePage';
import RemoveFavoritePage from './removeFavoritePage/RemoveFavoritePage';

function App() {
  return (
    <div>
      <Switch>
        <Route exact path={['/', '/home']}>
          <HomePage />
        </Route>
        <Route path="/menu" component={MenuPage} />
        <Route path="/add" component={AddFavoritePage} />
        <Route path="/remove" component={RemoveFavoritePage} />
      </Switch>
    </div>
  );
}

export default App;
