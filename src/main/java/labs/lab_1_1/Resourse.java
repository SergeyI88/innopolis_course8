package labs.lab_1_1;

public class Resourse {
    private String text;
    private String name;

    public Resourse(String text, String name) {
        this.text = text;
        this.name = name;
    }

    public Resourse() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
