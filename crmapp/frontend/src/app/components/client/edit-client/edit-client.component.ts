import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/Client';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  userform: FormGroup;
  submitted: boolean;

  client: Client = {};

  constructor(private fb: FormBuilder,
              private clientService: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.clientService.property$
      .subscribe(
        p => this.client = p
      );
    // this.userform = this.fb.group(
    //   {
    //     'title': new FormControl('', Validators.required),
    //     'alias': new FormControl('', Validators.required),
    //   });
  }
  
  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }
  
  onSubmit() {
    this.update();
    this.goBackToClient(1500);
  }

  private update(): void {
    this.clientService.updateClient(this.client)
      .subscribe(
        response => {
          this.client = response;
          let msg = 'Клиент ' + this.client.alias +  ' успешно обновлен (ID=' + response.id + ')';
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }

  private goBackToClient(timeMillis: number) {
    setTimeout(
      (router) => {
        this.router.navigate([this.client.url]);
      }, timeMillis);
  } 

}
