import { Component, OnInit } from '@angular/core';
import { Employee } from '../../../models/Employee';
import { DatePipe } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { EmployeeService } from '../../../services/employee.service';
import { FlashMessagesService } from 'angular2-flash-messages';

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
    private route: ActivatedRoute,
    private flashMessagesService: FlashMessagesService) { }

  ngOnInit() {
  }

  private save(): void {
    this.service.addEmployee(this.employee)
      .subscribe(response => {
          this.router.navigateByUrl("/employee/" + response.id);
        });
  }

  onSubmit() {
    this.save();
    this.flashMessagesService.show('Новый сотрудник успешно сохранен', {cssClass: 'alert-success', timeout: 2000});
  }

}
