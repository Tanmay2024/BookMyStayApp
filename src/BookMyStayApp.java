import java.util.*;
class Reservation {
    String guestName;
    String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}
class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();
    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
    public void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}
class BookingService {
    private Queue<Reservation> queue;
    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    public BookingService(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }
    public void processBookings() {
        System.out.println("Room Allocation Processing");
        while (!queue.isEmpty()) {
            Reservation request = queue.poll();
            String type = request.roomType;
            if (inventory.getAvailability(type) > 0) {
                int roomNumber = allocatedRooms.get(type).size() + 1;
                String roomId = type + "-" + roomNumber;
                allocatedRooms.get(type).add(roomId);
                inventory.decrement(type);
                System.out.println(
                        "Booking confirmed for Guest: "
                                + request.guestName
                                + ", Room ID: "
                                + roomId
                );
            } else {
                System.out.println(
                        "Booking failed for Guest: "
                                + request.guestName
                                + " (No rooms available)"
                );
            }
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        Queue<Reservation> requestQueue = new LinkedList<>();
        requestQueue.add(new Reservation("Abhi", "Single"));
        requestQueue.add(new Reservation("Subha", "Single"));
        requestQueue.add(new Reservation("Vanmathi", "Suite"));
        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(requestQueue, inventory);
        service.processBookings();
    }
}
