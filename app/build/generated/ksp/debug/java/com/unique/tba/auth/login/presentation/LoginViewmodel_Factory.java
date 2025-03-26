package com.unique.tba.auth.login.presentation;

import com.unique.tba.auth.login.domain.use_case.ConvertAccessTokenUseCase;
import com.unique.tba.auth.login.domain.use_case.LoginViaOtpUseCase;
import com.unique.tba.auth.login.domain.use_case.OAuthAccessTokenUseCase;
import com.unique.tba.auth.login.domain.use_case.RequestOtpUseCase;
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
public final class LoginViewmodel_Factory implements Factory<LoginViewmodel> {
  private final Provider<RequestOtpUseCase> requestOtpUseCaseProvider;

  private final Provider<LoginViaOtpUseCase> loginViaOtpUseCaseProvider;

  private final Provider<OAuthAccessTokenUseCase> oAuthAccessTokenUseCaseProvider;

  private final Provider<ConvertAccessTokenUseCase> convertAccessTokenUseCaseProvider;

  private final Provider<SharedPrefConfig> sharedPrefConfigProvider;

  public LoginViewmodel_Factory(Provider<RequestOtpUseCase> requestOtpUseCaseProvider,
      Provider<LoginViaOtpUseCase> loginViaOtpUseCaseProvider,
      Provider<OAuthAccessTokenUseCase> oAuthAccessTokenUseCaseProvider,
      Provider<ConvertAccessTokenUseCase> convertAccessTokenUseCaseProvider,
      Provider<SharedPrefConfig> sharedPrefConfigProvider) {
    this.requestOtpUseCaseProvider = requestOtpUseCaseProvider;
    this.loginViaOtpUseCaseProvider = loginViaOtpUseCaseProvider;
    this.oAuthAccessTokenUseCaseProvider = oAuthAccessTokenUseCaseProvider;
    this.convertAccessTokenUseCaseProvider = convertAccessTokenUseCaseProvider;
    this.sharedPrefConfigProvider = sharedPrefConfigProvider;
  }

  @Override
  public LoginViewmodel get() {
    return newInstance(requestOtpUseCaseProvider.get(), loginViaOtpUseCaseProvider.get(), oAuthAccessTokenUseCaseProvider.get(), convertAccessTokenUseCaseProvider.get(), sharedPrefConfigProvider.get());
  }

  public static LoginViewmodel_Factory create(Provider<RequestOtpUseCase> requestOtpUseCaseProvider,
      Provider<LoginViaOtpUseCase> loginViaOtpUseCaseProvider,
      Provider<OAuthAccessTokenUseCase> oAuthAccessTokenUseCaseProvider,
      Provider<ConvertAccessTokenUseCase> convertAccessTokenUseCaseProvider,
      Provider<SharedPrefConfig> sharedPrefConfigProvider) {
    return new LoginViewmodel_Factory(requestOtpUseCaseProvider, loginViaOtpUseCaseProvider, oAuthAccessTokenUseCaseProvider, convertAccessTokenUseCaseProvider, sharedPrefConfigProvider);
  }

  public static LoginViewmodel newInstance(RequestOtpUseCase requestOtpUseCase,
      LoginViaOtpUseCase loginViaOtpUseCase, OAuthAccessTokenUseCase oAuthAccessTokenUseCase,
      ConvertAccessTokenUseCase convertAccessTokenUseCase, SharedPrefConfig sharedPrefConfig) {
    return new LoginViewmodel(requestOtpUseCase, loginViaOtpUseCase, oAuthAccessTokenUseCase, convertAccessTokenUseCase, sharedPrefConfig);
  }
}
