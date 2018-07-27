import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, Subject, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Employee } from '../models/Employee';
import { EmployeeAddress } from '../models/EmployeeAddress';
import { EmployeeAccount } from '../models/EmployeeAccount';

@Injectable()
export class EmployeeService {

  private employeesUrl = '/api/employees';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
 
  private _property$: BehaviorSubject<Employee> = new BehaviorSubject({});

  set property(value: Employee) {
    this._property$.next(value);
  }

  get property$(): Observable<Employee> {
      return this._property$.asObservable();
  }

  constructor(private http: HttpClient) {}
  
  getEmployees(): Observable<Employee[]> {
    const url = `${this.employeesUrl}`;
    return this.http
      .get<Employee[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getEmployees', []))
      )
  }

  getEmployeeById(id: number): Observable<Employee> {
    const url = `${this.employeesUrl}/${id}`;
    return this.http
      .get<Employee>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained employee ID=${id}`)),
        catchError(this.handleError<Employee>('getEmployeeById'))
      )  
  }

  getAddressesByEmployeeId(id: number): Observable<EmployeeAddress[]> {
    const url = `${this.employeesUrl}/${id}/addresses`;
    return this.http
      .get<EmployeeAddress[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError<EmployeeAddress[]>('getAddressesByEmployeeId'))
      )
  }
  
  getAccountsByEmployeeId(employeeId: number): Observable<EmployeeAccount[]> {
    const url = `${this.employeesUrl}/${employeeId}/accounts`;
    return this.http
      .get<EmployeeAccount[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError<EmployeeAccount[]>('getAccountsByEmployeeId'))
      )
  }

  addEmployee(employee: Employee): Observable<Employee> {
    const url = `${this.employeesUrl}`;
    return this.http
      .post<Employee>(url, employee, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added employee shortName=${employee.personShortName}`)),
        catchError(this.handleError<Employee>('addEmployee'))
      )  
    }

  updateEmployee(employee: Employee): Observable<Employee> {
    const url = `${this.employeesUrl}/${employee.id}`;
    return this.http
      .put<Employee>(url, employee, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated employee id=${employee.id}`)),
        catchError(this.handleError<Employee>('updateEmployee'))
      );
  }

  deleteEmployee(employee: Employee) {
    const url = `${this.employeesUrl}/${employee.id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted employee ${employee.personShortName} (ID=${employee.id})`)),
        catchError(this.handleError<any>(`deleteEmployee`))
      );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
