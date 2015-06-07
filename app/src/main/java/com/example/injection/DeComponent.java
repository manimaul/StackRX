package com.example.injection;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ServicesModule.class, FragmentModule.class})
public interface DeComponent extends DeGraphComponent {
}
