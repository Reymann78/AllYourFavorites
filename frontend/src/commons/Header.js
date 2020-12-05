import React from 'react';
import styled from 'styled-components/macro';
import { CgAddR } from 'react-icons/cg';
import { IoMdLogOut } from 'react-icons/io';
import Navbar from './Navbar';
import NavItem from './NavItem';
import AddDropdownMenu from './AddDropdownMenu';

export default function Header({ title }) {
  return (
    <HeaderStyled>
      <HeadingStyled>{title}</HeadingStyled>
      <Navbar>
        <NavItem icon={<CgAddR />}>
          <AddDropdownMenu></AddDropdownMenu>
        </NavItem>
        <NavItem icon={<IoMdLogOut />} />
      </Navbar>
    </HeaderStyled>
  );
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
