package all;

import java.io.FileNotFoundException;
import java.util.*;


public class ResMain {

    public static void main(String[] args) throws FileNotFoundException {

        List<String> filePaths = new ArrayList<>();
        filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\DataMaking\\absolutelyRandom.txt");
        filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\DataMaking\\someSorted.txt");
        filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\DataMaking\\sorted.txt");
        filePaths.add("D:\\git_folder\\ASD\\Semestrovka\\src\\DataMaking\\reversedSorted.txt");



        //On Array:

        String SCVPath = "D:\\git_folder\\ASD\\Semestrovka\\src\\all\\ArrMainFromFiles.csv";

        //creating csv
        ArrayList<String> res = new ArrayList<>();
        Helper.writeHeadersAndResToFile(SCVPath, res, "bound,length,time(ms),swaps,jumps,partsSorted?");

        //adding lines to csv
        for(String filePath:filePaths){
            Helper.writeResultsOfTestingToExistingCSVFromWholeFile(SCVPath,filePath,"n","onArray");
        }





        //On list:

        SCVPath = "D:\\git_folder\\ASD\\Semestrovka\\src\\all\\ListMainFromFiles.csv";

        //creating csv
        res = new ArrayList<>();
        Helper.writeHeadersAndResToFile(SCVPath, res, "bound,length,time(ms),swaps,jumps,partsSorted?");

        //adding lines to csv
        for(String filePath:filePaths){
            Helper.writeResultsOfTestingToExistingCSVFromWholeFile(SCVPath,filePath,"n", "onList");
        }


    }


    public static void main2(String[] args) {












//
//        resLengths = new ArrayList<>();
//        resLengths.add(10);
//        resLengths.add(100);
//        resLengths.add(1000);



        //start




//
//
//        //with Array:
//
//        //creating csv
//        String nameOfCSV = "D:\\git_folder\\ASD\\Semestrovka\\src\\all\\ArrMain.csv";
//        ArrayList<String> res = new ArrayList<>();
//        Helper.writeHeadersAndResToFile(nameOfCSV, res);
//
//        String[] modes = new String[] {"absolutelyRandom", "someSorted"};
//        //adding Strings
//        for(int bound:bounds){
//            for(int len:resLengths){
//                for(String mode:modes) {
//                    for(int z=0;z<2;z++){
//                        double[] testResult = Helper.testTime(Helper.genArr(len, bound, mode));
//                        String testResultToString = Arrays.stream(testResult).boxed().toList().toString();
//                        String testResultToResString = bound + ", " + len + ", " + testResultToString.substring(1, testResultToString.length() - 1) + ", "+mode;
//
//                        Helper.writeNewLineToFile(nameOfCSV, testResultToResString);
//                    }
//                }
//            }
//        }
//
//        //with List:
//
//        //creating csv
//        nameOfCSV = "D:\\git_folder\\ASD\\Semestrovka\\src\\all\\ListMain.csv";
//        res = new ArrayList<>();
//        Helper.writeHeadersAndResToFile(nameOfCSV, res);
//
//
//        //adding Strings
//        for(int bound:bounds){
//            for(int len:resLengths){
//                for(String mode:modes) {
//                    for(int z=0;z<2;z++){
//                        double[] testResult = Helper.testTime(Helper.genList(len, bound, mode));
//                        String testResultToString = Arrays.stream(testResult).boxed().toList().toString();
//                        String testResultToResString = bound + ", " + len + ", " + testResultToString.substring(1, testResultToString.length() - 1) + ", "+mode;
//
//                        Helper.writeNewLineToFile(nameOfCSV, testResultToResString);
//                    }
//                }
//            }
//        }




//        //start2
//        for(int bound: bounds){
//            for(int i:resLengths){
//                String innerString = "";
//                innerString += bound+",";
//                arr = Helper.genArr(  i, bound, "absolutelyRandom");
//                double[] resTimeAndSwapsAndJumps = Helper.testTime( arr);
//                innerString += i+",";
//                innerString += resTimeAndSwapsAndJumps[0]+",";
//                innerString += resTimeAndSwapsAndJumps[1]+",";
//                innerString += resTimeAndSwapsAndJumps[2];
//                result.add( innerString );
//            }
//            System.out.println();
//        }
//
//
//        for(Object o:result){
//            System.out.println( o );
//        }




    }
}


