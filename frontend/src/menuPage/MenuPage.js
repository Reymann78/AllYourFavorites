import React from 'react';
import { useHistory } from 'react-router-dom';
import Header from '../commons/Header';
import styled from 'styled-components/macro';
import ActionButton from '../commons/ActionButton';

export default function MenuPage() {
  const history = useHistory();

  return (
    <>
      <Header title="Menu" />
      <ButtonGroupStyled>
        <ActionButton key="add" onClick={() => history.push('/add')}>
          Add favorite
        </ActionButton>
        <ActionButton key="remove" onClick={() => history.push('/remove')}>
          Remove favorite
        </ActionButton>
        <ActionButton key="home" onClick={() => history.push('/home')}>
          Back to favorites
        </ActionButton>
      </ButtonGroupStyled>
    </>
  );
}

const ButtonGroupStyled = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
`;
