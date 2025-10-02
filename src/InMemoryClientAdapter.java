import java.util.function.Consumer;

public class InMemoryClientAdapter implements ClientAdapter {
    private final String userId;
    private final Consumer<Message> onReceive;
    private boolean closed = false;

    public InMemoryClientAdapter(String userId, Consumer<Message> onReceive) {
        this.userId = userId;
        this.onReceive = onReceive;
    }

    @Override
    public String getUserId() { return userId; }

    @Override
    public void send(Message message) {
        if (closed) throw new IllegalStateException("adapter closed");
        onReceive.accept(message);
    }

    @Override
    public void close() { closed = true; }
}
