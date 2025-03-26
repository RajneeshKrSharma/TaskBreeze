package com.unique.tba;

import com.unique.tba.core.ConnectivityChecker;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class TaskBreezeApplication_MembersInjector implements MembersInjector<TaskBreezeApplication> {
  private final Provider<ConnectivityChecker> connectivityCheckerProvider;

  public TaskBreezeApplication_MembersInjector(
      Provider<ConnectivityChecker> connectivityCheckerProvider) {
    this.connectivityCheckerProvider = connectivityCheckerProvider;
  }

  public static MembersInjector<TaskBreezeApplication> create(
      Provider<ConnectivityChecker> connectivityCheckerProvider) {
    return new TaskBreezeApplication_MembersInjector(connectivityCheckerProvider);
  }

  @Override
  public void injectMembers(TaskBreezeApplication instance) {
    injectConnectivityChecker(instance, connectivityCheckerProvider.get());
  }

  @InjectedFieldSignature("com.unique.tba.TaskBreezeApplication.connectivityChecker")
  public static void injectConnectivityChecker(TaskBreezeApplication instance,
      ConnectivityChecker connectivityChecker) {
    instance.connectivityChecker = connectivityChecker;
  }
}
