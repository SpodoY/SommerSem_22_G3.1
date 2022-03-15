package at.ac.fhcampuswien;

import at.ac.fhcampuswien.gui.StartScreen;
import javafx.stage.Stage;

import java.util.Scanner;

public class Menu {

    private AppController controller;
    private static final String INVALID_INPUT_MESSAGE = "Invalid Input :(";
    private static final String EXIT_MESSAGE = "Bye bye!";

    public void start() {

        StartScreen.main(new String[0]);

//        Scanner in = new Scanner(System.in);
//        String selection;
//        controller = new AppController();
//        while (true) {
//            printMenu();
//            selection = in.nextLine();
//            handleInput(selection);
//        }

    }

    private void handleInput(String input) {
        // input handling via console input
        switch (input) {
            case "a" -> getTopHeadlinesAustria(controller);
            case "b" -> getAllNewsBitcoin(controller);
            case "y" -> getArticleCount(controller);
            case "q" -> {
                printExitMessage();
                System.exit(0);
            }
            default -> printInvalidInputMessage();
        }
    }

    private void getArticleCount(AppController ctrl) {
        System.out.println(ctrl.getArticleCount());
    }

    private void getTopHeadlinesAustria(AppController ctrl) {
        System.out.println(ctrl.getTopHeadlinesAustria());
    }

    private void getAllNewsBitcoin(AppController ctrl) {
        System.out.println(ctrl.getAllNewsBitcoin());
    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    private static void printMenu() {
        final int REPEAT_COUNT = 30;
        String[] options = new String[]{"Get top headlines Austria", "Get all news about bitcoin", "Count articles", "Quit program"};

        System.out.printf("%s%n%s%n%s%n", "*".repeat(REPEAT_COUNT), "*     Welcome to NewsApp     *", "*".repeat(REPEAT_COUNT));
        System.out.println("Enter what you wanna do: ");
        System.out.printf("a: %s%n" + "b: %s%n" + "y: %s%n" + "q: %s%n", options[0], options[1], options[2], options[3]);
    }


}
