import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, Subject, of } from 'rxjs';
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

  addPerson(person: Person): Observable<Person> {
    const url = `${this.personsUrl}`;
    return this.http
      .post<Person>(url, person, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`added person (alias=${person.shortName})`)),
        catchError(this.handleError<Person>('addPerson'))
      )  
  }

  updatePerson(person: Person): Observable<Person> {
    const url = `${this.personsUrl}/${person.id}`;
    return this.http
      .put<Person>(url, person, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`updated person (ID=${person.id}, alias=${person.shortName})`)),
        catchError(this.handleError<Person>('updatePerson'))
      );
  }

  deletePerson(person: Person): Observable<void> {
    const url = `${this.personsUrl}/${person.id}`;
    return this.http
      .delete(url, { headers: this.headers })
      .pipe(
        tap(_ => console.log(`deleted person ${person.shortName} (ID=${person.id})`)),
        catchError(this.handleError<any>('deletePerson'))
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
