import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Car {
    String brand;
    String model;
    double dailyRentalPrice;
    boolean available;

    public Car(String brand, String model, double dailyRentalPrice, boolean available) {
        this.brand = brand;
        this.model = model;
        this.dailyRentalPrice = dailyRentalPrice;
        this.available = available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class CarRentalSystem {
    private static final String DATABASE_FILE = "carDatabase.txt";
    private ArrayList<Car> carList;

    public CarRentalSystem() {
        carList = loadDatabase();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Customer");
            System.out.println("2. Seller");
            System.out.println("3. Return Car");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleCustomer();
                    break;
                case 2:
                    handleSeller();
                    break;
                case 3:
                    handleCarReturn();
                    break;
                case 4:
                    saveDatabase();
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAvailable Cars:");
        displayAvailableCars();

        System.out.print("Enter the index of the car you want to rent (or -1 to go back): ");
        int carIndex = scanner.nextInt();

        if (carIndex == -1) {
            return;
        }

        if (carIndex >= 0 && carIndex < carList.size() && carList.get(carIndex).available) {
            UserRegistration verify = new UserRegistration();
            boolean isVerified= verify.verifyUser();
            if(isVerified)
                rentCar(carIndex);
        } else {
            System.out.println("Invalid car selection or car not available for rent.");
        }
    }

    private void rentCar(int carIndex) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of days to rent: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number of days.");
            System.out.print("Number of days to rent: ");
            scanner.next();
        }

        int rentalDays = scanner.nextInt();



        Car selectedCar = carList.get(carIndex);
        selectedCar.setAvailable(false);

        double totalPrice = rentalDays * selectedCar.dailyRentalPrice;

        System.out.println("\n--- Rental  ---");
        System.out.println("Car Details: " + selectedCar.brand + " " + selectedCar.model);
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Total Price: ₹" + totalPrice);

        System.out.println("\nThank you for using our car rental system!");
    }

    private void handleSeller() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter details of the car to be added:");
        System.out.print("Brand: ");
        String brand = scanner.next();

        System.out.print("Model: ");
        String model = scanner.next();

        System.out.print("Daily Rental Price: ");
        double dailyRentalPrice = scanner.nextDouble();

        Car newCar = new Car(brand, model, dailyRentalPrice, true);
        carList.add(newCar);

        System.out.println("Car added successfully!");
    }

    private void handleCarReturn() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nCars currently rented:");
        displayRentedCars();

        System.out.print("Enter the index of the car to return (or -1 to go back): ");
        int carIndex = scanner.nextInt();

        if (carIndex == -1) {
            return;
        }

        if (carIndex >= 0 && carIndex < carList.size() && !carList.get(carIndex).available) {
            returnCar(carIndex);
        } else {
            System.out.println("Invalid car selection or car not rented.");
        }
    }

    private void returnCar(int carIndex) {
        Car returnedCar = carList.get(carIndex);
        returnedCar.setAvailable(true);

        System.out.println("Car returned successfully!");
    }

    private void displayAvailableCars() {
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            System.out.println(i + ". " + car.brand + " " + car.model +
                    " (Daily Rental Price: ₹" + car.dailyRentalPrice + ", Available: " + car.available + ")");
        }
    }

    private void displayRentedCars() {
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            if (!car.available) {
                System.out.println(i + ". " + car.brand + " " + car.model + " (Rented)");
            }
        }
    }

    private ArrayList<Car> loadDatabase() {
        ArrayList<Car> cars = new ArrayList<>();

        File databaseFile = new File(DATABASE_FILE);

        if (!databaseFile.exists()) {
            System.out.println("Database file not found. Creating a new database.");
            saveDatabase();
            return cars;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(databaseFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] carData = line.split(",");
                String brand = carData[0].trim();
                String model = carData[1].trim();
                double dailyRentalPrice = Double.parseDouble(carData[2].trim());
                boolean available = Boolean.parseBoolean(carData[3].trim());

                cars.add(new Car(brand, model, dailyRentalPrice, available));
            }
        } catch (IOException e) {
            System.out.println("Error loading database.");
        }

        if (cars.isEmpty()) {
            System.out.println("No cars found in the database.");
        }

        return cars;
    }

    private void saveDatabase() {
        File databaseFile = new File(DATABASE_FILE);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(databaseFile))) {
            for (Car car : carList) {
                writer.write(car.brand + "," + car.model + "," + car.dailyRentalPrice + "," + car.available);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving database.");
        }
    }
}

