import { TestBed, inject } from '@angular/core/testing';

import { MailOutputService } from './mail-output.service';

describe('MailOutputService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MailOutputService]
    });
  });

  it('should be created', inject([MailOutputService], (service: MailOutputService) => {
    expect(service).toBeTruthy();
  }));
});
