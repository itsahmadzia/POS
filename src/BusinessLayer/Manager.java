package BusinessLayer;
/**
 * The Manager class represents a user in the system with the role type Manager.
 * This class extends the abstract Role class, providing a specific implementation
 * for the getRoleType method.
 */
public class Manager extends Role {
    /**
     * Gets the role type of the Manager, which is "Manager".
     *
     * @return The role type of the Manager.
     */
    public String getRoleType() {
        return "Manager";
    }
}
