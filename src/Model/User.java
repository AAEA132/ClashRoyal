package Model;

/**
 * The type User.
 */
public class User {
    /**
     * The constant username.
     */
    public static String username;
    /**
     * The constant cards.
     */
    public static int[] cards = new int[8];

    /**
     * Instantiates a new User.
     *
     * @param username the username
     */
    public User(String username) {
        User.username = username;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        User.username = username;
    }

    /**
     * Get index card int.
     *
     * @param index the index
     * @return the int
     */
    public int getIndexCard(int index){
        return cards[index];
    }

    /**
     * Set index card.
     *
     * @param index the index
     * @param value the value
     */
    public void setIndexCard(int index, int value){
        cards[index] = value;
    }

    /**
     * Get cards int [ ].
     *
     * @return the int [ ]
     */
    public int[] getCards() {
        return cards;
    }

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards(int[] cards) {
        User.cards = cards;
    }
}
