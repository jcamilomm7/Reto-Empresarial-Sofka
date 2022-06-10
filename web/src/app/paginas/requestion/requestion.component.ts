import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AnswerI } from 'src/app/models/answer-i';
import { QuestionI } from 'src/app/models/question-i';
import { QuestionService } from 'src/app/Service/question.service';
import { ServiceService } from 'src/app/Service/service.service';

@Component({
  selector: 'app-requestion',
  templateUrl: './requestion.component.html',
  styleUrls: ['./requestion.component.css'],
})
export class RequestionComponent implements OnInit {
  question: QuestionI | undefined;
  answers: AnswerI[] | undefined;
  answersNew: AnswerI[] = [];
  currentAnswer: number = 0;
  questions: QuestionI[] | undefined;
  estrellasTotal: number = 0;
  promedio: number = 0;
  page: number = 0;

  constructor(
    private route: ActivatedRoute,
    private questionService: QuestionService,
    private service: QuestionService,
    private modalService: NgbModal,
    public authService: ServiceService
  ) {}

  id: string | undefined;
  userLogged = this.authService.getUserLogged();
  disabled: boolean = false;
  user: any;

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.getQuestions(`${id}`);
    this.get2();
  }

  openVerticallyCentered(content: any) {
    this.modalService.open(content, { centered: true });
  }

  get2() {
    let id = this.route.snapshot.paramMap.get('id');

    this.service.getAnswer(id).subscribe((data) => {
      this.userLogged.subscribe((value) => {
        this.user = value;
        console.log(this.user?.uid);
        console.log(this.question?.answers);
      });
      this.answers = data.answers;
    });
  }

  getQuestions(id: string): void {
    this.questionService.getQuestion(id).subscribe((data) => {
      this.question = data;
      this.answers = data.answers.sort((a, b) => {
        return b.position - a.position;
      });
      this.answers.map((respuesta) => {
        this.estrellasTotal += respuesta.position;
        this.promedio = this.estrellasTotal / data.answers.length;
      });
    });
  }

  AddAnwsers(index: number) {
    let last = this.currentAnswer + index;
    for (let i = this.currentAnswer; i < last; i++) {}
    this.currentAnswer += 10;
  }

  onScroll() {}
}
