import { Component, OnInit, Input } from '@angular/core';
import { ClientAccount } from '../../models/ClientAccount';

@Component({
  selector: 'app-client-details-accounts-tab',
  templateUrl: './client-details-accounts-tab.component.html',
  styleUrls: ['./client-details-accounts-tab.component.css']
})
export class ClientDetailsAccountsTabComponent implements OnInit {

  @Input('accounts') accounts: ClientAccount[];

  constructor() { }

  ngOnInit() {
  }

}
