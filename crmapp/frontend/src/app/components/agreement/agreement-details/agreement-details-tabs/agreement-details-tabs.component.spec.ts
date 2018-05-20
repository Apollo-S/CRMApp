import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgreementDetailsTabsComponent } from './agreement-details-tabs.component';

describe('AgreementDetailsTabsComponent', () => {
  let component: AgreementDetailsTabsComponent;
  let fixture: ComponentFixture<AgreementDetailsTabsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgreementDetailsTabsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgreementDetailsTabsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
