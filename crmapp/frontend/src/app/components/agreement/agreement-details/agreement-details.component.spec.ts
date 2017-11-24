import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgreementDetailsComponent } from './agreement-details.component';

describe('AgreementDetailsComponent', () => {
  let component: AgreementDetailsComponent;
  let fixture: ComponentFixture<AgreementDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgreementDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgreementDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
