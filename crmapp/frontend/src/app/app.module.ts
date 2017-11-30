import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { FlashMessagesModule } from 'angular2-flash-messages';

// Service Imports
import { ClientService } from './services/client.service';
import { EmployeeService } from './services/employee.service';
import { AgreementService } from './services/agreement.service';
import { VacationService } from './services/vacation.service';

// Component Imports 
import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NavbarBottomComponent } from './components/navbar-bottom/navbar-bottom.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

import { ClientsComponent } from './components/client/clients/clients.component';
import { AddClientComponent } from './components/client/add-client/add-client.component';
import { EditClientComponent } from './components/client/edit-client/edit-client.component';
import { ClientDetailsComponent } from './components/client/client-details/client-details.component';
import { ClientDetailsTabsComponent } from './components/client/client-details/client-details-tabs/client-details-tabs.component';
import { ClientDetailsMainTabComponent } from './components/client/client-details/client-details-main-tab/client-details-main-tab.component';
import { ClientDetailsAgreementsTabComponent } from './components/client/client-details/client-details-agreements-tab/client-details-agreements-tab.component';
import { ClientDetailsAddressesTabComponent } from './components/client/client-details/client-details-addresses-tab/client-details-addresses-tab.component';
import { ClientDetailsAccountsTabComponent } from './components/client/client-details/client-details-accounts-tab/client-details-accounts-tab.component';
import { ClientDetailsDirectorsTabComponent } from './components/client/client-details/client-details-directors-tab/client-details-directors-tab.component';

import { AgreementsComponent } from './components/agreement/agreements/agreements.component';
import { AddAgreementComponent } from './components/agreement/add-agreement/add-agreement.component';
import { EditAgreementComponent } from './components/agreement/edit-agreement/edit-agreement.component';
import { AgreementDetailsComponent } from './components/agreement/agreement-details/agreement-details.component';

import { DocumentsComponent } from './components/document/documents/documents.component';

import { EmployeesComponent } from './components/employee/employees/employees.component';
import { AddEmployeeComponent } from './components/employee/add-employee/add-employee.component';
import { EditEmployeeComponent } from './components/employee/edit-employee/edit-employee.component';
import { EmployeeDetailsComponent } from './components/employee/employee-details/employee-details.component';
import { EmployeeDetailsTabsComponent } from './components/employee/employee-details/employee-details-tabs/employee-details-tabs.component';
import { EmployeeDetailsMainTabComponent } from './components/employee/employee-details/employee-details-main-tab/employee-details-main-tab.component';
import { EmployeeDetailsAddressesTabComponent } from './components/employee/employee-details/employee-details-addresses-tab/employee-details-addresses-tab.component';
import { EmployeeDetailsAccountsTabComponent } from './components/employee/employee-details/employee-details-accounts-tab/employee-details-accounts-tab.component';

import { VacationsComponent } from './components/vacation/vacations/vacations.component';
import { VacationDetailsComponent } from './components/vacation/vacation-details/vacation-details.component';

const appRoutes: Routes = [
  {path: 'about', component:AboutComponent},
  {path: '', component:DashboardComponent, 
    children: 
    [
      {path:  '', redirectTo: 'clients', pathMatch: 'full'},
      {path: 'clients', component:ClientsComponent},
      {path: 'employees', component:EmployeesComponent},
      {path: 'documents', component:DocumentsComponent},
      {path: 'vacations', component:VacationsComponent},
      {path: 'agreements', component:AgreementsComponent},
    ]
  },
  {path: 'clients/add', component:AddClientComponent},
  {path: 'clients/:id/edit', component:EditClientComponent},
  {path: 'clients/:id', component:ClientDetailsComponent,
    children:
    [
      {path:  '', redirectTo: 'main', pathMatch: 'full'},
      {path: 'main', component: ClientDetailsMainTabComponent},
      {path: 'addresses', component: ClientDetailsAddressesTabComponent,
        children: 
        [
          {path: ':id/edit', component:EditClientComponent},
        ]
      },
      {path: 'accounts', component: ClientDetailsAccountsTabComponent},
      {path: 'directors', component: ClientDetailsDirectorsTabComponent},
      {path: 'agreements', component: ClientDetailsAgreementsTabComponent}
    ]
  },

  {path: 'employees/add', component:AddEmployeeComponent},
  {path: 'employees/:id/edit', component:EditEmployeeComponent},
  {path: 'employees/:id', component:EmployeeDetailsComponent, 
    children: 
    [
      {path:  '', redirectTo: 'main', pathMatch: 'full'},
      {path: 'main', component: EmployeeDetailsMainTabComponent},
      {path: 'addresses', component: EmployeeDetailsAddressesTabComponent},
      {path: 'accounts', component: EmployeeDetailsAccountsTabComponent}
    ]
  },

  {path: 'agreements/:id', component:AgreementDetailsComponent},
  {path: 'agreements/add', component:AddAgreementComponent},
  {path: 'agreements/:id/edit', component:EditAgreementComponent},

]

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    NavbarComponent,
    NavbarBottomComponent,
    ClientsComponent,
    AddClientComponent,
    ClientDetailsComponent,
    EditClientComponent,
    SidebarComponent,
    DashboardComponent,
    PageNotFoundComponent,
    EmployeesComponent,
    AgreementsComponent,
    DocumentsComponent,
    ClientDetailsAgreementsTabComponent,
    ClientDetailsAddressesTabComponent,
    ClientDetailsAccountsTabComponent,
    ClientDetailsDirectorsTabComponent,
    EmployeeDetailsComponent,
    AddEmployeeComponent,
    EditEmployeeComponent,
    EmployeeDetailsAddressesTabComponent,
    EmployeeDetailsAccountsTabComponent,
    AgreementDetailsComponent,
    AddAgreementComponent,
    EditAgreementComponent,
    VacationsComponent,
    VacationDetailsComponent,
    EmployeeDetailsMainTabComponent,
    EmployeeDetailsTabsComponent,
    ClientDetailsMainTabComponent,
    ClientDetailsTabsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    FlashMessagesModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    ClientService,
    EmployeeService,
    AgreementService,
    VacationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
