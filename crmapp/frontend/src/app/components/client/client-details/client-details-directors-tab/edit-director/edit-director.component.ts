import { Component, OnInit } from '@angular/core';
import { ClientDirector } from '../../../../../models/ClientDirector';
import { Post } from '../../../../../models/Post';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../../services/client.service';
import { PostService } from '../../../../../services/post.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-edit-director',
  templateUrl: './edit-director.component.html',
  styleUrls: ['./edit-director.component.css']
})
export class EditDirectorComponent implements OnInit {
  private _propertySubscribtion: Subscription;
  director: ClientDirector = {};
  directorId: number;
  posts: Post[];
  clientId: number; 
  
  constructor(private service: ClientService, 
              private postService: PostService,
              private flashMessagesService: FlashMessagesService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
    .subscribe(
      (params: Params) => {
        this.directorId = +params['id'];
        }
      )
    this._propertySubscribtion = this.service.property$
      .subscribe(p => {
        this.clientId = p;
        }
      );
    this.getDirectorById(this.directorId, this.clientId);
    this.getPosts();
  }

  onSubmit() {
    this.update();
    this.showMessage();
  }

  delete() {
    if (confirm("Удалить текущего директора?")) {
      this.service.deleteDirector(this.directorId, this.clientId)
        .subscribe(response => {
          this.goBackToDirectors();
        });
    }; 
  }  

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  private update(): void {
    this.service.updateDirector(this.director, this.clientId)
      .subscribe(response => {
        this.goBackToDirectors();
      })
  }

  private showMessage(): void {
    this.flashMessagesService.grayOut(false);
    this.flashMessagesService.show('Директор ' + this.director.shortName + ' успешно обновлен!', 
      {cssClass: 'alert-success text-center', timeout: 1500});
  }

  private getDirectorById(directorId: number, clientId: number) {
    this.service.getDirectorById(directorId, clientId)
      .subscribe(director => {
        this.director = director;
      });
  }
  
  private goBackToDirectors() {
    this.router.navigate(['/clients', this.clientId, 'directors']);
  }

  private getPosts() {
    this.postService.getPosts()
      .subscribe(post => {
        this.posts = post;
      })
  }

  compareFn(c1: Post, c2: Post): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

}
