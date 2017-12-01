import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDetailsTabsComponent } from './client-details-tabs.component';

describe('ClientDetailsTabsComponent', () => {
  let component: ClientDetailsTabsComponent;
  let fixture: ComponentFixture<ClientDetailsTabsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientDetailsTabsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDetailsTabsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
