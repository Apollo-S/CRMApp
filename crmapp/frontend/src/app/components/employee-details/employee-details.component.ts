import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Employee } from '../../models/Employee';
import { EmployeeService } from '../../services/employee.service';
import { EmployeeAddress } from '../../models/EmployeeAddress';
import { EmployeeAccount } from '../../models/EmployeeAccount';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {
  employee: Employee;
  id: number;
  addresses: EmployeeAddress[];
  accounts: EmployeeAccount[];

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute) { }

    ngOnInit() {
      this.id = parseInt(this.route.snapshot.params['id']);
      this.getEmployeeById(this.id);
      this.getAddressesByEmployeeId(this.id);
      this.getAccountsByEmployeeId(this.id);
    }
  
    getEmployeeById(id: number) {
      this.employeeService.getEmployeeById(id)
        .then(employee => this.employee = employee);
    }

    getAddressesByEmployeeId(employeeId: number) {
      this.employeeService.getAddressesByEmployeeId(employeeId)
        .then(addresses => this.addresses = addresses);
    }
  
    getAccountsByEmployeeId(employeeId: number) {
      this.employeeService.getAccountsByEmployeeId(employeeId)
        .then(accounts => this.accounts = accounts);
    }
  
    delete(id: number): void {
      this.employeeService.delete(this.id).then(() => this.goBackToEmployees());
    }
  
    goBackToEmployees(): void {
        this.router.navigateByUrl('/employees');
    }

}
