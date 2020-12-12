import React, { useState, useContext } from 'react';
import UserContext from '../contexts/UserContext';
import { useHistory } from 'react-router-dom';
import styled from 'styled-components/macro';
import Header from '../commons/Header';

const initialState = {
  username: '',
  email: '',
  password: '',
};

export default function SignUpPage() {
  const [signUpData, setSignUpData] = useState(initialState);
  const [firstPassword, setFirstPassword] = useState('');
  const [secondPassword, setSecondPassword] = useState('');
  const [errorFrontend, setErrorFrontend] = useState('');
  const [errorBackend, setErrorBackend] = useState(0);
  const { postSignUp } = useContext(UserContext);
  const history = useHistory();

  return (
    <>
      <Header title="Neu Anmelden" />
      <Main>
        <Form onSubmit={handleSubmit}>
          <Label>
            Username
            <input
              name="username"
              value={signUpData.username}
              onChange={handleChange}
              required
            />
          </Label>
          <Label>
            E-Mail-Adresse
            <input
              name="email"
              value={signUpData.email}
              onChange={handleChange}
              type="email"
              required
            />
          </Label>
          <Label>
            Passwort
            <input
              name="firstPassword"
              value={firstPassword}
              onChange={(event) => setFirstPassword(event.target.value)}
              type="password"
              required
            />
          </Label>
          <Label>
            Passwort wiederholen
            <input
              name="secondPassword"
              value={secondPassword}
              onChange={(event) => setSecondPassword(event.target.value)}
              type="password"
              required
            />
          </Label>
          <ErrorStyling>
            {errorFrontend ?? <p>{errorFrontend}</p>}
            {errorBackend === 400 && <p>Der Username ist bereist vorhanden!</p>}
            {errorBackend === 403 && (
              <p>Das Passwort entspricht nicht den Vorgaben (siehe unten)</p>
            )}
          </ErrorStyling>
          <Button type="submit">anmelden</Button>
          <Text>
            <p>
              Das Passwort muss aus mind. <strong>8 Zeichen</strong> bestehen
              und jeweils mind. einen <strong>Kleinbuchstaben</strong>, einen{' '}
              <strong>Großbuchstaben</strong> und eine <strong>Zahl</strong>{' '}
              enthalten.
            </p>
          </Text>
        </Form>
      </Main>
    </>
  );

  function handleChange(event) {
    setSignUpData({ ...signUpData, [event.target.name]: event.target.value });
  }

  function handleSubmit(event) {
    event.preventDefault();
    try {
      checkIfPasswordMatch();
      validatePassword();
      const signUpDataWithPassword = { ...signUpData, password: firstPassword };

      postSignUp(signUpDataWithPassword)
        .then(() => history.push('/login'))
        .catch((error) => setErrorBackend(error.response.status));
    } catch (e) {
      setErrorFrontend(e.message);
      clearPasswords();
    }
  }

  function clearPasswords() {
    setFirstPassword('');
    setSecondPassword('');
  }

  function checkIfPasswordMatch() {
    if (firstPassword !== secondPassword) {
      throw new Error('Die Passwörter stimmen nicht überein');
    }
  }

  function validatePassword() {
    checkPasswordLength();
    checkIfPasswordContainsNumbers();
    checkIfPasswordContainsLowercaseLetters();
    checkIfPasswordContainsUppercaseLetters();
  }

  function checkPasswordLength() {
    if (firstPassword.length < 8) {
      throw new Error('Das Passwort muss aus mind. 8 Zeichen bestehen!');
    }
  }
  function checkIfPasswordContainsNumbers() {
    if (!/\d/.test(firstPassword)) {
      throw new Error('Das Passwort muss mind. eine Zahl enthalten!');
    }
  }
  function checkIfPasswordContainsLowercaseLetters() {
    if (!/[a-z]/.test(firstPassword)) {
      throw new Error(
        'Das Passwort muss mind. einen Kleinbuchstaben enthalten!'
      );
    }
  }
  function checkIfPasswordContainsUppercaseLetters() {
    if (!/[A-Z]/.test(firstPassword)) {
      throw new Error(
        'Das Passwort muss mind. einen Großbuchstaben enthalten!'
      );
    }
  }
}

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

const Text = styled.span`
  font-size: var(--size-m);
  text-align: center;
`;

const ErrorStyling = styled.span`
  color: red;
  font-size: var(--size-l);
  text-align: center;
`;

const Button = styled.button`
  padding: var(--size-s);
  border: none;
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  color: var(--white);
  border-radius: var(--size-s);
  font-size: 1em;
  font-weight: 600;
  box-shadow: 3px 3px 3px var(--blue-50); ;
`;
