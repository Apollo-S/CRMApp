import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Category } from '../models/Category';

@Injectable()
export class CategoryService {

  private categoriesUrl = '/api/categories';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    const url = `${this.categoriesUrl}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
