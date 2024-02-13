import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

abstract class Person {
    int id;
    String name;
    int age;

    public Person(int id, String name, int age) {
        this.age = age;
        this.id = id;
        this.name = name;
    }

    int getid() {
        return id;
    }

    String getname() {
        return name;
    }

    int getage() {
        return age;
    }

    abstract void getDetails();

}

class Course {
    int Code;
    String name;
    int credit;

    public Course(int Code, String name, int credit) {
        this.credit = credit;
        this.Code = Code;
        this.name = name;
    }

    public int getCode() {
        return Code;
    }

    public String getname() {
        return name;
    }

    public int getcredit() {
        return credit;
    }

    public void display() {
        System.out.println(
                "Code = " + this.Code + " , Name =  " + this.name + " , Credit = " + this.credit);
    }

}

class Student extends Person {
    public HashMap<Integer, ArrayList<Course>> mp;

    public Student(int id, String name, int age) {
        super(id, name, age);
        mp = new HashMap<>();
    }

    public ArrayList<Course> courses(int id) {
        ArrayList<Course> temp = new ArrayList<>();

        if (mp.isEmpty()) {
            return temp;
        } else {
            return mp.get(id);
        }
    }

    void disp() {
        for (HashMap.Entry<Integer, ArrayList<Course>> entry : mp.entrySet()) {
            if (entry.getKey() == this.getid()) {
                ArrayList<Course> temp = entry.getValue();
                if (temp.size() == 0) {
                    System.out.println("NO course Yet....");
                    break;
                } else {
                    System.out.println("Enroll Courses are....");
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        System.out.println(
                                "Code = " + temp.get(i).getCode() + " , Name =  " + temp.get(i).getname()
                                        + " , Credit = " + temp.get(i).getcredit());
                    }
                }

            }
        }
    }

    public void enrollCourse(Course c) {
        ArrayList<Course> temp = courses(this.getid());
        temp.add(c);
        mp.put(this.getid(), temp);
    }

    public void getDetails() {
        System.err.println("Details of student are ");
        System.out.println(
                "Id = " + this.id + " , Name =  " + this.name + " , Age = " + this.age);

    }

}

class Faculity extends Person {

    HashMap<Integer, ArrayList<Course>> mp;
    String dep;

    public Faculity(int id, String name, int age, String dep) {
        super(id, name, age);
        this.dep = dep;
        mp = new HashMap<>();
    }

    void disp() {
        for (HashMap.Entry<Integer, ArrayList<Course>> entry : mp.entrySet()) {
            if (entry.getKey() == this.getid()) {
                ArrayList<Course> temp = entry.getValue();
                if (temp.size() == 0) {
                    System.out.println("NO course Yet....");
                    break;
                } else {
                    System.out.println("Your Tech Courses are....");
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        System.out.println(
                                "Code = " + temp.get(i).getCode() + " , Name =  " + temp.get(i).getname()
                                        + " , Credit = " + temp.get(i).getcredit());
                        // System.out.println(temp.get(i).getCode());
                    }
                }

            }
        }
    }

    public ArrayList<Course> courses(int id) {
        ArrayList<Course> temp = new ArrayList<>();

        if (mp.isEmpty()) {
            return temp;
        } else {
            return mp.get(id);
        }
    }

    public void enrollCourse(Course c) {
        ArrayList<Course> temp = courses(this.getid());
        temp.add(c);
        mp.put(this.getid(), temp);
    }

    public void getDetails() {
        System.err.println("Details of Faculity are ");
        System.out.println(
                "Id = " + this.id + " , Name =  " + this.name + " , Age = " + this.age + " , Department = "
                        + this.dep);

    }

}

class University {
    public ArrayList<Student> student = new ArrayList<>();
    public ArrayList<Faculity> teacher = new ArrayList<>();
    public static ArrayList<Course> course = new ArrayList<>();

    public void addstudent(Student s) {

        boolean f = true;
        for (int i = 0; i < student.size(); i++) {
            if (student.get(i).getid() == s.getid()) {
                System.out.println("Student already exist with this id , Please enroll with new id......");
                f = false;
                break;
            }
        }
        if (f) {
            student.add(s);
            System.out.println("Sucessfully enroled.......");

        }

    }

    public void addteacher(Faculity f) {
        boolean f1 = true;
        for (int i = 0; i < teacher.size(); i++) {
            if (teacher.get(i).getid() == f.getid()) {
                System.out.println("Teacher already exist with this id , Please enroll with new id......");
                f1 = false;
                break;
            }
        }
        if (f1) {
            teacher.add(f);
            System.out.println("Sucessfully enroled.......");

        }
    }

    public void addCourse(Course c) {

        boolean f = true;
        for (int i = 0; i < course.size(); i++) {
            if (course.get(i).getCode() == c.getCode()) {
                System.out.println("Course already exist with this Code , Please add a course with new Code......");
                f = false;
                break;
            }
        }
        if (f) {
            course.add(c);
            System.out.println("Course Successfully added.......");

        }

    }

    public void enrollTech(int id, int code) throws IllegalArgumentException {
        try {

            boolean f = true;
            for (int i = 0; i < teacher.size(); i++) {
                if (teacher.get(i).getid() == id) {

                    for (int j = 0; j < course.size(); j++) {
                        if (course.get(j).getCode() == code) {

                            f = false;
                            Course c = course.get(j);
                            Faculity s = teacher.get(i);
                            s.enrollCourse(c);
                            break;
                        }
                    }

                }
            }
            if (f)
                System.out.println("Either wrong course id or student id.....  ");
            else {
                System.out.println("Successfully Enrolled......");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Exception is " + e.getMessage());
        }

    }

    public void enrollStu(int id, int code) {
        boolean f = true;
        for (int i = 0; i < student.size(); i++) {
            if (student.get(i).getid() == id) {

                for (int j = 0; j < course.size(); j++) {
                    if (course.get(j).getCode() == code) {

                        f = false;
                        Course c = course.get(j);
                        Student s = student.get(i);
                        s.enrollCourse(c);
                        break;
                    }
                }

            }
        }
        if (f)
            System.out.println("Either wrong course id or student id.....  ");
        else {
            System.out.println("Successfully Enrolled......");
        }
    }

    public void removeStudent(int id) {
        boolean f = true;
        for (int i = 0; i < student.size(); i++) {
            if (student.get(i).getid() == id) {
                student.remove(i);
                System.out.println("Successfully Removed....");
                f = false;
                break;
            }

        }
        if (f)
            System.out.println("No student exist with this id.......");
    }

    public void removeTeacher(int id) {
        boolean f = true;
        for (int i = 0; i < teacher.size(); i++) {
            if (teacher.get(i).getid() == id) {
                teacher.remove(i);
                System.out.println("Successfully Removed....");
                f = false;
                break;
            }

        }
        if (f)
            System.out.println("No Teacher exist with this id.......");
    }

    public void removeCourse(int id) throws IllegalArgumentException {
        try {
            boolean f = true;
            for (int i = 0; i < course.size(); i++) {
                if (course.get(i).getCode() == id) {
                    course.remove(i);
                    System.out.println("Successfully Removed....");
                    f = false;
                    break;
                }

            }
            if (f)
                System.out.println("No Course exist with this Code.......");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public void displayStudent() {
        if (student.isEmpty())
            System.out.println("No Students Yet.....");
        else {
            for (int i = 0; i < student.size(); i++) {
                student.get(i).getDetails();
            }
        }
    }

    public void displayTeacher() {
        if (teacher.isEmpty())
            System.out.println("No Teachers Yet.....");
        else {
            for (int i = 0; i < teacher.size(); i++) {
                teacher.get(i).getDetails();
            }
        }
    }

    public boolean displayCourse() {
        if (course.isEmpty()) {
            System.out.println("No Courses Yet.....");
            return false;
        } else {
            System.out.println("Courses are .....");
            for (int i = 0; i < course.size(); i++) {
                course.get(i).display();
            }
            return true;
        }
    }

    public void disT(int id) {
        for (int i = 0; i < teacher.size(); i++) {
            if (teacher.get(i).getid() == id) {
                Faculity s = teacher.get(i);
                s.disp();

            }

        }

    }

    public void disc(int id) {

        for (int i = 0; i < student.size(); i++) {
            if (student.get(i).getid() == id) {
                Student s = student.get(i);
                s.disp();

            }

        }

    }
}

class UniversityManagementSystem {
    public static void main(String[] args) {
        University u = new University();
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        do {
            System.out.println(
                    "......................................................WELCOME TO UNIVERSITY MANAGEMENT SYSTEM.............................................................");
            System.out.println("1. Enroll as a student\n" +
                    "2. Enroll as a Teacher\n" +
                    "3. Add a Course \n" +
                    "4. Display All Students\n" +
                    "5. Display All Faculity\n" +
                    "6. Display All Couses\n" +
                    "7. Add Teach Course(Teacher)\n" +
                    "8. Display my Teach Courses(Teacher)\n" +
                    "9. Enroll for a Course(Student)\n" +
                    "10. Display My Enroll Courses(Student)\n" +
                    "11. Remove a Student\n" +
                    "12. Remove a Teacher\n" +
                    "13. Remove a Course\n" +
                    "14. Exit");
            System.out.println("ENTER YOUR CHOICE......");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    // int id, String name, int age, int studentid
                    System.out.println("Enter id - ");
                    int i = sc.nextInt();
                    System.out.println("Enter name - ");
                    String n = sc.next();
                    System.out.println("Enter age - ");
                    int age = sc.nextInt();
                    Student s = new Student(i, n, age);
                    u.addstudent(s);
                    break;
                case 2:
                    System.out.println("Enter id - ");
                    int i1 = sc.nextInt();
                    System.out.println("Enter name - ");
                    String n1 = sc.next();
                    System.out.println("Enter age - ");
                    int age1 = sc.nextInt();
                    System.out.println("Enter ur department");
                    String dep = sc.next();
                    Faculity f1 = new Faculity(i1, n1, age1, dep);
                    u.addteacher(f1);
                    break;
                case 3:
                    // int Code, int name, int credit
                    System.out.println("Enter code of course - ");
                    int code = sc.nextInt();
                    System.out.println("Enter name of course - ");
                    String nm = sc.next();
                    System.out.println("Enter credit hours of course - ");
                    int hr = sc.nextInt();
                    Course cr = new Course(code, nm, hr);
                    u.addCourse(cr);
                    break;
                case 4:
                    u.displayStudent();
                    break;
                case 5:
                    u.displayTeacher();
                    break;
                case 6:
                    u.displayCourse();
                    break;
                case 7:
                    if (u.displayCourse()) {
                        System.out.println("Enter ur id to enroll for course - ");
                        int i3 = sc.nextInt();
                        System.out.println("Enter no. of Teach courses u want to add- ");
                        int n12 = sc.nextInt();
                        for (int ij = 0; ij < n12; ij++) {
                            System.out.println("Enter  code / id of " + (ij + 1) + " course");
                            int c3 = sc.nextInt();
                            u.enrollTech(i3, c3);
                        }
                    }

                    break;

                case 10:
                    System.out.println("Enter ur id to see ur enroll courses ");
                    int i4 = sc.nextInt();
                    u.disc(i4);
                    break;
                case 9:
                    if (u.displayCourse()) {
                        System.out.println("Enter ur id to enroll for course - ");
                        int i3 = sc.nextInt();
                        System.out.println("Enter no. of courses u want to enroll - ");
                        int n11 = sc.nextInt();
                        for (int ii = 0; ii < n11; ii++) {
                            System.out.println("Enter  code / id of " + (ii + 1) + " course");
                            int c3 = sc.nextInt();
                            u.enrollStu(i3, c3);
                        }

                    }

                    break;
                case 11:
                    System.out.println("Enter id of student - ");
                    int id = sc.nextInt();
                    u.removeStudent(id);
                    break;
                case 12:
                    System.out.println("Enter id of Teacher - ");
                    int id1 = sc.nextInt();
                    u.removeTeacher(id1);
                    break;
                case 13:
                    System.out.println("Enter Id/Code of Course - ");
                    int id3 = sc.nextInt();
                    u.removeCourse(id3);
                    break;
                case 8:
                    System.out.println("Enter ur id to see ur Tech courses ");
                    int i5 = sc.nextInt();
                    u.disT(i5);

                    break;

                case 14:
                    f = false;
                    break;

            }

        } while (f);
        sc.close();

    }
}