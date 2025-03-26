package com.unique.tba.pre_auth.pre_auth_loading.presentation;

import com.unique.tba.pre_auth.pre_auth_loading.domain.use_case.GetPreAuthDetails;
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
public final class PreAuthViewmodel_Factory implements Factory<PreAuthViewmodel> {
  private final Provider<GetPreAuthDetails> getPreAuthDetailsProvider;

  public PreAuthViewmodel_Factory(Provider<GetPreAuthDetails> getPreAuthDetailsProvider) {
    this.getPreAuthDetailsProvider = getPreAuthDetailsProvider;
  }

  @Override
  public PreAuthViewmodel get() {
    return newInstance(getPreAuthDetailsProvider.get());
  }

  public static PreAuthViewmodel_Factory create(
      Provider<GetPreAuthDetails> getPreAuthDetailsProvider) {
    return new PreAuthViewmodel_Factory(getPreAuthDetailsProvider);
  }

  public static PreAuthViewmodel newInstance(GetPreAuthDetails getPreAuthDetails) {
    return new PreAuthViewmodel(getPreAuthDetails);
  }
}
