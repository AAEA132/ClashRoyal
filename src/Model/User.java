package Model;

public class User {
    public static String username;
    public static int[] cards = new int[8];

    public User(String username) {
        User.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        User.username = username;
    }

    public int getIndexCard(int index){
        return cards[index];
    }
    public void setIndexCard(int index, int value){
        cards[index] = value;
    }
    public int[] getCards() {
        return cards;
    }

    public void setCards(int[] cards) {
        User.cards = cards;
    }
}
