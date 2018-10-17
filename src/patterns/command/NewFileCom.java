package patterns.command;

public class NewFileCom implements Command {
    private FileManager fileManager;

    public NewFileCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length > 0) {
            fileManager.newFile(args[0]);
        }
    }

    @Override
    public void undo() {

    }
}
