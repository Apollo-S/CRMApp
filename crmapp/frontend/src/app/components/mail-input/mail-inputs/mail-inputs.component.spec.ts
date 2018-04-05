import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MailInputsComponent } from './mail-inputs.component';

describe('MailInputsComponent', () => {
  let component: MailInputsComponent;
  let fixture: ComponentFixture<MailInputsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MailInputsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MailInputsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
