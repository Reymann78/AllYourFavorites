import React from 'react';
import styled from 'styled-components/macro';

export default function Navbar({ children }) {
  return (
    <NavbarStyled>
      <NavbarNav>{children}</NavbarNav>
    </NavbarStyled>
  );
}

const NavbarStyled = styled.nav`
  height: var(--nav-size);
  display: flex;
`;

const NavbarNav = styled.ul`
  display: flex;
  justify-content: flex-end;
`;
