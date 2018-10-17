package patterns.command;

public class RemoveCom implements Command {
    private FileManager fileManager;

    public RemoveCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length > 0) {
            fileManager.remove(args[0]);
        }
    }

    @Override
    public void undo() {

    }
}
