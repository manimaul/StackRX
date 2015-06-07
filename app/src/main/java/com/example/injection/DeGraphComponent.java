package com.example.injection;

import com.example.main.activity.StackRXActivity;
import com.example.main.fragment.StackRXBaseFragmemt;

/**
 * The application base dependency injection graph component interface
 *
 * @see {http://google.github.io/dagger/api/latest/dagger/Component.html}
 */
public interface DeGraphComponent {
    void inject(StackRXActivity activity);

    void inject(StackRXBaseFragmemt fragmemt);
}
