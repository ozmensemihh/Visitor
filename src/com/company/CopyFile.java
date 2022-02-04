package com.company;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFile extends SimpleFileVisitor<Path> {

    private Path targetPath;
    private  Path sourcePath;

    public CopyFile(Path rootPath, Path sourcePath) {
        this.targetPath = rootPath;
        this.sourcePath = sourcePath;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error "+file.toAbsolutePath() + " "+ exc.getMessage());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path relatived = sourcePath.relativize(dir);
        Path solved = targetPath.resolve(relatived);
        try {
            Files.copy(dir,solved);
        }catch (IOException ex){
            System.out.println("Error");
            return FileVisitResult.SKIP_SUBTREE;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path relatived = sourcePath.relativize(file);
        Path solved = targetPath.resolve(relatived);
        try {
            Files.copy(file,solved);
        }catch (IOException ex){
            System.out.println("Error");
        }

        return FileVisitResult.CONTINUE;
    }
}
