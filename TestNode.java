package DHT_AZIZ;

public class TestNode {

    public static void main(String[] args) {
        // Test the join/leave functionality
        Node Node1 = new Node(1);
        Node Node2 = new Node(2);
        Node Node3 = new Node(3);
        Node Node4 = new Node(4);
        Node Node5 = new Node(5);
        
        
        
        Node3.joinLeft(Node1);
        Node2.joinRight(Node1);
        Node4.joinLeft(Node2);
        Node5.joinRight(Node2);
        
        
        // Test the neighbors of each Node
        System.out.println("Node 4 neighbors: " + Node4.getLeftNeighbor().getNodeId() + ", " + Node4.getRightNeighbor().getNodeId());
        System.out.println("Node 2 neighbors: " + Node2.getLeftNeighbor().getNodeId() + ", " + Node2.getRightNeighbor().getNodeId());
        System.out.println("Node 3 neighbors: " + Node3.getLeftNeighbor().getNodeId() + ", " + Node3.getRightNeighbor().getNodeId());

        // Node 2 leaves
        Node2.leave();

        // Test the neighbors after Node2 leaves
        System.out.println("Node 1 neighbors: " + Node1.getLeftNeighbor().getNodeId() + ", " + Node1.getRightNeighbor().getNodeId());
        System.out.println("Node 3 neighbors: " + Node3.getLeftNeighbor().getNodeId() + ", " + Node3.getRightNeighbor().getNodeId());
    }
}
