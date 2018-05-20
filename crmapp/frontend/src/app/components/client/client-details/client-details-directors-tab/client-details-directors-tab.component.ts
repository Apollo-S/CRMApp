import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../services/client.service';
import { ClientDirector } from '../../../../models/ClientDirector';
import { Client } from '../../../../models/Client';
import { Post } from '../../../../models/Post';

@Component({
  selector: 'app-client-details-directors-tab',
  templateUrl: './client-details-directors-tab.component.html',
  styleUrls: ['./client-details-directors-tab.component.css']
})
export class ClientDetailsDirectorsTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  columns: any[];
  directors: ClientDirector[] = [];
  posts: Post[] = [];
  client: Client = {};

  constructor(private service: ClientService) { }

  ngOnInit() { 
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => {
          this.client = p;
          this.getDirectorsByClientId(p.id);
        }
      );
    this.initColumns();
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private getDirectorsByClientId(id: number) {
    this.service.getDirectorsByClientId(id)
      .subscribe(
        directors => this.directors = directors
      );
  }

  private initColumns() {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'fullName', header: 'ФИО' },
      { field: 'postTitle', header: 'Должность' },
      { field: 'dateStart', header: 'Актуален с' }      
    ];
  }

}
