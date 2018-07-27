import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { MailOutput } from '../models/MailOutput';

@Injectable()
export class MailOutputService {

  private mailOutputsUrl = '/api/mail-outputs';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getMailOutputs(): Observable<MailOutput[]> {
    const url = `${this.mailOutputsUrl}`;
    return this.http
      .get<MailOutput[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getMailOutputs', []))
      );
  }

  getMailOutputById(id: number): Observable<MailOutput> {
    const url = `${this.mailOutputsUrl}/${id}`;
    return this.http
      .get<MailOutput>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained MailOutput ID=${id}`)),
        catchError(this.handleError<MailOutput>('getMailOutputById'))
      );
  }

  addMailOutput(mailOutput: MailOutput): Observable<MailOutput> {
    const url = `${this.mailOutputsUrl}`;
    return this.http
      .post<MailOutput>(url, mailOutput, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added mailOutput (number=${mailOutput.number})`)),
        catchError(this.handleError<MailOutput>('addMailOutput'))
      ); 
  }

  updateMailOutput(mailOutput: MailOutput): Observable<MailOutput> {
    const url = `${this.mailOutputsUrl}/${mailOutput.id}`;
    return this.http
      .put<MailOutput>(url, mailOutput, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated MailOutput (ID=${mailOutput.id}, number=${mailOutput.number})`)),
        catchError(this.handleError<MailOutput>('updateMailOutput'))
      );
  }

  deleteMailOutput(mailOutput: MailOutput): Observable<void> {
    const url = `${this.mailOutputsUrl}/${mailOutput.id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted mailOutput ${mailOutput.number} (ID=${mailOutput.id})`)),
        catchError(this.handleError<any>('deleteMailOutput'))
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
