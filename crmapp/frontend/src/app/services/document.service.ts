import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Document } from '../models/Document';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DocumentService {

  private documentsUrl = '/api/documents';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  getDocuments(): Observable<Document[]> {
    const url = `${this.documentsUrl}`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as Document[])
      .catch(this.handleError);
  }

  getDocumentById(id: number): Observable<Document> {
    const url = `${this.documentsUrl}/${id}`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as Document[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
