import java.util.*;
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();
    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }
    public void increment(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }
    public int getAvailability(String type) {
        return inventory.get(type);
    }
}
class CancellationService {
    private Set<String> allocatedRooms;
    private Stack<String> rollbackStack;
    private RoomInventory inventory;
    public CancellationService(Set<String> allocatedRooms, RoomInventory inventory) {
        this.allocatedRooms = allocatedRooms;
        this.inventory = inventory;
        this.rollbackStack = new Stack<>();
    }
    public void cancelBooking(String reservationId) {
        if (!allocatedRooms.contains(reservationId)) {
            System.out.println("Cancellation failed: Invalid reservation ID.");
            return;
        }
        String roomType = reservationId.split("-")[0];
        rollbackStack.push(reservationId);
        allocatedRooms.remove(reservationId);
        inventory.increment(roomType);
        System.out.println("Booking Cancellation");
        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
        System.out.println("\nRollback History (Most Recent First):");
        while (!rollbackStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + rollbackStack.pop());
        }
        System.out.println("\nUpdated " + roomType + " Room Availability: "
                + inventory.getAvailability(roomType));
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        Set<String> allocatedRooms = new HashSet<>();
        allocatedRooms.add("Single-1");
        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService(allocatedRooms, inventory);
        service.cancelBooking("Single-1");
    }
}
