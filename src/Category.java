import java.util.ArrayList;
import java.util.List;

public class Category {
    private int code;
    private String name;
    private String description;
    public ArrayList<Product> products=new ArrayList<>();

    public ArrayList<Category> sub_categories=new ArrayList<>();
    public void addSubCategory(Category c){
    sub_categories.add(c);
    }
    public void addProduct(Product c){
        products.add(c);
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

    public List<Product> getProducts() {
        return products;
    }

    public List<Category> getSubcategories() {
        return sub_categories;
    }
}
