import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import { Vacation } from '../models/Vacation';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class VacationService {

  private employeesUrl = '/api/employees';
  private vacationsUrl = this.employeesUrl + '/vacations';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getVacations(): Observable<Vacation[]> {
    const url = `${this.vacationsUrl}`;
    return this.http
      .get(url)
      .catch(this.handleError);
  }

  getVacationsByEmployeeId(employeeId: number): Observable<Vacation[]> {
    const url = `${this.employeesUrl}/${employeeId}/vacations`;
    return this.http
      .get(url)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
