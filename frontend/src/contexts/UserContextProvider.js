import UserContext from './UserContext';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import {
  deleteTokenFromLocalStorage,
  deleteUserDataFromLocalStorage,
  loadTokenFromLocalStorage,
  loadUserDataFromLocalStorage,
  saveTokenToLocalStorage,
  saveUserDataToLocalStorage,
} from '../service/localStorage';

export default function UserContextProvider({ children }) {
  const [token, setToken] = useState(loadTokenFromLocalStorage());
  const [userData, setUserData] = useState(loadUserDataFromLocalStorage());

  useEffect(() => {
    if (token) {
      try {
        const decoded = jwtDecode(token);
        if (decoded.exp > new Date().getTime() / 1000) {
          setUserData(decoded);
          saveTokenToLocalStorage(token);
          saveUserDataToLocalStorage(decoded);
        }
      } catch (e) {
        console.log(e);
      }
    }
  }, [token]);

  const tokenIsValid = () =>
    token && userData?.exp > new Date().getTime() / 1000;

  const postLogin = (loginData) =>
    axios
      .post('/auth/login', loginData)
      .then((response) => setToken(response.data));

  const postSignUp = (signUpData) =>
    axios
        .post('/auth/signup', signUpData)
        .then((response) => response.data);

  function logout() {
    deleteTokenFromLocalStorage();
    setToken('');
    deleteUserDataFromLocalStorage();
    setUserData('');
  }

  return (
    <UserContext.Provider
      value={{ token, userData, tokenIsValid, postLogin, logout, postSignUp }}
    >
      {children}
    </UserContext.Provider>
  );
}
