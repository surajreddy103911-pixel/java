package endsem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Edge{
	int c;
	int weight;
	Edge(int c,int w){
		this.c=c;
		this.weight=w;
	}
}

public class Rough {
	static ArrayList<ArrayList<Edge>> adj=new ArrayList<ArrayList<Edge>>();
	
	
	ArrayList<ArrayList<Edge>> bild(){
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		for(int i=0;i<a;i++) {
			adj.add(new ArrayList<>());
			
		}
		for(int i=0;i<b;i++) {
			int u=sc.nextInt();
			int v=sc.nextInt();
			int w=sc.nextInt();
		adj.get(u).add(new Edge(v, w));
		adj.get(v).add(new Edge(u, w));
		}
		
		      
		System.out.println("ok");
		for(int i=0;i<a;i++) {
		for(Edge n:adj.get(i)) {
			System.out.println(n.c);
		}
		}
		return adj;
		
	}
	
	static boolean cys(int a,int b,boolean[] v,ArrayList<ArrayList<Edge>> bs) {
		v[a]=true;
		
		for(Edge w:bs.get(a)) {
			int ne=w.c;
			if(!v[ne]) {
				if(cys(ne,a,v,bs)) {
				return true;}
			}
			
			
			
			
		}
		return false;
			
			
	}
	List<Integer> bfs(int s) {
		//ArrayList<Integer> adj=new ArrayList<>();
		boolean[]v=new boolean[adj.size()];
		ArrayList<Integer>b=new ArrayList<Integer>();
		Queue<Integer>q=new LinkedList<>();
		
		v[s]=true;
		q.add(s);
		
		while(!q.isEmpty()) {
			int ss=q.poll();
			b.add(ss);
			
			
			for(Edge e:adj.get(ss)) {
			int nn=e.c;
				if(!v[nn]) {
					v[nn]=true;
					q.add(nn);
				}
				
			}
			
		}
		return b;
		
		
	}
	
	void dfs(int s){
		boolean[] v=new boolean[adj.size()];
		System.out.println(s);
		dfa(v,s);
		
		
	}
	void dfa(boolean[] v,int s) {
		v[s]=true;
		System.out.println(s);
		
		for(Edge e:adj.get(s)) {
			int b=e.c;
			if(!v[b]) {
			//System.out.println(e);
			dfa(v,b);
			}
		}
		
	}
	
	
	
	void ra(ArrayList<ArrayList<Edge>> map,int c){
		for(Edge n:adj.get(c)) {
			System.out.println(n.c+n.weight);
		}
		//return map;
		
	}
	
	void dist(int n,ArrayList<ArrayList<Edge>> adj,int src){
		ArrayList<Integer> dist=new ArrayList<>(Collections.nCopies(n+1, Integer.MAX_VALUE));
		ArrayList<Integer> par=new ArrayList<>(Collections.nCopies(n+1, -1));
		
		dist.set(src ,0);
		par.set(src, src);
		
		PriorityQueue<Edge> pq=new PriorityQueue<>((a,b)->a.weight-b.weight);
		
		pq.add(new Edge(src, 0));
		
		while(!pq.isEmpty()) {
			Edge cu=pq.poll();
			int node=cu.c;
			int dis=cu.weight;
			
			for(Edge e:adj.get(node)) {
				int n1=e.c;
				int d=e.weight;
				if( d+ dis  < dist.get(n1)) {
					dist.set(n1, d+dis);
					par.set(n1, node);
					pq.add(new Edge(n1, dist.get(n1)));
					
				}
			}
			
		}
		System.out.println("dista");
		for(int i=0;i<n;i++) {
		System.out.println("node"+": "+  dist.get(i));
		}
		
		
		
		
		
	}
	void prims(int c,ArrayList<ArrayList<Edge>> ab) {
		int k[]=new int[c];
		int p[]=new int[c];
		Boolean[] b=new Boolean[c];
		Arrays.fill(k, Integer.MAX_VALUE);
		Arrays.fill(p, -1);
		Arrays.fill(b, false);
		k[0]=0;
		
		PriorityQueue<Edge> pq=new PriorityQueue<>((x,y)-> x.weight-  y.weight);
		pq.add(new Edge(0, 0));
		
		while(!pq.isEmpty()) {
			int cu=pq.poll().c;
			b[cu]=true;
			
			for(Edge e:adj.get(cu)) {
				int v=e.c;
				int w=e.weight;
				
				if(!b[v] &&w<k[v]) {
					p[v]=cu;
					k[v]=w;
					pq.add(new Edge(v, k[v]));
				}
			}
			
			
		}
		for(int i=0;i<c;i++) {
			System.out.println(k[i]+" "+p[i]);
		}
		System.out.println();
		
		
		
		
	}
	
	public static void main(String[] args) {
		Rough s=new Rough();
		s.bild();
		s.dist(adj.size(),adj,0);
		s.prims(adj.size(), adj);
		System.out.println("dcdv");
		s.ra(adj, 0);
		int ac=adj.size();
		boolean[] v=new boolean[ac];
		if(cys(0,-1,v,adj)) {
			
		}
		
	}
	

}
