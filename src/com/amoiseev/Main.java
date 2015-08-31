package com.amoiseev;

import com.amoiseev.controller.FileMerger;
import com.amoiseev.model.Student;

import java.io.*;
import java.util.*;

public class Main {




    public static void main(String[] args) {

        Map<String, Student> maps = new HashMap<String, Student>();
        String csvSpec = "C:\\Coding\\Java\\FileWorker\\files\\Pm.csv";
        String csvFil = "C:\\Coding\\Java\\FileWorker\\files\\Fil.csv";
        String csvIn = "C:\\Coding\\Java\\FileWorker\\files\\In.csv";
        FileMerger.readfile(maps, csvSpec, FileMerger.SPEC);
        ArrayList<String> listPeople = new ArrayList<String>(maps.keySet());
        FileMerger.readfile(maps, csvFil, FileMerger.FIL);
        FileMerger.readfile(maps, csvIn, FileMerger.IN);
        String nameResult ="C:\\Coding\\Java\\FileWorker\\files\\result1.csv";
        FileMerger.printResult(maps, listPeople, nameResult);
    }


}
