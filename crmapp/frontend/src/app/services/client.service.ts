import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Observable, BehaviorSubject } from 'rxjs';
import 'rxjs';
import { Client } from '../models/Client';
import { ClientAddress } from '../models/ClientAddress';
import { ClientAccount } from '../models/ClientAccount';
import { ClientAgreement } from '../models/ClientAgreement';
import { ClientDirector } from '../models/ClientDirector';

@Injectable()
export class ClientService {

  private clientsUrl = '/api/clients';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  private _property$: BehaviorSubject<number> = new BehaviorSubject(1);
  
  set property(value: number) {
    this._property$.next(value);
  }

  get property$(): Observable<number> {
      return this._property$.asObservable();
  }

  constructor(private http: Http) {}

  getClients(): Promise<Client[]> {
    return this.http.get(this.clientsUrl)
      .toPromise()
      .then(response => response.json() as Client[])
      .catch(this.handleError);
  }
  
  getClientById(id: number): Promise<Client> {
    const url = `${this.clientsUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Client)
      .catch(this.handleError); 
  }
  
  addClient(client: Client): Observable<Client> {
    const url = `${this.clientsUrl}`;
    return this.http.post(url, client)
      .map(response => response.json() as Client)
      .catch(this.handleError);
  }

  updateClient(client: Client): Promise<Client> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.http.put(url, client)
      .map(data => data.json()).toPromise()
      .catch(this.handleError);
  }

  deleteClient(id: number): Promise<void> {
    const url = `${this.clientsUrl}/${id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  // Addresses
  getAddressesByClientId(clientId: number) {
    const url = `${this.clientsUrl}/${clientId}/addresses`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as ClientAddress[])
      .catch(this.handleError);
  }

  getAddressById(id: number, clientId: number): Promise<ClientAddress> {
    const url = `${this.clientsUrl}/${clientId}/addresses/${id}`;
    return this.http.get(url)
    .toPromise()
    .then(response => response.json() as ClientAddress)
    .catch(this.handleError); 
  }

  addAddress(address: ClientAddress, clientId: number): Observable<ClientAddress> {
    const url = `${this.clientsUrl}/${clientId}/addresses`;
    return this.http.post(url, address)
      .map(response => response.json() as ClientAddress)
      .catch(this.handleError);
  }

  updateAddress(address: ClientAddress, clientId: number): Observable<ClientAddress>  {
    const url = `${this.clientsUrl}/${clientId}/addresses/${address.id}`;
    return this.http.put(url, address, { headers: this.headers })
      .map(data => data.json() as ClientAddress)
      .catch(this.handleError);
  }
  
  deleteAddress(id: number, clientId: number) {
    const url = `${this.clientsUrl}/${clientId}/addresses/${id}`;
    return this.http.delete(url, { headers: this.headers })
      .map(response => response.json())
      .catch(this.handleError);
  }
  
  // Accounts
  getAccountsByClientId(clientId: number): Observable<ClientAccount[]>  {
    const url = `${this.clientsUrl}/${clientId}/accounts`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as ClientAccount[])
      .catch(this.handleError);
  }

  getAccountById(id: number, clientId: number): Observable<ClientAccount> {
    const url = `${this.clientsUrl}/${clientId}/accounts/${id}`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as ClientAccount)
      .catch(this.handleError); 
  }

  addAccount(account: ClientAccount, clientId: number): Observable<ClientAccount> {
    const url = `${this.clientsUrl}/${clientId}/accounts`;
    return this.http.post(url, account)
      .map(response => response.json() as ClientAccount)
      .catch(this.handleError);
  }

  updateAccount(account: ClientAccount, clientId: number): Observable<ClientAccount>  {
    const url = `${this.clientsUrl}/${clientId}/accounts/${account.id}`;
    return this.http.put(url, account, { headers: this.headers })
      .map(data => data.json() as ClientAccount)
      .catch(this.handleError);
  }

  deleteAccount(id: number, clientId: number) {
    const url = `${this.clientsUrl}/${clientId}/accounts/${id}`;
    return this.http.delete(url, { headers: this.headers })
      .map(response => response.json())
      .catch(this.handleError);
  }

  // Directors
  getDirectorsByClientId(clientId: number): Observable<ClientDirector[]>  {
    const url = `${this.clientsUrl}/${clientId}/directors`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as ClientDirector[])
      .catch(this.handleError);
  }

  getDirectorById(id: number, clientId: number): Observable<ClientDirector> {
    const url = `${this.clientsUrl}/${clientId}/directors/${id}`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as ClientDirector)
      .catch(this.handleError); 
  }

  addDirector(director: ClientDirector, clientId: number): Observable<ClientDirector> {
    const url = `${this.clientsUrl}/${clientId}/directors`;
    return this.http.post(url, director, { headers: this.headers })
      .map(response => response.json() as ClientDirector)
      .catch(this.handleError);
  }

  updateDirector(director: ClientDirector, clientId: number): Observable<ClientDirector>  {
    const url = `${this.clientsUrl}/${clientId}/directors/${director.id}`;
    return this.http.put(url, director, { headers: this.headers })
      .map(data => data.json() as ClientDirector)
      .catch(this.handleError);
  }

  deleteDirector(id: number, clientId: number) {
    const url = `${this.clientsUrl}/${clientId}/directors/${id}`;
    return this.http.delete(url, { headers: this.headers })
      .map(response => response.json())
      .catch(this.handleError);
  }

  // Agreements
  getAgreementsByClientId(clientId: number): Promise<ClientAgreement[]> {
    const url = `${this.clientsUrl}/${clientId}/agreements`;
    return this.http.get(url)
        .toPromise()
        .then(response => response.json() as ClientAgreement[])
        .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
  
}
