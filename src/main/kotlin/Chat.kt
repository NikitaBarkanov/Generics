data class Chat(val ownerId: Int, val otherId: Int, val messages: MutableList<Message>,var deleted: Boolean = true)
