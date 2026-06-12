import java.util.HashMap;

/**
 * @author chenxuegui
 * @since 2024/11/28
 */
public class TestMe {


	public static void main(String[] args) {

		////////////////////////// 字符常量池中存在字符串 ///////////////////////////
		/* 堆中实际创建两个对象。 1、字面量"wowo"对象 。2、对象s1

		*  new String("wowo")
		*  等价于
		*  String s0 = "wowo"; //创建字符串常量池的对象
		*  new String(s0); //创建堆对象

		*  */
		 String s1 = new String("wowo");/* "wowo" 字面量提前池化 */


		 String s2 = s1.intern();/* 如果常量池中存在就直接返回，否则池中缓存后再返回 */
		 System.out.println(s1 == s2);/* 由于池中已经存在字面量"wowo"对象，返回的是池中旧对象，所以不相等 */
		 //System.out.println(s1.equals(s2));//equals一个个字符比较



		String pool1 = "aka"; /* 字面量提前池化 */
		String pool2= pool1.intern(); /*  池中存在"aka"对象，返回同一个对象  */
		System.out.println(pool1 == pool2);

		System.out.println("////////////////////////// 字符常量池中不存在 /////////////////////////////////\n");

		String ss1 = "hello";
		String ss2 = "good";
		String ss3 = ss1 + ss2; /* ss3不是字面量，没有提前在字符串常量池中生成对象 */
		String intern = ss3.intern();/* 池中不存在，所以缓存后返回自己 */
		System.out.println(ss3 == intern);

		System.out.println(Integer.toBinaryString(536870911));
		System.out.println( String.format("%32s", Integer.toBinaryString(536870911)));
		System.out.println( String.format("%32s", Integer.toBinaryString(~536870911)).replace(' ', '0'));


		HashMap<String,String> hashMap = new HashMap<>();
		hashMap.put("key1","val1");
	}
}
