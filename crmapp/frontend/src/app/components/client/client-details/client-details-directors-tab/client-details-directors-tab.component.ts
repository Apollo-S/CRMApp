import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientDirector } from '../../../../models/ClientDirector';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Post } from '../../../../models/Post';

@Component({
  selector: 'app-client-details-directors-tab',
  templateUrl: './client-details-directors-tab.component.html',
  styleUrls: ['./client-details-directors-tab.component.css']
})
export class ClientDetailsDirectorsTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  columns: any[];
  directors: ClientDirector[];
  posts: Post[];
  clientId: number;

  constructor(private service: ClientService,
              // private postService: PostService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() { 
    this._propertySubscribtion = this.service.property$
      .subscribe(p => {
        this.clientId = p;
      }
    );
    this.getDirectorsByClientId(this.clientId);
    this.columns = [
      { field: '', header: 'ID' },
      { field: '', header: 'ФИО' },
      { field: '', header: 'Должность' },
      { field: '', header: 'Актуален с' }      
    ];
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  getDirectorsByClientId(clientId: number) {
    this.service.getDirectorsByClientId(clientId)
      .subscribe(directors => this.directors = directors);
  }

}
