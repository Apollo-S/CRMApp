import { TestBed, inject } from '@angular/core/testing';

import { DocumentStatusService } from './document-status.service';

describe('StatusService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DocumentStatusService]
    });
  });

  it('should be created', inject([DocumentStatusService], (service: DocumentStatusService) => {
    expect(service).toBeTruthy();
  }));
});
