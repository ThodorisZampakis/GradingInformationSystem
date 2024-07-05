
import java.util.HashMap;

public class AppointmentManager {
    private static AppointmentManager instance;
    private HashMap<String, Boolean> bookedSlots;

    private AppointmentManager() {
        bookedSlots = new HashMap<>();
    }

    public static synchronized AppointmentManager getInstance() {
        if (instance == null) {
            instance = new AppointmentManager();
        }
        return instance;
    }

    public boolean isSlotBooked(String slot) {
        return bookedSlots.getOrDefault(slot, false);
    }

    public void bookSlot(String slot) {
        bookedSlots.put(slot, true);
    }

    public void cancelSlot(String slot) {
        bookedSlots.put(slot, false);
    }
}
