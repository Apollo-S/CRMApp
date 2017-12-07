import { TestBed, inject } from '@angular/core/testing';

import { ClientAddressService } from './client-address.service';

describe('ClientAddressService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ClientAddressService]
    });
  });

  it('should be created', inject([ClientAddressService], (service: ClientAddressService) => {
    expect(service).toBeTruthy();
  }));
});
