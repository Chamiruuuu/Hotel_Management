

import java.util.ArrayList;

public class Rooms {

    private int roomNum;
    private String roomType;
    private double totalRoomPrice;
    private ArrayList<Customer>customers;

    public Rooms(int roomNum, String roomType,double totalRoomPrice) {
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.totalRoomPrice = totalRoomPrice;
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public double getTotalRoomPrice(){
        return this.totalRoomPrice ;
    }

    public double setTotalRoomPrice(double totalRoomPrice){
        return  this.totalRoomPrice = totalRoomPrice;
    }

    public int getRoomNum(){
        return this.roomNum;
    }

    public String getRoomType(){
        return this.roomType;
    }

    public String setRoomType(String roomType){
        return this.roomType = roomType;
    }
    
    public ArrayList<Customer> getCustomers(){
        return this.customers;
    }

}