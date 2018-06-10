import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../../services/client.service';
import { PostService } from '../../../../../services/post.service';
import { UtilService } from '../../../../../services/util.service';
import { Client } from '../../../../../models/Client';
import { Post } from '../../../../../models/Post';
import { ClientDirector } from '../../../../../models/ClientDirector';
import { ConfirmationService, Message } from 'primeng/api';

@Component({
  selector: 'app-edit-director',
  templateUrl: './edit-director.component.html',
  styleUrls: ['./edit-director.component.css']
})
export class EditDirectorComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  client: Client = {};
  director: ClientDirector = {};
  posts: Post[] = [];
  years: string;
  ru: any;
  
  constructor(private service: ClientService, 
              private postService: PostService,
              private router: Router,
              private route: ActivatedRoute,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    let directorId: number;
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => this.client = p
      );
    this.route.params
      .subscribe(
        (params: Params) => {
          directorId = +params['id'];
          this.getDirectorById(directorId, this.client);
        }
      );
    this.getPosts();
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(5);
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  onSubmit() {
    this.update();
    this.goBackToDirectors();
  }

  confirmDeleting() {
    this.confirmationService.confirm({
      message: 'Действительно удалить руководителя?',
      header: 'Удаление адреса',
      icon: 'fa fa-trash',
      accept: () => {
        let msg  = 'Руководитель успешно удален (ID=' + this.director.id + ')';
        this.delete(msg);
        this.goBackToDirectors();
      },
      reject: () => {}
    });
  }

  private delete(msg: string) {
    this.service.deleteDirector(this.director.id, this.client)
      .subscribe(
        response => {
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }

  private update(): void {
    this.service.updateDirector(this.director, this.client)
      .subscribe(
        response => {
          let msg = 'Руководитель для ' + this.client.alias +  ' успешно обновлен (ID=' + response.id + ')';
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }

  private getDirectorById(directorId: number, client: Client) {
    this.service.getDirectorById(directorId, client)
      .subscribe(
        director => {
          this.director = director;
          this.director.dateStart = new Date(this.director.dateStart);
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