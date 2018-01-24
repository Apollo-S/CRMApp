import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Post } from '../models/Post';


@Injectable()
export class PostService {

  private postsUrl = '/api/posts';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getPosts(): Observable<Post[]> {
    const url = `${this.postsUrl}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError);
  }

  getPostById(id: number): Observable<Post> {
    const url = `${this.postsUrl}/${id}`;
    return this.http
      .get(url, { headers: this.headers })
      .catch(this.handleError); 
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
