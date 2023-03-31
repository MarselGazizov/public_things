package DataMaking;

import all.Helper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class R {

    public static void genDataToFile( String mode, int[] bounds, Integer[] lengths ){
        try(FileWriter wr = new FileWriter("D:\\git_folder\\ASD\\Semestrovka\\src\\DataMaking\\"+mode+".txt", false))
        {
            //adding Strings
            for(int bound:bounds){
                for(int len:lengths){
                    Integer[] set1 = Helper.genArr(len,bound,mode) ;
                    String set = Arrays.stream(set1).toList().toString();
                    String resSet = set.substring(1, set.length()-1) +"b"+bound;
                    wr.write( resSet );
                    wr.append('\n');
                }
                System.out.println("bound"+bound+"Done");
            }
            wr.flush();
            System.out.println("Done!");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {


        int[] bounds = new int[] {5, 10, 100, 1_000, 100_000, 10_000_000};

        ArrayList<Integer> resLengths = new ArrayList<>();
        for(int i =10;i<1_000_000;){
            resLengths.add( i );
            i+=2*i/( Integer.parseInt( (i+"").charAt(0)+"" ) );
        }
//        resLengths.add(2_000_000);
//        resLengths.add(3_000_000);
//        resLengths.add(4_000_000);
//        resLengths.add(5_000_000);
//        resLengths.add(7_000_000);

        resLengths = (ArrayList<Integer>) resLengths.stream().sorted().collect(Collectors.toList());
        System.out.println(resLengths);

        Integer[] lengths = resLengths.toArray( Integer[]::new );
        System.out.println( Arrays.stream(lengths).toList() );

        genDataToFile( "absolutelyRandom", bounds, lengths );
        genDataToFile( "someSorted", bounds, lengths );
        genDataToFile( "sorted", bounds, lengths );
        genDataToFile( "reversedSorted", bounds, lengths );

    }




}
