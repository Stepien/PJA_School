package zad2.calculator;

import java.util.HashMap;
import java.util.Map;

interface ICommand<Result> {
    Result execute(Object ...args);
}

public class Calculator{
    abstract class Operation implements ICommand<Double>{
        protected Double[] extract(int count, Object ...args){
            Double[] result = new Double[count];
            int counter = 0;
            for(Object arg: args){
                result[counter++] = (Double)arg;
                if(counter == count)
                    break;
            }
            return result;
        }
    }
    class Adder extends Operation {
        public final Double execute(Object ...args) {
            Double[] ts = extract(2, args);
            return ts[0]+ts[1];
        }
    }

    class Substracter extends Operation {
        public Double execute(Object ...args) {
            Double[] ts =  extract(2, args);
            return ts[0] - ts[1];
        }
    }

    class Multipliter extends Operation {
        public Double execute(Object ...args) {
            Double[] ts =  extract(2, args);
            return ts[0] * ts[1];
        }
    }

    class Divider extends Operation {
        public Double execute(Object ...args) {
            Double[] ts =  extract(2, args);
            return ts[0] / ts[1];
        }
    }

    private Map<String, Operation> operations = new HashMap<String, Operation>();

    public Calculator() {
        operations.put("add", new Adder());
        operations.put("sub", new Substracter());
        operations.put("mul", new Multipliter());
        operations.put("div", new Divider());
    }

    public void perform(String action) {
        String[] args = action.split("\\s+");
        System.out.println(operations.get(args[0]).execute(Double.parseDouble(args[1]), Double.parseDouble(args[2])));
    }
}