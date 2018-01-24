import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Client } from '../models/Client';
import { ClientAddress } from '../models/ClientAddress';
import { ClientAccount } from '../models/ClientAccount';
import { ClientAgreement } from '../models/ClientAgreement';
import { ClientDirector } from '../models/ClientDirector';

@Injectable()
export class ClientService {

  private clientsUrl = '/api/clients';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  private _property$: BehaviorSubject<number> = new BehaviorSubject(1);
  
  set property(value: number) {
    this._property$.next(value);
  }

  get property$(): Observable<number> {
      return this._property$.asObservable();
  }

  constructor(private http: HttpClient) {}

  getClients(): Observable<Client[]> {
    const url = `${this.clientsUrl}`;
    return this.http
      .get(url)
      .catch(this.handleError);
  }
  
  getClientById(id: number): Observable<Client> {
    const url = `${this.clientsUrl}/${id}`;
    return this.http
      .get(url)
      .catch(this.handleError); 
  }
  
  addClient(client: Client): Observable<Client> {
    const url = `${this.clientsUrl}`;
    return this.http
      .post(url, client)
      .catch(this.handleError);
  }

  updateClient(client: Client): Observable<Client> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.http
      .put(url, client)
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
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  getAddressById(id: number, clientId: number): Observable<ClientAddress> {
    const url = `${this.clientsUrl}/${clientId}/addresses/${id}`;
    return this.http
      .get(url)
      .catch(this.handleError); 
  }

  addAddress(address: ClientAddress, clientId: number): Observable<ClientAddress> {
    const url = `${this.clientsUrl}/${clientId}/addresses`;
    return this.http
      .post(url, address)
      .catch(this.handleError);
  }

  updateAddress(address: ClientAddress, clientId: number): Observable<ClientAddress>  {
    const url = `${this.clientsUrl}/${clientId}/addresses/${address.id}`;
    return this.http
      .put(url, address, { headers: this.headers })
      .catch(this.handleError);
  }
  
  deleteAddress(id: number, clientId: number) {
    const url = `${this.clientsUrl}/${clientId}/addresses/${id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .catch(this.handleError);
  }
  
  // Accounts
  getAccountsByClientId(clientId: number): Observable<ClientAccount[]>  {
    const url = `${this.clientsUrl}/${clientId}/accounts`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  getAccountById(id: number, clientId: number): Observable<ClientAccount> {
    const url = `${this.clientsUrl}/${clientId}/accounts/${id}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError); 
  }

  addAccount(account: ClientAccount, clientId: number): Observable<ClientAccount> {
    const url = `${this.clientsUrl}/${clientId}/accounts`;
    return this.http
      .post(url, account)
      .catch(this.handleError);
  }

  updateAccount(account: ClientAccount, clientId: number): Observable<ClientAccount>  {
    const url = `${this.clientsUrl}/${clientId}/accounts/${account.id}`;
    return this.http
      .put(url, account, { headers: this.headers })
      .catch(this.handleError);
  }

  deleteAccount(id: number, clientId: number) {
    const url = `${this.clientsUrl}/${clientId}/accounts/${id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .catch(this.handleError);
  }

  // Directors
  getDirectorsByClientId(clientId: number): Observable<ClientDirector[]>  {
    const url = `${this.clientsUrl}/${clientId}/directors`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  getDirectorById(id: number, clientId: number): Observable<ClientDirector> {
    const url = `${this.clientsUrl}/${clientId}/directors/${id}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError); 
  }

  addDirector(director: ClientDirector, clientId: number): Observable<ClientDirector> {
    const url = `${this.clientsUrl}/${clientId}/directors`;
    return this.http
      .post(url, director, { headers: this.headers })
      .catch(this.handleError);
  }

  updateDirector(director: ClientDirector, clientId: number): Observable<ClientDirector>  {
    const url = `${this.clientsUrl}/${clientId}/directors/${director.id}`;
    return this.http
      .put(url, director, { headers: this.headers })
      .catch(this.handleError);
  }

  deleteDirector(id: number, clientId: number) {
    const url = `${this.clientsUrl}/${clientId}/directors/${id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .catch(this.handleError);
  }

  // Agreements
  getAgreementsByClientId(clientId: number): Observable<ClientAgreement[]> {
    const url = `${this.clientsUrl}/${clientId}/agreements`;
    return this.http
      .get(url)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
  
}
