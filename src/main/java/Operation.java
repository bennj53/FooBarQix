import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Operation {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String compute(String param){

        Integer number;
        String result = "";

        try {
            number = Integer.parseInt(param);
            result = IntStream.of(number)
                    .mapToObj(n -> n%3 == 0
                                        ? (n%5 == 0
                                                ? (n%7 == 0 ? ("FooBarQix"):("FooBar"))
                                                : (n%7 == 0 ? ("FooQix"):("Foo")))
                                        : (n%5 == 0
                                                ? (n%7 == 0 ? ("BarQix") : ("Bar"))
                                                : (n%7 == 0 ? ("Qix") : (""))))
                    .collect(Collectors.joining());

            final boolean isDivisible = !result.equals("");

            result += param.chars()
                    .mapToObj(i -> (char) i)
                    .map(i -> i == '0'
                            ? ("*")
                            : (i == '3'
                            ? ("Foo")
                            : (i == '5'
                            ? ("Bar")
                            : (i == '7'
                            ? ("Qix")
                            : (!isDivisible
                            ?(String.valueOf(i))
                            :(""))))))
                    .reduce("", (partialString, element) -> partialString + element);

        }catch (NumberFormatException e){
            logger.error("Invalid parameter " + e.getMessage());
        }

        logger.debug(param + " => " + result);

        return result;
    }
}
