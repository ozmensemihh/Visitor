package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {

        Path path = FileSystems.getDefault().getPath("Dır1");
        Files.walkFileTree(path,new FileName());
        Path copyPath = FileSystems.getDefault().getPath("Dır1//Dır2");
        Files.walkFileTree(copyPath,new CopyFile(path,copyPath));
    }
}
