import { Component, OnInit, OnDestroy } from '@angular/core';
import { Employee } from '../../../../models/Employee';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { EmployeeService } from '../../../../services/employee.service';
import { MessageService } from 'primeng/components/common/messageservice';
import { ConfirmationService, Message } from 'primeng/api';

@Component({
  selector: 'app-employee-details-main-tab',
  templateUrl: './employee-details-main-tab.component.html',
  styleUrls: ['./employee-details-main-tab.component.css']
})
export class EmployeeDetailsMainTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  employee: Employee = {};
  
  constructor(private service: EmployeeService,
              private router: Router,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => this.employee = p
      );
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  confirmDeleting() {
    let msg  = 'Сотрудник \"' + this.employee.personShortName + '(ID=' + this.employee.id + ')\" успешно удален';
    this.confirmationService.confirm({
      message: 'Действительно удалить сотрудника?',
      header: 'Удаление объекта',
      icon: 'fa fa-trash',
      accept: () => {
        this.delete();
        this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
      },
      reject: () => {}
    });
  }

  private delete(): void {
    this.service.deleteEmployee(this.employee)
      .subscribe(
        () => this.goBackToEmpoloyees()
      );
  }

  private goBackToEmpoloyees(): void {
    setTimeout(
      () => {
        this.router.navigate(['/employees']);
      }, 1500);
  }

}
