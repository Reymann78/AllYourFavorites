import React from 'react';
import styled from 'styled-components/macro';

export default function RequestButton({ children, ...rest }) {

    return(
      <Button { ...rest }>
          { children }
      </Button>
    );
}

const Button = styled.button`
  --button-size: calc(var(--nav-size) * 1.6);
  width: var(--button-size);
  height: calc(var(--button-size) / 3);
  font-size: var(--size-m);
  background-color: var(--blue-75);
  border-radius: var(--size-s);  

  &:hover {
    color: var(--blue-25);
  }
`;