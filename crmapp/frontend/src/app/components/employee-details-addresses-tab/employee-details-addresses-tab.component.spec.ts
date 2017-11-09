import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeDetailsAddressesTabComponent } from './employee-details-addresses-tab.component';

describe('EmployeeDetailsAddressesTabComponent', () => {
  let component: EmployeeDetailsAddressesTabComponent;
  let fixture: ComponentFixture<EmployeeDetailsAddressesTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeDetailsAddressesTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeDetailsAddressesTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
