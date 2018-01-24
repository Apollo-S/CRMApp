import { Component, OnInit, OnDestroy } from '@angular/core';
import { EmployeeService } from '../../../../services/employee.service';
import { EmployeeAccount } from '../../../../models/EmployeeAccount';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-employee-details-accounts-tab',
  templateUrl: './employee-details-accounts-tab.component.html',
  styleUrls: ['./employee-details-accounts-tab.component.css']
})
export class EmployeeDetailsAccountsTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  accounts: EmployeeAccount[];
  employeeId: number;
  
  constructor(private service: EmployeeService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.getPropertySubscribtion();
    this.getAccountsByEmployeeId(this.employeeId);
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  private getPropertySubscribtion() {
    this._propertySubscribtion = this.service.property$
      .subscribe(p => {
        this.employeeId = p;
        console.log("account tab property = " + this.employeeId);
    });
  }

  getAccountsByEmployeeId(employeeId: number) {
    this.service.getAccountsByEmployeeId(employeeId)
      .subscribe(accounts => this.accounts = accounts);
  }

}
