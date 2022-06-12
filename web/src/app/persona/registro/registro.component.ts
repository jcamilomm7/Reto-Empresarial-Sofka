import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService, Message } from 'primeng/api';
import { ServiceService } from 'src/app/Service/service.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss'],
  providers: [MessageService],
})
export class RegistroComponent implements OnInit {
  mostrar: Boolean = false;
  val1: number = 3;

  public form: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
    rating: ['', []],
  });

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private authService: ServiceService,
    private route: Router,
    private afAuth: AngularFireAuth,
  ) {}

  ngOnInit(): void {}

  async ingresar() {
    const email = this.form.value.email;
    const password = this.form.value.password

    this.mostrar = !this.mostrar;
    await this.afAuth
      .createUserWithEmailAndPassword(email, password)
      .then((res) => {
        this.verificarCorreo();
        this.mostrar = !this.mostrar;
      });
  }

  async verificarCorreo() {
    await this.afAuth.currentUser
      .then((user) => user?.sendEmailVerification())
      .then(() => {
        this.messageService.add({
          severity: 'success',
          summary: '!Exitoso¡',
          detail: 'Le enviamos un correo electronico para su verificacion',
        });
        setTimeout(() => {
          this.route.navigate(['/login']);
        }, 2000);
      });
  }
  async ingresarGoogle() {
    this.mostrar = !this.mostrar;
    await this.authService
      .loginGoogle(this.form.value.email, this.form.value.password)
      .then((res) => {
        this.mostrar = !this.mostrar;
      });
  }
 getUserLogged() {
    this.authService.getUserLogged().subscribe((res) => {
    });
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

  spinner() {
    this.mostrar = !this.mostrar;
  }
}
