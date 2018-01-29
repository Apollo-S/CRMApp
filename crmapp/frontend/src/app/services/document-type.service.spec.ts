import { TestBed, inject } from '@angular/core/testing';

import { DocumentTypeService } from './document-type.service';

describe('DocumentTypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DocumentTypeService]
    });
  });

  it('should be created', inject([DocumentTypeService], (service: DocumentTypeService) => {
    expect(service).toBeTruthy();
  }));
});
