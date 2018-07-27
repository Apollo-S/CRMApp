import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MenuItem } from 'primeng/api';

@Injectable()
export class CategoryService {

  private categoriesUrl = '/api/categories';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  constructor(private http: HttpClient) { }

  getCategories(): Observable<MenuItem[]> {
    const url = `${this.categoriesUrl}`;
    return this.http
      .get<MenuItem[]>(url, { headers: this.headers })
      .pipe(
        catchError(this.handleError('getCategories', []))
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
