package cloud.hendra.petshop.utils.state

sealed class GuardState {
    object Authenticated : GuardState()
    object Unauthenticated : GuardState()
    object NeedsRefresh : GuardState()
}