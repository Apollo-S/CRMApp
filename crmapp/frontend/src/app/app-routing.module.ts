import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';

import { AboutComponent } from './components/about/about.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NavbarBottomComponent } from './components/navbar-bottom/navbar-bottom.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DashboardMenuComponent } from './components/dashboard/dashboard-menu/dashboard-menu.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

import { ClientsComponent } from './components/client/clients/clients.component';
import { AddClientComponent } from './components/client/add-client/add-client.component';
import { EditClientComponent } from './components/client/edit-client/edit-client.component';
import { ClientDetailsComponent } from './components/client/client-details/client-details.component';
import { ClientDetailsTabsComponent } from './components/client/client-details/client-details-tabs/client-details-tabs.component';
import { ClientDetailsMainTabComponent } from './components/client/client-details/client-details-main-tab/client-details-main-tab.component';
import { ClientDetailsAddressesTabComponent } from './components/client/client-details/client-details-addresses-tab/client-details-addresses-tab.component';
import { AddAddressComponent } from './components/client/client-details/client-details-addresses-tab/add-address/add-address.component';
import { EditAddressComponent } from './components/client/client-details/client-details-addresses-tab/edit-address/edit-address.component';
import { ClientDetailsAccountsTabComponent } from './components/client/client-details/client-details-accounts-tab/client-details-accounts-tab.component';
import { AddAccountComponent } from './components/client/client-details/client-details-accounts-tab/add-account/add-account.component';
import { EditAccountComponent } from './components/client/client-details/client-details-accounts-tab/edit-account/edit-account.component';
import { ClientDetailsDirectorsTabComponent } from './components/client/client-details/client-details-directors-tab/client-details-directors-tab.component';
import { AddDirectorComponent } from './components/client/client-details/client-details-directors-tab/add-director/add-director.component';
import { EditDirectorComponent } from './components/client/client-details/client-details-directors-tab/edit-director/edit-director.component';
import { ClientDetailsAgreementsTabComponent } from './components/client/client-details/client-details-agreements-tab/client-details-agreements-tab.component';

import { AgreementsComponent } from './components/agreement/agreements/agreements.component';
import { AddAgreementComponent } from './components/agreement/add-agreement/add-agreement.component';
import { EditAgreementComponent } from './components/agreement/edit-agreement/edit-agreement.component';
import { AgreementDetailsComponent } from './components/agreement/agreement-details/agreement-details.component';
import { AgreementDetailsDocumentsTabComponent } from './components/agreement/agreement-details/agreement-details-documents-tab/agreement-details-documents-tab.component';

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
import { PersonsComponent } from "./components/person/persons/persons.component";
import { AddPersonComponent } from "./components/person/add-person/add-person.component";
import { PersonDetailsComponent } from "./components/person/person-details/person-details.component";
import { MainPageComponent } from "./components/main-page/main-page.component";
import { MailOutputsComponent } from "./components/mail-output/mail-outputs/mail-outputs.component";
import { AgreementDetailsMainTabComponent } from "./components/agreement/agreement-details/agreement-details-main-tab/agreement-details-main-tab.component";
import { MailInputsComponent } from "./components/mail-input/mail-inputs/mail-inputs.component";
import { DocumentTypesComponent } from "./components/document-type/document-types/document-types.component";
import { EmployeeDetailsVacationsTabComponent } from "./components/employee/employee-details/employee-details-vacations-tab/employee-details-vacations-tab.component";
import { EmployeeDetailsSicksTabComponent } from "./components/employee/employee-details/employee-details-sicks-tab/employee-details-sicks-tab.component";

const appRoutes: Routes = [
  
  {path: '', component:DashboardComponent, 
    children: [
      {path: '', component:MainPageComponent},
      {path: 'clients', component:ClientsComponent},
      {path: 'clients/add', component:AddClientComponent},
      {path: 'clients/:id', component:ClientDetailsComponent,
        children: [
          {path:  '', redirectTo: 'main', pathMatch: 'full'},
          {path: 'main', component: ClientDetailsMainTabComponent},
          {path: 'main/edit', component:EditClientComponent},
          {path: 'addresses', component: ClientDetailsAddressesTabComponent},
          {path: 'addresses/add', component:AddAddressComponent},
          {path: 'addresses/:id', component:EditAddressComponent,
            children: [
              {path:  '', redirectTo: 'edit', pathMatch: 'full'},
              {path: 'edit', component:EditAddressComponent},
            ]
          },
          {path: 'accounts', component: ClientDetailsAccountsTabComponent},
          {path: 'accounts/add', component: AddAccountComponent},
          {path: 'accounts/:id', component: EditAccountComponent,
          children: [
              {path:  '', redirectTo: 'edit', pathMatch: 'full'},
              {path: 'edit', component:EditAccountComponent},
            ]
          },
          {path: 'directors', component: ClientDetailsDirectorsTabComponent},
          {path: 'directors/add', component: AddDirectorComponent},
          {path: 'directors/:id', component: EditDirectorComponent,
            children: [
              {path:  '', redirectTo: 'edit', pathMatch: 'full'},
              {path: 'edit', component:EditDirectorComponent},
            ]
          },
          {path: 'agreements', component: ClientDetailsAgreementsTabComponent},
          {path: 'agreements/add', component: AddAgreementComponent},
          {path: 'agreements/:id', component: AgreementDetailsComponent},
        ]
      },
      {path: 'employees', component:EmployeesComponent},
      {path: 'employees/add', component: AddEmployeeComponent},
      {path: 'employees/:id', component: EmployeeDetailsComponent, 
        children: [
          {path:  '', redirectTo: 'main', pathMatch: 'full'},
          {path: 'main', component: EmployeeDetailsMainTabComponent},
          {path: 'main/edit', component: EditEmployeeComponent},
          {path: 'addresses', component: EmployeeDetailsAddressesTabComponent},
          {path: 'accounts', component: EmployeeDetailsAccountsTabComponent},
          {path: 'vacations', component:EmployeeDetailsVacationsTabComponent},
          {path: 'sicks', component:EmployeeDetailsSicksTabComponent},
        ]
      },
      {path: 'documents', component:DocumentsComponent},
      {path: 'vacations', component:VacationsComponent},
      {path: 'agreements', component:AgreementsComponent},
      {path: 'agreements/add', component: AddAgreementComponent},
      {path: 'agreements/:id', component: AgreementDetailsComponent,
        children: [
          {path:  '', redirectTo: 'main', pathMatch: 'full'},
          {path: 'main', component: AgreementDetailsMainTabComponent},
          {path: 'main/edit', component: EditAgreementComponent},
          {path: 'documents', component: AgreementDetailsDocumentsTabComponent}
        ]
      },
      {path: 'persons', component:PersonsComponent},
      {path: 'persons/add', component:AddPersonComponent},
      {path: 'persons/:id', component: PersonDetailsComponent},
      {path: 'about', component:AboutComponent},
      {path: 'mail-outputs', component:MailOutputsComponent},
      {path: 'mail-inputs', component:MailInputsComponent},
      {path: 'document-types', component:DocumentTypesComponent},
    ]
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}