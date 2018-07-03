import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeDetailsSicksTabComponent } from './employee-details-sicks-tab.component';

describe('EmployeeDetailsSicksTabComponent', () => {
  let component: EmployeeDetailsSicksTabComponent;
  let fixture: ComponentFixture<EmployeeDetailsSicksTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeDetailsSicksTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeDetailsSicksTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
