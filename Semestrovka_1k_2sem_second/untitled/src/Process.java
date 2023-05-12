import java.util.ArrayList;
import java.util.Arrays;

public class Process {


    public static void main(String[] args) {

        BStarTree tree = new BStarTree(10);

        int boundOfElements = 100_000;
        int[] arrOfElementsToAdd = Helper.genArr( 10_000, boundOfElements );
        int[] arrOfElementsToSearch = Helper.genSubArrWithoutIndexRepeats(100, arrOfElementsToAdd);
        int[] arrOfElementsToDelete = Helper.genSubArrWithoutIndexRepeats(1000, arrOfElementsToAdd);

//        System.out.println(Arrays.toString(arrOfElementsToAdd));
//        System.out.println();
//        System.out.println(Arrays.toString(arrOfElementsToSearch));
//        System.out.println();
//        System.out.println(Arrays.toString(arrOfElementsToDelete));



        ArrayList<Long> operationsInAdd = new ArrayList<>();
        ArrayList<Long> timesInAdd = new ArrayList<>();
        for( int elemToAdd: arrOfElementsToAdd ){
            long timeStart = System.nanoTime();
            long resAmountOfOperations = tree.insert(elemToAdd);
            long timeEnd = System.nanoTime();

            long timeRes = timeEnd-timeStart;
            operationsInAdd.add(resAmountOfOperations);
            timesInAdd.add(timeRes);
            System.out.println( "operationsAdd: "+resAmountOfOperations+" time: "+timeRes );
        }


        ArrayList<Long> operationsInSearch = new ArrayList<>();
        ArrayList<Long> timesInSearch = new ArrayList<>();
        for( int elemToSearch: arrOfElementsToSearch ){
            long timeStart = System.nanoTime();
            long[] resAmountOfOperationsAndBoolean = tree.contain(elemToSearch);
            long timeEnd = System.nanoTime();

            long timeRes = timeEnd-timeStart;
            operationsInSearch.add((long) resAmountOfOperationsAndBoolean[0]);
            timesInSearch.add(timeRes);
            System.out.println( "operationsSearch: "+resAmountOfOperationsAndBoolean[0]+" time: "+timeRes );
        }


        ArrayList<Long> operationsInDeleting = new ArrayList<>();
        ArrayList<Long> timesInDeleting = new ArrayList<>();
        for( int elemToDel: arrOfElementsToDelete ){
            System.out.println();
            System.out.println("start deleting");
            System.out.println( "to del: "+elemToDel );
            System.out.println("contains"+" "+elemToDel+" : "+ Arrays.toString(tree.contain(elemToDel)));
            //tree.showCustom();

            long timeStart = System.nanoTime();
            long[] resAmountOfOperationsAndBoolean = tree.remove(elemToDel);
            long timeEnd = System.nanoTime();
            System.out.println( "AMOUNT: "+tree.getAmountOfKeysInWholeTree() );

            long timeRes = timeEnd-timeStart;
            operationsInDeleting.add((long) resAmountOfOperationsAndBoolean[0]);
            timesInDeleting.add(timeRes);
            System.out.println( "operationsDelete: "+resAmountOfOperationsAndBoolean[0]+" time: "+timeRes );
            System.out.println("end deleting");
            System.out.println();
        }






        //tree.showCustom();

        System.out.println("Adding:");
        System.out.println( Helper.getAverageOfList(operationsInAdd)+" operations" );
        System.out.println( Helper.getAverageOfList(timesInAdd)+" nano sec" );
        System.out.println();

        System.out.println("Searching:");
        System.out.println( Helper.getAverageOfList(operationsInSearch)+" operations" );
        System.out.println( Helper.getAverageOfList(timesInSearch)+" nano sec" );
        System.out.println();

        System.out.println("Deleting:");
        System.out.println( Helper.getAverageOfList(operationsInDeleting)+" operations" );
        System.out.println( Helper.getAverageOfList(timesInDeleting)+" nano sec" );
        System.out.println();

    }




}
