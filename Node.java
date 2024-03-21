
package DHT_AZIZ;
import java.util.HashMap;
import java.util.logging.Logger;

public class Node {
    private int nodeId;
    Node leftNeighbor;
    Node rightNeighbor;
    private HashMap<Integer, String> dataStorage; // Stockage des données
    private static final Logger LOGGER = Logger.getLogger(Node.class.getName());

    //Partie avancée non terminée
    //Triche pour les liens longs
    /*private Node longLink;

    // Méthode dans DHT pour établir des liens longs lors de l'ajout d'un nœud
    public void addLongLink(Node newNode) {
        // Exemple : lien vers le nœud avec un ID le plus proche de (nodeId + 10) comme lien long
        Node longLinkNode = findClosestNode(newNode.getNodeId() + 10);
        newNode.setLongLink(longLinkNode);
    }*/
    /*
    // Exemple de méthode dans Node pour gérer les informations piggybacked
    public void handleMessage(Message message) {
        // Exemple de mise à jour de la table de routage avec des infos piggybacked
        updateRoutingTableWithPiggybackedInfo(message.getPiggybackedInfo());

        // Poursuivre le routage du message comme précédemment
        sendMessage(message);
    }*/


    public Node(int nodeId) {
        this.nodeId = nodeId;
        this.leftNeighbor = null;
        this.rightNeighbor = null;
        this.dataStorage = new HashMap<>();
    }

    // Méthode renommée pour refléter son but de mise à jour des voisins
    public void updateNeighbors(Node leftNeighbor, Node rightNeighbor) {
        this.leftNeighbor = leftNeighbor;
        this.rightNeighbor = rightNeighbor;
        leftNeighbor.rightNeighbor = this;
        rightNeighbor.leftNeighbor = this;
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
