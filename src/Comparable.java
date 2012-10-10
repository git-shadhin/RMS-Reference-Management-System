import java.util.*;
import java.io.*;

/**
 * Support the sort of objects in collection
 * 
 * @author Tin Huynh 
 * @version 2010.10.26
 */
public interface Comparable
{
     /**
     * sort the project objects based on their names
     * @param projectList collection of Project object
     */
    void sortProject(ArrayList<Project> list);
    
    /**
     * sort the reference objects based on their authors
     * @param refList collection of Refererence object
     */
    void sortReference(ArrayList<Reference> list);
    
     /**
     * sort the Bibliographic Entries alphabetically
     * @param entriesList list of Bibliographic Entries
     */
    void sortBiblioGraphicEntries(String[] entriesList);
}
