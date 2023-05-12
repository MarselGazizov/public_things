import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper {

    static Random r = new Random();

    public static int[] genArr(int len, int boundOfElements) {

        int[] arrOfElementsToAdd = new int[len];

        for( int i=0;i<len;i++ ){
            arrOfElementsToAdd[i] = r.nextInt(boundOfElements);
        }
        return arrOfElementsToAdd;

    }


    public static int[] genSubArrWithoutIndexRepeats(int lenOfArrRes, int[] baseArr) {

        int[] arrRes = new int[lenOfArrRes];

        List<Integer> indexesOf_BaseArrElements_ForArrRes = new ArrayList<>();
        for( int i=0;i<baseArr.length;i++ ){
            indexesOf_BaseArrElements_ForArrRes.add(i);
        }

        while( indexesOf_BaseArrElements_ForArrRes.size()!= lenOfArrRes){
            indexesOf_BaseArrElements_ForArrRes.remove( r.nextInt(indexesOf_BaseArrElements_ForArrRes.size()) );
        }

        for( int i=0;i<lenOfArrRes;i++ ){
            arrRes[i]= baseArr[indexesOf_BaseArrElements_ForArrRes.get(i)];
        }

        return arrRes;

    }


    public static double getAverageOfList(List<Long> list) {
        long sum =0;

        for(long elem:list){
            sum+=elem;
        }

        double res = ((double)sum)/list.size();

        return res;
    }
}
