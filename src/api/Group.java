package api;

public enum Group {
    ADMIN(1),
    FACULTY(2),
    STUDENT(3);

    private final int levelCode;

    private Group(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getLevelCode() {
        return this.levelCode;
    }

}
