import { appConstants } from '../appConstants';
import { LoginResponse } from '../types/LoginResponse';
import { UsernamePasswordForm } from './UsernamePasswordForm';

export const Login = () => {
  const onSuccess = async (response: Response) => {
    const { token }: LoginResponse = await response.json();
    localStorage.setItem(appConstants.TOKEN_ITEM, window.btoa(token));
    window.location.reload();
  };

  return <UsernamePasswordForm actionName="Login" onSuccess={onSuccess} endpoint='login' />;
};
