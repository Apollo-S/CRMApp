import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Observable, BehaviorSubject, Subject } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import { Employee } from '../models/Employee';
import { EmployeeAddress } from '../models/EmployeeAddress';
import { EmployeeAccount } from '../models/EmployeeAccount';

@Injectable()
export class EmployeeService {

  private employeesUrl = '/api/employees';
  private headers = new Headers({ 'Content-Type': 'application/json' });
 
  private _property$: BehaviorSubject<number> = new BehaviorSubject(1);

  set property(value: number) {
    this._property$.next(value);
  }

  get property$(): Observable<number> {
      return this._property$.asObservable();
  }

  constructor(private http: Http) {}
  
    getEmployees(): Promise<Employee[]> {
      const url = `${this.employeesUrl}`;
      return this.http.get(url)
          .toPromise()
          .then(response => response.json() as Employee[])
          .catch(this.handleError);
    }

    getEmployeeById(id: number): Promise<Employee> {
      const url = `${this.employeesUrl}/${id}`;
      return this.http.get(url)
        .toPromise()
        .then(response => response.json() as Employee)
        .catch(this.handleError); 
    }

    getAddressesByEmployeeId(employeeId: number): Promise<EmployeeAddress[]> {
      const url = `${this.employeesUrl}/${employeeId}/addresses`;
      return this.http.get(url)
          .toPromise()
          .then(response => response.json() as EmployeeAddress[])
          .catch(this.handleError);
    }
    
    getAccountsByEmployeeId(employeeId: number): Promise<EmployeeAccount[]> {
      const url = `${this.employeesUrl}/${employeeId}/accounts`;
      return this.http.get(url)
          .toPromise()
          .then(response => response.json() as EmployeeAccount[])
          .catch(this.handleError);
    }
  
    // addEmployee(employee: Employee): Promise<Employee> {
    //   const url = `${this.employeesUrl}`;
    //   return this.http.post(url, employee)
    //     .toPromise()
    //     .then(response => response.json().data as Employee).then()
    //     .catch(this.handleError);
    // }

    addEmployee(employee: Employee): Observable<Employee> {
      const url = `${this.employeesUrl}`;
      return this.http.post(url, employee)
          .map(response => response.json() as Employee);
          
  }

    updateEmployee(employee: Employee): Promise<Employee> {
      const url = `${this.employeesUrl}/${employee.id}`;
      return this.http.put(url, employee)
        .map(data => data.json()).toPromise()
        .catch(this.handleError);
    }
  
    delete(id: number): Promise<void> {
      const url = `${this.employeesUrl}/${id}`;
      return this.http
          .delete(url, { headers: this.headers })
          .toPromise()
          .then(() => null)
          .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
      console.error('Error', error); // for demo purposes only
      return Promise.reject(error.message || error);
    }

}
