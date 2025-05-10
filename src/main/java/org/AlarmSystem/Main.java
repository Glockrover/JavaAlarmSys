package org.AlarmSystem;

import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String file_path = "/home/glockrover/Documents/JavaAlarmSys/src/main/resources/rihanna-diamonds.wav";

        File file = new File(file_path);

        AudioInputStream soundFile;
        Clip clip;

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

            while (!option.equals("S")) {
                clip.start();
                System.out.println("Alarm...");
                System.out.print("Press S to stop the alarm: ");
                option = scanner.nextLine().toUpperCase();
        }


            scanner.close();
        }
    }



}