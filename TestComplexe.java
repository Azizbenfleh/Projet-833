package DHT_AZIZ;

import java.util.logging.Logger;

public class TestComplexe {
    private static final Logger LOGGER = Logger.getLogger(TestComplexe.class.getName());

    public static void main(String[] args) {
        DHT dht = new DHT();

        // Ajout de nœuds avec des identifiants distincts
        LOGGER.info("Ajout des nœuds à la DHT...");
        dht.addNode(10);
        dht.addNode(20);
        dht.addNode(30);
        dht.addNode(40);

        LOGGER.info("\nÉtat de l'anneau DHT après l'ajout des nœuds :");
        dht.printRing();

        // Tester l'envoi de messages
        LOGGER.info("\nTest d'envoi de messages :");
        Node sender = dht.findNodeById(10);
        if (sender != null) {
            sender.sendMessage(new Message(10, 40, "Hello du nœud 10 au nœud 40!"));
        }

        // Tester le stockage et la récupération de données
        LOGGER.info("\nTest de stockage de données :");
        dht.storeDataInDHT(25, "Donnée associée à l'ID 25");
        dht.storeDataInDHT(35, "Donnée associée à l'ID 35");

        // Suppression d'un nœud et observation des effets sur l'anneau et le routage de message
        LOGGER.info("\nSuppression du nœud avec l'ID 20 et test de routage post-suppression :");
        dht.removeNode(20);

        LOGGER.info("\nÉtat de l'anneau DHT après la suppression du nœud :");
        dht.printRing();

        // Essai d'envoyer un message après la suppression d'un nœud pour voir le routage ajusté
        if (dht.findNodeById(10) != null) {
            dht.findNodeById(10).sendMessage(new Message(10, 40, "Hello du nœud 10 au nœud 40, après ajustement!"));
        }

        // Essai de rajouter un nœud après la suppression
        LOGGER.info("\nAjout d'un nœud après la suppression :");
        dht.addNode(20);
        LOGGER.info("\nÉtat de l'anneau DHT après l'ajout du nœud :");
        dht.printRing();

    }
}
