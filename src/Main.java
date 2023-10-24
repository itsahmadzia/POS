import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();

        //admin user
        Admin admin = new Admin(1, "admin1", "admin123");
        if (admin.authenticate("admin1", "admin123")) {
            admin.setLoggedIn(true);
            System.out.println("Admin login sucessfull");
        } else {
            System.out.println("Admin login failed");
        }
        //View users ,other than admin:0 
        admin.viewUsers(users);

        //manager 
        Manager managerRole = new Manager();
        User manager = admin.addUser(users, "manager1", "1234", managerRole);
        
        admin.viewUsers(users);
        Logout.logOutAdmin(admin);
        
        Login login = new Login(users);
        if (login.authenticateUser("manager1", "1234")) {
            System.out.println("Manager logged in");
        } else {
            System.out.println("Manager login failed");
        }

        Logout.logOut(manager);
        System.out.println("Manager logged out");
        
/* 
        // Create Product objects
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Iphone");
        product1.setPrice(4999.99);
        product1.setQuantity_per_pack(5);
        product1.setStock_quantity(100);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Lenovo ");
        product2.setPrice(99239.9);
        product2.setStock_quantity(10);

        Product product3 = new Product();
        product3.setId(3);
        product3.setName("frock ");
        product3.setPrice(6000.2033);
        product3.setStock_quantity(5);

        Product product4 = new Product();
        product4.setId(3);
        product4.setName("black leather jacket");
        product4.setPrice(10000.123);
        product4.setStock_quantity(7);
        // Create Category objects
        Category mainCategory1 = new Category();
        mainCategory1.setCode(1);
        mainCategory1.setName("Electronics");
        mainCategory1.setDescription("Main category for electronics");

        Category subCategory1 = new Category();
        subCategory1.setCode(11);
        subCategory1.setName("Smartphones");
        subCategory1.setDescription("Category for smartphones");

        Category subCategory2 = new Category();
        subCategory2.setCode(12);
        subCategory2.setName("Laptops");
        subCategory2.setDescription("Category for laptops");

        Category mainCategory2 = new Category();
        mainCategory2.setCode(2);
        mainCategory2.setName("Clothing");
        mainCategory2.setDescription("Main category for clothing");

        Category subCategory3 = new Category();
        subCategory3.setCode(21);
        subCategory3.setName("Men's Clothing");
        subCategory3.setDescription("Category for men's clothing");

        Category subCategory4 = new Category();
        subCategory4.setCode(22);
        subCategory4.setName("Women's Clothing");
        subCategory4.setDescription("Category for women's clothing");
        Category subSubCategory = new Category();
        subSubCategory.setCode(211);
        subSubCategory.setName("Occasional");
        subSubCategory.setDescription("Occasional clothing for men");

        // Create a product "Jeans"
        Product jeansProduct = new Product();
        jeansProduct.setId(5);
        jeansProduct.setName("Jeans");
        jeansProduct.setPrice(49.99);
        jeansProduct.setStock_quantity(60);


        Product common = new Product();
        common.setId(5);
        common.setName("common");
       common.setPrice(49.99);
        common.setStock_quantity(60);
common.addCategory(mainCategory2);
        // Add products to categories
        mainCategory1.addProduct(common);
        mainCategory1.addSubCategory(subCategory1);
        mainCategory1.addSubCategory(subCategory2);
        mainCategory1.addProduct(product1);
        mainCategory1.addProduct(product2);
        subCategory1.addProduct(product1);
        subCategory2.addProduct(product2);

        mainCategory2.addSubCategory(subCategory3);
        mainCategory2.addSubCategory(subCategory4);
        mainCategory2.addProduct(product3);
        mainCategory2.addProduct(product4);
        subCategory3.addProduct(product4);
        subCategory4.addProduct(product3);
        subCategory3.addSubCategory(subSubCategory);
        subSubCategory.addProduct(jeansProduct);

        // Print category structure and products
        System.out.println("Category Structure:");
        printCategory(mainCategory1, 3);
        printCategory(mainCategory2, 3);


        List<Product> productList1= new ArrayList<>();
        productList1=mainCategory1.products;
        List<Product> productList2= new ArrayList<>();
        productList2=mainCategory2.products;


    Cart cart = new Cart();


        Item item1 = new Item();
        item1.setQuantityorder(1);
        item1.setPack(true);
        item1.total(product1);

        Item item2 = new Item();
        item2.setQuantityorder(2);
        item2.setPack(false);
        item2.total(product2);


        cart.add(item1);
        cart.add(item2);


        Order order = cart.generateOrder();
        order.setOrder_id(123);
        order.setCustomerName("Ahmad zia");
        order.setTimestamp(new Date());
        String invoiceContent = order.generateInvoice();
        System.out.println(invoiceContent);
      System.out.println ( product1.getStock_quantity());
   System.out.println(  product2.getStock_quantity());
/*
        ItemContainer c=new ItemContainer();
        int i1=0;
        int j=1;
        for(Product product :productList1){

            System.out.println( "  Product: " + product.getName() + ", Price: PKR" + product.getPrice()+"   "+j);
            j++;
        }
        j=1;

        for(Product product :productList2){
            System.out.println( "  Product: " + product.getName() + ", Price: PKR" + product.getPrice()+"   "+j);
            j++;
        }
        for(Product p :productList1){
            i1++;
            Item m = new Item(i1);
            m.total(p);
            c.items.add(m);

        }
         i1=0;
        for(Product p :productList2){
            i1++;
            Item m = new Item(i1);
            m.total(p);
            c.items.add(m);

        }
        System.out.println("YOUR BILL IS "+c.total_price());
*/



    }

    public static void printCategory(Category category, int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }

        System.out.println(indent + "Category: " + category.getName());
        for (Product product : category.getProducts()) {
            System.out.println(indent + "  Product: " + product.getName() + ", Price: PKR" + product.getPrice());
        }

        for (Category subCategory : category.getSubcategories()) {
            printCategory(subCategory, depth + 1);
        }


    }
}
