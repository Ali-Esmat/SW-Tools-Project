import { appConstants } from './appConstants';
import { RequestMethod } from './types/RequestMethod';

export const sendRequest = async (method: RequestMethod, url: string, body: any = null) => {
  const token = localStorage.getItem(appConstants.TOKEN_ITEM);
  const headers = new Headers();
  headers.append('Content-Type', 'application/json');
  if (token !== null) {
    headers.append('Authorization', 'BASIC ' + token);
  }
  const bareRequestOptions = { headers, method };
  const requestOptions =
    body === null ? bareRequestOptions : { ...bareRequestOptions, body: JSON.stringify(body) };
  let response = new Response();
  try {
    response = await fetch(appConstants.API_URL + url, requestOptions);
    if (response.status === 401 || response.status === 403) {
      localStorage.removeItem(appConstants.TOKEN_ITEM);
      window.location.reload();
    }
    if (response.status === 500) {
      alert('AN error has occurred');
    }
  } catch (e) {
    console.log(e);
    localStorage.removeItem(appConstants.TOKEN_ITEM);
    window.location.reload();
  }
  return response;
};
