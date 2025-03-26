package com.unique.tba.pre_auth.splash.presentation;

import com.unique.tba.core.config.SharedPrefConfig;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class SplashViewmodel_Factory implements Factory<SplashViewmodel> {
  private final Provider<SharedPrefConfig> sharedPrefConfigProvider;

  public SplashViewmodel_Factory(Provider<SharedPrefConfig> sharedPrefConfigProvider) {
    this.sharedPrefConfigProvider = sharedPrefConfigProvider;
  }

  @Override
  public SplashViewmodel get() {
    return newInstance(sharedPrefConfigProvider.get());
  }

  public static SplashViewmodel_Factory create(
      Provider<SharedPrefConfig> sharedPrefConfigProvider) {
    return new SplashViewmodel_Factory(sharedPrefConfigProvider);
  }

  public static SplashViewmodel newInstance(SharedPrefConfig sharedPrefConfig) {
    return new SplashViewmodel(sharedPrefConfig);
  }
}
