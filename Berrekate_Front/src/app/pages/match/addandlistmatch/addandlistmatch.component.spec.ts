import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddandlistmatchComponent } from './addandlistmatch.component';

describe('AddandlistmatchComponent', () => {
  let component: AddandlistmatchComponent;
  let fixture: ComponentFixture<AddandlistmatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddandlistmatchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddandlistmatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
