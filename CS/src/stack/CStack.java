package stack;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class CStack<T> {
	private LinkedList<T> items;

	
	public CStack(){
		this.items = new LinkedList<T>();
	}
	
	/**
	 * returns the number of elements in the pile
	 * @return
	 */
	public int get_size(){
		assert this.items != null;
		return this.items.size();
	}
	
	
	/**
	 * adds the value at the beginning of the pile
	 * @param value
	 */
	public void add_value_top(T value){
		assert this.items != null;
		assert value != null;
		this.items.addFirst(value);
		
	}
	
	public void add_value_bottom(T value){
		assert this.items != null;
		assert value != null;
		
		this.items.addLast(value);
	}
	
	
	/**
	 * randomize the complete pile
	 */
	public void shuffle(){
		assert this.items != null;
		long seed = System.nanoTime();
		Collections.shuffle(this.items, new Random(seed));
		
	}
	
	/**
	 * returns the very first element of the pile
	 * @return
	 */
	public T get_first(){
		assert this.items != null;
		if (this.items.size() == 0)
				return null;
		return this.items.get(0);
		
	}
	
	public List<T> get_items(){
		return this.items;
	}
	
	/**
	 * returns the n-th element, starting with 1, n = (index +1)
	 * @param n
	 * @return
	 */
	public T get_nth(int n){
		assert this.items != null;
		if (n<1)
			return null;
		if (n>this.items.size())
			return null;
		return this.items.get(n-1);
	}
	
	/**
	 * returns the bottom elements of the pile
	 * @param n = 1: last item, 2: second last ...
	 * @return
	 */
	public T get_n_last(int n){
		assert n>0;
		assert this.items != null;
		return this.items.get(this.items.size()-n);
		
	}
	
	/**
	 * moves the top element to the bottom and returns the new top element (previously second top element)
	 * @return
	 */
	public T turn_over(){
		assert this.items != null;
//		T currentTop = this.items.getFirst();
		T currentTop = this.items.remove(0);
		this.items.addLast(currentTop);
		return this.items.getFirst();
	}
	
	/**
	 * removes the n-th element
	 * @param index staring with 1, i.e. array[index-1].remove
	 * @return
	 */
	public T removeElement(int index){
		assert this.items != null;
		return this.items.remove(index-1);
	}

	public String printPile() {
		StringBuffer sb = new StringBuffer();
		for (T item: this.items){
			sb.append(item.toString() + "-"); 
		}
		return sb.toString();
	}
	
	
}
