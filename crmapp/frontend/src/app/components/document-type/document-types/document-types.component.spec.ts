import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentTypesComponent } from './document-types.component';

describe('DocumentTypesComponent', () => {
  let component: DocumentTypesComponent;
  let fixture: ComponentFixture<DocumentTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
