package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

import stack.CStack;

public class Stacktest extends TestCase {

	private CStack<Integer> createRandomPile(int n) {
		CStack<Integer> newstack = new CStack<Integer>();
		Random generator = new Random(System.currentTimeMillis());

		for (int i = 0; i < n; i++) {
			newstack.add_value_top(generator.nextInt(30));
		}
		newstack.shuffle();
		return newstack;
	}

	@Test
	public void testGet_size() {
		for (int i = 0; i < 20; i++)
			assertTrue(createRandomPile(i).get_size() == i);
	}

	@Test
	public void testAdd_value_top() {
		CStack<Integer> stack = createRandomPile(20);
		for (int i = 0; i < 10; i++){
			stack.add_value_top(i);
			assertTrue(i == stack.get_first());
		};
	}

	@Test
	public void testAdd_value_bottom() {
		CStack<Integer> stack = createRandomPile(20);
		int size, bottom;

		for (int i = 1; i < 10; i++) {
			bottom = (new Random(System.currentTimeMillis())).nextInt(50);
			size = stack.get_size();

			stack.add_value_bottom(bottom);

			assertTrue(stack.get_size() == size + 1);
			assertTrue(stack.get_n_last(1) == bottom);
		}
	}

	@Test
	public void testShuffle() {
		for (int i = 0; i < 10; i++) {
			CStack<Integer> stack = createRandomPile(20);

			int first1 = stack.get_first();
			stack.shuffle();
			int first2 = stack.get_first();
			stack.shuffle();
			int first3 = stack.get_first();
			stack.shuffle();
			int first4 = stack.get_first();
			stack.shuffle();

			assertFalse(first1 == first2 && first1 == first3 && first1 == first4);
		}
	}

	@Test
	public void testGet_first() {
		CStack<Integer> stack = createRandomPile(20);
		for (int i = 0; i < 10; i++){
			stack.add_value_top(i);
			assertTrue(i == stack.get_first());
		}
	}

	@Test
	public void testGet_nth() {
		CStack<Integer> stack = createRandomPile(20);
		List<Integer> items = stack.get_items();
		for (int i = 1; i < 20; i++)
			assertTrue(
					String.format("%d. it. get=%d, items=%d\n%s", i,
							stack.get_nth(i), items.get(i - 1),
							items.toString()),
					stack.get_nth(i) == items.get(i - 1));
	}

	@Test
	public void testGet_n_last() {
		CStack<Integer> stack = createRandomPile(20);
		List<Integer> items = stack.get_items();
		for (int i = 1; i < 10; i++) {
			int size = stack.get_size();
			int first = stack.get_first();
			int second = stack.get_nth(2);
			int last = stack.get_n_last(1);

			stack.turn_over();

			assertTrue(stack.get_n_last(1) == first);
			assertTrue(stack.get_first() == second);
			assertTrue(stack.get_size() == size);
		}

	}

	@Test
	public void testTurn_over() {
		CStack<Integer> stack = createRandomPile(20);
		List<Integer> items = stack.get_items();
		for (int i = 1; i < 20; i++)
			assertTrue(
					String.format("%d. it. get=%d, items=%d\n%s", i,
							stack.get_n_last(i), items.get(20 - i),
							items.toString()),
					stack.get_n_last(i) == items.get(20 - i));
	}

	@Test
	public void testRemoveElement() {
		CStack<Integer> stack = createRandomPile(50);

		// remove in the middle
		for (int i = 0; i < 10; i++) {
			// make sure it's in the middle somewhere
			int removepos = (new Random(System.currentTimeMillis()))
					.nextInt(stack.get_size() - 5) + 1;
			int item = stack.get_nth(removepos);
			// int before = removepos-1;
			int after = stack.get_nth(removepos + 1);
			int size = stack.get_size();

			assertTrue(item == stack.removeElement(removepos));

			assertTrue(size - 1 == stack.get_size());
			assertTrue(after == stack.get_nth(removepos));
		}

		// TODO remove at th head
		stack = createRandomPile(50);
		for (int i = 0; i < 5; i++) {
			int first = stack.get_first();
			int second = stack.get_nth(2);
			int size = stack.get_size();

			assertTrue(first == stack.removeElement(1));

			assertTrue(stack.get_first() == second);
			assertTrue(stack.get_size() + 1 == size);
		}

		// TODO remove at the tail
		stack = createRandomPile(50);
		for (int i = 0; i < 5; i++) {
			int last = stack.get_n_last(1);
			int secondLast = stack.get_n_last(2);
			int size = stack.get_size();

			assertTrue(last == stack.removeElement(size));

			assertTrue(stack.get_n_last(1) == secondLast);
			assertTrue(stack.get_size() + 1 == size);
		}
	}

}
