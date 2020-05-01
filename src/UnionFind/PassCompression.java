package UnionFind;

public class PassCompression {
    private int id[];

    public PassCompression(int N){
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int p){
        while (p != id[p]){
            id[p] = id[id[p]];  //one-pass variant
            p = id[p];
        }
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
