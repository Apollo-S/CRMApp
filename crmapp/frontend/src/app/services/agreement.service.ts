import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
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

  addAgreement(agreement: ClientAgreement): Observable<ClientAgreement> {
    const url = `${this.agreementsUrl}`;
    return this.http
      .post<ClientAgreement>(url, agreement, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added ClientAgreement (number=${agreement.number})`)),
        catchError(this.handleError<ClientAgreement>('addAgreement'))
      ); 
  }

  updateAgreement(agreement: ClientAgreement): Observable<ClientAgreement> {
    const url = `${this.agreementsUrl}/${agreement.id}`;
    return this.http
      .put<ClientAgreement>(url, agreement, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated ClientAgreement (ID=${agreement.id}, alias=${agreement.number})`)),
        catchError(this.handleError<ClientAgreement>('updateAgreement'))
      );
  }

  deleteAgreement(agreement: ClientAgreement): Observable<void> {
    const url = `${this.agreementsUrl}/${agreement.id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted ClientAgreement ${agreement.number} (ID=${agreement.id})`)),
        catchError(this.handleError<any>('deleteAgreement'))
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
    };
  }

}
