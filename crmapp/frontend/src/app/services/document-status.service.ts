import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { DocumentStatus } from '../models/DocumentStatus';

@Injectable()
export class DocumentStatusService {

  private docStatusUrl = '/api/document-statuses';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  constructor(private http: HttpClient) { }

  getDocumentStatuses(): Observable<DocumentStatus[]> {
    const url = `${this.docStatusUrl}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  getDocumentStatusById(id: number): Observable<DocumentStatus> {
    const url = `${this.docStatusUrl}/${id}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
