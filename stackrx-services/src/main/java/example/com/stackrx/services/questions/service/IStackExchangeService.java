package example.com.stackrx.services.questions.service;

import example.com.stackrx.services.questions.model.Questions;
import retrofit.http.GET;
import rx.Observable;

public interface IStackExchangeService {
    @GET("/2.2/questions?order=desc&sort=activity&site=stackoverflow")
    Observable<Questions> getQuestions();
}
