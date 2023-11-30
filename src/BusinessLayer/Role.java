package BusinessLayer;
/**
 * The Role class is an abstract class representing a role in the system.
 * Subclasses must implement the getRoleType method to provide the specific role type.
 */
public abstract class Role {
    /**
     * Gets the type of the role.
     *
     * @return The type of the role.
     */
    public abstract String getRoleType();
}
