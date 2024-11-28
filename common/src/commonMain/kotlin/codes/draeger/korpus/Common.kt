package codes.draeger.korpus

import kotlinx.coroutines.flow.Flow
import kotlinx.rpc.RemoteService
import kotlinx.rpc.annotations.Rpc
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val address: String,
    val lastName: String,
)

@Rpc
interface MyService : RemoteService {
    suspend fun hello(user: String, userData: UserData): String

    suspend fun subscribeToNews(): Flow<String>
}