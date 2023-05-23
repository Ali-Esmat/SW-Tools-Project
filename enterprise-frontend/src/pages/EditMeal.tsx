import { useState } from 'react';
import { sendRequest } from '../http';

export const EditMeal = () => {
  const [id, setId] = useState(1);
  const [name, setName] = useState('');
  const [priceStr, setPriceStr] = useState('10.0');
  const edit = async () => {
    const res = await sendRequest('PUT', `restaurant/meal/${id}`, {
      name,
      price: parseFloat(priceStr)
    });
    if (res.ok) {
      alert('Success!');
    }
  };
  return (
    <form onSubmit={(e) => e.preventDefault()}>
      <input
        type="number"
        min="1"
        placeholder="Meal id"
        value={id}
        onChange={(e) => setId(parseInt(e.target.value))}
      />
      <br />
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
      <button onClick={edit}>Edit</button>
    </form>
  );
};
