import java.util.Scanner;

/**
 * Menu-driven CLI for Chat Room App.
 */
public class Main {
    public static void main(String[] args) {
        ChatRoomManager manager = ChatRoomManager.INSTANCE;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        System.out.println("================================");
        System.out.println("   Welcome to Chat Room App    ");
        System.out.println("================================");

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Room");
            System.out.println("2. Delete Room");
            System.out.println("3. Join Room");
            System.out.println("4. Send Message in Room");
            System.out.println("5. List Rooms");
            System.out.println("6. List Users in a Room");
            System.out.println("7. View History");
            System.out.println("8. Exit");
            System.out.print("> ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1": // Add Room
                        System.out.print("Enter Room ID: ");
                        String roomId = sc.nextLine();
                        manager.addRoom(roomId);
                        System.out.println("Room created: " + roomId);
                        break;

                    case "2": // Delete Room
                        System.out.print("Enter Room ID to delete: ");
                        String delId = sc.nextLine();
                        manager.deleteRoom(delId);
                        System.out.println("Room deleted: " + delId);
                        break;

                    case "3": // Join Room
                        System.out.print("Enter Room ID: ");
                        String joinRoom = sc.nextLine();
                        ChatRoom room = manager.getRoom(joinRoom);
                        if (room == null) { 
                            System.out.println("Room not found"); 
                            break; 
                        }
                        System.out.print("Enter your name: ");
                        String user = sc.nextLine();
                        ClientAdapter adapter = new InMemoryClientAdapter(user, msg -> System.out.println(msg));
                        room.join(adapter);
                        System.out.println(user + " joined " + joinRoom);
                        break;

                    case "4": // Send Message
                        System.out.print("Enter Room ID: ");
                        String sendRoom = sc.nextLine();
                        ChatRoom r = manager.getRoom(sendRoom);
                        if (r == null) { 
                            System.out.println("Room not found"); 
                            break; 
                        }
                        System.out.print("From: ");
                        String from = sc.nextLine();
                        System.out.print("Message: ");
                        String text = sc.nextLine();
                        Message msg = Message.of(from, sendRoom, text);
                        r.sendMessage(msg);
                        break;

                    case "5": // List Rooms
                        System.out.println("Rooms: " + manager.listRooms());
                        break;

                    case "6": // List Users
                        System.out.print("Enter Room ID: ");
                        String listRoom = sc.nextLine();
                        ChatRoom lr = manager.getRoom(listRoom);
                        if (lr == null) { 
                            System.out.println("Room not found"); 
                            break; 
                        }
                        System.out.println("Users in " + listRoom + ": " + lr.getActiveUsers());
                        break;

                    case "7": // View History
                        System.out.print("Enter Room ID: ");
                        String histRoom = sc.nextLine();
                        ChatRoom hr = manager.getRoom(histRoom);
                        if (hr == null) { 
                            System.out.println("Room not found"); 
                            break; 
                        }
                        System.out.println("History in " + histRoom + ":");
                        hr.getHistory().forEach(System.out::println);
                        break;

                    case "8": // Exit
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice! Please enter 1–8.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
        System.out.println("Bye 👋");
    }
}




