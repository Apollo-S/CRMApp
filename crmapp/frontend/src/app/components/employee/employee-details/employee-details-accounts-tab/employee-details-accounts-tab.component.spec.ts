import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeDetailsAccountsTabComponent } from './employee-details-accounts-tab.component';

describe('EmployeeDetailsAccountsTabComponent', () => {
  let component: EmployeeDetailsAccountsTabComponent;
  let fixture: ComponentFixture<EmployeeDetailsAccountsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeDetailsAccountsTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeDetailsAccountsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
