import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Employee } from '../../../models/Employee';
import { EmployeeService } from '../../../services/employee.service';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {
  employee: Employee = {};
  employeeId: number;

  constructor(private employeeService: EmployeeService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.employeeId = +params['id'];
      });
    this.getEmployeeById(this.employeeId);
  }
  
  onSubmit() {
    this.update();
    location.reload();
  }

  private getEmployeeById(id: number) {
    this.employeeService.getEmployeeById(this.employeeId)
      .subscribe(employee => this.employee = employee);
  }
  
  private update(): void {
    this.employeeService.updateEmployee(this.employee);
  }

}
