import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/Client';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {
  msgs: Message[] = [];
  userform: FormGroup;
  client: Client = {};

  constructor(private service: ClientService, 
              private fb: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.userform = this.fb.group(
      {
        'title': new FormControl('', Validators.compose(
          [
            Validators.required, 
            Validators.minLength(2)
          ])),
        'alias': new FormControl('', Validators.compose(
          [
            Validators.required, 
            Validators.minLength(2)
          ])),
        'edrpou': new FormControl('', Validators.compose(
          [
            Validators.required, 
            Validators.minLength(6),
            Validators.maxLength(14)
          ])),
        'inn': new FormControl('', Validators.compose(
          [
            Validators.maxLength(14)
          ])),
        'vatCertificate': new FormControl('')
      });
  }

  onSubmit() {
    this.save();
    // this.userform.controls['edrpou'].valid
  }

  private save(): void {
    this.service.addClient(this.client)
      .subscribe(
        response => {
          this.router.navigate([response.url]);
        }
      );
  }

}
