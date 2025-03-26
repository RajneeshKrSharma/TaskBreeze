package com.unique.tba.auth.login.domain.repository;

import com.unique.tba.auth.login.data.remote.LoginApis;
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
public final class LoginRepositoryImpl_Factory implements Factory<LoginRepositoryImpl> {
  private final Provider<LoginApis> apiProvider;

  public LoginRepositoryImpl_Factory(Provider<LoginApis> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public LoginRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static LoginRepositoryImpl_Factory create(Provider<LoginApis> apiProvider) {
    return new LoginRepositoryImpl_Factory(apiProvider);
  }

  public static LoginRepositoryImpl newInstance(LoginApis api) {
    return new LoginRepositoryImpl(api);
  }
}
