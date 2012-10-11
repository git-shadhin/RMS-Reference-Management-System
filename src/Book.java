import java.util.*;
import java.io.*;

/**
 * Class used to work with reference of type Book
 * Contains necessary attributes and methods to work with Book
 * Extends from Reference abstract class
 * @author Tin Huynh 
 * @version 2010.10.26
 */
public class Book extends Reference
{
    private String publisher;
    private String seriesTitle;
    private String edition;
    private String placeOfPublic;

    /**
     * Create a Book object
     */
    public Book()
    {
        
    }
    
    /**
     * Create a Book object that calls to method saving data to file
     * @param information collection of all information to be saved
     * @param noteText Text of the new Note
     * @param noteDate DateCreated of the new Note
     * @param bw used to write to file
     * @param br used to read from file
     * @exception IOException if an error occurs when output and input file
     */
    public Book(HashMap information, String noteText, String noteDate, BufferedWriter bw, BufferedReader br) throws IOException
    {
        int refNo = super.saveToFile(information, bw, br);
        saveToFile(refNo, information, noteText, noteDate, bw);
    }
    
    /**
     * Create a Book object
     * and instantiate the states for it
     * call to super class's constructor to instantiate common's attributes
     * @param information collection of all information to be saved
     */
    public Book(HashMap information)
    {
       super(information.get("Author").toString(), information.get("Year").toString(), information.get("Title").toString());
       publisher = information.get("Publisher").toString();
       seriesTitle = information.get("SeriesTitle").toString();
       edition = information.get("Edition").toString();
       placeOfPublic = information.get("PlaceOfPublic").toString();
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
     * return SeriesTitle of a Book
     * @return a String contains SeriesTitle'state
     */
    public String getSeriesTitle()
    {
        return seriesTitle;
    }
    
    /**
     * assign new value for SeriesTitle
     * @param newSeriesTitle new SeriesTitle value
     */
    public void setSeriesTitle(String newSeriesTitle)
    {
        seriesTitle = newSeriesTitle;
    }
    
     /**
     * return Edition of a Book
     * @return a String contains Edition'state
     */
    public String getEdition()
    {
        return edition;
    }
    
    /**
     * assign new value for Edition
     * @param newEdition new Edition value
     */
    public void setEdition(String newEdition)
    {
        edition = newEdition;
    }
    
    /**
     * return Place of Publication of a Book
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
     * save the states of Book to text file
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
            //continue to write Book specialized information to file after common information has been written by superclass
            bw.write("Publisher:" + information.get("Publisher").toString());
            bw.write("\r\n");
            bw.write("SeriesTitle:" + information.get("SeriesTitle").toString());
            bw.write("\r\n");
            bw.write("Edition:" + information.get("Edition").toString());
            bw.write("\r\n");
            bw.write("PlaceOfPublic:" + information.get("PlaceOfPublic").toString());
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
                       pw.println("SeriesTitle:"+ information.get("SeriesTitle").toString());
                       line = br.readLine();
                       pw.println("Edition:"+ information.get("Edition").toString());
                       line = br.readLine();
                       pw.println("PlaceOfPublic:"+ information.get("PlaceOfPublic").toString());
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
     * match the reference number from Project data file and lookup that Book in Reference data file
     * @param conNo number of the Book
     * @param filePath path of the text file
     * @return the found Conference Paper object
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public Book readByNo(String bookNo, String filePath) throws IOException
    {
       HashMap information =new HashMap();
       BufferedReader br = new BufferedReader(new FileReader(filePath));
       try {
            String line;
            while((line = br.readLine()) != null) {
                //if the referece number requested is found
                if(line.replaceFirst("No:".trim(),"").equals(bookNo)) {
                    //put all read information to a HashMap 
                    information.put("Type", br.readLine().replaceFirst("Type:".trim(),""));
                    information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
                    information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
                    information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
                    information.put("Publisher", br.readLine().replaceFirst("Publisher:".trim(),""));
                    information.put("SeriesTitle", br.readLine().replaceFirst("SeriesTitle:".trim(),""));
                    information.put("Edition", br.readLine().replaceFirst("Edition:".trim(),""));
                    information.put("PlaceOfPublic", br.readLine().replaceFirst("PlaceOfPublic:".trim(),""));
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
       //create a new object of Book with information provided for attributes
       Book myBook = new Book(information);
       return myBook;
    }
    
    /**
     * read book states from text file and instantiate the attributes
     * finally return the Book itself
     * @param br used to read from text file
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public Book readABook(BufferedReader br) throws IOException
    {
       String line;
       HashMap information =new HashMap();
       Note aNote = null;     
       Book myBook = null;
       try {     
             //read information from text file and create new Book object with these information
             information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
             information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
             information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
             information.put("Publisher", br.readLine().replaceFirst("Publisher:".trim(),""));
             information.put("SeriesTitle", br.readLine().replaceFirst("SeriesTitle:".trim(),""));
             information.put("Edition", br.readLine().replaceFirst("Edition:".trim(),""));
             information.put("PlaceOfPublic", br.readLine().replaceFirst("PlaceOfPublic:".trim(),""));
             myBook = new Book(information);   
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
                      super.addNote(myBook.myNotes, aNote);
                  }  
             }
       }
       catch(FileNotFoundException e) {}
       catch(IOException e) {}
       return myBook;
    }
    
    /**
     * return the BibliographicEntry format 
     * @return a String with bibliographical format
     */
    public String getBibliographicEntry()
    {
        String bibSeriesTitle = "";
        String bibEdition = "";
        String bibPublisher = "";
        String bibPlaceOfPublic = "";
        
        if(!seriesTitle.trim().equals("")) {
            bibSeriesTitle = seriesTitle + ", ";
        }
        if(!edition.trim().equals("")) {
            bibEdition = edition + " edn, ";
        }
        if(!publisher.trim().equals("")) {
            bibPublisher = publisher + ", ";
        }
        if(!placeOfPublic.trim().equals("")) {
            bibPlaceOfPublic = placeOfPublic;
        }
        String  bibliographicEntry = super.getBibliographicEntry();
        bibliographicEntry += bibSeriesTitle  + bibEdition  + bibPublisher + bibPlaceOfPublic + ".";  
        return bibliographicEntry;
    }
    
    /**
    * returns the states of book's attributes 
    * @return a String represents current states of all conference paper's attributes
    */
    public String toString()
    {
       String myString; 
       myString = super.toString();
       myString += " Publisher: " +publisher+ " Series Title: " +seriesTitle+ " Edition: " +edition+ " Place of Public: " +placeOfPublic;
       return myString; 
    }
}
