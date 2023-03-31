package all;

import org.junit.Assert;
import org.junit.Test;

public class TestHelper {


    @Test
    public void testCombSortOnInteger(){

        Integer[] arr = new Integer[] {3,2,1};
        Helper.combSort( arr );

        Assert.assertArrayEquals( new Integer[] {1,2,3}, arr );

    }

    @Test
    public void testCombSortOnString(){

        String[] arrStr = new String[] {"3","2","1"};
        Helper.combSort( arrStr );

        Assert.assertArrayEquals( new String[] {"1","2","3"}, arrStr );

    }

    @Test
    public void testPow(){

        Assert.assertEquals( 1, Helper.pow(10,0) );
        Assert.assertEquals( 10, Helper.pow(10,1) );
        Assert.assertEquals( 100, Helper.pow(10,2) );
        Assert.assertEquals( 1000, Helper.pow(10,3) );
        Assert.assertEquals( 10000, Helper.pow(10,4) );
        Assert.assertEquals( 100000, Helper.pow(10,5) );
        Assert.assertEquals( 1000000, Helper.pow(10,6) );
        Assert.assertEquals( 10000000, Helper.pow(10,7) );
        Assert.assertEquals( 100000000, Helper.pow(10,8) );
        Assert.assertEquals( 1000000000, Helper.pow(10,9) );

    }

}
