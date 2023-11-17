package src;

/**
 * Implements the function attachebleIsUp which is implemented in classes where a attacheble (e.g. ramp and loading platform) is present.
 */
public interface Hinged {
    /**
     * Checks if the hinge is raised.
     * @return True if the angle is more than 0, else it returns false.
     */
    boolean attacheableIsUp();
}
