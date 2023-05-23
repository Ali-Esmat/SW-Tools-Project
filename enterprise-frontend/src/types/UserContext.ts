export interface UserContext {
  id: number;
  name: string;
  role: 'RUNNER' | 'CUSTOMER' | 'RESTAURANT_OWNER';
}
