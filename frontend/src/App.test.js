import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';

xtest('renders All Your Favorites', () => {
  render(<App />);
  const linkElement = screen.getByText(/All Your Favorites/i);
  expect(linkElement).toBeInTheDocument();
});
