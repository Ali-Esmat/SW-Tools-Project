import { useEffect, useState } from 'react';
import { sendRequest } from '../http';
import { RestaurantReport } from '../types/RestaurantReport';

export const MyRestaurantReport = () => {
  const [restaurantReport, setRestaurantReport] = useState<RestaurantReport | null>(null);
  useEffect(() => {
    async function initRestaurantReport() {
      const res = await sendRequest('GET', 'restaurant/report');
      if (res.ok) {
        const r: RestaurantReport = await res.json();
        setRestaurantReport(r);
      }
    }
    initRestaurantReport();
  }, []);
  return (
    <div>
      <h1>Restaurant Report</h1>
      {restaurantReport === null ? (
        <p>You do not seem to have any restaurants, create one.</p>
      ) : (
        <div>
          <span>
            <b>ID:</b> {restaurantReport.id}
          </span>
          <br />
          <span>
            <b>Earns:</b> {restaurantReport.earns}
          </span>
          <br />
          <span>
            <b>Number of completed orders:</b> {restaurantReport.noCompletedOrders}
          </span>
          <br />
          <span>
            <b>Number of cancelled orders:</b> {restaurantReport.noCancelledOrders}
          </span>
        </div>
      )}
    </div>
  );
};
