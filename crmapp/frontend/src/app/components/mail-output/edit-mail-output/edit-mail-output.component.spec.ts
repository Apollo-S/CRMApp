import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditMailOutputComponent } from './edit-mail-output.component';

describe('EditMailOutputComponent', () => {
  let component: EditMailOutputComponent;
  let fixture: ComponentFixture<EditMailOutputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditMailOutputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMailOutputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
