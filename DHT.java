package DHT_AZIZ;

import java.util.ArrayList;
import java.util.List;

public class DHT {
    private List<Node> nodes;

    public DHT() {
        this.nodes = new ArrayList<>();
    }

    // Méthode pour ajouter un nœud dans l'anneau
    // Méthode pour ajouter un nœud dans l'anneau
    public void addNode(int nodeId) {
        Node newNode = new Node(nodeId);
        if (this.nodes.isEmpty()) {
            // Si c'est le premier nœud de l'anneau
            newNode.leftNeighbor = newNode; // Il devient son propre voisin de gauche
            newNode.rightNeighbor = newNode; // Et son propre voisin de droite
            this.nodes.add(newNode);
        } else {
            // Trouver l'index où insérer le nouveau nœud
            int index = 0;
            while (index < this.nodes.size() && this.nodes.get(index).getNodeId() < nodeId) {
                index++;
            }
            this.nodes.add(index, newNode); // Insère le nouveau nœud à l'index trouvé

            // Mise à jour des voisins
            Node leftNeighbor, rightNeighbor;
            if (index == 0) {
                // Insertion au début
                leftNeighbor = this.nodes.get(this.nodes.size() - 1); // Dernier devient le voisin gauche
                rightNeighbor = this.nodes.get(1); // Le suivant dans la liste devient le voisin droit
            } else if (index == this.nodes.size() - 1) {
                // Insertion à la fin
                leftNeighbor = this.nodes.get(index - 1); // Le précédent dans la liste devient le voisin gauche
                rightNeighbor = this.nodes.get(0); // Le premier devient le voisin droit
            } else {
                // Insertion au milieu
                leftNeighbor = this.nodes.get(index - 1);
                rightNeighbor = this.nodes.get(index + 1);
            }

            // Mise à jour des références des voisins
            newNode.leftNeighbor = leftNeighbor;
            newNode.rightNeighbor = rightNeighbor;
            leftNeighbor.rightNeighbor = newNode;
            rightNeighbor.leftNeighbor = newNode;
        }

        // Correction nécessaire seulement si l'anneau a plus de deux nœuds
        if (this.nodes.size() > 2) {
            // Ajustement des voisins pour le cas où l'insertion est au début ou à la fin
            this.nodes.get(0).leftNeighbor = this.nodes.get(this.nodes.size() - 1);
            this.nodes.get(this.nodes.size() - 1).rightNeighbor = this.nodes.get(0);
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

    public void storeDataInDHT(int dataId, String data) {
        Node closestNode = findClosestNode(dataId);
        closestNode.storeData(dataId, data);
    }

    private Node findClosestNode(int dataId) {
        Node closestNode = null;
        int closestDistance = Integer.MAX_VALUE;

        for (Node node : this.nodes) {
            int currentDistance = Math.abs(node.getNodeId() - dataId);
            if (currentDistance < closestDistance) {
                closestDistance = currentDistance;
                closestNode = node;
            }
        }

        return closestNode;
    }
    public Node findNodeById(int nodeId) {
        for (Node node : this.nodes) {
            if (node.getNodeId() == nodeId) {
                return node;
            }
        }
        return null; // Retourne null si le nœud n'est pas trouvé
    }
    // Méthode pour afficher l'état actuel de l'anneau
    public void printRing() {
        for (Node node : this.nodes) {
            System.out.println("Node ID: " + node.getNodeId() + " Left Neighbor: " + node.getLeftNeighbor().getNodeId() + " Right Neighbor: " + node.getRightNeighbor().getNodeId());
        }
    }
}
