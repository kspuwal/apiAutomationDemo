package com.cmpute.api_tests;

import org.apache.commons.lang3.ObjectUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Kuldeep Singh on 8/21/2017.
 */
public class AppnomicOddNumTest {

    static String fileName ="./readOddNum.txt";  // readOddNum.txt in current Directory
    static BufferedReader bufferedReader;
    static FileReader fileReader;


    public static void main(String arg[]){

        /*
        //Get Current Director But as per expected output I am not printing this in output So, i comment it
        //This will help to create your readOddNum.txt  in current director
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
                */
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            String currLine;
            while ((currLine =bufferedReader.readLine())!= null){
                //Print all the line of our readOddNum.txt file, but its not required in output as per expected result
                //System.out.print(currLine +"\n");

                // Convert Current line into Integer to perform math operation ( % )
                int lineInt = Integer.parseInt(currLine);

                // Check current line containing an odd number or not
                if(lineInt%2 != 0){
                    System.out.print(lineInt+"\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
