package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
//    Pierwsza wersja run, która wypisuje argumenty po przecinku
//    public static void run(String[] args){
//        System.out.println("Zwierzak idzie do przodu");
//        for (int i = 0; i < args.length - 1; i++){
//            System.out.print(args[i] + ", ");
//        }
//        if (args.length > 0) {
//            System.out.println(args[args.length - 1]);
//        }
//    }

//    Druga wersja run, odczytuje argumenty jako stringi
//    public static void run(String[] args){
//        for (String move : args) {
//            switch(move){
//                case "f" -> System.out.println("Zwierzak idzie do przodu");
//                case "b" -> System.out.println("Zwierzak idzie do tyłu");
//                case "r" -> System.out.println("Zwierzak idzie w prawo");
//                case "l" -> System.out.println("Zwierzak idzie w lewo");
//            }
//        }
//    }

    public static void run(MoveDirection[] moves){
        for (MoveDirection move : moves) {
            switch(move){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("system wystartował");
        MoveDirection[] moves = OptionsParser.parse(args);
        run(moves);
        System.out.println("system zakończył działanie");
    }
}
