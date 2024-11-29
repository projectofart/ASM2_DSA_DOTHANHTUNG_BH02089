

class Student {
    private int id;
    private String name;
    private double mark;
    private String ranking;
    Student left, right;

    // Constructor
    public Student(int id, String name, double mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
        this.ranking = assignRanking(mark);
        this.left = null;
        this.right = null;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getMark() {
        return mark;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMark(double mark) {
        this.mark = mark;
        this.ranking = assignRanking(mark); // Cập nhật ranking sau khi sửa điểm
    }
    public String getRanking() {
        return ranking;
    }
    // Hàm gán xếp hạng dựa vào điểm
    private String assignRanking(double mark) {
        if (mark >= 9) return "Excellent";
        if (mark >= 8) return "Good";
        if (mark >= 6.5) return "Fair";
        return "Poor";
    }
    // Cập nhật phương thức thêm sinh viên trong cây AVL
    // Cập nhật phương thức xóa sinh viên
    // Kiểm tra đầu vào hợp lệ của điểm


}