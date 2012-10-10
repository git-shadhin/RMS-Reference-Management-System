import java.util.*;
import java.io.*;

/**
 * Class used to test some functions of Reference
 * Do not call any method of this class anymore bacause they are not validated before execution
 * May lead to unexcepted behaviours if used as this time
 * You can use them if create different data files and change the file path to these new files
 * @author (Tin Huynh) 
 * @version (2010.10.27)
 */
public class ReferenceTestHarness
{
    private Reference r;
    private static final String PROJECT_FILE_NAME = "data/Project.txt";
    private static final String PROJECT_FILE_TEMP = "data/ProjectTemp.txt";
    private static final String REFERENCE_FILE_NAME = "data/Reference.txt";
   
    public ReferenceTestHarness()
    {
  
    }

    public void testSaveReference() throws IOException
    {
        HashMap information = new HashMap();
        information.put("Type", "Book");
        information.put("Author", "Tin Huynh");
        information.put("Year", "2010");
        information.put("Title", "Guide to junior game developer");
        information.put("Publisher", "Young and Wild");
        information.put("SeriesTitle", "");
        information.put("Edition", "First");
        information.put("PlaceOfPublic", "Swinburne Uni of Tech");
        
        FileWriter fw = null;
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            fw = new FileWriter("data/Reference.txt", true); //append the file
            bw = new BufferedWriter(fw);
            br = new BufferedReader(new FileReader("data/Reference.txt"));
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }
        if(information.get("Type").toString().equals("Book"))
        {
            Book aBook = new Book(information, "", "", bw, br);
        }
    }
    
    public void testEditReference() throws IOException
    {
        String currentAuthor = "Sobieralski, C";
        String currentYear = "1995";
        String currentTitle = "Development of a dangerous goods compliance model for the photographic manufacturing industry";
        
        HashMap information = new HashMap();
        information.put("Type", "Thesis");
        information.put("Author", "Sobieralski, Ca");
        information.put("Year", "1995a");
        information.put("Title", "Development of a dangerous goods compliance model for the photographic manufacturing industry");
        information.put("Publisher", "MEng thesisa");
        information.put("PlaceOfPublic", "Deakin");
        information.put("Location", "Deakin");
        
        try {
            //PrintWriter pw = new PrintWriter(new FileWriter("data/ReferenceTemp.txt")); //append the file
            BufferedReader br = new BufferedReader(new FileReader("data/ReferenceTest.txt"));
            if(information.get("Type").toString().equals("Thesis")) {
                Thesis aThe = new Thesis();
                aThe.editToFile(currentAuthor, currentYear, currentTitle, information, br);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }
    }
    
    public void testDeleteReference() throws IOException
    {
        String currentAuthor = "J Wong";
        String currentYear = "1976";
        String currentTitle = "IT Professional";
        
        /*String currentAuthor = "Tin Huynh";
        String currentYear = "2010";
        String currentTitle = "A devastating game approach to maniac gamer!!!";*/
        
       /* String currentAuthor = "Gosslin, D";
        String currentYear = "2010";
        String currentTitle = "A new approach to Psychological Model";*/
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/ReferenceTest.txt"));
            //Reference refIns = new Reference();
            Reference.deleteFromFile(currentAuthor, currentYear, currentTitle, br);
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }
       
    }
    
    public void testSaveNote() throws IOException
    {
        /*String type = "Book";
        String currentAuthor = "Gosslin, D";
        String currentYear = "2010";
        String currentTitle = "A new approach to Psychological Model";*/
        /* String type = "Book";
        String currentAuthor = "J Wong";
        String currentYear = "1976";
        String currentTitle = "IT Professional";*/
        String type = "Book";
        String currentAuthor = "Tin Huynh";
        String currentYear = "2010";
        String currentTitle = "A devastating game approach to maniac gamer!!!";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/Reference.txt"));
            Reference.saveNoteToFile(type, currentAuthor, currentYear, currentTitle, "Test note", "2/11/1999" , br);
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }
    }
    
    public void testEditNote() throws IOException
    {
        String type = "Book";
        String currentAuthor = "Tin Huynh";
        String currentYear = "2010";
        String currentTitle = "A devastating game approach to maniac gamer!!!";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/Reference.txt"));
            Reference.editNoteToFile(type, currentAuthor, currentYear, currentTitle, "1/1/2010",  "This is a good book about human psychology", "1/2/2008", "Old one" , br);
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }  
    }
    
    public void testDeleteNote() throws IOException
    {
       /* String type = "Book";
        String currentAuthor = "Tin Huynh";
        String currentYear = "2010";
        String currentTitle = "A devastating game approach to maniac gamer!!!";*/
        
        /*String type = "Book";
        String currentAuthor = "Gosslin, D";
        String currentYear = "2010";
        String currentTitle = "A new approach to Psychological Model";*/
        String type = "Book";
        String currentAuthor = "J Wong";
        String currentYear = "1976";
        String currentTitle = "IT Professional";
        
        String noteDate = "4/1/2010";
        String noteText = "Interesting fact in p199";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/Reference.txt"));
            //Reference refIns = new Reference();
            Reference.deleteNoteFromFile(type, currentAuthor, currentYear, currentTitle, noteDate, noteText , br);
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }  
    }
    
    public void testSearchReference(String keyword) throws IOException
    {
        try {
            ArrayList<Reference> result = new ArrayList<Reference>();
            BufferedReader br = new BufferedReader(new FileReader("data/Reference.txt"));
            //Reference refIns = new Reference();
            result = Reference.searchReferences(keyword, br);
            if(result.size() == 0)
                System.out.println("Search returns no reference");
            else {
                for(Reference r : result) {
                    System.out.println(r.getClass().getName() + " " + r.getTitle() + " " + r.getAuthor() + " " + r.getYearCreated());
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }  
    }
    
    public void testSearchNotes(String keyword) throws IOException
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
            //Reference refIns = new Reference();
            ArrayList<String> noteData = new ArrayList<String>();
            noteData = Reference.searchReferenceNotes(keyword ,br);
            for(int i=0; i< noteData.size(); i= i+6) {
                System.out.println("Date Created: " + noteData.get(i) + " Content: " + noteData.get(i+1) + " Belong to [" + noteData.get(i+2) + "] " + noteData.get(i+3) + " " + noteData.get(i+4) + ", " + noteData.get(i+5)); 
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }  
    }
    
    public void loadAllReference() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("data/Reference.txt"));
        ArrayList<Reference> refArr = Reference.readAllReferences(br);
        
        for(Reference re : refArr) {
            System.out.println(re.getBibliographicEntry());
            for(int i=0; i< re.myNotes.size(); i++) {
                System.out.println(re.myNotes.get(i).getDateCreated() + "   " + re.myNotes.get(i).getText());
            }
        }
    }
}
