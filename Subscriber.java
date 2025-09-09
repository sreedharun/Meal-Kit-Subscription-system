package mealKitSubscribtion;

public class Subscriber {
    private int id;
    private String name;
    private String email;
    private String deliveryAddress;
    private Plan activePlan;
    private boolean active;

    public Subscriber(int id, String name, String email, String deliveryAddress, Plan activePlan) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        this.activePlan = activePlan;
        this.active = true;
    }

    public int getId() { 
    	return id; 
    }
    public String getName() {
    	return name; 
    }
    public String getEmail() { 
    	return email; 
    }
    public String getDeliveryAddress() { 
    	return deliveryAddress;
    }
    public Plan getActivePlan() { 
    	return activePlan; 
    }
    public boolean isActive() { 
    	return active; 
    }

    public void setActivePlan(Plan plan) { 
    	this.activePlan = plan; 
    }
    public void setDeliveryAddress(String addr) { 
    	this.deliveryAddress = addr; 
    }
    public void deactivate() { 
    	this.active = false; 
    }
    public void activate() { 
    	this.active = true; 
    }

    @Override
    public String toString() {
        return String.format("Subscriber[%d - %s]", id, name);
    }
}
