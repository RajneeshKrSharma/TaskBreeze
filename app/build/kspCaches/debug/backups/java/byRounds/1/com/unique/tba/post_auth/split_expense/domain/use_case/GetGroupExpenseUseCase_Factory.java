package com.unique.tba.post_auth.split_expense.domain.use_case;

import com.unique.tba.post_auth.split_expense.domain.repository.SplitExpenseRepository;
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
public final class GetGroupExpenseUseCase_Factory implements Factory<GetGroupExpenseUseCase> {
  private final Provider<SplitExpenseRepository> repositoryProvider;

  public GetGroupExpenseUseCase_Factory(Provider<SplitExpenseRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetGroupExpenseUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetGroupExpenseUseCase_Factory create(
      Provider<SplitExpenseRepository> repositoryProvider) {
    return new GetGroupExpenseUseCase_Factory(repositoryProvider);
  }

  public static GetGroupExpenseUseCase newInstance(SplitExpenseRepository repository) {
    return new GetGroupExpenseUseCase(repository);
  }
}
