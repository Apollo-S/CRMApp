import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Client } from '../models/Client';
import { ClientAddress } from '../models/ClientAddress';
import { ClientAccount } from '../models/ClientAccount';
import { ClientAgreement } from '../models/ClientAgreement';
import { ClientDirector } from '../models/ClientDirector';

@Injectable()
export class ClientService {

  private clientsUrl = '/api/clients';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  private _property$: BehaviorSubject<Client> = new BehaviorSubject({});
  
  set property(value: Client) {
    this._property$.next(value);
  }

  get property$(): Observable<Client> {
      return this._property$.asObservable();
  }

  constructor(private http: HttpClient) {}

  getClients(): Observable<Client[]> {
    const url = `${this.clientsUrl}`;
    return this.http
      .get<Client[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getClients', []))
      );
  }
  
  getClientById(id: number): Observable<Client> {
    const url = `${this.clientsUrl}/${id}`;
    return this.http
      .get<Client>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained client ID=${id}`)),
        catchError(this.handleError<Client>('getClientById'))
      ); 
  }
  
  addClient(client: Client): Observable<Client> {
    const url = `${this.clientsUrl}`;
    return this.http
      .post<Client>(url, client, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added client (alias=${client.alias})`)),
        catchError(this.handleError<Client>('addClient'))
      ); 
  }

  updateClient(client: Client): Observable<Client> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.http
      .put<Client>(url, client, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated client (ID=${client.id}, alias=${client.alias})`)),
        catchError(this.handleError<Client>('updateClient'))
      );
  }

  deleteClient(client: Client): Observable<void> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted client ${client.alias} (ID=${client.id})`)),
        catchError(this.handleError<any>('deleteClient'))
      );
  }

  // Addresses
  getAddressesByClientId(clientId: number): Observable<ClientAddress[]> {
    const url = `${this.clientsUrl}/${clientId}/addresses`;
    return this.http
      .get<ClientAddress[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError<ClientAddress[]>('getAddressesByClientId'))
      );
  }

  getAddressById(id: number, client: Client): Observable<ClientAddress> {
    const url = `${this.clientsUrl}/${client.id}/addresses/${id}`;
    return this.http
      .get<ClientAddress>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained address ID=${id} for client ${client.alias}`)),
        catchError(this.handleError<ClientAddress>('getAddressById'))
      );
  }

  addAddress(address: ClientAddress, client: Client): Observable<ClientAddress> {
    const url = `${this.clientsUrl}/${client.id}/addresses`;
    return this.http
      .post<ClientAddress>(url, address, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added address for client ${client.alias}`)),
        catchError(this.handleError<ClientAddress>('addAddress'))
      );
  }

  updateAddress(address: ClientAddress, client: Client): Observable<ClientAddress>  {
    const url = `${this.clientsUrl}/${client.id}/addresses/${address.id}`;
    return this.http
      .put<ClientAddress>(url, address, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated address ID=${address.id} for client=${client.alias}`)),
        catchError(this.handleError<ClientAddress>('updateAddress'))
      );
  }
  
  deleteAddress(id: number, client: Client) {
    const url = `${this.clientsUrl}/${client.id}/addresses/${id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted address ID=${id} for client=${client.alias}`)),
        catchError(this.handleError<any>('deleteAddress'))
      );
  }
  
  // Accounts
  getAccountsByClientId(clientId: number): Observable<ClientAccount[]>  {
    const url = `${this.clientsUrl}/${clientId}/accounts`;
    return this.http
      .get<ClientAccount[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError<ClientAccount[]>('getAccountsByClientId'))
      );
  }

  getAccountById(id: number, client: Client): Observable<ClientAccount> {
    const url = `${this.clientsUrl}/${client.id}/accounts/${id}`;
    return this.http
      .get<ClientAccount>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained account ID=${id} for client ${client.alias}`)),
        catchError(this.handleError<ClientAccount>('getAccountById'))
      );
  }

  addAccount(account: ClientAccount, client: Client): Observable<ClientAccount> {
    const url = `${this.clientsUrl}/${client.id}/accounts`;
    return this.http
      .post<ClientAccount>(url, account)
      .pipe(
        tap(_ => console.log(`added account for client ${client.alias}`)),
        catchError(this.handleError<ClientAccount>('addAccount'))
      );
  }

  updateAccount(account: ClientAccount, client: Client): Observable<ClientAccount>  {
    const url = `${this.clientsUrl}/${client.id}/accounts/${account.id}`;
    return this.http
      .put<ClientAccount>(url, account, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated account ID=${account.id} for client ${client.alias}`)),
        catchError(this.handleError<ClientAccount>('updateAccount'))
      );
  }

  deleteAccount(id: number, client: Client) {
    const url = `${this.clientsUrl}/${client.id}/accounts/${id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted account ID=${id} for client ${client.alias}`)),
        catchError(this.handleError<any>('deleteAccount'))
      );
  }

  // Directors
  getDirectorsByClientId(clientId: number): Observable<ClientDirector[]>  {
    const url = `${this.clientsUrl}/${clientId}/directors`;
    return this.http
      .get<ClientDirector[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError<ClientDirector[]>('getDirectorsByClientId'))
      );
  }

  getDirectorById(id: number, client: Client): Observable<ClientDirector> {
    const url = `${this.clientsUrl}/${client.id}/directors/${id}`;
    return this.http
      .get<ClientDirector>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained director ID=${id} for client ${client.alias}`)),
        catchError(this.handleError<ClientDirector>('getDirectorById'))
      );
  }

  addDirector(director: ClientDirector, client: Client): Observable<ClientDirector> {
    const url = `${this.clientsUrl}/${client.id}/directors`;
    return this.http
      .post<ClientDirector>(url, director, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added director for client ${client.alias}`)),
        catchError(this.handleError<ClientDirector>('addDirector'))
      ); 
  }

  updateDirector(director: ClientDirector, client: Client): Observable<ClientDirector>  {
    const url = `${this.clientsUrl}/${client.id}/directors/${director.id}`;
    return this.http
      .put<ClientDirector>(url, director, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated director ID=${director.id} for client ${client.alias}`)),
        catchError(this.handleError<ClientDirector>('updateDirector'))
      );
  }

  deleteDirector(id: number, client: Client) {
    const url = `${this.clientsUrl}/${client.id}/directors/${id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted director ID=${id} for client ${client.alias}`)),
        catchError(this.handleError<any>('deleteDirector'))
      );
  }

  // Agreements
  getAgreementsByClientId(clientId: number): Observable<ClientAgreement[]> {
    const url = `${this.clientsUrl}/${clientId}/agreements`;
    return this.http
      .get<ClientAgreement[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError<ClientAgreement[]>('getAgreementsByClientId'))
      );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    }
  }
  
}
