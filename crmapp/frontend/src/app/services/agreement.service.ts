import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { ClientAgreement } from '../models/ClientAgreement';

@Injectable()
export class AgreementService {

  private agreementsUrl = 'http://localhost:9000/crmapp/api/agreements';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  getAgreements(): Promise<ClientAgreement[]> {
    return this.http.get(this.agreementsUrl)
        .toPromise()
        .then(response => response.json() as ClientAgreement[])
        .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
