import React, { useContext, useState } from 'react';
import Header from '../commons/Header';
import styled from 'styled-components/macro';
import UserContext from '../contexts/UserContext';
import { useHistory } from 'react-router-dom';

const initialState = {
  username: '',
  password: '',
};

export default function LoginPage() {
  const { postLogin } = useContext(UserContext);
  const [loginData, setLoginData] = useState({ initialState });
  const history = useHistory();
  const [error, setError] = useState('');
  return (
    <>
      <Header title="Login" />
      <Form onSubmit={handleSubmit}>
        <Label>
          Username
          <input
            name="username"
            value={loginData.username}
            onChange={handleChange}
            type="text"
          />
        </Label>
        <Label>
          Password
          <input
            name="password"
            value={loginData.password}
            onChange={handleChange}
            type="password"
          />
        </Label>
        {error && <p>error</p>}
        <Button>Login</Button>
      </Form>
    </>
  );

  function handleSubmit(event) {
    event.preventDefault();
    postLogin(loginData)
      .then(() => history.push('/'))
      .catch(() => setError('Unknown username or password!'));
  }

  function handleChange(event) {
    setLoginData({ ...loginData, [event.target.name]: event.target.value });
  }
}

const Form = styled.form`
  padding: var(--size-xl);
  display: grid;
  grid-auto-rows: min-content;
  gap: var(--size-xl);

  input {
    border-color: var(--blue-main);
    display: block;
    width: 100%;
  }
`;

const Label = styled.label`
  color: var(--blue-main);
`;

// const Input = styled.input`
//   border-color: var(--blue-main);
//   display: block;
//   width: 100%;
// `;

const Button = styled.button`
  padding: var(--size-m);
  border: none;
  background: var(--blue-main);
  color: var(--white);
  border-radius: var(--size-s);
  font-size: 1em;
`;
