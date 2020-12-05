import SoccerTeamContext from './SoccerTeamContext';
import React, { useState, useEffect, useContext } from 'react';
import { getSoccerTeams } from '../service/SoccerTeamService';
import UserContext from './UserContext';

export default function SoccerTeamContextProvider({ children }) {
  const [soccerTeams, setSoccerTeams] = useState([]);
  const { token, tokenIsValid } = useContext(UserContext);

  useEffect(() => {
    tokenIsValid &&
      getSoccerTeams(token).then(setSoccerTeams).catch(console.log);
  }, [token, tokenIsValid]);

  return (
    <SoccerTeamContext.Provider value={{ soccerTeams }}>
      {children}
    </SoccerTeamContext.Provider>
  );
}
