package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.data.repository.IndexRepository
import cloud.hendra.petshop.data.repository.IndexRepositoryImpl
import cloud.hendra.petshop.data.repository.ProtectedRepository
import cloud.hendra.petshop.data.repository.ProtectedRepositoryImpl
import cloud.hendra.petshop.data.repository.RefreshRepository
import cloud.hendra.petshop.data.repository.RefreshRepositoryImpl
import cloud.hendra.petshop.domain.usecase.GetIndexUseCase
import cloud.hendra.petshop.domain.usecase.RefreshUseCase
import org.koin.dsl.module

val repositoryModule = module {
    single<IndexRepository> { IndexRepositoryImpl(get()) }
    single { GetIndexUseCase(get()) }
    single<RefreshRepository> { RefreshRepositoryImpl(get()) }
    single { RefreshUseCase(get()) }
    single<ProtectedRepository> { ProtectedRepositoryImpl(get()) }
}