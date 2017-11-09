import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeNavTabsComponent } from './employee-nav-tabs.component';

describe('EmployeeNavTabsComponent', () => {
  let component: EmployeeNavTabsComponent;
  let fixture: ComponentFixture<EmployeeNavTabsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeNavTabsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeNavTabsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
