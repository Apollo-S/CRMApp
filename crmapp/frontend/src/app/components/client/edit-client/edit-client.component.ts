import { Component, OnInit, OnDestroy } from '@angular/core';
import { Client } from '../../../models/Client';
import { ClientService } from '../../../services/client.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;

  userform: FormGroup;
  submitted: boolean;

  client: Client = {};
  clientId: number;

  constructor(private fb: FormBuilder,
              private clientService: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.clientService.property$
      .subscribe(p => {
        this.clientId = p;
      });
    this.clientService.getClientById(this.clientId)
      .subscribe(client => this.client = client);
    // this.userform = this.fb.group(
    //   {
    //     'title': new FormControl('', Validators.required),
    //     'alias': new FormControl('', Validators.required),

    //   });
  }

  onSubmit() {
    this.update();
    this.ngOnInit();
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private update(): void {
    this.clientService.updateClient(this.client)
      .subscribe(response => this.client = response)
  }

}
