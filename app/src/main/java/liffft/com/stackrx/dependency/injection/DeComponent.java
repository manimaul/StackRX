package liffft.com.stackrx.dependency.injection;

import javax.inject.Singleton;

import dagger.Component;
import liffft.com.stackrx.questions.fragment.QuestionsFragment;

@Singleton
@Component(modules = ServicesModule.class)
public interface DeComponent {

    void inject(QuestionsFragment questionsFragment);
}
