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
    private Map<String, Integer> inventory = new HashMap<>();
    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }
    public synchronized boolean bookRoom(String type) {
        int available = inventory.getOrDefault(type, 0);
        if (available > 0) {
            inventory.put(type, available - 1);
            return true;
        }
        return false;
    }
    public Map<String, Integer> getInventory() {
        return inventory;
    }
}
class BookingProcessor extends Thread {
    private Reservation reservation;
    private RoomInventory inventory;
    private static Map<String, Integer> counters = new HashMap<>();
    public BookingProcessor(Reservation reservation, RoomInventory inventory) {
        this.reservation = reservation;
        this.inventory = inventory;
    }
    public void run() {
        synchronized (BookingProcessor.class) {
            boolean success = inventory.bookRoom(reservation.roomType);
            if (success) {
                int count = counters.getOrDefault(reservation.roomType, 0) + 1;
                counters.put(reservation.roomType, count);
                String roomId = reservation.roomType + "-" + count;
                System.out.println("Booking confirmed for Guest: "
                        + reservation.guestName + ", Room ID: " + roomId);
            } else {
                System.out.println("Booking failed for Guest: "
                        + reservation.guestName + " (No rooms available)");
            }
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Concurrent Booking Simulation");
        RoomInventory inventory = new RoomInventory();
        Thread t1 = new BookingProcessor(new Reservation("Abhi", "Single"), inventory);
        Thread t2 = new BookingProcessor(new Reservation("Vanmathi", "Double"), inventory);
        Thread t3 = new BookingProcessor(new Reservation("Kural", "Suite"), inventory);
        Thread t4 = new BookingProcessor(new Reservation("Subha", "Single"), inventory);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("\nRemaining Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.getInventory().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
