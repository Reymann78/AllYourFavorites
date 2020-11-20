import React from 'react';

export default function SoccerTeamList({ soccerTeams }) {
  return (
    <ul>
      {soccerTeams?.map((soccerTeam) => (
        <li key={soccerTeam.name}>{soccerTeam.name}</li>
      ))}
    </ul>
  );
}
