import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable , of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { DocumentType } from '../models/DocumentType';

@Injectable()
export class DocumentTypeService {

  private docTypeUrl = '/api/document-types';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getDocumentTypes(): Observable<DocumentType[]> {
    const url = `${this.docTypeUrl}`;
    return this.http
      .get<DocumentType[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getDocumentTypes', []))
      );
  }

  getDocumentTypeById(id: number): Observable<DocumentType> {
    const url = `${this.docTypeUrl}/${id}`;
    return this.http
    .get<DocumentType>(url, { headers: this.headers })
    .pipe(
      tap(_ => console.log(`obtained DocumentType ID=${id}`)),
      catchError(this.handleError<DocumentType>('getDocumentTypeById'))
    ); 
  }

  addDocumentType(docType: DocumentType): Observable<DocumentType> {
    const url = `${this.docTypeUrl}`;
    return this.http
      .post<DocumentType>(url, docType, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added DocumentType (alias=${docType.title})`)),
        catchError(this.handleError<DocumentType>('addDocumentType'))
      ); 
  }

  updateDocumentType(docType: DocumentType): Observable<DocumentType> {
    const url = `${this.docTypeUrl}/${docType.id}`;
    return this.http
      .put<DocumentType>(url, docType, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated DocumentType (ID=${docType.id}, title=${docType.title})`)),
        catchError(this.handleError<DocumentType>('updateDocumentType'))
      );
  }

  deleteDocumentType(docType: DocumentType): Observable<void> {
    const url = `${this.docTypeUrl}/${docType.id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted DocumentType '${docType.title}' (ID=${docType.id})`)),
        catchError(this.handleError<any>('deleteDocumentType'))
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
