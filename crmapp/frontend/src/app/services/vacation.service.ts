import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';
import { Vacation } from '../models/Vacation';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class VacationService {

  private employeesUrl = '/api/employees';
  private vacationsUrl = '/api/vacations';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getVacations(): Observable<Vacation[]> {
    const url = `${this.vacationsUrl}`;
    return this.http
      .get<Vacation[]>(url)
      .pipe(
        tap(_ => console.log(`got all Vacations`)),
        catchError(this.handleError<Vacation[]>('getVacations', []))
      )  
  }

  getVacationsByEmployeeId(employeeId: number): Observable<Vacation[]> {
    const url = `${this.employeesUrl}/${employeeId}/vacations`;
    return this.http
      .get<Vacation[]>(url)
      .pipe(
        tap(_ => console.log(`got all Vacations by EmployeeId=${employeeId}`)),
        catchError(this.handleError<Vacation[]>('getVacationsByEmployeeId', []))
      )  
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
