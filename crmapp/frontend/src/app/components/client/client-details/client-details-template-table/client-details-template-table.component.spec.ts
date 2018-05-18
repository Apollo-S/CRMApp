import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDetailsTemplateTableComponent } from './client-details-template-table.component';

describe('ClientDetailsTemplateTableComponent', () => {
  let component: ClientDetailsTemplateTableComponent;
  let fixture: ComponentFixture<ClientDetailsTemplateTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientDetailsTemplateTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDetailsTemplateTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
