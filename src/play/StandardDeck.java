package play;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <b>StandardDeck</b> is an immutable representation of a shuffled deck of a given number of 52
 * unique card packs.
 *
 * <p>After every card has been retrieved from a StandardDeck, the deck will shuffle its cards as
 * to continue producing cards.
 */
public class StandardDeck {

    /**
     * List containing the cards in this
     */
    public List<PlayingCard> deck;

    /**
     * Index of the next card to be "removed" from this
     */
    private int index;

    /**
     * Number of 52 card packs in this
     */
    private final int numPacks;

    /**
     * Standard number of packs in a deck;
     */
    private static final int standardNumPack = 6;

    /**
     * Set of possible suits
     */
    private static final Set<Character> suits = new HashSet<>(Arrays.asList('H', 'D', 'C', 'S'));

    // Abstraction Function:
    // A StandardDeck is a collection of 52 (4 suits x 13 ranks) unique PlayingCards

    // Representation Invariant:
    // deck != null &&
    // 0 <= index <= deck.size() &&
    // numPacks >= 1 &&
    // forall PlayingCard s in deck, s != null &&
    // forall PlayingCard s in deck, there exists exactly 'numPacks' - 1 other PlayingCards with
    // the same rank and suit.

    /**
     * Constructs a new StandardDeck with 6 x 52 unique playing cards
     */
    public StandardDeck() {
        this(standardNumPack);
    }

    /**
     * Constructs a new StandardDeck with 'numPacks' x 52 unique playing cards
     *
     * @param numPacks number of 52 card packs in this
     * @throws IllegalArgumentException if {@code numPacks < 1}
     */
    public StandardDeck(int numPacks) {
        if(numPacks < 1) {
            throw new IllegalArgumentException();
        }
        deck = new ArrayList<>();
        index = 0;
        this.numPacks = numPacks;
        for(int i = 0; i < numPacks; i++) {
            for(int j = 1; j <= 13; j++) {
                for(char suit : suits) {
                    deck.add(new PlayingCard(j, suit));
                }
            }
        }
        Collections.shuffle(deck);
        checkRep();
    }

    /**
     * Draws the next card in this. Shuffles the deck before drawing if every card in this have
     * already been drawn.
     *
     * @return the next PlayingCard in this
     */
    public PlayingCard drawNext() {
        checkRep();
        if(index == deck.size()) {
            Collections.shuffle(deck);
            index = 0;
        }
        PlayingCard nextCard = deck.get(index);
        index++;
        checkRep();
        return new PlayingCard(nextCard.rank, nextCard.suit);
    }

    /**
     * Checks the the representation invariant.
     *
     * @throws AssertionError representation invariant is broken
     */
    private void checkRep() {
        assert deck != null;
        assert index >= 0;
        assert index <= deck.size();
        assert numPacks >= 1;
        for(PlayingCard card : deck) {
            assert card != null;
        }
        // The following counts the copies of cards in deck, where each index represents a
        // different card.
        //
        // for the card at sortedDeck[i]:
        // its rank is represented by i mod 13 where 1-10 = ranks 1-10, 11 = Jack, 12 = Queen,
        // 0 = King
        //
        // its suit is represented by i/13 where 0 = Hearts, 1 = Diamonds 2 = Clubs, and 3 = Spades
        int[] sortedDeck = new int[52];
        for(PlayingCard card : deck) {
            int result = 0;
            if(card.suit == 'D') {
                result = 13;
            } else if (card.suit == 'C') {
                result = 26;
            } else if (card.suit == 'S') {
                result = 39;
            }
            if(card.rank != 13) {
                result += card.rank;
            }
            sortedDeck[result]++;
        }
        // Checks if there are 'numPacks' copies of each card
        for(int i : sortedDeck) {
            assert i == numPacks;
        }
    }

}
