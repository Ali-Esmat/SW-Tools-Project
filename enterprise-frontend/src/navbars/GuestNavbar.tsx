import React from 'react';
import { SignupOwner } from '../pages/SignupOwner';
import { Login } from '../pages/Login';

interface GuestNavbarProps {
  setPage: (page: React.JSX.Element) => void;
}

export const GuestNavbar = ({ setPage }: GuestNavbarProps) => {
  return (
    <div>
      <button onClick={() => setPage(<SignupOwner />)}>Signup owner</button>
      <button onClick={() => setPage(<Login />)}>Login</button>
    </div>
  );
};
