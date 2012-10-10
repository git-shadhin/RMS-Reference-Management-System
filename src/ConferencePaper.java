import java.util.*;
import java.io.*;

/**
 * Class used to work with reference of type ConferencePaper
 * Contains necessary attributes and methods to work with ConferencePaper object
 * Extends from Reference abstract class
 * @author Tin Huynh 
 * @version 2010.10.26
 */
public class ConferencePaper extends Reference
{
    private String publisher;
    private String placeOfPublic;
    private String placeHeld;
    private String timeHeld;
    private String pageNumber;

    /**
     * Create a conference paper object
     */
    public ConferencePaper()
    {
     
    }

    /**
     * Create a conference paper object that calls to method saving data to file
     * @param information collection of all information to be saved
     * @param noteText Text of the new Note
     * @param noteDate DateCreated of the new Note
     * @param bw used to write to file
     * @param br used to read from file
     * @exception IOException if an error occurs when output and input file
     */
    public ConferencePaper(HashMap information, String noteText, String noteDate, BufferedWriter bw, BufferedReader br) throws IOException
    {
        int refNo = super.saveToFile(information, bw, br);
        saveToFile(refNo, information, noteText, noteDate, bw);
    }
    
    /**
     * Create a conference paper object
     * and instantiate the states for it
     * call to super class's constructor to instantiate common's attributes
     * @param information collection of all information to be saved
     */
    public ConferencePaper(HashMap information)
    {
       super(information.get("Author").toString(), information.get("Year").toString(), information.get("Title").toString());
       publisher = information.get("Publisher").toString();
       placeOfPublic = information.get("PlaceOfPublic").toString();
       placeHeld = information.get("PlaceHeld").toString();
       timeHeld = information.get("TimeHeld").toString();
       pageNumber = information.get("PageNumber").toString();
    }
    
    /**
     * return Publisher of a conference paper
     * @return a String contains Publisher'state
     */
    public String getPublisher()
    {
        return publisher;
    }
    
    /**
     * assign new value for Publisher
     * @param newPublisher new Publisher value
     */
    public void setPublisher(String newPublisher)
    {
        publisher = newPublisher;
    }
    
    /**
     * return Place of Publication of a conference paper
     * @return a String contains Place of Publication'state
     */
    public String getPlaceOfPublic()
    {
        return placeOfPublic;
    }
    
    /**
     * assign new value for Place of Publication
     * @param newPlaceOfPublic new Place of Publication value
     */
    public void setPlaceOfPublic(String newPlaceOfPublic)
    {
        placeOfPublic = newPlaceOfPublic;
    }
    
    /**
     * assign new value for PlaceHeld
     * @param newPlaceHeld new PlaceHeld value
     */
    public void setPlaceHeld(String newPlaceHeld)
    {
        placeHeld = newPlaceHeld;
    }
    
    /**
     * return Place Held of a conference paper
     * @return a String contains Place Held'state
     */
    public String getPlaceHeld()
    {
        return placeHeld;
    }
    
    /**
     * assign new value for TimeHeld
     * @param newTimeHeld new TimeHeld value
     */
    public void setTimeHeld(String newTimeHeld)
    {
        timeHeld = newTimeHeld;
    }
    
    /**
     * return Time Held of a conference paper
     * @return a String contains Time Held'state
     */
    public String getTimeHeld()
    {
        return timeHeld;
    }
    
    /**
     * assign new value for PageNumber
     * @param newPageNumber new PageNumber value
     */
    public void setPageNumber(String newPageNumber)
    {
        pageNumber = newPageNumber;
    }
    
    /**
     * return PageNumber of a conference paper
     * @return a String contains PageNumber'state
     */
    public String getPageNumber()
    {
        return pageNumber;
    }
    
    /**
     * save the states of conference paper to text file
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
            //continue to write ConferencePaper specialized information to file after common information has been written by superclass
            bw.write("Publisher:" + information.get("Publisher").toString());
            bw.write("\r\n");
            bw.write("PlaceOfPublic:" + information.get("PlaceOfPublic").toString());
            bw.write("\r\n");
            bw.write("PlaceHeld:" + information.get("PlaceHeld").toString());
            bw.write("\r\n");
            bw.write("TimeHeld:" + information.get("TimeHeld").toString());
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
            if(bw != null)
                bw.close();
        }
    }
    
    /**
     * read and modify reference information to text file
     * @param currentAuthor current author of editing conference paper
     * @param currentYear current year created of editing conference paper
     * @param currentTitle current title of editing conference paper
     * @param information editing information of conference paper
     * @param br used to read from file
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public void editToFile(String currentAuthor, String currentYear, String currentTitle, HashMap information, BufferedReader br) throws IOException
    {
            //call super class method to edit common attributes
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
                       pw.println("Publisher:"+ information.get("Publisher").toString());
                       line = br.readLine();
                       pw.println("PlaceOfPublic:"+ information.get("PlaceOfPublic").toString());
                       line = br.readLine();
                       pw.println("PlaceHeld:"+ information.get("PlaceHeld").toString());
                       line = br.readLine();
                       pw.println("TimeHeld:"+ information.get("TimeHeld").toString());
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
     * match the reference number from Project data file and lookup that conference paper in Reference data file
     * @param conNo number of the reference
     * @param filePath path of the text file
     * @return the found Conference Paper object
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public ConferencePaper readByNo(String conNo, String filePath) throws IOException
    {
       HashMap information =new HashMap();
       BufferedReader br = new BufferedReader(new FileReader(filePath));
       try {
            String line;
            while((line = br.readLine()) != null) {
                //if the referece number requested is found
                if(line.replaceFirst("No:".trim(),"").equals(conNo)) {
                    //put all read information to a HashMap 
                    information.put("Type", br.readLine().replaceFirst("Type:".trim(),""));
                    information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
                    information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
                    information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
                    information.put("Publisher", br.readLine().replaceFirst("Publisher:".trim(),""));
                    information.put("PlaceOfPublic", br.readLine().replaceFirst("PlaceOfPublic:".trim(),""));
                    information.put("PlaceHeld", br.readLine().replaceFirst("PlaceHeld:".trim(),""));
                    information.put("TimeHeld", br.readLine().replaceFirst("TimeHeld:".trim(),""));
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
       //create a new object of ConferencePaper with information provided for attributes
       ConferencePaper myConferencePaper = new ConferencePaper(information);
       return myConferencePaper;
    }
    
    /**
     * read conference paper states from text file and instantiate the attributes
     * finally return the ConferencePaper itself
     * @param br used to read from text file
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public ConferencePaper readAConferencePaper(BufferedReader br) throws IOException
    {
       String line; 
       HashMap information =new HashMap();
       Note aNote = null;     
       ConferencePaper myConferencePaper = null;
       try {     
             //read information from text file and create new ConferencePaper object with these information
             information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
             information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
             information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
             information.put("Publisher", br.readLine().replaceFirst("Publisher:".trim(),""));
             information.put("PlaceOfPublic", br.readLine().replaceFirst("PlaceOfPublic:".trim(),""));
             information.put("PlaceHeld", br.readLine().replaceFirst("PlaceHeld:".trim(),""));
             information.put("TimeHeld", br.readLine().replaceFirst("TimeHeld:".trim(),""));
             information.put("PageNumber", br.readLine().replaceFirst("PageNumber:".trim(),""));
             myConferencePaper = new ConferencePaper(information);  
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
                      super.addNote(myConferencePaper.myNotes, aNote);
                  }  
             }
       }
       catch(FileNotFoundException e) {}
       catch(IOException e) {}
       return myConferencePaper;
    }
    
    /**
     * return the BibliographicEntry format 
     * @return a String with bibliographical format
     */
    public String getBibliographicEntry()
    {
        String bibPublisher = "";
        String bibPlaceOfPublic = "";
        String bibPlaceHeld = "";
        String bibTimeHeld = "";
        String bibPageNumber = ""; 
        if(!publisher.trim().equals("")) {
            bibPublisher = publisher + ", ";
        }
        if(!placeOfPublic.trim().equals("")) {
            bibPlaceOfPublic = placeOfPublic + ", ";
        }
        if(!placeHeld.trim().equals("")) {
            bibPlaceHeld = placeHeld + ", ";
        }
        if(!timeHeld.trim().equals("")) {
            bibTimeHeld = timeHeld + ", ";
        }
        if(!pageNumber.trim().equals("")) {
            bibPageNumber = "pp. " + pageNumber;
        }
        String  bibliographicEntry = super.getBibliographicEntry();
        bibliographicEntry += bibPlaceHeld  + bibTimeHeld  + bibPublisher + bibPlaceOfPublic + bibPageNumber + ".";  
        return bibliographicEntry;
    }
   
    /**
    * returns the states of conference paper's attributes 
    * @return a String represents current states of all conference paper's attributes
    */
    public String toString()
    {
       String myString; 
       myString = super.toString();
       myString += " Publisher: " +publisher+ " Place Held: " +placeHeld+ " Time Held: " +timeHeld+ " Place of Public: " +placeOfPublic + " Page number: " + pageNumber;
       return myString; 
    }
}
