package mealKitSubscribtion;
import java.util.*;

public abstract class Plan {
    protected int planId;
    protected String name;
    protected int mealsPerWeek;
    protected double pricePerMeal;
    protected List<String> cuisinePrefs;
    protected double baseDeliveryFee = 50.0;

    public Plan(int planId, String name, int mealsPerWeek, double pricePerMeal, List<String> cuisinePrefs) {
        this.planId = planId;
        this.name = name;
        this.mealsPerWeek = mealsPerWeek;
        this.pricePerMeal = pricePerMeal;
        this.cuisinePrefs = new ArrayList<>(cuisinePrefs);
    }

    public int getPlanId() { 
    	return planId; 
    }
    public String getName() { 
    	return name; 
    }
    public int getMealsPerWeek() { 
    	return mealsPerWeek; 
    }
    public double getPricePerMeal() { 
    	return pricePerMeal; 
    }
    public List<String> getCuisinePrefs() { 
    	return Collections.unmodifiableList(cuisinePrefs); 
    }

    public double computeTotal(Order order) {
        double subtotal = 0;
        for (Meal m : order.getSelectedMeals()) subtotal += m.getPrice();
        subtotal -= order.getDiscount();
        subtotal += getDeliveryFee();
        return subtotal;
    }

    public double getDeliveryFee() {
        return baseDeliveryFee;
    }

    @Override
    public String toString() {
        return String.format("Plan[%d - %s]", planId, name);
    }
}

