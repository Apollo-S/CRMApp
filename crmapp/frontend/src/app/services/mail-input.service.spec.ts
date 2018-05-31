import { TestBed, inject } from '@angular/core/testing';

import { MailInputService } from './mail-input.service';

describe('MailInputService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MailInputService]
    });
  });

  it('should be created', inject([MailInputService], (service: MailInputService) => {
    expect(service).toBeTruthy();
  }));
});
