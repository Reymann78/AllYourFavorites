import React, { useContext, useState } from 'react';
import Header from '../commons/Header';
import styled from 'styled-components/macro';
import UserContext from '../contexts/UserContext';
import { useHistory } from 'react-router-dom';

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
        <Form onSubmit={handleSubmit}>
          <Label>
            <input
              name="username"
              value={credentials.username}
              onChange={handleChange}
              type="text"
              placeholder="Username"
            />
          </Label>
          <Label>
            <input
              name="password"
              value={credentials.password}
              onChange={handleChange}
              type="password"
              placeholder="Password"
            />
          </Label>
          {error && <p>{error}</p>}
          <Button>Login</Button>
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

const Main = styled.main`
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: var(--size-xxl);
  color: var(--blue-main);
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
    width: 100%;
    font-size: var(--size-l);
    color: var(--blue-main);
  }
`;

const Label = styled.label`
  //color: var(--blue-main);
`;

const Button = styled.button`
  padding: var(--size-m);
  border: none;
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  color: var(--white);
  border-radius: var(--size-s);
  font-size: 1em;
  font-weight: 600;
`;
