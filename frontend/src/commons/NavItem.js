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
`;

const IconButtonStyled = styled.button`
  --button-size: calc(var(--nav-size) * 1);
  width: var(--button-size);
  height: var(--button-size);
  background-color: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  font-size: var(--size-xxl);
  padding-top: 6px;

  &:hover {
    color: darkgrey;
  }
`;
