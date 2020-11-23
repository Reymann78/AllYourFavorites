import React from 'react';
import styled from 'styled-components/macro';
import HomePage from './homePage/HomePage';
import { Switch, Route } from 'react-router-dom';
import SoccerTeamContextProvider from './contexts/SoccerTeamContextProvider';
import OpenMenuContextProvider from './contexts/OpenMenuContextProvider';

function App() {
  return (
    <SoccerTeamContextProvider>
      <OpenMenuContextProvider>
        <PageLayout>
          <Switch>
            <Route exact path={['/', '/home']}>
              <HomePage />
            </Route>
          </Switch>
        </PageLayout>
      </OpenMenuContextProvider>
    </SoccerTeamContextProvider>
  );
}

export default App;

const PageLayout = styled.div`
  display: grid;
  grid-template-rows: min-content 1fr;
  height: 100vh;
`;
