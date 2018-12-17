package app.repbulic.order.orderrepublic.models;

import android.util.Log;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String orderTitle;
    private String userEmail;
    private String address;
    private String status;
    private String specialGuidelines;
    private Long receivedTime;
    private Long doneTime;
    private ArrayList<Food> foods;
    private ArrayList<Integer> quantities;
    private String totalPrice;
    private String userId;

    public Order() {
    }


    public Order(String orderId, String orderTitle, String userEmail, String address, String status, String specialGuidelines, Long receivedTime, Long doneTime, ArrayList<Food> foods, ArrayList<Integer> quantities, String userId) {
        this.orderId = orderId;
        this.orderTitle = orderTitle;
        this.userEmail = userEmail;
        this.address = address;
        this.status = status;
        this.specialGuidelines = specialGuidelines;
        this.receivedTime = receivedTime;
        this.doneTime = doneTime;
        this.foods = foods;
        this.quantities = quantities;
        this.userId = userId;
        this.totalPrice = calculateTotal();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Long receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Long getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Long doneTime) {
        this.doneTime = doneTime;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(ArrayList<Integer> quantities) {
        this.quantities = quantities;
    }

    public String getSpecialGuidelines() {
        return specialGuidelines;
    }

    public void setSpecialGuidelines(String specialGuidelines) {
        this.specialGuidelines = specialGuidelines;
    }

    public static ArrayList<Order> getDefaults() {
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Integer> quan = new ArrayList<>();
        quan.add(2);
        quan.add(1);
        Long a = Long.valueOf(12312), b = Long.valueOf(232143432);
        Order order = new Order(" ",
                "to Rahim",
                "mail@mail.ru",
                "Ziyolilar 9, M. Ulugbek, Tashkent",
                "received",
                "knock the door",
                a,
                b,
                Food.getDefaults(),
                quan,
                "-LTq1uzUTmvBLkR1H-Cq");
        orders.add(order);
        //order.setOrderId(" ");

        return orders;
    }

    public String calculateTotal() {
        String total;
        int totalInt = 0;
        for (int i = 0; i < this.getQuantities().size(); i++) {
            totalInt += (Integer.parseInt(this.getFoods().get(i).getPrice())) * (this.getQuantities().get(i));
        }
        total = String.valueOf(totalInt);
        return total;
    }

    public void logger() {
        Log.d("order", this.getOrderId());
        Log.d("order", this.getOrderTitle());
        Log.d("order", this.getSpecialGuidelines());
        Log.d("order", this.getAddress());
        Log.d("order", this.getStatus());
        Log.d("order", this.getTotalPrice());
        Log.d("order", String.valueOf(this.getQuantities()));
        Log.d("order", this.getUserEmail());
        //Log.d("order", this.getUserId());
    }
}
