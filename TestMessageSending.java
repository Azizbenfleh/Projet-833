package DHT_AZIZ;

public class TestMessageSending {
    public static void main(String[] args) {
        // Création de la DHT et ajout de nœuds
        DHT dht = new DHT();
        dht.addNode(1); // ID de nœud
        dht.addNode(5);
        dht.addNode(10);
        dht.addNode(15);

        // Supposons que nous voulons envoyer un message du nœud 1 au nœud 15
        Message message = new Message(1, 15, "Bonjour du nœud 1 au nœud 15!");

        // Trouver le nœud émetteur (dans un vrai scénario, le nœud connaîtrait déjà cette information)
        Node senderNode = dht.findNodeById(1);
        if (senderNode != null) {
            senderNode.sendMessage(message);
        } else {
            System.out.println("Nœud émetteur non trouvé.");
        }
    }
}