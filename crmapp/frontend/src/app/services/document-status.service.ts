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
      .get<DocumentStatus[]>(url, { headers: this.headers });
  }

  getDocumentStatusById(id: number): Observable<DocumentStatus> {
    const url = `${this.docStatusUrl}/${id}`;
    return this.http
      .get<DocumentStatus>(url, { headers: this.headers })
      .catch(this.handleError);
  }

  private handleError(error: any): Observable<any> {
    console.error('Error', error); // for demo purposes only
    return Observable.of(error.message || error);
  }

}
