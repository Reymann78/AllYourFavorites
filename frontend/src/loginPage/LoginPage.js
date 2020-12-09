import React, { useContext, useState } from 'react';
import Header from '../commons/Header';
import styled from 'styled-components/macro';
import UserContext from '../contexts/UserContext';
import { useHistory } from 'react-router-dom';
import appLogo from '../assets/allYourFavoritesLogo.png';

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
      <Header title="All Your Favorites" />
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
          <Button value="login">Login</Button>
        </Form>
      </Main>
    </>
  );

  function handleSubmit(event) {
    event.preventDefault();
    postLogin(credentials)
      .then(() => history.push('/favorites'))
      .catch(() => setError('Unknown username or password!'));
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
  text-shadow: 1px 1px 1px var(--blue-50);
`;

const Form = styled.form`
  width: 100%;
  height: border-box;
  display: grid;
  grid-auto-rows: min-content;
  gap: var(--size-xxl);

  input {
    border-color: var(--blue-main);
    display: block;
    width: 98%;
    height: 30px;
    font-size: var(--size-l);
    color: var(--blue-main);
    box-shadow: 3px 3px 3px var(--blue-50);
    border-radius: var(--size-s);
  }
`;

const Label = styled.label`
  font-size: 1.2em;
`;

const Button = styled.button`
  margin-top: var(--size-xxl);
  padding: var(--size-m);
  border: none;
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  color: var(--white);
  border-radius: var(--size-s);
  font-size: 1em;
  font-weight: 600;
  box-shadow: 3px 3px 3px var(--blue-50); ;
`;
