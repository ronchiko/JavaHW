package prototyping.shantingyard;

public class ShuntingYardUtils {

    public static Double toDouble(String s){
        Double number = null;

        try {
            number = Double.parseDouble(s);
        }catch (NumberFormatException ignored){
        }

        return number;
    }

}
