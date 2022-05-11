package com.company;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class Main {

    public static void main(String[] args) {

        GameProgress game1 = new GameProgress(100, 1, 1, 0);
        GameProgress game2 = new GameProgress(300, 5, 10, 2500);
        GameProgress game3 = new GameProgress(1000, 20, 100, 25000);

        GameProgress.saveGame("C://Games//savegames//save.dat", game1);
        GameProgress.saveGame("C://Games//savegames//save.dat", game2);
        GameProgress.saveGame("C://Games//savegames//save.dat", game3);

        GameProgress.zipFiles("C://Games//savegames//zip.zip","C://Games//savegames//save.dat");

    }

}
