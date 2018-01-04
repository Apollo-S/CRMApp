import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { DocumentType } from '../models/DocumentType';

@Injectable()
export class DocumentTypeService {

  private docTypeUrl = '/api/document-types';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  getDocumentTypes(): Observable<DocumentType[]> {
    const url = `${this.docTypeUrl}`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as DocumentType[])
      .catch(this.handleError);
  }

  getDocumentTypeById(id: number): Observable<DocumentType> {
    const url = `${this.docTypeUrl}/${id}`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as DocumentType[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
