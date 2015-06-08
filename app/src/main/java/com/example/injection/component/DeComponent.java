package com.example.injection.component;

import com.example.injection.module.FragmentModule;
import com.example.injection.module.ServicesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ServicesModule.class, FragmentModule.class})
public interface DeComponent extends DeGraphComponent {
}
