/* import { QuestionService } from './question.service';
import { of, throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

describe('Service: User', () => {
  let httpClientSpy: { get: jasmine.Spy };
  let questionService: QuestionService;

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    questionService = new QuestionService(httpClientSpy as any);
  });

  it('should return expected users (HttpClient called once)', () => {
    const expectedQuestion = [
      { id: 1, name: 'A' },
      { id: 2, name: 'B' }
    ];

    httpClientSpy.get.and.returnValue(of(expectedQuestion));

    questionService.getQuestionPrueba

    expect(questionService.users.length).toBe(2);
    expect(questionService.users[0]['name']).toBe('A');
    expect(httpClientSpy.get.calls.count()).toBe(1, 'one call');
  });

  it('should return an error when the server returns a 404', () => {
    const errorResponse = new HttpErrorResponse({
      error: 'test 404 error',
      status: 404,
      statusText: 'Not Found'
    });

    httpClientSpy.get.and.returnValue(throwError(errorResponse));

    userService.getUsers();

    expect(userService.users).toBeUndefined();
  });
 });
*/
