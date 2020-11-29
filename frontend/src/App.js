import React from 'react';
import styled from 'styled-components/macro';
import HomePage from './homePage/HomePage';
import { Switch, Route } from 'react-router-dom';
import SoccerTeamContextProvider from './contexts/SoccerTeamContextProvider';
import OpenMenuContextProvider from './contexts/OpenMenuContextProvider';
import LoginPage from './loginPage/LoginPage';
import UserContextProvider from './contexts/UserContextProvider';

function App() {
  return (
    <UserContextProvider>
      <SoccerTeamContextProvider>
        <OpenMenuContextProvider>
          <PageLayout>
            <Switch>
              <Route path="/login">
                <LoginPage />
              </Route>
              <Route exact path={['/', '/api/favorites/soccerTeams']}>
                <HomePage />
              </Route>
            </Switch>
          </PageLayout>
        </OpenMenuContextProvider>
      </SoccerTeamContextProvider>
    </UserContextProvider>
  );
}

export default App;

const PageLayout = styled.div`
  display: grid;
  grid-template-rows: min-content 1fr;
  height: 100vh;
`;
