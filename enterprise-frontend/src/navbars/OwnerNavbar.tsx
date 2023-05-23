import React from 'react';
import { Navbar } from './Navbar';
import { MyRestaurant } from '../pages/MyRestaurant';
import { CreateRestaurant } from '../pages/CreateRestaurant';
import { CreateMeal } from '../pages/CreateMeal';
import { EditMeal } from '../pages/EditMeal';
import { MyRestaurantReport } from '../pages/MyRestaurantReport';
import { RemoveMeal } from '../pages/RemoveMeal';

interface OwnerNavbarProps {
  setPage: (page: React.JSX.Element) => void;
}

export const OwnerNavbar = ({ setPage }: OwnerNavbarProps) => {
  return (
    <Navbar
      setPage={setPage}
      pages={{
        'My restaurant': <MyRestaurant />,
        'My restaurant report': <MyRestaurantReport />,
        'Create restaurant': <CreateRestaurant />,
        'Create meal': <CreateMeal />,
        'Edit meal': <EditMeal />,
        'Remove meal': <RemoveMeal />
      }}
    />
  );
};
