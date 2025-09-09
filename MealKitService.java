package mealKitSubscribtion;
import java.time.LocalDate;
import java.util.*;

public class MealKitService {
    private static Map<Integer, Meal> menu = new HashMap<>();
    private Map<Integer, Order> orders = new HashMap<>();
    private int nextOrderId = 1000;

    public void generateSampleMenu() {
        addMealToMenu(new Meal(1, "Paneer Tikka", "Indian", 150.0, true));
        addMealToMenu(new Meal(2, "Grilled Chicken", "BBQ", 250.0, false));
        addMealToMenu(new Meal(3, "Veg Pasta", "Italian", 180.0, true));
        addMealToMenu(new Meal(4, "Steak & Mash", "Continental", 320.0, false));
        addMealToMenu(new Meal(5, "Fried Rice", "Chinese", 130.0, true));
    }

    public void addMealToMenu(Meal m) {
        menu.put(m.getId(), m);
    }

    public static Meal findMealById(int id) {
        return menu.get(id);
    }

    public Order createOrder(Subscriber s, int weekOfYear) {
        Order o = new Order(nextOrderId++, s, weekOfYear);
        orders.put(o.getOrderId(), o);
        return o;
    }

    public void applyCoupon(Order o, Coupon c) {
        if (o == null || c == null) return;
        double discount = 0.0;
        if (c.getType() == Coupon.Type.FLAT) discount = c.getValue();
        else if (c.getType() == Coupon.Type.PERCENTAGE) {
            double subtotal = 0;
            for (Meal m : o.getSelectedMeals()) subtotal += m.getPrice() * o.getPortionMultiplier(m);
            discount = subtotal * (c.getValue() / 100.0);
        }
        o.applyDiscount(discount);
    }

    public void scheduleDelivery(Order o, LocalDate date) {
        if (o != null) o.schedule(date);
    }

    public void dispatch(Order o) {
        if (o == null) return;
        Plan plan = o.getSubscriber().getActivePlan();
        double total = plan.computeTotal(o);
        o.setTotal(total);
        o.dispatch();
    }

    public void markDelivered(Order o) {
        if (o != null) o.markDelivered();
    }

    public void printWeeklyRevenue(int weekOfYear) {
        double revenue = 0;
        for (Order o : orders.values()) {
            if (o.getWeekOfYear() == weekOfYear && o.getStatus() == Order.Status.DELIVERED) revenue += o.getTotal();
        }
        System.out.printf("Total revenue for week %d = %.2f%n", weekOfYear, revenue);
    }

    public void printMealPopularity(int weekOfYear) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (Order o : orders.values()) {
            if (o.getWeekOfYear() != weekOfYear) continue;
            for (Meal m : o.getSelectedMeals()) counts.put(m.getId(), counts.getOrDefault(m.getId(), 0) + 1);
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(counts.entrySet());
        list.sort((a,b) -> b.getValue() - a.getValue());
        for (Map.Entry<Integer,Integer> e : list) {
            Meal m = menu.get(e.getKey());
            System.out.printf("%s -> %d orders%n", m.getName(), e.getValue());
        }
    }
}

