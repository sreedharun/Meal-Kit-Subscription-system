package mealKitSubscribtion;
import java.util.*;

public class NonVegPlan extends Plan {
    private boolean freeProteinBoost;

    public NonVegPlan(int planId, String name, int mealsPerWeek, double pricePerMeal, List<String> cuisinePrefs) {
        super(planId, name, mealsPerWeek, pricePerMeal, cuisinePrefs);
        this.freeProteinBoost = true;
        this.baseDeliveryFee = 60.0;
    }

    public void setFreeProteinBoost(boolean val) { 
    	this.freeProteinBoost = val; 
    }
    public boolean hasFreeProteinBoost() { 
    	return freeProteinBoost; 
    }

    @Override
    public double computeTotal(Order order) {
        double subtotal = 0;
        for (Meal m : order.getSelectedMeals()) {
            subtotal += m.getPrice();
            if (m.getName().toLowerCase().contains("steak") || m.getName().toLowerCase().contains("chicken"))
                subtotal += 20;
        }
        subtotal -= order.getDiscount();
        subtotal += getDeliveryFee();
        return subtotal;
    }
}

