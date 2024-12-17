
import javax.swing.JOptionPane;

public class HotelManagement {

    public static void main(String[] args) {
        Booking booking = new Booking();

        while (true) {

            String options = "1. Add Booking \n2. Remove Booking\n3. Edit Booking \n4. View Bookings \n 5. Exit ";
            String input = JOptionPane.showInputDialog(options + "\nEnter Option:");

            int customerOptions;

            try {
                customerOptions = Integer.parseInt(input);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                continue;
            }

            switch (customerOptions) {
                case 1:
                    booking.addRoomBooking();
                    break;
                case 2:
                    booking.removeRoomBooking();
                    break;
                case 3:
                    booking.editRoomBooking();
                    break;
                case 4:
                    booking.displayBookings();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    System.exit(0);
                default:
                    throw new AssertionError();

            }
        }
    }
}

