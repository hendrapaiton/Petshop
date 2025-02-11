package cloud.hendra.petshop.data.module

import cloud.hendra.petshop.data.repository.IndexRepository
import cloud.hendra.petshop.data.repository.IndexRepositoryImpl
import cloud.hendra.petshop.domain.usecase.GetIndexUseCase
import org.koin.dsl.module

val repositoryModule = module {
    single<IndexRepository> { IndexRepositoryImpl(get()) }
    single { GetIndexUseCase(get()) }
}