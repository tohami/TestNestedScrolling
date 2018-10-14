package com.company.project.injection.component;

import dagger.Subcomponent;
import com.company.project.features.main.MainActivity;
import com.company.project.injection.PerActivity;
import com.company.project.injection.module.ActivityModule;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
