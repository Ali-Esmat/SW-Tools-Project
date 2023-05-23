import React from 'react';
import { NavbarProps } from '../types/NavbarProps';
import { appConstants } from '../appConstants';

export const Navbar = ({ setPage, pages }: NavbarProps) => {
  const buttons = [];
  for (const name in pages) {
    buttons.push(
      <button key={name} onClick={() => setPage(pages[name])}>
        {name}
      </button>
    );
  }
  buttons.push(
    <button
      key="logout"
      onClick={() => {
        localStorage.removeItem(appConstants.TOKEN_ITEM);
        window.location.reload();
      }}
    >
      Log out
    </button>
  );
  return <div>{buttons}</div>;
};
