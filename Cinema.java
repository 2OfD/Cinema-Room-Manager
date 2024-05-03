package cinema;

import java.util.Scanner;

public class Cinema {
    static String[][] plattegrond;
    static int ticketCounter = 0;

    static int currentIncome = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();


        plattegrond = new String[rows][seats];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                plattegrond[i][j] = "S";
//                plattegrond[i][j] = "°";
            }
        }

        printCinema(rows, seats);

        menu(rows, seats);

    }

    public static void menu(int rows, int seats) {
        Scanner scanner = new Scanner(System.in);

        int exit = 2;


        while (exit != 0) {

            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int select = scanner.nextInt();

            switch (select) {
                case 1:
                    printCinema(rows, seats);
                    break;
                case 2:
                    boolean gelukt = false;
                    while (gelukt == false) {
                        System.out.println("Enter a row number:");
                        int rowCoord = scanner.nextInt();
                        if (rowCoord > rows) {
                            System.out.println("Wrong input!");
                            continue;
                        }
                        System.out.println("Enter a seat number in that row:");
                        int seatCoord = Integer.parseInt(scanner.next());
                        if (seatCoord > seats) {
                            System.out.println("Wrong input!");
                            continue;
                        }
                        if (plattegrond[rowCoord - 1][seatCoord - 1].equals/*("°")*/("S")) {
                            plattegrond[rowCoord - 1][seatCoord - 1] = "B";
                        } else {
                            System.out.println("That ticket has already been purchased!");
                            continue;
                        }
                        System.out.println("Ticket price: $" + inkomenPerStoel(rows, seats, rowCoord));
                        ticketCounter++;
                        currentIncome += inkomenPerStoel(rows, seats, rowCoord);
                        gelukt = true;
                    }
                    break;
                case 3:
                    statistics(rows, seats);
                    break;
                case 0:
                    exit = 0;
                    break;
            }
        }


    }


    public static void statistics(int rows, int seats) {

        System.out.println("Number of purchased tickets: " + countPurchasedTickets());


        Double percentage = (countPurchasedTickets() * 100.0 / (rows * seats));
        System.out.printf("Percentage: %.2f%% \n", percentage);

        int omzet = currentIncome();
        System.out.println("Current income: $" + omzet);

        int inkomen = totalIncome(rows, seats);
        System.out.println("Total income: $" + inkomen);

    }

    static int countPurchasedTickets() {
        return ticketCounter;
    }

    static int currentIncome() {
        return currentIncome;
    }

    public static void printCinema(int rows, int seats) {

        System.out.println("Cinema:");
        System.out.print(" ");
        for (int j = 0; j < seats; j++) {
            System.out.print(" " + (j + 1));
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(plattegrond[i][j] + " ");
            }
            System.out.println();
        }

    }

    static int totalIncome(int rijen, int kolommen) {
        int totaal = 0;
        for (int i = 1; i <= rijen; i++) {
            for (int j = 1; j <= kolommen; j++) {
                totaal += inkomenPerStoel(rijen, kolommen, i);
            }
        }
        return totaal;
    }

    static int inkomenPerStoel(int rijen, int kolommen, int rowCoord) {

        int inkomst = 0;

        if (rijen * kolommen <= 60) {
            inkomst = 10;
        } else if (rijen * kolommen > 60) {
            if (rowCoord <= (rijen / 2)) {
                inkomst = 10;
            } else {
                inkomst = 8;
            }
        }
        return inkomst;
    }
}