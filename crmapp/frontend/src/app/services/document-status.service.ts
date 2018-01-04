import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { DocumentStatus } from '../models/DocumentStatus';

@Injectable()
export class DocumentStatusService {

  private docStatusUrl = '/api/document-statuses';
  private headers = new Headers({ 'Content-Type': 'application/json' });
  
  constructor(private http: Http) { }

  getDocumentStatuses(): Observable<DocumentStatus[]> {
    const url = `${this.docStatusUrl}`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as DocumentStatus[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
