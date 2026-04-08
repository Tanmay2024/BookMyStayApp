import java.util.*;
class Reservation {
    private String guestName;
    private String roomType;
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
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }
    public List<Reservation> getAllBookings() {
        return history;
    }
}
class BookingReportService {
    public void generateReport(List<Reservation> bookings) {
        System.out.println("Booking History and Reporting\n");
        System.out.println("Booking History Report\n");
        for (Reservation r : bookings) {
            System.out.println(
                    "Guest: " + r.getGuestName() +
                            ", Room Type: " + r.getRoomType()
            );
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history.getAllBookings());
    }
}
