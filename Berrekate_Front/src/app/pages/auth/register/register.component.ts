import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApidbService } from 'src/app/services/apidb.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerform: FormGroup;
  loginError: String = "";
  constructor(
    private router: Router,
    private apidb: ApidbService
  ) { 
    this.registerform = new FormGroup({
      username: new FormControl('',[Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      password2: new FormControl('', [Validators.required],),
   
    });
  }

  ngOnInit(): void {
  }
  registerUser(): void {
    // Getting form values
    var username = this.registerform.get('username')?.value;
    var email = this.registerform.get('email')?.value;
    var password = this.registerform.get('password')?.value;
    var password2 = this.registerform.get('password2')?.value;

    // Checking if email already exists
    // this.apidb.getList('client/', {}).subscribe((dataCL: any) => {
        // Assuming dataCL is an array, checking its length
        // if (dataCL.length == 0) {
            // Adding registration data if email doesn't exist
            this.apidb.register(username,email,password,password2).subscribe(
                // Handling successful registration
                data => {
                    // Displaying success message
                    Swal.fire({
                        title: '',
                        html: '',
                        icon: 'success',
                        timer: 1000,
                        didOpen: () => {
                            Swal.showLoading();
                        }
                    });

               

                    // Navigating to home page after a delay
                    setTimeout(() => {
                        this.router.navigate(['/']);
                    }, 500);
                },
                // Handling registration error
                err => {
                    this.loginError = err.error.msg;
                    Swal.fire('Erreur...', err.error.msg, 'error');
                }
            );
        // } else {
        //     // Displaying error if email already exists
        //     Swal.fire('Erreur...', 'Email existe déjà', 'error');
        // }
    // });
}
}