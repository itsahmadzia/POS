package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * The Category class represents a category in a composite structure. It can
 * have child categories. This class implements the Composite interface,
 * providing a way to organize objects into tree structures.
 */
public class Category implements Composite {
    private int code;
    private String name;
    private List<Composite> children;
    private String description;
    String parentCategoryName;
    
     /**
     * Constructs a new Category with the specified code, name, and description.
     *
     * @param code        The unique id for the category.
     * @param name        The name of the category.
     * @param description A brief description of the category.
     */
    public Category(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.children = new ArrayList<>();
    }
    /**
     * Constructs an empty Category.
     */
    public Category() {

    }
    
    /**
     * Adds a component (child category) to this category.
     *
     * @param component The component to be added.
     */
    public void addComponent(Composite component) {
        children.add(component);
    }
    
    /**
     * Displays information about the category and its children.
     */
    @Override
    public void display() {
        System.out.println("Category: " + name);
        for (Composite child : children) {
            child.display();
        }
    }
    /**
     * Sets the description of the category.
     *
     * @param description A brief description of the category.
     */

    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Sets the name of the category.
     *
     * @param name The name of the category.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the code of the category.
     *
     * @param code The unique id for the category.
     */
    public void setCode(int code) {
        this.code = code;
    }
    /**
     * Gets the description of the category.
     *
     * @return The description of the category.
     */
    public String getDescription() {
        return description;
    }
     /**
     * Gets the name of the category.
     *
     * @return The name of the category.
     */

    public String getName() {
        return name;
    }
   
    /**
     * Gets the code of the category.
     *
     * @return The unique identifier for the category.
     */
    public int getCode() {
        return code;
    }
    /**
     * Gets the list of children (components) of the category.
     *
     * @return The list of children components.
     */
    public List<Composite> getChildren() {
        return children;
    }
    
     /**
     * Sets the name of the parent category.
     *
     * @param pparentCategoryName The name of the parent category.
     */
    public void setParentCategoryName(String pparentCategoryName) {
        parentCategoryName=pparentCategoryName;
    }
    
    /**
     * Gets the name of the parent category.
     *
     * @return The name of the parent category.
     */
    public String getParentCategoryName() {
        return parentCategoryName;
    }
}
