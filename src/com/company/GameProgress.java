package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public static void saveGame(String adress, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(adress, true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipAdress, String adress) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipAdress));
             FileInputStream fis = new FileInputStream(adress)) {
            ZipEntry entry = new ZipEntry("packed_save.dat");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    public static void openZip(String zipAdress, String adress) {
        try (ZipInputStream zinp = new ZipInputStream(new FileInputStream(zipAdress));
             FileOutputStream fos = new FileOutputStream(adress)) {
            ZipEntry entry;
            String name;
            while ((entry = zinp.getNextEntry()) != null) {
                name = entry.getName();
                for (int c = zinp.read(); c != -1; c = zinp.read()) {
                    fos.write(c);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress(String adress, GameProgress gameProgress) {
        try (FileInputStream fis = new FileInputStream(adress);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
    }


    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}