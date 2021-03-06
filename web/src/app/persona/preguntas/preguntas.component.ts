import { Component, Input, OnInit } from '@angular/core';
import { QuestionI } from 'src/app/models/question-i';
import { QuestionService } from 'src/app/Service/question.service';
import { ServiceService } from 'src/app/Service/service.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-preguntas',
  templateUrl: './preguntas.component.html',
  styleUrls: ['./preguntas.component.css'],
})
export class PreguntasComponent implements OnInit {
  userLogged = this.authService.getUserLogged();
  uid: any;
userData: any
email: any
totalQuestions: number = 0
  questions: any | undefined;

  user: any = '';
  page: number = 0;
  pages: Array<number> | undefined;
  disabled: boolean = false;
  dataUser: any;



  constructor(
    private service: QuestionService,
    public authService: ServiceService,
    private modalService: NgbModal,
    private afAuth: AngularFireAuth,
     private router: Router
  ) {}

  ngOnInit(): void {
    this.getQuestions();
    this.traerdatos();
    this.getQuestionsAll();


  }
  prueba: any;





  getQuestionsAll(): void {
    this.userData =JSON.parse(localStorage.getItem('user')!);
    this.email = this.userData?.email
    console.log(this.userData?.stsTokenManager?.accessToken)
    this.service.getQuestionAll().subscribe(value =>{


      this.prueba = value

      this.questions = value
      this.totalQuestions = this.questions.length
        this.pages = new Array(Math.ceil(this.totalQuestions / 10))

  });
  }

  getQuestions(): void {
    this.userLogged.subscribe(value =>{
        this.uid=value?.uid

    });
    this.service.getPage(this.page).subscribe((data) => {
        this.questions = data;


    });
    this.service
      .getTotalPages()
      .subscribe((data) => {
        this.pages = new Array(data)

      });
    this.service
      .getCountQuestions()
      .subscribe((data) => (this.totalQuestions = data));
  }

  isLast(): boolean {
    let totalPeges: any = this.pages?.length;
    return this.page == totalPeges - 1;
  }

  isFirst(): boolean {
    return this.page == 0;
  }

  previousPage(): void {
    !this.isFirst() ? (this.page--, this.getQuestions()) : false;
  }

  nextPage(): void {
    !this.isLast() ? (this.page++, this.getQuestions()) : false;
  }

  getPage(page: number): void {
    this.page = page;
    this.getQuestions();
  }

  traerdatos() {
    this.userLogged.subscribe((value) => {

      if (value?.email ) {
        console.log("si existe")
        this.disabled = true;
      } else {
        this.disabled = false;
      }
    });
  }
}
