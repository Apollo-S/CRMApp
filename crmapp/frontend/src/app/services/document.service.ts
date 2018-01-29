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
      .get<Document[]>(url, { headers: this.headers })
  }

  getDocumentById(id: number): Observable<Document> {
    const url = `${this.documentsUrl}/${id}`;
    return this.http
      .get<Document>(url, { headers: this.headers })
  }

  private handleError(error: any): Observable<any> {
    console.error('Error', error); // for demo purposes only
    return Observable.of(error.message || error);
  }

}
