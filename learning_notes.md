# 第一周作业

### 问题描述

详细描述请参见：[Percolation](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php)

通俗理解：有一个n×n个方格组成的系统，每个单独的方格（site）都可以被打开，假如从顶层灌水，能找到任意一条通路能流到底层的话，那么称这一个系统是可渗透的（percolate）。每个单独的方格可能有三种状态：关闭（blocked），打开但没有来自顶层的水流过（open but empty），打开且有水流过（open and full）。


### 渗透模型

现在假设每个site被打开的概率是$p$，关闭的概率是$1-p$，通过模拟实验（非数学证明）我们可以发现，当$p$大于某个阈值的时候，这个系统大概率是可渗透的。我们可以通过蒙特卡洛仿真来得到$p$：先让所有site关闭，再随机挑选一个site将其打开，重复挑选直到系统变成可渗透的，记录此时的open的site的数量$x$，此时$p'=\frac{x}{n*n}$是对$p$的一个模拟。把上述实验重复多次，即可得到p的样本均值以及置信区间。

作业要求需要定义两个类，用来构建渗透模型以及相关的一些统计参数。

第一个类：`Percolation`

```java
public class Percolation {

    // creates n-by-n grid, with all sites initially blocked    
    public Percolation(int n)

    // opens the site (row, col) if it is not open already    
    public void open(int row, int col)

    // is the site (row, col) open?    
    public boolean isOpen(int row, int col)

    // is the site (row, col) full?    
    public boolean isFull(int row, int col)

    // returns the number of open sites  
    public int numberOfOpenSites()

    // does the system percolate?    
    public boolean percolates()

    // test client (optional)    
    public static void main(String[] args)

}
```

第二个类：`PercolationStats`

```java
public class PercolationStats {

    // perform independent trials on an n-by-n grid    
    public PercolationStats(int n, int trials)

    // sample mean of percolation threshold    
    public double mean()

    // sample standard deviation of percolation threshold    
    public double stddev()

    // low endpoint of 95% confidence interval
    public double confidenceLo()

    // high endpoint of 95% confidence interval
    public double confidenceHi()

  // test client (see below)
    public static void main(String[] args)

}
```

### 解答

先考虑第一个类。用一个 $n*n$ 的布尔矩阵表示每个site的开闭状态，为了能够使用加权并查集模型 `WeightedQuickUnionUF` ，需要把二维的数组转变称一维存储，用公式 $n*(row-1)+col$ 转化即可。

教授在课上提示，可以在首尾各增加一个虚拟site，分别和第一行、最后一行的site并起来，这样的话最后判断每一个渗透模型是否渗透，只需要判断这两个虚拟site是否连通即可；判断某个site是否是full的，只需要判断该site和首部的虚拟site是否连通即可。

但是这个方法会出现一个问题，在可渗透的系统中，位于最后一行的open but empty的site应该不是full的，但由于和尾部的虚拟site连通，尾部的虚拟site和首部的连通，会导致最后一行的open but empty的site在调用`isFull()`方法的时候会被判断成`true`，我这边采用的方法是另外初始化一个仅包含首部虚拟site而不包含尾部虚拟site的加权并查集模型，这样就避免了刚刚提到的这个问题，但是每次更新原来的模型的同时也要更新这个额外的模型，所以牺牲了空间存储，多了一倍的计算时间。

```java
public class Percolation {
    private boolean[][] grid;
    private final WeightedQuickUnionUF wqu;
    private final WeightedQuickUnionUF wquTop;
    private int countOpen = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        // initialized grid, 0 = blocked, 1 = open
        grid = new boolean[n][n];
        // plus 2 means the 2 vitual sites beyond the top line and
        // under the bottom line
        wqu = new WeightedQuickUnionUF(n*n + 2);
        wquTop = new WeightedQuickUnionUF(n*n + 1);
        for (int i = 0; i < n; i++) {
            wqu.union(0, map(1, i+1));
            wqu.union(n*n+1, map(n, i+1));
            wquTop.union(0, map(1, i+1));
        }
    }
    // map 2d array to 1d
    private int map(int row, int col) {
        return grid.length * (row - 1) + col;
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > grid.length || col < 1 || col > grid.length)
            throw new IllegalArgumentException("Index out of bounds. Please check.");
        if (!grid[row-1][col-1]) {
            grid[row-1][col-1] = true;
            countOpen++;

            if (row!=1 && isOpen(row-1, col)) {
                wqu.union(map(row, col), map(row - 1, col));
                wquTop.union(map(row, col), map(row - 1, col));
            }

            if (col!=1 && isOpen(row, col-1)) {
                wqu.union(map(row, col), map(row, col - 1));
                wquTop.union(map(row, col), map(row, col - 1));
            }

            if (row!=grid.length && isOpen(row+1, col)) {
                wqu.union(map(row, col), map(row + 1, col));
                wquTop.union(map(row, col), map(row + 1, col));
            }

            if (col!=grid.length && isOpen(row, col+1)) {
                wqu.union(map(row, col), map(row, col + 1));
                wquTop.union(map(row, col), map(row, col + 1));
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > grid.length || col < 1 || col > grid.length)
            throw new IllegalArgumentException("Index out of bounds. Please check.");
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (grid.length == 1) {
            return isOpen(1, 1);
        }
        return isOpen(row, col) && wquTop.connected(0, map(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (grid.length == 1) {
            return isOpen(1, 1);
        }
        return wqu.connected(grid.length*grid.length+1, 0);
    }

}
```

再说第二个类。第二个类就是要求我们对于仿真实验做一些统计操作。仿真实验的目的就是找到样本$p$的均值，在指定了$n$之后，要做多次的仿真实验，因此这个类的构造函数需要同时指定trail的数量。

在每次做随机数生成之后，要进行一步`isOpen()`的判断，这样就能保证每次选取并打开的site都是原先关闭的。所以生成随机数的次数一般来说都会比最后open的site的数量要大。

我在这里用一个`threshold[]`数组来存放每个trail结束之后的实例$p$值，即$\frac{x}{n*n}$，打开的site数量和总数量的比值。把最重要的均值算出来之后，其他的就是标准差和置信区间的计算，没啥好说的，可以直接调用教授写好的`StdStats`包。

不得不说这门课程的支撑学习材料，代码库以及评分系统都非常优秀，我提交了几次作业，每次作业的评阅都非常详细，哪里应该打空格的地方没有打，哪里应该加`final` `static`等关键字建议我在某些地方的代码应该怎么写比较符合规范，便于阅读，收获还是很大的。比如评阅建议我将`1.96`设置成一个常量，因为会多次调用。虽然在这个小工程里作用不是很大，但是对代码习惯的养成很有帮助。

```java
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double[] threshold;
    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) throw new IllegalArgumentException();
        threshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation per = new Percolation(n);
            while (!per.percolates()) {
                int site = StdRandom.uniform(n*n) + 1;
                int col = site % n;
                // 每一行的最后一个要特殊处理
                if (col == 0) col = n;
                int row = (site - col) / n + 1;
                if (!per.isOpen(row, col)) {
                    per.open(row, col);
                }
            }
            threshold[i] = (double) per.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
        // double sum = 0;
        // for (int i = 0; i < threshold.length; i++) {
        //     sum += threshold[i];
        // }
        // return sum/threshold.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
        // double mean = mean();
        // double sum = 0;
        // for (int i = 0; i < threshold.length; i++) {
        //     sum += Math.pow(threshold[i] - mean, 2);
        // }
        // return Math.pow(sum/(threshold.length - 1), 1/2);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(threshold.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(threshold.length);
    }

}
```

# 第二周作业

### 问题描述

详细描述请参见[Queues](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php)

简单来讲，就是实现一个双头队列（`Deque`. A double-ended queue or deque, pronounced “deck”），和一个随机队列`RandomizedQueue`。双头队列要求实现可以任意选择在队列的头部或尾部添加元素，或者从头部或尾部删除元素。随机队列的入列和普通队列相同，出列要求是从队列中随机选取一个元素出列，同时提供一个`sample()`方法，随机返回队列中的一个元素。

下面是教授要求的API：
```java
public class Deque<Item> implements Iterable<Item> {

    // construct an empty deque
    public Deque()

    // is the deque empty?
    public boolean isEmpty()

    // return the number of items on the deque
    public int size()

    // add the item to the front
    public void addFirst(Item item)

    // add the item to the back
    public void addLast(Item item)

    // remove and return the item from the front
    public Item removeFirst()

    // remove and return the item from the back
    public Item removeLast()

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()

    // unit testing (required)
    public static void main(String[] args)

}
```
```java
public class RandomizedQueue<Item> implements Iterable<Item> {

    // construct an empty randomized queue
    public RandomizedQueue()

    // is the randomized queue empty?
    public boolean isEmpty()

    // return the number of items on the randomized queue
    public int size()

    // add the item
    public void enqueue(Item item)

    // remove and return a random item
    public Item dequeue()

    // return a random item (but do not remove it)
    public Item sample()

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()

    // unit testing (required)
    public static void main(String[] args)

}
```

先说第一个类，因为要同时满足双向操作，所以很容易想到使用双向链表作为基础的数据结构，在每个`Node`私有类中添加一个`prior`指针指向节点的前驱。双向链表的节点每个占用内存48byte，正好满足题目对内存占用的要求。对于`size()`方法需要返回队列中的元素数量，只要维护一个`size`属性即可，每次入列出列对其进行`++`和`--`的操作，同时这个`size`属性可以用来判断队列是否为空。

```java
public class Deque<Item> implements Iterable<Item> {
    // inner class node 16+8*2+8+8=48 bytes memory
    private class Node {
        Item item;
        Node next;
        Node prior;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }

        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            else throw new NoSuchElementException("There is no next item. ");
        }

        public void remove() {
            throw new UnsupportedOperationException("This method is not supported. ");
        }
    }

    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() { }

    // is the deque empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the deque
    public int size() { return size; }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException("A null item detected."); }
        else if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
            size++;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prior = first;
            size++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException("A null item detected."); }
        else if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
            size++;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prior = oldLast;
            oldLast.next = last;
            size++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (!isEmpty()) {
            Item item = first.item;
            first = first.next;
            size--;
            return item;
        }
        else {
            throw new NoSuchElementException("The deque is empty.");
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (!isEmpty()) {
            Item item = last.item;
            last = last.prior;
            size--;
            return item;
        }
        else {
            throw new NoSuchElementException("The deque is empty.");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new ListIterator(); }

    // unit testing (required)
    public static void main(String[] args) {
        // unit testing大家自己写吧
    }
}
```

再说第二个类。随机队列的入列不需要再考虑，主要考虑当随机选取了一个节点之后，如何找到这个节点并remove掉。看了网上的一些解答，意识到这里可能确实是选择用数组作为底层的数据结构更好一些，因为数组可以在$O(1)$的操作时间中找到需要出列的节点。但是我既然已经用了链表那就还是用链表实现一下吧。

正常的想法是，随机生成一个索引`i`，从`1`开始经过`i-1`次操作找到出列元素，然后对出列元素 前驱的后继、后继的前驱 进行修改（当然边界情况要考虑，只有1个元素，只有2个元素以及随机索引在头尾的情况）。这边我考虑到我们的链表已经是双向链表了，如果根据索引`i`在上半部分还是下半部分（和`size/2`作比较），可以选择从头部或者尾部开始向后或向前寻找，这样把最坏情况$O(size)$降低到$O(size/2)$，在现实中应该是会有些速度提升的吧，然而我最后的timing打分还是没全过，169/193，应该是链表的原因了，看到网上有前辈用数组可以满分，见[EnPFighter](https://www.cnblogs.com/enigmaxp/p/5905598.html). `sample()`方法的实现原理和上面描述的差不多，只需要找到并返回`item`就行了，更简单一些。

关于随机遍历器，我想了想只用链表解决的话，还是跟上面这段一样，要写比较多的代码并且套循环，代码不整洁，速度也不够快，所以这里还是借用了临时数组的思想。记录一个随机生成的索引`target`和临时数组中未被抽到过的元素的最大所以`lastIndex`，每次生成在`[1, lastIndex]`之间生成随机`target`，并将`target`的元素和`lastIndex`的元素进行调换，然后执行`lastIndex--`以保证已经抽到过的不会再被抽到。

```java
public class RandomizedQueue<Item> implements Iterable<Item> {
    // inner class node 16+8*2+8+8=48 bytes memory
    private class Node {
        Item item;
        Node next;
        Node prior;
    }

    private class ListIterator implements Iterator<Item> {
        private int lastIndex;
        private Item[] temp;

        public ListIterator() {
            lastIndex = size - 1;
            temp = (Item[]) new Object[size];
            Node current = first;
            for (int i = 0; i < size; i++) {
                temp[i] = current.item;
                current = current.next;
            }
        }

        public boolean hasNext() { return lastIndex >= 0; }

        public Item next() {
            if (hasNext()) {
                int target = StdRandom.uniform(lastIndex + 1);
                Item item = temp[target];
                temp[target] = temp[lastIndex];
                temp[lastIndex] = item;
                lastIndex--;
                return item;
            }
            else throw new NoSuchElementException("There is no next item. ");
        }

        public void remove() {
            throw new UnsupportedOperationException("This method is not supported. ");
        }
    }

    private Node first;
    private Node last;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() { }

    // is the randomized queue empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the randomized queue
    public int size() { return size; }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("The item is null.");
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else {
                last.prior = oldLast;
                oldLast.next = last;
            }
            size++;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("The RandomQueue is empty.");
        else {
            if (size == 1) {
                Item item = first.item;
                first = null;
                last = null;
                size--;
                return item;
            }
            else {
                int i = StdRandom.uniform(size) + 1;
                if (i > size / 2) {
                    if (size == 2) {
                        Item item = last.item;
                        last = first;
                        last.prior = null;
                        first.next = null;
                        size--;
                        return item;
                    }
                    else {
                        if (i == size) {
                            Item item = last.item;
                            last = last.prior;
                            last.next = null;
                            size--;
                            return item;
                        }
                        else {
                            Node current = last;
                            for (int j = 0; j < size - i; j++) {
                                current = current.prior;
                            }
                            current.prior.next = current.next;
                            current.next.prior = current.prior;
                            size--;
                            return current.item;
                        }
                    }
                }
                else {
                    if (size == 2) {
                        Item item = first.item;
                        first = last;
                        last.prior = null;
                        first.next = null;
                        size--;
                        return item;
                    }
                    else {
                        if (i == 1) {
                            Item item = first.item;
                            first = first.next;
                            first.prior = null;
                            size--;
                            return item;
                        }
                        else {
                            Node current = first;
                            for (int j = 0; j < i - 1; j++) {
                                current = current.next;
                            }
                            current.prior.next = current.next;
                            current.next.prior = current.prior;
                            size--;
                            return current.item;
                        }
                    }
                }
            }
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("The RandomQueue is empty.");
        else {
            int i = StdRandom.uniform(size) + 1;
            if (i > size / 2) {
                Node current = last;
                for (int j = 0; j < size - i; j++) {
                    current = current.prior;
                }
                return current.item;
            }
            else {
                Node current = first;
                for (int j = 0; j < i - 1; j++) {
                    current = current.next;
                }
                return current.item;
            }
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new ListIterator(); }
}
```

最后要实现一个`Permutation`类用来产生队列中随机的`k`个不重复的元素。因为不重复这个条件在`Iterator`的`next()`方法已经实现了，所以直接调用`foreach`语句就行。

```java
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
```
最终成绩是92分，不够满分，有部分时间和空间爆了。