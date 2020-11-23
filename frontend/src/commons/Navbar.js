import React from 'react';
import styled from 'styled-components/macro';

export default function Navbar(props) {
  return (
    <NavbarStyled>
      <NavbarNavStyled>{props.children}</NavbarNavStyled>
    </NavbarStyled>
  );
}

const NavbarStyled = styled.nav`
  height: var(--nav-size);
  padding: 0 1rem;
`;

const NavbarNavStyled = styled.ul`
  max-width: 100%;
  height: 100%;
  display: flex;
  justify-content: flex-end;
`;
