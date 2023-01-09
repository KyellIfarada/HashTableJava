package edu.ser222.m03_04;

/**
 * A symbol table implemented using a hashtable with linear probing.
 * 
 * @author (put your name here), Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompletedLinearProbingHT<Key, Value> implements ProbingHT<Key, Value> {

	private int N;
	private int M;
	private final LinearProbeItem<Key, Value>[] st;

	
	public static class LinearProbeItem<Key, Val> 
	{
		
		private	Key	    NewKey;
		private	Val  	NewValue;
		
		
		public LinearProbeItem(Key key, Val value) 
		{
				    NewKey   = key;
		        	NewValue = value;
		}
	}	

		
		public CompletedLinearProbingHT() 
		{
			this(997);
		}

		public CompletedLinearProbingHT(int size) 
		{
			this.M = size;
			this.N = 0;
			st = (LinearProbeItem<Key, Value>[]) new LinearProbeItem[M];
		}
		
		
    @Override
    public int hash(Key key, int i) 
    {
    	return (((key.hashCode() & 0x7fffffff) + i) % M);
    }
    
    @Override
    public void put(Key key, Value val) 
    {
    	
    	int i = 0;
    	int HashIndex = hash(key,i);
    	int j=HashIndex;
    	LinearProbeItem Array = new LinearProbeItem<Key, Value>(key, val);

    	for (j = HashIndex; st[j] != null; j= (j+1) %M) 
    	{
    		
    		
    		if (st[j].NewKey.equals(key) && (st[j].NewValue.equals(null)))
    		{
    			st[j].NewValue =(Value) val;
    			return ;	
    		}
    		else if
    			(st[j].NewKey.equals(key) && !(st[j].NewValue.equals(null) ) ) 
    		{
    			
    			HashIndex = hash(key,i+1);
    			
    			st[j].NewValue = val;
    			return; 
    		}
    		
    		
	    	}
    	st[j] = Array;
    	N++;
    }
    	


    @Override
    public Value get(Key key) 
    {
    	int i = 0;
    	int HashIndex = hash(key,i);
    	int j;
    	for (j = HashIndex; st[j] != null; j = (j + 1) ) 
    	{
    		if (st[j].NewKey.equals(key)) 
    		{
    			return	st[j].NewValue;	
    		}
    	}
    	return null;
    }

    @Override
    public void delete(Key key) 
    {
    	
    	int i = 0;
    	int HashIndex = hash(key,i);
    	int j=HashIndex;
    	
    	for ( j = HashIndex; st[j] != null; j = (j + 1) %M  ) 
    	{
    		if (st[j].NewKey.equals(key))
    		{
    			for (int b = j + 1; st[j] != null ; b = (b + 1) %M ) 
    			{
    				st[j] = st[b];
    				j = (j + 1) % M;
    			}
    			st[j] = null;
    			N--;
    		}
    		
    	}
    	
    }

    @Override
    public boolean contains(Key key) {
    	 return (this.get(key) != null) ;
     
    }

    @Override
    public boolean isEmpty() {
        
        return N == 0 ;
    }

    @Override
    public int size() {
        //TODO
        return N;
    }

    @Override
    public Iterable<Key> keys() 
    {
    	
    	LinkedList<Key> NewList = new LinkedList<Key>();
    	for (int x = 0; x < M; x++) 
    	{
    			if(st[x] != null)
    		{
    			NewList.add(st[x].NewKey);
    		}
    	}
    		return NewList;
    }
    

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE PROBINGHT INTERFACE.

    @Override
    public int getM() {
        //TODO. We suggest something like:
        //return M;

        return M;
    }

    @Override
    public Object getTableEntry(int i) {
     
    //	if(!st[i].NewValue.equals(null))
        return st[i];
    }
}