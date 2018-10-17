package patterns.command;

public class FileManager {
    private static final String ROOT_NAME = "C:";
    private SomeFile currentDir;
    private SomeFile root;

    public FileManager() {
        this.root = new SomeFile(ROOT_NAME, null, true);
        this.currentDir = root;
    }

    public void newFile(String fileName) {
        createNew(fileName, false);
    }

    public void newDirectory(String dirName) {
        createNew(dirName, true);
    }

    public void remove(String name) {
        SomeFile found = currentDir.getByName(name);
        if (currentDir.removeFile(found)) {
            print(getType(found) + " " + name + " removed");
        } else {
            print("File/Directory " + name + " does not exist");
        }
    }

    public void rename(String name, String newName) {
        SomeFile found = currentDir.getByName(name);
        if (found != null) {
            found.setName(newName);
            print(getType(found) + " name changed: " + name + " -> " + newName);
        } else {
            print("File/Directory " + name + " does not exist");
        }
    }

    public void move(String name, String destinationPath) {
        SomeFile found = currentDir.getByName(name);
        if (found != null) {
            SomeFile dest = parsePath(destinationPath);
            if (dest != null) {
                if (!dest.addFile(found)) {
                    print(getType(found) + " with this name already exist in destination path");
                    return;
                }
                if (!currentDir.removeFile(found)) {
                    print(getType(found) + " " + name + " cannot be removed");
                    dest.removeFile(found);
                }
                print(getType(found) + " " + name + " moved to " + destinationPath);
            }
            print("Wrong destination path");
            return;
        }
        print("File/Directory " + name + " does not exist");
    }

    public void changeDir(String path) {
        SomeFile found = parsePath(path);
        if (found == null) {
            found = parsePath(currentDir.getPath() + "/" + path);
            if (found == null) {
                print("Wrong path");
                return;
            }
        }
        currentDir = found;
        print("");
    }

    public void list() {
        print("");
        currentDir.printList();
    }

    private void createNew(String name, boolean isDirectory) {
        SomeFile newDir = new SomeFile(name, currentDir, isDirectory);
        if (currentDir.addFile(newDir)) {
            print(getType(newDir) + " " + newDir.getPath() + " created");
        } else {
            print("File/Directory with this name already exist");
        }
    }

    private SomeFile parsePath(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        SomeFile currDir = root;
        String[] split = path.split("/");
        if (!ROOT_NAME.equals(split[0])) {
            return null;
        }
        if (split.length == 1) {
            return currDir;
        }
        String[] filesWithoutRoot = new String[split.length - 1];
        System.arraycopy(split, 1, filesWithoutRoot, 0, split.length - 1);
        for (String dir : filesWithoutRoot) {
            SomeFile found = currDir.getByName(dir);
            if (found == null) {
                return null;
            }
            currDir = found;
        }
        return currDir;
    }

    private String getType(SomeFile fileByName) {
        return fileByName.isDirectory() ? "Directory" : "File";
    }

    private String getHeader() {
        return currentDir.getPath() + "> ";
    }

    private void print(String s) {
        System.out.println(getHeader() + s);
    }
}
