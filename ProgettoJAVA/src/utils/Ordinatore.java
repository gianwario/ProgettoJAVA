package utils;

import java.util.ArrayList;

public class Ordinatore<T> {
	
	ArrayList<T> array;
	Comparabile<T> comparazione;
	
	public Ordinatore(ArrayList<T> array, Comparabile<T> comparazione) {
		
		this.array = array;
		this.comparazione = comparazione;
		
	}
	
	public void setArray(ArrayList<T> newArray) {
		
		this.array = newArray;
	}
	
	public void setComparazione(Comparabile<T> newComparazione) {
		
		this.comparazione = newComparazione;
	}
	
	public ArrayList<T> ordina() { //bubble sort
		
		for(int i = 0; i < array.size(); i++) {
			
			for(int j = 0; j < array.size()-1; j++) 
				
				if(comparazione.compara(array.get(j), array.get(j+1))> 0)	{				
					T tmp = array.get(j);
					array.set(j, array.get(j+1));
					array.set(j+1, tmp);
				}
		}
		return array;
	}
	


}
