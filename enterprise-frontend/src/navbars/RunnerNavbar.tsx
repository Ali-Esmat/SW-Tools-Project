import React from 'react';
import { Navbar } from './Navbar';
import { MyCompletedTrips } from '../pages/MyCompletedTrips';

interface RunnerNavbarProps {
  setPage: (page: React.JSX.Element) => void;
}

export const RunnerNavbar = ({ setPage }: RunnerNavbarProps) => {
  return (
    <Navbar
      setPage={setPage}
      pages={{
        'My completed trips': <MyCompletedTrips />
      }}
    />
  );
};
