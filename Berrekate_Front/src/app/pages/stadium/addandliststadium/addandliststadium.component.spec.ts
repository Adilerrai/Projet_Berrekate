import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddandliststadiumComponent } from './addandliststadium.component';

describe('AddandliststadiumComponent', () => {
  let component: AddandliststadiumComponent;
  let fixture: ComponentFixture<AddandliststadiumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddandliststadiumComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddandliststadiumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
