import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Employee } from '../../models/Employee';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {
  employee: Employee;
  id: number;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute) { }

    ngOnInit() {
      this.id = parseInt(this.route.snapshot.params['id']);
      this.getEmployeeById(this.id);
    }
  
    getEmployeeById(id: number) {
      this.employeeService.getEmployeeById(id)
        .then(employee => this.employee = employee);
    }
  
    delete(id: number): void {
      this.employeeService.delete(this.id).then(() => this.goBackToEmployees());
    }
  
    goBackToEmployees(): void {
        this.router.navigateByUrl('/employees');
    }

}
