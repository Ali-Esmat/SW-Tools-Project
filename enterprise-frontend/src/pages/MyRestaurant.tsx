import { useEffect, useState } from 'react';
import { sendRequest } from '../http';
import { Restaurant } from '../types/Restaurant';

export const MyRestaurant = () => {
  const [restaurant, setRestaurant] = useState<Restaurant | null>(null);
  useEffect(() => {
    async function initRestaurant() {
      const res = await sendRequest('GET', 'restaurant');
      if (res.status >= 400) return;
      const r: Restaurant = await res.json();
      setRestaurant(r);
    }
    initRestaurant();
  }, []);
  return (
    <div>
      <h1>My restaurant</h1>
      {restaurant === null ? (
        <p>You do not seem to have any restaurants, create one.</p>
      ) : (
        <div>
          <span><b>ID:</b> {restaurant.id}</span> <br />
          <span><b>Name:</b> {restaurant.name}</span> <br />
          <span><b>Owner name:</b> {restaurant.owner.name}</span> <br />
          <span><b>Menu</b></span> <br />
          <ul>
            {restaurant.menu.map((meal) => (
              <li key={meal.id}>
                {meal.name} - {meal.price}$
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};
