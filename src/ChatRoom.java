import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatRoom {
    private final String roomId;
    private final Map<String, ClientAdapter> users = new HashMap<>();
    private final List<Message> history = new CopyOnWriteArrayList<>();
    private final List<MessageListener> listeners = new ArrayList<>();

    public ChatRoom(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() { return roomId; }

    public void join(ClientAdapter client) {
        users.put(client.getUserId(), client);
        broadcast(Message.of("SYSTEM", roomId, client.getUserId() + " joined the room"));
    }

    public void leave(String userId) {
        users.remove(userId);
        broadcast(Message.of("SYSTEM", roomId, userId + " left the room"));
    }

    public void sendMessage(Message message) {
        history.add(message);
        users.values().forEach(u -> {
            try { u.send(message); } catch (Exception ignored) {}
        });
        listeners.forEach(l -> l.onMessageDelivered(message));
    }

    public void broadcast(Message message) {
        sendMessage(message);
    }

    public void addListener(MessageListener l) { listeners.add(l); }

    public List<String> getActiveUsers() {
        return new ArrayList<>(users.keySet());
    }

    public List<Message> getHistory() {
        return new ArrayList<>(history);
    }
}

