
import java.util.*;

class Student {

    protected String name;
    protected List<Integer> quizzes;

    public Student(String name) {
        this.name = name;
        this.quizzes = new ArrayList<>();
    }

    public void addQuizScore(int score) {
        if (quizzes.size() < 15) {
            quizzes.add(score);
        }
    }

    public double getAverageQuizScore() {
        return quizzes.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public List<Integer> getQuizScores() {
        return quizzes;
    }

    public String getName() {
        return name;
    }
}

class PartTimeStudent extends Student {

    public PartTimeStudent(String name) {
        super(name);
    }
}

class FullTimeStudent extends Student {

    private final int examScore1;
    private final int examScore2;

    public FullTimeStudent(String name, int examScore1, int examScore2) {
        super(name);
        this.examScore1 = examScore1;
        this.examScore2 = examScore2;
    }

    public int getExamScore1() {
        return examScore1;
    }

    public int getExamScore2() {
        return examScore2;
    }
}

class Session {

    private final List<Student> students;

    public Session() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (students.size() < 20) {
            students.add(student);
        }
    }

    public double getClassAverageQuizScore() {
        return students.stream().mapToDouble(Student::getAverageQuizScore).average().orElse(0);
    }

    public void printSortedQuizScores() {
        List<Integer> allQuizzes = new ArrayList<>();
        for (Student s : students) {
            allQuizzes.addAll(s.getQuizScores());
        }
        Collections.sort(allQuizzes);
        System.out.println("Sorted Quiz Scores: " + allQuizzes);
    }

    public void printPartTimeStudents() {
        System.out.println("Part-Time Students:");
        for (Student s : students) {
            if (s instanceof PartTimeStudent) {
                System.out.println(s.getName());
            }
        }
    }

    public void printFullTimeStudentExamScores() {
        System.out.println("Full-Time Student Exam Scores:");
        for (Student s : students) {
            if (s instanceof FullTimeStudent ft) {
                System.out.println(ft.getName() + " - Exam 1: " + ft.getExamScore1() + ", Exam 2: " + ft.getExamScore2());
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Session session = new Session();
        Random rand = new Random();

        for (int i = 1; i <= 10; i++) {
            FullTimeStudent ft = new FullTimeStudent("FullTimeStudent" + i, rand.nextInt(50) + 50, rand.nextInt(50) + 50);
            for (int j = 0; j < 15; j++) {
                ft.addQuizScore(rand.nextInt(10) + 1);
            }
            session.addStudent(ft);
        }

        for (int i = 1; i <= 10; i++) {
            PartTimeStudent pt = new PartTimeStudent("PartTimeStudent" + i);
            for (int j = 0; j < 15; j++) {
                pt.addQuizScore(rand.nextInt(10) + 1);
            }
            session.addStudent(pt);
        }

        System.out.println("Class Average Quiz Score: " + session.getClassAverageQuizScore());
        session.printSortedQuizScores();
        session.printPartTimeStudents();
        session.printFullTimeStudentExamScores();
    }
}
