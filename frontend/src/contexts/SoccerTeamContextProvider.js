import SoccerTeamContext from './SoccerTeamContext';
import React, { useState, useEffect, useContext } from 'react';
import { getSoccerTeams } from '../service/SoccerTeamService';
import UserContext from './UserContext';

export default function SoccerTeamContextProvider({ children }) {
  const [soccerTeams, setSoccerTeams] = useState([]);
  const { token } = useContext(UserContext);

  useEffect(() => {
    token && getSoccerTeams(token).then(setSoccerTeams).catch(console.log);
  }, [token]);

  return (
    <SoccerTeamContext.Provider value={{ soccerTeams }}>
      {children}
    </SoccerTeamContext.Provider>
  );
}
