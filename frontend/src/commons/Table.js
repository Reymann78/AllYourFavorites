import React from 'react';
import styled from "styled-components/macro";
import { CgRemoveR } from 'react-icons/cg';

export default function Table({ open, children, onClose }) {
    if (!open) return null;
    return (
      <TableStyled>
          <TableHeader>
              <RemoveButton onClick={onClose}>{<CgRemoveR />}</RemoveButton>
          </TableHeader>
        {children}
      </TableStyled>
    )

}

const TableStyled = styled.div`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 2px solid var(--blue-main);
  background-color: var(--white);
  `;

const RemoveButton = styled.button`
  --button-size: calc(var(--nav-size) * 0.6);
  width: var(--button-size);
  height: var(--button-size);
  color: var(--white);
  justify-self: end;
  font-size: var(--size-xl);
  background-color: linear-gradient(20deg, var(--blue-main), var(--blue-75));

  &:hover {
    color: var(--blue-25);
  }
`;

const TableHeader = styled.header`
  display: grid;
  grid-template-columns: 1fr;
  align-items: end;
  padding-bottom: var(--size-s);
  color: var(--white);
  background-color: var(--blue-75);
  text-align: center;
  font-weight: 600;
  font-size: 1.2rem;
`;