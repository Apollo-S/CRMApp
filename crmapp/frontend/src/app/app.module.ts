import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// PrimeNG
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { ButtonModule } from 'primeng/button'; 
import { CardModule } from 'primeng/components/card/card';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DataListModule } from 'primeng/datalist';
import { DataTableModule } from 'primeng/datatable'; //deprecated
import { DialogModule } from 'primeng/dialog';
import { GrowlModule } from 'primeng/growl';
import { InputTextModule } from 'primeng/inputtext';
import { MenubarModule } from 'primeng/menubar';
import { PanelMenuModule } from 'primeng/panelmenu';
import { PanelModule } from 'primeng/panel';
import { PaginatorModule } from 'primeng/paginator';
import { SplitButtonModule } from 'primeng/splitbutton';
import { TableModule } from 'primeng/table';
import { TabMenuModule } from 'primeng/tabmenu';
import { TabViewModule } from 'primeng/tabview';
import { TreeModule }  from 'primeng/tree';

// Custom Service Imports
import { CategoryService } from './services/category.service';
import { ClientService } from './services/client.service';
import { EmployeeService } from './services/employee.service';
import { AgreementService } from './services/agreement.service';
import { VacationService } from './services/vacation.service';
import { PostService } from './services/post.service';
import { DocumentService } from './services/document.service';
import { DocumentTypeService } from './services/document-type.service';
import { DocumentStatusService } from './services/document-status.service';
import { MessageService } from 'primeng/components/common/messageservice';
import { ConfirmationService } from 'primeng/api';

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
      {path: 'addresses', component: ClientDetailsAddressesTabComponent},
      {path: 'addresses/add', component:AddAddressComponent},
      {path: 'addresses/:id', component:EditAddressComponent,
        children: 
        [
          {path:  '', redirectTo: 'edit', pathMatch: 'full'},
          {path: 'edit', component:EditAddressComponent},
        ]
      },
      {path: 'accounts', component: ClientDetailsAccountsTabComponent},
      {path: 'accounts/add', component: AddAccountComponent},
      {path: 'accounts/:id', component: EditAccountComponent,
      children: 
        [
          {path:  '', redirectTo: 'edit', pathMatch: 'full'},
          {path: 'edit', component:EditAccountComponent},
        ]
      },
      {path: 'directors', component: ClientDetailsDirectorsTabComponent},
      {path: 'directors/add', component: AddDirectorComponent},
      {path: 'directors/:id', component: EditDirectorComponent,
        children: 
        [
          {path:  '', redirectTo: 'edit', pathMatch: 'full'},
          {path: 'edit', component:EditDirectorComponent},
        ]
      },
      {path: 'agreements', component: ClientDetailsAgreementsTabComponent},
      {path: 'agreements/add', component: AddAgreementComponent},
      {path: 'agreements/:id', component: AgreementDetailsComponent},
    ]
  },

  {path: 'agreements/add', component: AddAgreementComponent},
  {path: 'agreements/:id', component: AgreementDetailsComponent,
    children: 
    [
      {path:  '', redirectTo: 'main', pathMatch: 'full'},
      {path: 'main', component: EditAgreementComponent},
      {path: 'edit', component: EditAgreementComponent},
      {path: 'documents', component: AgreementDetailsDocumentsTabComponent}
    ]
  },
  {path: 'agreements/:id/edit', component: EditAgreementComponent},

  {path: 'employees/add', component: AddEmployeeComponent},
  {path: 'employees/:id/edit', component: EditEmployeeComponent},
  {path: 'employees/:id', component: EmployeeDetailsComponent, 
    children: 
    [
      {path:  '', redirectTo: 'main', pathMatch: 'full'},
      {path: 'main', component: EmployeeDetailsMainTabComponent},
      {path: 'addresses', component: EmployeeDetailsAddressesTabComponent},
      {path: 'accounts', component: EmployeeDetailsAccountsTabComponent}
    ]
  },

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
    ClientDetailsTabsComponent,
    AddAddressComponent,
    EditAddressComponent,
    AddAccountComponent,
    EditAccountComponent,
    AddDirectorComponent,
    EditDirectorComponent,
    AgreementDetailsDocumentsTabComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),

    ButtonModule, 
    BreadcrumbModule,
    CardModule,
    ConfirmDialogModule,
    DataListModule,
    DataTableModule, 
    DialogModule,
    GrowlModule,
    InputTextModule, 
    MenubarModule,
    PaginatorModule,
    PanelMenuModule,
    PanelModule,
    SplitButtonModule,
    TableModule,
    TabMenuModule,
    TabViewModule,
    TreeModule,
  ],
  providers: [
    CategoryService,
    ClientService,
    EmployeeService,
    AgreementService,
    VacationService,
    PostService,
    DocumentService,
    DocumentTypeService,
    DocumentStatusService,
    MessageService, 
    ConfirmationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
