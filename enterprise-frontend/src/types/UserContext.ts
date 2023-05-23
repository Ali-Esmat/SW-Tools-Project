export interface UserContext {
  id: number;
  name: string;
  role: 'RUNNER' | 'CUSTOMER' | 'OWNER';
}
