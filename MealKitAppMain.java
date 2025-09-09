package mealKitSubscribtion;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

public class MealKitAppMain {
    public static void main(String[] args) {
        MealKitService service = new MealKitService();

        service.generateSampleMenu();

        Plan vegPlan = new VegPlan(1, "Veg Delight", 3, 200.0, Arrays.asList("Indian", "Mediterranean"));
        Plan nonVegPlan = new NonVegPlan(2, "Protein Pack", 4, 300.0, Arrays.asList("Continental", "BBQ"));

        Subscriber s1 = new Subscriber(101, "Asha", "asha@example.com", "12 Green St", vegPlan);
        Subscriber s2 = new Subscriber(102, "Rohan", "rohan@example.com", "34 Blue Rd", nonVegPlan);

        int weekOfYear = LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

        Order o1 = service.createOrder(s1, weekOfYear);
        o1.addMeal(1);
        o1.addMeal(3, "Large");
        service.applyCoupon(o1, new Coupon("WELCOME10", Coupon.Type.PERCENTAGE, 10.0));

        Order o2 = service.createOrder(s2, weekOfYear);
        o2.addMeal(2);
        o2.addMeal(4);
        service.applyCoupon(o2, new Coupon("FLAT50", Coupon.Type.FLAT, 50.0));

        service.scheduleDelivery(o1, LocalDate.now().plusDays(1));
        service.scheduleDelivery(o2, LocalDate.now().plusDays(2));
        
        service.dispatch(o1);
        service.markDelivered(o1);

        service.dispatch(o2);
        service.markDelivered(o2);

        System.out.println("\n--- Weekly Revenue Report ---");
        service.printWeeklyRevenue(weekOfYear);

        System.out.println("\n--- Meal Popularity Report ---");
        service.printMealPopularity(weekOfYear);
    }
}
