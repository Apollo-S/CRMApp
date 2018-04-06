import { TestBed, inject } from '@angular/core/testing';

import { MailDocumentTypeService } from './mail-document-type.service';

describe('MailDocumentTypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MailDocumentTypeService]
    });
  });

  it('should be created', inject([MailDocumentTypeService], (service: MailDocumentTypeService) => {
    expect(service).toBeTruthy();
  }));
});
