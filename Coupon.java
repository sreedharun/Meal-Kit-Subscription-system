package mealKitSubscribtion;

public class Coupon {
    public enum Type { PERCENTAGE, FLAT }
    private String code;
    private Type type;
    private double value;

    public Coupon(String code, Type type, double value) {
        this.code = code;
        this.type = type;
        this.value = value;
    }

    public String getCode() { 
    	return code; 
    }
    public Type getType() { 
    	return type; 
    }
    public double getValue() { 
    	return value; 
    }
}
