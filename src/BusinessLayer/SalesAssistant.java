package BusinessLayer;

import DBLayer.OrderDAO;
import DBLayer.ProductDAO;
/**
 * The SalesAssistant class represents the role of a sales assistant (also referred to as an operator) in the system.
 * It extends the Role class and implements the getRoleType method to return the role type "Operator."
 */
public class SalesAssistant extends Role {
    /**
     * Gets the type of the role, which is "Operator" for a sales assistant.
     *
     * @return The type of the role ("Operator" for a sales assistant).
     */
    @Override
    public String getRoleType() {
        return "Operator"; //since table in db is operator
    }
    
}
