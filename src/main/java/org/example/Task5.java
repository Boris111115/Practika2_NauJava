package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task5 implements Task {
    private final Path sourceDir;
    private final Path targetDir;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private Thread worker;

    public Task5(String source, String target) {
        this.sourceDir = Paths.get(source);
        this.targetDir = Paths.get(target);
    }

    @Override
    public void start() {
        if (running.get()) {
            System.out.println("Синхронизация уже запущена.");
            return;
        }
        running.set(true);

        worker = new Thread(() -> {
            try {
                Files.createDirectories(targetDir);
                System.out.println("Синхронизация запущена: " + sourceDir + " → " + targetDir);

                while (running.get()) {
                    Files.walk(sourceDir)
                            .filter(Files::isRegularFile)
                            .forEach(src -> {
                                try {
                                    Path relative = sourceDir.relativize(src);
                                    Path dest = targetDir.resolve(relative);
                                    Files.createDirectories(dest.getParent());
                                    Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                                    System.out.println("Синхронизирован: " + src);
                                } catch (IOException e) {
                                    System.err.println("Ошибка: " + e.getMessage());
                                }
                            });
                    Thread.sleep(5000); // пауза 5 секунд
                }
            } catch (IOException | InterruptedException e) {
                if (running.get()) {
                    System.err.println("Ошибка синхронизации: " + e.getMessage());
                }
            }
        });

        worker.start();
    }

    @Override
    public void stop() {
        if (!running.get()) {
            System.out.println("Синхронизация не запущена.");
            return;
        }
        running.set(false);
        if (worker != null) {
            worker.interrupt();
        }
        System.out.println("Синхронизация остановлена.");
    }

    public static void main(String[] args) throws InterruptedException {
        Task5 task = new Task5("source_folder", "target_folder");
        task.start();

        Thread.sleep(15000); // работаем 15 секунд

        task.stop();
    }
}