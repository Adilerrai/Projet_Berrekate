import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddandlistevenementComponent } from './addandlistevenement.component';

describe('AddandlistevenementComponent', () => {
  let component: AddandlistevenementComponent;
  let fixture: ComponentFixture<AddandlistevenementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddandlistevenementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddandlistevenementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
