import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.io.*;
import javax.swing.table.*;

/**
 * A class to manage project for the program.
 * Contain all the attributes and methods necessarry that are needed to work with project
 * Also work with objects contain inside a project (references and notes)
 * 
 * @author Tin Huynh
 * @version 2010.10.26
 */
public class Project implements hasNotes
{
    protected  String name;
    protected  String briefDesc;
    private static final String PROJECT_FILE_NAME = "data/Project.txt";
    private static final String PROJECT_FILE_TEMP = "data/ProjectTemp.txt";
    private static final String REFERENCE_FILE_NAME = "data/Reference.txt";
    protected ArrayList<Reference> myReferences;
    protected ArrayList<Note> myNotes;

    /**
     * Creates a project and use this project to save the project' states to text file 
     * Used for saving project
     */
    public Project(String newName, String newBriefDesc, BufferedWriter bw, BufferedReader br, ArrayList<String> refList, String noteText, String noteDate) throws IOException
    {
        name = newName;
        briefDesc = newBriefDesc;
        //store data of a new project to a file after its initialization
        saveToFile(newName, newBriefDesc, bw, br, refList, noteText, noteDate);
    }
    
    /** 
     * Creates a project and instantiate its attributes
     */
    public Project(String newName, String newBriefDesc) throws IOException
    {
        name = newName;
        briefDesc = newBriefDesc;
    }
    
    /**
     * Creates a project
     * 
     */
    public Project()
    {
       
    }
    
    /**
     * Creates a project and use this project to load the states from the text file
     * Used for loading project
     */
    public Project(String projName, BufferedReader br) throws IOException
    {
        Project projIns = readProjectByName(projName, br);
        name = projIns.getName();
        briefDesc = projIns.getDescription();
        myReferences = projIns.myReferences;
        myNotes = projIns.myNotes;
    }
    
    /**
     * return the value of project name 
     * @return name of the project
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * set new value for peoject name
     * @param newName project name
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**
     * return the value of project's brief description 
     * @return brief description of the project
     */
    public String getDescription()
    {
        return briefDesc;
    }
    
    /**
     * set new description for project brief description
     * @param newBriefDesc project's brief description
     */
    public void setDescription(String newBriefDesc)
    {
        briefDesc = newBriefDesc;
    }
    
    /**
     * return the number of Reference objects in myReferences ArrayList 
     * @return number of Reference objects 
     */
    public int noOfReference()
    {
        return myReferences.size();
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
        if(noteNumber > 0 && noteNumber <= noOfNote()) {
            myNote = myNotes.get(noteNumber-1);       
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
     * Save append new project'states to text file.
     * @param newName name of the project
     * @param newBriefDesc brief description of the project
     * @param bw used to write to text file
     * @param br used to read from text file
     * @param refList list of reference numbers to be added to the project
     * @param noteText content of the Note to be added to the project
     * @param noteDate date created of the Note to be added to the project
     * @exception IOException  If an input or output exception occurred
     * @exception FileNotFoundException  If the text file cannot be found
     * @exception NumberFormatException If the found Project Number cannot be parsed to an Integer
     */
    public static void saveToFile(String newName, String newBriefDesc, BufferedWriter bw, BufferedReader br, ArrayList<String> refList, String noteText, String noteDate) throws IOException
    {
        String projNo;
        int projNoInt;
        try {
            //call method to return latest project number
            projNo = readLastProjectNo(br);
            if(projNo.equals("")) { 
                //no project in file yet
                projNoInt = 1;
            }
            else {
                projNoInt = Integer.parseInt(projNo) +1;
            }
            //write states of Project to file 
            bw.write("No:" + projNoInt);
            bw.write("\r\n");
            bw.write("Name:" + newName);
            bw.write("\r\n");
            bw.write("Brief:" + newBriefDesc);
            bw.write("\r\n");
            //write delimiter of Reference number list
            bw.write("Proj" + projNoInt +"RefNoList");
            bw.write("\r\n");
            //write all Reference numbers
            for(int i=0; i< refList.size(); i++) {
                bw.write(refList.get(i));
                bw.write("\r\n");
            }
            //write delimiter of Note list
            bw.write("Proj" + projNoInt +"NoteList");
            bw.write("\r\n");
            //if Note Text is not null, write new Note states under the current project
            if(!noteText.equals(""))
            {
                bw.write("NoteNo:1");
                bw.write("\r\n");
                bw.write("NoteDateCreated:" + noteDate);
                bw.write("\r\n");
                bw.write("NoteText:" + noteText);
                bw.write("\r\n");
            }
            //write delimiter for the whole project, end the project
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
     * Rewrite list of reference numbers in a specified project
     * @param requestProjName name of the project that has references being updated
     * @param br used to read from text file
     * @param refList list of new reference numbers to be updated to the project
     * @exception IOException  If an input or output exception occurred
     * @exception FileNotFoundException  If the text file cannot be found
     */
    public static void rewriteRefList(String requestProjName, BufferedReader br, ArrayList<String> refList) throws IOException
    {
        String projNo = "";
        try {
            //call method to find Project Number with supplied name
            projNo = findProjectNo(requestProjName, br);
        }
        catch(FileNotFoundException e) {}
        finally{
            if(br != null)
               br.close();
        }
        //if Project Number is found
        if(!projNo.equals("")) {
            try  {
                //Create a new temp file
                File tempFile = new File(PROJECT_FILE_TEMP);
                File originalFile = new File(PROJECT_FILE_NAME);
                tempFile.createNewFile();
                
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                BufferedReader newFilebr = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                String line;
                
                //Read from the original file and write to the new file
                //unless content matches data to be removed.
                while((line = newFilebr.readLine()) != null) {
                   pw.println(line);
                   //if file pointer is under the requested project and reach the Reference List
                   if(line.equals(("Proj"+ projNo +"RefNoList").trim())) {
                      //write the new reference list under the selected project
                      for(int i=0; i< refList.size(); i++) {
                          pw.println(refList.get(i));
                      }
                      //until we reach the Note delimeter, do not write any of the old Reference list
                      while(!line.equals(("Proj"+ projNo +"NoteList").trim())) {
                        //read on, do not write the old reference list
                        line = newFilebr.readLine();
                      }
                      //write the delimeter text for Note
                      pw.println("Proj"+ projNo +"NoteList");
                   }
                } 
                pw.close();
                newFilebr.close();       
                //delete the original file
                originalFile.delete();
                //rename the temp file to the name of original file
                tempFile.renameTo(originalFile);
            }      
            catch (FileNotFoundException ex) {}
            catch (IOException ex) {}
        }
    }
    
    /**
     * Save the Note'states of the project to text file
     * Class Project will be responsible for writing its note to text file
     * because note'states information resides in the project text file
     * @param projName name of the project that has note being saved
     * @param noteText content of the Note to be added to the project
     * @param dateCreated date created of the Note to be added to the project
     * @param br used to read from text file
     * @exception IOException  If an input or output exception occurred
     * @exception FileNotFoundException  If the text file cannot be found
     * @exception NumberFormatException If the found Note Number cannot be parsed to an Integer
     */
    public static void saveNoteToFile(String projName, String noteText, String dateCreated, BufferedReader br) throws IOException
    {
        boolean foundProject = false;
        String noteNo;
        int noteNoInt;
        try {
            //call method to find Project with supplied name
            foundProject = findProjectName(projName, br);
        }
        catch(FileNotFoundException e) {}
        finally{
            if(br != null)
               br.close();
        }
        //if the project is found
        if(foundProject) {
            try {
                br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                //call method to read lastest Note number under this Project
                noteNo = readLastNoteNo(projName, br);
                if(noteNo.equals("")) {
                    //no Note in file yet
                    noteNoInt = 1;
                }
                else {
                    noteNoInt = Integer.parseInt(noteNo) +1;
                }
                File tempFile = new File(PROJECT_FILE_TEMP);
                File originalFile = new File(PROJECT_FILE_NAME);
                //Create a new temp file
                tempFile.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                String line;
                while((line = br.readLine()) != null) {
                   //If file pointer has reached the Project to be added new Note
                   if(line.equals("Name:".trim() + projName)) {
                        pw.println(line);
                        line = br.readLine();
                        //while delimiter is not reached, write to temp file line from original file
                        while(!line.equals("-----".trim())) {
                            pw.println(line);        
                            line = br.readLine();
                        }
                        //if file pointer has reached the delimiter, before writing it write new Note states to temp file
                        if(line.equals("-----".trim())) {
                            pw.println("NoteNo:" + noteNoInt);  
                            pw.println("NoteDateCreated:" + dateCreated);                 
                            pw.println("NoteText:" + noteText);  
                            //finally write delimiter
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
            }
            catch(FileNotFoundException e) {}
            catch(IOException e) {}
            catch(NumberFormatException e) {}
        }
    }
    
    /**
     * Check whether a Note with the same name and date already exists
     * in the requested project
     * @param projectName name of the project that has note being checked
     * @param newDate date created of the Note to be checked
     * @param newText content of the Note to be checked
     * @param br used to read from text file
     * @return a boolean that states whether the Note is existed or not
     * @exception IOException  If an input or output exception occurred
     */
    public boolean checkExistedNote(String projectName, String newDate, String newText, BufferedReader br) throws IOException
    {
        boolean existedNote = false;
        try {
            String line;
            while((line = br.readLine()) != null) {
                //if file pointer reaches the requested Project
                if(line.replaceFirst("Name:", "").equals(projectName)) {
                    //read line until delimiter is reached
                    while(!line.equals("-----".trim())) {
                        line = br.readLine();
                        //if DateCreated of a Note matches
                        if(line.replaceFirst("NoteDateCreated:", "").equals(newDate)) {
                            line = br.readLine();
                            //and Text is also matches
                            if(line.replaceFirst("NoteText:", "").equals(newText)) {
                                //the Note exists, exit the loop
                                existedNote = true;
                                break;
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
     * Make a copy of the text file, check and write new Note states 
     * to the position of the requested Note in the old text file
     * Rename the copy file and delete the original file
     * @param projName name of the project that has note being edited
     * @param currentDate current DateCreated of the Note being edited
     * @param currentText current Text of the Note being edited
     * @param newDate new DateCreated of the Note being edited
     * @param newText new Text of the Note being edited
     * @param br used to read from text file
     * @exception IOException  If an input or output exception occurred
     * @exception FileNotFoundException  If the text file cannot be found
     */
    public void editNoteToFile(String projName, String currentDate, String currentText, String newDate, String newText, BufferedReader br) throws IOException
    {
        boolean foundProject = false;
        String noteNo;
        int noteNoInt;
        try {
            //call method to find Project with supplied name
            foundProject = findProjectName(projName, br);
        }
        catch(FileNotFoundException e) {}
        finally{
            if(br != null)
               br.close();
        }
        //if the project is found
        if(foundProject) {
            try {
                File tempFile = new File(PROJECT_FILE_TEMP);
                File originalFile = new File(PROJECT_FILE_NAME);
                //Create a new temp file
                tempFile.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                String line; 
                while((line = br.readLine()) != null) {
                   //If file pointer has reached the Project that has the Note to be edited
                   if(line.equals("Name:".trim() + projName)) {
                        pw.println(line);
                        line = br.readLine();
                        //while delimiter is not reached
                        while(!line.equals("-----".trim())) {
                            //if the Note to be edited has been found, write the modified information
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
                            //else write to temp file line from original file
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
                br.close();       
                pw.close();
                //delete the original file
                originalFile.delete();
                //rename the temp file to the name of original file
                tempFile.renameTo(originalFile);
                   
            }catch(FileNotFoundException e) {}
            catch(IOException e) {}
        }
    }
    
    /**
     * Check to ensure the editing Note does not change its DateCreated and Text to a same combination
     * of DateCreated and Text of any other Note in a same project
     * Rename the copy file and delete the original file
     * @param projName name of the project that has note being edited
     * @param oldNoteDate current DateCreated of the Note being edited
     * @param oldNoteText current Text of the Note being edited
     * @param newNoteDate new DateCreated of the Note being edited
     * @param newNoteText new Text of the Note being edited
     * @param br used to read from text file
     * @return a boolean that states whether the new DateCreated and Text are duplicated
     * @exception IOException  If an input or output exception occurred
     */
    public static boolean validateEditNote(String projName, String oldNoteDate, String oldNoteText, String newNoteDate, String newNoteText, BufferedReader br) throws IOException
    {
        boolean existedNote = false;
        String noteDate = "";
        String noteText = "";
        try {
            String line;
            while((line = br.readLine()) != null) {
                //if the Project requested is reached
                if(line.replaceFirst("Name:".trim(), "").equals(projName)) {
                    //read on while delimiter is not reached
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
        catch(IOException e) {}
        return existedNote;
    }
    
    /**
     * Make a copy of the text file, check and rewrite 
     * except lines contain information about the Note to be deleted
     * Rename the copy file and delete the original file
     * @param projName name of the project that has note being deleted
     * @param currentDate current DateCreated of the Note being deleted
     * @param currentText current Text of the Note being deleted
     * @param br used to read from text file
     * @exception IOException  If an input or output exception occurred
     * @exception FileNotFoundException  If the text file cannot be found
     */
    public void deleteNoteFromFile(String projName, String currentDate, String currentText, BufferedReader br) throws IOException
    {
        boolean foundProject = false;
        boolean foundDeleteNote = false;
        String noteNoToDelete = "";
        try {
            //find the Project that has Note to be deleted 
            foundProject = findProjectName(projName, br);
        }
        catch(FileNotFoundException e) {}
        finally{
            if(br != null)
               br.close();
        }
        //Project found
        if(foundProject) {
            try {
                File tempFile = new File(PROJECT_FILE_TEMP);
                File originalFile = new File(PROJECT_FILE_NAME);
                //create new temp file
                tempFile.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                String line;
                while((line = br.readLine()) != null) {
                   //file pointer reaches the found project
                   if(line.equals("Name:".trim() + projName)) {
                        pw.println(line);
                        line = br.readLine();
                        //while delimiter is not found
                        while(!line.equals("-----".trim())) {
                            //if line is a Note number line and the matching combination of Note is not found
                            //assign Note number to variable
                            if(line.startsWith("NoteNo:".trim()) && !foundDeleteNote) {
                                noteNoToDelete = line;         
                            }
                            //write nothing to temp file if Note to delete is found 
                            if(line.equals("NoteDateCreated:" + currentDate)) {
                                String tempDate = line;
                                line =br.readLine();
                                if(line.equals("NoteText:" + currentText)) {
                                    foundDeleteNote = true;
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
                br.close();       
                pw.close();
                //delete original file
                originalFile.delete();
                //rename temp file to original file
                tempFile.renameTo(originalFile);
                //make the same process again, this time write nothing if deleted note number is found
                tempFile = new File(PROJECT_FILE_TEMP);
                originalFile = new File(PROJECT_FILE_NAME);
                tempFile.createNewFile();
                pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                
                while((line = br.readLine()) != null) {
                   if(line.equals("Name:".trim() + projName)) {
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
                pw.close();
                br.close();
                
                originalFile.delete();
                tempFile.renameTo(originalFile); 
            }
            catch(FileNotFoundException e) {}
            catch(IOException e) {}
        }
    }
    
    /**
     * Make a copy of the text file, check and rewrite 
     * except lines contain information about the Project to be deleted
     * Rename the copy file and delete the original file
     * @param projName name of the project being deleted
     * @param br used to read from text file
     * @exception IOException  If an input or output exception occurred
     * @exception FileNotFoundException  If the text file cannot be found
     */
    public void deleteProjectFromFile(String projName, BufferedReader br) throws IOException
    {
        boolean found = false;
        boolean foundDeleteName = false;
        String projNoToDelete="";
        try {
            //find the Project to be deleted
            found = findProjectName(projName, br);
        }
        catch(FileNotFoundException e) {}
        finally{
            if(br != null)
               br.close();
        }
        if(found) {
            try  {
                File tempFile = new File(PROJECT_FILE_TEMP);
                File originalFile = new File(PROJECT_FILE_NAME);
                //create a new temp file
                tempFile.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                String line;
                while((line = br.readLine()) != null) {
                    //if line is a Project number line and requested Project name is not found
                    //assign Project number to variable
                   if(line.startsWith("No:".trim()) && !foundDeleteName) {
                        projNoToDelete = line;         
                   }
                   //id the Project name is found, write nothing to new file 
                   //until pass the delimiter
                   if(line.equals("Name:".trim() + projName)) {
                      foundDeleteName = true;
                      while(!line.equals("-----".trim())) {
                        line = br.readLine();    
                      }
                   }
                   else {
                        pw.println(line);
                        pw.flush();  
                   }
                } 
                br.close();       
                pw.close();
                //delete the original file
                originalFile.delete();
                //rename temp file to original file
                tempFile.renameTo(originalFile);
                //make the same process again, this time write nothing if number of deleted project is found
                tempFile = new File(PROJECT_FILE_TEMP);
                originalFile = new File(PROJECT_FILE_NAME);
                tempFile.createNewFile();
                pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                
                while((line = br.readLine()) != null) {
                    if(line.equals(projNoToDelete)) {
                        continue;
                    }
                    else {
                        pw.println(line);
                        pw.flush();  
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
    }
    
    /**
     * Make a copy of the text file, check and write 
     * new lines at original lines contain information about the Project to be edited
     * Rename the copy file and delete the original file
     * @param oldName current name of the project being edited
     * @param newName new name of the project being edited
     * @param newDescription new description of the project being edited
     * @param br used to read from text file
     * @exception IOException  If an input or output exception occurred
     * @exception FileNotFoundException  If the text file cannot be found
     */
    public void editProjectToFile(String oldName, String newName, String newDescription, BufferedReader br) throws IOException
    {
        boolean found = false;
        boolean foundExistedName = false;
        try {
            //find the Project to be edited
            found = findProjectName(oldName, br);
        }
        catch(FileNotFoundException e) {}
        finally {
            if(br != null)
               br.close();
        }
        //Project found
        if(found) {
            try {
                File tempFile = new File(PROJECT_FILE_TEMP);
                File originalFile = new File(PROJECT_FILE_NAME);
                //create a new temp file
                tempFile.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                String line;
                while((line = br.readLine()) != null) {   
                   //if file reader of original file has reached the Project name to be edited
                   if(line.equals("Name:".trim() + oldName)) {
                      //write new information to temp file instead of old information
                      pw.println("Name:" + newName);
                      line = br.readLine();
                      pw.println("Brief:" + newDescription);
                   }
                   //else write to temp file line read from original file
                   else {
                      pw.println(line);
                   }
                } 
                br.close();       
                pw.close();
                //delete original file
                originalFile.delete();
                //rename temp file name to original file name
                tempFile.renameTo(originalFile);
            }      
            catch (FileNotFoundException ex) {}
            catch (IOException ex) {}
        }
    }
    
    /**
     * Check to ensure the editing Project does not change its Name 
     * to a Name of any other existing project
     * @param oldName current name of the project that is being edited
     * @param newName new name of the project that is being edited
     * @param br used to read from text file
     * @return a boolean that states whether the new name duplicates with another projects' names
     * @exception IOException  If an input or output exception occurred
     */
    public static boolean validateEditProjectName(String oldName, String newName, BufferedReader br) throws IOException
    {
        String projName;
        boolean existedName = false;
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Name:".trim())) {
                    projName = line.replaceFirst("Name:", "");
                    //Disregard the Project with the name equals old name(project whose name is currently edited)
                    if(!projName.equals(oldName)) {
                        if(newName.equals(projName) )
                            existedName = true;
                    }
                }
            }   
        }
        catch(IOException e) {}
        return existedName;
    }
    
    /**
     * Read data from project's text file, load attributes of project with these data
     * and return a full list of all Project objects
     * @param br used to read from text file
     * @return an ArrayList that contains all the Project objects with states loaded from the text file
     * @exception IOException  If an input or output exception occurred
     * @exception NullPointerException if the reader encounters a null line but does not raech end of file
     */
    public static ArrayList<Project> readAllProject(BufferedReader br) throws IOException
    {
        ArrayList<Project> allProjects = new ArrayList<Project>();
        Project projIns = null;
        String line;
        String projNo;
        String refType;
      try {
            while((line = br.readLine()) != null && (!line.equals(""))) {
              //if line with Project Number is found, instantiate a Project object
              //and load states for its attributes with data read from text file
              if(line.startsWith("No:".trim())) {
                  projIns = new Project();
                  projIns.myReferences = new ArrayList<Reference>();
                  projIns.myNotes = new ArrayList<Note>();
                  projNo = line.replaceFirst("No:".trim(),"");
                  line = br.readLine();
                  projIns.setName(line.replaceFirst("Name:".trim(),""));
                  line = br.readLine();
                  projIns.setDescription(line.replaceFirst("Brief:".trim(),""));
                  line = br.readLine();
                  while(!line.equals(("Proj"+ projNo +"RefNoList").trim())) {
                      projIns.setDescription(projIns.getDescription() + line);
                      line = br.readLine();
                  }
                  line = br.readLine();
                  //if Reference Number lisr is reached
                  while(!line.equals(("Proj"+ projNo +"NoteList").trim())) {
                      //call method that returns Reference type by its number
                      refType = findReferenceNo(line);
                      //based on this type, create an object of appropriate type
                      //and add these to array list of References of Project
                      if(refType != null) {
                          if(refType.toLowerCase().equals("book".trim())) {
                              Book bookIns = new Book();
                              Book myBook = bookIns.readByNo(line, REFERENCE_FILE_NAME);
                              projIns.myReferences.add(myBook);
                          }
                          else if(refType.toLowerCase().equals("webpage".trim())) {       
                              WebPage wegPageIns = new WebPage();
                              WebPage myWebPage = wegPageIns.readByNo(line, REFERENCE_FILE_NAME);
                              projIns.myReferences.add(myWebPage);
                          }
                          else if(refType.toLowerCase().equals("thesis".trim())) {
                              Thesis thesisIns = new Thesis();
                              Thesis myThesis = thesisIns.readByNo(line, REFERENCE_FILE_NAME);
                              projIns.myReferences.add(myThesis);
                          }
                          else if(refType.toLowerCase().equals("conferencepaper".trim())) {
                              ConferencePaper conIns = new ConferencePaper();
                              ConferencePaper myConferencePaper = conIns.readByNo(line, REFERENCE_FILE_NAME);
                              projIns.myReferences.add(myConferencePaper);
                          }
                          else if(refType.toLowerCase().equals("journal".trim())) {
                               Journal journalIns = new Journal();
                               Journal myJournal = journalIns.readByNo(line, REFERENCE_FILE_NAME);
                               projIns.myReferences.add(myJournal);
                          }
                          else if(refType.toLowerCase().equals("onlinejournal".trim())) {
                               OnlineJournal oJournalIns = new OnlineJournal();
                               OnlineJournal myOnlineJournal = oJournalIns.readByNo(line, REFERENCE_FILE_NAME);
                               projIns.myReferences.add(myOnlineJournal);
                          }   
                      }
                      line = br.readLine();
                  }
                  //read on 1 line
                  line = br.readLine();
                  Note aNote = null;       
                  while(!line.equals(("-----").trim())) {
                      //create Note objects, assign values for their attributes
                      //and add these Note objects to list Note of Project
                      if(line.startsWith("NoteDateCreated:".trim())) {
                          aNote = new Note();
                          aNote.setDateCreated(line.replaceFirst("NoteDateCreated:".trim(), ""));
                      }
                      if(line.startsWith("NoteText:".trim())) {
                          aNote.setText(line.replaceFirst("NoteText:".trim(), ""));
                          projIns.addNote(projIns.myNotes, aNote);
                      }
                      line = br.readLine();
                  }
             }
             allProjects.add(projIns);
        } 
      }
      catch(IOException e) {}
      catch(NullPointerException e) {}
      finally{
          if(br != null)
             br.close();
      }
      //return the Array of Project
      return allProjects;
    }
  
    /**
     * Read data from project's text file, find the project with the supplied name.
     * If found, load attributes of this project with data read from text file
     * and return this project
     * @param projName name of the project to be loaded
     * @param br used to read from text file
     * @return a Project that matches with the supplied name
     * @exception IOException  If an input or output exception occurred
     * @exception FileNotFoundException if the text file cannot be found
     */
    public static Project readProjectByName(String projName, BufferedReader br) throws IOException
    {
        boolean found = false;
        boolean foundProjectName = false;
        String refType = null;
        String projNo = null;
        Project myProject = new Project();
        myProject.myReferences = new ArrayList<Reference>();
        myProject.myNotes = new ArrayList<Note>();
        try {
            //call method to find requested Project
            found = findProjectName(projName, br);
        }
        catch(FileNotFoundException e) {}
        finally{
            if(br != null)
               br.close();
        }
        try {
            br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
            //if the Project is found
            if(found) {
                String line;
                while((line = br.readLine()) != null) {
                    //if requesting Project name is not reached, assign Project No to a variable
                    //stop assigning if requesting Project name is reached
                    if(line.startsWith("No:".trim()) && !foundProjectName) {
                        projNo = line.replaceFirst("No:".trim(), "");
                    }         
                    //if line with Project Name is found, load states for its attributes with data read from text file
                    if(line.startsWith("Name:".trim() + projName)) {
                        foundProjectName = true;
                        myProject.setName(line.replaceFirst("Name:".trim(),""));
                        line = br.readLine();
                        myProject.setDescription(line.replaceFirst("Brief:".trim(),""));
                        line = br.readLine();
                        while(!line.equals(("Proj"+ projNo +"RefNoList").trim())) {
                            myProject.setDescription(myProject.getDescription() + line);
                            line = br.readLine();
                        }
                        line = br.readLine();
                        //Reference Number list delimiter is found
                        while(!line.equals(("Proj"+ projNo +"NoteList").trim())) {
                            //call method that returns Reference type by its number
                            refType = findReferenceNo(line);
                            //based on this type, create an object of appropriate type
                            //and add these to array list of References of Project
                            if(refType != null) {
                                if(refType.toLowerCase().equals("book".trim())) {
                                    Book bookIns = new Book();
                                    Book myBook = bookIns.readByNo(line, REFERENCE_FILE_NAME);
                                    myProject.myReferences.add(myBook);
                                }
                                else if(refType.toLowerCase().equals("webpage".trim())) {
                                    WebPage webPageIns = new WebPage();
                                    WebPage myWebPage = webPageIns.readByNo(line, REFERENCE_FILE_NAME);
                                    myProject.myReferences.add(myWebPage);
                                }
                                else if(refType.toLowerCase().equals("thesis".trim())) {
                                    Thesis thesisIns = new Thesis();
                                    Thesis myThesis = thesisIns.readByNo(line, REFERENCE_FILE_NAME);
                                    myProject.myReferences.add(myThesis);
                                }
                                else if(refType.toLowerCase().equals("conferencepaper".trim())) {
                                    ConferencePaper conIns = new ConferencePaper();
                                    ConferencePaper myConferencePaper = conIns.readByNo(line, REFERENCE_FILE_NAME);
                                    myProject.myReferences.add(myConferencePaper);
                                }
                                else if(refType.toLowerCase().equals("journal".trim())) {
                                    Journal journalIns = new Journal();
                                    Journal myJournal = journalIns.readByNo(line, REFERENCE_FILE_NAME);
                                    myProject.myReferences.add(myJournal);
                                }
                                 else if(refType.toLowerCase().equals("onlinejournal".trim())) {
                                    OnlineJournal oJournalIns = new OnlineJournal();
                                    OnlineJournal myOnlineJournal = oJournalIns.readByNo(line, REFERENCE_FILE_NAME);
                                    myProject.myReferences.add(myOnlineJournal);
                                }
                            }
                            line = br.readLine();
                        }
                        //read on 1 line
                        line = br.readLine();
                        Note aNote = null;
                        //create Note objects, assign values for their attributes
                        //and add these Note objects to list Note of Project
                        while(!line.equals(("-----").trim())) {
                            if(line.startsWith("NoteDateCreated:".trim())) {
                                aNote = new Note();
                                aNote.setDateCreated(line.replaceFirst("NoteDateCreated:".trim(), ""));
                            }
                            if(line.startsWith("NoteText:".trim())) {
                                aNote.setText(line.replaceFirst("NoteText:".trim(), ""));
                                myProject.addNote(myProject.myNotes, aNote);
                            }
                            line = br.readLine();
                        }
                        break;
                    }
                } 
            }
        }
        catch(FileNotFoundException e) {}
        finally{
            if(br != null) {
               br.close();
            }
        }
        return myProject;
    }
    
    /**
     * Read data from reference's text file, find the reference with the supplied number.
     * If found, return the type of this reference
     * @param requestRefNo number of the reference to be checked
     * @return a String that contains reference type
     * @exception IOException  If an input or output exception occurred
     */
    public static String findReferenceNo(String requestRefNo) throws IOException
    {
        String refNo;
        String refType = null;
        BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("No:".trim())) {
                    refNo = line.replaceFirst("No:", "");
                    if(refNo.equals(requestRefNo)) {
                        line= br.readLine();
                        if(line.startsWith("Type:".trim())) {
                            refType = line.replaceFirst("Type:", ""); 
                        }
                        break;
                    }
                }
            }
        }
        catch(IOException e) {}
        finally{
            if(br != null)
               br.close();
        }
        return refType;
    }
    
    /**
     * Read data from project's text file, 
     * return the latest number of project
     * @param br used to read from text file
     * @return a String that contains last number of project
     * @exception IOException  If an input or output exception occurred
     */
    public static String readLastProjectNo(BufferedReader br) throws IOException
    {
        String projID = "";
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("No:".trim())) {
                    projID = line.replaceFirst("No:", "");
                }
            }   
        }
        catch(IOException e) {}
        finally {
            if(br != null) {
                br.close();
            }
        }
        return projID.trim();
    }
    
    /**
     * Read data from project's text file, 
     * return the latest number of note of the requested project
     * @param projName name of the project
     * @param br used to read from text file
     * @return a String that contains last number of note
     * @exception IOException  If an input or output exception occurred
     */
    public static String readLastNoteNo(String projName, BufferedReader br) throws IOException
    {
        String noteNo = "";
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Name:".trim() + projName)) {
                    line = br.readLine();
                    while(!line.equals("-----")) {
                        line = br.readLine();
                        if(line.startsWith("NoteNo:".trim())) {
                            noteNo = line.replaceFirst("NoteNo:", "");
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
        return noteNo.trim();
    }
    
    /**
     * Read data from project's text file, 
     * find and return number of the requested project name
     * @param requestProjName name of the project
     * @param br used to read from text file
     * @return a String that contains last number of project
     * @exception IOException  If an input or output exception occurred
     */
    public static String findProjectNo(String requestProjName, BufferedReader br) throws IOException
    {
        String projNo = "";
        boolean foundName = false;
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("No:".trim()) && !foundName) {
                    projNo = line.replaceFirst("No:", "");
                }
                if(line.replaceFirst("Name:".trim(), "").equals(requestProjName)) {
                    foundName = true;
                }
            }   
        }
        catch(IOException e) {}
        return projNo;
    }
    
    /**
     * Read data from project's text file, 
     * determine if a project with the supplied name exists
     * @param requestProjName name of the project
     * @param br used to read from text file
     * @return a boolean to determine whether the project with the supplied exists in the text file or not
     * @exception IOException  If an input or output exception occurred
     */
    public static boolean findProjectName(String requestProjName, BufferedReader br) throws IOException
    {
        String projName;
        boolean found = false;
        try {
            String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Name:".trim())) {
                    projName = line.replaceFirst("Name:", "");
                    if(projName.equals(requestProjName)) {
                        found = true;
                        break;
                    }
                }
            }   
        }
        catch(IOException e) {}
        return found;
   }
   
   /**
    * read from text file and find keyword in each Project name
    * if found, crate a Project, load its attributes and add it to the list
    * @param keyword the keyword used to filter the Project, filter is applied on Project Name
    * @param br used to read from text file
    * @return ArrayList<Project> array contains list of found Projects
    * @exception IOException if there is any error when input or output
    */
   public static ArrayList<Project> searchProjects(String keyword, BufferedReader br) throws IOException
   {
       ArrayList<Project> searchProjects = new ArrayList<Project>();
       Project projIns = null;
       String line ="";
       String projNo = "";
       String refType ="";
       try {
        while((line = br.readLine()) != null && (!line.equals(""))) {
              //store Project Number to variable to find delimiter
              if(line.startsWith("No:".trim())) {
                  projNo = line.replaceFirst("No:".trim(), "");    
              }
              if(line.startsWith("Name:".trim())) {
                //if the Project Name contains the keyword, create Project object and fill it attributes with data read from file
                if(line.replaceFirst("Name:".trim(), "").toLowerCase().contains(keyword.toLowerCase())) {
                  projIns = new Project();
                  projIns.myReferences = new ArrayList<Reference>();
                  projIns.myNotes = new ArrayList<Note>();
                  projIns.setName(line.replaceFirst("Name:".trim(),""));
                  line = br.readLine();
                  projIns.setDescription(line.replaceFirst("Brief:".trim(),""));
                  line = br.readLine();
                  while(!line.equals(("Proj"+ projNo +"RefNoList").trim())) {
                      projIns.setDescription(projIns.getDescription() + line);
                      line = br.readLine();
                  }
                  line = br.readLine();
                  while(!line.equals(("Proj"+ projNo +"NoteList").trim())) {
                      //call method that returns Reference type by its number
                      refType = findReferenceNo(line);
                      //based on this type, create an object of appropriate type
                      //and add these to array list of References of Project
                      if(refType != null){
                          if(refType.toLowerCase().equals("book".trim())) {
                              Book bookIns = new Book();
                              Book myBook = bookIns.readByNo(line, REFERENCE_FILE_NAME);
                              projIns.myReferences.add(myBook);
                          }
                          else if(refType.toLowerCase().equals("webpage".trim())) {       
                              WebPage wegPageIns = new WebPage();
                              WebPage myWebPage = wegPageIns.readByNo(line, REFERENCE_FILE_NAME);
                              projIns.myReferences.add(myWebPage);
                          }
                          else if(refType.toLowerCase().equals("thesis".trim())) {
                              Thesis thesisIns = new Thesis();
                              Thesis myThesis = thesisIns.readByNo(line, REFERENCE_FILE_NAME);
                              projIns.myReferences.add(myThesis);
                          }
                          else if(refType.toLowerCase().equals("conferencepaper".trim())) {
                              ConferencePaper conIns = new ConferencePaper();
                              ConferencePaper myConferencePaper = conIns.readByNo(line, REFERENCE_FILE_NAME);
                              projIns.myReferences.add(myConferencePaper);
                          }
                          else if(refType.toLowerCase().equals("journal".trim())) {
                               Journal journalIns = new Journal();
                               Journal myJournal = journalIns.readByNo(line, REFERENCE_FILE_NAME);
                               projIns.myReferences.add(myJournal);
                          }
                          else if(refType.toLowerCase().equals("onlinejournal".trim())) {
                               OnlineJournal oJournalIns = new OnlineJournal();
                               OnlineJournal myOnlineJournal = oJournalIns.readByNo(line, REFERENCE_FILE_NAME);
                               projIns.myReferences.add(myOnlineJournal);
                          }
                      }
                      line = br.readLine();
                  }
                  //read on 1 line
                  line = br.readLine();
                  Note aNote = null;       
                  //create Note objects, assign values for their attributes
                  //and add these Note objects to list Note of Project
                  while(!line.equals(("-----").trim())) {
                      if(line.startsWith("NoteDateCreated:".trim())) {
                          aNote = new Note();
                          aNote.setDateCreated(line.replaceFirst("NoteDateCreated:".trim(), ""));
                      }
                      if(line.startsWith("NoteText:".trim())) {
                          aNote.setText(line.replaceFirst("NoteText:".trim(), ""));
                          projIns.addNote(projIns.myNotes, aNote);
                      }
                      line = br.readLine();
                  }
                  searchProjects.add(projIns);
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
      return searchProjects;
   }
   
   /**
    * read and match the Note with text contains the supplied keyword.
    * if found, add this Note's DateCreated, Text and Name of the project containing it
    * to an array of String
    * @param keyword the keyword used to filter the Note, filter is applied on Note Text 
    * @param br used to read from text file
    * @return ArrayList<String> array contains data about the found Notes
    * @exception IOException if there is any error when input or output
    */
   public static ArrayList<String> searchProjectNotes(String keyword, BufferedReader br) throws IOException
   {
       ArrayList<String> noteData = new ArrayList<String>();
       String tempProjectName = "";
       String tempNoteDate = "";
       String tempNoteText = "";
       try {
           String line;
            while((line = br.readLine()) != null) {
                if(line.startsWith("Name:".trim())) {
                    //store Project name to variable in case the Note inside it matches the keyword
                    tempProjectName = line.replaceFirst("Name:".trim(), "");
                    while(!line.equals("-----")) {
                        //if not a line of Note DateCreated, read normally
                        if(!line.startsWith("NoteDateCreated:".trim())) {
                            line = br.readLine();
                        }
                        else {
                            tempNoteDate = line.replaceFirst("NoteDateCreated:".trim(), "");
                            line = br.readLine();
                            tempNoteText = line.replaceFirst("NoteText:".trim(), "");
                            //if Note text contains the keyword, store its data and its Project name to an array list of String
                            if(tempNoteText.toLowerCase().contains(keyword.toLowerCase())) {
                                noteData.add(tempNoteDate);
                                noteData.add(tempNoteText);
                                noteData.add(tempProjectName);
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
    * returns the states of project's attributes 
    * @return a String represents current states of all project's attributes
    */
   public String toString()
   {
        return "Name:" + name + " Brief Description: " + briefDesc;
   }
}

