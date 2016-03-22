package example.com.stackrx.services.questions.service;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import example.com.stackrx.services.questions.model.Questions;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import rx.Observable;

public class StackExchangeService {

    private static final String ENDPOINT = "https://api.stackexchange.com";

    private IStackExchangeService mService;

    public StackExchangeService() {
        RequestInterceptor interceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("format", "json");
                request.addHeader("Accept", "application/json");
                request.addHeader("Content-Type", "application/json");
            }
        };

        RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        restAdapterBuilder
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(interceptor)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS);

        RestAdapter restAdapter = restAdapterBuilder.build();
        mService = restAdapter.create(IStackExchangeService.class);
    }

    /**
     * GET list of questions
     *
     * @return List of questions
     */
    public Observable<Questions> getQuestions() {
        return mService.getQuestions();
    }
}
