import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientAccount } from '../../../../../models/ClientAccount';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../../services/client.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  account: ClientAccount = {};
  clientId: number;  

  constructor(private service: ClientService, 
    private flashMessagesService: FlashMessagesService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
    .subscribe(p => {
      this.clientId = p;
      }
    );
  }

  onSubmit() {
    this.save();
    this.showMessage();
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  private save(): void {
    this.service.addAccount(this.account, this.clientId)
      .subscribe(response => {
          this.router.navigate(['/clients', this.clientId, 'accounts']);
        }
      );
  }

  private showMessage(): void {
    this.flashMessagesService.show('Новый банк. счет успешно сохранен', 
      {cssClass: 'alert-success', timeout: 1000});
  }

}