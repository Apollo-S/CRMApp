import { Component, OnInit } from '@angular/core';
import { Client } from '../../../models/Client';
import { ClientService } from '../../../services/client.service';
import { Router } from '@angular/router';
import { FlashMessagesService } from 'angular2-flash-messages';
// import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {
  // form = new FormGroup({
  //   title: new FormControl(),
  //   alias: new FormControl(),
  //   edrpou: new FormControl(),
  //   inn: new FormControl(),
  //   vatCertificate: new FormControl()
  // });

  client: Client = {
    id: 0,
    title: '',
    alias: '',
    edrpou: '',
    inn: '',
    vatCertificate: ''
  };

  constructor(
    private clientService: ClientService, 
    private flashMessagesService: FlashMessagesService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  private save(): void {
    this.clientService.addClient(this.client);    
  }

  onSubmit() {
    this.save();
    this.flashMessagesService.show('Новый клиент успешно сохранен', {cssClass: 'alert-success', timeout: 3000});
    this.router.navigateByUrl('/clients');
    // location.reload(true);
  }

}
