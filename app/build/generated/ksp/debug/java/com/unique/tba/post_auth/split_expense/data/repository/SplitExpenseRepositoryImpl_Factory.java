package com.unique.tba.post_auth.split_expense.data.repository;

import com.unique.tba.post_auth.split_expense.data.remote.dto.SplitExpenseApis;
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
public final class SplitExpenseRepositoryImpl_Factory implements Factory<SplitExpenseRepositoryImpl> {
  private final Provider<SplitExpenseApis> splitExpenseApisProvider;

  public SplitExpenseRepositoryImpl_Factory(Provider<SplitExpenseApis> splitExpenseApisProvider) {
    this.splitExpenseApisProvider = splitExpenseApisProvider;
  }

  @Override
  public SplitExpenseRepositoryImpl get() {
    return newInstance(splitExpenseApisProvider.get());
  }

  public static SplitExpenseRepositoryImpl_Factory create(
      Provider<SplitExpenseApis> splitExpenseApisProvider) {
    return new SplitExpenseRepositoryImpl_Factory(splitExpenseApisProvider);
  }

  public static SplitExpenseRepositoryImpl newInstance(SplitExpenseApis splitExpenseApis) {
    return new SplitExpenseRepositoryImpl(splitExpenseApis);
  }
}
