class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] ans = new int[2];
        int[] parent = new int[n];
        Arrays.fill(parent,-1);
        int[] rank = new int[n];
        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0] - 1;
            int v = edges[i][1] - 1;
            if(union(u,v,rank,parent)){
                ans[0] = u + 1;
                ans[1] = v + 1;
            }
        }
        
        return ans;
    }
    
    static int find(int i, int[] parent){
        if(parent[i] == -1){
            return i;
        }
        
        return find(parent[i],parent);
    }
    
    static boolean union(int a, int b, int[] rank, int[] parent){
        int s1 = find(a, parent);
        int s2 = find(b, parent);
        
        boolean isCyclic = false;
        if(s1 == s2){
            isCyclic =  true;
        }
        
        if(s1 != s2)
        parent[s2] = s1;     
        
        return isCyclic;
    }
}
