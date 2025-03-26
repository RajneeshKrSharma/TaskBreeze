package com.unique.tba.core.di;

import android.content.Context;
import com.unique.tba.core.local_db.TaskBreezeDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideTaskBreezeDatabaseFactory implements Factory<TaskBreezeDatabase> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideTaskBreezeDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public TaskBreezeDatabase get() {
    return provideTaskBreezeDatabase(contextProvider.get());
  }

  public static AppModule_ProvideTaskBreezeDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new AppModule_ProvideTaskBreezeDatabaseFactory(contextProvider);
  }

  public static TaskBreezeDatabase provideTaskBreezeDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTaskBreezeDatabase(context));
  }
}
