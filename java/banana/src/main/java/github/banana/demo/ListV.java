package github.banana.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ListV {

	/**
	 * List有ArrayList和LinkedList两种实现
	 * 
	 * 遍历List使用Iterator或者foreach循环
	 * 
	 * List和Array可以相互转换
	 * 
	 * List是一种有序列表，通过索引访问元素。
	 * 
	 * void add(E e) 在末尾添加一个元素
	 * 
	 * void add(int index, E e) 在指定索引添加一个元素
	 * 
	 * int remove(int index) 删除指定索引的元素
	 * 
	 * int remove(Object e) 删除某个元素
	 * 
	 * E get(int index) 获取指定索引的元素
	 * 
	 * int size() 获取链表大小（包含元素的个数）
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// List
		System.out.println("List...");
		List<String> strList = new ArrayList<String>();
		strList.add("Apple");
		strList.add("Android");
		strList.add("ZTE");
		for (String s : strList) {
			System.out.println(s);
		}

		// 把List转换为数组
		System.out.println("把List转换为数组...");
		String[] list = strList.toArray(new String[strList.size()]);
		for (String s : list) {
			System.out.println(s);
		}

		// 链表
		System.out.println("链表...");
		List<String> link = new LinkedList<String>();
		link.add("Apple");
		link.add("MeiZu");
		link.add("MI");
		link.add("360");
		for (String s : link) {
			System.out.println(s);
		}

		// 做一个map
		// Map是一种键值映射表，可以通过Key快速查找Value
		List<Person> p = Arrays.asList(new Person("张三", 19), new Person("李四", 15), new Person("王五", 30));
		// HashMap无序打印, 顺序打印要用TreeMap
		// 而且顺序只是对键起作用, 不会操作值
		Map<String, Person> map = new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return -o1.compareTo(o2);
			}

		});
		for (Person t : p) {
			map.put(t.getName(), t);
		}

		System.out.println(map.get("张三"));
		System.out.println(map.get("田七"));

		for (String m : map.keySet()) {
			System.out.println(m + " -> " + map.get(m));
		}

		for (Map.Entry<String, Person> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}

		System.out.println("Set...");
		List<String> list1 = Arrays.asList("pear", "apple", "banana", "orange", "apple", "banana");
		List<String> list2 = removeDuplicate(list1);
		System.out.println(list2);
		
		List<String> list3 = Arrays.asList("abc", "xyz", "abc", "www", "edu", "www", "abc");
		Set<String> set = new LinkedHashSet<>(list3);
		System.out.println(new ArrayList<String>(set));
	}

	/**
	 * 去除List中重复的元素
	 * 
	 * @param list
	 * @return
	 */
	private static List<String> removeDuplicate(List<String> list) {
		// 使用Set的方式存储
		// 利用Set可以去除重复元素
		// HashSet是无序的
		// TreeSet是有序的
		// 实现了SortedSet接口的是有序Set
		// 自定义排序算法
		Set<String> set = new TreeSet<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return -o1.compareTo(o2);
			}

		});
		set.addAll(list);
		// 把set转化为list返回
		return new ArrayList<String>(set);
	}

}
