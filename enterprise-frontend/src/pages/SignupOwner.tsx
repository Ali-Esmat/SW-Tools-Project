import { UsernamePasswordForm } from "./UsernamePasswordForm";

export const SignupOwner = () => {
  const onSuccess = () => {
    alert("Signed up successfully, you can now login");
  };

  return <UsernamePasswordForm actionName="Signup" onSuccess={onSuccess} endpoint="signup/owner"/>;
};
