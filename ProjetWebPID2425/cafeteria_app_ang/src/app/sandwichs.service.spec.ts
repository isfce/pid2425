import { TestBed } from '@angular/core/testing';

import { SandwichsService } from './sandwichs.service';

describe('SandwichsService', () => {
  let service: SandwichsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SandwichsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
