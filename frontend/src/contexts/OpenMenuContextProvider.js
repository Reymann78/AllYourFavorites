import OpenMenuContext from './OpenMenuContext';
import React, { useState } from 'react';

export default function OpenMenuContextProvider({ children }) {
  const [open, setOpen] = useState(false);

  return (
    <OpenMenuContext.Provider value={{ open, setOpen }}>
      {children}
    </OpenMenuContext.Provider>
  );
}
