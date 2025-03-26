package com.unique.tba.post_auth.home.presentation;

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
public final class HomeViewmodel_Factory implements Factory<HomeViewmodel> {
  private final Provider<SharedPrefConfig> sharedPrefConfigProvider;

  public HomeViewmodel_Factory(Provider<SharedPrefConfig> sharedPrefConfigProvider) {
    this.sharedPrefConfigProvider = sharedPrefConfigProvider;
  }

  @Override
  public HomeViewmodel get() {
    return newInstance(sharedPrefConfigProvider.get());
  }

  public static HomeViewmodel_Factory create(Provider<SharedPrefConfig> sharedPrefConfigProvider) {
    return new HomeViewmodel_Factory(sharedPrefConfigProvider);
  }

  public static HomeViewmodel newInstance(SharedPrefConfig sharedPrefConfig) {
    return new HomeViewmodel(sharedPrefConfig);
  }
}
