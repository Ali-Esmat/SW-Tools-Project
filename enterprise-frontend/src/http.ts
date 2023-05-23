import { appConstants } from './appConstants';
import { ErrorResponse } from './types/ErrorResponse';
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
    } else if (response.status === 500) {
      alert('An error has occurred');
    } else if (response.status === 400) {
      const body: ErrorResponse = await response.json();
      alert(body.error);
    }
  } catch (e) {
    console.log(e);
    alert("Resetting token")
    localStorage.removeItem(appConstants.TOKEN_ITEM);
    window.location.reload();
  }
  return response;
};
