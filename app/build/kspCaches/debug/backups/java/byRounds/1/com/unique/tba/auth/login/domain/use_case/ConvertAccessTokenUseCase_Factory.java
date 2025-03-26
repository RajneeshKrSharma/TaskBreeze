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
public final class ConvertAccessTokenUseCase_Factory implements Factory<ConvertAccessTokenUseCase> {
  private final Provider<LoginRepository> repositoryProvider;

  public ConvertAccessTokenUseCase_Factory(Provider<LoginRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ConvertAccessTokenUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ConvertAccessTokenUseCase_Factory create(
      Provider<LoginRepository> repositoryProvider) {
    return new ConvertAccessTokenUseCase_Factory(repositoryProvider);
  }

  public static ConvertAccessTokenUseCase newInstance(LoginRepository repository) {
    return new ConvertAccessTokenUseCase(repository);
  }
}
