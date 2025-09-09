package mealKitSubscribtion;
import java.time.LocalDate;
import java.util.*;

public class Order {
    public enum Status { CREATED, SCHEDULED, DISPATCHED, DELIVERED, CANCELLED }

    private int orderId;
    private Subscriber subscriber;
    private int weekOfYear;
    private List<Meal> selectedMeals;
    private Map<Integer, Double> portionMultiplier;
    private Status status;
    private double discount;
    private double total;
    private LocalDate scheduledDate;

    public Order(int orderId, Subscriber subscriber, int weekOfYear) {
        this.orderId = orderId;
        this.subscriber = subscriber;
        this.weekOfYear = weekOfYear;
        this.selectedMeals = new ArrayList<>();
        this.portionMultiplier = new HashMap<>();
        this.status = Status.CREATED;
        this.discount = 0.0;
        this.total = 0.0;
        this.scheduledDate = null;
    }

    public int getOrderId() { 
    	return orderId; 
    }
    public Subscriber getSubscriber() { 
    	return subscriber; 
    }
    public int getWeekOfYear() { 
    	return weekOfYear; 
    }
    public List<Meal> getSelectedMeals() { 
    	return Collections.unmodifiableList(selectedMeals); 
    }
    public Status getStatus() { 
    	return status; 
    }
    public double getDiscount() { 
    	return discount; 
    }
    public double getTotal() { 
    	return total;
    }
    public LocalDate getScheduledDate() { 
    	return scheduledDate; 
    }

    public void addMeal(int mealId) {
        Meal m = MealKitService.findMealById(mealId);
        if (m != null) {
            selectedMeals.add(m);
            portionMultiplier.put(m.getId(), 1.0);
        }
    }

    public void addMeal(int mealId, String portionSize) {
        Meal m = MealKitService.findMealById(mealId);
        if (m != null) {
            selectedMeals.add(m);
            double mult = 1.0;
            if (portionSize.equalsIgnoreCase("large") || portionSize.equalsIgnoreCase("family")) 
            	mult = 1.5;
            if (portionSize.equalsIgnoreCase("small")) 
            	mult = 0.8;
            portionMultiplier.put(m.getId(), mult);
        }
    }

    public void removeMeal(int mealId) {
        selectedMeals.removeIf(meal -> meal.getId() == mealId);
        portionMultiplier.remove(mealId);
    }

    public double getPortionMultiplier(Meal m) {
        return portionMultiplier.getOrDefault(m.getId(), 1.0);
    }

    public void applyDiscount(double amount) {
        if (amount >= 0) this.discount += amount;
    }

    public void schedule(LocalDate date) {
        if (status == Status.CREATED || status == Status.SCHEDULED) {
            this.scheduledDate = date;
            this.status = Status.SCHEDULED;
        }
    }

    public void dispatch() {
        if (status == Status.SCHEDULED) 
        	this.status = Status.DISPATCHED;
    }

    public void markDelivered() {
        if (status == Status.DISPATCHED) 
        	this.status = Status.DELIVERED;
    }

    public void cancel() {
        if (status == Status.CREATED || status == Status.SCHEDULED) 
        	this.status = Status.CANCELLED;
    }

    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return String.format("Order[%d - %s - %s - Meals:%d - Total:%.2f]",
                orderId, subscriber.getName(), status, selectedMeals.size(), total);
    }
}
