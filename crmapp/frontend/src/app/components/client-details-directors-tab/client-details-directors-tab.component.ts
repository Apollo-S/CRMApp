import { Component, OnInit, Input } from '@angular/core';
import { ClientDirector } from '../../models/ClientDirector';

@Component({
  selector: 'app-client-details-directors-tab',
  templateUrl: './client-details-directors-tab.component.html',
  styleUrls: ['./client-details-directors-tab.component.css']
})
export class ClientDetailsDirectorsTabComponent implements OnInit {

  @Input('directors') directors: ClientDirector[];

  constructor() { }

  ngOnInit() {
  }

}
