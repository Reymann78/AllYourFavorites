import React from 'react';
import styled from 'styled-components/macro';
import { FaPlus } from 'react-icons/fa';
import { IoMdLogOut } from 'react-icons/io';
import Navbar from './Navbar';
import NavItem from './NavItem';
import AddDropdownMenu from './AddDropdownMenu';

export default function Header({ title }) {
  return (
    <HeaderStyled>
      <HeadingStyled>{title}</HeadingStyled>
      <Navbar>
        <NavItem icon={<FaPlus />}>
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
  background: linear-gradient(20deg, var(--blue-75), var(--blue-25));
  padding: var(--size-xl);
`;

const HeadingStyled = styled.h1`
  margin: 0;
`;
