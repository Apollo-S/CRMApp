import { Component, OnInit } from '@angular/core';
import { Employee } from '../../../models/Employee';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { EmployeeService } from '../../../services/employee.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  employee: Employee = {};

  constructor(
    private service: EmployeeService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
  }

  onSubmit() {
    this.save();
  }

  private save(): void {
    this.service.addEmployee(this.employee)
      .subscribe(response => {
          this.router.navigate(['/employees', response.id]);
          console.log("new employee id = " + response.id)
        });
  }

}
