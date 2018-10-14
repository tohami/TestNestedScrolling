package com.company.project.injection.component;

import dagger.Subcomponent;
import com.company.project.injection.PerFragment;
import com.company.project.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
}
