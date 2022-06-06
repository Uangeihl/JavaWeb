package Control;

public class Student {
    private int id;
    private String name;
    private int czxt;
    private int wjyl;
    private int jsjwl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCzxt() {
        return czxt;
    }

    public void setCzxt(int czxt) {
        this.czxt = czxt;
    }

    public int getWjyl() {
        return wjyl;
    }

    public void setWjyl(int wjyl) {
        this.wjyl = wjyl;
    }

    public int getJsjwl() {
        return jsjwl;
    }

    public void setJsjwl(int jsjwl) {
        this.jsjwl = jsjwl;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", czxt=" + czxt +
                ", wjyl=" + wjyl +
                ", jsjwl=" + jsjwl +
                '}';
    }
}
