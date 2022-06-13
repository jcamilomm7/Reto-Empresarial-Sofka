import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnswerComponent } from './paginas/answer/answer.component';
import { QuestionComponent } from './paginas/question/question.component';
import { RequestionComponent } from './paginas/requestion/requestion.component';
import { LoginComponent } from './persona/login/login.component';
import { PreguntasComponent } from './persona/preguntas/preguntas.component';
import { RecuperarPasswordComponent } from './persona/recuperar-password/recuperar-password.component';
import { RegistroComponent } from './persona/registro/registro.component';
import { VerificarCorreoComponent } from './persona/verificar-correo/verificar-correo.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'preguntas', component: PreguntasComponent},
  { path: 'registro', component: RegistroComponent},
  { path: 'answer', component: AnswerComponent},
  { path: 'app-recuperar-password', component: RecuperarPasswordComponent},
  { path: 'app-verificar-correo', component: VerificarCorreoComponent},
  {path: 'question/:id', component: RequestionComponent},
  {path: '**', pathMatch: 'full', redirectTo:'preguntas'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }



