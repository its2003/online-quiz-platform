import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;

    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ": " + options.get(i));
            }

            int userAnswer = getUserAnswer(scanner, options.size());

            if (question.isCorrect(userAnswer - 1)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + options.get(question.getCorrectAnswerIndex()));
            }
            System.out.println();
        }

        System.out.println("Quiz over! Your score is: " + score + "/" + questions.size());
        scanner.close();
    }

    private int getUserAnswer(Scanner scanner, int numOptions) {
        int userAnswer = -1;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Your answer (1-" + numOptions + "): ");
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                if (userAnswer >= 1 && userAnswer <= numOptions) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and " + numOptions + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear the invalid input
            }
        }
        return userAnswer;
    }
}

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        quiz.addQuestion(new Question(
            "What is the capital of France?",
            Arrays.asList("Berlin", "Madrid", "Paris", "Lisbon"),
            2
        ));
        quiz.addQuestion(new Question(
            "Which planet is known as the Red Planet?",
            Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"),
            1
        ));
        quiz.addQuestion(new Question(
            "What is the largest ocean on Earth?",
            Arrays.asList("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
            3
        ));

        quiz.start();
    }
}
