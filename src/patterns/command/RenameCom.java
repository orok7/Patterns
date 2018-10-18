package patterns.command;

public class RenameCom implements Command {
    private FileManager fileManager;

    public RenameCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length == 2) {
            fileManager.rename(args[0], args[1]);
            fileManager.printHeader();
        } else {
            fileManager.printHeader();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void undo(String[] args) {

    }
}
