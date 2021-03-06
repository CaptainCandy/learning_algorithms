package UnionFind;

public class WeightedQuickUnion {
    private int id[];
    private int sz[]; //maintain size of every tree on its root

    public WeightedQuickUnion(int N){
        id = new int[N];
        sz = new int[N];
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
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] > sz[j]){
            id[j] = i;
            sz[i] += sz[j];
        }
        else{
            id[i] = j;
            sz[j] += sz[i];
        }
    }

    public static void main(String[] args) {

    }
}
