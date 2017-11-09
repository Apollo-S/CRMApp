import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VacationDetailsComponent } from './vacation-details.component';

describe('VacationDetailsComponent', () => {
  let component: VacationDetailsComponent;
  let fixture: ComponentFixture<VacationDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VacationDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VacationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
