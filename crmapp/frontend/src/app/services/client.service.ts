import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Observable, BehaviorSubject } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs';
import { Client } from '../models/Client';
import { ClientAddress } from '../models/ClientAddress';
import { ClientAccount } from '../models/ClientAccount';
import { ClientAgreement } from '../models/ClientAgreement';

@Injectable()
export class ClientService {

  private clientsUrl = 'http://localhost:9000/crmapp/api/clients';
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

  getAddressesByClientId(clientId: number): Promise<ClientAddress[]> {
    const url = `${this.clientsUrl}/${clientId}/addresses`;
    return this.http.get(url)
        .toPromise()
        .then(response => response.json() as ClientAddress[])
        .catch(this.handleError);
  }
  
  getAccountsByClientId(clientId: number): Promise<ClientAccount[]> {
    const url = `${this.clientsUrl}/${clientId}/accounts`;
    return this.http.get(url)
        .toPromise()
        .then(response => response.json() as ClientAccount[])
        .catch(this.handleError);
  }

  getAgreementsByClientId(clientId: number): Promise<ClientAgreement[]> {
    const url = `${this.clientsUrl}/${clientId}/agreements`;
    return this.http.get(url)
        .toPromise()
        .then(response => response.json() as ClientAgreement[])
        .catch(this.handleError);
  }
  
  getClientById(id: number): Promise<Client> {
    const url = `${this.clientsUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Client)
      .catch(this.handleError); 
  }

  addClient(client: Client): Promise<Client> {
    const url = `${this.clientsUrl}`;
    return this.http.post(url, client)
      .toPromise()
      .then(response => response.json().data as Client)
      .catch(this.handleError);
  }

  // addClient(client: Client): Promise<Client> {
  //   return this.http
  //     .post(this.clientsUrl, JSON.stringify({client}), {headers: this.headers})
  //     .toPromise()
  //     .then(res => res.json().data as Client)
  //     .catch(this.handleError);
  // }

  updateClient(client: Client): Promise<Client> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.http.put(url, client)
      .map(data => data.json()).toPromise()
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    const url = `${this.clientsUrl}/${id}`;
    return this.http
        .delete(url, { headers: this.headers })
        .toPromise()
        .then(() => null)
        .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  
}
