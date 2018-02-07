package com.aarthik.cardoptimiser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CardOptimiser {
    public static final int CLUBS = 0;
    public static final int DIAMONDS = 1;
    public static final int HEARTS = 2;
    public static final int SPADES = 3;
    static List<List<Card>> sortedList = new ArrayList<List<Card>>();
    static List<Card> jokerList = new ArrayList<Card>();
    static List<Card> iseqlist = new ArrayList<Card>();

    private static final int THREE = 3;
    private static final int TWO = 2;

    private static final Card JOKER = new Card("11h");

    private static String[][] handCardsData = {
            // { "14s", "5s", "7s", "11c", "9d", "4d", "9d", "14c", "14h", "7c",
            // "8s", "8h", "8c" },
            // { "4d", "7c", "1s", "9d", "1c", "9d", "8c", "1h", "7s", "11c",
            // "8s", "5s", "8h" },
            // { "5s", "4d", "7c", "6d", "11c", "5d", "8s", "5c", "11d", "11s",
            // "10s", "7s", "9s" },
            // { "1s", "5s", "7s", "6d", "9d", "4d", "9d", "1c", "5c", "7c",
            // "4h", "8h", "8c" },
            // { "1s", "5s", "7s", "6d", "9d", "4d", "9d", "1c", "5c", "7c",
            // "8s", "8h", "8c" },
            // { "1s", "5s", "7s", "6d", "9d", "4d", "9d", "1c", "1h", "7c",
            // "8s", "8h", "8c" },
            // { "11s", "5s", "7s", "6d", "9d", "4d", "9d", "1c", "5c", "7c",
            // "4h", "12h", "8c" },
            // { "11s", "5s", "7s", "6d", "9d", "4d", "9d", "1c", "5c", "7c",
            // "8s", "8h", "8c" },
            // { "1s", "5s", "7s", "11c", "9d", "4d", "9d", "1c", "1h", "7c",
            // "8s", "8h", "8c" },
            // { "11s", "5s", "7s", "6d", "11d", "4d", "9d", "1c", "5c", "7c",
            // "4h", "12h", "8c" },
            // { "11s", "5s", "7s", "6d", "11d", "4d", "9d", "1c", "5c", "7c",
            // "8s", "8h", "8c" },
            // { "11s", "5s", "7s", "6d", "11d", "4d", "12d", "12c", "12h",
            // "7c", "8s", "8h", "8c" },
            // { "11s", "5s", "7s", "6d", "11d", "4d", "12d", "1d", "11h", "7c",
            // "8s", "8h", "8c" },
            // { "6s", "5s", "7s", "6d", "9d", "4d", "9d", "1c", "5c", "7c",
            // "4h", "12h", "8c" },
            // { "6s", "5s", "7s", "6d", "9d", "4d", "9d", "1c", "5c", "7c",
            // "8s", "8h", "8c" },
            // { "1s", "5s", "7s", "6s", "9d", "4d", "9d", "1c", "1h", "7c",
            // "8s", "8h", "8c" },
            // { "11s", "5s", "7s", "6d", "5d", "4d", "9d", "1c", "5c", "7c",
            // "4h", "12h", "8c" },
            // { "11s", "5s", "7s", "6d", "5d", "4d", "9d", "1c", "5c", "7c",
            // "8s", "8h", "8c" },
            // { "6s", "5s", "7s", "11c", "6d", "4d", "9d", "1c", "1h", "1s",
            // "8s", "8h", "8c" },
            // { "11s", "5s", "7s", "6d", "5d", "4d", "9d", "11c", "5c", "7c",
            // "4h", "12h", "8c" },
            { "11s", "5s", "7s", "6d", "5d", "4d", "9d", "11c", "5c", "7c", "8s", "8h", "8c" },
            { "11s", "5s", "7s", "6d", "5d", "4d", "12d", "11c", "5c", "7c", "8s", "9s", "10s" },
            { "11s", "5s", "7s", "6d", "5d", "4d", "11d", "11c", "5c", "7c", "8s", "9s", "10s" } };

    public static void main(String[] args) {
        List<Card> mainCardList = new ArrayList<Card>();
        jokerList.clear();
        for (String[] one : handCardsData) {
            mainCardList.clear();
            for (String card : one) {
                Card thisCard = new Card(card);
                mainCardList.add(thisCard);
                if (isJoker(JOKER, thisCard)) {
                    jokerList.add(thisCard);
                }
                Collections.sort(jokerList);// TODO
            }

            sortedList.add(new ArrayList<Card>());
            sortedList.add(new ArrayList<Card>());
            sortedList.add(new ArrayList<Card>());
            sortedList.add(new ArrayList<Card>());
            sortedList.add(new ArrayList<Card>());
            sortedList.add(new ArrayList<Card>());
            sortedList.add(new ArrayList<Card>());
            sortedList.add(new ArrayList<Card>());
            System.out.print(mainCardList);
            long timestart = System.nanoTime();
            List<Card> seqList = makeSequence(mainCardList);
            for (Card card : seqList) {
                mainCardList.remove(card);
            }
            if (!seqList.isEmpty()) {
                List<Card> impureList = makeImpureSequences(mainCardList);
                for (Card card : impureList) {
                    mainCardList.remove(card);
                }
                if (!impureList.isEmpty()) {
                    List<Card> setList = makeSet(mainCardList);
                    for (Card card : setList) {
                        mainCardList.remove(card);
                    }
                }
            }
            System.out.print("Time taken in ns :" + (System.nanoTime() - timestart));
            // System.out.println("Seq:" + seqList);
            // System.out.println("Set : " + setList);
            // System.out.println("Remaining :" + mainCardList);
            System.out.println("   Score : " + calculateScore(mainCardList));

        }

    }

    public static List<Card> makeSequence(List<Card> mainCardList) {
        List<Card> returnList = new ArrayList<Card>();

        for (Card thiscard : mainCardList) {
            switch (thiscard.getSuite()) {
            case Card.SPADES:
                if (sortedList.get(SPADES).contains(thiscard)) {
                    sortedList.get(SPADES + 4).add(thiscard);
                } else {
                    sortedList.get(SPADES).add(thiscard);
                }
                break;
            case Card.HEARTS:
                if (sortedList.get(HEARTS).contains(thiscard)) {
                    sortedList.get(HEARTS + 4).add(thiscard);
                } else {
                    sortedList.get(HEARTS).add(thiscard);
                }
                break;
            case Card.CLUBS:
                if (sortedList.get(CLUBS).contains(thiscard)) {
                    sortedList.get(CLUBS + 4).add(thiscard);
                } else {
                    sortedList.get(CLUBS).add(thiscard);
                }
                break;
            case Card.DIAMONDS:
                if (sortedList.get(DIAMONDS).contains(thiscard)) {
                    sortedList.get(DIAMONDS + 4).add(thiscard);
                } else {
                    sortedList.get(DIAMONDS).add(thiscard);
                }
                break;
            default:
                break;
            }
        }

        for (List<Card> cardList : sortedList) {
            if (cardList.size() >= TWO) {
                Collections.sort(cardList);
                for (int i = 0; i < cardList.size() - 2; i++) {
                    try {
                        if (((cardList.get(i).getRank() - cardList.get(i + 1).getRank()) == 1)
                                && (cardList.get(i + 1).getRank() - cardList.get(i + 2).getRank()) == 1
                                && cardList.size() >= THREE) {
                            if (isJoker(JOKER, cardList.get(i))) {
                                jokerList.remove(cardList.get(i));
                            }
                            if (isJoker(JOKER, cardList.get(i + 1))) {
                                jokerList.remove(cardList.get(i + 1));
                            }
                            if (isJoker(JOKER, cardList.get(i + 2))) {
                                jokerList.remove(cardList.get(i + 2));
                            }
                            returnList.add(cardList.get(i));
                            returnList.add(cardList.get(i + 1));
                            returnList.add(cardList.get(i + 2));

                            cardList.remove(cardList.get(i));
                            cardList.remove(cardList.get(i));
                            cardList.remove(cardList.get(i));
                            try {
                                while ((cardList.get(i).getRank() - cardList.get(i + 1).getRank()) == 1) {
                                    returnList.add(cardList.get(i));
                                    cardList.remove(cardList.get(i));
                                    if (isJoker(JOKER, cardList.get(i))) {
                                        jokerList.remove(cardList.get(i));
                                    }
                                    i = i + 1;
                                }
                            } catch (Exception e) {
                            }

                            if (returnList.size() > 2) {
                                for (int x = 0; x < returnList.size(); x++) {
                                    if (returnList.get(x).getRank() == JOKER.getRank()) {
                                        jokerList.add(returnList.get(x));
                                        returnList.remove(returnList.get(x));
                                    }
                                }
                            }
                            i = i + 3;
                        }

                    } catch (Exception e) {
                        break;
                    }
                }
            }
        }
        // iseqlist.clear();
        // System.out.println("Impure Seq list:" + iseqlist);
        // System.out.println("Pure Seq List:" + returnList);

        return returnList;
    }

    public static boolean isJoker(Card joker, Card card) {
        if (joker.getRank() == card.getRank()) {
            return true;
        }
        return false;
    }

    public static List<Card> makeSet(List<Card> cardList) {
        List<Card> returnList = new ArrayList<Card>();
        if (jokerList.size() > 0) {
            for (int i = 14; i > 1; i--) {
                Set<Card> smallerList = new HashSet<Card>();
                for (Card card : cardList) {
                    if (card.getRank() == i && card.getRank() != JOKER.getRank()) {
                        if (!returnList.contains(card)) {
                            smallerList.add(card);
                        }
                    }
                }
                if (smallerList.size() > 0 && (smallerList.size() + jokerList.size() > 2)) {
                    while (returnList.size() < 2) {
                        if (jokerList.size() > 0) {
                            smallerList.add(jokerList.get(0));
                            jokerList.remove(0);
                        } else {
                            break;
                        }
                    }
                    returnList.addAll(smallerList);
                }
            }
        }
        return returnList;
    }

    public static List<Card> makeImpureSequences(List<Card> mainList) {
        iseqlist.clear();
        
        for (List<Card> cardList : sortedList) {
            if (cardList.size() > 1) {
                Set<Card> localIseqlist = new HashSet<Card>();
                for (int j = 0; j < cardList.size() - 1; j++) {
                    if ((cardList.get(j).getRank() - cardList.get(j + 1).getRank()) == 1) {
                        localIseqlist.add(cardList.get(j));
                        localIseqlist.add(cardList.get(j + 1));
                    } else if ((cardList.get(j).getRank() - cardList.get(j + 1).getRank()) == 2
                            && jokerList.size() > 0 && localIseqlist.size()<=2) {
                        localIseqlist.add(cardList.get(j));
                        localIseqlist.add(cardList.get(j + 1));
                        localIseqlist.add(jokerList.get(0));
                        jokerList.remove(0);
                    } else if (localIseqlist.size() == 2 && jokerList.size() > 0) {
                        localIseqlist.add(jokerList.get(0));
                        jokerList.remove(0);
                        break;
                    } else if (localIseqlist.size() < 3 && !localIseqlist.isEmpty()) {
                        localIseqlist.clear();
                        break;
                    }
                    if(localIseqlist.size()>3) {
                        final Iterator<Card> itr = localIseqlist.iterator();
                        Card firstElement = itr.next();
                        Card lastElement = itr.next();
                        while(itr.hasNext()) {
                            lastElement = itr.next();
                        }
                        for(Card card:localIseqlist) {
                            cardList.remove(card);
                        }
                        for(Card card:mainList) {
                            if(lastElement.getRank()==JOKER.getRank()||firstElement.getRank()==JOKER.getRank()) {
                                localIseqlist.remove(card);
                            }else if(lastElement.getRank()==card.getRank()||firstElement.getRank()==card.getRank()) {
                                localIseqlist.remove(card);
                            }
                            break;
                        }
                    }
                    if (localIseqlist.size() > 2) {
                        iseqlist.addAll(localIseqlist);
                        localIseqlist.clear();
                    }
                }
            }
        }

        sortedList.get(0).clear();
        sortedList.get(1).clear();
        sortedList.get(2).clear();
        sortedList.get(3).clear();
        sortedList.get(4).clear();
        sortedList.get(5).clear();
        sortedList.get(6).clear();
        sortedList.get(7).clear();
        return iseqlist;
    }

    public static Integer calculateScore(List<Card> cardList) {
        if (cardList.isEmpty() || cardList == null) {
            return 0;
        }
        Integer returnvalue = 0;
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getRank() == JOKER.getRank()) {

            } else if (cardList.get(i).getRank() > 9) {
                returnvalue = returnvalue + 10;
            } else {
                returnvalue = cardList.get(i).getRank() + returnvalue;
            }
        }
        if (returnvalue > 80) {
            return 80;
        }
        return returnvalue;
    }
}
