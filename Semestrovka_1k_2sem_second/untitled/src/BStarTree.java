import java.util.ArrayList;
import java.util.Arrays;

public class BStarTree {

    class Node {

        int amountOfStoredKeys;
        int[] keys = new int[ 2 * p - 1 ];

        Node[] nextNodes = new Node[ 2 * p ];

        boolean isLeaf = true;

        /**
         *
         *
         * @return int[] {operations, res} t
         */
        public int[] find(int k) {
            int operations=0;
            for (int i = 0; i < this.amountOfStoredKeys; i++) {
                //System.out.println( ":"+this.keys[i]+" "+ k );
                //
                operations+=1;
                //
                if (this.keys[i] == k) {
                    return new int[] {operations,i};
                }
            }
            return new int[] {operations,-1};
        }

        public boolean isRoot(){
            return this==root;
        }

        public boolean checkConditionals(){
            if( !this.isRoot() ){
                return amountOfStoredKeys >= p-1;
            }
            return true;
        }

    }

    private int p;
    private Node root;

    public BStarTree(int p) {
        if( !( p>=2 ) ){
            throw new IllegalArgumentException("cant be less than 2");
        }
        this.p = p;
        root = new Node();
        root.amountOfStoredKeys = 0;
        root.isLeaf = true;
    }

    // search the keys
    /**
     * searching key starting from Node x
     * @return Object[] { operations, key found? Node : null } t
     */
    private Object[] search(Node x, int key) { // return {(long)operations, Node}
        int i = 0;
        long operations=0;

        if (x == null) {
            return new Object[]{operations, x};
        }

        for (i = 0; i < x.amountOfStoredKeys; i++) {
            operations++;
            if (key < x.keys[i]) {
                break;
            }

            operations++;
            if (key == x.keys[i]) {
                return new Object[] {operations, x};
            }
        }

        operations++;
        if (x.isLeaf) {
            return new Object[] {operations, null};
        } else {
            Object[] res = search(x.nextNodes[i], key);
            return new Object[] { (long)res[0]+operations, res[1] };
        }
    }

    // split function

    /**
     *
     * @return operations t
     */
    public long split(Node x, int pos, Node y) {
        long operations=0;

        Node z = new Node();
        z.isLeaf = y.isLeaf;
        z.amountOfStoredKeys = p - 1;
        operations+=3;

        for (int j = 0; j < p - 1; j++) {
            z.keys[j] = y.keys[j + p];
            operations++;
        }
        if (!y.isLeaf) {
            operations++;
            for (int j = 0; j < p; j++) {
                z.nextNodes[j] = y.nextNodes[j + p];
                operations++;
            }
        }

        y.amountOfStoredKeys = p - 1;

        for (int j = x.amountOfStoredKeys; j >= pos + 1; j--) {
            x.nextNodes[j + 1] = x.nextNodes[j];
            operations++;
        }
        x.nextNodes[pos + 1] = z;
        operations++;

        for (int j = x.amountOfStoredKeys - 1; j >= pos; j--) {
            x.keys[j + 1] = x.keys[j];
            operations++;
        }
        x.keys[pos] = y.keys[p - 1];
        x.amountOfStoredKeys = x.amountOfStoredKeys + 1;
        operations+=2;
        return operations;
    }

    // insert the keys

    /**
     *
     * @return operations t
     */
    public long insert(final int key) {
        long operations=0;
        Node r = root;

        if (r.amountOfStoredKeys == 2 * p - 1) {
            Node s = new Node();
            root = s;
            s.isLeaf = false;
            s.amountOfStoredKeys = 0;
            s.nextNodes[0] = r;
            operations+=2;
            operations+=split(s, 0, r);
            operations+=insertPr(s, key);
        } else {
            operations+=insertPr(r, key);
        }
        return operations;
    }

    // insert the node

    /**
     *
     *
     * @return operations t
     */
    private long insertPr(Node x, int k) { //return operations
        long operations =1;

        if (x.isLeaf) {
            int i = 0;
            for (i = x.amountOfStoredKeys - 1; i >= 0 && k < x.keys[i]; i--) {
                x.keys[i + 1] = x.keys[i];
                operations++;
            }
            x.keys[i + 1] = k;
            x.amountOfStoredKeys = x.amountOfStoredKeys + 1;
            operations+=2;
        } else {
            int i = 0;
            for (i = x.amountOfStoredKeys - 1; i >= 0 && k < x.keys[i]; i--) {
            }
            i++;

            Node tmp = x.nextNodes[i];
            operations+=1;

            if (tmp.amountOfStoredKeys == 2 * p - 1) {
                //
                operations+=1;
                //
                operations+=split(x, i, tmp);
                if (k > x.keys[i]) {
                    i++;
                    //
                    operations+=1;
                    //
                }
            }
            operations+=insertPr(x.nextNodes[i], k);
        }
        return operations;
    }

    /**
     *
     *
     * @return operations t
     */
    private int remove(Node x, int key) {
        int operations =0;

        int[] resFind = x.find(key);
        operations+=resFind[0];
        int pos = resFind[1];

        if (pos != -1) {
            //
            operations+=1;
            //
            if (x.isLeaf) {
                int i = 0;
                for (i = 0; i < x.amountOfStoredKeys && x.keys[i] != key; i++) {
                }
                for (; i < x.amountOfStoredKeys; i++) {
                    //
                    operations+=1;
                    //
                    if (i != 2 * p - 2) {
                        x.keys[i] = x.keys[i + 1];
                    }
                }
                x.amountOfStoredKeys--;
                return operations;
            }
            if (!x.isLeaf) {
                Node pred = x.nextNodes[pos];
                int predKey = 0;
                //
                operations+=1;
                //
                if (pred.amountOfStoredKeys >= p) {
                    //
                    operations+=1;
                    //
                    while(true) {
                        if (pred.isLeaf) {
                            //
                            operations+=1;
                            //
                            //todo that "if" added:
                            if(pred.amountOfStoredKeys >0){
                                //
                                operations+=1;
                                //
                                predKey = pred.keys[pred.amountOfStoredKeys - 1];
                                break;
                            }
                        } else {
                            //
                            operations+=1;
                            //
                            pred = pred.nextNodes[pred.amountOfStoredKeys];
                        }
                    }
                    operations += remove(pred, predKey);
                    x.keys[pos] = predKey;
                    return operations;
                }

                Node nextNode = x.nextNodes[pos + 1];
                //
                operations+=1;
                //
                if (nextNode.amountOfStoredKeys >= p) {
                    int nextKey = nextNode.keys[0];
                    //
                    operations+=1;
                    //
                    if (!nextNode.isLeaf) {
                        nextNode = nextNode.nextNodes[0];
                        //
                        operations+=1;
                        //
                        while(true) {
                            if (nextNode.isLeaf) {
                                //
                                operations+=1;
                                //
                                nextKey = nextNode.keys[nextNode.amountOfStoredKeys - 1];
                                break;
                            } else {
                                //
                                operations+=1;
                                //
                                nextNode = nextNode.nextNodes[nextNode.amountOfStoredKeys];
                            }
                        }
                    }
                    operations += remove(nextNode, nextKey);
                    x.keys[pos] = nextKey;
                    return operations;
                }

                int temp = pred.amountOfStoredKeys + 1;
                pred.keys[pred.amountOfStoredKeys++] = x.keys[pos];
                //
                operations+=1;
                //
                for (int i = 0, j = pred.amountOfStoredKeys; i < nextNode.amountOfStoredKeys; i++) {
                    pred.keys[j++] = nextNode.keys[i];
                    pred.amountOfStoredKeys++;
                    //
                    operations+=1;
                    //
                }
                for (int i = 0; i < nextNode.amountOfStoredKeys + 1; i++) {
                    pred.nextNodes[temp++] = nextNode.nextNodes[i];
                    //
                    operations+=1;
                    //
                }

                x.nextNodes[pos] = pred;
                //
                operations+=1;
                //
                for (int i = pos; i < x.amountOfStoredKeys; i++) {
                    if (i != 2 * p - 2) {
                        x.keys[i] = x.keys[i + 1];
                    }
                    //
                    operations+=1;
                    //
                }
                for (int i = pos + 1; i < x.amountOfStoredKeys + 1; i++) {
                    if (i != 2 * p - 1) {
                        x.nextNodes[i] = x.nextNodes[i + 1];
                    }
                    //
                    operations+=1;
                    //
                }
                x.amountOfStoredKeys--;
                if (x.amountOfStoredKeys == 0) {
                    if (x == root) {
                        root = x.nextNodes[0];
                    }
                    x = x.nextNodes[0];
                    //
                    operations+=1;
                    //
                }
                operations += remove(pred, key);
                return operations;
            }
        } else {
            for (pos = 0; pos < x.amountOfStoredKeys; pos++) {
                if (x.keys[pos] > key) {
                    break;
                }
                //
                operations+=1;
                //
            }
            Node tmp = x.nextNodes[pos];
            if (tmp.amountOfStoredKeys >= p) {
                operations += remove(tmp, key);
                //
                operations+=1;
                //
                return operations;
            }
            //
            Node nb = null;
            int devider = -1;

            if (pos != x.amountOfStoredKeys && x.nextNodes[pos + 1].amountOfStoredKeys >= p) {
                devider = x.keys[pos];
                nb = x.nextNodes[pos + 1];
                x.keys[pos] = nb.keys[0];
                tmp.keys[tmp.amountOfStoredKeys++] = devider;
                tmp.nextNodes[tmp.amountOfStoredKeys] = nb.nextNodes[0];
                //
                operations+=5;
                //
                for (int i = 1; i < nb.amountOfStoredKeys; i++) {
                    nb.keys[i - 1] = nb.keys[i];
                    //
                    operations+=1;
                    //
                }
                for (int i = 1; i <= nb.amountOfStoredKeys; i++) {
                    nb.nextNodes[i - 1] = nb.nextNodes[i];
                    //
                    operations+=1;
                    //
                }
                nb.amountOfStoredKeys--;
                operations += remove(tmp, key);
                return operations;
            } else if (pos != 0 && x.nextNodes[pos - 1].amountOfStoredKeys >= p) {
                devider = x.keys[pos - 1];
                nb = x.nextNodes[pos - 1];
                x.keys[pos - 1] = nb.keys[nb.amountOfStoredKeys - 1];
                Node child = nb.nextNodes[nb.amountOfStoredKeys];
                //
                operations+=3;
                //
                nb.amountOfStoredKeys--;

                for (int i = tmp.amountOfStoredKeys; i > 0; i--) {
                    tmp.keys[i] = tmp.keys[i - 1];
                    //
                    operations+=1;
                    //
                }
                tmp.keys[0] = devider;
                for (int i = tmp.amountOfStoredKeys + 1; i > 0; i--) {
                    tmp.nextNodes[i] = tmp.nextNodes[i - 1];
                    //
                    operations+=1;
                    //
                }
                tmp.nextNodes[0] = child;
                tmp.amountOfStoredKeys++;
                operations += remove(tmp, key);
                return operations;
            } else {
                Node lt = null;
                Node rt = null;
                boolean last = false;
                if (pos != x.amountOfStoredKeys) {
                    devider = x.keys[pos];
                    lt = x.nextNodes[pos];
                    rt = x.nextNodes[pos + 1];
                    //
                    operations+=2;
                    //
                } else {
                    devider = x.keys[pos - 1];
                    rt = x.nextNodes[pos];
                    lt = x.nextNodes[pos - 1];
                    last = true;
                    pos--;
                    //
                    operations+=2;
                    //
                }
                for (int i = pos; i < x.amountOfStoredKeys - 1; i++) {
                    x.keys[i] = x.keys[i + 1];
                    //
                    operations+=1;
                    //
                }
                for (int i = pos + 1; i < x.amountOfStoredKeys; i++) {
                    x.nextNodes[i] = x.nextNodes[i + 1];
                    //
                    operations+=1;
                    //
                }
                x.amountOfStoredKeys--;
                lt.keys[lt.amountOfStoredKeys++] = devider;

                for (int i = 0, j = lt.amountOfStoredKeys; i < rt.amountOfStoredKeys + 1; i++, j++) {
                    if (i < rt.amountOfStoredKeys) {
                        lt.keys[j] = rt.keys[i];
                    }
                    lt.nextNodes[j] = rt.nextNodes[i];
                    //
                    operations+=2;
                    //
                }
                lt.amountOfStoredKeys += rt.amountOfStoredKeys;
                if (x.amountOfStoredKeys == 0) {
                    if (x == root) {
                        root = x.nextNodes[0];
                    }
                    x = x.nextNodes[0];
                    //
                    operations+=2;
                    //
                }
                operations += remove(lt, key);
                return operations;
            }

        }
        return operations;
    }

    /**
     *
     * @return long[] {operations, 0 or 1} t
     */
    public long[] remove(int key) { // returns {operations, 0 or 1}
        Object[] operationsAndNode = search(root, key);
        Node x = (Node)operationsAndNode[1];
        long operationsInSearch = (long)operationsAndNode[0];

        if (x == null) {
            return new long[] { operationsInSearch, 0 };
        }
        long operationsInDeleting = remove(root, key);
        return new long[] { operationsInSearch+operationsInDeleting, 1 };
    }


    /**
     *
     * @return long[] {operations, contains? 1 : 0 } t
     */
    public long[] contain(int k) { //return long {operations, 1 or 0}
        Object[] operationsAndNode = this.search(root, k);
        if ( operationsAndNode[1]!= null) {
            return new long[] {(long)operationsAndNode[0], 1};
        } else {
            return new long[] {(long)operationsAndNode[0], 0};
        }
    }





    public void show() {
        System.out.println();
        show(root,0);
        System.out.println();
    }

    // show from the node
    private void show(Node x, int layer) {
        assert (x == null);
        System.out.print(" lay"+layer+"|");
        for (int i = 0; i < x.amountOfStoredKeys; i++) {
            System.out.print(x.keys[i] + " ");
        }
        System.out.print("| ");
        if (!x.isLeaf) {
            System.out.println();
            for (int i = 0; i < x.amountOfStoredKeys + 1; i++) {
                show(x.nextNodes[i], layer+1);
            }
        }
    }

    public void showCustom(){
        ArrayList<ArrayList<ArrayList<Integer>>> res = showCustom( root, 0, new ArrayList<ArrayList<ArrayList<Integer>>>() );
        int count=0;
        for( ArrayList<ArrayList<Integer>> inner:res ){
            System.out.println( "layer:"+count+" "+inner );
            count++;
        }
        System.out.println();
    }

    //show from the node
    private ArrayList<ArrayList<ArrayList<Integer>>> showCustom(Node x, int layer, ArrayList<ArrayList<ArrayList<Integer>>> list){

        assert (x == null);
        ArrayList<Integer> inner = new ArrayList<>();
        for (int i = 0; i < x.amountOfStoredKeys; i++) {
            inner.add(x.keys[i]);
        }
        if( list.size()<=layer ){
            list.add( new ArrayList<>() );
        }
        list.get(layer).add(inner); //не по порядку, просто чтобы видеть высоту дерева

        if (!x.isLeaf) {
            for (int i = 0; i < x.amountOfStoredKeys + 1; i++) {
                list = showCustom(x.nextNodes[i], layer+1, list);
            }
        }
        return list;

    }


    public int getAmountOfKeysInWholeTree(){
        int result = 0;
        if( root==null ){
            return 0;
        }
        ArrayList<ArrayList<ArrayList<Integer>>> res = showCustom( root,0, new ArrayList<>() );
        for( ArrayList<ArrayList<Integer>> inner:res ){
            for( ArrayList<Integer> iinner:inner ){
                result += iinner.size();
                //System.out.println( iinner );
            }
        }
        return result;
    }





    public static void main(String[] args) {
        BStarTree b = new BStarTree(3);
        b.insert(8);
        b.showCustom();
        b.insert(9);
        b.showCustom();
        b.insert(10);
        b.showCustom();
        b.insert(11);
        b.showCustom();
        b.insert(15);
        b.showCustom();
        b.insert(20);
        b.showCustom();
        b.insert(17);
        b.showCustom();

        b.remove(10);
        b.showCustom();

        b.remove(8);
        b.showCustom();



        b.insert(22);
        b.insert(23);
        b.insert(24);
        b.insert(25);
        b.showCustom();

//        System.out.println("spl");
//        b.split(b.root, 0, b.root.nextNodes[1]);
//        b.showCustom();

//        b.split(b.root, 0, b.root.nextNodes[0]);
//        b.showCustom();
//
//        b.split(b.root, 1, b.root.nextNodes[0]);
//        b.showCustom();

        b.insert(124);
        b.insert(1242);
        b.insert(1234);
        b.insert(92841);
        b.insert(437289);
        b.insert(1423);
        b.insert(6239);
        b.insert(432879);
        b.insert(42398);
        b.insert(46782);
        b.insert(32748);
        b.insert(4328);

        b.showCustom();

        System.out.println( b.getAmountOfKeysInWholeTree() );

        System.out.println(Arrays.toString(b.contain(10)));
        System.out.println(Arrays.toString(b.contain(124)));
        System.out.println(Arrays.toString(b.remove(124)));

        b.showCustom();

    }
}
