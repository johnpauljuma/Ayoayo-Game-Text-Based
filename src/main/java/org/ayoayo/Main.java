package org.ayoayo;

public class Main {
    public static void main(String[] args) {
        Ayoayo game = new Ayoayo();
        Player player1 = game.createPlayer("Juma");
        Player player2 = game.createPlayer("Brian");

        //System.out.println(game.playGame(1, 3));
        game.playGame(1, 1);
        game.playGame(1, 2);
        game.playGame(1, 3);
        game.playGame(1, 4);
        game.playGame(1, 5);
        game.playGame(1, 6);
        game.printBoard();
        System.out.println(game.returnWinner());
    }
}