import { UsernamePasswordForm } from './UsernamePasswordForm';

export const SignupRunner = () => {
  const onSuccess = () => {
    alert('Signed up successfully, you can now login');
  };

  return (
    <UsernamePasswordForm actionName="Signup" onSuccess={onSuccess} endpoint="signup/runner" />
  );
};
