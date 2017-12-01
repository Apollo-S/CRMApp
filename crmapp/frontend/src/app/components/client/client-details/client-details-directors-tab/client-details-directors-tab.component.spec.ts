import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDetailsDirectorsTabComponent } from './client-details-directors-tab.component';

describe('ClientDetailsDirectorsTabComponent', () => {
  let component: ClientDetailsDirectorsTabComponent;
  let fixture: ComponentFixture<ClientDetailsDirectorsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientDetailsDirectorsTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDetailsDirectorsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
