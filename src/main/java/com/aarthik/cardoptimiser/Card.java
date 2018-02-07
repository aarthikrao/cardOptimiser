package com.aarthik.cardoptimiser;

/**
 * Card POJO DO NOT MODIFY THIS CLASS
 **/
public class Card implements Comparable {
    // private static final long serialVersionUID = 3624237855955516867L;
    // public static final int ACE = 14;
    // public static final int TWO = 2;
    // public static final int THREE = 3;
    // public static final int FOUR = 4;
    // public static final int FIVE = 5;
    // public static final int SIX = 6;
    // public static final int SEVEN = 7;
    // public static final int EIGHT = 8;
    // public static final int NINE = 9;
    // public static final int TEN = 10;
    // public static final int JACK = 11;
    // public static final int QUEEN = 12;
    // public static final int KING = 13;
    public static final int CLUBS = 1;
    public static final int DIAMONDS = 2;
    public static final int HEARTS = 3;
    public static final int SPADES = 4;
     public static final int MAX_RANK = 14;
     public static final int FIRST_RANK = 2;
    private int rank;
    private int suite;

    /*
     * Card created from its string annotation like 12s @param cid String
     */
    public Card(String cid) {
        if (cid.equals("j")) {
            this.rank = 0;
            this.suite = 0;
        } else {
            this.rank = Integer.parseInt(cid.substring(0, cid.length() - 1));
            if (rank == 1)
                rank = 14;
            String s = cid.substring(cid.length() - 1);
            if (s.equals("c"))
                this.suite = CLUBS;
            else if (s.equals("s"))
                this.suite = SPADES;
            else if (s.equals("d"))
                this.suite = DIAMONDS;
            else if (s.equals("h"))
                this.suite = HEARTS;
        }
    }

    /*
     * returns the suit of the card. @ return suit Integer
     */
    public int getSuite() {
        return suite;
    }

    /*
     * returns the rank of the card. @ return rank Integer.
     */
    public int getRank() {
        return rank;
    }
    

    /*
     * checks if the object passed is equal to the same card
     * 
     * @see java.lang.Object#equals(java.lang.Object) @ return boolean
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Card)) {
            return false;
        }
        Card casted = (Card) obj;
        if (compareTo(casted) == 0) {
            return true;
        }
        return false;
    }

    /*
     * checks if the object passeed is smaller or greater then the card @ return
     * Integer
     */
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }
        Card card = (Card) o;
        if (rank < card.getRank()) {
            return 1;
        } else if (rank == card.getRank()) {
            if (suite < card.getSuite()) {
                return 1;
            } else if (suite == card.getSuite()) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    @Override
    public int hashCode() {
        return rank + suite;
    }

    public String toString() {
        String s;
        if (rank == 0 & suite == 0) {
            s = "j";
        } else {
                s = "" + this.rank;
            // String s = ""+this.rank;
            switch (suite) {
            case CLUBS:
                return s + "c";
            case HEARTS:
                return s + "h";
            case SPADES:
                return s + "s";
            case DIAMONDS:
                return s + "d";
            }
        }
        return s;
    }   
}