package mealKitSubscribtion;
import java.util.*;

public class VegPlan extends Plan {
    private boolean organicOnly;

    public VegPlan(int planId, String name, int mealsPerWeek, double pricePerMeal, List<String> cuisinePrefs) {
        super(planId, name, mealsPerWeek, pricePerMeal, cuisinePrefs);
        this.organicOnly = false;
        this.baseDeliveryFee = 40.0;
    }

    public void setOrganicOnly(boolean val) { 
    	this.organicOnly = val; 
    }
    public boolean isOrganicOnly() { 
    	return organicOnly; 
    }

    @Override
    public double computeTotal(Order order) {
        double subtotal = 0;
        for (Meal m : order.getSelectedMeals()) subtotal += m.getPrice();
        subtotal -= order.getDiscount();
        if (order.getSelectedMeals().size() >= mealsPerWeek) subtotal *= 0.98;
        subtotal += getDeliveryFee();
        return subtotal;
    }
}
