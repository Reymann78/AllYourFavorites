import UserContext from './UserContext';
import React, { useState } from 'react';
import axios from 'axios';

export default function UserContextProvider({ children }) {
  const [token, setToken] = useState();

  const postLogin = (loginData) =>
    axios
      .post('/auth/login', loginData)
      .then((response) => setToken(response.data));

  return (
    <UserContext.Provider value={{ token, postLogin }}>
      {children}
    </UserContext.Provider>
  );
}
