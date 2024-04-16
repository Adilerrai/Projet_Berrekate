import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatestadiumComponent } from './updatestadium.component';

describe('UpdatestadiumComponent', () => {
  let component: UpdatestadiumComponent;
  let fixture: ComponentFixture<UpdatestadiumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatestadiumComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatestadiumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
