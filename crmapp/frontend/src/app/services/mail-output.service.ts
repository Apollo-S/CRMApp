import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { MailOutput } from '../models/MailOutput';
import { of } from 'rxjs/observable/of';
import { catchError, tap } from 'rxjs/operators';

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