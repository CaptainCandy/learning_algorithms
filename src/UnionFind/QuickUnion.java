package UnionFind;

public class QuickUnion {
    private int id[];

    public QuickUnion(int N){
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int p){
        while (p != id[p]) p = id[p];
        return p;
    }

    public boolean Connected(int p, int q){
        return root(p) == root(q);
    }

    public void Union(int p, int q){
        id[root(p)] = root(q);
    }

    public static void main(String[] args) {

    }
}
