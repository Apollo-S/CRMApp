import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgreementDetailsMainTabComponent } from './agreement-details-main-tab.component';

describe('AgreementDetailsMainTabComponent', () => {
  let component: AgreementDetailsMainTabComponent;
  let fixture: ComponentFixture<AgreementDetailsMainTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgreementDetailsMainTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgreementDetailsMainTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
