package DHT_AZIZ;

import java.util.ArrayList;
import java.util.List;

public class DHT {
    private List<Node> nodes;

    public DHT() {
        this.nodes = new ArrayList<>();
    }

    // Méthode pour ajouter un nœud dans l'anneau
    public void addNode(int nodeId) {
        Node newNode = new Node(nodeId);
        if (this.nodes.isEmpty()) {
            // Premier nœud de l'anneau
            newNode.joinRight(newNode); // Il devient son propre voisin de droite et de gauche
            this.nodes.add(newNode);
        } else {
            // Insérer le nouveau nœud en maintenant l'ordre des identifiants
            int index = 0;
            while (index < this.nodes.size() && this.nodes.get(index).getNodeId() < nodeId) {
                index++;
            }
            this.nodes.add(index % this.nodes.size(), newNode);

            // Mise à jour des voisins
            if (this.nodes.size() > 1) {
                Node leftNeighbor = (index == 0) ? this.nodes.get(this.nodes.size() - 2) : this.nodes.get((index - 1) % this.nodes.size());
                Node rightNeighbor = this.nodes.get((index + 1) % this.nodes.size());
                newNode.joinRight(leftNeighbor);
                newNode.joinLeft(rightNeighbor);
            }
        }
        // Mise à jour des liaisons pour tous les nœuds après ajout
        for (int i = 0; i < this.nodes.size(); i++) {
            Node current = this.nodes.get(i);
            Node left = this.nodes.get((i + this.nodes.size() - 1) % this.nodes.size()); // Précédent dans la liste ou dernier si premier
            Node right = this.nodes.get((i + 1) % this.nodes.size()); // Suivant dans la liste ou premier si dernier
            current.leftNeighbor = left;
            current.rightNeighbor = right;
        }
    }


    // Méthode pour retirer un nœud de l'anneau
    public void removeNode(int nodeId) {
        Node nodeToRemove = null;
        for (Node node : this.nodes) {
            if (node.getNodeId() == nodeId) {
                nodeToRemove = node;
                break;
            }
        }
        if (nodeToRemove != null) {
            nodeToRemove.leave(); // Le nœud contacte ses voisins pour qu'ils se connectent entre eux
            this.nodes.remove(nodeToRemove);
        }
    }

    // Méthode pour afficher l'état actuel de l'anneau
    public void printRing() {
        for (Node node : this.nodes) {
            System.out.println("Node ID: " + node.getNodeId() + " Left Neighbor: " + node.getLeftNeighbor().getNodeId() + " Right Neighbor: " + node.getRightNeighbor().getNodeId());
        }
    }
}
