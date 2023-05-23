import { useState } from 'react';
import { sendRequest } from '../http';

export const RemoveMeal = () => {
  const [id, setId] = useState(1);
  const remove = async () => {
    const res = await sendRequest('DELETE', `restaurant/meal/${id}`);
    if (res.ok) {
      alert('Success!');
    }
  };
  return (
    <div>
      <h1>Remove meal</h1>
      <form onSubmit={(e) => e.preventDefault()}>
        <input
          type="number"
          min="1"
          placeholder="Meal id"
          value={id}
          onChange={(e) => setId(parseInt(e.target.value))}
        />
        <br />
        <button onClick={remove}>Remove</button>
      </form>
    </div>
  );
};
