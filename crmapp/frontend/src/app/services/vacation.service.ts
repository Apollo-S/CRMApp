import { Injectable } from '@angular/core';
import { Observable, of  } from 'rxjs';
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

  addVacation(vacation: Vacation): Observable<Vacation> {
    const url = `${this.vacationsUrl}`;
    return this.http
      .post<Vacation>(url, vacation, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added Vacation (employee=${vacation.employeeShortName})`)),
        catchError(this.handleError<Vacation>('addVacation'))
      ); 
  }

  updateVacation(vacation: Vacation): Observable<Vacation> {
    const url = `${this.vacationsUrl}/${vacation.id}`;
    return this.http
      .put<Vacation>(url, vacation, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated Vacation (ID=${vacation.id}, employee=${vacation.employeeShortName})`)),
        catchError(this.handleError<Vacation>('updateVacation'))
      );
  }

  deleteVacation(vacation: Vacation): Observable<void> {
    const url = `${this.vacationsUrl}/${vacation.id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted Vacation (ID=${vacation.id})`)),
        catchError(this.handleError<any>('deleteVacation'))
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
