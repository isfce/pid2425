import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SandwichsComponent } from './sandwichs.component';

describe('SandwichsComponent', () => {
  let component: SandwichsComponent;
  let fixture: ComponentFixture<SandwichsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SandwichsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SandwichsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
