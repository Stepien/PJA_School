import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class Calc {
	public String doCalc(String cm) {
		BigDecimal a;
		BigDecimal b;
		String cmd[];
		Map<String, Method> znaki = new HashMap<String, Method>();

		try {
			znaki.put("-",
					BigDecimal.class.getMethod("subtract", BigDecimal.class));
			znaki.put("+", BigDecimal.class.getMethod("add", BigDecimal.class));
			znaki.put("*",
					BigDecimal.class.getMethod("multiply", BigDecimal.class));
			znaki.put("/",
					BigDecimal.class.getMethod("divide", BigDecimal.class));

			cmd = cm.trim().split(" ");
			a = new BigDecimal(cmd[0]);
			b = new BigDecimal(cmd[2]);

			return String.valueOf((BigDecimal) znaki.get(cmd[1]).invoke(a, b));

		} catch (Exception e) {
			return "Nie mozna wykonac";
		}

	}
}