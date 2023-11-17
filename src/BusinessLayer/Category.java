package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

public class Category implements Composite {
    private int code;
    private String name;
    private List<Composite> children;
    private String description;

    Category(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.children = new ArrayList<>();
    }

    public void addComponent(Composite component) {
        children.add(component);
    }

    @Override
    public void display() {
        System.out.println("Category: " + name);
        for (Composite child : children) {
            child.display();
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public List<Composite> getChildren() {
        return children;
    }
}
