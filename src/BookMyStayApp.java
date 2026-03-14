import java.util.LinkedList;
import java.util.Queue;
class Reservation {
    String guestName;
    String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public String getGuestName() {
        return guestName;
    }
    public String getRoomType() {
        return roomType;
    }
}
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }
    public void processRequests() {
        System.out.println("Booking Request Queue");
        while (!queue.isEmpty()) {
            Reservation r = queue.poll();
            System.out.println(
                    "Processing booking for Guest: "
                            + r.getGuestName()
                            + ", Room Type: "
                            + r.getRoomType()
            );
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingRequestQueue requestQueue = new BookingRequestQueue();
        requestQueue.addRequest(new Reservation("Abhi", "Single"));
        requestQueue.addRequest(new Reservation("Subha", "Double"));
        requestQueue.addRequest(new Reservation("Vanmathi", "Suite"));
        requestQueue.processRequests();
    }
}
