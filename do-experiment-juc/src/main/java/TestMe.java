import java.util.HashMap;

/**
 * @author chenxuegui
 * @since 2024/11/28
 */
public class TestMe {
	static final int SHARED_SHIFT   = 16;
	static final int SHARED_UNIT    = (1 << SHARED_SHIFT);  /* 2^16 */

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(536870911));
		System.out.println( String.format("%32s", Integer.toBinaryString(536870911)));
		System.out.println( String.format("%32s", Integer.toBinaryString(~536870911)).replace(' ', '0'));


		HashMap<String,String> hashMap = new HashMap<>();
		hashMap.put("key1","val1");
	}
}
