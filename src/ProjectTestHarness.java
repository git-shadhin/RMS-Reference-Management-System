import java.util.*;
import java.io.*;

/**
 * Class used to test some functions of Project
 * Do not call any method of this class anymore bacause they are not validated before execution
 * May lead to unexcepted behaviours if used as this time
 * You can use them if create different data files and change the file path to these new files
 * @author (Tin Huynh) 
 * @version (2010.10.27)
 */
public class ProjectTestHarness
{
    // instance variables - replace the example below with your own
    private Project p;
    private static final String PROJECT_FILE_NAME = "data/Project.txt";
    private static final String PROJECT_FILE_TEMP = "data/ProjectTemp.txt";
    private static final String REFERENCE_FILE_NAME = "data/Reference.txt";
    
    /**
     * Constructor for objects of class ProjectTestHarness
     */
    public ProjectTestHarness() throws IOException
    {

    }

    public void showProjectDetail(Project aProject) throws IOException
    {
        System.out.println(aProject.getName());
        System.out.println(aProject.getDescription());
        System.out.println(aProject.myReferences.size());
        for(Reference r : aProject.myReferences) {
            System.out.println(r.getClass().getName() + " " + aProject.myReferences.toString());
        }
        
        for(Note n : aProject.myNotes) {
            System.out.println(" " + n.getDateCreated() + " " + n.getText());
        }
    }
    
    public void testEditNote() throws IOException
    {
        BufferedReader br = null;    
        br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
        Project projIns = new Project();
        projIns.editNoteToFile("u1", "2/3/1999", "try to find out", "4/3/1999", "found", br);
    }
    
    public void testDeleteNote() throws IOException
    {
        BufferedReader br = null;    
        br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
        Project projIns = new Project();
        projIns.deleteNoteFromFile("Software Team Project", "9/2/2010", "Focus more on analyzing the current context of use.Not the future system.", br);
        
    }
    
    public void testSearchNotes(String keyword) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
        Project projIns = new Project();
        ArrayList<String> noteData = new ArrayList<String>();
        noteData = projIns.searchProjectNotes(keyword ,br);
        for(int i=0; i< noteData.size(); i= i+3) {
            System.out.println("Date Created: " + noteData.get(i) + " Content: " + noteData.get(i+1) + " Belong to Project " + noteData.get(i+2)); 
        }
    }
    
    public void testSearchProject(String keyword) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
        Project projIns = new Project();
        ArrayList<Project> searchProjects = projIns.searchProjects(keyword ,br);
        for(int i=0; i< searchProjects.size(); i++) {
            System.out.println("Name: " + searchProjects.get(i).getName() + " Description: " + searchProjects.get(i).getDescription()); 
        }
    }
}
