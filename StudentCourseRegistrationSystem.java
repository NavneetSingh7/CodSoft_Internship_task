import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    public boolean hasAvailableSlot() {
        return registeredStudents < capacity;
    }

    public void registerStudent() {
        registeredStudents++;
    }

    public void unregisterStudent() {
        registeredStudents--;
    }

    @Override
    public String toString() {
        return courseCode + " - " + title + ": " + description + " | Capacity: " + capacity + 
               " | Registered: " + registeredStudents + " | Schedule: " + schedule;
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.registerStudent();
    }

    public void removeCourse(Course course) {
        registeredCourses.remove(course);
        course.unregisterStudent();
    }

    public void listRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

class CourseDatabase {
    HashMap<String, Course> courses = new HashMap<>();

    public void addCourse(Course course) {
        courses.put(course.courseCode, course);
    }

    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

    public void listCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }
}

class StudentDatabase {
    HashMap<String, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.studentID, student);
    }

    public Student getStudent(String studentID) {
        return students.get(studentID);
    }
}

public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CourseDatabase courseDB = new CourseDatabase();
        StudentDatabase studentDB = new StudentDatabase();

        // Sample Data
        courseDB.addCourse(new Course("CS101", "Introduction to Computer Science", "Basics of CS", 30, "MWF 10-11 AM"));
        courseDB.addCourse(new Course("CS102", "Data Structures", "Introduction to Data Structures", 25, "TTh 2-3:30 PM"));
        courseDB.addCourse(new Course("CS103", "Algorithms", "Fundamentals of Algorithms", 20, "MW 4-5:30 PM"));

        studentDB.addStudent(new Student("S001", "John Doe"));
        studentDB.addStudent(new Student("S002", "Jane Smith"));

        int choice;

        do {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Available Courses ---");
                    courseDB.listCourses();
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = sc.next();
                    Student student = studentDB.getStudent(studentID);
                    if (student == null) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    System.out.print("Enter Course Code to register: ");
                    String courseCode = sc.next();
                    Course course = courseDB.getCourse(courseCode);
                    if (course == null) {
                        System.out.println("Invalid Course Code.");
                    } else if (!course.hasAvailableSlot()) {
                        System.out.println("Course is full.");
                    } else {
                        student.registerCourse(course);
                        System.out.println("Successfully registered for " + course.title);
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = sc.next();
                    student = studentDB.getStudent(studentID);
                    if (student == null) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    System.out.print("Enter Course Code to drop: ");
                    courseCode = sc.next();
                    course = courseDB.getCourse(courseCode);
                    if (course == null) {
                        System.out.println("Invalid Course Code.");
                    } else if (!student.registeredCourses.contains(course)) {
                        System.out.println("You are not registered for this course.");
                    } else {
                        student.removeCourse(course);
                        System.out.println("Successfully dropped " + course.title);
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = sc.next();
                    student = studentDB.getStudent(studentID);
                    if (student == null) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }
                    System.out.println("\n--- Registered Courses ---");
                    student.listRegisteredCourses();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);

        sc.close();
    }
}
