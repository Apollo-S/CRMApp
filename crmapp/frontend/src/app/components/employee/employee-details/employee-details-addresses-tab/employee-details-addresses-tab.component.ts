import { Component, OnInit, OnDestroy } from '@angular/core';
import { EmployeeAddress } from '../../../../models/EmployeeAddress';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { EmployeeService } from '../../../../services/employee.service';

@Component({
  selector: 'app-employee-details-addresses-tab',
  templateUrl: './employee-details-addresses-tab.component.html',
  styleUrls: ['./employee-details-addresses-tab.component.css']
})
export class EmployeeDetailsAddressesTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  addresses: EmployeeAddress[];
  employeeId: number;

  constructor(private service: EmployeeService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(p => {
        this.employeeId = p;
        console.log("address tab property = " + this.employeeId);
    });
    this.getAddressesByEmployeeId(this.employeeId);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  getAddressesByEmployeeId(employeeId: number) {
    this.service.getAddressesByEmployeeId(employeeId)
      .then(addresses => this.addresses = addresses);
  }

}
