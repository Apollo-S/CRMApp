import { Component, OnInit, OnDestroy } from '@angular/core';
import { EmployeeService } from '../../../../services/employee.service';
import { EmployeeAccount } from '../../../../models/EmployeeAccount';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Employee } from '../../../../models/Employee';

@Component({
  selector: 'app-employee-details-accounts-tab',
  templateUrl: './employee-details-accounts-tab.component.html',
  styleUrls: ['./employee-details-accounts-tab.component.css']
})
export class EmployeeDetailsAccountsTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  columns: any[];
  employee: Employee = {};
  accounts: EmployeeAccount[];
  
  constructor(private service: EmployeeService) { }

  ngOnInit() {
    this.initSubscription();
    this.initColumns();
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }
  
  private initSubscription() {
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => {
          this.employee = p;
          this.getAccountsByEmployeeId(p.id);
        }
      );
  }

  private getAccountsByEmployeeId(id: number) {
    this.service.getAccountsByEmployeeId(id)
      .subscribe(
        accounts => this.accounts = accounts
      );
  }

  private initColumns(): void {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'presentation', header: 'Представление' },
      { field: 'dateStart', header: 'Действует с' }      
    ];
  }

}
