import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { of } from 'rxjs/observable/of';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs';
import { ClientAgreement } from '../models/ClientAgreement';
import { Document } from '../models/Document';

@Injectable()
export class AgreementService {

  private agreementsUrl = '/api/agreements';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  private _property$: BehaviorSubject<ClientAgreement> = new BehaviorSubject({});
  
  set property(value: ClientAgreement) {
    this._property$.next(value);
  }

  get property$(): Observable<ClientAgreement> {
      return this._property$.asObservable();
  }

  constructor(private http: HttpClient) { }

  getAgreements(): Observable<ClientAgreement[]> {
    const url = `${this.agreementsUrl}`;
    return this.http
      .get<ClientAgreement[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getAgreements', []))
      )
  }

  getAgreementById(id: number): Observable<ClientAgreement> {
    const url = `${this.agreementsUrl}/${id}`;
    return this.http
      .get<ClientAgreement>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError<ClientAgreement>('getAgreementById'))
      )
  }

  getDocumentsByAgreementId(agreementId: number): Observable<Document[]> {
    const url = `${this.agreementsUrl}/${agreementId}/documents`;
    return this.http
      .get<Document[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getDocumentsByAgreementId', []))
      )
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
