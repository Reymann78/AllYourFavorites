import React, { useContext } from 'react';
import styled from 'styled-components/macro';
import { CgAddR } from 'react-icons/cg';
import { IoMdLogOut } from 'react-icons/io';
import Navbar from './Navbar';
import NavItem from './NavItem';
import AddDropdownMenu from './AddDropdownMenu';
import UserContext from '../contexts/UserContext';

export default function Header({ title }) {
  const { logout } = useContext(UserContext);

  return (
    <HeaderStyled>
      <HeadingStyled>{title}</HeadingStyled>
      <Navbar>
        <NavItem icon={<CgAddR />}>
          <AddDropdownMenu></AddDropdownMenu>
        </NavItem>
        <NavItem key="logout" icon={<IoMdLogOut />} onClick={handleLogout} />
      </Navbar>
    </HeaderStyled>
  );

  function handleLogout() {
    logout();
  }
}

const HeaderStyled = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-transform: uppercase;
  font-size: var(--size-m);
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  padding: var(--size-s);
`;

const HeadingStyled = styled.h1`
  margin: 0;
`;
