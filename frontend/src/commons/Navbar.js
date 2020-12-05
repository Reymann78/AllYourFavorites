import React from 'react';
import styled from 'styled-components/macro';

export default function Navbar({ children }) {
  return (
    <NavbarStyled>
      <NavbarNavStyled>{children}</NavbarNavStyled>
    </NavbarStyled>
  );
}

const NavbarStyled = styled.nav`
  height: var(--nav-size);
  display: flex;
`;

const NavbarNavStyled = styled.ul`
  display: flex;
  justify-content: flex-end;
`;
