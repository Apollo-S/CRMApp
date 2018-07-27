import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { MailInput } from '../models/MailInput';

@Injectable()
export class MailInputService {

  private MailInputsUrl = '/api/mail-inputs';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getMailInputs(): Observable<MailInput[]> {
    const url = `${this.MailInputsUrl}`;
    return this.http
      .get<MailInput[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getMailInputs', []))
      );
  }

  getMailInputById(id: number): Observable<MailInput> {
    const url = `${this.MailInputsUrl}/${id}`;
    return this.http
      .get<MailInput>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained MailInput ID=${id}`)),
        catchError(this.handleError<MailInput>('getMailInputById'))
      );
  }

  addMailInput(MailInput: MailInput): Observable<MailInput> {
    const url = `${this.MailInputsUrl}`;
    return this.http
      .post<MailInput>(url, MailInput, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added MailInput (number=${MailInput.number})`)),
        catchError(this.handleError<MailInput>('addMailInput'))
      ); 
  }

  updateMailInput(MailInput: MailInput): Observable<MailInput> {
    const url = `${this.MailInputsUrl}/${MailInput.id}`;
    return this.http
      .put<MailInput>(url, MailInput, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated MailInput (ID=${MailInput.id}, number=${MailInput.number})`)),
        catchError(this.handleError<MailInput>('updateMailInput'))
      );
  }

  deleteMailInput(MailInput: MailInput): Observable<void> {
    const url = `${this.MailInputsUrl}/${MailInput.id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted MailInput ${MailInput.number} (ID=${MailInput.id})`)),
        catchError(this.handleError<any>('deleteMailInput'))
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
