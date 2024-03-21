
package DHT_AZIZ;
import java.util.HashMap;
import java.util.logging.Logger;

public class Node {
    private int nodeId;
    Node leftNeighbor;
    Node rightNeighbor;
    private HashMap<Integer, String> dataStorage; // Stockage des données
    private static final Logger LOGGER = Logger.getLogger(Node.class.getName());

    public Node(int nodeId) {
        this.nodeId = nodeId;
        this.leftNeighbor = null;
        this.rightNeighbor = null;
        this.dataStorage = new HashMap<>();
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
    // Méthode pour envoyer un message à un autre nœud
    public void sendMessage(Message message) {
        if (this.nodeId == message.getReceiverId()) {
            LOGGER.info(String.format("[%s] Message reçu par le nœud %d : %s",
                    new java.util.Date(), this.nodeId, message.getContent()));
        } else {
            LOGGER.info(String.format("[%s] Transfert du message du nœud %d au nœud %d",
                    new java.util.Date(), this.nodeId, this.rightNeighbor.getNodeId()));
            this.rightNeighbor.sendMessage(message);
        }
    }
    // Méthode pour stocker une donnée
    public void storeData(int dataId, String data) {
        // Stocker la donnée dans ce nœud
        this.dataStorage.put(dataId, data);
        System.out.println("Donnée stockée dans le nœud " + this.nodeId + ": " + data);

        // Réplication sur les voisins immédiats
        this.leftNeighbor.dataStorage.put(dataId, data);
        System.out.println("Donnée répliquée dans le nœud voisin gauche " + this.leftNeighbor.nodeId);

        this.rightNeighbor.dataStorage.put(dataId, data);
        System.out.println("Donnée répliquée dans le nœud voisin droit " + this.rightNeighbor.nodeId);
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
