import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { ClientAgreement } from '../models/ClientAgreement';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs';
import { Document } from '../models/Document';

@Injectable()
export class AgreementService {

  private agreementsUrl = '/api/agreements';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  private _property$: BehaviorSubject<number> = new BehaviorSubject(1);
  
  set property(value: number) {
    this._property$.next(value);
  }

  get property$(): Observable<number> {
      return this._property$.asObservable();
  }

  constructor(private http: Http) { }

  getAgreements(): Observable<ClientAgreement[]> {
    const url = `${this.agreementsUrl}`;
    return this.http.get(url, { headers: this.headers })
        .map(response => response.json() as ClientAgreement[])
        .catch(this.handleError);
  }

  getAgreementById(id: number): Observable<ClientAgreement> {
    const url = `${this.agreementsUrl}/${id}`;
    return this.http.get(url, { headers: this.headers })
        .map(response => response.json() as ClientAgreement[])
        .catch(this.handleError);
  }

  getDocumentsByAgreementId(agreementId: number): Observable<Document[]> {
    const url = `${this.agreementsUrl}/${agreementId}/documents`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as Document[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
