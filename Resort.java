package ProjectPhase1;

public class Resort implements MinistryOfTravel
{
    private int rating;
    private boolean localsAllowed;

    public Resort(int rating, boolean allowLocals)
    {
        this.rating = rating;
        this.localsAllowed = allowLocals;
    }

    public int getRating()
    {
        return rating;
    }

    public boolean allowLocals()
    {
        return localsAllowed;
    }
}
