import java.io.*;
import java.nio.file.*;

public class BackupAndPrintTree {
    public static void main(String[] args) {
        // Путь к исходной директории для создания резервной копии файлов
        String sourceDir = "C:\\Users\\ilyas\\Desktop\\Java разработчик\\Java Core\\Java_Core_homework_5\\source_directory";
        // Путь к корневой директории для печати дерева директорий и файлов
        String rootDir = "C:\\Users\\ilyas\\Desktop\\Java разработчик\\Java Core\\Java_Core_homework_5\\root_directory";
        // Путь для создания папки резервных копий
        String backupDir = "./backup";

        try {
            // Создание папки для резервных копий
            Files.createDirectories(Paths.get(backupDir));

            // Создание резервной копии файлов
            File sourceDirectory = new File(sourceDir);
            File[] files = sourceDirectory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        Path sourceFilePath = file.toPath();
                        Path targetFilePath = Paths.get(backupDir, file.getName());
                        Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                System.out.println("Резервная копия создана успешно.");
            } else {
                System.out.println("Пустая директория.");
            }

            // Печать дерева директорий и файлов
            File rootFolder = new File(rootDir);
            print(rootFolder, "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print(File folder, String indent) {
        System.out.println(indent + "+ " + folder.getName());

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    print(file, indent + "  ");
                } else {
                    System.out.println(indent + "  - " + file.getName());
                }
            }
        }
    }
}