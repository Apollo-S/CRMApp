import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDetailsAgrementsTabComponent } from './client-details-agrements-tab.component';

describe('ClientDetailsAgrementsTabComponent', () => {
  let component: ClientDetailsAgrementsTabComponent;
  let fixture: ComponentFixture<ClientDetailsAgrementsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientDetailsAgrementsTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDetailsAgrementsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
