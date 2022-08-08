import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] theatreSize = getTheatreSizeFromInput(scanner);
        Seat[][] cinemaArray = initializeCinemaArray(theatreSize[0], theatreSize[1]);

        run(cinemaArray, scanner);
    }

    private static void run(Seat[][] cinemaArray, Scanner scanner) {
        while (true) {
            System.out.println("\n1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");

            switch (scanner.nextInt()) {
                case 1 -> printCinemaArray(cinemaArray);
                case 2 -> {
                    while (true){
                        int[] inputSeatRowAndCols = getSeatRowAndColumnFromInput(scanner);
                        if(buyTicket(cinemaArray, inputSeatRowAndCols[0], inputSeatRowAndCols[1])){
                            break;
                        }
                    }
                }
                case 3 -> printStatistics(cinemaArray);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Unknown action");
            }

        }

    }

    private static void printStatistics(Seat[][] cinemaArray) {

        int[] statistics = calculateStatistics(cinemaArray);

        System.out.println("\nNumber of purchased tickets: " + statistics[0]);

        double percentage = (double)statistics[0] * 100.00 / (double)(cinemaArray.length * cinemaArray[0].length);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + statistics[1]);
        System.out.println("Total income: $" + statistics[2]);
    }

    private static int[] calculateStatistics(Seat[][] cinemaArray){

        int totalPurchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        for (Seat[] row : cinemaArray){
            for (Seat seat: row) {
                totalIncome += seat.getPrice();
                if (seat.isSold()){
                    totalPurchasedTickets++;
                    currentIncome += seat.getPrice();
                }
            }
        }
        return new int[]{totalPurchasedTickets, currentIncome, totalIncome};
    }

    private static boolean buyTicket(Seat[][] cinemaArray, int rowNumber, int columnNumber){
        if (rowNumber > cinemaArray.length || columnNumber > cinemaArray[0].length) {
            System.out.println("\nWrong input!");
            return false;
        }
        else {
            if (cinemaArray[rowNumber-1][columnNumber-1].isSold()){
                System.out.println("\nThat ticket has already been purchased!");
                return false;
            }
            else {
                cinemaArray[rowNumber-1][columnNumber-1].setSold(true);
                System.out.println("Ticket price: $" + cinemaArray[rowNumber-1][columnNumber-1].getPrice());
                return true;
            }
        }

    }

    private static int[] getTheatreSizeFromInput(Scanner scanner){
        int[] result = new int[2];
        System.out.println("Enter the number of rows:");
            result[0] = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
            result[1] = scanner.nextInt();
        return result;
    }

    private static int[] getSeatRowAndColumnFromInput(Scanner scanner){
        int[] result = new int[2];
        System.out.println("\nEnter a row number:");
        result[0] = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        result[1] = scanner.nextInt();
        return result;
    }

    private static Seat[][] initializeCinemaArray(int x, int y) {
        Seat[][] cinemaArray = new Seat[x][y];
        boolean isLargeTheater = isLargeTheater(x, y);

        if (isLargeTheater) {
            int frontHalf = cinemaArray.length / 2;

            for (int i = 0; i < cinemaArray.length; i++) {
                for (int j = 0; j < cinemaArray[0].length; j++) {
                    if (i < frontHalf){
                        cinemaArray[i][j] = new Seat(i+1, y+1, 10);
                    }
                    else {
                        cinemaArray[i][j] = new Seat(i+1, y+1, 8);
                    }
                }
            }
        }
        else{
            for (int i = 0; i < cinemaArray.length; i++) {
                for (int j = 0; j < cinemaArray[0].length; j++) {
                    cinemaArray[i][j] = new Seat(i+1, y+1, 10);
                }
            }

        }
        return cinemaArray;
    }

    private static boolean isLargeTheater(int x, int y) {
        return x * y > 60;
    }


    private static void printCinemaArray(Seat[][] cinemaArray){

        System.out.println("Cinema:");

        for (int i = 0; i < cinemaArray.length + 1; i++) {
            for (int j = 0; j < cinemaArray[0].length + 1; j++) {
                if (i >= 1 && j >= 1) {
                    System.out.print(cinemaArray[i-1][j-1]);
                    System.out.print(" ");
                }
                else {
                    if (i == 0 && j == 0) {
                        System.out.print(" ");
                    }
                    else{
                        System.out.print(i+j);
                    }
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

}
