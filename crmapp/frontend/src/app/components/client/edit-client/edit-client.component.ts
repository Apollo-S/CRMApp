import { Component, OnInit } from '@angular/core';
import { Client } from '../../../models/Client';
import { ClientService } from '../../../services/client.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit {
  client: Client = {};
  id: number;

  constructor(private clientService: ClientService,
              private router: Router,
              private route: ActivatedRoute,
              private flashMessagesService: FlashMessagesService
  ) { }

  ngOnInit() {
    this.id = parseInt(this.route.snapshot.params['id']);
    this.clientService.getClientById(this.id)
      .then(client => this.client = client);
  }

  onSubmit() {
    this.update();
    this.flashMessagesService.show('Изменения сохранены', {cssClass: 'alert-success', timeout: 1500});
    location.reload();
    }

    private update(): void {
      this.clientService.updateClient(this.client);
    }

}
