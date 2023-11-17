import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryFromFile {

    private static Map<Integer, Integer> categoryParentMapping = new HashMap<>();

    public static void main(String[] args) {

        String categoriesCsvFilePath = "src/categories.csv";
        String productsCsvFilePath = "src/products.csv";

        List<Category> categories = loadCategoriesFromCsv(categoriesCsvFilePath);
        List<Product> products = loadProductsFromCsv(productsCsvFilePath, categories);

        displayData(categories, products);
        int categoryCodeToSearch = 102;
        List<Product> productsInCategory = listProductsInCategoryCode(categories, categoryCodeToSearch);

        System.out.println("\nProducts in Category with Code " + categoryCodeToSearch + ":");
        for (Product product : productsInCategory) {
            product.display();
        }
        List<Product> productsInthisCategory =listProductsInThisCategoryCode(categories, categoryCodeToSearch);
        System.out.println("\nProducts in only this Category with Code " + categoryCodeToSearch + ":");

        for (Product product : productsInthisCategory) {
            product.display();
        }
    }

    private static List<Category> loadCategoriesFromCsv(String filePath) {
        List<Category> categories = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] data = line.split(",");
                int code = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String description = data[2].trim();
                int parentCode = 0;

                if (data.length > 3) {
                    int potentialParentCode = Integer.parseInt(data[3].trim());
               //checking if the parent is valid or not
                    if (findCategoryByCode(categories, potentialParentCode) != null) {
                        parentCode = potentialParentCode;
                    }
                }

                Category category = new Category(code, name, description);
                categories.add(category);

                categoryParentMapping.put(code, parentCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        linkCategories(categories);

        return categories;
    }

    private static List<Product> loadProductsFromCsv(String filePath, List<Category> categories) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String description = data[2].trim();
                double price = Double.parseDouble(data[3].trim());
                int stockQuantity = Integer.parseInt(data[4].trim());
                int quantityPerPack = Integer.parseInt(data[5].trim());
                int categoryCode = Integer.parseInt(data[6].trim());

                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setStock_quantity(stockQuantity);
                product.setQuantity_per_pack(quantityPerPack);

                Category category = findCategoryByCode(categories, categoryCode);
                if (category != null) {
                    category.addComponent(product);
                }

                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    private static Category findCategoryByCode(List<Category> categories, int code) {
        for (Category category : categories) {
            if (category.getCode() == code) {
                return category;
            }
        }
        return null;
    }

    private static void linkCategories(List<Category> categories) {
        for (Category category : categories) {
            int parentCode = categoryParentMapping.get(category.getCode());
            if (parentCode != 0) {
                Category parent = findCategoryByCode(categories, parentCode);
                if (parent != null) {
                    parent.addComponent(category);
                }
            }
        }
    }

    private static void displayData(List<Category> categories, List<Product> products) {
        for (Category category : categories) {
            category.display();
        }

        for (Product product : products) {
            product.display();
        }
    }

    private static List<Product> listProductsInCategoryCode(List<Category> categories, int categoryCode) {
        List<Product> productsInCategory = new ArrayList<>();

        Category category = findCategoryByCode(categories, categoryCode);
        if (category != null) {
            getAllProductsInCategory(category, productsInCategory);
        }

        return productsInCategory;
    }
    private static List<Product> listProductsInThisCategoryCode(List<Category> categories, int categoryCode) {
        List<Product> productsInCategory = new ArrayList<>();

        Category category = findCategoryByCode(categories, categoryCode);
        if (category != null) {
            getAllProductsInthisCategory(category, productsInCategory);
        }

        return productsInCategory;
    }
    private static void getAllProductsInCategory(Category category, List<Product> productsInCategory) {
        for (Composite component : category.getChildren()) {
            if (component instanceof Product) {
                productsInCategory.add((Product) component);
            } else if (component instanceof Category) {
                getAllProductsInCategory((Category) component, productsInCategory);
            }
        }
    }
    private static void getAllProductsInthisCategory(Category category, List<Product> productsInCategory) {
        for (Composite component : category.getChildren()) {
            if (component instanceof Product) {
                productsInCategory.add((Product) component);
            }
        }
    }
}
