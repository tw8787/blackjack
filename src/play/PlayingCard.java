package play;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <b>PlayingCard</b> is an immutable playing card from a 52-card pack.
 */
public final class PlayingCard {

    /**
     * Rank of the card represented by this
     */
    public final int rank;

    /**
     * Suit of the card represented by this
     */
    public final char suit;

    /**
     * Set of possible suits
     */
    public final Set<Character> suits = new HashSet<>(Arrays.asList('H', 'D', 'C', 'S'));

    // Abstraction function:
    // A PlayingCard is uniquely identified by its:
    //      rank, where 1-10 -> ranks 1-10, 11 -> Jack, 12 -> Queen, 13 -> King
    //      suit, where H -> Hearts, D -> Diamonds, C -> Clubs, S -> Spades

    // Representation Invariant:
    // suits.contains(suit) &&
    // 0 <= rank <= 13

    /**
     * @param rank the rank of this
     * @param suit the suit of this
     * @throws IllegalArgumentException if {@code !suits.contains(suit) || rank < 1 || rank > 13}
     */
    public PlayingCard(int rank, char suit) {
        if(!suits.contains(Character.toUpperCase(suit)) || rank < 1 || rank > 13) {
            throw new IllegalArgumentException();
        }
        this.rank = rank;
        this.suit = Character.toUpperCase(suit);
        checkRep();
    }

    private void checkRep() {
        assert suits.contains(suit);
        assert rank >= 1;
        assert rank <= 13;
    }


}
