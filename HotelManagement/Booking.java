

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Booking {


    // Stores list of rooms
    private ArrayList<Rooms> roomList;

    // Stores list of room numbers
    private ArrayList<Integer> bookedRoomNumbers;

    public Booking() {
        this.roomList = new ArrayList<>();
        this.bookedRoomNumbers = new ArrayList<>();
    }


    //Adds new room booking. Promps the user for room details, customer info and calculates total price, and generates receipt
    public void addRoomBooking() {
        while (true) {
            try {
                int roomNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter room number."));

                // Validate room number must be 3 digits only
                if (!(roomNumber >= 100 && roomNumber <= 999)) {
                    JOptionPane.showMessageDialog(null, "Enter a valid 3-digit room number.");
                    continue;
                }

                //Checks if room number already exist
                if (bookedRoomNumbers.contains(roomNumber)) {
                    JOptionPane.showMessageDialog(null, "Room number already exists.");
                    return;
                }

                //Prompt the user to select riin type
                String options = "Single\nDouble\nTriple\nFamily";
                String roomType = JOptionPane.showInputDialog(options + "\nEnter room type:").toLowerCase();

                //Ask number of nights to stay
                int nights = Integer.parseInt(JOptionPane.showInputDialog(null, "How many nights do you want to stay?"));

                //Create and Initialize room object
                Rooms room = new Rooms(roomNumber, roomType, roomPrice(roomType,nights));
                bookedRoomNumbers.add(roomNumber);

                //Prompt for user information
                String customerName = JOptionPane.showInputDialog(null, "Enter customer name.");
                int contactNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter contact number."));
                Customer customer = new Customer(customerName, contactNumber);

                //Add customer to the room and save  to the room list
                roomList.add(room);
                room.addCustomer(customer);
                JOptionPane.showMessageDialog(null, "Customer added to room successfully!");

                //Generate receipts
                String receipt = generateReceipt(roomNumber, customerName, contactNumber, roomType, nights, room.getTotalRoomPrice());
                JOptionPane.showMessageDialog(null, receipt);
                return;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter a valid number.");
            }
        }
    }


    // roomType Room type the type of room
    //nigts The number of nights to stay
    // return the total room price, after multiplying the number of nights and the roomPrice
    public double roomPrice(String roomType, int nights){
        double roomPrice = 0;
        return switch (roomType) {
            case "single" ->roomPrice+=2000 * nights;
            case "double" -> roomPrice+=3500 * nights;
            case "triple" -> roomPrice+=4500 * nights;
            case "family"-> roomPrice+=5000 * nights;
            default -> 0.0;
        };
    }

    //Remove a booking room by asking the room number
    public void removeRoomBooking() {

        while (true) {
            try {
                int roomNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter room number."));
                if (!(roomNumber >= 100 && roomNumber <= 999)) {

                    //Validate room number must be 3 digits
                    JOptionPane.showMessageDialog(null, "Enter a valid 3-digit room number.");
                    continue;
                }

                //Search for the room and remove it if  the room number found
                for (Rooms room : roomList) {
                    if (room.getRoomNum() == roomNumber) {
                        roomList.remove(room);
                        bookedRoomNumbers.remove(Integer.valueOf(roomNumber));
                        JOptionPane.showMessageDialog(null, "Room removed successfully.");
                        return;
                    }
                }

                //Notify if the room number not found
                JOptionPane.showMessageDialog(null, "Room number not found.");
                return;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter a valid number.");
            }

        }
    }

    //Generate a script for the room booking
    public String generateReceipt(int roomNumber, String customerName, int contactNumber, String roomType, int nights, double totalPrice) {
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.toString();

        return String.format("""
                            Date Check-In: %s
                            Room Number: %d
                            Customer Name: %s
                            Contact Number: %d
                            Room Type: %s
                            Nights of Stay: %d
                            Total Price: $%.2f
                            """, date, roomNumber, customerName, contactNumber, roomType, nights, totalPrice);
    } 

    /**
     * Edits the details of an existing room booking. Allows updating room type, customer information, and nights of stay.
     */

    public void editRoomBooking() {
        while (true) {
            try {
                int roomNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter room number."));
                if (!(roomNumber >= 100 && roomNumber <= 999)) {
                    JOptionPane.showMessageDialog(null, "Enter a valid 3-digit room number.");
                    continue;
                }
                
                //Searchin for specified room num and prompting the user to Choose what specific he/she wants to edit
                for (Rooms room : roomList) {
                    if (room.getRoomNum() == roomNumber) {
                        while (true) {
                            String choice = JOptionPane.showInputDialog("""
                                    What would you like to edit?
                                    1. Room Type
                                    2. Customer Name
                                    3. Contact Number
                                    4. Nights of Stay
                                    5. Exit
                                    Enter your choice (1-5):""");


                            String message = "";         
    
                            switch (choice) {
                                case "1" -> {
                                    String newRoomType = JOptionPane.showInputDialog("Enter new room type:").toLowerCase();
                                    room.setRoomType(newRoomType);
                                    JOptionPane.showMessageDialog(null, "Room type updated successfully.");
                                    message = "Room type: " + newRoomType;
                                }
                                case "2" -> {
                                    for (Customer customer : room.getCustomers()) {
                                        String newCustomerName = JOptionPane.showInputDialog("Enter new customer name:");
                                        customer.setName(newCustomerName);
                                        JOptionPane.showMessageDialog(null, "Customer name updated successfully.");
                                        message = "Customer name: " + newCustomerName;
                                    }
                                }
                                case "3" -> {
                                    for (Customer customer : room.getCustomers()) {
                                        int newContactNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter new contact number:"));
                                        customer.setContact(newContactNumber);
                                        JOptionPane.showMessageDialog(null, "Contact number updated successfully.");
                                        message = "Contact number: " + newContactNumber;
                                    }
                                }
                                case "4" -> {
                                    int newNights = Integer.parseInt(JOptionPane.showInputDialog("Enter new number of nights to stay:"));
                                    double newTotalPrice = roomPrice(room.getRoomType(), newNights);
                                    room.setTotalRoomPrice(newTotalPrice);
                                    JOptionPane.showMessageDialog(null, "Number of nights and total price updated successfully.");
                                    message = "Number of nights: " + newNights + "\nTotal price: $ " + newTotalPrice;
                                }
                                case "5" -> {
                                    JOptionPane.showMessageDialog(null, "Exiting edit menu.");
                                    return; 
                                }
                                default -> JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                            }
                            JOptionPane.showMessageDialog(null, "Summary of changes:\n" + message);
                        }
                    }
                }
    
                JOptionPane.showMessageDialog(null, "Room number not found.");
                return;
    
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Enter only numeric values.");
            }
        }
    }
    

    /**
     * Displays all room bookings and their respective customer information.
     */
    public void displayBookings() {

        if (roomList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No bookings available.");
            return;
        }

        StringBuilder bookingDetails = new StringBuilder();

        for (Rooms room : roomList) {

            bookingDetails.append("Room Number: ").append(room.getRoomNum()).append("\n")
                    .append("Room Type: ").append(room.getRoomType()).append("\n")
                    .append("\nCustomers for this room:\n");

            if (room.getCustomers().isEmpty()) {
                bookingDetails.append("No customers booked for this room.\n");
            } else {

                for (Customer customer : room.getCustomers()) {
                    bookingDetails.append("Name: ").append(customer.getName()).append("\n")
                            .append("Contact: ").append(customer.getContact()).append("\n");
                }
            }

            bookingDetails.append("<-------------------------------------->\n");
        }

        JOptionPane.showMessageDialog(null, bookingDetails.toString());
    }

}
