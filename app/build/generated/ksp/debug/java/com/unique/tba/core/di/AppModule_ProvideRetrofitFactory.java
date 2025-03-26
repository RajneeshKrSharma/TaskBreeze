package com.unique.tba.core.di;

import com.unique.tba.core.config.SharedPrefConfig;
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
public final class AppModule_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final Provider<SharedPrefConfig> sharedPrefConfigProvider;

  public AppModule_ProvideRetrofitFactory(Provider<SharedPrefConfig> sharedPrefConfigProvider) {
    this.sharedPrefConfigProvider = sharedPrefConfigProvider;
  }

  @Override
  public Retrofit get() {
    return provideRetrofit(sharedPrefConfigProvider.get());
  }

  public static AppModule_ProvideRetrofitFactory create(
      Provider<SharedPrefConfig> sharedPrefConfigProvider) {
    return new AppModule_ProvideRetrofitFactory(sharedPrefConfigProvider);
  }

  public static Retrofit provideRetrofit(SharedPrefConfig sharedPrefConfig) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRetrofit(sharedPrefConfig));
  }
}
