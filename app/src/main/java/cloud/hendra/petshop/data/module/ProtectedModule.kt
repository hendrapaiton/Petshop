package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.ui.viewmodel.ProtectedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val protectedModule = module {
    viewModel {
        ProtectedViewModel(
            protectedRepository = get(),
            tokenManager = get()
        )
    }
}