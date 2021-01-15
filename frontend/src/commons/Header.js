import React, { useContext } from 'react';
import { useHistory } from 'react-router-dom';
import styled from 'styled-components/macro';
import { CgAddR } from 'react-icons/cg';
import { IoMdLogOut } from 'react-icons/io';
import Navbar from './Navbar';
import NavItem from './NavItem';
import AddDropdownMenu from './AddDropdownMenu';
import UserContext from '../contexts/UserContext';

export default function Header({ title }) {
  const history = useHistory();
  const { logout } = useContext(UserContext);

  return (
    <HeaderStyled>
      <Heading>{title}</Heading>
      {window.location.pathname === '/favorites' ? (
        <Navbar>
          <NavItem icon={<CgAddR />}>
            <AddDropdownMenu></AddDropdownMenu>
          </NavItem>
          <ActionButton onClick={handleLogout}>
            <IoMdLogOut />
          </ActionButton>
        </Navbar>
      ) : (
        ''
      )}
      {window.location.pathname === '/favorites'  || window.location.pathname === '/login' ? (
      <ImprintButton onClick={() => history.push('/imprint')}>
        Impressum
      </ImprintButton>
      ) : (
        ''
      )}
    </HeaderStyled>
  );

  function handleLogout() {
    logout();
    history.push('/login');
  }
}

const HeaderStyled = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-transform: uppercase;
  font-size: var(--size-s);
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  padding: var(--size-s);
`;

const Heading = styled.h1`
  margin: 0;
  padding: var(--size-s);
`;

const ImprintButton = styled.button`
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  text-decoration: none;
  
  &:hover {
    color: var(--blue-25);
  }
`;

const ActionButton = styled.button`
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
