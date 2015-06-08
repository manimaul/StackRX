package com.example.injection.component;

import com.example.main.activity.StackRXActivity;
import com.example.main.fragment.StackRXBaseFragment;

/**
 * The application base dependency injection graph component interface
 *
 * @see {http://google.github.io/dagger/api/latest/dagger/Component.html}
 */
public interface DeGraphComponent {

    void inject(StackRXActivity activity);

    void inject(StackRXBaseFragment fragment);

}
