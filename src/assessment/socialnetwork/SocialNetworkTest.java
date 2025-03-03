package assessment.socialnetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class SocialNetworkTest {
    private SocialNetwork network;

    @BeforeEach
    void setUp() {
        network = new SocialNetwork();
    }

    @Test
    void testAddUser() {
        User user1 = new User("1", "user1", "user1@example.com");
        network.addUser(user1);

        assertEquals(1, network.calculateDegreeCentrality().size());
        assertTrue(network.calculateDegreeCentrality().containsKey("1"));
    }

    @Test
    void testAddDuplicateUser() {
        User user1 = new User("1", "user1", "user1@example.com");
        network.addUser(user1);
        network.addUser(user1); // Adding same user again

        assertEquals(1, network.calculateDegreeCentrality().size());
    }

    @Test
    void testRemoveUser() {
        User user1 = new User("1", "user1", "user1@example.com");
        network.addUser(user1);
        network.removeUser("1");

        assertFalse(network.calculateDegreeCentrality().containsKey("1"));
    }

    @Test
    void testCreateFriendship() {
        network.addUser(new User("1", "user1", "user1@example.com"));
        network.addUser(new User("2", "user2", "user2@example.com"));

        network.createFriendship("1", "2");

        Map<String, Integer> centrality = network.calculateDegreeCentrality();
        assertEquals(1, centrality.get("1"));
        assertEquals(1, centrality.get("2"));
    }

    @Test
    void testCreateDuplicateFriendship() {
        network.addUser(new User("1", "user1", "user1@example.com"));
        network.addUser(new User("2", "user2", "user2@example.com"));

        network.createFriendship("1", "2");
        network.createFriendship("1", "2"); // Duplicate should be ignored

        Map<String, Integer> centrality = network.calculateDegreeCentrality();
        assertEquals(1, centrality.get("1"));
        assertEquals(1, centrality.get("2"));
    }

    @Test
    void testRemoveFriendship() {
        network.addUser(new User("1", "user1", "user1@example.com"));
        network.addUser(new User("2", "user2", "user2@example.com"));

        network.createFriendship("1", "2");
        network.removeFriendship("1", "2");

        Map<String, Integer> centrality = network.calculateDegreeCentrality();
        assertEquals(0, centrality.get("1"));
        assertEquals(0, centrality.get("2"));
    }

    @Test
    void testFindShortestPath() {
        network.addUser(new User("1", "user1", "user1@example.com"));
        network.addUser(new User("2", "user2", "user2@example.com"));
        network.addUser(new User("3", "user3", "user3@example.com"));
        network.createFriendship("1", "2");
        network.createFriendship("2", "3");

        List<String> path = network.findShortestPath("1", "3");
        assertEquals(Arrays.asList("1", "2", "3"), path);
    }

    @Test
    void testFindShortestPathNoConnection() {
        network.addUser(new User("1", "user1", "user1@example.com"));
        network.addUser(new User("2", "user2", "user2@example.com"));

        List<String> path = network.findShortestPath("1", "2");
        assertTrue(path.isEmpty()); // No connection
    }

    @Test
    void testCalculateDegreeCentrality() {
        network.addUser(new User("1", "user1", "user1@example.com"));
        network.addUser(new User("2", "user2", "user2@example.com"));
        network.addUser(new User("3", "user3", "user3@example.com"));
        
        network.createFriendship("1", "2");
        network.createFriendship("1", "3");

        Map<String, Integer> centrality = network.calculateDegreeCentrality();
        assertEquals(2, centrality.get("1")); 
        assertEquals(1, centrality.get("2"));
        assertEquals(1, centrality.get("3"));
    }
}
