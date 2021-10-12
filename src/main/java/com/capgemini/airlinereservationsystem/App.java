package com.capgemini.airlinereservationsystem;

import com.capgemini.airlinereservationsystem.controller.MainController;

public class App 
{
    public static void main( String[] args )
    {
        MainController controller = new MainController();
        controller.getStart();
    }
}
