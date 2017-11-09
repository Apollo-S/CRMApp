import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
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
import { ClientsComponent } from './components/clients/clients.component';
import { AddClientComponent } from './components/add-client/add-client.component';
import { ClientDetailsComponent } from './components/client-details/client-details.component';
import { EditClientComponent } from './components/edit-client/edit-client.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { AgreementsComponent } from './components/agreements/agreements.component';
import { DocumentsComponent } from './components/documents/documents.component';
import { ClientDetailsAgrementsTabComponent } from './components/client-details-agrements-tab/client-details-agrements-tab.component';
import { ClientDetailsAddressesTabComponent } from './components/client-details-addresses-tab/client-details-addresses-tab.component';
import { ClientDetailsAccountsTabComponent } from './components/client-details-accounts-tab/client-details-accounts-tab.component';
import { ClientDetailsDirectorsTabComponent } from './components/client-details-directors-tab/client-details-directors-tab.component';
import { EmployeesComponent } from './components/employees/employees.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { AddEmployeeComponent } from './components/add-employee/add-employee.component';
import { EditEmployeeComponent } from './components/edit-employee/edit-employee.component';
import { EmployeeDetailsAddressesTabComponent } from './components/employee-details-addresses-tab/employee-details-addresses-tab.component';
import { EmployeeDetailsAccountsTabComponent } from './components/employee-details-accounts-tab/employee-details-accounts-tab.component';
import { AgreementDetailsComponent } from './components/agreement-details/agreement-details.component';
import { AddAgreementComponent } from './components/add-agreement/add-agreement.component';
import { EditAgreementComponent } from './components/edit-agreement/edit-agreement.component';
import { EmployeeNavTabsComponent } from './components/employee-nav-tabs/employee-nav-tabs.component';
import { VacationsComponent } from './components/vacations/vacations.component';
import { VacationDetailsComponent } from './components/vacation-details/vacation-details.component';


const appRoutes: Routes = [
  {path: '', component:DashboardComponent},
  {path: 'about', component:AboutComponent},

  {path: 'clients', component:ClientsComponent},
  {path: 'client/:id', component:ClientDetailsComponent},
  {path: 'edit-client/:id', component:EditClientComponent},
  {path: 'add-client', component:AddClientComponent},

  {path: 'employees', component:EmployeesComponent},
  {path: 'employee/:id', component:EmployeeDetailsComponent},
  {path: 'edit-employee/:id', component:EditEmployeeComponent},
  {path: 'add-employee', component:AddEmployeeComponent},

  {path: 'agreements', component:AgreementsComponent},
  {path: 'agreement/:id', component:AgreementDetailsComponent},
  {path: 'add-agreement', component:AddAgreementComponent},
  {path: 'edit-agreement/:id', component:EditAgreementComponent},

  {path: 'vacations', component:VacationsComponent},

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
    ClientDetailsAgrementsTabComponent,
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
    EmployeeNavTabsComponent,
    VacationsComponent,
    VacationDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    FlashMessagesModule,
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
