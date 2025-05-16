package security;

public class Permission {
    private int id;
    private String name;

    public Permission(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
