package com.unique.tba.core.config;

import android.content.SharedPreferences;
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
public final class SharedPrefConfig_Factory implements Factory<SharedPrefConfig> {
  private final Provider<SharedPreferences> sharedPreferencesProvider;

  public SharedPrefConfig_Factory(Provider<SharedPreferences> sharedPreferencesProvider) {
    this.sharedPreferencesProvider = sharedPreferencesProvider;
  }

  @Override
  public SharedPrefConfig get() {
    return newInstance(sharedPreferencesProvider.get());
  }

  public static SharedPrefConfig_Factory create(
      Provider<SharedPreferences> sharedPreferencesProvider) {
    return new SharedPrefConfig_Factory(sharedPreferencesProvider);
  }

  public static SharedPrefConfig newInstance(SharedPreferences sharedPreferences) {
    return new SharedPrefConfig(sharedPreferences);
  }
}
