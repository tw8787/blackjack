package play;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <b>PlayingCard</b> is an immutable playing card from a 52-card pack.
 *
 * <p>The ranks are represented as their numbers and 11 = Jack, 12 = Queen, and 13 = King, and the
 * suits are represented as their first letter H(Hearts), D(Diamonds), C(Clubs), S(Spades).
 *
 * <p>Typical playing cards are represented as 2S (2 of Spades), 10H (10 of Hearts), and KD (King
 * of Diamonds).
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
    private static final Set<Character> suits = new HashSet<>(Arrays.asList('H', 'D', 'C', 'S'));

    // Abstraction Function:
    // A PlayingCard is uniquely identified by its:
    //      rank, where 1-10 -> ranks 1-10, 11 -> (J) Jack, 12 -> (Q) Queen, 13 -> (K) King
    //      suit, where H -> Hearts, D -> Diamonds, C -> Clubs, S -> Spades

    // Representation Invariant:
    // suits.contains(suit) &&
    // 0 <= rank <= 13

    /**
     * Constructs a new PlayingCard with rank 'rank' and suit 'suit'
     *
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

    /**
     * Returns the string representation of this. Possible examples include "3S", "6H", and "JD".
     *
     * @return a string representation of this in the form rank + suit with no whitespace
     */
    @Override
    public String toString() {
        String result = "" + rank;
        if(rank == 11) {
            result = "J";
        } else if (rank == 12) {
            result = "Q";
        } else if (rank == 13) {
            result = "K";
        }
        return result + suit;
    }

    /**
     * Checks the the representation invariant.
     *
     * @throws AssertionError representation invariant is broken
     */
    private void checkRep() {
        assert suits.contains(suit);
        assert rank >= 1;
        assert rank <= 13;
    }
}
