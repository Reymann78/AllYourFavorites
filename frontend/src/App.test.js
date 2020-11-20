import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';
import { MemoryRouter as Router } from 'react-router-dom';

test('renders All Your Favorites', () => {
  render(
    <Router>
      <App />
    </Router>
  );
  const linkElement = screen.getByText(/All Your Favorites/i);
  expect(linkElement).toBeInTheDocument();
});
