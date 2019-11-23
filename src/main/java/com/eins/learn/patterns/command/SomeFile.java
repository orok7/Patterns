package com.eins.learn.patterns.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = { "isDirectory", "files" })
public class SomeFile {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private SomeFile root;
    @Getter
    private String path;
    @Getter
    private boolean isDirectory;
    private Set<SomeFile> files;

    public SomeFile(String name, SomeFile root, boolean isDirectory) {
        this.name = name;
        this.root = root;
        this.path = root != null ? root.getPath() + "/" + name : name;
        this.isDirectory = isDirectory;
        if (isDirectory) {
            files = new HashSet<>();
        }
    }

    public boolean addFile(SomeFile file) {
        return isDirectory && files.add(file);
    }

    public SomeFile getFile(SomeFile file) {
        if (!isDirectory) {
            return null;
        }
        return files.stream().filter(f -> f.equals(file)).findAny().orElse(null);
    }

    public SomeFile getByName(String fileName) {
        if (!isDirectory) {
            return null;
        }
        return files.stream().filter(f -> f.getName().equals(fileName)).findFirst().orElse(null);
    }

    public boolean removeFile(SomeFile file) {
        return isDirectory && files.remove(file);
    }

    public void printList() {
        files.stream().sorted(getComparator()).forEach(f -> System.out.println("\t" + f.getName() + "\t" + (f.isDirectory() ? "DIR" : "FILE")));
    }

    private Comparator<SomeFile> getComparator() {
        return (o1, o2) -> {
            if (o1 == null || o2 == null) {
                return 0;
            }
            if (o1.isDirectory() && !o2.isDirectory()) {
                return -1;
            } else if (!o1.isDirectory() && o2.isDirectory()) {
                return 1;
            }
            return o1.getName().compareTo(o2.getName());
        };
    }

    private boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    private boolean isCollectionNotEmpty(Collection<?> collection) {
        return !isCollectionEmpty(collection);
    }
}
