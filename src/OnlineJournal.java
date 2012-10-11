import java.util.*;
import java.io.*;

/**
 * Class used to work with reference of type OnlineJournal
 * Contains necessary attributes and methods to work with OnlineJournal object
 * Extends from Reference abstract class
 * @author Tin Huynh 
 * @version 2010.10.26
 */
public class OnlineJournal extends Journal
{
    private String titleOfDB;
    private String firstViewedDate;

    /**
     * Create an OnlineJournal object
     */
    public OnlineJournal()
    {
        
    }
    
    /**
     * Create an OnlineJournal object that calls to method saving data to file
     * @param information collection of all information to be saved
     * @param noteText Text of the new Note
     * @param noteDate DateCreated of the new Note
     * @param bw used to write to file
     * @param br used to read from file
     * @exception IOException if an error occurs when output and input file
     */
    public OnlineJournal(HashMap information, String noteText, String noteDate, BufferedWriter bw, BufferedReader br) throws IOException
    {
        int refNo = super.saveToFile(information, bw, br, "subchild");
        saveToFile(refNo, information, noteText, noteDate, bw);
    }
    
    /**
     * Create an OnlineJournal object
     * and instantiate the states for it
     * call to super class's constructor to instantiate common's attributes
     * @param information collection of all information to be saved
     */
    public OnlineJournal(HashMap information)
    {
       super(information);
       titleOfDB = information.get("TitleOfDB").toString();
       firstViewedDate = information.get("FirstViewedDate").toString();
    }
    
    /**
     * return Database title of an OnlineJournal
     * @return a String contains Database title'state
     */
    public String getTitleOfDB()
    {
        return titleOfDB;
    }
    
    /**
     * assign new value for Database title
     * @param newtitleOfDB new Database title value
     */
    public void setTitleOfDB(String newtitleOfDB)
    {
        titleOfDB = newtitleOfDB;
    }
    
    /**
     * return First viewed date of a OnlineJournal
     * @return a String contains First viewed date'state
     */
    public String getFirstViewedDate()
    {
        return firstViewedDate;
    }
    
    /**
     * assign new value for First viewed date
     * @param newFirstViewedDate new First viewed date value
     */
    public void setFirstViewedDate(String newFirstViewedDate)
    {
        firstViewedDate = newFirstViewedDate;
    }
    
    /**
     * save the states of OnlineJournal to text file
     * @param refNoInt number of the reference
     * @param information collection of information to be saved
     * @param noteText text of the new Note
     * @param noteDate date created of the new Note
     * @param bw used to write to text file
     * @exception IOException if an error occurs when output and input file
     * @exception FileNotFoundException if the file cannot be found
     * @exception NumberFormatException if a String cannot be parsed to integer
     */
    public void saveToFile(int refNoInt, HashMap information, String noteText, String noteDate, BufferedWriter bw) throws IOException
    {
        int refNo = refNoInt;
        try {
            //continue to write OnlineJournal specialized information to file after common information has been written by superclass
            bw.write("PeriodicalTitle:" + information.get("PeriodicalTitle").toString());
            bw.write("\r\n");
            bw.write("PlaceOfPublic:" + information.get("PlaceOfPublic").toString());
            bw.write("\r\n");
            bw.write("Volume:" + information.get("Volume").toString());
            bw.write("\r\n");
            bw.write("Time:" + information.get("Time").toString());
            bw.write("\r\n");
            bw.write("PageNumber:" + information.get("PageNumber").toString());
            bw.write("\r\n");
            bw.write("TitleOfDB:" + information.get("TitleOfDB").toString());
            bw.write("\r\n");
            bw.write("FirstViewedDate:" + information.get("FirstViewedDate").toString());
            bw.write("\r\n");
            bw.write("Reference" + refNo +"NoteList");
            bw.write("\r\n");
            //write new Note if text is not empty
            if(!noteText.equals("")) {
                bw.write("NoteNo:1");
                bw.write("\r\n");
                bw.write("NoteDateCreated:" + noteDate);
                bw.write("\r\n");
                bw.write("NoteText:" + noteText);
                bw.write("\r\n");
            }
            bw.write("-----");
            bw.write("\r\n");
        }
        catch(FileNotFoundException e) {}
        catch(IOException e) {}
        catch(NumberFormatException e) {}
        finally {
            if(bw != null) {
                bw.close();
            }
        }
    }
    
    /**
     * read and modify reference information to text file
     * @param currentAuthor current author of editing OnlineJournal
     * @param currentYear current year created of editing OnlineJournal
     * @param currentTitle current title of editing Journal
     * @param information editing information of Journal
     * @param br used to read from file
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public void editToFile(String currentAuthor, String currentYear, String currentTitle, HashMap information, BufferedReader br) throws IOException
    {      
            super.editToFile(currentAuthor, currentYear, currentTitle, information, br, "subchild");
            try {
                File tempFile = new File(REFERENCE_FILE_TEMP);
                File originalFile = new File(REFERENCE_FILE_NAME);
                tempFile.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                String line;
                while((line = br.readLine()) != null) {      
                   //continue from super class, if Title is matched, continue to write edited information
                   if(line.equals("Title:".trim() + information.get("Title"))) {
                       pw.println(line); //modified recently
                       line = br.readLine();
                       pw.println("PeriodicalTitle:"+ information.get("PeriodicalTitle").toString());
                       line = br.readLine();
                       pw.println("PlaceOfPublic:"+ information.get("PlaceOfPublic").toString());
                       line = br.readLine();
                       pw.println("Volume:"+ information.get("Volume").toString());
                       line = br.readLine();
                       pw.println("Time:"+ information.get("Time").toString());
                       line = br.readLine();
                       pw.println("PageNumber:"+ information.get("PageNumber").toString());
                       line = br.readLine();
                       pw.println("TitleOfDB:"+ information.get("TitleOfDB").toString());
                       line = br.readLine();
                       pw.println("FirstViewedDate:"+ information.get("FirstViewedDate").toString());
                   }
                   else {
                       pw.println(line);
                   }
                } 
                br.close();       
                pw.close();
                originalFile.delete();
                tempFile.renameTo(originalFile);
            }      
            catch (FileNotFoundException ex) {}
            catch (IOException ex) {}
    }
    
    /**
     * match the reference number from Project data file and lookup that conference paper in Reference data file
     * @param onlineJournalNo number of the reference
     * @param filePath path of the text file
     * @return the found OnlineJournal object
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public OnlineJournal readByNo(String onlineJournalNo, String filePath) throws IOException
    {
       HashMap information =new HashMap();
       BufferedReader br = new BufferedReader(new FileReader(filePath));
       try {
            String line;
            while((line = br.readLine()) != null) {
                //if the referece number requested is found
                if(line.replaceFirst("No:".trim(),"").equals(onlineJournalNo)) {
                    information.put("Type", br.readLine().replaceFirst("Type:".trim(),""));
                    information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
                    information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
                    information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
                    information.put("PeriodicalTitle", br.readLine().replaceFirst("PeriodicalTitle:".trim(),""));
                    information.put("PlaceOfPublic", br.readLine().replaceFirst("PlaceOfPublic:".trim(),""));
                    information.put("Volume", br.readLine().replaceFirst("Volume:".trim(),""));
                    information.put("Time", br.readLine().replaceFirst("Time:".trim(),""));
                    information.put("PageNumber", br.readLine().replaceFirst("PageNumber:".trim(),""));
                    information.put("TitleOfDB", br.readLine().replaceFirst("TitleOfDB:".trim(),""));
                    information.put("FirstViewedDate", br.readLine().replaceFirst("FirstViewedDate:".trim(),""));
                }
            }
       }
       catch(FileNotFoundException e) {}
       catch(IOException e) {}
       finally {
            if(br != null) {
               br.close();
            }
       }
       //create a new object of OnlineJournal with information provided for attributes
       OnlineJournal myOnlineJournal = new OnlineJournal(information);
       return myOnlineJournal;
    }
    
    /**
     * read OnlineJournal states from text file and instantiate the attributes
     * finally return the OnlineJournal itself
     * @param br used to read from text file
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public OnlineJournal readAnOnlineJournal(BufferedReader br) throws IOException
    {
       String line;
       HashMap information =new HashMap();
       Note aNote = null;     
       OnlineJournal myOnlineJournal = null;
       try {     
             //read information from text file and create new OnlineJournal object with these information
             information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
             information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
             information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
             information.put("PeriodicalTitle", br.readLine().replaceFirst("PeriodicalTitle:".trim(),""));
             information.put("PlaceOfPublic", br.readLine().replaceFirst("PlaceOfPublic:".trim(),""));
             information.put("Volume", br.readLine().replaceFirst("Volume:".trim(),""));
             information.put("Time", br.readLine().replaceFirst("Time:".trim(),""));
             information.put("PageNumber", br.readLine().replaceFirst("PageNumber:".trim(),""));
             information.put("TitleOfDB", br.readLine().replaceFirst("TitleOfDB:".trim(),""));
             information.put("FirstViewedDate", br.readLine().replaceFirst("FirstViewedDate:".trim(),""));
             myOnlineJournal = new OnlineJournal(information);  
             line = br.readLine();
             //also add Note objects to this reference's list Notes
             while(!line.equals(("-----").trim())) {
                  line = br.readLine();
                  if(line.startsWith("NoteDateCreated:".trim())) {
                      aNote = new Note();
                      aNote.setDateCreated(line.replaceFirst("NoteDateCreated:".trim(), ""));
                  }
                  if(line.startsWith("NoteText:".trim())) {
                      aNote.setText(line.replaceFirst("NoteText:".trim(), ""));
                      super.addNote(myOnlineJournal.myNotes, aNote);
                  }  
             }
       }
       catch(FileNotFoundException e) {}
       catch(IOException e) {}
       return myOnlineJournal;     
    }
    
    /**
     * return the BibliographicEntry format 
     * @return a String with bibliographical format
     */
    public String getBibliographicEntry()
    {
        String bibTitleOfDB = "";
        String bibFirstViewedDate = "";
        if(!titleOfDB.trim().equals("")) {
            bibTitleOfDB = titleOfDB + ", ";
        }
        if(!firstViewedDate.trim().equals("")) {
            bibFirstViewedDate = firstViewedDate;
        } 
        String  bibliographicEntry = super.getBibliographicEntry("subchild");
        bibliographicEntry += bibTitleOfDB  + bibFirstViewedDate + ".";  
        return bibliographicEntry;
    }
   
    /**
     * return the BibliographicEntry format 
     * @param signifies the use of OnlineJournal class
     * @return a String with bibliographical format
     */
    public String toString()
    {
       String myString; 
       myString = super.toString();
       myString += " TitleOfDB: " +titleOfDB+ " First Viewed Date: " +firstViewedDate;
       return myString; 
    }
}
