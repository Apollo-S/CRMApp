import { Component, OnInit, Input } from '@angular/core';
import { EmployeeAccount } from '../../models/EmployeeAccount';

@Component({
  selector: 'app-employee-details-accounts-tab',
  templateUrl: './employee-details-accounts-tab.component.html',
  styleUrls: ['./employee-details-accounts-tab.component.css']
})
export class EmployeeDetailsAccountsTabComponent implements OnInit {
  
  @Input('accounts') accounts: EmployeeAccount[];
  
  constructor() { }

  ngOnInit() {
  }

}
