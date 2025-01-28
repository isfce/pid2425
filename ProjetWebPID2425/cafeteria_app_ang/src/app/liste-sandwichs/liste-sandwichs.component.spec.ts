import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeSandwichsComponent } from './liste-sandwichs.component';

describe('ListeSandwichsComponent', () => {
  let component: ListeSandwichsComponent;
  let fixture: ComponentFixture<ListeSandwichsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListeSandwichsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListeSandwichsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
