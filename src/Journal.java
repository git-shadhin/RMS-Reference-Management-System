import java.util.*;
import java.io.*;

/**
 * Class used to work with reference of type Journal
 * Contains necessary attributes and methods to work with Journal object
 * Extends from Reference abstract class
 * @author Tin Huynh 
 * @version 2010.10.26
 */
public class Journal extends Reference
{
    private String periodicalTitle;
    private String placeOfPublic;
    private String volume;
    private String time;
    private String pageNumber;

    /**
     * Create a Journal object
     */
    public Journal()
    {
   
    }

     /**
     * Create a Journal object that calls to method saving data to file
     * @param information collection of all information to be saved
     * @param noteText Text of the new Note
     * @param noteDate DateCreated of the new Note
     * @param bw used to write to file
     * @param br used to read from file
     * @exception IOException if an error occurs when output and input file
     */
    public Journal(HashMap information, String noteText, String noteDate, BufferedWriter bw, BufferedReader br) throws IOException
    {
        int refNo = super.saveToFile(information, bw, br);
        saveToFile(refNo, information, noteText, noteDate, bw);
    }
    
    /**
     * Create a Journal object
     * and instantiate the states for it
     * call to super class's constructor to instantiate common's attributes
     * @param information collection of all information to be saved
     */
    public Journal(HashMap information)
    {
       super(information.get("Author").toString(), information.get("Year").toString(), information.get("Title").toString());
       periodicalTitle = information.get("PeriodicalTitle").toString();
       placeOfPublic = information.get("PlaceOfPublic").toString();
       volume = information.get("Volume").toString();
       time = information.get("Time").toString();
       pageNumber = information.get("PageNumber").toString();
    }
    
    /**
     * return PeriodicalTitle of a Journal
     * @return a String contains PeriodicalTitle'state
     */
    public String getPeriodicalTitle()
    {
        return periodicalTitle;
    }
    
    /**
     * assign new value for PeriodicalTitle
     * @param newPeriodicalTitle new PeriodicalTitle value
     */
    public void setPeriodicalTitle(String newPeriodicalTitle)
    {
        periodicalTitle = newPeriodicalTitle;
    }
    
    /**
     * return PlaceOfPublic of a Journal
     * @return a String contains PlaceOfPublic'state
     */
    public String getPlaceOfPublic()
    {
        return placeOfPublic;
    }
    
    /**
     * assign new value for Place Of Publication
     * @param newPlaceOfPublic new Place Of Publication value
     */
    public void setPlaceOfPublic(String newPlaceOfPublic)
    {
        placeOfPublic = newPlaceOfPublic;
    }
    
    /**
     * return Volume of a Journal
     * @return a String contains Volume'state
     */
    public String getVolume()
    {
        return volume;
    }
    
    /**
     * assign new value for Volume
     * @param newVolume new Volume value
     */
    public void setVolume(String newVolume)
    {
        volume = newVolume;
    }
    
    /**
     * return Time of a Journal
     * @return a String contains Time'state
     */
    public String getTime()
    {
        return time;
    }
    
    /**
     * assign new value for Time
     * @param newTime new Time value
     */
    public void setTime(String newTime)
    {
        time = newTime;
    }
    
    /**
     * return PageNumber of a Journal
     * @return a String contains PageNumber'state
     */
    public String getPageNumber()
    {
        return pageNumber;
    }
    
    /**
     * assign new value for Page Number
     * @param newPageNumber new Page Numbe value
     */
    public void setPageNumber(String newPageNumber)
    {
        pageNumber = newPageNumber;
    }
    
    /**
     * save the states of Journal to text file
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
            //continue to write Journal specialized information to file after common information has been written by superclass
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
     * call super class save to file method, used by OnlineJournal class
     * @param information collection of information to be saved
     * @param bw used to write to text file
     * @param br used to read from text file
     * @param subchild signifies the use of OnlineJournal class
     * @exception IOException if an error occurs when output and input file
     */
    public int saveToFile(HashMap information, BufferedWriter bw, BufferedReader br, String subchild) throws IOException
    {
        return super.saveToFile(information, bw, br);
    }
    
    /**
     * read and modify reference information to text file
     * @param currentAuthor current author of editing Journal
     * @param currentYear current year created of editing Journal
     * @param currentTitle current title of editing Journal
     * @param information editing information of Journal
     * @param br used to read from file
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public void editToFile(String currentAuthor, String currentYear, String currentTitle, HashMap information, BufferedReader br) throws IOException
    {      
            super.editToFile(currentAuthor, currentYear, currentTitle, information, br);
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
                       pw.println(line); 
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
     * call super class edit to file method, used by OnlineJournal class
     * @param currentAuthor current author of editing OnlineJournal
     * @param currentYear current year created of editing OnlineJournal
     * @param currentTitle current title of editing OnlineJournal
     * @param information collection of information to be saved
     * @param bw used to write to text file
     * @param br used to read from text file
     * @param subchild signifies the use of OnlineJournal class
     * @exception IOException if an error occurs when output and input file
     */
    public void editToFile(String currentAuthor, String currentYear, String currentTitle, HashMap information, BufferedReader br, String subchild) throws IOException
    {      
            super.editToFile(currentAuthor, currentYear, currentTitle, information, br);
    }
    
    /**
     * match the reference number from Project data file and lookup that conference paper in Reference data file
     * @param journalNo number of the reference
     * @param filePath path of the text file
     * @return the found Journal object
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public Journal readByNo(String journalNo, String filePath) throws IOException
    {
       HashMap information =new HashMap();
       BufferedReader br = new BufferedReader(new FileReader(filePath));
       try {
            String line;
            while((line = br.readLine()) != null) {
                //if the referece number requested is found
                if(line.replaceFirst("No:".trim(),"").equals(journalNo)) {
                    information.put("Type", br.readLine().replaceFirst("Type:".trim(),""));
                    information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
                    information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
                    information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
                    information.put("PeriodicalTitle", br.readLine().replaceFirst("PeriodicalTitle:".trim(),""));
                    information.put("PlaceOfPublic", br.readLine().replaceFirst("PlaceOfPublic:".trim(),""));
                    information.put("Volume", br.readLine().replaceFirst("Volume:".trim(),""));
                    information.put("Time", br.readLine().replaceFirst("Time:".trim(),""));
                    information.put("PageNumber", br.readLine().replaceFirst("PageNumber:".trim(),""));
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
       //create a new object of Journal with information provided for attributes
       Journal myJournal = new Journal(information);
       return myJournal;
    }
    
    /**
     * read Journal states from text file and instantiate the attributes
     * finally return the Journal itself
     * @param br used to read from text file
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public Journal readAJournal(BufferedReader br) throws IOException
    {
       String line;
       HashMap information =new HashMap();
       Note aNote = null;     
       Journal myJournal = null;
       try {     
             //read information from text file and create new Journal object with these information
             information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
             information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
             information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
             information.put("PeriodicalTitle", br.readLine().replaceFirst("PeriodicalTitle:".trim(),""));
             information.put("PlaceOfPublic", br.readLine().replaceFirst("PlaceOfPublic:".trim(),""));
             information.put("Volume", br.readLine().replaceFirst("Volume:".trim(),""));
             information.put("Time", br.readLine().replaceFirst("Time:".trim(),""));
             information.put("PageNumber", br.readLine().replaceFirst("PageNumber:".trim(),""));
             myJournal = new Journal(information);  
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
                      super.addNote(myJournal.myNotes, aNote);
                  }  
             }
       }
       catch(FileNotFoundException e) {}
       catch(IOException e) {}
       return myJournal;                 
    }
    
    /**
     * return the BibliographicEntry format 
     * @return a String with bibliographical format
     */
    public String getBibliographicEntry()
    {
        String bibPeriodicalTitle = "";
        String bibPlaceOfPublic = "";
        String bibVolume = "";
        String bibTime = "";
        String bibPageNumber = "";
        if(!periodicalTitle.equals("")) {
            bibPeriodicalTitle = periodicalTitle + ", ";
        }
        if(!placeOfPublic.equals("")) {
            bibPlaceOfPublic = placeOfPublic + ", ";
        }
        if(!volume.equals("")) {
            bibVolume = "vol. " + volume + ", ";
        }
        if(!time.trim().equals("")) {
            bibTime = time + ", ";
        }
        if(!pageNumber.equals("")) {
            bibPageNumber = "p. " + pageNumber;
        } 
        String  bibliographicEntry = super.getBibliographicEntry();
        bibliographicEntry += bibPeriodicalTitle  + bibPlaceOfPublic  + bibVolume + bibTime + bibPageNumber + ".";  
        return bibliographicEntry;
    }
    
    /**
     * return the BibliographicEntry format 
     * @param signifies the use of OnlineJournal class
     * @return a String with bibliographical format
     */
    public String getBibliographicEntry(String subchild)
    {
        String bibPeriodicalTitle = "";
        String bibPlaceOfPublic = "";
        String bibVolume = "";
        String bibTime = "";
        String bibPageNumber = "";
        if(!periodicalTitle.trim().equals("")) {
            bibPeriodicalTitle = periodicalTitle + ", ";
        }
        if(!placeOfPublic.trim().equals("")) {
            bibPlaceOfPublic = placeOfPublic + ", ";
        }
        if(!volume.trim().equals("")) {
            bibVolume = "vol. " + volume + ", ";
        }
        if(!time.trim().equals("")) {
            bibTime = time + ", ";
        }
        if(!pageNumber.trim().equals("")) {
            bibPageNumber = "p. " + pageNumber + ", ";
        }   
        String  bibliographicEntry = super.getBibliographicEntry();
        bibliographicEntry += bibPeriodicalTitle + bibVolume + bibTime + bibPageNumber;  
        return bibliographicEntry;
    }
   
    /**
    * returns the states of Journal's attributes 
    * @return a String represents current states of all Journal's attributes
    */
    public String toString()
    {
       String myString; 
       myString = super.toString();
       myString += " PeriodicalTitle: " +periodicalTitle+ " Place of Public: " +placeOfPublic+ " Volume: " +volume+ " Time: " +time+ " PageNum: " +pageNumber;
       return myString; 
    }
}
