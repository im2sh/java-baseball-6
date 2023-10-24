package baseball.controller;

import baseball.domain.Computer;
import baseball.service.GameService;
import baseball.utils.GameMessage;
import baseball.view.InputView;
import baseball.view.OutputView;
import java.util.List;

public class BaseballGame {
    private final GameService gameService;
    private final static String exitNumber = "2";
    private static boolean endOrNot = false;
    private static List<Integer> userNumber;
    private static List<Integer> computerNumber;

    public BaseballGame() {
        gameService = new GameService();
    }

    public void startGame() {
        initStartGame();
        while (endOrNot) {
            startOneGame();
            exitGame();
        }
        System.out.println(GameMessage.EXIT_GAME.getMessage());
    }

    private void startOneGame() {
        gamePreparation();
        while (!gameProgress()) {

        }
    }

    public void gamePreparation() {
        Computer computer = new Computer();
        computerNumber = computer.getComputerNumber();
    }

    public boolean gameProgress() {
        userNumber = inputUserNumber();
        List<Integer> gameResult = gameService.playGame(computerNumber, userNumber);
        if (getOneGameResult(gameResult)) {
            return true;
        }
        return false;
    }

    public boolean getOneGameResult(List<Integer> gameResult) {
        if (OutputView.outputResult(gameResult)) {
            return true;
        }
        return false;
    }

    private List<Integer> inputUserNumber() {
        System.out.print(GameMessage.REQUIRED_INPUT_NUMBER.getMessage());
        return gameService.parsingInputNumber(InputView.Input());
    }


    private void initStartGame() {
        System.out.println(GameMessage.BASEBALL_START.getMessage());
        endOrNot = true;
    }

    private void exitGame() {
        System.out.println(GameMessage.DECIDE_CONTINUE_OR_EXIT.getMessage());
        if (InputView.Input().equals(exitNumber)) {
            endOrNot = false;
        }
    }
}
