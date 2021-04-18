package com.ram.leadboard.utils;

import java.util.Objects;

public class PairData<U, V> {

	public final U first;
	public final V second;

	public PairData(U first, V second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		System.out.println("equal");
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		PairData<?, ?> pair = (PairData<?, ?>) o;
		if (!first.equals(pair.first)) {
			return false;
		}
		System.out.println(first+""+pair.first);
		System.out.println(second+""+pair.second);
		//return second.equals(pair.second);
		return Objects.equals(first, pair.first)
	            && Objects.equals(second, pair.second);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 31 * first.hashCode() + second.hashCode();
	}
	
	@Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
 
//    // Factory method for creating a typed Pair immutable instance
//    public static <U, V> PairData<U, V> of(U a, V b)
//    {
//        // calls private constructor
//        return new PairData<U, V>(a, b);
//    }

	public static PairData<String, String> of(String a, String b) {
		// TODO Auto-generated method stub
		
		return  new PairData<String, String>(a, b);
	}

}
