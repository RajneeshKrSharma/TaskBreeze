package com.unique.tba;

import android.app.Activity;
import android.app.Service;
import android.content.SharedPreferences;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.unique.tba.auth.login.data.remote.LoginApis;
import com.unique.tba.auth.login.domain.repository.LoginRepositoryImpl;
import com.unique.tba.auth.login.domain.use_case.ConvertAccessTokenUseCase;
import com.unique.tba.auth.login.domain.use_case.LoginViaOtpUseCase;
import com.unique.tba.auth.login.domain.use_case.OAuthAccessTokenUseCase;
import com.unique.tba.auth.login.domain.use_case.RequestOtpUseCase;
import com.unique.tba.auth.login.presentation.LoginViewmodel;
import com.unique.tba.auth.login.presentation.LoginViewmodel_HiltModules;
import com.unique.tba.auth.login.presentation.LoginViewmodel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.unique.tba.auth.login.presentation.LoginViewmodel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.unique.tba.core.ConnectivityChecker;
import com.unique.tba.core.PendingRequestManager;
import com.unique.tba.core.config.SharedPrefConfig;
import com.unique.tba.core.di.AppModule_ProvideLoginApiFactory;
import com.unique.tba.core.di.AppModule_ProvidePendingRequestManagerFactory;
import com.unique.tba.core.di.AppModule_ProvidePreAuthApiFactory;
import com.unique.tba.core.di.AppModule_ProvideRetrofitFactory;
import com.unique.tba.core.di.AppModule_ProvideSplitExpenseApiFactory;
import com.unique.tba.core.di.AppModule_SharedPreferencesModule_ProvideSharedPreferencesFactory;
import com.unique.tba.core.presentation.BaseViewModel;
import com.unique.tba.core.presentation.BaseViewModel_HiltModules;
import com.unique.tba.core.presentation.BaseViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.unique.tba.core.presentation.BaseViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.unique.tba.post_auth.home.presentation.HomeViewmodel;
import com.unique.tba.post_auth.home.presentation.HomeViewmodel_HiltModules;
import com.unique.tba.post_auth.home.presentation.HomeViewmodel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.unique.tba.post_auth.home.presentation.HomeViewmodel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.unique.tba.post_auth.split_expense.data.remote.dto.SplitExpenseApis;
import com.unique.tba.post_auth.split_expense.data.repository.SplitExpenseRepositoryImpl;
import com.unique.tba.post_auth.split_expense.domain.repository.SplitExpenseRepository;
import com.unique.tba.post_auth.split_expense.domain.use_case.GetGroupExpenseUseCase;
import com.unique.tba.post_auth.split_expense.presentation.SplitExpenseViewModel;
import com.unique.tba.post_auth.split_expense.presentation.SplitExpenseViewModel_HiltModules;
import com.unique.tba.post_auth.split_expense.presentation.SplitExpenseViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.unique.tba.post_auth.split_expense.presentation.SplitExpenseViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.unique.tba.pre_auth.pre_auth_loading.data.remote.PreAuthApis;
import com.unique.tba.pre_auth.pre_auth_loading.data.repository.PreAuthRepositoryImpl;
import com.unique.tba.pre_auth.pre_auth_loading.domain.use_case.GetPreAuthDetails;
import com.unique.tba.pre_auth.pre_auth_loading.presentation.PreAuthViewmodel;
import com.unique.tba.pre_auth.pre_auth_loading.presentation.PreAuthViewmodel_HiltModules;
import com.unique.tba.pre_auth.pre_auth_loading.presentation.PreAuthViewmodel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.unique.tba.pre_auth.pre_auth_loading.presentation.PreAuthViewmodel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.unique.tba.pre_auth.splash.presentation.SplashViewmodel;
import com.unique.tba.pre_auth.splash.presentation.SplashViewmodel_HiltModules;
import com.unique.tba.pre_auth.splash.presentation.SplashViewmodel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.unique.tba.pre_auth.splash.presentation.SplashViewmodel_HiltModules_KeyModule_Provide_LazyMapKey;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class DaggerTaskBreezeApplication_HiltComponents_SingletonC {
  private DaggerTaskBreezeApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public TaskBreezeApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements TaskBreezeApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public TaskBreezeApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements TaskBreezeApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public TaskBreezeApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements TaskBreezeApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public TaskBreezeApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements TaskBreezeApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TaskBreezeApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements TaskBreezeApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TaskBreezeApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements TaskBreezeApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public TaskBreezeApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements TaskBreezeApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public TaskBreezeApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends TaskBreezeApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends TaskBreezeApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends TaskBreezeApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends TaskBreezeApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(6).put(BaseViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, BaseViewModel_HiltModules.KeyModule.provide()).put(HomeViewmodel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HomeViewmodel_HiltModules.KeyModule.provide()).put(LoginViewmodel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, LoginViewmodel_HiltModules.KeyModule.provide()).put(PreAuthViewmodel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, PreAuthViewmodel_HiltModules.KeyModule.provide()).put(SplashViewmodel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SplashViewmodel_HiltModules.KeyModule.provide()).put(SplitExpenseViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SplitExpenseViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends TaskBreezeApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<BaseViewModel> baseViewModelProvider;

    private Provider<HomeViewmodel> homeViewmodelProvider;

    private Provider<LoginViewmodel> loginViewmodelProvider;

    private Provider<PreAuthViewmodel> preAuthViewmodelProvider;

    private Provider<SplashViewmodel> splashViewmodelProvider;

    private Provider<SplitExpenseViewModel> splitExpenseViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private RequestOtpUseCase requestOtpUseCase() {
      return new RequestOtpUseCase(singletonCImpl.loginRepositoryImplProvider.get());
    }

    private LoginViaOtpUseCase loginViaOtpUseCase() {
      return new LoginViaOtpUseCase(singletonCImpl.loginRepositoryImplProvider.get());
    }

    private OAuthAccessTokenUseCase oAuthAccessTokenUseCase() {
      return new OAuthAccessTokenUseCase(singletonCImpl.loginRepositoryImplProvider.get());
    }

    private ConvertAccessTokenUseCase convertAccessTokenUseCase() {
      return new ConvertAccessTokenUseCase(singletonCImpl.loginRepositoryImplProvider.get());
    }

    private GetPreAuthDetails getPreAuthDetails() {
      return new GetPreAuthDetails(singletonCImpl.preAuthRepositoryImplProvider.get(), singletonCImpl.providePendingRequestManagerProvider.get());
    }

    private GetGroupExpenseUseCase getGroupExpenseUseCase() {
      return new GetGroupExpenseUseCase(singletonCImpl.bindSplitExpenseRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.baseViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.homeViewmodelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.loginViewmodelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.preAuthViewmodelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.splashViewmodelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.splitExpenseViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(6).put(BaseViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) baseViewModelProvider)).put(HomeViewmodel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) homeViewmodelProvider)).put(LoginViewmodel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) loginViewmodelProvider)).put(PreAuthViewmodel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) preAuthViewmodelProvider)).put(SplashViewmodel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) splashViewmodelProvider)).put(SplitExpenseViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) splitExpenseViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.unique.tba.core.presentation.BaseViewModel 
          return (T) new BaseViewModel(singletonCImpl.connectivityChecker());

          case 1: // com.unique.tba.post_auth.home.presentation.HomeViewmodel 
          return (T) new HomeViewmodel(singletonCImpl.sharedPrefConfigProvider.get());

          case 2: // com.unique.tba.auth.login.presentation.LoginViewmodel 
          return (T) new LoginViewmodel(viewModelCImpl.requestOtpUseCase(), viewModelCImpl.loginViaOtpUseCase(), viewModelCImpl.oAuthAccessTokenUseCase(), viewModelCImpl.convertAccessTokenUseCase(), singletonCImpl.sharedPrefConfigProvider.get());

          case 3: // com.unique.tba.pre_auth.pre_auth_loading.presentation.PreAuthViewmodel 
          return (T) new PreAuthViewmodel(viewModelCImpl.getPreAuthDetails());

          case 4: // com.unique.tba.pre_auth.splash.presentation.SplashViewmodel 
          return (T) new SplashViewmodel(singletonCImpl.sharedPrefConfigProvider.get());

          case 5: // com.unique.tba.post_auth.split_expense.presentation.SplitExpenseViewModel 
          return (T) new SplitExpenseViewModel(viewModelCImpl.getGroupExpenseUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends TaskBreezeApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends TaskBreezeApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends TaskBreezeApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<SharedPreferences> provideSharedPreferencesProvider;

    private Provider<SharedPrefConfig> sharedPrefConfigProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<LoginApis> provideLoginApiProvider;

    private Provider<LoginRepositoryImpl> loginRepositoryImplProvider;

    private Provider<PreAuthApis> providePreAuthApiProvider;

    private Provider<PreAuthRepositoryImpl> preAuthRepositoryImplProvider;

    private Provider<PendingRequestManager> providePendingRequestManagerProvider;

    private Provider<SplitExpenseApis> provideSplitExpenseApiProvider;

    private Provider<SplitExpenseRepositoryImpl> splitExpenseRepositoryImplProvider;

    private Provider<SplitExpenseRepository> bindSplitExpenseRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private ConnectivityChecker connectivityChecker() {
      return new ConnectivityChecker(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideSharedPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<SharedPreferences>(singletonCImpl, 1));
      this.sharedPrefConfigProvider = DoubleCheck.provider(new SwitchingProvider<SharedPrefConfig>(singletonCImpl, 0));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 4));
      this.provideLoginApiProvider = DoubleCheck.provider(new SwitchingProvider<LoginApis>(singletonCImpl, 3));
      this.loginRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<LoginRepositoryImpl>(singletonCImpl, 2));
      this.providePreAuthApiProvider = DoubleCheck.provider(new SwitchingProvider<PreAuthApis>(singletonCImpl, 6));
      this.preAuthRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<PreAuthRepositoryImpl>(singletonCImpl, 5));
      this.providePendingRequestManagerProvider = DoubleCheck.provider(new SwitchingProvider<PendingRequestManager>(singletonCImpl, 7));
      this.provideSplitExpenseApiProvider = DoubleCheck.provider(new SwitchingProvider<SplitExpenseApis>(singletonCImpl, 9));
      this.splitExpenseRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 8);
      this.bindSplitExpenseRepositoryProvider = DoubleCheck.provider((Provider) splitExpenseRepositoryImplProvider);
    }

    @Override
    public void injectTaskBreezeApplication(TaskBreezeApplication taskBreezeApplication) {
      injectTaskBreezeApplication2(taskBreezeApplication);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private TaskBreezeApplication injectTaskBreezeApplication2(TaskBreezeApplication instance) {
      TaskBreezeApplication_MembersInjector.injectConnectivityChecker(instance, connectivityChecker());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.unique.tba.core.config.SharedPrefConfig 
          return (T) new SharedPrefConfig(singletonCImpl.provideSharedPreferencesProvider.get());

          case 1: // android.content.SharedPreferences 
          return (T) AppModule_SharedPreferencesModule_ProvideSharedPreferencesFactory.provideSharedPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.unique.tba.auth.login.domain.repository.LoginRepositoryImpl 
          return (T) new LoginRepositoryImpl(singletonCImpl.provideLoginApiProvider.get());

          case 3: // com.unique.tba.auth.login.data.remote.LoginApis 
          return (T) AppModule_ProvideLoginApiFactory.provideLoginApi(singletonCImpl.provideRetrofitProvider.get());

          case 4: // retrofit2.Retrofit 
          return (T) AppModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.sharedPrefConfigProvider.get());

          case 5: // com.unique.tba.pre_auth.pre_auth_loading.data.repository.PreAuthRepositoryImpl 
          return (T) new PreAuthRepositoryImpl(singletonCImpl.providePreAuthApiProvider.get());

          case 6: // com.unique.tba.pre_auth.pre_auth_loading.data.remote.PreAuthApis 
          return (T) AppModule_ProvidePreAuthApiFactory.providePreAuthApi(singletonCImpl.provideRetrofitProvider.get());

          case 7: // com.unique.tba.core.PendingRequestManager 
          return (T) AppModule_ProvidePendingRequestManagerFactory.providePendingRequestManager(singletonCImpl.connectivityChecker());

          case 8: // com.unique.tba.post_auth.split_expense.data.repository.SplitExpenseRepositoryImpl 
          return (T) new SplitExpenseRepositoryImpl(singletonCImpl.provideSplitExpenseApiProvider.get());

          case 9: // com.unique.tba.post_auth.split_expense.data.remote.dto.SplitExpenseApis 
          return (T) AppModule_ProvideSplitExpenseApiFactory.provideSplitExpenseApi(singletonCImpl.provideRetrofitProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
