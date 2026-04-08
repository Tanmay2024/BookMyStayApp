import java.util.*;
class AddOnService {
    private String name;
    private double cost;
    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
    public double getCost() {
        return cost;
    }
    public String getName() {
        return name;
    }
}
class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }
    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = serviceMap.get(reservationId);
        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }
        return total;
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        String reservationId = "Single-1";
        AddOnServiceManager manager = new AddOnServiceManager();
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1000));
        double totalCost = manager.calculateTotalCost(reservationId);
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}
