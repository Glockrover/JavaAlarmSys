package org.AlarmSystem;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String file_path = "/home/glockrover/Documents/JavaAlarmSys/src/main/resources/rihanna-diamonds.wav";

        File file = new File(file_path);

        AudioInputStream soundFile;
        Clip clip;

        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(formatter);

        {
            try {
                soundFile = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(soundFile);

            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }

            Scanner scanner = new Scanner(System.in);

            String option = "";
            System.out.print("Enter the time you want to wakeup: (HH:mm): ");
            String wakeUpTime = scanner.nextLine();


            while (!formattedTime.equals(wakeUpTime)) {
                try {
                    Thread.sleep(1000); // Wait 1 second before checking again
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                formattedTime = getCurrentTime(); // <-- Update time each loop
            }

            while (!option.equals("S")) {
                clip.start();
                System.out.printf("%s Alarm...\n", formattedTime);
                System.out.print("Press S to stop the alarm: ");
                option = scanner.nextLine().toUpperCase();
            }

            scanner.close();
        }

    }

    public static String getCurrentTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}