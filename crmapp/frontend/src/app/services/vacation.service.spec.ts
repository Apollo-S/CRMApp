import { TestBed, inject } from '@angular/core/testing';

import { VacationService } from './vacation.service';

describe('VacationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VacationService]
    });
  });

  it('should be created', inject([VacationService], (service: VacationService) => {
    expect(service).toBeTruthy();
  }));
});
