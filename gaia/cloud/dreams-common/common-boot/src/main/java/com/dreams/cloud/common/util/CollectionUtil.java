package com.dreams.cloud.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionUtil {
	
	public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
		if (elements instanceof Collection) {
			Collection<? extends E> collection = (Collection<? extends E>)elements;
			return new ArrayList<E>(collection);
		} else {
			return newArrayList(elements.iterator());
		}
	}
	
	public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
		ArrayList<E> list = newArrayList();
		while (elements.hasNext()) {
			list.add(elements.next());
		}
		return list;
	}
	
	public static <E> ArrayList<E> newArrayList() {
		return new ArrayList<E>();
	}
}
