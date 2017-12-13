import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientDirector } from '../../../../../models/ClientDirector';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../../services/client.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-director',
  templateUrl: './add-director.component.html',
  styleUrls: ['./add-director.component.css']
})
export class AddDirectorComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  director: ClientDirector = {};
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

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  onSubmit() {
    this.save();
    this.showMessage();
  }

  private save(): void {
    // this.service.addDirector(this.director, this.clientId)
    //   .subscribe(response => {
    //       this.router.navigate(['/clients', this.clientId, 'directors']);
    //     }
    //   );
  }

  private showMessage(): void {
    this.flashMessagesService.grayOut(true);
    this.flashMessagesService.show('Новый директор успешно сохранен', 
      {cssClass: 'alert-success', timeout: 1500});
  }

}
