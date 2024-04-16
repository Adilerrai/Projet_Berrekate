import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ApidbService } from '../../../services/apidb.service';
import { Router } from '@angular/router';

import { AuthModule } from '../auth.module';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent   {
  loginForm: FormGroup;
  constructor
  (
    public router: Router,
    public apidb: ApidbService,
    public fb: FormBuilder,

  
) {
  this.loginForm = this.fb.group({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });
}
ngOnInit(): void {

}
login(): void {
    var username = this.loginForm.get('username')?.value;
    var password = this.loginForm.get('password')?.value;
    this.apidb.login(username, password).subscribe(
        (data: any) => {
            localStorage.setItem('token', data?.access);
            localStorage.setItem('Username', username);
            this.apidb.getList('client/', {}).subscribe(
                (res) => {
                    if (data.nbconnect <= 0) {
                        this.router.navigate(['/changerpassword']);
                        Swal.fire('Alerte ...', 'Vous devez changer le mot de passe', 'info');
                    } else {
                        Swal.fire('Bienvenue chez CRM CLIENT', '', 'success');
                        // ------------
                        this.apidb.getList('client/', {}).subscribe(
                            (dataemploye) => {
                                localStorage.setItem('currentEmploye', JSON.stringify(dataemploye));
                                this.router.navigate(['']);
              
                            },
                            err => {
                                Swal.fire('Erreur de connexion ...', 'Vérifier vos coordonées de connexion', 'error');
                            }
                        );
                        // ------------
                        this.router.navigate(['']);
                    }
                },
                err => {
                    if (err.error && err.error.code === 'token_not_valid') {
                        // Clear invalid token
                        localStorage.removeItem('token');
                        // Redirect to login page
                        this.router.navigate(['/login']);
                    } else {
                        Swal.fire('Erreur...', err.error.msg, 'error');
                    }
                }
            );
        },
        err => {
            Swal.fire('Erreur de connexion ...', 'Erreur de connexion', 'error');
        }
    );
}
}
