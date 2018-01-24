import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Document } from '../models/Document';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DocumentService {

  private documentsUrl = '/api/documents';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getDocuments(): Observable<Document[]> {
    const url = `${this.documentsUrl}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  getDocumentById(id: number): Observable<Document> {
    const url = `${this.documentsUrl}/${id}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
