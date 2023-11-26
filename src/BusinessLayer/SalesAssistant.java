package BusinessLayer;

import DBLayer.OrderDAO;
import DBLayer.ProductDAO;

public class SalesAssistant extends Role {
    @Override
    public String getRoleType() {
        return "Operator"; //since table in db is operator
    }
    
}
