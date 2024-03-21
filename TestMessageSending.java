package DHT_AZIZ;

import java.util.logging.Logger;

public class TestMessageSending {
    private static final Logger LOGGER = Logger.getLogger(TestMessageSending.class.getName());

    public static void main(String[] args) {
        // Création de la DHT et ajout de nœuds
        DHT dht = new DHT();
        dht.addNode(1); // ID de nœud
        dht.addNode(5);
        dht.addNode(10);
        dht.addNode(15);

        LOGGER.info("État de l'anneau DHT après l'ajout des nœuds :");
        dht.printRing();

        // Supposons que nous voulons envoyer un message du nœud 1 au nœud 15
        Message message = new Message(1, 15, "Bonjour du nœud 1 au nœud 15!");

        // Trouver le nœud émetteur
        Node senderNode = dht.findNodeById(1);
        if (senderNode != null) {
            LOGGER.info("Envoi d'un message du nœud " + senderNode.getNodeId() + " au nœud " + message.getReceiverId());
            senderNode.sendMessage(message);
        } else {
            LOGGER.warning("Nœud émetteur non trouvé.");
        }
    }
}
