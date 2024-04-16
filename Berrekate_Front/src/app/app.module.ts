import { CUSTOM_ELEMENTS_SCHEMA, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';

import { FooterComponent } from './pages/footer/footer.component';
import { AcceuilComponent } from './pages/acceuil/acceuil.component';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { LazyDirectiveDirective } from './lazy-directive.directive';
import { AppRoutingModule } from './app-routing.module';
import { AuthModule } from './pages/auth/auth.module';
import { TokenInterceptor } from './services/token.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';
import { ApidbService } from './services/apidb.service';
import { AddrestaurantComponent } from './pages/restaurant/addrestaurant/addrestaurant.component';
import { ListrestaurantComponent } from './pages/restaurant/listrestaurant/listrestaurant.component';
import { UpdaterestaurantComponent } from './pages/restaurant/updaterestaurant/updaterestaurant.component';
import { MatTableModule } from '@angular/material/table';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddandliststadiumComponent } from './pages/stadium/addandliststadium/addandliststadium.component';
import { UpdatestadiumComponent } from './pages/stadium/updatestadium/updatestadium.component';
import { AddandlistevenementComponent } from './pages/evenement/addandlistevenement/addandlistevenement.component';
import { UpdateevenementComponent } from './pages/evenement/updateevenement/updateevenement.component';
import { AddandlistmatchComponent } from './pages/match/addandlistmatch/addandlistmatch.component';
import { UpdatematchComponent } from './pages/match/updatematch/updatematch.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    AcceuilComponent,
    LazyDirectiveDirective,
    AddrestaurantComponent,
    ListrestaurantComponent,
    UpdaterestaurantComponent,
    AddandliststadiumComponent,
    UpdatestadiumComponent,
    AddandlistevenementComponent,
    UpdateevenementComponent,
    AddandlistmatchComponent,
    UpdatematchComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    AuthModule,
    BrowserAnimationsModule,
    MatTableModule
  ],
  providers: [
    ApidbService,
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    { provide: LOCALE_ID, useValue: 'fr' }
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],

  bootstrap: [AppComponent]
})
export class AppModule { }
