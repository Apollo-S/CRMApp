import { Component, OnInit, OnDestroy } from '@angular/core';
import { EmployeeService } from '../../../../services/employee.service';
import { EmployeeAccount } from '../../../../models/EmployeeAccount';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Employee } from '../../../../models/Employee';

@Component({
  selector: 'app-employee-details-accounts-tab',
  templateUrl: './employee-details-accounts-tab.component.html',
  styleUrls: ['./employee-details-accounts-tab.component.css']
})
export class EmployeeDetailsAccountsTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  employee: Employee = {};
  accounts: EmployeeAccount[];
  
  constructor(private service: EmployeeService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => this.employee = p
      );
    this.getAccountsByEmployeeId(this.employee.id);
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  getAccountsByEmployeeId(id: number) {
    this.service.getAccountsByEmployeeId(id)
      .subscribe(
        accounts => this.accounts = accounts
      );
  }

}
