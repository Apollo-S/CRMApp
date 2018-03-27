import { Component, OnInit, Input } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar-menu-bar',
  templateUrl: './navbar-menu-bar.component.html',
  styleUrls: ['./navbar-menu-bar.component.css']
})
export class NavbarMenuBarComponent implements OnInit {
  @Input('model') model: MenuItem[];
  @Input('autoDisplay') autoDisplay: boolean = true;

  constructor() { }

  ngOnInit() {
  }

}
