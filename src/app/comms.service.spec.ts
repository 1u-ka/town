import { TestBed } from '@angular/core/testing';

import { CommsService } from './fetcher.service';

describe('CommsService', () => {
  let service: FetcherService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
