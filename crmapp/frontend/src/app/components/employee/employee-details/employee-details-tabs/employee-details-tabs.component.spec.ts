import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeDetailsTabsComponent } from './employee-details-tabs.component';

describe('EmployeeDetailsTabsComponent', () => {
  let component: EmployeeDetailsTabsComponent;
  let fixture: ComponentFixture<EmployeeDetailsTabsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeDetailsTabsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeDetailsTabsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
