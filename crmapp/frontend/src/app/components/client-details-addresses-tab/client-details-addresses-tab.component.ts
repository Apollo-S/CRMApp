import { Component, OnInit, Input  } from '@angular/core';
import { ClientAddress } from '../../models/ClientAddress';

@Component({
  selector: 'app-client-details-addresses-tab',
  templateUrl: './client-details-addresses-tab.component.html',
  styleUrls: ['./client-details-addresses-tab.component.css']
})
export class ClientDetailsAddressesTabComponent implements OnInit {

  @Input('addresses') addresses: ClientAddress[];
  
  constructor() { }

  ngOnInit() { 
  }

}
