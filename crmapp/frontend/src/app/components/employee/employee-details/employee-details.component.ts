import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { EmployeeService } from '../../../services/employee.service';
import { Employee } from '../../../models/Employee';
import { Location } from '@angular/common';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  employee: Employee = {};

  constructor(private service: EmployeeService,
              private route: ActivatedRoute, 
              private location: Location) { }

  ngOnInit() {
    let employeeId: number;
    this.route.params
      .subscribe(
        (params: Params) => {
          employeeId = +params['id'];
          this.getEmployeeById(employeeId);
        }
      );
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  goBack() {
    this.location.back();
  }
  
  private getEmployeeById(id: number) {
    this.service.getEmployeeById(id)
      .subscribe(
        employee => {
          this.employee = employee;
          this.service.property = this.employee;
          this._propertySubscribtion = this.service.property$
            .subscribe(
              p => this.employee = p
            );
        }
      );
  }

}
