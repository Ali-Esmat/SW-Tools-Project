import { useEffect, useState } from 'react';
import { UserContext } from './types/UserContext';
import { sendRequest } from './http';
import { GuestNavbar } from './navbars/GuestNavbar';

function App() {
  const [page, setPage] = useState(<div></div>);
  const [navbar, setNavbar] = useState(<GuestNavbar setPage={setPage}/>);
  useEffect(() => {
    async function initFromUserContext() {
      const contextResponse = await sendRequest('GET', 'user');
      if (contextResponse.status < 400) {
        const contextJson = await contextResponse.json();
        const context: UserContext = contextJson.context;
      }
    }
    initFromUserContext();
  });

  return (
    <div className="App">
      {navbar}
      {page}
    </div>
  );
}

export default App;
