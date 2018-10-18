package patterns.command;

public class NewFileCom implements Command {
    private FileManager fileManager;

    public NewFileCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length == 1) {
            fileManager.newFile(args[0]);
            fileManager.printHeader();
        } else {
            fileManager.printHeader();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void undo(String[] args) {
        new RemoveCom(fileManager).execute(args);
    }
}
