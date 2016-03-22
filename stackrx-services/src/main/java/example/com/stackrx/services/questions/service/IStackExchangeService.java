package example.com.stackrx.services.questions.service;

import example.com.stackrx.services.questions.model.Questions;
import retrofit.http.GET;
import rx.Observable;

/**
 * Retrofit service interface to the stack exchange API
 * <p/>
 * {@see <a href="https://api.stackexchange.com/docs">API</a>
 */
public interface IStackExchangeService {

    /**
     * Gets all the questions on the site.
     *
     * {@see <a href="https://api.stackexchange.com/docs/questions">API</a> }
     */
    @GET("/2.2/questions?order=desc&sort=activity&site=stackoverflow")
    Observable<Questions> getQuestions();
}
