import java.util.HashMap;
import java.util.Map;
class Room {
    private String type;
    private int beds;
    private int size;
    private double price;
    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }
    public void displayDetails(int availability) {
        System.out.println(type + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + availability);
        System.out.println();
    }
}
class RoomInventory {
    private HashMap<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
    public Map<String, Integer> getInventory() {
        return inventory;
    }
}
class RoomSearchService {
    public void searchRooms(RoomInventory inventory, Map<String, Room> rooms) {
        System.out.println("Room Search\n");
        for (String type : rooms.keySet()) {
            int available = inventory.getAvailability(type);
            if (available > 0) {
                rooms.get(type).displayDetails(available);
            }
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        Map<String, Room> rooms = new HashMap<>();
        rooms.put("Single Room", new Room("Single Room", 1, 250, 1500.0));
        rooms.put("Double Room", new Room("Double Room", 2, 400, 2500.0));
        rooms.put("Suite Room", new Room("Suite Room", 3, 750, 5000.0));
        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchRooms(inventory, rooms);
    }
}
