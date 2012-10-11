import java.util.*;
import java.io.*;
/**
 * A class to manage reference for the program.
 * Contain all the general attributes and methods necessarry that are needed to work with a reference
 * This class is abstract
 * Also work with objects contain inside a reference (references and notes)
 * 
 * @author Tin Huynh
 * @version 2010.10.26
 */
public abstract class Reference implements Harvard, hasNotes
{
    protected String author;
    protected String yearCreated;
    protected String title;
    protected static final String REFERENCE_FILE_NAME = "data/Reference.txt";
    protected static final String REFERENCE_FILE_TEMP = "data/ReferenceTemp.txt";
    protected ArrayList<Note> myNotes;
    
    /**
     * for the subclass constructor to run properly
     */
    public Reference()
    {
        
    }
    
    /**
     * called by subclasses to instantiate common attributes of reference 
     */
    public Reference(String newAuthor, String newYearCreated, String newTitle)
    {
        author = newAuthor;
        yearCreated = newYearCreated;
        title = newTitle;
        myNotes = new ArrayList<Note>();
    }
    
    /**
     * return author of the reference
     * @return reference author
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * assign a new value for reference author
     * @param newAuthor new author to be assigned
     */
    public void setAuthor(String newAuthor)
    {
        author = newAuthor;
    }
    
    /**
     * return year created of the reference
     * @return reference year created
     */
    public String getYearCreated()
    {
        return yearCreated;
    }
    
    /**
     * assign a new value for reference year created
     * @param newYearCreated new year created to be assigned
     */
    public void setYearCreated(String newYearCreated)
    {
        yearCreated = newYearCreated;
    }
    
    /**
     * return title of the reference
     * @return reference title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * assign a new value for reference title
     * @param newTitle new title to be assigned
     */
    public void setTitle(String newTitle)
    {
        title = newTitle;
    }
    
    /**
     * return the number of Reference objects in myReferences ArrayList 
     * @param myNotes list of Note objects
     * @param aNote the Note object to be added to the ArrayList
     */
    public void addNote(ArrayList<Note> myNotes, Note aNote)
    {
        myNotes.add(aNote);
    }
    
    /**
     * return a Note at the supplied index
     * @param noteNumber index of the Note
     * @return the found Note object
     */
    public Note showNote(int noteNumber)
    {
        Note myNote = null;
        if(noteNumber <= 0) {
            
        }
        else if (noteNumber >= noOfNote()) {
            
        }
        else {
            myNote = myNotes.get(noteNumber);            
        }
        return myNote;
    }
    
    /**
     * return collection of all Note objects
     * @return collection of Note objects
     */
    public ArrayList<Note> listNotes()
    {
        return myNotes;
    }
    
    /**
     * remove a Note from the collection at the supplied index
     * @param noteNumber index of the remove Note
     */
    public void removeNote(int noteNumber)
    {
        if(noteNumber < 0) {
            
        }
        else if(noteNumber >= noOfNote()) {
           
        }
        else {
             myNotes.remove(noteNumber);
        }
    }
    
    /**
     * return collection of Notes that contain the searchString in their text
     * @param searchString the keyword 
     * @return Collection of match Note objects
     */
    public ArrayList<Note> search(String searchString)
    {
        ArrayList<Note> searchNotes = new ArrayList<Note>();
        for(int i=0; i < noOfNote(); i++)
        {
            Note aNote = myNotes.get(i);
            if(aNote.getText().contains(searchString)) {
                searchNotes.add(aNote);
            }
        }
        return searchNotes;
    }
    
    /**
     * return the number of Note objects in myNotes ArrayList 
     * @return number of Note objects 
     */
    public int noOfNote()
    {
        return myNotes.size();
    }
   
    /**
     * read the reference data file and check if the reference existed
     * @param type the type of the reference
     * @param theAuthor author of the reference
     * @param theYear year created of the reference
     * @param theTitle title of the reference
     * @return a boolean value determine existence of the reference
     * @exception IOException if an error occurs when input or output file 
     */
    public static boolean checkExistReference(String type, String theAuthor, String theYear, String theTitle, BufferedReader br) throws IOException
    {
        boolean found = false;
        String tempType = "", tempAuthor = "", tempYear = "", tempTitle = "";
        try {
            String line;
            while((line = br.readLine()) != null) {
                //if the line is about Type of reference
                if(line.startsWith("Type:".trim())) {
                    //assign type value for a variable
                    tempType = line.replaceFirst("Type:", "");
                    //read on and assign other 3 key fields
                    line = br.readLine();
                    if(line.startsWith("Author:".trim())) {
                        tempAuthor = line.replaceFirst("Author:", "");
                    }
                    line = br.readLine();
                    if(line.startsWith("Year:".trim())) {
                        tempYear = line.replaceFirst("Year:", "");
                    }
                    line = br.readLine();
                    if(line.startsWith("Title:".trim())) {
                        tempTitle = line.replaceFirst("Title:", "");
                    }
                }
                //if 4 key fields of a reference is matched with 4 passed in key fields, the reference exists
                if(tempType.trim().equals(type.trim()) && tempAuthor.trim().equals(theAuthor.trim()) && tempYear.trim().equals(theYear.trim()) && tempTitle.trim().equals(theTitle.trim())) {
                    found = true;    
                    break;
                }
            }   
        }
        catch(IOException e) {}
        finally {
            if(br!= null)
                br.close();
        }
        return found;
    }
    
    /**
     * Find the reference number from the supplied key fields
     * @param type the type of the reference
     * @param theAuthor author of the reference
     * @param theYear year created of the reference
     * @param theTitle title of the reference
     * @param br used to read from text file
     * @return a String value contains reference number
     * @exception IOException if an error occurs when input or output file 
     */
    public static String findReferenceNo(String type, String theAuthor, String theYear, String theTitle, BufferedReader br) throws IOException
    {
        String refNo ="";
        boolean found = false;
        String tempType = "", tempAuthor = "", tempYear = "", tempTitle = "";
        try {
            String line;
            while((line = br.readLine()) != null) 
            {
                //assign Reference Number to a variable every time file reader reaches the Reference Number lines if matched Reference has not been found
                if(line.startsWith("No:".trim()) && !found) {
                    refNo = line.replaceFirst("No:".trim(), "");
                }
                if(line.startsWith("Type:".trim())) {
                    tempType = line.replaceFirst("Type:", "");
                    line = br.readLine();
                    if(line.startsWith("Author:".trim()))
                        tempAuthor = line.replaceFirst("Author:", "");
                    line = br.readLine();
                    if(line.startsWith("Year:".trim()))
                        tempYear = line.replaceFirst("Year:", "");
                    line = br.readLine();
                    if(line.startsWith("Title:".trim()))
                        tempTitle = line.replaceFirst("Title:", "");
                }
                if(tempType.trim().equals(type) && tempAuthor.trim().equals(theAuthor) && tempYear.trim().equals(theYear) && tempTitle.trim().equals(theTitle)) {
                    found = true;
                }
            }   
        }
        catch(IOException e) {}
        finally {
            if(br!= null)
                br.close();
        }
        return refNo;
    }
    
    /**
     * Read from textfile and load all data to attributes of references
     * Behaviour varies based on type of reference
     * @param br used to read from text file
     * @return a full collection of all reference objects
     * @exception IOException if an error occurs when input or output file 
     * @exception NullPointerException when textfile cannot be found
     */
    public static ArrayList<Reference> readAllReferences(BufferedReader br) throws IOException
    {
        ArrayList<Reference> allReferences = new ArrayList<Reference>();
        Reference refIns = null;
        String line;
        String refNo;
        String refType;
        try {
        while((line = br.readLine()) != null && (!line.equals(""))) {
              //first start with the line contains Reference Number
              if(line.startsWith("No:".trim())) {
                  line = br.readLine();
                  //based on Type read from file, create appropriate Reference object type and add it to the list of References 
                  if(line.replaceFirst("Type:", "").toLowerCase().equals("book")) {
                      Book bookIns = new Book();
                      Book aBook = bookIns.readABook(br);
                      allReferences.add(aBook);
                  }
                  if(line.replaceFirst("Type:", "").toLowerCase().equals("conferencepaper")) {
                      ConferencePaper conferencePaperIns = new ConferencePaper();
                      ConferencePaper aConferencePaper = conferencePaperIns.readAConferencePaper(br);
                      allReferences.add(aConferencePaper);
                  }
                  if(line.replaceFirst("Type:", "").toLowerCase().equals("journal")) {
                      Journal journalIns = new Journal();
                      Journal aJournal = journalIns.readAJournal(br);
                      allReferences.add(aJournal);
                  }
                  if(line.replaceFirst("Type:", "").toLowerCase().equals("onlinejournal")) {
                      OnlineJournal onlineJournalIns = new OnlineJournal();
                      OnlineJournal anOnlineJournal = onlineJournalIns.readAnOnlineJournal(br);
                      allReferences.add(anOnlineJournal);
                  }
                  if(line.replaceFirst("Type:", "").toLowerCase().equals("thesis")) {
                      Thesis thesisIns = new Thesis();
                      Thesis aThesis = thesisIns.readAThesis(br);
                      allReferences.add(aThesis);
                  }
                  if(line.replaceFirst("Type:", "").toLowerCase().equals("webpage")) {
                      WebPage webPageIns = new WebPage();
                      WebPage aWebPage = webPageIns.readAWebPage(br);
                      allReferences.add(aWebPage);
                  }
              }    
          } 
      }
      catch(IOException e) {}
      catch(NullPointerException e) {}
      finally {
          if(br != null)
             br.close();
      }
      return allReferences;
    }
    
    /**
     * save the new Reference states into file
     * @param information all data of new project
     * @param bw used to write to file
     * @param br used to read from file
     * @return an integer of reference no for the subclass to continue to write information
     */
    public int saveToFile(HashMap information, BufferedWriter bw, BufferedReader br) 
    {
        String refNo;
        int refNoInt;
        try {
            //call method that returns last reference number in file
            refNo = readLastReferenceNo(br);
            if(refNo.equals("")) { 
                //no reference in file yet
                refNoInt = 1;
            }
            else {
                refNoInt = Integer.parseInt(refNo) +1;
            }
            //write object states to file in format
            //information received from a HashMap
            bw.write("No:" + refNoInt);
            bw.write("\r\n");
            bw.write("Type:" + information.get("Type").toString());
            bw.write("\r\n");
            bw.write("Author:" + information.get("Author").toString());
            bw.write("\r\n");
            bw.write("Year:" + information.get("Year").toString());
            bw.write("\r\n");
            bw.write("Title:" + information.get("Title").toString());
            bw.write("\r\n");
            //return the number of this new Reference for subclasses to continue to write their own information
            return refNoInt;
        }
        catch(FileNotFoundException e) {}
        catch(IOException e) {}
        catch(NumberFormatException e) {}
        return -1;
    }
    
    /**
     * save the new Note states into file
     * @param type type of the reference has note being added
     * @param theAuthor author of the reference has note being added
     * @param theYear year of the reference has note being added
     * @param theTitle title of the reference has note being added
     * @param noteText Text of the new note
     * @param dateCreated DateCreated of the new Note
     * @param br used to read from file
     * @return an integer of reference no for the subclass to continue to write information
     * @exception IOException if an error occurs when input or output file 
     * @exception NullPointerException when textfile cannot be found
     * @exception NumberFormatException when a String cannot be parsed to an integer
     */
    public static void saveNoteToFile(String type, String theAuthor, String theYear, String theTitle, String noteText, String dateCreated, BufferedReader br) throws IOException 
    {
        boolean foundReference = false;
        String noteNo;
        int noteNoInt;
        try {
            //call method to check Reference existence on file
            foundReference = checkExistReference(type, theAuthor, theYear, theTitle, br);
        }
        catch(FileNotFoundException e) {}
        //if exists
        if(foundReference) {
            try {
                br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                //call method to read lastest Note number under this Reference
                noteNo = readLastNoteNo(type, theAuthor, theYear, theTitle, br);
                if(noteNo.equals("")) { //no note in file yet
                    noteNoInt = 1;
                }
                else {
                    noteNoInt = Integer.parseInt(noteNo) +1;
                }
                File tempFile = new File(REFERENCE_FILE_TEMP);
                File originalFile = new File(REFERENCE_FILE_NAME);
                //Create a new temp file
                tempFile.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                String line;            
                while((line = br.readLine()) != null) {
                   //To determine the Reference, 4 key fields must be matched. If one of them is not matched, this is not the reference to add Note to
                   if(line.equals("Type:".trim() + type)) {
                      pw.println(line);
                      line = br.readLine();
                      if(line.equals("Author:".trim() + theAuthor)) {
                          pw.println(line);
                          line = br.readLine();
                          if(line.equals("Year:".trim() + theYear)) {
                             pw.println(line);
                             line = br.readLine();
                             if(line.equals("Title:".trim() + theTitle)) {
                                    pw.println(line);
                                    line = br.readLine();
                                    while(!line.equals("-----".trim())) {
                                        pw.println(line);        
                                        line = br.readLine();
                                    }
                                    //before delimiter is written to temp file, write information of new Note first
                                    if(line.equals("-----".trim())) {
                                        pw.println("NoteNo:" + noteNoInt);  
                                        pw.println("NoteDateCreated:" + dateCreated);                 
                                        pw.println("NoteText:" + noteText);  
                                        pw.println(line);
                                    }
                              }
                              else {
                                pw.println(line);
                              }
                          }
                          else {
                              pw.println(line);
                          }
                      }
                      else {
                        pw.println(line);
                      }
                   }
                   else {
                       pw.println(line);
                   }
                } 
                br.close();       
                pw.close();
                //delete the original file
                originalFile.delete();
                //rename the temp file to the name of original file
                tempFile.renameTo(originalFile);   
            }catch(FileNotFoundException e) {}
            catch(IOException e) {}
            catch(NumberFormatException e) {}
        }
    }
    
    /**
     * ensure the reference will not change its key fields' combination 
     * to the same with any other reference
     * @param type type of the reference
     * @param oldAuthor current author of the reference
     * @param oldYearCreated current year created of the reference
     * @param oldTitle title current title of the reference
     * @param newAuthor Text new author of the reference
     * @param newYearCreated new year created of the reference
     * @param newTitle new title of the reference
     * @param br used to read from file
     * @return a boolean value if the change validates with another reference
     * @exception IOException if an error occurs when input or output file 
     */
    public static boolean validateEditReference(String type, String oldAuthor, String oldYearCreated, String oldTitle, String newAuthor, String newYearCreated, String newTitle, BufferedReader br) throws IOException
    {
        String tempType = "";
        String tempAuthor = "";
        String tempYearCreated = "";
        String tempTitle = "";
        boolean existedReference = false;
        try {
            String line;
            while((line = br.readLine()) != null) {
                //first, the type is matched
                if(line.replaceFirst("Type:".trim(), "").equals(type)) {
                    tempType = line.replaceFirst("Type:".trim(),"");
                    line = br.readLine();
                    tempAuthor = line.replaceFirst("Author:".trim(),"");
                    line = br.readLine();
                    tempYearCreated = line.replaceFirst("Year:".trim(),"");
                    line = br.readLine();
                    tempTitle = line.replaceFirst("Title:".trim(),"");
                    //Disregard the Reference with the key fields match old key fields (reference whose key fields are currently edited)
                    if(!(tempAuthor.equals(oldAuthor) && tempYearCreated.equals(oldYearCreated) && tempTitle.equals(oldTitle))) {
                        if(tempAuthor.equals(newAuthor) && tempYearCreated.equals(newYearCreated) && tempTitle.equals(newTitle)) {
                            existedReference = true;
                        }
                    }
                }
            }   
        }
        catch(IOException e) {}
        return existedReference;
    }
    
    /**
     * ensure the note will not change its key fields' combination 
     * to the same with any other note in a same reference except the current note itself
     * @param type type of the reference
     * @param author author of the reference
     * @param yearCreated year created of the reference
     * @param title title title of the reference
     * @param oldNoteDate current dateCreated of the note being edited
     * @param oldNoteText current text of the note being edited
     * @param newNoteDate new dateCreated of the note being edited
     * @param newNoteText new Text of the note being edited
     * @param br used to read from file
     * @return a boolean value if the change validates with another note
     * @exception IOException if an error occurs when input or output file 
     */
    public static boolean validateEditNote(String type, String author, String yearCreated, String title, String oldNoteDate, String oldNoteText, String newNoteDate, String newNoteText, BufferedReader br) throws IOException
    {
        boolean existedNote = false;
        String tempType = "";
        String tempAuthor = "";
        String tempYearCreated = "";
        String tempTitle = "";        
        String noteDate = "";
        String noteText = "";
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.replaceFirst("Type:".trim(), "").equals(type)) {
                    line = br.readLine();
                    tempAuthor = line.replaceFirst("Author:".trim(), "");
                    line = br.readLine();
                    tempYearCreated = line.replaceFirst("Year:".trim(), "");
                    line = br.readLine();
                    tempTitle = line.replaceFirst("Title:".trim(), "");
                    //if the Reference requested is reached
                    if(tempAuthor.equals(author) && tempYearCreated.equals(yearCreated) && tempTitle.equals(title)) {
                        while(!line.equals("-----".trim())) {
                            line = br.readLine();
                            if(line.startsWith("NoteDateCreated:".trim())) {
                                noteDate = line.replaceFirst("NoteDateCreated:".trim(),"");  
                                line = br.readLine();
                                noteText = line.replaceFirst("NoteText:".trim(),"");  
                                //if the combination of new information matches with any combination of Note in file, except the combination of current Note itself
                                if(!noteDate.equals(oldNoteDate) && !noteText.equals(oldNoteText)) {
                                    if(noteDate.equals(newNoteDate) && noteText.equals(newNoteText)) {
                                        existedNote = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }   
        }
        catch(IOException e) {}
        return existedNote;
    }
    
    /**
     * ensure the note will not change its key fields' combination 
     * to the same with any other note in a same reference
     * @param type type of the reference
     * @param theAuthor author of the reference
     * @param theYear year created of the reference
     * @param theTitle title title of the reference
     * @param newDate new dateCreated of the note
     * @param newText new Text of the note
     * @param br used to read from file
     * @return a boolean value if the change validates with another note
     * @exception IOException if an error occurs when input or output file 
     */
    public static boolean checkExistedNote(String type, String theAuthor, String theYear, String theTitle, String newDate, String newText, BufferedReader br) throws IOException
    {
        String tempType = "";
        String tempAuthor = "";
        String tempYear = "";
        String tempTitle = "";
        boolean existedNote = false;
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Type".trim())) {
                    tempType = line.replaceFirst("Type:".trim(),"");
                    line  = br.readLine();
                    tempAuthor = line.replaceFirst("Author:".trim(),"");
                    line  = br.readLine();
                    tempYear = line.replaceFirst("Year:".trim(),"");
                    line  = br.readLine();
                    tempTitle = line.replaceFirst("Title:".trim(),"");
                    //find the reference that has new note to be added. Find note under this reference that has the existed combination of date created and text
                    if(tempType.equals(type) && tempAuthor.equals(theAuthor) && tempYear.equals(theYear) && tempTitle.equals(theTitle)) {
                        while(!line.equals("-----".trim())) {
                            line = br.readLine();
                            if(line.replaceFirst("NoteDateCreated:", "").equals(newDate)) {
                                line = br.readLine();
                                if(line.replaceFirst("NoteText:", "").equals(newText)) {
                                    existedNote = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }   
        }
        catch(IOException e) {}
        return existedNote;
    }
    
    /**
     * read and modify note information to text file
     * @param type type of reference that has note being edited
     * @param theAuthor author of reference that has note being edited
     * @param theYear year created of reference that has note being edited
     * @param theTitle title of reference that has note being edited
     * @param currentDate current date created of the editing note
     * @param currentText current text of the editing note
     * @param newDate new date created of the editing note
     * @param newText new text of the editing note
     * @param br used to read from file
     * @exception IOException if error occurs when input and output file
     */
    public static void editNoteToFile(String type, String theAuthor, String theYear, String theTitle, String currentDate, String currentText, String newDate, String newText, BufferedReader br) throws IOException
    {
        boolean foundReference = false;
        String noteNo;
        int noteNoInt; 
        try {
            //call method to check for Reference existence
            foundReference = checkExistReference(type, theAuthor, theYear, theTitle, br);
        }
        catch(FileNotFoundException e) {}
        //if Reference found
        if(foundReference) {
            try {
                File tempFile = new File(REFERENCE_FILE_TEMP);
                File originalFile = new File(REFERENCE_FILE_NAME);
                //Create a new temp file
                tempFile.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                String line;
                while((line = br.readLine()) != null) {
                   //check each key fields to find the right reference
                   //only edit Note of the chosen Reference
                   if(line.equals("Type:".trim() + type)) {
                      pw.println(line);
                      line = br.readLine();
                      if(line.equals("Author:".trim() + theAuthor)) {
                          pw.println(line);
                          line = br.readLine();
                          if(line.equals("Year:".trim() + theYear)) {
                             pw.println(line);
                             line = br.readLine();
                             if(line.equals("Title:".trim() + theTitle)) {
                                     pw.println(line);
                                     line = br.readLine();
                                     while(!line.equals("-----".trim())) {
                                         if(line.equals("NoteDateCreated:" + currentDate)) {
                                             String tempDate = line;
                                             line =br.readLine();
                                             if(line.equals("NoteText:" + currentText)) {
                                                 pw.println("NoteDateCreated:" + newDate);
                                                 pw.println("NoteText:" + newText);
                                             }
                                             else {
                                                 pw.println(tempDate);
                                                 pw.println(line);
                                             }
                                         }
                                         else {
                                             pw.println(line);        
                                         }
                                         line = br.readLine();
                                     }        
                                     pw.println(line);
                              }
                              else {
                                pw.println(line);
                              }
                          }
                          else {
                              pw.println(line);
                          }
                      }
                      else {
                        pw.println(line);
                      }
                   }
                   else {
                       pw.println(line);
                   }
                } 
                br.close();       
                pw.close();
                //delete the original file
                originalFile.delete();
                //rename the temp file to the name of original file
                tempFile.renameTo(originalFile); 
            }catch(FileNotFoundException e) {}
            catch(IOException e) {}
            catch(NumberFormatException e) {}
        }
    }
    
    /**
     * read and delete note information from text file
     * @param type type of reference that has note being deleted
     * @param theAuthor author of reference that has note being deleted
     * @param theYear year created of reference that has note being deleted
     * @param theTitle title of reference that has note being deleted
     * @param currentDate current date created of the deleting note
     * @param currentText current text of the deleting note
     * @param br used to read from file
     * @FileNotFoundException if text file cannot be found
     */
    public static void deleteNoteFromFile(String type, String theAuthor, String theYear, String theTitle, String currentDate, String currentText, BufferedReader br) throws IOException
    {
        boolean foundReference = false;
        boolean foundDeleteNote = false;
        String noteNoToDelete = "";
        try {
            //check reference existence
            foundReference = checkExistReference(type, theAuthor, theYear, theTitle, br);
        }
        catch(FileNotFoundException e) {}
        //if reference exists
        if(foundReference) {
            try {
                File tempFile = new File(REFERENCE_FILE_TEMP);
                File originalFile = new File(REFERENCE_FILE_NAME);
                //create new temp file
                tempFile.createNewFile();
                
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                String line;
                //write to new file while checking from original file if the reference that has note being edited is reached or not
                while((line = br.readLine()) != null) {
                   if(line.equals("Type:".trim() + type)) {
                      pw.println(line);
                      line = br.readLine();
                      if(line.equals("Author:".trim() + theAuthor)) {
                          pw.println(line);
                          line = br.readLine();
                          if(line.equals("Year:".trim() + theYear)) {
                             pw.println(line);
                             line = br.readLine();
                             if(line.equals("Title:".trim() + theTitle)) {
                                     pw.println(line);
                                     line = br.readLine();
                                     while(!line.equals("-----".trim())) {
                                         //store Note number to variable if found Delete Note is not reached
                                         if(line.startsWith("NoteNo:".trim()) && !foundDeleteNote) {
                                             noteNoToDelete = line;         
                                         }
                                         //if Note to be deleted is found, do no write these data to new file
                                         if(line.equals("NoteDateCreated:" + currentDate)) {
                                             String tempDate = line;
                                             line = br.readLine();
                                             if(line.equals("NoteText:" + currentText)) {
                                                 foundDeleteNote = true;
                                             }
                                             //only DateCreated matched, not the Note to be deleted
                                             else {
                                                pw.println(tempDate);
                                                pw.println(line);
                                             }
                                         }
                                         else {
                                             pw.println(line);        
                                         }
                                         line = br.readLine();
                                     } 
                                     pw.println(line);
                              }
                              else {
                                pw.println(line);
                              }
                          }
                          else {
                              pw.println(line);
                          }
                      }
                      else {
                        pw.println(line);
                      }
                   }
                   else {
                       pw.println(line);
                   }
                } 
                br.close();       
                pw.close();
                //delete the original file
                originalFile.delete();
                //rename temp file to original file
                tempFile.renameTo(originalFile);
                
                //repeat the above process to remove the line with deleted Note Number
                tempFile = new File(REFERENCE_FILE_TEMP);
                originalFile = new File(REFERENCE_FILE_NAME);
                tempFile.createNewFile();
                pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                
                while((line = br.readLine()) != null) {
                   if(line.equals("Type:".trim() + type)) {
                      pw.println(line);
                      line = br.readLine();
                      if(line.equals("Author:".trim() + theAuthor)) {
                          pw.println(line);
                          line = br.readLine();
                          if(line.equals("Year:".trim() + theYear)) {
                             pw.println(line);
                             line = br.readLine();
                             if(line.equals("Title:".trim() + theTitle)) {
                                        pw.println(line);
                                        line = br.readLine();
                                        while(!line.equals("-----".trim())) {
                                            if(line.equals(noteNoToDelete)) {
                                                line = br.readLine();
                                            }
                                            else {
                                                pw.println(line);
                                                line = br.readLine();
                                            }
                                        }   
                                        pw.println(line);
                              }
                              else {
                                pw.println(line);
                              }
                          }
                          else {
                              pw.println(line);
                          }
                      }
                      else {
                        pw.println(line);
                      }
                   }
                   else {
                       pw.println(line);
                   }
                }
                pw.close();
                br.close();
                originalFile.delete();
                tempFile.renameTo(originalFile);     
            }catch(FileNotFoundException e) {}
            catch(IOException e) {}
            catch(NumberFormatException e) {}
        }
    }
    
    /**
     * @param type type of the reference
     * @param theAuthor author of the reference
     * @param theYear year created of the reference
     * @param theTitle title of the reference
     * @return a String contains latest note number 
     * @exception IOException if error occurs when input and output file
     */
    public static String readLastNoteNo(String type, String theAuthor, String theYear, String theTitle, BufferedReader br) throws IOException
    {
        String noteNo = "";
        try {
            String line;
            while((line = br.readLine()) != null) {
                //find inside the requested Reference, the last Note Number
                if(line.equals("Type:".trim() + type)) {
                      line = br.readLine();
                      if(line.equals("Author:".trim() + theAuthor)) {
                          line = br.readLine();
                          if(line.equals("Year:".trim() + theYear)) {
                             line = br.readLine();
                             if(line.equals("Title:".trim() + theTitle)) { 
                                    line = br.readLine();
                                    while(!line.equals("-----".trim())) {
                                        line = br.readLine();
                                        if(line.startsWith("NoteNo:".trim()))
                                        noteNo = line.replaceFirst("NoteNo:", "");
                                    }
                               }
                          }
                      }
                 }   
            }
        }
        catch(IOException e) {}
        finally {
            if(br != null)
            {
                br.close();
            }
        }
        return noteNo.trim();
    }
    
    /**
     * read and modify reference information to text file
     * @param currentAuthor current author of editing reference
     * @param currentYear current year created of editing reference
     * @param currentTitle current title of editing reference
     * @param information editing information of reference
     * @param br used to read from file
     * @exception IOException if error occurs when input and output file
     */
    public void editToFile(String currentAuthor, String currentYear, String currentTitle, HashMap information, BufferedReader br) throws IOException
    {
            PrintWriter pw = null;
            File tempFile = new File(REFERENCE_FILE_TEMP);
            File originalFile = new File(REFERENCE_FILE_NAME);
            try { 
                //create new temp file
                tempFile.createNewFile();
                pw = new PrintWriter(new FileWriter(tempFile));
                String line;
                while((line = br.readLine()) != null) {      
                   //check key fields, if matched all of them, write new information in their position in new file
                   //if not matched or partially matched, write old data from original file
                   if(line.equals("Author:".trim() + currentAuthor)) {
                      String authorTemp = line;
                      line = br.readLine();
                      if(line.equals("Year:".trim() + currentYear)) {
                          String yearTemp = line;
                          line = br.readLine();
                          if(line.equals("Title:".trim() + currentTitle)) {
                              pw.println("Author:" + information.get("Author").toString());
                              pw.println("Year:" + information.get("Year").toString());
                              pw.println("Title:" + information.get("Title").toString());
                          }
                          else {
                              pw.println(authorTemp);
                              pw.println(yearTemp);
                              pw.println(line);
                          }
                      }
                      else {
                        pw.println(authorTemp);
                        pw.println(line);
                      }
                   }
                   else {
                       pw.println(line);
                   }
                }         
            }      
            catch (FileNotFoundException ex) {}
            catch (IOException ex) {}
            finally {
                if(br!=null) {
                    br.close();
                }
                if(pw!=null) {
                    pw.close();
                }
            }
            //delete original file
           originalFile.delete();
           //rename temp file to new file
           tempFile.renameTo(originalFile);
    }
    
    /**
     * read and delete reference information from text file
     * @param currentAuthor author of the reference to be deleted
     * @param currentYear year created of the reference to be deleted
     * @param currentTitle title of the reference to be deleted
     * @param br used to read from file
     * @IOException if error occurs when input and output file
     * @FileNotFoundException if text file cannot be found
     */
    public static void deleteFromFile(String currentAuthor, String currentYear, String currentTitle, BufferedReader br) throws IOException
    {
            try 
            {
                boolean foundDeleteRef = false;
                String refNoToDelete = "";
                File tempFile = new File(REFERENCE_FILE_TEMP);
                File originalFile = new File(REFERENCE_FILE_NAME);
                tempFile.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                String line;
                while((line = br.readLine()) != null) {
                   if(line.startsWith("No:".trim()) && !foundDeleteRef) {
                        refNoToDelete = line;         
                   }
                   if(line.equals("Author:".trim() + currentAuthor)) {
                      String authorTemp = line;
                      line = br.readLine();
                      if(line.equals("Year:".trim() + currentYear)) {
                          String yearTemp = line;
                          line = br.readLine();
                          if(line.equals("Title:".trim() + currentTitle)) {
                              foundDeleteRef = true;
                              //if all key fields matched, write nothing to new file until reached delimiter
                              while(!line.equals("-----".trim())) {
                                line = br.readLine();    
                              }
                          }
                          else {
                              pw.println(authorTemp);
                              pw.println(yearTemp);
                              pw.println(line);
                          }
                      }
                      else {
                        pw.println(authorTemp);
                        pw.println(line);
                      }
                   }
                   else {
                       pw.println(line);
                   }
                } 
                br.close();       
                pw.close();
                
                originalFile.delete();
                tempFile.renameTo(originalFile);
                //repeat the process with the remaining Reference Number line
                tempFile = new File(REFERENCE_FILE_TEMP);
                originalFile = new File(REFERENCE_FILE_NAME);
                tempFile.createNewFile();
                pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                while((line = br.readLine()) != null) {
                    if(line.equals(refNoToDelete)) {
                        line = br.readLine();
                    }
                    else {
                        pw.println(line);
                    }
                }
                pw.close();
                br.close();
                
                originalFile.delete();
                tempFile.renameTo(originalFile);
            }      
            catch (FileNotFoundException ex) {}
            catch (IOException ex) {}
    }
    
    /**
     * read and return latest reference number from text file
     * @param br used to read from file
     * @return a String contains latest reference number
     * @exception IOException if error occurs when input and output file
     */
    public static String readLastReferenceNo(BufferedReader br) throws IOException
    {
        String refID = "";
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("No:".trim())) {
                    refID = line.replaceFirst("No:", "");
                }
            }   
        }
        catch(IOException e) {}
        finally {
            if(br != null) {
                br.close();
            }
        }
        return refID.trim();
    }
    
    /**
     * read and return list of Reference objects that contain keyword in title
     * @param keyword word to find Note
     * @param br used to read from file
     * @return list of found References 
     * @exception IOException if error occurs when input and output file
     */
    public static ArrayList<Reference> searchReferences(String keyword, BufferedReader br) throws IOException
    {
        //list number of references is created because file pointer does not read backward
        ArrayList<Reference> resultReferences = new ArrayList<Reference>();
        ArrayList<String> resultReferenceNo = new ArrayList<String>();
        String titleTemp = "";
        String refNoTemp = "";
        boolean foundRefNo = false;
        try {
            String line;
            while((line = br.readLine()) != null) {
                //assign a variable a value of Reference Number
                if(line.startsWith("No:".trim())) {
                    refNoTemp = line.replaceFirst("No:".trim(),"");
                    //read down and file line with Title information
                    while(!line.startsWith("Title:".trim())) {
                        line = br.readLine();
                    }
                    //reach the Title
                    if(line.startsWith("Title:".trim())) {
                        //if title contains keyword, add its Number to the list
                        titleTemp = line.replaceFirst("Title:".trim(), "").toLowerCase();
                        if(titleTemp.contains(keyword.trim().toLowerCase())) {
                            resultReferenceNo.add(refNoTemp);        
                        }
                    }
                }
            }
        }
        catch(IOException e) {}
        finally {
            if(br != null) {
                br.close();
            }
        }
        //read through the file again
        br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("No:".trim())) {
                    foundRefNo = false;
                    //compare each element of found reference numbers to the current Number line
                    for(int i=0; i< resultReferenceNo.size(); i++) {
                        if(line.replaceFirst("No:".trim(), "").equals(resultReferenceNo.get(i))) {
                            foundRefNo = true;
                            break;
                        }
                    }
                    //if the current line is one in matched reference number
                    if(foundRefNo) {
                        line = br.readLine();
                        //read down and create object of appropriate reference type
                        //add these objects to a Reference list
                        if(line.replaceFirst("Type:", "").toLowerCase().equals("book")) {
                            Book bookIns = new Book();
                            Book aBook = bookIns.readABook(br);
                            resultReferences.add(aBook);
                        }
                        if(line.replaceFirst("Type:", "").toLowerCase().equals("conferencepaper")) {
                            ConferencePaper conferencePaperIns = new ConferencePaper();
                            ConferencePaper aConferencePaper = conferencePaperIns.readAConferencePaper(br);
                            resultReferences.add(aConferencePaper);
                        }
                        if(line.replaceFirst("Type:", "").toLowerCase().equals("journal")) {
                            Journal journalIns = new Journal();
                            Journal aJournal = journalIns.readAJournal(br);
                            resultReferences.add(aJournal);
                        }
                        if(line.replaceFirst("Type:", "").toLowerCase().equals("onlinejournal")) {
                            OnlineJournal onlineJournalIns = new OnlineJournal();
                            OnlineJournal anOnlineJournal = onlineJournalIns.readAnOnlineJournal(br);
                            resultReferences.add(anOnlineJournal);
                        }
                        if(line.replaceFirst("Type:", "").toLowerCase().equals("thesis")) {
                            Thesis thesisIns = new Thesis();
                            Thesis aThesis = thesisIns.readAThesis(br);
                            resultReferences.add(aThesis);
                        }
                        if(line.replaceFirst("Type:", "").toLowerCase().equals("webpage")) {
                            WebPage webPageIns = new WebPage();
                            WebPage aWebPage = webPageIns.readAWebPage(br);
                            resultReferences.add(aWebPage);
                        } 
                    }
                }
            }
        }
        catch(IOException e) {}
        finally {
            if(br != null) {
                br.close();
            }
        }   
        return resultReferences;
    }
    
    /**
     * read and return list of data about Notes that contain keyword
     * @param keyword word to find Note
     * @param br used to read from file
     * @return list of data about found Notes
     * @exception IOException if error occurs when input and output file
     */
    public static ArrayList<String> searchReferenceNotes(String keyword, BufferedReader br) throws IOException
    {
       ArrayList<String> noteData = new ArrayList<String>();
       String tempType = "";
       String tempAuthor = "";
       String tempYear = "";
       String tempTitle = "";
       String tempNoteDate = "";
       String tempNoteText = "";
       try {
            String line;
            while((line = br.readLine()) != null) {
                //read and store Reference information that stores the Note to temp variables
                if(line.startsWith("Type:".trim())) {
                    tempType = line.replaceFirst("Type:".trim(), "");
                    while(!line.equals("-----")) {
                        line = br.readLine();
                        if(line.startsWith("Author:".trim())) {
                            tempAuthor = line.replaceFirst("Author:".trim(), "");
                        }
                        if(line.startsWith("Year:".trim())) {
                            tempYear = line.replaceFirst("Year:".trim(), "");
                        }
                        if(line.startsWith("Title:".trim())) {
                            tempTitle = line.replaceFirst("Title:".trim(), "");
                        }    
                        if(line.startsWith("NoteDateCreated:".trim())) {
                            tempNoteDate = line.replaceFirst("NoteDateCreated:".trim(), "");
                            line = br.readLine();
                            tempNoteText = line.replaceFirst("NoteText:".trim(), "");
                            //if keyword is contained in the text of Note, store this Note data and its Reference's key fields to an array of String
                            if(tempNoteText.toLowerCase().contains(keyword.toLowerCase())) {
                                noteData.add(tempNoteDate);
                                noteData.add(tempNoteText);
                                noteData.add(tempType);
                                noteData.add(tempAuthor);
                                noteData.add(tempYear);
                                noteData.add(tempTitle);
                            }
                            line = br.readLine();
                        }
                    }
                }
            }
       }
       catch(IOException e) {}
       finally {
          if(br != null) {
              br.close();
          }
       }
       return noteData;
    }
    
    /**
     * return the BibliographicEntry format 
     * @return a String with bibliographical format
     */
    public String getBibliographicEntry()
    {
        String bibAuthor = "";
        String bibYearCreated = "";
        String bibTitle = "";
        if(!author.trim().equals("")) {
            bibAuthor = author + " ";
        }
        if(!yearCreated.trim().equals("")) {
            bibYearCreated = yearCreated + ", ";
        }
        if(!title.trim().equals("")) {
            bibTitle = title + ", ";
        }
        String bibliographicEntry = bibAuthor + bibYearCreated  + bibTitle; 
        return bibliographicEntry;
    }
    
    /**
     * save all bibliographic entries to text file
     * @param entriesList list of Bibliographic Entries
     * @param pw used to write to file
     */
    public static void saveBiblioToFile(String[] entriesList, PrintWriter pw)
    {
        try {
            for(int i=0; i< entriesList.length; i++) {
                pw.println(entriesList[i]);
            }
        }
        catch(Exception e) {}
        finally {
            if(pw!= null) {
                pw.close();
            }
        }
    }
    
    /**
    * returns the states of reference's attributes 
    * @return a String represents current states of all reference's attributes
    */
    public String toString()
    {
        return " Author: " + author + " Year Created: " + yearCreated + " Title: " + title;
    }
} 
                                

