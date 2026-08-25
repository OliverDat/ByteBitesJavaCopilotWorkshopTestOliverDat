package dk.zealand;

import java.util.Scanner;

public class Main {

    private record Dish(String name, int price) {
    }

    private static final Dish[] DISHES = {
            new Dish("Festivalburger", 59),
            new Dish("Sprøde fritter", 35),
            new Dish("Vegansk bowl", 65)
    };

    private static final OrderManager orderManager = new OrderManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showDishes();
                case "2" -> createOrder(scanner);
                case "0" -> running = false;
                default -> System.out.println(
                        "Ugyldigt valg. Vælg 0, 1 eller 2."
                );
            }
        }

        System.out.println("Programmet er afsluttet.");
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Vis retter");
        System.out.println("2. Opret bestilling");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }

    private static void showDishes() {
        System.out.println("Retter:");

        for (int i = 0; i < DISHES.length; i++) {
            Dish dish = DISHES[i];
            System.out.printf("%d. %s - %d kr.%n", i + 1, dish.name(), dish.price());
        }
    }

    private static void createOrder(Scanner scanner) {
        if (orderManager.getOrderCount() >= 10) {
            System.out.println("Maksimalt antal bestillinger nået. Kan ikke oprette flere.");
            return;
        }

        System.out.println();
        showDishes();
        System.out.print("Vælg ret (1-3): ");
        String dishChoice = scanner.nextLine().trim();

        int dishIndex;
        try {
            dishIndex = Integer.parseInt(dishChoice);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt input. Vælg en ret med nummer 1-3.");
            return;
        }

        if (dishIndex < 1 || dishIndex > DISHES.length) {
            System.out.println("Ugyldigt valg. Vælg en ret med nummer 1-3.");
            return;
        }

        System.out.print("Antal: ");
        String quantityStr = scanner.nextLine().trim();

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt input. Antal skal være et tal.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Ugyldigt antal. Antal skal være større end 0.");
            return;
        }

        Dish selectedDish = DISHES[dishIndex - 1];
        Order order = orderManager.createOrder(selectedDish.name(), quantity);

        if (order != null) {
            System.out.println("✓ " + order);
        } else {
            System.out.println("Bestillingen kunne ikke oprettes.");
        }
    }
}
