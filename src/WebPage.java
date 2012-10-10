import java.util.*;
import java.io.*;

/**
 * Class used to work with reference of type WebPage
 * Contains necessary attributes and methods to work with WebPage object
 * Extends from Reference abstract class
 * @author Tin Huynh 
 * @version 2010.10.26
 */
public class WebPage extends Reference
{
    
    private String groupHosting;
    private String dateViewed;
    private String url;

    /**
     * Create a WebPage object
     */
    public WebPage()
    {

    }
    
    /**
     * Create a WebPage object that calls to method saving data to file
     * @param information collection of all information to be saved
     * @param noteText Text of the new Note
     * @param noteDate DateCreated of the new Note
     * @param bw used to write to file
     * @param br used to read from file
     * @exception IOException if an error occurs when output and input file
     */
    public WebPage(HashMap information, String noteText, String noteDate, BufferedWriter bw, BufferedReader br) throws IOException
    {
        int refNo = super.saveToFile(information, bw, br);
        saveToFile(refNo, information, noteText, noteDate, bw);
    }
    
    /**
     * Create a WebPage object
     * and instantiate the states for it
     * call to super class's constructor to instantiate common's attributes
     * @param information collection of all information to be saved
     */
    public WebPage(HashMap information)
    {
       super(information.get("Author").toString(), information.get("Year").toString(), information.get("Title").toString());
       groupHosting = information.get("GroupHosting").toString();
       dateViewed = information.get("DateViewed").toString();
       url = information.get("URL").toString();
    }
    
    /**
     * return GroupHosting of a WebPage
     * @return a String contains GroupHosting'state
     */
    public String getGroupHosting()
    {
        return groupHosting;
    }
    
    /**
     * assign new value for GroupHosting
     * @param newGroupHosting new GroupHosting value
     */
    public void setGroupHosting(String newGroupHosting)
    {
        groupHosting = newGroupHosting;
    }
    
    /**
     * return First viewed date of a WebPage
     * @return a String contains First viewed date'state
     */
    public String getDateViewed()
    {
        return dateViewed;
    }
    
    /**
     * assign new value for First viewed date
     * @param newDateViewed new First viewed date value
     */
    public void setDateViewed(String newDateViewed)
    {
        dateViewed = newDateViewed;
    }
    
    /**
     * return URL of a WebPage
     * @return a String contains URL'state
     */
    public String getURL()
    {
        return url;
    }
    
    /**
     * assign new value for URL
     * @param newURL new URL value
     */
    public void setURL(String newURL)
    {
        url = newURL;
    }
    
    /**
     * save the states of WebPage to text file
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
            bw.write("GroupHosting:" + information.get("GroupHosting").toString());
            bw.write("\r\n");
            bw.write("DateViewed:" + information.get("DateViewed").toString());
            bw.write("\r\n");
            bw.write("URL:" + information.get("URL").toString());
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
     * @param currentAuthor current author of editing WebPage
     * @param currentYear current year created of editing WebPage
     * @param currentTitle current title of editing WebPage
     * @param information editing information of WebPage
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
                       pw.println("GroupHosting:"+ information.get("GroupHosting").toString());
                       line = br.readLine();
                       pw.println("DateViewed:"+ information.get("DateViewed").toString());
                       line = br.readLine();
                       pw.println("URL:"+ information.get("URL").toString());
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
     * @param webPageNo number of the reference
     * @param filePath path of the text file
     * @return the found WebPage object
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public WebPage readByNo(String webPageNo, String filePath) throws IOException
    {
       HashMap information =new HashMap();
       BufferedReader br = new BufferedReader(new FileReader(filePath));
       try {
            String line;
            while((line = br.readLine()) != null) {
                //if the referece number requested is found
                if(line.replaceFirst("No:".trim(),"").equals(webPageNo)) {
                    information.put("Type", br.readLine().replaceFirst("Type:".trim(),""));
                    information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
                    information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
                    information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
                    information.put("GroupHosting", br.readLine().replaceFirst("GroupHosting:".trim(),""));
                    information.put("DateViewed", br.readLine().replaceFirst("DateViewed:".trim(),""));
                    information.put("URL", br.readLine().replaceFirst("URL:".trim(),""));
                }
            }
       }
       catch(FileNotFoundException e) {}
       catch(IOException e) {}
       finally{
            if(br != null) {
               br.close();
            }
       }
       WebPage myWebPage = new WebPage(information);
       return myWebPage;
    }
    
    /**
     * read WebPage states from text file and instantiate the attributes
     * finally return the WebPage itself
     * @param br used to read from text file
     * @exception IOException if error occurs when input and output file
     * @exception FileNotFoundException if the file cannot be found
     */
    public WebPage readAWebPage(BufferedReader br) throws IOException
    {
       String line;
       HashMap information =new HashMap();
       Note aNote = null;     
       WebPage myWebPage = null;
       try {  
             //read information from text file and create new WebPage object with these information
             information.put("Author", br.readLine().replaceFirst("Author:".trim(),""));
             information.put("Year", br.readLine().replaceFirst("Year:".trim(),""));
             information.put("Title", br.readLine().replaceFirst("Title:".trim(),""));
             information.put("GroupHosting", br.readLine().replaceFirst("GroupHosting:".trim(),""));
             information.put("DateViewed", br.readLine().replaceFirst("DateViewed:".trim(),""));
             information.put("URL", br.readLine().replaceFirst("URL:".trim(),""));
             myWebPage = new WebPage(information);  
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
                      super.addNote(myWebPage.myNotes, aNote);
                  }  
             }
       }
       catch(FileNotFoundException e) {}
       catch(IOException e) {}
       return myWebPage;
    }
    
    /**
     * return the BibliographicEntry format 
     * @return a String with bibliographical format
     */
    public String getBibliographicEntry()
    {
        String bibGroupHosting = "";
        String bibDateViewed = "";
        String bibURL = ""; 
        if(!groupHosting.trim().equals("")) {
            bibGroupHosting = groupHosting + ", ";
        }
        if(!dateViewed.trim().equals("")) {
            bibDateViewed = "viewed " + dateViewed + ", ";
        }
        if(!url.trim().equals("")) {
            bibURL = "<" + url + ">";
        }
        String  bibliographicEntry = super.getBibliographicEntry();
        bibliographicEntry += bibGroupHosting  + bibDateViewed + bibURL + ".";  
        return bibliographicEntry;
    }
   
    /**
    * returns the states of WebPage's attributes 
    * @return a String represents current states of all WebPage's attributes
    */
    public String toString()
    {
       String myString; 
       myString = super.toString();
       myString += " Group Hosting: " +groupHosting+ " Date Viewed: " +dateViewed+ " URL: " +url;
       return myString; 
    }
}
