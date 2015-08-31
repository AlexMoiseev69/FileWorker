package com.amoiseev.controller;

import com.amoiseev.model.Student;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by amoiseev on 30.07.2015.
 */
public class FileMerger {

    private static final String SEMI_COLON = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public static final String FIL = "fil";
    public static final String SPEC = "spec";
    public static final String IN = "in";

    public static void printResult(Map<String, Student> maps, List<String> listPeople, String nameResult) {
        FileWriter fileWriter= null;

        //Write a new student object list to the CSV file
        try {
            fileWriter = new FileWriter(nameResult);
            for (String man : listPeople) {
                Student st = maps.get(man);
                fileWriter.append(man);
                fileWriter.append(SEMI_COLON);
                fileWriter.append(String.valueOf(st.getSpec()));
                fileWriter.append(SEMI_COLON);
                fileWriter.append(String.valueOf(st.getIn()));
                fileWriter.append(SEMI_COLON);
                fileWriter.append(String.valueOf(st.getFil()));
                fileWriter.append(SEMI_COLON);
                fileWriter.append(String.valueOf(st.getFil()+st.getSpec()+st.getIn()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Student> readfile(Map<String, Student> maps, String csvFile, String type){

        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] elemets = line.split(SEMI_COLON);
                if(elemets==null)
                    continue;
                Float sum = 0.0f;
                if (maps.containsKey(elemets[0])) {
                    Student st = maps.get(elemets[0]);
                    switch (type){
                        case FIL:
                            sum = (float)st.getFil();
                            sum += Float.valueOf(elemets[1]);
                            st.setFil(sum.intValue());
                            break;
                        case SPEC:
                            sum =(float) st.getSpec();
                            sum += Float.valueOf(elemets[1]);
                            st.setSpec(sum.intValue());
                            break;
                        case IN:
                            sum = (float)st.getIn();
                            sum += Float.valueOf(elemets[1]);
                            st.setIn(sum.intValue());
                            break;
                    }
                    maps.put(elemets[0], st);
                }
                else
                {
                    Student newSt = new Student();
                    switch (type){
                        case FIL:
                            newSt.setFil(Float.valueOf(elemets[1]).intValue());
                            break;
                        case SPEC:
                            newSt.setSpec(Float.valueOf(elemets[1]).intValue());
                            break;
                        case IN:
                            newSt.setIn(Float.valueOf(elemets[1]).intValue());
                            break;
                    }
                    maps.put(elemets[0], newSt);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            System.out.println(line);
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return maps;
    }
}
