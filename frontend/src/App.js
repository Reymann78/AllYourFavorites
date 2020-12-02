import React from 'react';
import styled from 'styled-components/macro';
import HomePage from './homePage/HomePage';
import { Redirect, Switch, Route } from 'react-router-dom';
import SoccerTeamContextProvider from './contexts/SoccerTeamContextProvider';
import OpenMenuContextProvider from './contexts/OpenMenuContextProvider';
import LoginPage from './loginPage/LoginPage';
import UserContextProvider from './contexts/UserContextProvider';
import ProtectedRoute from './routing/ProtectedRoute';
import FavoriteContextProvider from './contexts/FavoriteContextProvider';

function App() {
  return (
    <UserContextProvider>
      <FavoriteContextProvider>
        <SoccerTeamContextProvider>
          <OpenMenuContextProvider>
            <PageLayout>
              <Switch>
                <Route path="/login" component={LoginPage} />
                <ProtectedRoute path="/favorites" component={HomePage} />
                <Route path="/">
                  <Redirect to="/login" />
                </Route>
              </Switch>
            </PageLayout>
          </OpenMenuContextProvider>
        </SoccerTeamContextProvider>
      </FavoriteContextProvider>
    </UserContextProvider>
  );
}

export default App;

const PageLayout = styled.div`
  display: grid;
  grid-template-rows: min-content 1fr;
  height: 100vh;
`;
