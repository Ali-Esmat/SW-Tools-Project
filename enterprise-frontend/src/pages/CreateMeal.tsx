import { useState } from 'react';
import { sendRequest } from '../http';

export const CreateMeal = () => {
  const [name, setName] = useState('');
  const [priceStr, setPriceStr] = useState('10.0');
  const create = async () => {
    const res = await sendRequest('POST', 'restaurant/meal', {
      name,
      price: parseFloat(priceStr)
    });
    if (res.ok) {
      alert('Success!');
    }
  };
  return (
    <div>
      <h1>Create meal</h1>
      <form onSubmit={(e) => e.preventDefault()}>
        <input placeholder="Meal name" value={name} onChange={(e) => setName(e.target.value)} />
        <br />
        <input
          placeholder="Meal price"
          value={priceStr}
          type="number"
          step="0.001"
          onChange={(e) => setPriceStr(e.target.value)}
        />
        <br />
        <button onClick={create}>Create</button>
      </form>
    </div>
  );
};
