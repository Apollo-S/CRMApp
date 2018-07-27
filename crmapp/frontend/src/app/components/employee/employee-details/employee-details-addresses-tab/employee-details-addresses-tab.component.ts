import { Component, OnInit, OnDestroy } from '@angular/core';
import { EmployeeAddress } from '../../../../models/EmployeeAddress';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { EmployeeService } from '../../../../services/employee.service';
import { Employee } from '../../../../models/Employee';

@Component({
  selector: 'app-employee-details-addresses-tab',
  templateUrl: './employee-details-addresses-tab.component.html',
  styleUrls: ['./employee-details-addresses-tab.component.css']
})
export class EmployeeDetailsAddressesTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  columns: any[] = [];
  addresses: EmployeeAddress[] = [];
  employee: Employee = {};

  constructor(private service: EmployeeService) { }

  ngOnInit() {
    this.initSubscription();
    this.initColumns();
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private initSubscription() {
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => {
          this.employee = p;
          this.getAddressesByEmployeeId(p.id);
        }
      );
  }

  private getAddressesByEmployeeId(id: number) {
    this.service.getAddressesByEmployeeId(id)
      .subscribe(
        addresses => this.addresses = addresses
      );
  }

  private initColumns(): void {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'presentation', header: 'Адрес' },
      { field: 'dateStart', header: 'Действует с' }      
    ];
  }

}
