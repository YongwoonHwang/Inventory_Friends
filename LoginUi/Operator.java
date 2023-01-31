package com.localapp.localtest.LoginUi;


public class Operator {

    com.localapp.localtest.LoginUi.LoginFrame lf = null;
    Database db = null;

    public static void main(String[] args) {
        Operator opt = new Operator();
        opt.db = new Database();
        opt.lf = new LoginFrame(opt);
    }
}
