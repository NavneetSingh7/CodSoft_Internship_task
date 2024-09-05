import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {

    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static int currentQuestionIndex = 0;
    static boolean answerSubmitted = false;

    static class Question {
        String question;
        String[] options;
        int correctAnswer;

        Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    static Question[] questions = {
            new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3),
            new Question("What is 2 + 2?", new String[]{"1. 3", "2. 4", "3. 5", "4. 6"}, 2),
            new Question("What is the capital of Japan?", new String[]{"1. Beijing", "2. Tokyo", "3. Seoul", "4. Bangkok"}, 2)
    };

    public static void main(String[] args) {
        for (int i = 0; i < questions.length; i++) {
            currentQuestionIndex = i;
            askQuestion(questions[i]);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answerSubmitted) {
                        System.out.println("\nTime's up!");
                        checkAnswer(-1); // No answer submitted
                    }
                }
            }, 10000); // 10 seconds for each question

            waitAnswer(); // Wait for user input

            timer.cancel();
        }

        showResults();
    }

    static void askQuestion(Question question) {
        System.out.println(question.question);
        for (String option : question.options) {
            System.out.println(option);
        }
        System.out.println("You have 10 seconds to answer...");
    }

    static void waitAnswer() {
        int answer = scanner.nextInt();
        answerSubmitted = true;
        checkAnswer(answer);
    }

    static void checkAnswer(int answer) {
        if (answer == questions[currentQuestionIndex].correctAnswer) {
            System.out.println("Correct!\n");
            score++;
        } else if (answer == -1) {
            System.out.println("No answer submitted.\n");
        } else {
            System.out.println("Incorrect. The correct answer was option " + questions[currentQuestionIndex].correctAnswer + ".\n");
        }
        answerSubmitted = false;
    }

    static void showResults() {
        System.out.println("Quiz finished!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
    }
}
