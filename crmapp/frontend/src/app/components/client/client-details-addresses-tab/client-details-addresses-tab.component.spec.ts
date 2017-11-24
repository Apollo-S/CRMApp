import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDetailsAddressesTabComponent } from './client-details-addresses-tab.component';

describe('ClientDetailsAddressesTabComponent', () => {
  let component: ClientDetailsAddressesTabComponent;
  let fixture: ComponentFixture<ClientDetailsAddressesTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientDetailsAddressesTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDetailsAddressesTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
