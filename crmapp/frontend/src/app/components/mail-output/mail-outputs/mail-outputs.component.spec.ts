import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MailOutputsComponent } from './mail-outputs.component';

describe('MailOutputsComponent', () => {
  let component: MailOutputsComponent;
  let fixture: ComponentFixture<MailOutputsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MailOutputsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MailOutputsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
