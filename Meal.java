package mealKitSubscribtion;
public class Meal {
    private int id;
    private String name;
    private String cuisine;
    private double price;
    private boolean vegetarian;

    public Meal(int id, String name, String cuisine, double price, boolean vegetarian) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.price = price;
        this.vegetarian = vegetarian;
    }

    public int getId() { 
    	return id; 
    }
    public String getName() { 
    	return name; 
    }
    public String getCuisine() { 
    	return cuisine; 
    }
    public double getPrice() { 
    	return price; 
    }
    public boolean isVegetarian() { 
    	return vegetarian;
    }

    @Override
    public String toString() {
        return String.format("Meal[%d - %s - %.2f]", id, name, price);
    }
}

