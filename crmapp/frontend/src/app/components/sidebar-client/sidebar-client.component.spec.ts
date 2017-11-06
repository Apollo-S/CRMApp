import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarClientComponent } from './sidebar-client.component';

describe('SidebarClientComponent', () => {
  let component: SidebarClientComponent;
  let fixture: ComponentFixture<SidebarClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidebarClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
