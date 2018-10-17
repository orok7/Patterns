package patterns.command;

public class UserInterface {
    private Command newFileCom;
    private Command newDirCom;
    private Command removeCom;
    private Command renameCom;
    private Command moveCom;

    private CommandsHistory commandsHistory;

    public UserInterface(FileManager fileManager) {
        newFileCom = new NewFileCom(fileManager);
        newDirCom = new NewDirCom(fileManager);
        removeCom = new RemoveCom(fileManager);
        renameCom = new RenameCom(fileManager);
        moveCom = new MoveCom(fileManager);
    }

    private class CommandsHistory {

    }
}
