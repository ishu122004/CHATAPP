import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Message {
    private final String id;
    private final String fromUser;
    private final String roomId;
    private final String payload;
    private final Instant sentAt;

    private Message(String id, String fromUser, String roomId, String payload, Instant sentAt) {
        this.id = id;
        this.fromUser = fromUser;
        this.roomId = roomId;
        this.payload = payload;
        this.sentAt = sentAt;
    }

    public static Message of(String fromUser, String roomId, String payload) {
        Objects.requireNonNull(fromUser, "fromUser required");
        Objects.requireNonNull(roomId, "roomId required");
        Objects.requireNonNull(payload, "payload required");
        String cleaned = payload.trim();
        if (cleaned.isEmpty()) throw new IllegalArgumentException("Message cannot be empty");
        return new Message(UUID.randomUUID().toString(), fromUser, roomId, cleaned, Instant.now());
    }

    @Override
    public String toString() {
        return String.format("[%s @ %s]: %s", fromUser, roomId, payload);
    }
}

