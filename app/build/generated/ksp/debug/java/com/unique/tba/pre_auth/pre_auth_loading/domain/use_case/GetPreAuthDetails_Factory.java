package com.unique.tba.pre_auth.pre_auth_loading.domain.use_case;

import com.unique.tba.core.PendingRequestManager;
import com.unique.tba.pre_auth.pre_auth_loading.domain.repository.PreAuthRepository;
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
public final class GetPreAuthDetails_Factory implements Factory<GetPreAuthDetails> {
  private final Provider<PreAuthRepository> repositoryProvider;

  private final Provider<PendingRequestManager> pendingRequestManagerProvider;

  public GetPreAuthDetails_Factory(Provider<PreAuthRepository> repositoryProvider,
      Provider<PendingRequestManager> pendingRequestManagerProvider) {
    this.repositoryProvider = repositoryProvider;
    this.pendingRequestManagerProvider = pendingRequestManagerProvider;
  }

  @Override
  public GetPreAuthDetails get() {
    return newInstance(repositoryProvider.get(), pendingRequestManagerProvider.get());
  }

  public static GetPreAuthDetails_Factory create(Provider<PreAuthRepository> repositoryProvider,
      Provider<PendingRequestManager> pendingRequestManagerProvider) {
    return new GetPreAuthDetails_Factory(repositoryProvider, pendingRequestManagerProvider);
  }

  public static GetPreAuthDetails newInstance(PreAuthRepository repository,
      PendingRequestManager pendingRequestManager) {
    return new GetPreAuthDetails(repository, pendingRequestManager);
  }
}
