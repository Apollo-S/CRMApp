import { TestBed, inject } from '@angular/core/testing';

import { AgreementService } from './agreement.service';

describe('AgreementService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AgreementService]
    });
  });

  it('should be created', inject([AgreementService], (service: AgreementService) => {
    expect(service).toBeTruthy();
  }));
});
