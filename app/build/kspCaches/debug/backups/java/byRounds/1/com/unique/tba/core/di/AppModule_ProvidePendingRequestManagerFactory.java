package com.unique.tba.core.di;

import com.unique.tba.core.ConnectivityChecker;
import com.unique.tba.core.PendingRequestManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvidePendingRequestManagerFactory implements Factory<PendingRequestManager> {
  private final Provider<ConnectivityChecker> connectivityCheckerProvider;

  public AppModule_ProvidePendingRequestManagerFactory(
      Provider<ConnectivityChecker> connectivityCheckerProvider) {
    this.connectivityCheckerProvider = connectivityCheckerProvider;
  }

  @Override
  public PendingRequestManager get() {
    return providePendingRequestManager(connectivityCheckerProvider.get());
  }

  public static AppModule_ProvidePendingRequestManagerFactory create(
      Provider<ConnectivityChecker> connectivityCheckerProvider) {
    return new AppModule_ProvidePendingRequestManagerFactory(connectivityCheckerProvider);
  }

  public static PendingRequestManager providePendingRequestManager(
      ConnectivityChecker connectivityChecker) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePendingRequestManager(connectivityChecker));
  }
}
