import { Menu } from './Menu';
import { Owner } from './Owner';

export interface Restaurant {
  id: number;
  name: string;
  owner: Owner;
  menu: Menu;
}
