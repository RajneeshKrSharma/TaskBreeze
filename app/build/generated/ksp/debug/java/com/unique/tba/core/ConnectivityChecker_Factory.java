package com.unique.tba.core;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ConnectivityChecker_Factory implements Factory<ConnectivityChecker> {
  private final Provider<Context> contextProvider;

  public ConnectivityChecker_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ConnectivityChecker get() {
    return newInstance(contextProvider.get());
  }

  public static ConnectivityChecker_Factory create(Provider<Context> contextProvider) {
    return new ConnectivityChecker_Factory(contextProvider);
  }

  public static ConnectivityChecker newInstance(Context context) {
    return new ConnectivityChecker(context);
  }
}
