import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A utility class for generating and manipulating poker hands.
public class Poker {

    private static final String[] DECK = {
        "cA", "cK", "cQ", "cJ", "c10", "c9", "c8", "c7", "c6", "c5", "c4", "c3", "c2",
        "pA", "pK", "pQ", "pJ", "p10", "p9", "p8", "p7", "p6", "p5", "p4", "p3", "p2",
        "hA", "hK", "hQ", "hJ", "h10", "h9", "h8", "h7", "h6", "h5", "h4", "h3", "h2",
        "dA", "dK", "dQ", "dJ", "d10", "d9", "d8", "d7", "d6", "d5", "d4", "d3", "d2"
    };

    public List<String[]> playerCombinations = new ArrayList<>();
    public List<String[]> opponentCombinations = new ArrayList<>();

    public String[] discardCards(String[] hand, String[] toDiscard) {
        List<String> result = new ArrayList<>();
        List<String> discardList = Arrays.asList(toDiscard);

        for (String card : hand) {
            if (!discardList.contains(card)) {
                result.add(card);
            }
        }

        return result.toArray(new String[0]);
    }

    public void generatePlayerHands(String[] available, String[] current) {
        if (current.length == 5) {
            playerCombinations.add(current);
            return;
        }

        for (int i = 0; i < available.length; i++) {
            String[] next = Arrays.copyOf(current, current.length + 1);
            next[current.length] = available[i];
            String[] remaining = Arrays.copyOfRange(available, i + 1, available.length);
            generatePlayerHands(remaining, next);
        }
    }

    public void generateOpponentHands(String[] available, String[] community, String[] current) {
        if (current.length == 5) {
            long communityCount = Arrays.stream(current)
                    .filter(card -> Arrays.asList(community).contains(card))
                    .count();

            if (communityCount >= 3 && isUniqueCombination(current, opponentCombinations)) {
                opponentCombinations.add(current);
            }
            return;
        }

        for (int i = 0; i < available.length; i++) {
            String[] next = Arrays.copyOf(current, current.length + 1);
            next[current.length] = available[i];

            String[] remaining = new String[available.length - 1];
            for (int j = 0, idx = 0; j < available.length; j++) {
                if (j != i) remaining[idx++] = available[j];
            }

            generateOpponentHands(remaining, community, next);
        }
    }

    private boolean isUniqueCombination(String[] combination, List<String[]> combinations) {
        String[] sorted = Arrays.copyOf(combination, combination.length);
        Arrays.sort(sorted);

        for (String[] combo : combinations) {
            String[] sortedCombo = Arrays.copyOf(combo, combo.length);
            Arrays.sort(sortedCombo);
            if (Arrays.equals(sorted, sortedCombo)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Poker poker = new Poker();

        // -- TESTING PURPOSES: Example hand and discard --
        String[] cards = {"c2", "c3", "c4", "c5", "c6", "c7"};
        String[] discard = {"c4"};
        String[] result = poker.discardCards(cards, discard);
        System.out.println("After discard: " + Arrays.toString(result));

        // -- TESTING PURPOSES: Generate all player 5-card hands --
        poker.generatePlayerHands(cards, new String[0]);
        System.out.println("Player hands:");
        for (String[] hand : poker.playerCombinations) {
            System.out.println(Arrays.toString(hand));
        }

        // -- TESTING PURPOSES: Generate opponent hands using community cards --
        String[] community = {"c2", "c3", "c4"};
        poker.generateOpponentHands(cards, community, new String[0]);
        System.out.println("Opponent hands:");
        for (String[] hand : poker.opponentCombinations) {
            System.out.println(Arrays.toString(hand));
        }
    }
}
