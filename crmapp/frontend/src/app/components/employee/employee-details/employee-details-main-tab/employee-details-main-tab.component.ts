import { Component, OnInit, OnDestroy } from '@angular/core';
import { Employee } from '../../../../models/Employee';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { EmployeeService } from '../../../../services/employee.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-employee-details-main-tab',
  templateUrl: './employee-details-main-tab.component.html',
  styleUrls: ['./employee-details-main-tab.component.css']
})
export class EmployeeDetailsMainTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  employee: Employee = {};
  employeeId: number;
  
  constructor(private service: EmployeeService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(p => {
        this.employeeId = p;
        console.log("main tab property = " + this.employeeId);
      });
    this.getEmployeeById(this.employeeId);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  getEmployeeById(id: number) {
    this.service.getEmployeeById(id)
      .subscribe(employee => this.employee = employee);
  }

}
