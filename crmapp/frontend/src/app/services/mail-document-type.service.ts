import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { MailDocumentType } from '../models/MailDocumentType';

@Injectable()
export class MailDocumentTypeService {

  private mailDocTypeUrl = '/api/mail-document-types';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  constructor(private http: HttpClient) { }

  getMailDocumentTypes(): Observable<MailDocumentType[]> {
    const url = `${this.mailDocTypeUrl}`;
    return this.http
      .get<MailDocumentType[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getMailDocumentTypes', []))
      );
  }

  getMailDocumentTypeById(id: number): Observable<MailDocumentType> {
    const url = `${this.mailDocTypeUrl}/${id}`;
    return this.http
    .get<MailDocumentType>(url, { headers: this.headers })
    .pipe(
      tap(_ => console.log(`obtained DocumentType ID=${id}`)),
      catchError(this.handleError<MailDocumentType>('getMailDocumentTypeById'))
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