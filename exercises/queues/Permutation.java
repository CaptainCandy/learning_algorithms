/* *****************************************************************************
 *  Name: Xinze Tang
 *  Date: 2020-05-13
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rq.enqueue(item);
        }

        for (String s: rq
             ) {
            if (k == 0) break;
            StdOut.println(s);
            k--;
        }

    }
}
