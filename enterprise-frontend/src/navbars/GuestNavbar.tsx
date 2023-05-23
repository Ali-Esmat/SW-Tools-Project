import React from 'react';
import { SignupOwner } from '../pages/SignupOwner';
import { Login } from '../pages/Login';
import { Navbar } from './Navbar';
import { SignupRunner } from '../pages/SignupRunner';

interface GuestNavbarProps {
  setPage: (page: React.JSX.Element) => void;
}

export const GuestNavbar = ({ setPage }: GuestNavbarProps) => {
  return (
    <Navbar
      setPage={setPage}
      pages={{
        'Signup owner': <SignupOwner />,
        'Signup runner': <SignupRunner />,
        Login: <Login />
      }}
    />
  );
};
