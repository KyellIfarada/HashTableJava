package edu.ser222.m03_04;

/**
 * A symbol table implemented using a hashtable with chaining.
 * Does not support load balancing or resizing.
 * 
 * @author (put your name here), Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompletedTwoProbeChainHT<Key, Value> implements TwoProbeChainHT<Key, Value> {

    //any constructors must be made public
	private int N; // Number of Key Value Pairs
	private int M ; // Number of Hashtable pairs
	private	LinkedList<LinkedListChainNode>[] st;
	
	public  class LinkedListChainNode<Key,Value> 
	{
	private LinkedListChainNode NodeHead; // Head of each Chain
	Key   ThisKey ;
	Value ThisValue ; 
	
		
		public LinkedListChainNode (Key Key, Value val)
		
		{
			   ThisKey   = Key;
			   ThisValue = val; 
			   
		}
			
	}

  	public CompletedTwoProbeChainHT ()
  	{
  		this(997);	
  			
  	}
	
	//Creates LinkedList Vertical List for Horizontal List to be appended to
    public CompletedTwoProbeChainHT(int i) {
    		this.M = i;	
    		this.N = 0;
    	
    		
    		st =(LinkedList<LinkedListChainNode>[])  new LinkedList[M];
    		for(int j=0; j< M; j++)
    		{
    			
    			st[j] = new LinkedList<>();
    			
    		}
    }

	@Override
    public int hash(Key key) 
	{

       int hash1 = (((key.hashCode() & 0x7fffffff) % M));
       return hash1;
    }

    @Override
    public int hash2(Key key) 
    {
    	int hash2 = (((key.hashCode() & 0x7fffffff) % M) * 31) % M;
        return hash2;
    }

    @Override
    public void put(Key key, Value val) 
    {
    	
   
   		int HashIndex  =  hash(key);
   		int HashIndex2 =  hash2(key);
   		int ChainOneSize =  st[HashIndex].size();
    	int ChainTwoSize =  st[HashIndex2].size();
   		
   		//If a LinkedList exists at this Index ; Then find Key in LinkedList ; if Key Exists return value
   
   		for (int i = 0; i < ChainOneSize; i++) 
   		{
			if (st[HashIndex].get(i).ThisKey.equals(key)) 
			{
				st[HashIndex].get(i).ThisValue = val;
				return;
			}
		}

		for (int j = 0; j < ChainTwoSize; j++) 
		{
			if (st[HashIndex2].get(j).ThisKey.equals(key)) 
			{
				st[HashIndex2].get(j).ThisValue = val;
				return;
			}
		
		}
   
   
   
   
		// If Hash1 LinkedList Larger add to Hash2 and vice versa.
   
   
   
    	if (ChainOneSize < ChainTwoSize) 
    	{
			st[HashIndex].add(new LinkedListChainNode(key, val));
		} 

		else 
		{
			st[HashIndex2].add(new LinkedListChainNode(key, val));
		}
    	
    	//ADD Node counter
	     N++;
    		
    		
    		
    		
    	
    }

    @Override
    public Value get(Key key) {
    	
    	
    	int GHashIndex   =  hash(key);
   		int GHashIndex2  =  hash2(key);
    	int ChainOneSize =  st[GHashIndex].size();
    	int ChainTwoSize =  st[GHashIndex2].size();
   
    		for (int i = 0; i < ChainOneSize; i++) 
    		{
    			
    			if (st[GHashIndex].get(i).ThisKey.equals(key)) 
    			{
    				
    			
    				Value FoundValue = (Value) st[GHashIndex].get(i).ThisValue;
    				return FoundValue;
    			}
    		
    		}

    		for (int i = 0; i < ChainTwoSize; i++) 
    		{
    			if (st[GHashIndex2].get(i).ThisKey.equals(key)) 
    		
    			{
    				Value FoundValue =  (Value) st[GHashIndex2].get(i).ThisValue;
    				return FoundValue;
    			}

    		}
    		return null;

    	

    }
    @Override
    public void delete(Key key) 
    {
    	
    	int Hashvalue  = hash(key);
    	int Hashvalue2 = hash2(key);
    
    	for (int i = 0; i < st[Hashvalue].size(); i++) 
    	{
    		if (st[Hashvalue].get(i).ThisKey.equals(key)) 
    		{
    			st[Hashvalue].remove(i);
    			N--;
    		}
    	}
    	
    	for (int c = 0; c < st[Hashvalue2].size(); c++) 
    	{
    		if (st[Hashvalue2].get(c).ThisKey.equals(key)) 
    		{
    			st[Hashvalue2].remove(c);
    			N--;
    		}
    	}
    	
    }
    				
    @Override
    public boolean contains(Key key) 
    {
    	Value MyValue = get(key) ;
    	if (MyValue == null)
    		return false;
    	else 
    		return true;
    }

    @Override
    public boolean isEmpty() {
        if (this.size() == 0 )
        	return true;
        else
        {
        return false;
        }
    }

    @Override
    public int size() {
        
        return N;
    }

    @Override
    public Iterable<Key> keys() 
    {
    	
    	LinkedList<Key> NewList = new LinkedList<Key>();
    	
    	for (int a = 0; a < M; a++) 
    	{
    		for (int b = 0; b < st[a].size(); b++) 
    		{
    			NewList.add((Key) st[a].get(b).ThisKey);
    		}
    	}
    		return NewList;
    }
        	
    

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE TWOPROBECHAINHT INTERFACE.

    @Override
    public int getM() {
        //TODO. We suggest something like:
        //return M;

        return M;
    }

    @Override
    public int getChainSize(int i) {
        //TODO. We suggest something like:
        //return entries[i].size();

        return st[i].size();
    }
}