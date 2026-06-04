package org.example;

import org.example.model.Card;
import org.example.model.Player;
import org.example.model.Value;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        ArrayList<Card> fulldeck = new ArrayList<Card>(52);

        Scanner scanner = new Scanner(System.in);


        while(true){

            fulldeck.clear();

            fillWithColor(fulldeck,"PIQUE");
            fillWithColor(fulldeck,"COEUR");
            fillWithColor(fulldeck,"CARREAU");
            fillWithColor(fulldeck,"TRÈFLE");

            boolean next = false;

            while(!next) {
                System.out.println("************* Menu principal *************\n" +
                        "1- Afficher les cartes.\n" +
                        "2- Mélanger les cartes\n" +
                        "3- Commencer la partie\n" +
                        "*********************************************");

                try {
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        fulldeck.forEach(System.out::println);
                    } else if (choice == 2) {
                        Collections.shuffle(fulldeck);
                        fulldeck.forEach(System.out::println);
                    } else if (choice == 3) {
                        next = true;
                    } else System.out.println("Hors du menu");
                } catch (InputMismatchException e) {
                    System.err.println("Ce n'est même pas un chiffre !");
                    scanner.close();
                    return;
                }
            }

            int playerCount = 0;

            while(playerCount < 1 || 52 / playerCount < 2 ) {
                System.out.println("Veuillez indiquer le nombre de joueur :");
                playerCount = scanner.nextInt();
            }

            int cardCount = 52 / playerCount;

            ArrayList<Player> players = new ArrayList<Player>();
            for (int i = 1; i <= playerCount; i++) {
                players.add(new Player(i));
            }

            for (int j = 0;j < cardCount ;j++){
                for(int i = 0;i < playerCount; i++){
                    players.get(i).init(fulldeck.removeFirst());
                }
            }

            boolean loop = false;
            next = false;

            while(players.size() > 1){

                if(!loop) {

                    System.out.println("**************** Menu jeu ****************\n" +
                            "1- Main suivante\n" +
                            "2- Afficher la distribution des cartes par joueur\n" +
                            "3- Automatiser les mains jusqu’à la fin de la partie\n" +
                            "******************************************");

                    try {
                        int choice = scanner.nextInt();

                        if (choice == 1) {

                        } else if (choice == 2) {
                            players.forEach(player -> System.out.println("Joueur " + player.getId() + " a " + player.deck));
                            continue;
                        } else if (choice == 3) {
                            loop = true;
                        } else {
                            System.out.println("Hors du menu");
                            continue;
                        }
                    } catch (InputMismatchException e) {
                        System.err.println("Ce n'est pas un chiffre");
                        scanner.close();
                        return;
                    }

                }

                ArrayList<Card> board = new ArrayList<Card>();

                players.forEach(player -> board.add(player.draw()));
                Card winnerCard = board.stream().max(Comparator.naturalOrder()).get();
                int winner = board.indexOf(winnerCard);


                showGameStatus(players, board);

                if(board.stream().
                        filter(card -> card.rivals(winnerCard)).
                        count() > 1){
                    ArrayList<Player> drawPlayers = board.stream()
                            .filter(card -> card.rivals(winnerCard))
                            .mapToInt(card -> board.indexOf(card))
                            .mapToObj(n -> players.get(n))
                            .filter(player -> !player.deck.isEmpty())
                            .collect(Collectors.toCollection(ArrayList::new));


                    while (drawPlayers.size() > 1) {

                        System.out.println("Duel !");

                        ArrayList<Card> drawboard = new ArrayList<Card>();
                        drawPlayers.forEach(player -> drawboard.add(player.draw()));

                        showGameStatus(drawPlayers,drawboard);

                        Card drawWinnerCard = drawboard.stream().max(Comparator.naturalOrder()).get();
                        drawPlayers.removeIf(player -> !drawboard.get(drawPlayers.indexOf(player)).rivals(drawWinnerCard));
                        board.addAll(drawboard);
                    }

                }
                System.out.println("Joueur "+players.get(winner).getId()+" gagne");

                players.get(winner).battleWon(board);

                players.removeIf(player -> player.gameOver());


            }
        }



    }

    private static void showGameStatus(ArrayList<Player> players, ArrayList<Card> board) {
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Joueur "+ players.get(i).getId()
                    +" : Joue "+ board.get(i)
                    + " ( " + players.get(i).deck.size()
                    + " cartes restantes )");
        }
    }

    private static void fillWithColor(List<Card> fulldeck, String color ) {
        fulldeck.add(new Card(Value.DEUX,color));
        fulldeck.add(new Card(Value.TROIS,color));
        fulldeck.add(new Card(Value.QUATRE,color));
        fulldeck.add(new Card(Value.CINQ,color));
        fulldeck.add(new Card(Value.SIX,color));
        fulldeck.add(new Card(Value.SEPT,color));
        fulldeck.add(new Card(Value.HUIT,color));
        fulldeck.add(new Card(Value.NEUF,color));
        fulldeck.add(new Card(Value.DIX,color));
        fulldeck.add(new Card(Value.VALET,color));
        fulldeck.add(new Card(Value.REINE,color));
        fulldeck.add(new Card(Value.ROI,color));
        fulldeck.add(new Card(Value.AS,color));
    }
}