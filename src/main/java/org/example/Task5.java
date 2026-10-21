package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

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
                // Создаем целевую папку, если её нет
                Files.createDirectories(targetDir);
                System.out.println("Синхронизация запущена: " + sourceDir.toAbsolutePath()
                        + " → " + targetDir.toAbsolutePath());

                while (running.get()) {
                    // ИСПРАВЛЕНИЕ: используем try-with-resources для закрытия Stream
                    try (Stream<Path> paths = Files.walk(sourceDir)) {
                        paths.filter(Files::isRegularFile)
                                .forEach(src -> {
                                    try {
                                        // Вычисляем относительный путь и путь назначения
                                        Path relative = sourceDir.relativize(src);
                                        Path dest = targetDir.resolve(relative);

                                        // Создаем родительские папки, если их нет
                                        Files.createDirectories(dest.getParent());

                                        // Копируем файл с заменой, если он уже существует
                                        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                                        System.out.println("Синхронизирован: " + src);
                                    } catch (IOException e) {
                                        System.err.println("Ошибка при копировании файла " + src + ": " + e.getMessage());
                                    }
                                });
                    } catch (IOException e) {
                        System.err.println("Ошибка при обходе папки: " + e.getMessage());
                    }

                    // Пауза между циклами синхронизации
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                // Поток был прерван методом stop()
                System.out.println("Поток синхронизации был прерван.");
            } catch (IOException e) {
                System.err.println("Критическая ошибка файловой системы: " + e.getMessage());
            } finally {
                System.out.println("Работа потока синхронизации завершена.");
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

        // Меняем флаг, чтобы цикл while завершился
        running.set(false);

        // Прерываем поток, если он спит (Thread.sleep)
        if (worker != null) {
            worker.interrupt();
        }
        System.out.println("Синхронизация остановлена.");
    }

    // Метод main оставлен для возможности запуска задания отдельно (как просил куратор)
    public static void main(String[] args) throws InterruptedException {
        Task5 task = new Task5("source_folder", "target_folder");
        task.start();

        // Работаем 15 секунд для демонстрации
        Thread.sleep(15000);

        task.stop();
    }
}