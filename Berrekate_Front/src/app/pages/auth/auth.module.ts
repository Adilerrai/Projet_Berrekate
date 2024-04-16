import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './auth-routing.module';

import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';

import { LoginComponent } from './login/login.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { BrowserModule } from '@angular/platform-browser';
import { ApidbService } from 'src/app/services/apidb.service';
import { AuthGuard } from 'src/app/services/auth.guard';
import { RegisterComponent } from './register/register.component';




@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    AuthRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserModule,
  ],
  providers: [
    ApidbService,
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    AuthGuard,
  ],
  

})
export class AuthModule { }
