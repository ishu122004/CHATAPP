import java.util.*;

public enum ChatRoomManager {
    INSTANCE;

    private final Map<String, ChatRoom> rooms = new HashMap<>();

    public ChatRoom addRoom(String roomId) {
        if (rooms.containsKey(roomId)) throw new IllegalArgumentException("Room already exists");
        ChatRoom room = new ChatRoom(roomId);
        rooms.put(roomId, room);
        return room;
    }

    public void deleteRoom(String roomId) {
        rooms.remove(roomId);
    }

    public ChatRoom getRoom(String roomId) {
        return rooms.get(roomId);
    }

    public List<String> listRooms() {
        return new ArrayList<>(rooms.keySet());
    }
}

