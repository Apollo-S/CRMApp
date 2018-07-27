import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { DocumentStatus } from '../models/DocumentStatus';

@Injectable()
export class DocumentStatusService {

  private docStatusUrl = '/api/document-statuses';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  constructor(private http: HttpClient) { }

  getDocumentStatuses(): Observable<DocumentStatus[]> {
    const url = `${this.docStatusUrl}`;
    return this.http
      .get<DocumentStatus[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getDocumentStatuses', []))
      );
  }

  getDocumentStatusById(id: number): Observable<DocumentStatus> {
    const url = `${this.docStatusUrl}/${id}`;
    return this.http
      .get<DocumentStatus>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained DocumentStatus ID=${id}`)),
        catchError(this.handleError<DocumentStatus>('getDocumentStatusById'))
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
