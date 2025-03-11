import ro.mpp2025.model.ComputerRepairRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerRepairRequestTest {

    @Test
    public void testGetters() {
        ComputerRepairRequest request = new ComputerRepairRequest(1, "John Doe", "123 Main St", "1234567890", "Dell XPS", "2025-03-04", "Not turning on");

        assertEquals(1, request.getID());
        assertEquals("John Doe", request.getOwnerName());
        assertEquals("123 Main St", request.getOwnerAddress());
        assertEquals("1234567890", request.getPhoneNumber());
        assertEquals("Dell XPS", request.getModel());
        assertEquals("2025-03-04", request.getDate());
        assertEquals("Not turning on", request.getProblemDescription());
    }

    @Test
    public void testSetters() {
        ComputerRepairRequest request = new ComputerRepairRequest();

        request.setID(2);
        request.setOwnerName("Jane Doe");
        request.setOwnerAddress("456 Elm St");
        request.setPhoneNumber("0987654321");
        request.setModel("MacBook Pro");
        request.setDate("2025-03-05");
        request.setProblemDescription("Battery not charging");

        assertEquals(2, request.getID());
        assertEquals("Jane Doe", request.getOwnerName());
        assertEquals("456 Elm St", request.getOwnerAddress());
        assertEquals("0987654321", request.getPhoneNumber());
        assertEquals("MacBook Pro", request.getModel());
        assertEquals("2025-03-05", request.getDate());
        assertEquals("Battery not charging", request.getProblemDescription());
    }
}
