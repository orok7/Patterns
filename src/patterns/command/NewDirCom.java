package patterns.command;

public class NewDirCom implements Command {
    private FileManager fileManager;

    public NewDirCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length > 0) {
            fileManager.newDirectory(args[0]);
        }
    }

    @Override
    public void undo() {

    }
}
