import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';

// PrimeNG
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { ButtonModule } from 'primeng/button'; 
import { CalendarModule } from 'primeng/calendar';
import { CardModule } from 'primeng/card';
import { CheckboxModule } from 'primeng/checkbox';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DataListModule } from 'primeng/datalist';
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { FieldsetModule } from 'primeng/fieldset';
import { GrowlModule } from 'primeng/growl';
import { InplaceModule } from 'primeng/inplace';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { InputTextModule } from 'primeng/inputtext';
import { InputSwitchModule } from 'primeng/inputswitch';
import { KeyFilterModule } from 'primeng/keyfilter';
import { ListboxModule } from 'primeng/listbox';
import { MenubarModule } from 'primeng/menubar';
import { MultiSelectModule } from 'primeng/multiselect';
import { PanelMenuModule } from 'primeng/panelmenu';
import { PanelModule } from 'primeng/panel';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { SidebarModule } from 'primeng/sidebar';
import { SpinnerModule } from 'primeng/spinner';
import { SplitButtonModule } from 'primeng/splitbutton';
import { TableModule } from 'primeng/table';
import { TabMenuModule } from 'primeng/tabmenu';
import { TabViewModule } from 'primeng/tabview';
import { TieredMenuModule } from 'primeng/tieredmenu';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { ToolbarModule } from 'primeng/toolbar';
import { TreeModule }  from 'primeng/tree';

import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/components/common/messageservice';

// Custom Service Imports
import { AppConst } from './app-const';
import { AgreementService } from './services/agreement.service';
import { CategoryService } from './services/category.service';
import { ClientService } from './services/client.service';
import { DocumentService } from './services/document.service';
import { DocumentStatusService } from './services/document-status.service';
import { DocumentTypeService } from './services/document-type.service';
import { EmployeeService } from './services/employee.service';
import { MailDocumentTypeService } from './services/mail-document-type.service';
import { MailInputService } from './services/mail-input.service';
import { MailOutputService } from './services/mail-output.service';
import { PersonService } from './services/person.service';
import { PostService } from './services/post.service';
import { VacationService } from './services/vacation.service';
import { UtilService } from './services/util.service';

// Component Imports 
import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NavbarBottomComponent } from './components/navbar-bottom/navbar-bottom.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DashboardMenuComponent } from './components/dashboard/dashboard-menu/dashboard-menu.component';
import { DashboardTableComponent } from './components/dashboard/dashboard-table/dashboard-table.component';
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
import { PersonsComponent } from './components/person/persons/persons.component';
import { AddPersonComponent } from './components/person/add-person/add-person.component';
import { EditPersonComponent } from './components/person/edit-person/edit-person.component';
import { PersonDetailsComponent } from './components/person/person-details/person-details.component';
import { LoadingStatusComponent } from './components/dashboard/loading-status/loading-status.component';
import { NavbarMenuBarComponent } from './components/navbar/navbar-menu-bar/navbar-menu-bar.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { MailOutputsComponent } from './components/mail-output/mail-outputs/mail-outputs.component';
import { MailInputsComponent } from './components/mail-input/mail-inputs/mail-inputs.component';
import { AgreementDetailsTabsComponent } from './components/agreement/agreement-details/agreement-details-tabs/agreement-details-tabs.component';
import { AgreementDetailsMainTabComponent } from './components/agreement/agreement-details/agreement-details-main-tab/agreement-details-main-tab.component';
import { DocumentTypesComponent } from './components/document-type/document-types/document-types.component';
import { ClientEmployeeDetailsTemplateTableComponent } from './templates/client-employee-details-template-table/client-employee-details-template-table.component';
import { ControlMessagesComponent } from './templates/control-messages/control-messages.component';
import { EmployeeDetailsVacationsTabComponent } from './components/employee/employee-details/employee-details-vacations-tab/employee-details-vacations-tab.component';
import { EmployeeDetailsSicksTabComponent } from './components/employee/employee-details/employee-details-sicks-tab/employee-details-sicks-tab.component';

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    ClientsComponent,
    AddClientComponent,
    AgreementsComponent,
    NavbarComponent,
    NavbarBottomComponent,
    ClientDetailsComponent,
    EditClientComponent,
    SidebarComponent,
    DashboardComponent,
    DashboardMenuComponent,
    DashboardTableComponent,
    PageNotFoundComponent,
    EmployeesComponent,
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
    AgreementDetailsDocumentsTabComponent,
    PersonsComponent,
    AddPersonComponent,
    EditPersonComponent,
    PersonDetailsComponent,
    LoadingStatusComponent,
    NavbarMenuBarComponent,
    MainPageComponent,
    MailOutputsComponent,
    MailInputsComponent,
    AgreementDetailsTabsComponent,
    AgreementDetailsMainTabComponent,
    DocumentTypesComponent,
    ClientEmployeeDetailsTemplateTableComponent,
    ControlMessagesComponent,
    EmployeeDetailsVacationsTabComponent,
    EmployeeDetailsSicksTabComponent,
  ],
  imports: [
    AppRoutingModule, 
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,

    BreadcrumbModule,
    ButtonModule, 
    CalendarModule,
    CardModule,
    CheckboxModule,
    ConfirmDialogModule,
    DataListModule,
    DataViewModule,
    DialogModule,
    DropdownModule,
    GrowlModule,
    FieldsetModule,
    InplaceModule,
    InputTextareaModule,
    InputTextModule, 
    InputSwitchModule,
    KeyFilterModule,
    ListboxModule,
    MenubarModule,
    MultiSelectModule,
    PaginatorModule,
    PanelMenuModule,
    PanelModule,
    ProgressSpinnerModule,
    SidebarModule,
    SpinnerModule,
    SplitButtonModule,
    TableModule,
    TabMenuModule,
    TabViewModule,
    TieredMenuModule,
    ToggleButtonModule,
    ToolbarModule,
    TreeModule,
  ],
  providers: [
    AppConst,
    AgreementService,
    CategoryService,
    ClientService,
    ConfirmationService,
    DocumentService,
    DocumentStatusService,
    DocumentTypeService,
    EmployeeService,
    MailDocumentTypeService,
    MailInputService,
    MailOutputService,
    MessageService, 
    PersonService,
    PostService,
    VacationService,
    UtilService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
