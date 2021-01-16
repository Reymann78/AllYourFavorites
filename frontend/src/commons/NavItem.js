import React, { useContext } from 'react';
import styled from 'styled-components/macro';
import OpenMenuContext from '../contexts/OpenMenuContext';

export default function NavItem(props) {
  const { open, setOpen } = useContext(OpenMenuContext);

  return (
    <NavItemStyled>
      <IconButton onClick={() => setOpen(!open)}>{props.icon}</IconButton>
      {open && props.children}
    </NavItemStyled>
  );
}

const NavItemStyled = styled.li`
  width: calc(var(--nav-size) * 0.7);
`;

const IconButton = styled.button`
  --button-size: calc(var(--nav-size) * 0.7);
  width: var(--button-size);
  height: var(--button-size);
  background-color: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  font-size: var(--size-xxl);
  padding-top: 14px;

  &:hover {
    color: var(--blue-25);
  }
`;
