import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author chenxuegui
 * @since 2024/8/29
 */
public class TestGetMethodParameter {

	public void like(Long aa,Long bbb){

	}

	public static void main(String[] args) {
		TestGetMethodParameter test = new TestGetMethodParameter();
		for (Method method : TestGetMethodParameter.class.getMethods()) {
			for (Parameter parameter : method.getParameters()) {
				/* 获得方法的参数名   */
				System.out.println(parameter.getName());
			}
		}
	}
}
