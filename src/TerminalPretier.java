
/**
 * Classe responsável por embelezar nossos outputs no terminal 
*/
public  class TerminalPretier {
    

    public  String boldText(String text){    
        return "\u001b[1m "+text;
    }

    public String whiteColor(String text){
        return "\u001b[37m " +text;
    }

    public String resetFormat(){
        return "\u001b[m ";
    }

    public  String blueBackground(String text){
        return "\u001b[44m \u001b[33m "+ text;
    }

    public String ratingStars(String rating){
        var floatRating = Double.parseDouble(rating);
        var average = Math.round(floatRating);
        String ratingStars = " ";
        for (int i = 0; i < average; i++) {
            // ratingStars+="\u2B50";
            ratingStars+="*";
        }
        return ratingStars;
    }
}
