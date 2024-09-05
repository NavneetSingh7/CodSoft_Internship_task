import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Take marks obtained (out of 100) in each subject
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];
        }

        // Calculate Total Marks: Sum up the marks obtained in all subjects
        System.out.println("Total Marks: " + totalMarks);

        // Calculate Average Percentage: Divide the total marks by the total number of subjects to get the average percentage
        double averagePercentage = (double) totalMarks / numSubjects;
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);

        // Grade Calculation: Assign grades based on the average percentage achieved
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display Results: Show the total marks, average percentage, and the corresponding grade to the user
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
