package utils;

import java.util.ArrayList;

public class Selezionatore<T> {
	
	ArrayList<T> input;
	ArrayList<T> output;
	Selezionabile<T> selezione;
	
	public Selezionatore(ArrayList<T> array, Selezionabile<T> selezione) {
		
		this.input = array;
		this.selezione = selezione;
		output = new ArrayList<T>();
	}
	
	public void setInput(ArrayList<T> newArray) {
		
		this.input = newArray;
	}
	
	public void setSelezione(Selezionabile<T> newSelezione) {
		
		this.selezione = newSelezione;
	}
	
	public ArrayList<T> seleziona() {
		
		for(T t : input)
			if(selezione.seleziona(t) == true)
				output.add(t);
		return output;
	}

}
