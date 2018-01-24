import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, Subject } from 'rxjs';
import { Employee } from '../models/Employee';
import { EmployeeAddress } from '../models/EmployeeAddress';
import { EmployeeAccount } from '../models/EmployeeAccount';

@Injectable()
export class EmployeeService {

  private employeesUrl = '/api/employees';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
 
  private _property$: BehaviorSubject<number> = new BehaviorSubject(1);

  set property(value: number) {
    this._property$.next(value);
  }

  get property$(): Observable<number> {
      return this._property$.asObservable();
  }

  constructor(private http: HttpClient) {}
  
  getEmployees(): Observable<Employee[]> {
    const url = `${this.employeesUrl}`;
    return this.http
      .get(url)
      .catch(this.handleError);
  }

  getEmployeeById(id: number): Observable<Employee> {
    const url = `${this.employeesUrl}/${id}`;
    return this.http
      .get(url)
      .catch(this.handleError); 
  }

  getAddressesByEmployeeId(employeeId: number): Observable<EmployeeAddress[]> {
    const url = `${this.employeesUrl}/${employeeId}/addresses`;
    return this.http
      .get(url)
      .catch(this.handleError);
  }
  
  getAccountsByEmployeeId(employeeId: number): Observable<EmployeeAccount[]> {
    const url = `${this.employeesUrl}/${employeeId}/accounts`;
    return this.http
      .get(url)
      .catch(this.handleError);
  }

  addEmployee(employee: Employee): Observable<Employee> {
    const url = `${this.employeesUrl}`;
    return this.http
      .post(url, employee)
      .catch(this.handleError);  
  }

  updateEmployee(employee: Employee): Observable<Employee> {
    const url = `${this.employeesUrl}/${employee.id}`;
    return this.http
      .put(url, employee)
      .catch(this.handleError);
  }

  delete(id: number): Observable<void> {
    const url = `${this.employeesUrl}/${id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
