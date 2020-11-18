import React from 'react';
import styled from 'styled-components/macro';

export default function ActionButton({ children, ...rest }) {
  return <ActionButtonStyled {...rest}>{children}</ActionButtonStyled>;
}

const ActionButtonStyled = styled.button`
  color: #ffffff;
  height: 60px;
  border: var(--blue-border);
  border-radius: var(--size-s);
  background: linear-gradient(20deg, var(--blue-75), var(--blue-25));
  font-size: var(--size-l);
  margin-top: 50px;
  margin-right: 50px;
  margin-left: 50px;
`;
