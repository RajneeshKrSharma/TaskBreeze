package com.unique.tba.post_auth.split_expense.presentation;

import com.unique.tba.post_auth.split_expense.domain.use_case.GetGroupExpenseUseCase;
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
public final class SplitExpenseViewModel_Factory implements Factory<SplitExpenseViewModel> {
  private final Provider<GetGroupExpenseUseCase> getGroupExpenseUseCaseProvider;

  public SplitExpenseViewModel_Factory(
      Provider<GetGroupExpenseUseCase> getGroupExpenseUseCaseProvider) {
    this.getGroupExpenseUseCaseProvider = getGroupExpenseUseCaseProvider;
  }

  @Override
  public SplitExpenseViewModel get() {
    return newInstance(getGroupExpenseUseCaseProvider.get());
  }

  public static SplitExpenseViewModel_Factory create(
      Provider<GetGroupExpenseUseCase> getGroupExpenseUseCaseProvider) {
    return new SplitExpenseViewModel_Factory(getGroupExpenseUseCaseProvider);
  }

  public static SplitExpenseViewModel newInstance(GetGroupExpenseUseCase getGroupExpenseUseCase) {
    return new SplitExpenseViewModel(getGroupExpenseUseCase);
  }
}
