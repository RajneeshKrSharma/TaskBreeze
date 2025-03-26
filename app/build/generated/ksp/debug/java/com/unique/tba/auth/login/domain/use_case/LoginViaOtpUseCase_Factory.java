package com.unique.tba.auth.login.domain.use_case;

import com.unique.tba.auth.login.data.repository.LoginRepository;
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
public final class LoginViaOtpUseCase_Factory implements Factory<LoginViaOtpUseCase> {
  private final Provider<LoginRepository> repositoryProvider;

  public LoginViaOtpUseCase_Factory(Provider<LoginRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LoginViaOtpUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static LoginViaOtpUseCase_Factory create(Provider<LoginRepository> repositoryProvider) {
    return new LoginViaOtpUseCase_Factory(repositoryProvider);
  }

  public static LoginViaOtpUseCase newInstance(LoginRepository repository) {
    return new LoginViaOtpUseCase(repository);
  }
}
