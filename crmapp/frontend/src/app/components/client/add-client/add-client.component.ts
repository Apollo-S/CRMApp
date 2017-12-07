import { Component, OnInit } from '@angular/core';
import { Client } from '../../../models/Client';
import { ClientService } from '../../../services/client.service';
import { Router } from '@angular/router';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {

  client: Client = {};

  constructor(private service: ClientService, 
              private flashMessagesService: FlashMessagesService,
              private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    this.save();
    this.showMessage();
  }

  private save(): void {
    this.service.addClient(this.client)
      .subscribe(response => {
          this.router.navigate(['/clients', response.id]);
      });
  }

  private showMessage(): void {
    this.flashMessagesService.show('Новый клиент успешно сохранен', 
      {cssClass: 'alert-success', timeout: 1500});
  }

}
