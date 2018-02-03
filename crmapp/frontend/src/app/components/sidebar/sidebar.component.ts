import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  @Input ('url') url: string;
  @Input ('title') title: string;
  @Input ('icon') icon: string;
  @Input ('classButton') classButton: string;

  constructor() { }

  ngOnInit() {
  }

}
