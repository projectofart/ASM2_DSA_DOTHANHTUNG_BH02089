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
            System.out.println("ID: " + root.getId() + ", Name: " + root.getName() + ", Mark: " + String.format("%.2f", root.getMark()) + ", Ranking: " + root.getRanking());
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
                return null; 
            }
            
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
    public static List<Student> mergeSort(List<Student> students) {
        if (students.size() <= 1) {
            return students;
        }

        int mid = students.size() / 2;

        List<Student> left = new ArrayList<>(students.subList(0, mid));
        List<Student> right = new ArrayList<>(students.subList(mid, students.size()));

        left = mergeSort(left);
        right = mergeSort(right);


        return merge(left, right);
    }

    private static List<Student> merge(List<Student> left, List<Student> right) {
        List<Student> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getMark() <= right.get(j).getMark()) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }
        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }
    public static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hank", "Ivy", "Jack", "Kara", "Leo", "Mona", "Nina", "Oscar", "Paul", "Quinn", "Rita", "Sam", "Tina", "Uma", "Victor", "Wendy", "Xander", "Yara", "Zane"};

        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String name = names[random.nextInt(names.length)] + (random.nextInt(900) + 100);
            double mark = 0.5 + random.nextDouble() * 9.5;
            students.add(new Student(idCounter++, name, mark));
        }

        return students;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        root = Main.addStudent(new Student(0, "Alice", 8.5), root);
        root = Main.addStudent(new Student(1, "Bob", 9.0), root);
        root = Main.addStudent(new Student(2, "Charlie", 7.0), root);
        root = Main.addStudent(new Student(3, "David", 8.0), root);
        root = Main.addStudent(new Student(4, "Eve", 7.5), root);
        root = Main.addStudent(new Student(5, "Frank", 8.8), root);
        root = Main.addStudent(new Student(6, "Grace", 9.2), root);
        root = Main.addStudent(new Student(7, "Hank", 6.5), root);
        root = Main.addStudent(new Student(8, "Ivy", 8.3), root);
        // Student in array list to compare with Binara tree
        List<Student> students = new ArrayList<>();
        students.add(new Student(0, "Alice", 8.5));
        students.add(new Student(1, "Bob", 9.0));
        students.add(new Student(2, "Charlie", 7.0));
        students.add(new Student(3, "David", 8.0));
        students.add(new Student(4, "Eve", 7.5));
        students.add(new Student(5, "Frank", 8.8));
        students.add(new Student(6, "Grace", 9.2));
        students.add(new Student(7, "Hank", 6.5));
        students.add(new Student(8, "Ivy", 8.3));

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1: Add new student");
            System.out.println("2: Edit student by ID");
            System.out.println("3: Delete student by ID");
            System.out.println("4: Search student by ID");
            System.out.println("5: Sort students by marks");
            System.out.println("6: See all students");
            System.out.println("7: compare two sort algorithm (merge sort vs BTS)");
            System.out.println("0: Exit");

            int choice;
            while (true) {
                System.out.println("Enter your choice (0-7): ");
                if (input.hasNextInt()) {
                    choice = input.nextInt();
                    if (choice >= 0 && choice <= 7) {
                        break; 
                    } else {
                        System.out.println("Enter invalid! Please choose a number between 0 and 6.");
                    }
                } else {
                   
                    System.out.println("Invalid input! Please enter a number between 0 and 6.");
                    input.next();  
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
                case 7: {
                    System.out.print("Enter number of random students to generate: ");
                    int numberOfStudents = input.nextInt();
                    input.nextLine();

                    System.out.println("Generating " + numberOfStudents + " random students...");
                    List<Student> randomStudents = generateStudents(numberOfStudents);

                    System.out.println("Adding students to BST...");
                    for (Student st : randomStudents) {
                        root = addStudent(st, root);
                    }

                    // So sánh BST và Merge Sort
                    System.out.println("\nComparing Binary Search Tree printing and Merge Sort:");

                    // 1. BST in-order traversal
                    long bstPrintStart = System.nanoTime();
                    System.out.println("\nAll students in the BST (in-order traversal):");
                    printStudents(root);
                    long bstPrintEnd = System.nanoTime();
                    System.out.println("BST print execution time: " + (bstPrintEnd - bstPrintStart) + " nanoseconds");

                    // 2. Merge Sort
                    List<Student> studentsCopy = new ArrayList<>();
                    collectStudents(root, studentsCopy);

                    long mergeSortStart = System.nanoTime();
                    List<Student> sortedStudents = mergeSort(studentsCopy);
                    long mergeSortEnd = System.nanoTime();

                    System.out.println("\nMerge Sort Results:");
                    for (Student st : sortedStudents) {
                        System.out.println("ID: " + st.getId() + ", Name: " + st.getName() + ", Mark: " + String.format("%.2f", st.getMark()));
                    }
                    System.out.println("Merge Sort execution time: " + (mergeSortEnd - mergeSortStart) + " nanoseconds");

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

