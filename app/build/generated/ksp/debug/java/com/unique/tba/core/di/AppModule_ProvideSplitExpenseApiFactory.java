package com.unique.tba.core.di;

import com.unique.tba.post_auth.split_expense.data.remote.dto.SplitExpenseApis;
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
public final class AppModule_ProvideSplitExpenseApiFactory implements Factory<SplitExpenseApis> {
  private final Provider<Retrofit> retrofitProvider;

  public AppModule_ProvideSplitExpenseApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public SplitExpenseApis get() {
    return provideSplitExpenseApi(retrofitProvider.get());
  }

  public static AppModule_ProvideSplitExpenseApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new AppModule_ProvideSplitExpenseApiFactory(retrofitProvider);
  }

  public static SplitExpenseApis provideSplitExpenseApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSplitExpenseApi(retrofit));
  }
}
