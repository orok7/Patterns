package patterns.command;

public class RenameCom implements Command {
    private FileManager fileManager;

    public RenameCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length > 1) {
            fileManager.rename(args[0], args[1]);
        }
    }

    @Override
    public void undo() {

    }
}
