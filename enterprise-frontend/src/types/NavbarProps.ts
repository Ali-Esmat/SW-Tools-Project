import React from 'react';

interface Page {
  [name: string]: React.JSX.Element;
}

export interface NavbarProps {
  setPage: (page: React.JSX.Element) => void;
  pages: Page;
}
