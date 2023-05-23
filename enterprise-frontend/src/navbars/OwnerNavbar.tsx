import React from 'react';
import { Navbar } from './Navbar';
import { MyRestaurant } from '../pages/MyRestaurant';
import { CreateRestaurant } from '../pages/CreateRestaurant';
import { CreateMeal } from '../pages/CreateMeal';
import { EditMeal } from '../pages/EditMeal';

interface OwnerNavbarProps {
  setPage: (page: React.JSX.Element) => void;
}

export const OwnerNavbar = ({ setPage }: OwnerNavbarProps) => {
  return (
    <Navbar
      setPage={setPage}
      pages={{
        'My restaurant': <MyRestaurant />,
        'Create restaurant': <CreateRestaurant />,
        'Create meal': <CreateMeal />,
        'Edit meal': <EditMeal />
      }}
    />
  );
};
