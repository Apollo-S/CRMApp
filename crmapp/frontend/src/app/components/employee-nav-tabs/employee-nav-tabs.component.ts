import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-employee-nav-tabs',
  templateUrl: './employee-nav-tabs.component.html',
  styleUrls: ['./employee-nav-tabs.component.css']
})
export class EmployeeNavTabsComponent implements OnInit {

  @Input ('disabled') disabled: string;

  constructor() { }

  ngOnInit() {
  }

}
