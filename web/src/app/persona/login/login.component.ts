import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService, Message } from 'primeng/api';
import { ServiceService } from 'src/app/Service/service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [MessageService],
})
export class LoginComponent implements OnInit {
  mostrar: Boolean = false;
  mostrar2: Boolean = false;
  val1: number = 3;
  displayModal: boolean = false;
  email: any = '';

  public form: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(10)]],
    rating: ['', []],
  });
  public form2: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private authService: ServiceService,
    private route: Router,
    private afAuth: AngularFireAuth
  ) {}

  ngOnInit(): void {}

  ingresar() {
    const email = this.form.value.email;
    const password = this.form.value.password;

    this.mostrar = !this.mostrar;
    this.afAuth.signInWithEmailAndPassword(email, password).then((user) => {
      if (user.user?.emailVerified) {
        this.messageService.add({
          severity: 'success',
          summary: 'Bienvenido',
          detail: 'Disfruta de tu estadía',
        });

        /* probando */
        this.authService.almacenarLocalStorage();

        /* aca termina */
        this.route.navigate(['preguntas']);
      } else {
        this.messageService.add({
          severity: 'error',
          summary: 'Rectifique los datos',
          detail: 'Clave o Usuario incorrecto, Intente de Nuevo',
        });
      }

      this.mostrar = !this.mostrar;
    });
  }
  ingresarGoogle() {
    this.mostrar = !this.mostrar;
    this.authService
      .loginGoogle(this.form.value.email, this.form.value.password)
      .then((res) => {
        if (res) {
          this.messageService.add({
            severity: 'success',
            summary: 'Bienvenido',
            detail: 'Disfruta de tu estadía',
          });
          setTimeout(() => {
            this.route.navigate(['preguntas']);
          }, 3000);
        } else {
          this.messageService.add({
            severity: 'error',
            summary: 'Rectifique los datos',
            detail: 'Clave o Usuario incorrecto, Intente de Nuevo',
          });
        }
        this.mostrar = !this.mostrar;
      });
  }
  getUserLogged() {
    this.authService.getUserLogged().subscribe((res) => {});
  }

  preguntasHome() {
    this.route.navigate(['preguntas']);
  }

  //TODO: Utilidades
  showSuccess() {
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Message Content',
    });
  }
  showInfo() {
    this.messageService.add({
      severity: 'info',
      summary: 'Info',
      detail: 'Message Content',
    });
  }
  showError() {}
  spinner() {
    this.mostrar = !this.mostrar;
  }

  showModalDialog() {
    this.displayModal = true;
  }

  recuperarEmail() {
    try {
      this.mostrar2 = !this.mostrar2;
      this.authService.resetPassword(this.form2.value.email).then((res) => {
        this.displayModal = false;
        this.messageService.add({
          severity: 'success',
          summary: '!Exitoso¡',
          detail: 'Revisa tu bandeja de entrada',
        });
      });
      this.mostrar2 = !this.mostrar2;
    } catch (error) {}
  }
}
