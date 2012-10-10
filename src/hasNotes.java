import java.util.*;
/**
 * Interface that supports functionalities for a Note
 * 
 * @author Tin Huynh
 * @version 2010.10.26
 */
public interface hasNotes
{
    /**
     * return the number of Note objects in myNotes ArrayList 
     * @return number of Note objects 
     */
    int noOfNote();
    
     /**
     * return the number of Reference objects in myReferences ArrayList 
     * @param myNotes list of Note objects
     * @param aNote the Note object to be added to the ArrayList
     */
    void addNote(ArrayList<Note> myNotes, Note aNote);
    
    /**
     * return a Note at the supplied index
     * @param noteNumber index of the Note
     * @return the found Note object
     */
    Note showNote(int noteNumber);
    
     /**
     * return collection of all Note objects
     * @return collection of Note objects
     */
    ArrayList<Note> listNotes();
    
    /**
     * remove a Note from the collection at the supplied index
     * @param noteNumber index of the remove Note
     */
    void removeNote(int noteNumber);
    
    /**
     * return collection of Notes that contain the searchString in their text
     * @param searchString the keyword 
     * @return Collection of match Note objects
     */
    ArrayList<Note> search(String searchString);
}