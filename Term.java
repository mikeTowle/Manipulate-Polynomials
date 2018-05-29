package Clm;
/**
 *
 * @author mike
 */
public class Term{
    
    private double coef;
    private int power;
    
    public Term(double c, int p){
        coef =c;
        power =p;
    }
    
    @Override
    public String toString(){
        if(power>1){
            return coef +"x^"+ power;
        }
        if(power ==1){
            return coef +"x";
        }
        else
            return ""+coef;
    }
    
   
}
