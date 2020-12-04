import React, { useContext } from 'react';
import styled from 'styled-components/macro';
import OpenMenuContext from '../contexts/OpenMenuContext';

export default function NavItem(props) {
  const { open, setOpen } = useContext(OpenMenuContext);

  return (
    <NavItemStyled>
      <IconButtonStyled onClick={() => setOpen(!open)}>
        {props.icon}
      </IconButtonStyled>
      {open && props.children}
    </NavItemStyled>
  );
}

const NavItemStyled = styled.li`
  width: calc(var(--nav-size) * 0.9);
  display: flex;
  align-items: center;
  justify-content: center;
  padding-top: 10px;
`;

const IconButtonStyled = styled.button`
  --button-size: calc(var(--nav-size) * 0.6);
  width: var(--button-size);
  height: var(--button-size);
  background-color: var(--white);
  color: var(--blue-main);
  border-radius: var(--border-radius);
  padding: 2px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-color: var(--blue-main);
  font-size: var(--size-xl);

  &:hover {
    color: var(--blue-main);
    background-color: lightgrey;
  }
`;
