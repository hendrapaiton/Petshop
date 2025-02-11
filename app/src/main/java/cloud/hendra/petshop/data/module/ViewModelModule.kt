package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.ui.viewmodel.IndexViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { IndexViewModel(get()) }
}