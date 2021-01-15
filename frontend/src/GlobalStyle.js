import { createGlobalStyle } from 'styled-components';

export default createGlobalStyle`
  :root {
   --blue-main: #0F008A;
   --blue-75: #0431B4;
   --blue-50: #2E64FE;
   --blue-25: #81BEF7;
   --white: #ffffff;
 
   --size-xs: 4px;
   --size-s: 8px;
   --size-m: 12px;
   --size-l: 16px;
   --size-xl: 20px;
   --size-xxl: 24px;
   --size-xxxl: 32px;
   
   --bg: var(--blue-main);
   --bg-accent: var(--blue-25);
   --text-color: var(--white);
   
   --nav-size: 50px;
   
   --blue-shadow: 1px 1px 1px var(--blue-50);
   --blue-border: 2px solid var(--blue-main);
   --border-radius: var(--size-s);
   
   --speed: 500ms;
  
  }
  
  button {
  border: none;
  outline: none;
  color: var(--white);
  padding: 0;
  }
  
  ul {
    list-style: none;
    margin: 0;
    padding: 0;
  }
  
  div {
    padding: 0;
  }
  
  h1, h2, h3, p {
  margin: 0;
  padding: var(--size-xs);
  }
  
  html, header, body {
    margin: 0;
    padding: 0;
    color: var(--white);
    font-family: "Helvetica Neue", sans-serif;
  }
`;
