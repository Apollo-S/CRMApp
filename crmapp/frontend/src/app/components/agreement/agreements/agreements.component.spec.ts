import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgreementsComponent } from './agreements.component';

describe('AgreementsComponent', () => {
  let component: AgreementsComponent;
  let fixture: ComponentFixture<AgreementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgreementsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgreementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
