import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ClientDetailsAgreementsTabComponent } from './client-details-agreements-tab.component';

describe('ClientDetailsAgreementsTabComponent', () => {
  let component: ClientDetailsAgreementsTabComponent;
  let fixture: ComponentFixture<ClientDetailsAgreementsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientDetailsAgreementsTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDetailsAgreementsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
