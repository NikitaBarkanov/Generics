class ChatService {

    val chats = mutableMapOf<Int, Chat>()

    fun addMessage(
        userId: Int,
        deleted: Boolean,
        message: Message
    ) {
        /*       if (chats.containsKey(userId)){
            chats[userId]?.messages?.plusAssign (message)
        }
        else{
            chats[userId] = Chat(mutableListOf(message))
        }*/
        chats.getOrPut(userId) { Chat(mutableListOf()) }.messages += message
    }

    fun deleteChat(userId: Int) {
        chats.remove(userId)
    }

    fun deleteMessage(userId: Int, message: Message) {
        chats[userId]?.messages?.remove(message)
        if (chats[userId]?.messages?.isEmpty() == true) {
            deleteChat(userId)
        }
    }

    fun getChats(userId: Int, message: Message): List<Chat> {
        return chats.values
            .filter { it.ownerId == userId && it.deleted }
            .filter {
                it.messages.size >= 1 && it.messages.any {message -> !message.deleted
            }}
            .map { chat: Chat -> chat.copy(messages = chat.messages.filter { !it.deleted }.toMutableList()) }
    }
}