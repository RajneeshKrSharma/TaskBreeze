package com.unique.tba.pre_auth.pre_auth_loading.data.repository;

import com.unique.tba.pre_auth.pre_auth_loading.data.remote.PreAuthApis;
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
public final class PreAuthRepositoryImpl_Factory implements Factory<PreAuthRepositoryImpl> {
  private final Provider<PreAuthApis> apiProvider;

  public PreAuthRepositoryImpl_Factory(Provider<PreAuthApis> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public PreAuthRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static PreAuthRepositoryImpl_Factory create(Provider<PreAuthApis> apiProvider) {
    return new PreAuthRepositoryImpl_Factory(apiProvider);
  }

  public static PreAuthRepositoryImpl newInstance(PreAuthApis api) {
    return new PreAuthRepositoryImpl(api);
  }
}
