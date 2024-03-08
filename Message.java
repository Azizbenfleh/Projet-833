package DHT_AZIZ;

public class Message {
    private int senderId; // Identifiant du nœud expéditeur
    private int receiverId; // Identifiant du nœud destinataire
    private String content; // Contenu du message

    // Constructeur
    public Message(int senderId, int receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    // Getters et Setters
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Méthode pour afficher le message
    @Override
    public String toString() {
        return "Message from Node " + senderId + " to Node " + receiverId + ": " + content;
    }
}
