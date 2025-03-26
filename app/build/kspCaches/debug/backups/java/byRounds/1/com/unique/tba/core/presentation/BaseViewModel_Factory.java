package com.unique.tba.core.presentation;

import com.unique.tba.core.ConnectivityChecker;
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
public final class BaseViewModel_Factory implements Factory<BaseViewModel> {
  private final Provider<ConnectivityChecker> connectivityCheckerProvider;

  public BaseViewModel_Factory(Provider<ConnectivityChecker> connectivityCheckerProvider) {
    this.connectivityCheckerProvider = connectivityCheckerProvider;
  }

  @Override
  public BaseViewModel get() {
    return newInstance(connectivityCheckerProvider.get());
  }

  public static BaseViewModel_Factory create(
      Provider<ConnectivityChecker> connectivityCheckerProvider) {
    return new BaseViewModel_Factory(connectivityCheckerProvider);
  }

  public static BaseViewModel newInstance(ConnectivityChecker connectivityChecker) {
    return new BaseViewModel(connectivityChecker);
  }
}
