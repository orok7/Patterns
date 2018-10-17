package patterns.command;

public class MoveCom implements Command {
    private FileManager fileManager;

    public MoveCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length > 1) {
            fileManager.move(args[0], args[1]);
        }
    }

    @Override
    public void undo() {

    }
}
