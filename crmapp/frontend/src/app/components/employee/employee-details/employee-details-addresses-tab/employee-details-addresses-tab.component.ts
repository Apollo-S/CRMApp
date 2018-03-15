import { Component, OnInit, OnDestroy } from '@angular/core';
import { EmployeeAddress } from '../../../../models/EmployeeAddress';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { EmployeeService } from '../../../../services/employee.service';
import { Employee } from '../../../../models/Employee';

@Component({
  selector: 'app-employee-details-addresses-tab',
  templateUrl: './employee-details-addresses-tab.component.html',
  styleUrls: ['./employee-details-addresses-tab.component.css']
})
export class EmployeeDetailsAddressesTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  addresses: EmployeeAddress[] = [];
  employee: Employee = {};

  constructor(private service: EmployeeService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => this.employee = p
      );
    this.getAddressesByEmployeeId(this.employee.id);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  getAddressesByEmployeeId(id: number) {
    this.service.getAddressesByEmployeeId(id)
      .subscribe(
        addresses => this.addresses = addresses
      );
  }

}
