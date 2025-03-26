package com.unique.tba.core.di;

import com.unique.tba.pre_auth.pre_auth_loading.data.remote.PreAuthApis;
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
public final class AppModule_ProvidePreAuthApiFactory implements Factory<PreAuthApis> {
  private final Provider<Retrofit> retrofitProvider;

  public AppModule_ProvidePreAuthApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PreAuthApis get() {
    return providePreAuthApi(retrofitProvider.get());
  }

  public static AppModule_ProvidePreAuthApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new AppModule_ProvidePreAuthApiFactory(retrofitProvider);
  }

  public static PreAuthApis providePreAuthApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePreAuthApi(retrofit));
  }
}
