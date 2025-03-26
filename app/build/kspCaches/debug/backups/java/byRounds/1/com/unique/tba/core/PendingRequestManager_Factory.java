package com.unique.tba.core;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class PendingRequestManager_Factory implements Factory<PendingRequestManager> {
  private final Provider<ConnectivityChecker> connectivityCheckerProvider;

  public PendingRequestManager_Factory(Provider<ConnectivityChecker> connectivityCheckerProvider) {
    this.connectivityCheckerProvider = connectivityCheckerProvider;
  }

  @Override
  public PendingRequestManager get() {
    return newInstance(connectivityCheckerProvider.get());
  }

  public static PendingRequestManager_Factory create(
      Provider<ConnectivityChecker> connectivityCheckerProvider) {
    return new PendingRequestManager_Factory(connectivityCheckerProvider);
  }

  public static PendingRequestManager newInstance(ConnectivityChecker connectivityChecker) {
    return new PendingRequestManager(connectivityChecker);
  }
}
