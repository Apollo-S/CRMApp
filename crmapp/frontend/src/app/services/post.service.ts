import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Post } from '../models/Post';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class PostService {

  private postsUrl = '/api/posts';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  getPosts(): Observable<Post[]> {
    const url = `${this.postsUrl}`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as Post[])
      .catch(this.handleError);
  }

  getPostById(id: number): Observable<Post> {
    const url = `${this.postsUrl}/${id}`;
    return this.http.get(url, { headers: this.headers })
      .map(response => response.json() as Post)
      .catch(this.handleError); 
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
