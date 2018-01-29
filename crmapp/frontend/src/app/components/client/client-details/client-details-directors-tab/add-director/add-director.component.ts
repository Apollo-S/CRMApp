import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientDirector } from '../../../../../models/ClientDirector';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Post } from '../../../../../models/Post';
import { PostService } from '../../../../../services/post.service';

@Component({
  selector: 'app-add-director',
  templateUrl: './add-director.component.html',
  styleUrls: ['./add-director.component.css']
})
export class AddDirectorComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  director: ClientDirector = {};
  posts: Post[];
  clientId: number;  

  constructor(private service: ClientService, 
              private postService: PostService,
              private router: Router) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
    .subscribe(p => {
      this.clientId = p;
      }
    );
    this.getPosts();
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  onSubmit() {
    this.save();
  }

  private save(): void {
    this.service.addDirector(this.director, this.clientId)
      .subscribe(response => {
          this.router.navigate(['/clients', this.clientId, 'directors']);
        }
      );
  }

  private getPosts() {
    this.postService.getPosts()
      .subscribe(post => {
        this.posts = post;
      })
  }

}
