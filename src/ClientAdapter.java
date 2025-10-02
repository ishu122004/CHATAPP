public interface ClientAdapter {
    String getUserId();
    void send(Message message) throws Exception;
    void close();
}


