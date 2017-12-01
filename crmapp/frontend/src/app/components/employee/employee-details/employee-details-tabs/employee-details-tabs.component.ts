import { Component, OnInit, Input } from '@angular/core';
import { Employee } from '../../../../models/Employee';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-employee-details-tabs',
  templateUrl: './employee-details-tabs.component.html',
  styleUrls: ['./employee-details-tabs.component.css']
})
export class EmployeeDetailsTabsComponent implements OnInit {

  @Input ('employee') employee: Employee;
  
  constructor(private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
  }

}
