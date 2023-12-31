package ru.geekbrains.core.lesson5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BackupCreator {

    public static void main(String[] args) throws IOException {
        String sourceDirectory = "./";
        String backupDirectory = "./backup";

        createBackup(sourceDirectory, backupDirectory);
    }

    public static void createBackup(String sourceDirectory, String backupDirectory) throws IOException {

        // Создаем папку для резервных копий, если ее нет
        File backupDir = new File(backupDirectory);
        if (backupDir.exists()) {
            System.out.println("Папка " + backupDir.getPath() + " уже существует");
        } else {
            boolean mkdir = backupDir.mkdir();
            System.out.println("Папка для копирования файлов " + backupDir.getPath() + " создана");
        }

        // Получаем список файлов в директории
        File sourceDir = new File(sourceDirectory);
        File[] filesToBackup = sourceDir.listFiles();

        // Копируем каждый файл в папку с резервными копиями
        assert filesToBackup != null;
        System.out.println("В папку " + backupDir.getPath() + " успешно скопированы файлы: ");
        for (File file : filesToBackup) {
            if (!file.isFile()) {
                continue;
            }
            Files.copy(file.toPath(), new File(backupDir.getPath() + "/" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(file.getName());
        }
    }
}
