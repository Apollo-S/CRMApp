import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgreementDetailsDocumentsTabComponent } from './agreement-details-documents-tab.component';

describe('AgreementDetailsDocumentsTabComponent', () => {
  let component: AgreementDetailsDocumentsTabComponent;
  let fixture: ComponentFixture<AgreementDetailsDocumentsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgreementDetailsDocumentsTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgreementDetailsDocumentsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
