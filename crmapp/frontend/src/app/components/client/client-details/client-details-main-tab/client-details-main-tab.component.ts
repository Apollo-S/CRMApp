import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Client } from '../../../../models/Client';
import { ClientService } from '../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Message } from 'primeng/components/common/api';
import { MessageService } from 'primeng/components/common/messageservice';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-client-details-main-tab',
  templateUrl: './client-details-main-tab.component.html',
  styleUrls: ['./client-details-main-tab.component.css']
})
export class ClientDetailsMainTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  client: Client = {};
  clientId: number;

  constructor(private service: ClientService,
              private router: Router,
              private route: ActivatedRoute,
              private messageService: MessageService,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
    .subscribe(p => {
      this.clientId = p;
    });
    this.getClientById(this.clientId);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  getClientById(clientId: number) {
    this.service.getClientById(clientId)
      .subscribe(client => this.client = client);
  }

  delete(): void {
      this.service.deleteClient(this.clientId)
        .subscribe(() => 
          this.goBackToClients()
      );
  }

  private goBackToClients(): void {
      this.router.navigate(['/clients']);
  }

  confirmDeleting() {
    let msg  = 'Клиент с ID=' + this.clientId + ' успешно удален';
    this.confirmationService.confirm({
        message: 'Действительно удалить клиента?',
        header: 'Удаление объекта',
        icon: 'fa fa-trash',
        accept: () => {
          this.delete();
          this.msgs = [{severity:'success', summary:'Confirmed', detail: msg}];
        },
        reject: () => {}
    });
}

}
