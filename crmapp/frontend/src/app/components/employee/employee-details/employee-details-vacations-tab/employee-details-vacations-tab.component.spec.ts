import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeDetailsVacationsTabComponent } from './employee-details-vacations-tab.component';

describe('EmployeeDetailsVacationsTabComponent', () => {
  let component: EmployeeDetailsVacationsTabComponent;
  let fixture: ComponentFixture<EmployeeDetailsVacationsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeDetailsVacationsTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeDetailsVacationsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
