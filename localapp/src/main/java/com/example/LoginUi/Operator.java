package com.example.LoginUi;


public class Operator {

    com.example.LoginUi.LoginFrame lf = null;
    Database db = null;

    public static void main(String[] args) {
        Operator opt = new Operator();
        opt.db = new Database();
        opt.lf = new LoginFrame(opt);
    }
}
