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
  width: calc(var(--nav-size) * 1);
  display: flex;
  align-items: center;
  justify-content: center;
`;

const IconButtonStyled = styled.button`
  --button-size: calc(var(--nav-size) * 0.8);
  width: var(--button-size);
  height: var(--button-size);
  background-color: #ffffff;
  color: var(--blue-50);
  border-radius: var(--border-radius);
  padding: 5px;
  margin: 2px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-color: var(--blue-50);

  &:hover {
    color: var(--blue-main);
    background-color: lightgrey;
  }
`;
