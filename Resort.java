import java.io.Serializable;
public class Resort implements MinistryOfTravel, Serializable
{
    private String name;
    private int rating;

    public Resort(String name, int rating)
    {
        this.rating = rating;
        this.name = name;
    }

    public void Display()
    {
        System.out.println("Name: " + name + "\tRating: " + rating);
    }

    public String getDetails() 
    {
        return "Name: " + name + "\tRating: " + rating + "\n";
    }


    public int getRating()
    {
        return rating;
    }

    public String getName()
    {
        return name;
    }
}
