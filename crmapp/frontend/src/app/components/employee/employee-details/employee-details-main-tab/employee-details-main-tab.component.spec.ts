import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeDetailsMainTabComponent } from './employee-details-main-tab.component';

describe('EmployeeDetailsMainTabComponent', () => {
  let component: EmployeeDetailsMainTabComponent;
  let fixture: ComponentFixture<EmployeeDetailsMainTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeDetailsMainTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeDetailsMainTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
