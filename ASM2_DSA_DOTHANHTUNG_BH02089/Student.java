

class Student {
    private int id;
    private String name;
    private double mark;
    private String ranking;
    Student left, right;

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
        this.ranking = assignRanking(mark);
    }
    public String getRanking() {
        return ranking;
    }

    private String assignRanking(double mark) {
        if (mark >= 9) return "Excellent";
        if (mark >= 8) return "Good";
        if (mark >= 6.5) return "Fair";
        return "Poor";
    }


}