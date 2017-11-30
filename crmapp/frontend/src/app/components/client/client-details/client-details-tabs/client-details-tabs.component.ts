import { Component, OnInit, Input } from '@angular/core';
import { Client } from '../../../../models/Client';

@Component({
  selector: 'app-client-details-tabs',
  templateUrl: './client-details-tabs.component.html',
  styleUrls: ['./client-details-tabs.component.css']
})
export class ClientDetailsTabsComponent implements OnInit {

  @Input("client") client: Client;

  constructor() { }

  ngOnInit() {
  }

}
