import React from 'react';
import styled from 'styled-components/macro';
import { AiOutlineMenu } from 'react-icons/all';
import { useHistory } from 'react-router-dom';

export default function Header({ title }) {
  const history = useHistory();

  return (
    <HeaderStyled>
      <HeadingStyled>{title}</HeadingStyled>
      <button onClick={() => history.push('/menu/')}>
        <AiOutlineMenu />
      </button>
    </HeaderStyled>
  );
}

const HeaderStyled = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-transform: uppercase;
  font-size: var(--size-m);
  background: linear-gradient(20deg, var(--blue-75), var(--blue-25));
  padding: var(--size-xl);
`;

const HeadingStyled = styled.h1`
  margin: 0;
`;
