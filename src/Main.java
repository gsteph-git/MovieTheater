import java.util.*;

public class Main {
    /* Do not change code below */
    public static int rows, seats, rowNum, seatNum, seatPrice, totalSeats, ticketPrice, currentIncome;
    public static int seatsPurchased = 0;

    public static void main(String[] args) {
        // Write your code here
        Scanner scan = new Scanner(System.in);
        int count = 1;

        System.out.println("Enter the number of rows: ");
        rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        seats = scan.nextInt();

        String [][] matrix = new String[rows+1][seats+1];
        matrix = createTheater(rows,seats,matrix);
        System.out.println();
        printArray(matrix);
        totalSeats = rows * seats;
        printMenu(rows, seats, matrix);
    }

    public static String[][] pickSeat(String [][] matrix) {
        System.out.println();
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            System.out.println("Enter a row number:");
            rowNum = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scan.nextInt();
            if ((rowNum > rows) || (seatNum > seats)) {
                System.out.println("Wrong input!");
                System.out.println();
            } else if (matrix[rowNum][seatNum] == "B ") {
               System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else {
                matrix[rowNum][seatNum] = "B ";
                valid = true;
            }
        }
        return matrix;
    }

    public static void printMenu(int rows, int seats, String[][] matrix) {
        System.out.println();
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int input = scan.nextInt();
        while (input != 0) {
            switch (input) {
                case 0:
                    input = 0;
                    break;
                case 1:
                    System.out.println();
                    printArray(matrix);
                    System.out.println();
                    System.out.println("1. Show the seats");
                    System.out.println("2. Buy a ticket");
                    System.out.println("3. Statistics");
                    System.out.println("0. Exit");
                    input = scan.nextInt();
                    break;
                case 2:

                    seatsPurchased++;
                    pickSeat(matrix);
                    ticketPrice(totalSeats, rows, rowNum);
                    System.out.println();
                    System.out.println("1. Show the seats");
                    System.out.println("2. Buy a ticket");
                    System.out.println("3. Statistics");
                    System.out.println("0. Exit");
                    input = scan.nextInt();
                    break;
                case 3:
//                    pickSeat(matrix);
//                    ticketPrice(totalSeats, rows, rowNum);
//                    System.out.println();
//                    System.out.println("1. Show the seats");
//                    System.out.println("2. Buy a ticket");
//                    System.out.println("3. Statistics");
//                    System.out.println("0. Exit");
//                    input = scan.nextInt();
                    System.out.println("Number of purchased tickets: " + seatsPurchased);
                    percentCap();
                    System.out.println();
                    System.out.println("Current income: $" + currentIncome);
                    totalIncome();
                    System.out.println();
                    System.out.println("1. Show the seats");
                    System.out.println("2. Buy a ticket");
                    System.out.println("3. Statistics");
                    System.out.println("0. Exit");
                    input = scan.nextInt();
                    break;
            }
        }
    }


    public static String[][] createTheater(int rows, int seats, String [][] theater) {

        for (int i = 0; i <= rows; i++) {

            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0) {
                    theater[i][j] = "  ";
                    //    System.out.print("  ");
                } else if (i == 0) {
                    theater[i][j] = j + " ";
                    //      System.out.print(j + " ");
                } else if (j == 0) {
                    theater[i][j] = i + " ";
                    //        System.out.println(i + " ");
                } else {
                    theater[i][j] = "S ";
                    //        System.out.println("S ");
                }
            }
        }
        return theater;
    }

    public static void printArray(String [][] matrix) {
        System.out.print("Cinema:");
        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
        }
        System.out.println();
    }

    public static int ticketPrice(int totalSeats, int row, int rowNum) {
        if (totalSeats <= 60 ) {
            ticketPrice = 10;
        } else if (rowNum <= row / 2) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        System.out.println();
        System.out.println("Ticket price: $" + ticketPrice);
        currentIncome += ticketPrice;
        return ticketPrice;
    }

    public static void totalIncome() {
        int income = 0;
        if (totalSeats <= 60) {
            income = totalSeats * 10;
        } else if (rows % 2 == 0){
            int firstHalfIncome = (totalSeats / 2) * 10;
            int secondHalfIncome = (totalSeats / 2) * 8;
            income = firstHalfIncome + secondHalfIncome;
        } else {
            int firstHalfIncome = ((rows / 2) * seats * 10);
            int secondHalfRows = (rows - (rows / 2));
            int secondHalfIncome = (secondHalfRows * seats * 8);
            income = firstHalfIncome + secondHalfIncome;
        }

        System.out.print("Total income: ");
        System.out.println("$" + income);
    }

    public static void percentCap() {
        System.out.printf("Percentage: %.2f%%", (double) seatsPurchased/ (double) totalSeats * 100);
    }
}