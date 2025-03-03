package assessment.socialnetwork;
import java.util.*;

public class SocialNetwork {
    private Map<String, User> userMap;
    private Map<String, Set<String>> frdShipMap;

    public SocialNetwork() {
        userMap = new HashMap<>();
        frdShipMap = new HashMap<>();
    }

    public void addUser(User user) {
        if (userMap.containsKey(user.getuId())) {
            System.out.println("User already exists in the system.");
            return;
        }
        userMap.put(user.getuId(), user);
        frdShipMap.put(user.getuId(), new HashSet<>());
    }

    public void removeUser(String userId) {
        if (!userMap.containsKey(userId)) {
            System.out.println("Cannot Find User.");
            return;
        }
        userMap.remove(userId);
        frdShipMap.remove(userId);
        frdShipMap.values().forEach(friends -> friends.remove(userId));
    }

    public void listUsers() {
        userMap.values().forEach(user -> System.out.println(user.getuId() + " - " + user.getName()));
    }

    public void createFriendship(String userId1, String userId2) {
        if (!userMap.containsKey(userId1) || !userMap.containsKey(userId2)) {
            System.out.println("One or Both Users are not found.");
            return;
        }
        if (userId1.equals(userId2)) {
            System.out.println("User Cannot Be Friend with Themselves");
            return;
        }
        frdShipMap.get(userId1).add(userId2);
        frdShipMap.get(userId2).add(userId1);
    }

    public void removeFriendship(String userId1, String userId2) {
        if (!frdShipMap.containsKey(userId1) || !frdShipMap.containsKey(userId2)) {
            System.out.println("One or Both Users are not found.");
            return;
        }
        frdShipMap.get(userId1).remove(userId2);
        frdShipMap.get(userId2).remove(userId1);
    }

    public void listFriends(String userId) {
        if (!frdShipMap.containsKey(userId)) {
            System.out.println("User Cannot be found");
            return;
        }
        System.out.println("Friends of " + userId + ": " + frdShipMap.get(userId));
    }

    public List<String> findShortestPath(String userId1, String userId2) {
        if (!userMap.containsKey(userId1) || !userMap.containsKey(userId2)) {
            System.out.println("One or Both Users are not found.");
            return Collections.emptyList();
        }
        return bfsShortestPath(userId1, userId2);
    }

    private List<String> bfsShortestPath(String start, String end) {
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(Arrays.asList(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastNode = path.get(path.size() - 1);
            if (lastNode.equals(end)) return path;

            for (String neighbor : frdShipMap.getOrDefault(lastNode, new HashSet<>())) {
                if (!visited.contains(neighbor)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                    visited.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    public Map<String, Integer> calculateDegreeCentrality() {
        Map<String, Integer> centrality = new HashMap<>();
        frdShipMap.forEach((user, friends) -> centrality.put(user, friends.size()));
        return centrality;
    }
}
