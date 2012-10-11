/**
 * Class that supports methods and attributes to work with a Note
 * 
 * @author Tin Huynh
 * @version 2010.10.26
 */
public class Note
{
    private String dateCreated;
    private String text;

    /**
     * create a new Note object
     */
    public Note()
    {
        
    }
    
    /**
     * create a new Note object
     * instantiate for its attributes
     */
    public Note(String newDateCreated, String newText)
    {
        dateCreated = newDateCreated;
        text = newText;
    }
    
    /**
     * return date created of the Note
     * @return a String contains Note's date created
     */
    public String getDateCreated()
    {
        return dateCreated;
    }
    
    /**
     * assign a value for date created
     * @param newDateCreated new value for date created
     */
    public void setDateCreated(String newDateCreated)
    {
        dateCreated = newDateCreated;
    }
    
    /**
     * return text of the Note
     * @return a String contains Note's text
     */
    public String getText()
    {
        return text;
    }
    
    /**
     * assign a value for text
     * @param newText new value for text
     */
    public void setText(String newText)
    {
        text = newText;
    }
    
    /**
     * @return a String contains current state of all attributes
     */
    public String toString()
    {
        return "DateCreated: " + dateCreated + " Text: " + text;
    }
}
