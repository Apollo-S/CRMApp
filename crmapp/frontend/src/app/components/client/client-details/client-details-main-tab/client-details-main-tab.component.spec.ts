import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDetailsMainTabComponent } from './client-details-main-tab.component';

describe('ClientDetailsMainTabComponent', () => {
  let component: ClientDetailsMainTabComponent;
  let fixture: ComponentFixture<ClientDetailsMainTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientDetailsMainTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDetailsMainTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
