import React from 'react';
import { Navbar } from './Navbar';
import { MyRestaurant } from '../pages/MyRestaurant';
import { CreateRestaurant } from '../pages/CreateRestaurant';

interface OwnerNavbarProps {
  setPage: (page: React.JSX.Element) => void;
}

export const OwnerNavbar = ({ setPage }: OwnerNavbarProps) => {
  return (
    <Navbar
      setPage={setPage}
      pages={{
        'My restaurant': <MyRestaurant />,
        'Create restaurant': <CreateRestaurant />
      }}
    />
  );
};
