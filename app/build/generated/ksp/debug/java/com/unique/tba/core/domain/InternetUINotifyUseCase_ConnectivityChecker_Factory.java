package com.unique.tba.core.domain;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class InternetUINotifyUseCase_ConnectivityChecker_Factory implements Factory<InternetUINotifyUseCase.ConnectivityChecker> {
  private final Provider<Context> contextProvider;

  public InternetUINotifyUseCase_ConnectivityChecker_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public InternetUINotifyUseCase.ConnectivityChecker get() {
    return newInstance(contextProvider.get());
  }

  public static InternetUINotifyUseCase_ConnectivityChecker_Factory create(
      Provider<Context> contextProvider) {
    return new InternetUINotifyUseCase_ConnectivityChecker_Factory(contextProvider);
  }

  public static InternetUINotifyUseCase.ConnectivityChecker newInstance(Context context) {
    return new InternetUINotifyUseCase.ConnectivityChecker(context);
  }
}
