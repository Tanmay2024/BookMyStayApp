import java.io.*;
import java.util.*;
class PersistenceService {
    private static final String FILE_NAME = "inventory.dat";
    public void save(Map<String, Integer> inventory) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            out.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving inventory.");
        }
    }
    public Map<String, Integer> load() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {
            return (Map<String, Integer>) in.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        PersistenceService service = new PersistenceService();
        System.out.println("System Recovery");
        Map<String, Integer> inventory = service.load();
        if (inventory == null) {
            System.out.println("No valid inventory data found. Starting fresh.\n");
            inventory = new HashMap<>();
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        service.save(inventory);
    }
}
