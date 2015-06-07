package com.example.questions.fragment;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.test.ActivityInstrumentationTestCase2;

import com.example.injection.DeGraphComponent;
import com.example.main.activity.StackRXActivity;
import com.example.main.application.StackRXApp;
import com.example.main.fragment.InitialFragment;
import com.example.main.fragment.StackRXBaseFragmemt;
import com.example.main.user.UserSession;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import example.com.stackrx.R;
import example.com.stackrx.services.questions.dao.QuestionsDAO;
import example.com.stackrx.services.questions.model.Questions;
import rx.Observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class QuestionsFragmentTest extends ActivityInstrumentationTestCase2<StackRXActivity> {

    private QuestionsFragmentTestModule _questionsFragmentTestModule;

    public QuestionsFragmentTest() {
        super(StackRXActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        //work around for mockito https://code.google.com/p/dexmaker/issues/detail?id=2
        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());

        //setup testing dependency injection
        _questionsFragmentTestModule = new QuestionsFragmentTestModule();

        StackRXApp app = (StackRXApp) getInstrumentation().getTargetContext().getApplicationContext();

        DeGraphComponent component = DaggerQuestionsFragmentTest_DeTestGraphComponent.builder()
                .questionsFragmentTestModule(_questionsFragmentTestModule)
                .build();

        app.setComponent(component);

        //Minimum mock data to prevent Fragment NPE
        Questions questions = new Questions();
        Mockito.when(_questionsFragmentTestModule._questionsDAO.getQuestions()).thenReturn(Observable.just(questions));
    }


    private QuestionsFragment startFragment() {
        return (QuestionsFragment) getActivity().getFragmentManager().findFragmentById(R.id.container);
    }

    //Todo: failing test
    public void testdNetwork_Disconnected() throws Exception {
        /*
         * When network state is disconnected, verify that QuestionsDAO.getQuestions() is NOT called
         */

        NetworkInfo networkInfo = Mockito.mock(NetworkInfo.class);
        Mockito.when(networkInfo.getState()).thenReturn(NetworkInfo.State.DISCONNECTED);
        Mockito.when(_questionsFragmentTestModule._connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        startFragment();

        //The thing under test
        Mockito.verify(_questionsFragmentTestModule._questionsDAO, never()).getQuestions();
    }

    public void testNetwork_Connected() throws Exception {
        /*
         * When network state is connected, verify that QuestionsDAO.getQuestions() is called
         */

        NetworkInfo networkInfo = Mockito.mock(NetworkInfo.class);
        Mockito.when(networkInfo.getState()).thenReturn(NetworkInfo.State.CONNECTED);
        Mockito.when(_questionsFragmentTestModule._connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        startFragment();

        //The thing under test
        Mockito.verify(_questionsFragmentTestModule._questionsDAO, times(1)).getQuestions();
    }


    //region Dependency Injection ------------------------------------------------------------------

    @Singleton
    @Component(modules = {QuestionsFragmentTestModule.class})
    public interface DeTestGraphComponent extends DeGraphComponent {
    }

    @Module
    public static class QuestionsFragmentTestModule {

        @Mock
        QuestionsDAO _questionsDAO;

        @Mock
        UserSession _userSession;

        @Mock
        ConnectivityManager _connectivityManager;

        public QuestionsFragmentTestModule() {
            MockitoAnnotations.initMocks(this);
        }

        @Provides
        InitialFragment provideFragmentCreator() {

            return new InitialFragment() {
                @Override
                public StackRXBaseFragmemt createInitialFragment() {
                    return new QuestionsFragment();
                }
            };
        }

        @Provides
        ConnectivityManager provideConnectivityManager() {
            return _connectivityManager;
        }

        @Provides
        UserSession provideUserSession() {
            return _userSession;
        }

        @Provides
        QuestionsDAO provideQuestionDAO() {
            return _questionsDAO;
        }
    }

    //endregion
}
