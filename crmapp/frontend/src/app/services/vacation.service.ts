import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import { Vacation } from '../models/Vacation';

@Injectable()
export class VacationService {

  private employeesUrl = '/api/employees';
  private vacationsUrl = this.employeesUrl + '/vacations';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  getVacations(): Promise<Vacation[]> {
    const url = `${this.vacationsUrl}`;
    return this.http.get(url)
        .toPromise()
        .then(response => response.json() as Vacation[])
        .catch(this.handleError);
  }

  getVacationsByEmployeeId(employeeId: number): Promise<Vacation[]> {
    const url = `${this.employeesUrl}/${employeeId}/vacations`;
    return this.http.get(url)
        .toPromise()
        .then(response => response.json() as Vacation[])
        .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
