
public class Resort implements MinistryOfTravel
{
    private String name;
    private int rating;

    public Resort(String name, int rating)
    {
        this.rating = rating;
        this.name = name;
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
