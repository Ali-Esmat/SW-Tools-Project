import React from 'react';
import { SignupOwner } from '../pages/SignupOwner';
import { Login } from '../pages/Login';
import { Navbar } from './Navbar';

interface GuestNavbarProps {
  setPage: (page: React.JSX.Element) => void;
}

export const GuestNavbar = ({ setPage }: GuestNavbarProps) => {
  return (
    <Navbar
      setPage={setPage}
      pages={{
        'Signup owner': <SignupOwner />,
        Login: <Login />
      }}
    />
  );
};
