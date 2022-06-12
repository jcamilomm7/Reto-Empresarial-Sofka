import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { QuestionI } from '../models/question-i';
import { AnswerI } from '../models/answer-i';
import { getToken } from 'firebase/app-check';
@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  push(arg0: string) {
    throw new Error('Method not implemented.');
  }


  private url: string = 'https://reto-empresarial-sofka.herokuapp.com/';
  private headers: HttpHeaders = new HttpHeaders();
  private token:String="";



  constructor(private http: HttpClient) {


  }

  getToken():string{
    let userData:any = JSON.parse(localStorage.getItem('user')!);
    let token:string;

    console.log("token: "+userData?.stsTokenManager?.accessToken);
    token=userData?.stsTokenManager?.accessToken;
    if(token===undefined){
      token="";

    }
    return token;
  }
  getHeader(): HttpHeaders{
    let headers: HttpHeaders = new HttpHeaders()
    headers = headers.append('Authorization',this.getToken());
    headers = headers.append('x-Flatten', 'true');
    headers = headers.append('Content-Type', 'application/json');
    return headers;
  }

  getPage(page: number): Observable<QuestionI[]> {
    //this.headers=this.getHeader();
    let direction = this.url + 'pagination/' + page;
    return this.http.get<QuestionI[]>(direction,{headers:this.headers});
  }

  getAnswer(id: any): Observable<QuestionI> {
    this.headers=this.getHeader();
    let direction = this.url + 'get/' + id;
    return this.http.get<QuestionI>(direction,{headers:this.headers});
  }

  //Nuevo
  getQuestionAll(): Observable<QuestionI> {
    this.headers=this.getHeader();
    let direction = this.url + 'getAll/';
    return this.http.get<QuestionI>(direction,{headers:this.headers});
  }

  getQuestion(id: string): Observable<QuestionI> {
    this.headers=this.getHeader();
    let direction = this.url + 'get/' + id;
    return this.http.get<QuestionI>(direction,{headers:this.headers});
  }

  getTotalPages(): Observable<number> {
    this.headers=this.getHeader();
    let direction = this.url + 'totalPages';
    return this.http.get<number>(direction,{headers:this.headers});
  }

  getCountQuestions(): Observable<number> {
    this.headers=this.getHeader();
    let direction = this.url + 'countQuestions';
    return this.http.get<number>(direction,{headers:this.headers});
  }

  saveQuestion(question: QuestionI): Observable<any> {
    this.headers=this.getHeader();
    let direction = this.url + 'create';
    return this.http.post<any>(direction, question,{headers:this.headers})/* {
      responseType: 'text' as 'json',
    }); */
  }

  saveAnswer(answer: AnswerI): Observable<any> {
    this.headers=this.getHeader();
    let direction = this.url + 'add';
    return this.http.post<any>(direction, answer,{headers:this.headers});
  }

  editQuestion(question: QuestionI): Observable<any> {
    this.headers=this.getHeader();
    let direction = this.url + 'update';
    return this.http.post<any>(direction, question,{headers:this.headers});
  }
}
