import { useEffect, useState } from 'react';
import { sendRequest } from '../http';
import { CompletedTrips } from '../types/CompletedTrips';

export const MyCompletedTrips = () => {
  const [completedTrips, setCompletedTrips] = useState<CompletedTrips | null>(null);
  useEffect(() => {
    async function initCompletedTrips() {
      const res = await sendRequest('GET', 'trips');
      if (res.ok) {
        const t: CompletedTrips = await res.json();
        setCompletedTrips(t);
      }
    }
    initCompletedTrips();
  }, []);
  return (
    <div>
      <h1>Completed trips</h1>
      {completedTrips === null ? (
        <p>Loading</p>
      ) : (
        <div>
          <span>
            <b>Number of completed trips:</b> {completedTrips.noCompletedTrips}
          </span>
        </div>
      )}
    </div>
  );
};
