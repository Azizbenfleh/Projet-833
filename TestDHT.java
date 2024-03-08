package DHT_AZIZ;

public class TestDHT {
    public static void main(String[] args) {
        DHT dht = new DHT();

        // Ajout de 4 nœuds avec des identifiants distincts
        System.out.println("Ajout des nœuds à la DHT...");
        dht.addNode(10);
        dht.addNode(20);
        dht.addNode(30);
        dht.addNode(40);

        System.out.println("\nÉtat de l'anneau DHT après l'ajout des nœuds :");
        dht.printRing();

        // Vous pouvez également tester la suppression d'un nœud si vous le souhaitez
        // System.out.println("\nSuppression du nœud avec l'ID 20...");
        // dht.removeNode(20);

        // System.out.println("\nÉtat de l'anneau DHT après la suppression du nœud :");
        // dht.printRing();
    }
}
