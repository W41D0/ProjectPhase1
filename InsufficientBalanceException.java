//Custom checked exception: thrown when a guest cannot Afford The Room Price$
public class InsufficientBalanceException extends Exception
{
 private double required;
 private double available;

 public InsufficientBalanceException(double required, double available)
 {
     super("Insufficient balance. Required: " + required + " Riyals, but only " + available + " Riyals available.");
     this.required = required;
     this.available = available;
 }
//Getters-----------
 public double getRequired()
 {
     return required;
 }

 public double getAvailable()
 {
     return available;
 }
}