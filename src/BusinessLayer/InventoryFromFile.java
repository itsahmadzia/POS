package BusinessLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * The InventoryFromFile class is responsible for loading categories and products from CSV files,
 * associating products with categories, and providing methods to list and display information about
 * categories and products.
 */
public class InventoryFromFile {

    private static Map<Integer, Integer> categoryParentMapping = new HashMap<>();

    public static void main(String[] args) {

        String categoriesCsvFilePath = "src/categories.csv";
        String productsCsvFilePath = "src/products.csv";

        List<Category> categories = loadCategoriesFromCsv(categoriesCsvFilePath);
        List<Product> products = loadProductsFromCsv(productsCsvFilePath, categories);



             int categoryCodeToSearch = 101;
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
    
    /**
     * Load categories from a CSV file and populate the category list.
     *
     * @param filePath The file path of the CSV containing category information.
     * @return A list of Category objects loaded from the CSV file.
     */
    public static List<Category> loadCategoriesFromCsv(String filePath) {
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
    
    /**
     * Load products from a CSV file, associate them with categories, and populate the product list.
     *
     * @param filePath   The file path of the CSV containing product information.
     * @param categories The list of categories to associate with the loaded products.
     * @return A list of Product objects loaded from the CSV file.
     */
    public static List<Product> loadProductsFromCsv(String filePath, List<Category> categories) {
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
                String expiryDateString = data[7].trim();
                Date expiryDate = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    expiryDate = dateFormat.parse(expiryDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Handle the parsing exception as needed
                }


                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setStock_quantity(stockQuantity);
                product.setQuantity_per_pack(quantityPerPack);
                product.setExp(expiryDate);

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
    
     /**
     * Find a category by its code in the provided list of categories.
     *
     * @param categories The list of categories to search.
     * @param code       The code of the category to find.
     * @return The Category object with the specified code, or null if not found.
     */
    public static Category findCategoryByCode(List<Category> categories, int code) {
        for (Category category : categories) {
            if (category.getCode() == code) {
                return category;
            }
        }
        return null;
    }
    
    /**
     * Link categories based on parent codes to establish hierarchical relationships.
     *
     * @param categories The list of categories to link.
     */
    public static void linkCategories(List<Category> categories) {
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
    /**
     * Display information about categories and products in the console.
     *
     * @param categories The list of categories to display.
     * @param products   The list of products to display.
     */
    public static void displayData(List<Category> categories, List<Product> products) {
        for (Category category : categories) {
            category.display();
        }

        for (Product product : products) {
            product.display();
        }
    }
    
    /**
     * List all products in the specified category code, including products in subcategories.
     *
     * @param categories   The list of categories containing the target category.
     * @param categoryCode The code of the target category.
     * @return A list of Product objects in the specified category and its subcategories.
     */
    public static List<Product> listProductsInCategoryCode(List<Category> categories, int categoryCode) {
        List<Product> productsInCategory = new ArrayList<>();

        Category category = findCategoryByCode(categories, categoryCode);
        if (category != null) {
            getAllProductsInCategory(category, productsInCategory);
        }

        return productsInCategory;
    }
    
    /**
     * List products only in the specified category code, excluding products in subcategories.
     *
     * @param categories   The list of categories containing the target category.
     * @param categoryCode The code of the target category.
     * @return A list of Product objects in the specified category only.
     */
    public static List<Product> listProductsInThisCategoryCode(List<Category> categories, int categoryCode) {
        List<Product> productsInCategory = new ArrayList<>();

        Category category = findCategoryByCode(categories, categoryCode);
        if (category != null) {
            getAllProductsInthisCategory(category, productsInCategory);
        }

        return productsInCategory;
    }
    
    /**
     * Recursively get all products in the specified category and its subcategories.
     *
     * @param category            The target category.
     * @param productsInCategory A list to store products in the category and its subcategories.
     */
    public static void getAllProductsInCategory(Category category, List<Product> productsInCategory) {
        for (Composite component : category.getChildren()) {
            if (component instanceof Product) {
                productsInCategory.add((Product) component);
            } else if (component instanceof Category) {
                getAllProductsInCategory((Category) component, productsInCategory);
            }
        }
    }
    
    /**
     * Get all products only in the specified category, excluding products in subcategories.
     *
     * @param category            The target category.
     * @param productsInCategory A list to store products only in the specified category.
     */
    public static void getAllProductsInthisCategory(Category category, List<Product> productsInCategory) {
        for (Composite component : category.getChildren()) {
            if (component instanceof Product) {
                productsInCategory.add((Product) component);
            }
        }
    }
}
