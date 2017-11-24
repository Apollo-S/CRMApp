import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Employee } from '../../../models/Employee';
import { EmployeeService } from '../../../services/employee.service';
import { EmployeeAddress } from '../../../models/EmployeeAddress';
import { EmployeeAccount } from '../../../models/EmployeeAccount';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  employee: Employee = {};
  employeeId: number;

  constructor(private service: EmployeeService,
              private router: Router,
              private route: ActivatedRoute,
              private messagesService: FlashMessagesService) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.employeeId = +params['id'];
        }
      )
    this.service.property = this.employeeId;
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => {
        this.employeeId = p;
      });
    this.getEmployeeById(this.employeeId);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }
  
  getEmployeeById(id: number) {
    this.service.getEmployeeById(id)
      .then(employee => this.employee = employee);
  }

  delete(id: number): void {
    this.service.delete(this.employeeId);
    this.messagesService.show('Cотрудник успешно удален', {cssClass: 'alert-success', timeout: 2000});
    this.router.navigateByUrl("/employees");
    location.reload();
  }

}
