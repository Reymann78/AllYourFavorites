import React from 'react';
import styled from 'styled-components/macro';

export default function ActionButton({ children, ...rest }) {

    return (
        <Button {...rest }>
            {children}
        </Button>
    );

}

const Button = styled.button`
  margin-top: var(--size-xxl);
  padding: var(--size-m);
  border: none;
  background: linear-gradient(20deg, var(--blue-main), var(--blue-75));
  color: var(--white);
  border-radius: var(--size-s);
  font-size: 1em;
  font-weight: 600;
  box-shadow: 3px 3px 3px var(--blue-50); ;
`;