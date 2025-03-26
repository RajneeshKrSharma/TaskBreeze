package com.unique.tba.core.di;

import com.unique.tba.auth.login.data.remote.LoginApis;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class AppModule_ProvideLoginApiFactory implements Factory<LoginApis> {
  private final Provider<Retrofit> retrofitProvider;

  public AppModule_ProvideLoginApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public LoginApis get() {
    return provideLoginApi(retrofitProvider.get());
  }

  public static AppModule_ProvideLoginApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new AppModule_ProvideLoginApiFactory(retrofitProvider);
  }

  public static LoginApis provideLoginApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideLoginApi(retrofit));
  }
}
