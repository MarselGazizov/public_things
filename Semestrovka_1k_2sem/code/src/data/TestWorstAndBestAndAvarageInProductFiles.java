package data;

import all.Helper;

import java.io.FileNotFoundException;
import java.util.*;

public class TestWorstAndBestAndAvarageInProductFiles {






    public static void main(String[] args) throws FileNotFoundException {


        List<String> filePaths = new ArrayList<>();
        filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\data\\productlen2.txt");
        filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\data\\productlen3.txt");
        filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\data\\productlen4.txt");
        filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\data\\productlen5.txt");
        filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\data\\productlen6.txt");
        //filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\data\\productlen7.txt");


        String nameOfSCV = "D:\\git_folder\\ASD\\Semestrovka\\src\\data\\products.csv";

        //creating csv
        ArrayList<String> res = new ArrayList<>();
        Helper.writeHeadersAndResToFile(nameOfSCV, res, "bound,length,time(ms),swaps,jumps,partsSorted?,array");

        //adding lines to csv
        for(String filePath:filePaths){
            Helper.writeResultsOfTestingToExistingCSVFromWholeFile( nameOfSCV,filePath,"y","onArray");
        }





    }


}
