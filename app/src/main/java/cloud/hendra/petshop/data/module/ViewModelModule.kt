package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.ui.viewmodel.GuardViewModel
import cloud.hendra.petshop.ui.viewmodel.IndexViewModel
import cloud.hendra.petshop.ui.viewmodel.ProtectedViewModel
import cloud.hendra.petshop.ui.viewmodel.RefreshViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { IndexViewModel(get()) }
    viewModel { ProtectedViewModel(get(), get()) }
    viewModel { RefreshViewModel(get()) }
    viewModel { GuardViewModel(get()) }
}