import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, BehaviorSubject } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Document } from '../models/Document';
import { DocumentType } from '../models/DocumentType';

@Injectable()
export class DocumentService {

  private documentsUrl = '/api/documents';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  private _property$: BehaviorSubject<Document> = new BehaviorSubject({});
  
  set property(value: Document) {
    this._property$.next(value);
  }

  get property$(): Observable<Document> {
      return this._property$.asObservable();
  }

  constructor(private http: HttpClient) { }

  getDocuments(): Observable<Document[]> {
    const url = `${this.documentsUrl}`;
    return this.http
      .get<Document[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getDocuments', []))
      );
  }

  getDocumentsAccordingFilter(docTypes: number[], docStatuses: number[], 
    clients: number[], sortField: string, sortType: string) : Observable<Document[]>  {
    const url = `${this.documentsUrl}/filter/docTypes=[${docTypes}]&docStatuses=[${docStatuses}]` + 
      `&clients=[${clients}]&sortField=${sortField}&sortType=${sortType}`;
    return this.http
      .get<Document[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getDocumentsAccordingFilter', []))
      );
  }

  getDocumentById(id: number): Observable<Document> {
    const url = `${this.documentsUrl}/${id}`;
    return this.http
      .get<Document>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained document ID=${id}`)),
        catchError(this.handleError<Document>('getDocumentById'))
      );
  }

  addDocument(document: Document): Observable<Document> {
    const url = `${this.documentsUrl}`;
    return this.http
      .post<Document>(url, document, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added document (id=${document.id})`)),
        catchError(this.handleError<Document>('addDocument'))
      );
  }

  updateDocument(document: Document): Observable<Document> {
    const url = `${this.documentsUrl}/${document.id}`;
    return this.http
      .put<Document>(url, document, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated document (ID=${document.id}, number=${document.number})`)),
        catchError(this.handleError<Document>('updateDocument'))
      );
  }

  deleteDocument(document: Document): Observable<void> {
    const url = `${this.documentsUrl}/${document.id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted document ${document.number} (ID=${document.id})`)),
        catchError(this.handleError<any>('deleteDocument'))
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
