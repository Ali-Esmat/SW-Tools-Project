import { useState } from 'react';
import { sendRequest } from '../http';

interface UsernamePasswordFormProps {
  actionName: string;
  endpoint: string;
  onSuccess: (response: Response) => void;
}

export const UsernamePasswordForm = (props: UsernamePasswordFormProps) => {
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');

  const login = async () => {
    const response = await sendRequest('POST', props.endpoint, { name, password });
    if (response.ok) {
      props.onSuccess(response);
    }
  };

  return (
    <form onSubmit={(e) => e.preventDefault()}>
      <div>
        <label>Name</label> <br />
        <input onChange={(e) => setName(e.target.value)} value={name} /> <br />
        <label>Password</label> <br />
        <input
          type="password"
          onChange={(e) => setPassword(e.target.value)}
          value={password}
        />{' '}
        <br />
        <button onClick={login}>{props.actionName}</button>
      </div>
    </form>
  );
};
