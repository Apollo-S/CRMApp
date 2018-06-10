import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../../services/client.service';
import { PostService } from '../../../../../services/post.service';
import { UtilService } from '../../../../../services/util.service';
import { Client } from '../../../../../models/Client';
import { ClientDirector } from '../../../../../models/ClientDirector';
import { Post } from '../../../../../models/Post';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-add-director',
  templateUrl: './add-director.component.html',
  styleUrls: ['./add-director.component.css']
})
export class AddDirectorComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  director: ClientDirector = {};
  posts: Post[] = [];
  client: Client = {};
  years: string;
  ru: any;

  constructor(private service: ClientService, 
              private postService: PostService,
              private router: Router) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => this.client = p
      );
    this.getPosts();
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(5);
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  onSubmit() {
    this.save();
    this.goBackToDirectors();
  }

  private save(): void {
    let msg  = '';
    this.service.addDirector(this.director, this.client)
      .subscribe(
        response => {
          msg = 'Руководитель для ' + this.client.alias +  ' успешно добавлен (ID=' + response.id + ')';
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }

  private getPosts() {
    this.postService.getPosts()
      .subscribe(
        post => this.posts = post
      );
  }

  private goBackToDirectors() {
    setTimeout(
      (router) => {
        this.router.navigate([this.client.url, 'directors']);
      }, 1500);
  } 

}
