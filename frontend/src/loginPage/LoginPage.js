import React, { useContext, useState } from 'react';
import Header from '../component/Header';
import styled from 'styled-components/macro';
import UserContext from '../contexts/UserContext';
import { useHistory } from 'react-router-dom';
import appLogo from '../assets/allYourFavoritesLogo.png';
import ActionButton from "../button/ActionButton";

const emptyCredentials = {
  username: '',
  password: '',
};

export default function LoginPage() {
  const { postLogin } = useContext(UserContext);
  const [credentials, setCredentials] = useState(emptyCredentials);
  const history = useHistory();
  const [error, setError] = useState('');
  return (
    <>
      <Header title="Login" />
      <Main>
        <AppLogo src={appLogo} alt="appLogo" />
        <Form onSubmit={handleSubmit}>
          <Label>
            Username
            <input
              name="username"
              value={credentials.username}
              onChange={handleChange}
              type="text"
            />
          </Label>
          <Label>
            Password
            <input
              name="password"
              value={credentials.password}
              onChange={handleChange}
              type="password"
            />
          </Label>
          {error && <p>{error}</p>}
          <ActionButton key="login" type="submit">Login</ActionButton>
          <ActionButton key="signUp" type="button" onClick={() => history.push('/signUp')}>
            Neu anmelden
          </ActionButton>
        </Form>
      </Main>
    </>
  );

  function handleSubmit(event) {
    event.preventDefault();
    postLogin(credentials)
      .then(() => history.push('/favorites'))
      .catch(() => setError('Username oder Passwort sind nicht bekannt!'));
  }

  function handleChange(event) {
    setCredentials({ ...credentials, [event.target.name]: event.target.value });
  }
}

const AppLogo = styled.img`
  width: 60%;
`;

const Main = styled.main`
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: var(--size-xxl);
  color: var(--blue-main);
  text-shadow: 1px 1px 1px var(--blue-25);
`;

const Form = styled.form`
  width: 100%;
  height: border-box;
  display: grid;
  grid-auto-rows: min-content;
  gap: var(--size-xxl);
  *:focus {
    outline: none;
  }

  input {
    border-color: var(--blue-50);
    display: block;
    width: 98%;
    height: 30px;
    font-size: var(--size-l);
    color: var(--blue-main);
    box-shadow: 2px 2px 2px var(--blue-25);
    border-radius: var(--size-s);
  }
`;

const Label = styled.label`
  font-size: 1.2em;
`;
