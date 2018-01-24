import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { DocumentType } from '../models/DocumentType';

@Injectable()
export class DocumentTypeService {

  private docTypeUrl = '/api/document-types';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getDocumentTypes(): Observable<DocumentType[]> {
    const url = `${this.docTypeUrl}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  getDocumentTypeById(id: number): Observable<DocumentType> {
    const url = `${this.docTypeUrl}/${id}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
