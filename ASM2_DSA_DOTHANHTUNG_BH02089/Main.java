import java.util.*;

public class Main {

    private static int idCounter = 0;
    private static Student root = null;

    public static Student addStudent(Student st, Student root) {
        if (st == null) return root;
        if (root == null) return st;

        if (st.getMark() < root.getMark()) {
            root.left = addStudent(st, root.left);
        } else {
            root.right = addStudent(st, root.right);
        }
        return root;
    }

    public static void printStudents(Student root) {
        if (root != null) {
            printStudents(root.left);
            System.out.println("ID: " + root.getId() + ", Name: " + root.getName() + ", Mark: " + root.getMark() + ", Ranking: " + root.getRanking());
            printStudents(root.right);
        }
    }

    public static Student searchById(Student root, int id) {
        if (root == null) return null;
        if (root.getId() == id) return root;
        Student leftResult = searchById(root.left, id);
        return (leftResult != null) ? leftResult : searchById(root.right, id);
    }

    public static Student deleteStudent(Student root, int id) {
        if (root == null) return null;

        if (id < root.getId()) {
            root.left = deleteStudent(root.left, id);
        } else if (id > root.getId()) {
            root.right = deleteStudent(root.right, id);
        } else {
            if (root.left == null && root.right == null) {
                return null; // Node không có con -> xóa bằng cách trả về null.
            }
            // Trường hợp tìm thấy node cần xóa
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Student minNode = findMin(root.right);
            root = new Student(minNode.getId(), minNode.getName(), minNode.getMark());
            root.right = deleteStudent(root.right, minNode.getId());
        }
        return root;
    }

    private static Student findMin(Student root) {
        while (root.left != null) root = root.left;
        return root;
    }


    public static void sortStudents() {
        List<Student> students = new ArrayList<>();
        collectStudents(root, students);
        students.sort(Comparator.comparingDouble(Student::getMark).reversed());
        System.out.println("Sorted Students:");
        for (Student st : students) {
            System.out.println("ID: " + st.getId() + ", Name: " + st.getName() + ", Mark: " + st.getMark() + ", Ranking: " + st.getRanking());
        }
    }


    private static void collectStudents(Student root, List<Student> students) {
        if (root != null) {
            collectStudents(root.left, students);
            students.add(root);
            collectStudents(root.right, students);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        root = Main.addStudent(new Student(0, "Alice", 8.5), root);
        root = Main.addStudent(new Student(1, "Bob", 9.0), root);
        root = Main.addStudent(new Student(2, "Charlie", 7.0), root);
        root = Main.addStudent(new Student(3, "David", 8.0), root);
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1: Add new student");
            System.out.println("2: Edit student by ID");
            System.out.println("3: Delete student by ID");
            System.out.println("4: Search student by ID");
            System.out.println("5: Sort students by marks");
            System.out.println("6: See all students");
            System.out.println("0: Exit");

            int choice;
            while (true) {
                System.out.println("Enter your choice (0-6): ");
                // Kiểm tra xem người dùng có nhập đúng một số nguyên không
                if (input.hasNextInt()) {
                    choice = input.nextInt();  // Đọc lựa chọn người dùng

                    // Kiểm tra xem lựa chọn có nằm trong phạm vi hợp lệ không
                    if (choice >= 0 && choice <= 6) {
                        break;  // Nếu hợp lệ, thoát khỏi vòng lặp
                    } else {
                        System.out.println("Enter invalid! Please choose a number between 0 and 6.");
                    }
                } else {
                    // Nếu người dùng nhập không phải là số, yêu cầu nhập lại
                    System.out.println("Invalid input! Please enter a number between 0 and 6.");
                    input.next();  // Đọc và bỏ qua giá trị không hợp lệ
                }
            }
            input.nextLine();
            switch (choice) {
                case 1: {
                    System.out.print("Enter name: ");
                    String name = input.nextLine();
                    System.out.print("Enter mark: ");
                    double mark = input.nextDouble();
                    Student newStudent = new Student(idCounter++, name, mark);
                    root = addStudent(newStudent, root);
                    System.out.println("Student added successfully.");
                    break;
                }
                case 2: {
                    System.out.print("Enter ID of student to edit: ");
                    int id = input.nextInt();
                    input.nextLine();
                    Student st = searchById(root, id);
                    if (st != null) {
                        System.out.print("Enter new name: ");
                        String name = input.nextLine();
                        System.out.print("Enter new mark: ");
                        double mark = input.nextDouble();
                        st.setName(name);
                        st.setMark(mark);
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Enter ID of student to delete: ");
                    int id = input.nextInt();
                    root = deleteStudent(root, id);
                    System.out.println("Student deleted successfully.");
                    break;
                }
                case 4: {
                    System.out.print("Enter ID of student to search: ");
                    int id = input.nextInt();
                    Student st = searchById(root, id);
                    if (st != null) {
                        System.out.println("Found Student - ID: " + st.getId() + ", Name: " + st.getName() + ", Mark: " + st.getMark() + ", Ranking: " + st.getRanking());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                }
                case 5: {
                    sortStudents();
                    break;
                }
                case 6: {
                    if (root == null) {
                        System.out.println("No students found.");
                    } else {
                        printStudents(root);
                    }
                    break;
                }
                case 0: {
                    System.out.println("Exiting...");
                    input.close();
                    return;
                }
                default: {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}

