import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlaceComponent } from './place.component';

describe('PlaceComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlaceComponent ]
    })
  }));

  it('should create', () => {
    let fixture = TestBed.createComponent(PlaceComponent);
    let app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should create', () => {
    let fixture = TestBed.createComponent(PlaceComponent);
    let app = fixture.debugElement.componentInstance;
    let placeService = fixture.debugElement.injector.get(placeService);
    expect(app.places).toEqual(placeService.places);
  });
});
