import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarMenuBarComponent } from './navbar-menu-bar.component';

describe('NavbarMenuBarComponent', () => {
  let component: NavbarMenuBarComponent;
  let fixture: ComponentFixture<NavbarMenuBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavbarMenuBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarMenuBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
