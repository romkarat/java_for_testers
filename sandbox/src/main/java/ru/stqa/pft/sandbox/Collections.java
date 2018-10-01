package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    //инициализация массива при помощи фигурных скобок
    String[] langs = {"Java", "C#", "Python", "PHP"};

    // инициализация массива (аналог для списка)
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : languages){
      System.out.println("Я хочу выучить " + l);
    }
  }
}
