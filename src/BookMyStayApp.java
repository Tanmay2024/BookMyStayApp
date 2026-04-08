import java.util.*;
class BookingValidator {
    private static final Set<String> validRoomTypes =
            new HashSet<>(Arrays.asList("Single", "Double", "Suite"));
    public static void validate(String guestName, String roomType) throws Exception {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new Exception("Guest name cannot be empty.");
        }
        if (!validRoomTypes.contains(roomType)) {
            throw new Exception("Invalid room type selected.");
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Booking Validation");
            System.out.print("Enter guest name: ");
            String name = sc.nextLine();
            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = sc.nextLine();
            BookingValidator.validate(name, roomType);
            System.out.println("Booking input is valid. Proceeding...");
        } catch (Exception e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
        sc.close();
    }
}
