
package DHT_AZIZ;

public class Node {
    private int nodeId;
    Node leftNeighbor;
    Node rightNeighbor;

    public Node(int nodeId) {
        this.nodeId = nodeId;
        this.leftNeighbor = null;
        this.rightNeighbor = null;
    }

    public void joinRight(Node existingNode) {
        if (existingNode == null) {
            // Si existingNode est null, le nouveau nœud est le premier dans la DHT
            this.leftNeighbor = this;
            this.rightNeighbor = this;
        } else {
            // Insérer le nouveau nœud à droite de existingNode
            if (existingNode.rightNeighbor != null) {
                this.rightNeighbor = existingNode.rightNeighbor;
                this.leftNeighbor = existingNode;
                existingNode.rightNeighbor.leftNeighbor = this;
                existingNode.rightNeighbor = this;
            } else {
                // Si existingNode n'a pas de voisin droit, le nouveau nœud devient son voisin droit
                existingNode.rightNeighbor = this;
                this.leftNeighbor = existingNode;
            }
        }
    }


    public void joinLeft(Node existingNode) {
        if (existingNode == null) {
            // Si existingNode est null, le nouveau nœud est le premier dans la DHT
            this.leftNeighbor = this;
            this.rightNeighbor = this;
        } else {
            // Insérer le nouveau nœud à gauche de existingNode
            if (existingNode.leftNeighbor != null) {
                this.leftNeighbor = existingNode.leftNeighbor;
                this.rightNeighbor = existingNode;
                existingNode.leftNeighbor.rightNeighbor = this;
                existingNode.leftNeighbor = this;
            } else {
                // Si existingNode n'a pas de voisin gauche, le nouveau nœud devient son voisin gauche
                existingNode.leftNeighbor = this;
                this.rightNeighbor = existingNode;
                this.leftNeighbor = existingNode;
            }
        }
    }


    public void leave() {
        if (this.leftNeighbor == this && this.rightNeighbor == this) {
            // Seul nœud dans la DHT, pas besoin de mettre à jour les voisins
            return;
        }
        // Mettre à jour les voisins pour supprimer ce nœud
        this.leftNeighbor.rightNeighbor = this.rightNeighbor;
        this.rightNeighbor.leftNeighbor = this.leftNeighbor;
    }

    public int getNodeId() {
        return nodeId;
    }

    public Node getLeftNeighbor() {
        return leftNeighbor;
    }

    public Node getRightNeighbor() {
        return rightNeighbor;
    }
}
