package com.company.project.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.company.project.common.injection.module.ApplicationTestModule;
import com.company.project.injection.component.AppComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends AppComponent {
}
