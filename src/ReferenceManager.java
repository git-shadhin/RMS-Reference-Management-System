import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.io.*;
import javax.swing.table.*;

/**
 * A class to support all operations of the program
 * Hold the collections of projects and references and methods to format these objects before passing to GUI
 * Support the logic of the program
 * 
 * @author Tin Huynh
 * @version 2010.10.26
 */
public class ReferenceManager implements Comparable
{
    // instance variables - replace the example below with your own
    private  ArrayList<Project> myProjects;
    private  ArrayList<Reference> myReferences;
    private static final String PROJECT_FILE_NAME = "data/Project.txt";
    private static final String REFERENCE_FILE_NAME = "data/Reference.txt";
    private static final String BIBLIO_FILE_NAME = "data/Biblio.txt";


    /**
     * Instantiate the collections of projects and references
     * load data to these collections
     */
    public ReferenceManager() throws IOException
    {
        myProjects = new ArrayList<Project>();
        myReferences = new ArrayList<Reference>();
        load();
    }
    
    /**
     * Provide a way to access the collection of projects
     * @return collection of projects
     */
    public ArrayList<Project> getMyProjects()
    {
        return myProjects;
    }
    
    /**
     * Provide a way to access the collection of references
     * @return collection of references
     */
    public ArrayList<Reference> getMyReferences()
    {
        return myReferences;
    }
    
    /**
     * Connect to the text file that stores project data
     * Call the constructor of class Project that calls the method to save project data to file 
     * @param newName name of the new project
     * @param newBriefDesc brief description of the new project
     * @param refList a list of reference number to be added under this project
     * @param noteText text of the new Note 
     * @param noteDate date created of the new Note
     * @exception IOException if an error occurs when input or output from file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public void addNewProject(String newName, String newBriefDesc, ArrayList<String> refList, String noteText, String noteDate) throws IOException
    {
        FileWriter fw = null;
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            fw = new FileWriter(PROJECT_FILE_NAME, true); //append the file
            bw = new BufferedWriter(fw);
            br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
        }
        catch(FileNotFoundException e) {}
        Project proj = new Project(newName, newBriefDesc, bw, br, refList, noteText, noteDate);
    }
    
    /**
     * Connect to the text file that stores reference data
     * Call appropriate constructor based on the type of the new reference to save data to text file
     * @param information collection of information about the new reference
     * @param noteDate date created of the new Note
     * @param noteText text of the new Note 
     * @exception IOException if an error occurs when input or output from file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public void addNewReference(HashMap information, String noteDate, String noteText) throws IOException
    {
        FileWriter fw = null;
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            fw = new FileWriter(REFERENCE_FILE_NAME, true); //append the file
            bw = new BufferedWriter(fw);
            br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
        }
        catch(FileNotFoundException e) {}
        if(information.get("Type").toString().equals("Book")) {
            Book aBook = new Book(information, noteText, noteDate, bw, br);
        }
        if(information.get("Type").toString().equals("Thesis")) {
            Thesis aThesis = new Thesis(information, noteText, noteDate, bw, br);
        }
        if(information.get("Type").toString().equals("Journal")) {
            Journal aJournal = new Journal(information, noteText, noteDate, bw, br);
        }
        if(information.get("Type").toString().equals("OnlineJournal")) {
            OnlineJournal anOJournal = new OnlineJournal(information, noteText, noteDate, bw, br);
        }
        if(information.get("Type").toString().equals("ConferencePaper")) {
            ConferencePaper aConferencePaper = new ConferencePaper(information, noteText, noteDate, bw, br);
        }
        if(information.get("Type").toString().equals("WebPage")) {
            WebPage aWebPage = new WebPage(information, noteText, noteDate, bw, br);
        }
    }
    
     /**
     * Connect to the text file that stores project data
     * Call method to edit a Note information to text file of a Project object
     * @param projName name of the project which has the note to be edited
     * @param currentDate current Note's date created
     * @param currentText current Note's text
     * @param newDate new Note's date created
     * @param newText new Note's date created
     * @exception IOException if an error occurs when input or output from file
     */
    public void editProjectNote(String projName, String currentDate, String currentText, String newDate, String newText) throws IOException
    {
        BufferedReader br = null;    
        br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
        Project projIns = new Project();
        projIns.editNoteToFile(projName, currentDate, currentText, newDate, newText, br);
    }
    
    /**
     * Connect to the text file that stores reference data
     * Call method of Reference class to edit Note information to text file
     * @param type type of the reference that has note being edited
     * @param author author of the reference that has note being edited
     * @param yearCreated year created of the reference that has note being edited
     * @param title title of the reference that has note being edited
     * @param currentDate current Note's date created
     * @param currentText current Note's date created
     * @param newDate new Note's date created
     * @param newText new Note's date created
     * @exception IOException if an error occurs when input or output from file
     */
    public void editReferenceNote(String type, String author, String yearCreated, String title, String currentDate, String currentText, String newDate, String newText) throws IOException
    {
        BufferedReader br = null;    
        br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
        Reference.editNoteToFile(type, author, yearCreated, title, currentDate, currentText, newDate, newText, br);
    }
    
    /**
     * Connect to the text file that stores reference data
     * Call method of Reference class to edit Reference information to text file
     * @param type type of the reference being edited
     * @param currentAuthor current author of the reference being edited
     * @param currentYear current year created of the reference being edited
     * @param currentTitle current title of the reference being edited
     * @param information collection of new information 
     * @exception IOException if an error occurs when input or output from file
     * @exception FileNotFoundException if the text file cannot be found
     */
    public void editReference(String type, String currentAuthor, String currentYear, String currentTitle, HashMap information) throws IOException
    {
        BufferedReader br = null;
        boolean referenceExist = false;
        try {
            br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            //Reference refIns = new Reference();
            referenceExist = Reference.checkExistReference(type, currentAuthor, currentYear, currentTitle, br);
        }
        catch(FileNotFoundException e) {}
        if(referenceExist) {
            br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            if(information.get("Type").toString().equals("Book")) {
                Book aBook = new Book();
                aBook.editToFile(currentAuthor, currentYear, currentTitle, information, br);
            }
            if(information.get("Type").toString().equals("Thesis")) {
                Thesis aThesis = new Thesis();
                aThesis.editToFile(currentAuthor, currentYear, currentTitle, information, br);
            }
            if(information.get("Type").toString().equals("Journal")) {
                Journal aJournal = new Journal();
                aJournal.editToFile(currentAuthor, currentYear, currentTitle, information, br);
            }
            if(information.get("Type").toString().equals("OnlineJournal")) {
                OnlineJournal anOJournal = new OnlineJournal();
                anOJournal.editToFile(currentAuthor, currentYear, currentTitle, information, br);
            }
            if(information.get("Type").toString().equals("ConferencePaper")) {
                ConferencePaper aConferencePaper = new ConferencePaper();
                aConferencePaper.editToFile(currentAuthor, currentYear, currentTitle, information, br);
            }
            if(information.get("Type").toString().equals("WebPage")) {
                WebPage aWebPage = new WebPage();
                aWebPage.editToFile(currentAuthor, currentYear, currentTitle, information, br);
            }
        }
    }
    
    /**
     * Connect to the text file that stores reference data
     * Call method of Reference class to delete Note information from text file
     * @param type type of the reference being edited
     * @param currentAuthor current author of the reference being edited
     * @param currentYear current year created of the reference being edited
     * @param currentTitle current title of the reference being edited
     * @exception IOException if an error occurs when input or output from file
     * @exception FileNotFoundException if the text file cannot be found
     */
    public void removeReference(String type, String currentAuthor, String currentYear, String currentTitle) throws IOException
    {
        PrintWriter pw = null;
        BufferedReader br = null;
        boolean referenceExist = false;
        try {
            br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            //Reference refIns = new Reference();
            referenceExist = Reference.checkExistReference(type, currentAuthor, currentYear, currentTitle, br);
        }
        catch(FileNotFoundException e) {}
        if(referenceExist) {
            br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            Reference.deleteFromFile(currentAuthor, currentYear, currentTitle, br);
        }
    }
    
    /**
     * sort the reference objects based on their authors
     * @param refList collection of Refererence object
     */
    public void sortReference(ArrayList<Reference> refList)
    {
        Collections.sort(refList, new Comparator(){
            public int compare(Object o1, Object o2) {
                Reference r1 = (Reference) o1;
                Reference r2 = (Reference) o2;
               return r1.getAuthor().compareToIgnoreCase(r2.getAuthor());
            }
        });
    }
    
    /**
     * sort the project objects based on their names
     * @param projectList collection of Project object
     */
    public void sortProject(ArrayList<Project> projectList)
    {
        Collections.sort(projectList, new Comparator(){
            public int compare(Object o1, Object o2) {
                Project p1 = (Project) o1;
                Project p2 = (Project) o2;
               return p1.getName().compareToIgnoreCase(p2.getName());
            }
        });
    }
    
    /**
     * sort the Bibliographic Entries alphabetically
     * @param entriesList list of Bibliographic Entries
     */
    public void sortBiblioGraphicEntries(String[] entriesList)
    {
        Arrays.sort(entriesList);
    }
    
    /**
     * @param aProject add a project to the collection
     */
    public void loadProject(Project aProject)
    {
        myProjects.add(aProject);
    }
    
    /**
     * @return number of project in the collection
     */
    public int noOfProject()
    {
        return myProjects.size();
    }
    
    /**
     * @return number of reference in the collection
     */
    public int noOfReference()
    {
        return myReferences.size();
    }
    
    /**
     * load all projects and references to collections
     * @exception IOException if an error occurs when input or output from text file
     */
    public void load() throws IOException
    {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            Project projIns = new Project();
            myProjects = projIns.readAllProject(br);
            sortProject(myProjects);
            br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            myReferences = Reference.readAllReferences(br);
            sortReference(myReferences);
        }
        catch(FileNotFoundException e) {}
    }
    
    /**
     * connect to project text file and rewrite list of reference numbers
     * @param projectName name of the project
     * @param refList list of new reference numbers 
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public void updateReferenceList(String projectName, ArrayList<String> refList) throws IOException
    { 
        try {
            BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            Project proj = new Project();
            proj.rewriteRefList(projectName, br, refList);
        }
        catch(FileNotFoundException e) {}
    }
    
    /**
     * connect to project text file and read to return the project
     * with the requested project name
     * @param projName name of the project to be searched
     * @return found Project object
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public Project findAProject(String projName) throws IOException
    {
        Project aProject = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            aProject = new Project(projName, br);
        }
        catch(FileNotFoundException e) {}
        return aProject;
    }
    
    /**
     * Connect to project text file, call project method to delete project information
     * @param projName name of the project to be deleted
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public void deleteProject(String projName) throws IOException
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            Project aProject = new Project();
            aProject.deleteProjectFromFile(projName, br);
        }
        catch(FileNotFoundException e) {}
    }
    
    /**
     * Connect to project text file, call project method to read and delete update information
     * @param currentProjName current name of the project to be updated
     * @param newProjName new name of the project to be updated
     * @param newDescription new description of the project to be updated
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public void updateProject(String currentProjName, String newProjName, String newDescription) throws IOException
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            Project aProject = new Project();
            aProject.editProjectToFile(currentProjName, newProjName, newDescription, br);
        }
        catch(FileNotFoundException e) {}
    }
    
    /**
     * Connect to project text file, call method to save new Note information
     * @param projName name of the project that has note to be added
     * @param noteText text of the new Note
     * @param dateCreated date created of the new Note
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public void saveProjectNote(String projName, String noteText, String dateCreated) throws IOException
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            Project proj = new Project();
            proj.saveNoteToFile(projName, noteText, dateCreated, br);
        }
        catch(FileNotFoundException e) {}
    }
    
    /**
     * Connect to reference text file, call method to save new Note information
     * @param type type of the reference that has note to be added
     * @param theAuthor author of the reference that has note to be added
     * @param theYear yearCreated of the reference that has note to be added
     * @param theTitle title of the reference that has note to be added
     * @param noteText text of the new Note
     * @param dateCreated date created of the new Note
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public void saveReferenceNote(String type, String theAuthor, String theYear, String theTitle, String noteText, String dateCreated) throws IOException
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            //Reference ref = new Reference();
            Reference.saveNoteToFile(type, theAuthor, theYear, theTitle, noteText, dateCreated, br);
        }
        catch(FileNotFoundException e) {}
    }
    
    /**
     * Connect to project text file, call method to delete Note information
     * @param projName name of the project that has note to be deleted
     * @param noteText text of the Note to be deleted
     * @param noteDate date created of the Note to be deleted
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public void deleteProjectNote(String projName, String noteText, String noteDate) throws IOException
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            Project proj = new Project();
            proj.deleteNoteFromFile(projName, noteDate, noteText, br);
        }
        catch(FileNotFoundException e) {}
    }
    
    /**
     * Connect to reference text file, call method to delete Note information
     * @param type type of the reference that has note to be deleted
     * @param theAuthor author of the reference that has note to be deleted
     * @param theYear yearCreated of the reference that has note to be deleted
     * @param theTitle title of the reference that has note to be deleted
     * @param noteText text of the Note to be deleted
     * @param dateCreated date created of the Note to be deleted
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public void deleteReferenceNote(String type, String theAuthor, String theYear, String theTitle, String noteText, String dateCreated) throws IOException
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            //Reference ref = new Reference();
            Reference.deleteNoteFromFile(type, theAuthor, theYear, theTitle, dateCreated, noteText, br);
        }
        catch(FileNotFoundException e) {}
    }
    
    /**
     * call method to find the references that have the keyword in their title
     * @param keyword word to be found
     * @return List of found references
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public ArrayList<Project> searchProjects(String keyword) throws IOException
    {
        ArrayList<Project> projectList = new ArrayList<Project>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            Project proj = new Project();
            projectList = proj.searchProjects(keyword, br);
        }
        catch(FileNotFoundException e) {}
        return projectList;
    }
    
    /**
     * call method to find the notes that have the keyword in their text
     * @param keyword word to be found
     * @return List of data for found Notes
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public ArrayList<String> searchProjectNotes(String keyword) throws IOException
    {
        ArrayList<String> projectNotes = new ArrayList<String>();
        try {    
            BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            Project proj = new Project();
            projectNotes = proj.searchProjectNotes(keyword, br);
        }
        catch(FileNotFoundException e) {}
        return projectNotes;
    }
    
    /**
     * call method to find the notes that have the keyword in their text
     * @param keyword word to be found
     * @return List of data for found Notes
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public ArrayList<String> searchReferenceNotes(String keyword) throws IOException
    {
        ArrayList<String> referenceNotes = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            //Reference ref = new Reference();
            referenceNotes = Reference.searchReferenceNotes(keyword, br);
        }
        catch(FileNotFoundException e) {}
        return referenceNotes;
    }
    
    /**
     * call method to find the references that have the keyword in their title
     * @param keyword word to be found
     * @return List of found references
     * @exception IOException if an error occurs when input or output from text file
     * @exception FileNotFoundException if the textfile cannot be found
     */
    public ArrayList<Reference> searchReferences(String keyword) throws IOException
    {
        ArrayList<Reference> referenceList = new ArrayList<Reference>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            //Reference ref = new Reference();
            referenceList = Reference.searchReferences(keyword, br);
        }
        catch(FileNotFoundException e) {}
        return referenceList;
    }
    
    /**
     * call method of class Reference to save Bibliographic Entries to text file
     * @param entriesList list of Bibliographic Entries
     * @exception FileNotFoundException if the text file cannot be found
     */
    public void saveBiblioListToFile(String[] entriesList) throws IOException
    {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(BIBLIO_FILE_NAME));
            Reference.saveBiblioToFile(entriesList, pw);
        }
        catch(FileNotFoundException e) {}
    }
}
