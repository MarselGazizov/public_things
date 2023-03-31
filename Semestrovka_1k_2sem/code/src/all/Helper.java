package all;

import java.io.*;
import java.util.*;

public class Helper {




    static int dcase = 0;

    public static double[] testTime( Integer[] arr){

        //set time
        long startTime = System.nanoTime();

        int[] amountOfSwapsAndJumps = Helper.combSort( arr );

        //check time
        long endTime = System.nanoTime();

        dcase ++;
        double resTime = (endTime - startTime)/ (double)Helper.pow(10,6);
        System.out.println( "case="+dcase+";  " + "bound;  " + "length="+arr.length+";  " + "time(ms)="+resTime+";  " +"swaps="+amountOfSwapsAndJumps[0]+";  "+ "jumps="+amountOfSwapsAndJumps[1]+";" );
        return new double[] {resTime, amountOfSwapsAndJumps[0], amountOfSwapsAndJumps[1]};
    }

    public static double[] testTime( ArrayList<Integer> arr){

        //set time
        long startTime = System.nanoTime();

        int[] amountOfSwapsAndJumps = Helper.combSort( arr );

        //check time
        long endTime = System.nanoTime();

        dcase ++;
        double resTime = (endTime - startTime)/ (double)Helper.pow(10,6);
        System.out.println( "case="+dcase+";  " + "bound;  " + "length="+arr.size()+";  " + "time(ms)="+resTime+";  " +"swaps="+amountOfSwapsAndJumps[0]+";  "+ "jumps="+amountOfSwapsAndJumps[1]+";" );
        return new double[] {resTime, amountOfSwapsAndJumps[0], amountOfSwapsAndJumps[1]};
    }






    public static <E extends Comparable<? super E>> int[] bubbleSort(E[] arr) {
        int swaps = 0;
        int jumps=0;
        E temp;
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if ( arr[j].compareTo(arr[j + 1])>0 ) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    swaps+=1;
                }
            }
            jumps+=arr.length - i - 1;
            if (!swapped) {
                break;
            }
        }
        return new int[] { swaps,jumps };
    }

    public static <E extends Comparable<? super E>> int[] combSort( E[] input ) {
        int swaps = 0;
        int jumps =0;

        int gap = input.length;
        boolean swapped = true;
        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / 1.247330950103979);
            }

            int i = 0;
            swapped = false;
            while (i + gap < input.length) {
                if (input[i].compareTo(input[i + gap]) > 0) {
                    E t = input[i];
                    input[i] = input[i + gap];
                    input[i + gap] = t;
                    swapped = true;
                    swaps +=1;
                }
                i++;
                jumps += 1;
            }
        }
        return new int[] {swaps,jumps};
    }

    public static int[] combSort( ArrayList<Integer> input ) {
        int swaps = 0;
        int jumps =0;

        int gap = input.size();
        boolean swapped = true;
        Integer elem;
        Integer nextElem;
        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / 1.247330950103979);
            }

            int i = 0;
            swapped = false;
            while (i + gap < input.size()) {
                elem =input.get(i);
                nextElem =input.get(i+gap);
                if (elem.compareTo(nextElem) > 0) {
                    input.set(i, nextElem);
                    input.set(i+gap, elem);
                    swapped = true;
                    swaps +=1;
                }
                i++;
                jumps += 1;
            }
        }
        return new int[] {swaps,jumps};
    }







    public static int pow(int elem, int deg ){

        int res = 1;
        int adding = elem;

        String bs = Integer.toBinaryString( deg );

        for( int i =bs.length()-1; i>=0; i-- ){

            if( bs.charAt(i)=='1' ){
                res *= adding;
            }

            adding *= adding;

        }

        //System.out.println( res );
        return res;
    }

    public static Integer[] genArr(int len, int bound, String mode){
        if( mode.equals( "someSorted" ) ){
            Integer[] res = new Integer[len];

            for(int i =0; i<len; i++){
                res[i] = i;
            }

            Random r =new Random();
            int index=r.nextInt( len/3 )+1;
            while( index < len ){
                res[ index ] = r.nextInt(bound);
                index+=r.nextInt( len/3 )+1;
            }

            return res;
        }
        else if( mode.equals( "absolutelyRandom" ) ){
            Integer[] res = new Integer[len];
            Random r = new Random();

            for(int i =0; i<len; i++){
                res[i] = r.nextInt(bound);
            }

            return res;
        }
        else if(mode.equals( "sorted" )){
            Integer[] res = new Integer[len];

            for(int i =0; i<len; i++){
                res[i] = i;
            }

            return res;
        }
        else if(mode.equals( "reversedSorted" )){
            Integer[] res = new Integer[len];

            for(int i =0; i<len; i++){
                res[i] = ( len-1 )-i;
            }

            return res;
        }
        else{
            return null;
        }
    }

    public static List<Integer> genList(int len, int bound, String mode){
        if( mode.equals( "someSorted" ) ){

            List<Integer> res = new ArrayList<>();

            for(int i =0; i<len; i++){
                res.add(i);
            }

            Random r =new Random();
            int index=r.nextInt( len/3 )+1;
            while( index < len ){
                res.set( index,r.nextInt(bound) );
                index+=r.nextInt( len/3 )+1;
            }

            return res;
        }
        else if( mode.equals( "absolutelyRandom" ) ){
            List<Integer> res = new ArrayList<>();
            Random r = new Random();

            for(int i =0; i<len; i++){
                res.add( r.nextInt(bound) );
            }

            return res;
        }
        else{
            return null;
        }
    }


    public static void writeHeadersAndResToFile(String pathOut, ArrayList<String> data, String headers){
        try (PrintWriter writer = new PrintWriter(new File(pathOut))) {

            StringBuilder sb = new StringBuilder();
            sb.append("John Doe");
            sb.append(',');
            sb.append("Las Vegas");
            sb.append('\n');


            writer.write(headers);
            writer.write("\n");
            for(String str: data){
                writer.write(str);
                writer.write("\n");
            }
            writer.close();
            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeHeadersAndResToFile(String pathOut, ArrayList<String> data){
        String headers = "bound,length,time(ms),swaps,jumps,partsSorted?";
        writeHeadersAndResToFile( pathOut, data, headers);
    }



    public static void writeNewLineToFile( String pathOut, String str){
        try {
            FileWriter fw = new FileWriter(pathOut, true);

            fw.write(str);
            fw.write("\n");
            fw.close();
            System.out.println("done");

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
//        ArrayList<String> res = new ArrayList<>();
//        res.add("1111, 2222, 333333, swswda");
//        res.add("1111, 2222, 333333, swswda");
//        writeHeadersAndResToFile("test.csv",res);
//
//        writeNewLineToFile( "test.csv","ssssssssssss" );
//        writeNewLineToFile( "test.csv","ssssssssssss" );
//        writeNewLineToFile( "test.csv","ssssssssssss" );
//
//
//        //creating csv
//        res = new ArrayList<>();
//        writeHeadersAndResToFile("test.csv",res);
//
//        //adding Strings
//        String[] modes = new String[] {"absolutelyRandom", "someSorted"};
//        for(int bound=10;bound<11;bound++){
//            for(int len=10;len<100;len++){
//                for(String mode:modes){
//                    double[] testResult = testTime( genArr( len,bound,mode ) );
//                    String testResultToString = Arrays.stream( testResult ).boxed().toList().toString();
//                    String testResultToResString = bound+", "+ len+ ", "+testResultToString.substring(1,testResultToString.length()-1) +", " + mode;
//
//                    writeNewLineToFile( "test.csv", testResultToResString );
//                }
//
//            }
//        }


    }




    public static ArrayList< String[] > processFileToListOfStringArrs(String filePath) throws FileNotFoundException { // работа с файлами (.txt)

        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        int lastSlash = filePath.lastIndexOf("\\");
        System.out.println( lastSlash );
        String fileName = filePath.substring(lastSlash+1);

        String mode = "";
        if( fileName.contains("product") ){
            mode = "product";
        }
        else if(fileName.contains("someSorted")){
            mode = "someSorted";
        }
        else if(fileName.contains("absolutelyRandom")){
            mode = "absolutelyRandom";
        }
        else if(fileName.contains("sorted")){
            mode = "sorted";
        }
        else if(fileName.contains("reversedSorted")){
            mode = "reversedSorted";
        }
        else{
            mode="undefined";
        }

        System.out.println(mode);

        ArrayList<String[]> result = new ArrayList<>();

        while(sc.hasNext()){
            String in = sc.nextLine();
            String[] in2 = in.split("b");

            String bound = in2[1];
            String set = in2[0];

            String [] res = new String[] { set, bound, mode };
            result.add( res );
        }

        /// res = { [ "1, 1, 1", "2", "filename[:-3]" ],[ "1, 1, 1", "2", "filename[:-3]" ] }

        //System.out.println( "result=" );
        result.stream()
                .map(x-> Arrays.stream(x).toList())
                .forEach(System.out::println);
        return result;
    }




//    public static ArrayList< Integer[] > getArraysFromFiles( String[] fileNames ) throws FileNotFoundException {
//        ArrayList< Integer[] > resArrays = new ArrayList<>();
//
//        for( String fileName:fileNames ){
//            String[]!!! in = Helper.processFileToArrOfStrings( fileName );
//            String file = in[0];
//            String bound = in[1];
//
//            for(int i=0;i< file.length;i++){
//                int[] set = file[i].chars().toArray(); //set = [1,1]
//                Integer[] setRes = IntStream.of(set).boxed().toArray( Integer[]::new ); //setRes = [1,1]
//                resArrays.add( setRes );
//            }
//
//            ArrayList< List<Integer> > resLists = new ArrayList<>();
//            for(int i=0;i< file.length;i++){
//                int[] set = file[i].chars().toArray();
//                List<Integer> inner = Arrays.stream(set).boxed().toList();
//                resLists.add( inner );
//            }
//        }
//
//
//
//        return new ArrayList<>();
//
//    }





    public static void writeResultsOfTestingToExistingCSVFromWholeFile(String SCVPath, String filePath, String itIsProductFile, String onWhat) throws FileNotFoundException {


        File file = new File(filePath);
        Scanner sc = new Scanner(file);



        //adding Strings

        ArrayList<String[]> setAndBoundAndModeInStringArrInArrayList = Helper.processFileToListOfStringArrs(filePath); // {[ "1, 1, 1", "2", "random"], [ "1, 1, 1", "2", "random"]}
//        System.out.println( "data InStringArrInArrayList from file " );
//        setAndBoundAndModeInStringArrInArrayList.stream()
//                .map(x-> Arrays.stream(x).toList())
//                .forEach(System.out::println);

        for(int i=0;i< setAndBoundAndModeInStringArrInArrayList.size();i++){

            String[] inner = setAndBoundAndModeInStringArrInArrayList.get(i);

            String[] set = inner[0].split(", "); // (0,0) [ "0","0",] -> [0,0]
            Double bound = Double.valueOf( inner[1] );
            String mode = inner[2];

            //System.out.println( "set inner ="+ Arrays.stream(set).toList() );

            double[] testResult = new double[]{};
            double sizeOfBetweenRes=0;
            if(onWhat.equals("onArray")){
                Integer[] betweenRes = Arrays.stream(set)
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                sizeOfBetweenRes = betweenRes.length+0.0;
                testResult = Helper.testTime( betweenRes ); //[time, swaps, jumps]
            }
            else if( onWhat.equals("onList") ){
                ArrayList<Integer> betweenRes = new ArrayList<>( Arrays.stream(set)
                        .map(Integer::parseInt)
                        .toList() );
                sizeOfBetweenRes = betweenRes.size()+0.0;
                testResult = Helper.testTime( betweenRes ); //[time, swaps, jumps]
            }
            else{
                throw new ArithmeticException("sort on List|Array?");
            }



            List<Double> testResult2 = new ArrayList<>(Arrays.stream(testResult).boxed().toList());
            testResult2.add(0, sizeOfBetweenRes );
            testResult2.add(0, bound );
            String testResultToString = testResult2.toString();


            String testResultToResString = "";
            if( itIsProductFile.equals("y") ){
                testResultToResString = testResultToString.substring(1, testResultToString.length() - 1)+", "+mode +", "+ String.join("", set);
            }
            else{
                testResultToResString = testResultToString.substring(1, testResultToString.length() - 1)+", "+mode;
            }


            Helper.writeNewLineToFile(SCVPath, testResultToResString);
        }


        //todo tree map for products
//        /////////////////////////////////////////////////////////////////////////
//
//        // data = { {1,2,3,4,5,6},{...},{...} }
//        // resTimesSwapsJumps = { {0.001,22,22},{..},{..} }
//        // timeAndDataSortedMap = { {0.001,22,22}={1,2,3,4,5,6},  }
//
//        ArrayList<int[]> data = new ArrayList<>();
//
//        int[] inner = new int[7];
//        String subStringInIn2="";
//        for(int i = 0; i< dataInStringArray.length; i++){
//            subStringInIn2 = dataInStringArray[i];
//            inner = new int[number];
//            data.add(inner);
//            for(int j=0;j<number;j++){
//                inner[j] = Integer.parseInt(subStringInIn2.charAt(j)+"");
//            }
//        }
//
//        // ArrayList<int[]> data = { {0,0,0},{0,1,0} ... }
//        data.stream()
//                .forEach( x-> System.out.println( IntStream.of(x).boxed().toList()  ) );
//        System.out.println("!");
//
//
//
//
//        // below for sorting
//
//        List<double[]> resTimesSwapsJumps = data.stream()
//                .map( x->  IntStream.of( x ).boxed().toArray( Integer[]::new ) )
//                .map(Helper::testTime)
//                .toList();// { [0.88,33,33],[0.88,33,33], ...}
//
//
//        Comparator<List<Double>> f = new Comparator<List<Double>>() {
//            @Override
//            public int compare(List<Double> o1, List<Double> o2) {
//                if( (double)o1.get(0)>(double)o2.get(0) ){
//                    return 1;
//                }
//                else if( (double)o1.get(0) == (double)o2.get(0) ){
//                    return 0;
//                }
//                else{
//                    return -1;
//                }
//            }
//
//        };
//
//        TreeMap<List<Double>,List<Integer>> timeAndDataSortedMap = new TreeMap<>( f );
//        for(int i=0;i<resTimesSwapsJumps.size();i++){
//            timeAndDataSortedMap.put( Arrays.stream(resTimesSwapsJumps.get(i)).boxed().toList() , Arrays.stream(IntStream.of( data.get(i) ).boxed().toArray( Integer[]::new )).toList() );
//        }
//
//
//        for( List<Double> i: timeAndDataSortedMap.keySet() ){
//            System.out.println(i+"  "+ timeAndDataSortedMap.get(i) );
//        }
//        return "";
    }



}
