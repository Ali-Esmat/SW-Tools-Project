import { useState } from 'react';
import { sendRequest } from '../http';
import { ErrorResponse } from '../types/ErrorResponse';

export const CreateRestaurant = () => {
  const [name, setName] = useState('');
  const create = async () => {
    const res = await sendRequest('POST', 'restaurant', {
      name
    });
    if (res.status === 400) {
      const body: ErrorResponse = await res.json();
      alert(body.error);
    } else if (res.ok) {
      alert('Success!');
    }
  };
  return (
    <form onSubmit={(e) => e.preventDefault()}>
      <input placeholder="Restaurant name" value={name} onChange={(e) => setName(e.target.value)} />
      <button onClick={create}>Create</button>
    </form>
  );
};
