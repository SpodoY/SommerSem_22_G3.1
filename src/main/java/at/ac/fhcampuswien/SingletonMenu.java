package at.ac.fhcampuswien;

import at.ac.fhcampuswien.gui.NewGUI;

public class SingletonMenu {

    // The field must be declared volatile so that double check lock would work correctly.
    // volatile -> everytime the variable is accessed,it is read directly from the main memory
    // Singleton - only one instance is created
    private static volatile SingletonMenu instance = null;
    private SingletonAppController controller;
    private static final String INVALID_INPUT_MESSAGE = "Invalid Input :(";
    private static final String EXIT_MESSAGE = "Bye bye!";

    private SingletonMenu() {
    }

    public static SingletonMenu getInstance() {
        // The approach taken here is called double-checked locking (DCL). It
        // exists to prevent race condition between multiple threads that may
        // attempt to get singleton instance at the same time, creating separate
        // instances as a result.
        //
        // It may seem that having the `result` variable here is completely
        // pointless. There is, however, a very important caveat when
        // implementing double-checked locking in Java, which is solved by
        // introducing this local variable.
        //
        // You can read more info DCL issues in Java here:
        // https://refactoring.guru/java-dcl-issue


        SingletonMenu result = instance;
        //even if the instance was created, every thread has to wait before returning it
        //-> therefore extra if (instance != null) immediately return instance/result
        if (result != null) {
            return result;
        }
        synchronized(SingletonMenu.class) {
            if (instance == null) {
                instance = new SingletonMenu();
            }
            return instance;
        }
    }

    public void start() {

        NewGUI.main(new String[0]);

    }

    /**
     * Based on which String is given the corresponding method gets called.
     * If the input is wrong a message is displayed
     *
     * @param input The string to tell which method should be called
     */
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

    private void getArticleCount(SingletonAppController ctrl) {
        System.out.println(ctrl.getArticleCount());
    }

    private void getTopHeadlinesAustria(SingletonAppController ctrl) {
        System.out.println(ctrl.getArticles());
    }

    private void getAllNewsBitcoin(SingletonAppController ctrl) {
        System.out.println(ctrl.getArticles());
    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    /**
     * Prints the menu as stated in the specifications
     */
    private static void printMenu() {
        final int REPEAT_COUNT = 30;
        // String with the 4 Options the user can choose
        String[] options = new String[]{"Get top headlines Austria", "Get all news about bitcoin", "Count articles", "Quit program"};

        /* Prints '*' REPEAT_COUNT times, then a new line with the text and then prints '*' again in the third line */
        System.out.printf("%s%n%s%n%s%n", "*".repeat(REPEAT_COUNT), "*     Welcome to NewsApp     *", "*".repeat(REPEAT_COUNT));
        System.out.println("Enter what you wanna do: ");
        /* Prints the four options which are written in the options[] */
        System.out.printf("a: %s%n" + "b: %s%n" + "y: %s%n" + "q: %s%n", options[0], options[1], options[2], options[3]);
    }


}
