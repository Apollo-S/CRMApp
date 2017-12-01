import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDetailsAccountsTabComponent } from './client-details-accounts-tab.component';

describe('ClientDetailsAccountsTabComponent', () => {
  let component: ClientDetailsAccountsTabComponent;
  let fixture: ComponentFixture<ClientDetailsAccountsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientDetailsAccountsTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDetailsAccountsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
