package ejemplos;

import java.util.concurrent.ForkJoinPool;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

/*
src/test/resources/junit-plattform.properties
*/
@Execution(ExecutionMode.SAME_THREAD)
public class _09_Test_Paralelos {

	@Test
	public void test1() {
		System.out.println(Thread.currentThread().getName()+": Test 1");
	}
	
	@Test
	public void test2() {
		System.out.println(Thread.currentThread().getName()+": Test 2");
	}
	
	@Test
	public void test3() {
		System.out.println(Thread.currentThread().getName()+": Test 3");
	}
	
	@Test
	public void test4() {
		System.out.println(Thread.currentThread().getName()+": Test 4");
	}
	
	@Test
	public void test5() {
		System.out.println(Thread.currentThread().getName()+": Test 5");
	}
	
	@Test
	public void test6() {
		System.out.println(Thread.currentThread().getName()+": Test 6");
	}
	
	@Test
	public void test7() {
		System.out.println(Thread.currentThread().getName()+": Test 7");
	}
	
	@Test
	public void test8() {
		System.out.println(Thread.currentThread().getName()+": Test 8");
	}
	
	//FORK JOIN POOL
	public static void main(String[] args) {
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		System.out.println(commonPool.getParallelism());    //		
	}

}

