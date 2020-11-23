import SoccerTeamContext from './SoccerTeamContext';
import React, { useState, useEffect } from 'react';
import { getSoccerTeams } from '../service/SoccerTeamService';

export default function SoccerTeamContextProvider({ children }) {
  const [soccerTeams, setSoccerTeams] = useState([]);

  useEffect(() => {
    getSoccerTeams().then(setSoccerTeams).catch(console.log);
  }, []);

  return (
    <SoccerTeamContext.Provider value={{ soccerTeams }}>
      {children}
    </SoccerTeamContext.Provider>
  );
}
