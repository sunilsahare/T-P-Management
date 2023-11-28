package com.tnpserver.util;

import com.tnpserver.constants.AppConst;
import com.tnpserver.exception.BusinessException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for common file and directory operations.
 */
public class FileUtil {

    private FileUtil() {}

    /**
     * Creates a directory at the specified path.
     *
     * @param directoryPath The path of the directory to create.
     */
    public static void createDirectories(String directoryPath) {
        try {
            Files.createDirectories(Paths.get(directoryPath));
            System.err.println("created directories");
        } catch (IOException e) {
            System.err.println("Error while creating directories");
            throw new BusinessException("Error while creating directories - "+directoryPath);
        }
    }

    public static void saveFileInFolder(String path, String fileName, byte[] fileBytes) {
        try {
            Path filePath = Paths.get(path, fileName);
            Files.write(filePath, fileBytes);
        } catch (IOException e) {
            System.err.println("Error while saving file");
            throw new BusinessException("Error while saving file - "+fileName);
        }
    }

    /**
     * Deletes a directory and its contents recursively.
     *
     * @param directoryPath The path of the directory to delete.
     */
    public static void deleteDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists()) {
            deleteRecursive(directory.toPath());
        }
    }

    /**
     * Creates a file at the specified path.
     *
     * @param filePath The path of the file to create.
     * @throws IOException If an I/O error occurs.
     */
    public static void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Deletes a file at the specified path.
     *
     * @param filePath The path of the file to delete.
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Copies a file from the source path to the destination path.
     *
     * @param sourcePath      The path of the source file.
     * @param destinationPath The path of the destination file.
     * @throws IOException If an I/O error occurs during copying.
     */
    public static void copyFile(String sourcePath, String destinationPath) throws IOException {
        Files.copy(new File(sourcePath).toPath(), new File(destinationPath).toPath());
    }

    /**
     * Moves a file from the source path to the destination path.
     *
     * @param sourcePath      The path of the source file.
     * @param destinationPath The path of the destination file.
     * @throws IOException If an I/O error occurs during moving.
     */
    public static void moveFile(String sourcePath, String destinationPath) throws IOException {
        Files.move(new File(sourcePath).toPath(), new File(destinationPath).toPath());
    }

    /**
     * Checks if a file exists at the specified path.
     *
     * @param filePath The path of the file to check.
     * @return {@code true} if the file exists; {@code false} otherwise.
     */
    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    /**
     * Checks if a directory exists at the specified path.
     *
     * @param directoryPath The path of the directory to check.
     * @return {@code true} if the directory exists; {@code false} otherwise.
     */
    public static boolean isDirectoryExist(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.exists() && directory.isDirectory();
    }

    /**
     * Recursively deletes a directory and its contents.
     *
     * @param path The path of the directory to delete.
     */
    private static void deleteRecursive(Path path) {
        try {
            Files.walk(path)
                    .map(Path::toFile)
                    .sorted((o1, o2) -> -o1.compareTo(o2))
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPathForUser(String username){
        return filesPath() + File.separator + username;
    }

    public static String filesPath(){
        return appDataPath() + File.separator + AppConst.FILES;
    }

    public static String appDataPath(){
        return AppProperties.getInstance().getBasePath() + File.separator + AppConst.APP_DATA;
    }

}
