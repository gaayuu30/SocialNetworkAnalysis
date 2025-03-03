package assessment.socialnetwork;

public class InvokeMe {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();

        network.addUser(new User("1", "user1", "user1@example.com"));
        network.addUser(new User("2", "user2", "user2@example.com"));
        network.addUser(new User("3", "user3", "user3@example.com"));
        network.addUser(new User("4", "user4", "user4@example.com"));

        network.createFriendship("1", "2");
        network.createFriendship("2", "3");
        network.createFriendship("3", "4");

        network.listFriends("1");

        System.out.println("Shortest path between 1 and 3: " + network.findShortestPath("2", "4"));
        System.out.println("Degree centrality: " + network.calculateDegreeCentrality());
    }
}