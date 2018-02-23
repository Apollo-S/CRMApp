import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, Subject } from 'rxjs';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';
import { Person } from '../models/Person';

@Injectable()
export class PersonService {

  private personsUrl = '/api/persons';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {}
  
  getPersons(): Observable<Person[]> {
    const url = `${this.personsUrl}`;
    return this.http
      .get<Person[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getPersons', []))
      )
  }

  getPersonById(id: number): Observable<Person> {
    const url = `${this.personsUrl}/${id}`;
    return this.http
      .get<Person>(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`obtained person ID=${id}`)),
        catchError(this.handleError<Person>('getPersonById'))
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
