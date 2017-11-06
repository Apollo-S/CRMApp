import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Employee } from '../../models/Employee';
import { EmployeeService } from '../../services/employee.service';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {
  employee: Employee;
  id: number;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute,
    private flashMessagesService: FlashMessagesService) { }

  ngOnInit() {
    this.id = parseInt(this.route.snapshot.params['id']);
    this.employeeService.getEmployeeById(this.id)
      .then(employee => this.employee = employee);
  }

  private update(): void {
    this.employeeService.updateEmployee(this.employee);
  }

  onSubmit() {
    this.update();
    this.flashMessagesService.show('Изменения сохранены', {cssClass: 'alert-success', timeout: 3000});
    location.reload();
  }

}
