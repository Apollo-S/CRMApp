import { Component, OnInit, Input } from '@angular/core';
import { EmployeeAddress } from '../../models/EmployeeAddress';

@Component({
  selector: 'app-employee-details-addresses-tab',
  templateUrl: './employee-details-addresses-tab.component.html',
  styleUrls: ['./employee-details-addresses-tab.component.css']
})
export class EmployeeDetailsAddressesTabComponent implements OnInit {
  
  @Input('addresses') addresses: EmployeeAddress[];

  constructor() { }

  ngOnInit() {
  }

}
