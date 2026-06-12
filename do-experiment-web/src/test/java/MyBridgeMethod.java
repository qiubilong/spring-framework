/**
 * @author chenxuegui
 * @since 2024/10/21
 */
public class MyBridgeMethod extends BridgeMethodBase<String> {

	@Override
	public void doSomeThing(String value) {

	}


	/*  编译器会自动给泛型方法Method生成桥接方法 BridgeMethod
		public synthetic bridge doSomeThing(Object value) {
			this.doSomeThing((String)value);
		}

	 */
}
