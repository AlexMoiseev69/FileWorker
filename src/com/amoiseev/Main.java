package com.amoiseev;

import java.io.*;
import java.util.*;

public class Main {

    private static final String SEMI_COLON = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";



    public static void main(String[] args) {

        Map<String, Float> maps = new HashMap<String, Float>();
        String csvSpec = "C:\\Coding\\Java\\FileWorker\\files\\Spec.csv";
        String csvFil = "C:\\Coding\\Java\\FileWorker\\files\\Fil.csv";
        String csvIn = "C:\\Coding\\Java\\FileWorker\\files\\In.csv";
        readfile(maps, csvSpec);
        ArrayList<String> listPeople = new ArrayList<String>(maps.keySet());
        readfile(maps, csvFil);
        readfile(maps, csvIn);

        printResult(maps, listPeople);
    }

    private static void printResult(Map<String, Float> maps, List<String> listPeople) {
        FileWriter fileWriter= null;

        //Write a new student object list to the CSV file
        try {
            fileWriter = new FileWriter("C:\\Coding\\Java\\FileWorker\\files\\result.csv");
            for (String man : listPeople) {
                fileWriter.append(man);
                fileWriter.append(SEMI_COLON);
                fileWriter.append(String.valueOf(maps.get(man).intValue()));
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

    public static Map<String, Float> readfile(Map<String, Float> maps, String csvFile){

        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] elemets = line.split(SEMI_COLON);
                if(elemets==null)
                    continue;
                if (maps.containsKey(elemets[0])) {
                    Float sum = maps.get(elemets[0]);
                    if(elemets[1]!=null && !elemets[1].isEmpty())
                    {
                        try {
                            sum += Float.valueOf(elemets[1]);
                        }catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    else {
                        System.out.println("Problem!!! Element:"+ elemets[0] +" Value: "+ elemets[1]);
                    }
                    maps.put(elemets[0], sum);
                }
                else
                {
                    Float sum = 0.0f;
                    if(elemets[1]!=null && !elemets[1].isEmpty())
                    {
                        try {
                            sum += Float.valueOf(elemets[1]);
                        }catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    maps.put(elemets[0], sum);
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
