import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientAgreement } from '../../../models/ClientAgreement';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Message } from 'primeng/api';
import { Subscription } from 'rxjs';
import { AgreementService } from '../../../services/agreement.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-agreement',
  templateUrl: './edit-agreement.component.html',
  styleUrls: ['./edit-agreement.component.css']
})
export class EditAgreementComponent implements OnInit , OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  userform: FormGroup;
  agreement: ClientAgreement = {};

  constructor(private fb: FormBuilder,
              private service: AgreementService,
              private router: Router) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => this.agreement = p
      );
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
          'inn': new FormControl(''),
          'vatCertificate': new FormControl('')
        });
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

}
