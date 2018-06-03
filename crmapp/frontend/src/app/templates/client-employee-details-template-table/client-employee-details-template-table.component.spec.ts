import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientEmployeeDetailsTemplateTableComponent } from './client-employee-details-template-table.component';

describe('ClientEmployeeDetailsTemplateTableComponent', () => {
  let component: ClientEmployeeDetailsTemplateTableComponent;
  let fixture: ComponentFixture<ClientEmployeeDetailsTemplateTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientEmployeeDetailsTemplateTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientEmployeeDetailsTemplateTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
