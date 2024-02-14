package DHT_AZIZ;

public class Test {

    public static void main(String[] args) {
        // Test the join/leave functionality
        NODE node1 = new NODE(1);
        NODE node2 = new NODE(2);
        NODE node3 = new NODE(3);
        NODE node4 = new NODE(4);
        NODE node5 = new NODE(5);
        
        
        
        node3.joinLeft(node1);
        node2.joinRight(node1);
        node4.joinLeft(node2);
        node5.joinRight(node2);
        
        
        // Test the neighbors of each node
        System.out.println("Node 4 neighbors: " + node4.getLeftNeighbor().getNodeId() + ", " + node4.getRightNeighbor().getNodeId());
        System.out.println("Node 2 neighbors: " + node2.getLeftNeighbor().getNodeId() + ", " + node2.getRightNeighbor().getNodeId());
        System.out.println("Node 3 neighbors: " + node3.getLeftNeighbor().getNodeId() + ", " + node3.getRightNeighbor().getNodeId());

        // Node 2 leaves
        node2.leave();

        // Test the neighbors after node2 leaves
        System.out.println("Node 1 neighbors: " + node1.getLeftNeighbor().getNodeId() + ", " + node1.getRightNeighbor().getNodeId());
        System.out.println("Node 3 neighbors: " + node3.getLeftNeighbor().getNodeId() + ", " + node3.getRightNeighbor().getNodeId());
    }
}
