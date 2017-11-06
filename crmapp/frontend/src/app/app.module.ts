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
import { SidebarEmployeeComponent } from './components/sidebar-employee/sidebar-employee.component';
import { SidebarClientComponent } from './components/sidebar-client/sidebar-client.component';
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
    SidebarEmployeeComponent,
    SidebarClientComponent,
    AgreementsComponent,
    DocumentsComponent,
    ClientDetailsAgrementsTabComponent,
    ClientDetailsAddressesTabComponent,
    ClientDetailsAccountsTabComponent,
    ClientDetailsDirectorsTabComponent,
    EmployeeDetailsComponent,
    AddEmployeeComponent,
    EditEmployeeComponent
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
    AgreementService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
