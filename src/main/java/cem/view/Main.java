package cem.view;


import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        //muestra el JFrame menu
        MenuJFrame menu = new MenuJFrame();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
