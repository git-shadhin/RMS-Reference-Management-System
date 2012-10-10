import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.io.*;
import javax.swing.table.*;
import java.net.MalformedURLException;

/**
 * User Interface that interacts with user
 * Receive and display data to user
 * Receive user's requests and send to logic tier
 * 
 * @author Tin Huynh 
 * @version 2010.10.26
 */
public class ReferenceManagerGUI
{
    private static final String PROJECT_FILE_NAME = "data/Project.txt";
    private static final String REFERENCE_FILE_NAME = "data/Reference.txt";

    private ReferenceManager rm;
    private int selectedIndex;
    private JFrame mainFrame;
    private JFrame newProjectFrame;
    private JFrame projectDetailFrame;
    private JFrame referenceDetailFrame;
    private JFrame newNoteFrame;
    private JFrame referenceManagementFrame;
    private JFrame editProjectFrame;
    private JFrame editNoteFrame;
    private JFrame searchFrame;
    private JFrame biblioFrame;
    private JFrame newThesisFrame;
    private JFrame newBookFrame;
    private JFrame newConferencePaperFrame;
    private JFrame newJournalFrame;
    private JFrame newOnlineJournalFrame;
    private JFrame newWebPageFrame;
    private JFrame editThesisFrame;
    private JFrame editBookFrame;
    private JFrame editConferencePaperFrame;
    private JFrame editJournalFrame;
    private JFrame editOnlineJournalFrame;
    private JFrame editWebPageFrame;

    private ImageIcon detailIcon; 
    private ImageIcon addIcon; 
    private ImageIcon editIcon; 
    private ImageIcon removeIcon; 
    private ImageIcon searchIcon; 
    private ImageIcon manageIcon;
    private ImageIcon saveIcon;
    private ImageIcon bibliographyIcon;

    /**
     * Create an object of class ReferenceManagerGUI
     * instantiate some controls and an object of class ReferenceManager
     * call method displays the Main frame
     * @exception IOException if error occurs when input and output file
     */
    public ReferenceManagerGUI() throws IOException
    {
       rm= new ReferenceManager();
       detailIcon = new ImageIcon("images/detail.png");
       addIcon = new ImageIcon("images/add.png");
       editIcon = new ImageIcon("images/edit.png");
       removeIcon = new ImageIcon("images/delete.png");
       searchIcon = new ImageIcon("images/search.gif");
       manageIcon = new ImageIcon("images/mana.gif");
       saveIcon = new ImageIcon("images/save.png");
       bibliographyIcon = new ImageIcon("images/fullbook.png");
       makeMainFrame();
    }
    
   /**
    * terminate the program
    */
   public void quit()
   {
        System.exit(0);    
   }
   
   /**
    * create a menu bar on top of a frame
    * @param JFrame the frame to add menu
    * @exception IOException if error occurs when input and output file
    */
   private void makeMenuBar(JFrame frame) throws IOException
   {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);  
        JMenu menu = new JMenu("Menu");
        menubar.add(menu);   
        JMenuItem mainItem = new JMenuItem("Main");
            mainItem.addActionListener(new ActionListener()  {
                               public void actionPerformed(ActionEvent e)  {
                                   try {
                                       makeMainFrame();
                                   }
                                   catch(IOException ioe) {
                                       ioe.getMessage();
                                   }
                                }
                           });
        menu.add(mainItem);
        JMenuItem searchItem = new JMenuItem("Search");
            searchItem.addActionListener(new ActionListener()  {
                               public void actionPerformed(ActionEvent e)  {
                                   try {
                                       makeSearchFrame();
                                   }
                                   catch(IOException ioe) {
                                       ioe.getMessage();
                                   }
                                }
                           });
        menu.add(searchItem);
        JMenuItem biblioItem = new JMenuItem("Get Entire BiblioGraphic Entries");
            biblioItem.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { 
                                   try {
                                        makeBiblioListFrame(); 
                                   }
                                   catch(Exception ex) {
                                        ex.getMessage();    
                                   }
                                }
                           });
        menu.add(biblioItem);        
        JMenuItem newProjItem = new JMenuItem("New Project");
            newProjItem.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   try {
                                       makeNewProjectFrame();
                                   }
                                   catch(IOException ioe) {
                                       ioe.getMessage();
                                   }
                               }
                           });
        menu.add(newProjItem);
        final JFrame frame2 = frame;       
        JMenuItem newRefItem = new JMenuItem("New Reference");
        newRefItem.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { 
                                   try {
                                        String[] types = {"Book", "Conference Paper", "Journal", "Online Journal", "Thesis", "WebPage"};
                                        String s = (String)JOptionPane.showInputDialog(frame2, "Choose type of Reference you want too add", "Choose Reference Type" ,JOptionPane.PLAIN_MESSAGE, saveIcon, types, "Book");
                                        if(s != null) {
                                            if (s.equals("Thesis")) {
                                                makeNewThesisFrame();
                                            }
                                            if(s.equals("Book")) {
                                                makeNewBookFrame();
                                            }
                                            if(s.equals("Conference Paper")) {
                                                makeNewConferencePaperFrame();
                                            }
                                            if(s.equals("Journal")) {
                                                makeNewJournalFrame();
                                            }
                                            if(s.equals("Online Journal")) {
                                                makeNewOnlineJournalFrame();
                                            }
                                            if(s.equals("WebPage")) {
                                                makeNewWebPageFrame();
                                            }
                                        }
                                   }
                                   catch(Exception ioe) {}
                               }
                           });
        menu.add(newRefItem);
        JMenuItem quitItem = new JMenuItem("Quit");
            quitItem.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { quit(); }
                           });
        menu.add(quitItem); 
    }
   
   /**
    * hide all active frames
    */
   private void hideAllFrame()
   {
       if(mainFrame != null) {
           mainFrame.setVisible(false);
       }
       if(searchFrame != null) {
           searchFrame.setVisible(false);
       }
       if(newProjectFrame != null) {
           newProjectFrame.setVisible(false);
       }
       if(editProjectFrame != null) { 
           editProjectFrame.setVisible(false);
       }
       if(projectDetailFrame != null) {
           projectDetailFrame.setVisible(false);
       }
       if(referenceDetailFrame != null) {
           referenceDetailFrame.setVisible(false);
       }
       if(newNoteFrame != null) {
           newNoteFrame.setVisible(false);
       }
       if(editNoteFrame != null) {
           editNoteFrame.setVisible(false);
       }
       if(biblioFrame != null) {
           biblioFrame.setVisible(false);
       }
       if(referenceManagementFrame != null) { 
           referenceManagementFrame.setVisible(false);
       }
       if(newThesisFrame != null) {
           newThesisFrame.setVisible(false);
       }
       if(newBookFrame != null) {
           newBookFrame.setVisible(false);
       }
       if(newConferencePaperFrame != null) {
           newConferencePaperFrame.setVisible(false);
       }
       if(newJournalFrame != null) {
           newJournalFrame.setVisible(false);
       }
       if(newOnlineJournalFrame != null) {
           newOnlineJournalFrame.setVisible(false);
       }
       if(newWebPageFrame != null) {
           newWebPageFrame.setVisible(false);
       }
       if(editThesisFrame!= null) {
           editThesisFrame.setVisible(false);
       }
       if(editBookFrame != null) {
           editBookFrame.setVisible(false);
       }
       if(editConferencePaperFrame != null) {
           editConferencePaperFrame.setVisible(false);
       }
       if(editJournalFrame != null) {
           editJournalFrame.setVisible(false);
       }
       if(editOnlineJournalFrame != null) {
          editOnlineJournalFrame.setVisible(false);
       }
       if(editWebPageFrame != null) {
           editWebPageFrame.setVisible(false);
       }
   }
   
   /**
    * create program 's Main frame 
    * @exception IOException if error occurs when input and output file
    */
   public void makeMainFrame() throws IOException
   {
        hideAllFrame();
        mainFrame = new JFrame("Reference Management System - Main");  
        mainFrame.setResizable(false);
        makeMenuBar(mainFrame);
        Container contentPane = mainFrame.getContentPane(); 
        contentPane.setLayout(new BorderLayout(6,6));
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout(6,6));
        JPanel westPanelInner1 = new JPanel();
        westPanelInner1.setLayout(new BoxLayout(westPanelInner1, BoxLayout.Y_AXIS));
        JButton btnSearch = new JButton("Go to Search Page", searchIcon);
        btnSearch.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   try {
                                       makeSearchFrame();
                                   }
                                   catch(IOException ioe) {}
                               }
                           });
        westPanelInner1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Search Reference and Note"),BorderFactory.createEmptyBorder(5,5,5,5)));
        westPanelInner1.add(btnSearch);
        JPanel westPanelInner2 = new JPanel();
        westPanelInner2.setLayout(new BoxLayout(westPanelInner2, BoxLayout.Y_AXIS));   
        JButton btnAddProject = new JButton("Add New Project", addIcon);
        btnAddProject.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   try {
                                       makeNewProjectFrame();
                                   }
                                   catch(IOException ioe) {}
                               }
                           });
        JButton btnAddReference = new JButton("Add New Reference", addIcon);
        btnAddReference.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { 
                                   try {
                                        String[] types = {"Book", "Conference Paper", "Journal", "Online Journal", "Thesis", "WebPage"};
                                        String s = (String)JOptionPane.showInputDialog(mainFrame, "Choose type of Reference you want too add", "Choose Reference Type" ,JOptionPane.PLAIN_MESSAGE, saveIcon, types, "Book");                                     
                                        if(s != null) {
                                            if (s.equals("Thesis")) {
                                                makeNewThesisFrame();
                                            }
                                            if(s.equals("Book")) {
                                                makeNewBookFrame();
                                            }
                                            if(s.equals("Conference Paper")) {
                                                makeNewConferencePaperFrame();
                                            }
                                            if(s.equals("Journal")) {
                                                makeNewJournalFrame();
                                            }
                                            if(s.equals("Online Journal")) {
                                                makeNewOnlineJournalFrame();
                                            }
                                            if(s.equals("WebPage")) {
                                                makeNewWebPageFrame();
                                            }
                                        }
                                   }
                                   catch(Exception ioe) {}
                               }
                           });  
        westPanelInner2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("New Project & Reference"),BorderFactory.createEmptyBorder(5,5,5,5)));
        westPanelInner2.add(btnAddProject);
        westPanelInner2.add(Box.createVerticalStrut(5));
        westPanelInner2.add(btnAddReference);   
        JPanel westPanelInner3 = new JPanel();
        westPanelInner3.setLayout(new BoxLayout(westPanelInner3, BoxLayout.Y_AXIS));
        westPanelInner3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("BiblioGraphic Entry"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JButton btnBiblio = new JButton("Get all BiblioGraphicEntries", bibliographyIcon);
        btnBiblio.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { 
                                   try {
                                        if(rm.getMyReferences().size() == 0) {
                                            JOptionPane.showMessageDialog(mainFrame, "There must be at least 1 Reference to make Bibliography" , "Error", JOptionPane.ERROR_MESSAGE);    
                                        }
                                        else {
                                            makeBiblioListFrame();
                                        }
                                   }
                                   catch(Exception ex) {}
                                }
                           });
        
        westPanelInner3.add(btnBiblio);
        westPanel.add(westPanelInner1, BorderLayout.NORTH);
        westPanel.add(westPanelInner2, BorderLayout.CENTER);
        westPanel.add(westPanelInner3, BorderLayout.SOUTH); 
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());    
        JPanel centerPanelInnerCenter = new JPanel();
        centerPanelInnerCenter.setLayout(new BoxLayout(centerPanelInnerCenter, BoxLayout.Y_AXIS));
        centerPanelInnerCenter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Project Listing"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel centerPanelInnerEast = new JPanel();
        centerPanelInnerEast.setLayout(new BoxLayout(centerPanelInnerEast, BoxLayout.Y_AXIS));
        centerPanelInnerEast.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Reference Listing"),BorderFactory.createEmptyBorder(5,5,5,5)));
        centerPanel.add(centerPanelInnerCenter, BorderLayout.CENTER);
        centerPanel.add(centerPanelInnerEast, BorderLayout.EAST);
        //call method of Reference Manager class that loads all Projects and References
        rm.load();
        //fill JTable with rows of data from ArrayLists
        final JTable projtable = new JTable();
        projtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final DefaultTableModel pmodel = new DefaultTableModel();
        projtable.setModel(pmodel);
        pmodel.setColumnIdentifiers(new String[] {"Name", "Description"});
        for (Project p : rm.getMyProjects()) {
            pmodel.addRow(new String[] {p.getName(), p.getDescription()});
        }
        JScrollPane projScrollPane = new JScrollPane(projtable);
        final JTable reftable = new JTable();
        reftable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final DefaultTableModel rmodel = new DefaultTableModel();
        reftable.setModel(rmodel);
        rmodel.setColumnIdentifiers(new String[] {"Type", "Author", "Year", "Title"});
        for (Reference r : rm.getMyReferences()) {
            rmodel.addRow(new String[] {r.getClass().getName(), r.getAuthor(),r.getYearCreated(), r.getTitle()});
        }
        JScrollPane refScrollPane = new JScrollPane(reftable);
        JButton btnProjDetail = new JButton("Project Details", detailIcon);
        btnProjDetail.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       if(projtable.getSelectedRow() == -1) {
                                          JOptionPane.showMessageDialog(mainFrame, "Please choose a project to view details" , "Error", JOptionPane.ERROR_MESSAGE);
                                       }
                                       else {
                                           //call method that finds the selected Project
                                           Project aProject = rm.findAProject(projtable.getValueAt(projtable.getSelectedRow(),0).toString());
                                           if  (aProject.getName() == null) {
                                                JOptionPane.showMessageDialog(mainFrame, "The Project can no longer be found" , "Error", JOptionPane.ERROR_MESSAGE);
                                           }
                                           else {
                                                //if found, passed the Project object to the Project Details frame
                                                makeProjectDetailFrame(aProject);
                                           }
                                       }
                                   }
                                   catch(IOException ioe) {}
                                }
                            });
        JButton btnRefDetail = new JButton("Reference Details", detailIcon);
        btnRefDetail.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       if(reftable.getSelectedRow() == -1) {
                                          JOptionPane.showMessageDialog(mainFrame, "Please choose a reference to view details" , "Error", JOptionPane.ERROR_MESSAGE);
                                       }
                                       else {
                                           boolean foundRef = false;
                                           //loop through array list of Reference inside Reference Manager class
                                           for(Reference r : rm.getMyReferences()) {
                                               String refType = reftable.getValueAt(reftable.getSelectedRow(),0).toString();
                                               String refAuthor = reftable.getValueAt(reftable.getSelectedRow(),1).toString();
                                               String refYear = reftable.getValueAt(reftable.getSelectedRow(),2).toString();
                                               String refTitle = reftable.getValueAt(reftable.getSelectedRow(),3).toString();
                                               //if the selected Reference entry in JTable matches with the Reference object in the ArrayList
                                               if(refAuthor.equals(r.getAuthor()) && refYear.equals(r.getYearCreated()) && refTitle.equals(r.getTitle())) {
                                                    //pass the Reference object and its type to Reference Details frame
                                                    makeReferenceDetailFrame(refType, r);
                                                    foundRef = true;
                                                    break;
                                               }                                                 
                                           }
                                           if(!foundRef) {
                                               JOptionPane.showMessageDialog(mainFrame, "The Reference can no longer be found" , "Error", JOptionPane.ERROR_MESSAGE); 
                                            }
                                       }
                                   }
                                   catch(IOException ioe) {}
                                }
                            });
        centerPanelInnerCenter.add(projScrollPane);
        centerPanelInnerCenter.add(Box.createVerticalStrut(5));
        centerPanelInnerCenter.add(btnProjDetail);      
        centerPanelInnerEast.add(refScrollPane);
        centerPanelInnerEast.add(Box.createVerticalStrut(5));
        centerPanelInnerEast.add(btnRefDetail);
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2,2));
        contentPane.add(westPanel, BorderLayout.WEST);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        mainFrame.pack();
        mainFrame.setVisible(true);
   }
   
   /**
    * create program 's BiblioGraphic frame (all References)
    * @exception IOException if error occurs when input and output file
    */
   public void makeBiblioListFrame() throws IOException
   {
       hideAllFrame();
       biblioFrame = new JFrame("Bibliographic Entry");
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       int height = screenSize.height;
       int width = screenSize.width;
       biblioFrame.setPreferredSize(new Dimension(width, height));
       makeMenuBar(biblioFrame);  
       Container contentPane = biblioFrame.getContentPane();
       contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));      
       JPanel btnPanel = new JPanel();
       btnPanel.setLayout(new FlowLayout());     
       JButton btnSave = new JButton("Save to file", saveIcon);                       
       btnPanel.add(btnSave);
       BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));    
       final ArrayList<Reference> myReferences = Reference.readAllReferences(br);
       //sort References by author
       rm.sortReference(myReferences);
       final String[] entriesList = new String[myReferences.size()];
       for(int i=0; i< myReferences.size(); i++) {
            Reference r = myReferences.get(i);
            entriesList[i] = r.getBibliographicEntry();
       }
       rm.sortBiblioGraphicEntries(entriesList);
       JList bibList = new JList(entriesList);
       JScrollPane scrollPane = new JScrollPane(bibList);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                        rm.saveBiblioListToFile(entriesList);
                                        JOptionPane.showMessageDialog(biblioFrame, "Save Bibliographic Entries successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                   }
                                   catch(IOException ioe) {}
                                }
                            });
       
       contentPane.add(scrollPane);
       contentPane.add(btnPanel);
       biblioFrame.pack();
       biblioFrame.setVisible(true);
   }
   
   /**
    * create program 's BiblioGraphic frame (for a project)
    * @param aProject Project that has BiblioGraphic Entries listed
    * @exception IOException if error occurs when input and output file
    */
   public void makeBiblioListFrame(Project aProject) throws IOException
   {
       hideAllFrame();
       biblioFrame = new JFrame("Bibliographic Entry - Project: " + aProject.getName());
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       int height = screenSize.height;
       int width = screenSize.width;
       biblioFrame.setPreferredSize(new Dimension(width, height));
       makeMenuBar(biblioFrame);
       Container contentPane = biblioFrame.getContentPane();
       contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
       JPanel btnPanel = new JPanel();
       btnPanel.setLayout(new FlowLayout()); 
       JButton btnSave = new JButton("Save to file", saveIcon);                    
       btnPanel.add(btnSave);
       final Project myProject = aProject;
       //sort References by author
       rm.sortReference(myProject.myReferences);
       final String[] entriesList = new String[myProject.myReferences.size()];
       for(int i=0; i< myProject.myReferences.size(); i++) {
            Reference r = myProject.myReferences.get(i);
            entriesList[i] = r.getBibliographicEntry();
       }
       rm.sortBiblioGraphicEntries(entriesList);
       JList bibList = new JList(entriesList);
       JScrollPane scrollPane = new JScrollPane(bibList);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                        rm.saveBiblioListToFile(entriesList);
                                        JOptionPane.showMessageDialog(biblioFrame, "Save Bibliographic Entries successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                   }
                                   catch(IOException ioe) {}
                                }
                            });
       
       contentPane.add(scrollPane);
       contentPane.add(btnPanel);      
       biblioFrame.pack();
       biblioFrame.setVisible(true);
   }
   
   /**
    * create program 's Search frame 
    * @exception IOException if error occurs when input and output file
    */
   public void makeSearchFrame() throws IOException
   {
        hideAllFrame();
        searchFrame = new JFrame("Reference Management System - Search");  
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        searchFrame.setPreferredSize(new Dimension(width, height));
        searchFrame.setResizable(false);
        makeMenuBar(searchFrame);    
        Container contentPane = searchFrame.getContentPane(); 
        contentPane.setLayout(new BorderLayout(6,6));
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Search Project Name - Reference Title - Note Text"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel northPanel1 = new JPanel(new FlowLayout());
        JPanel northPanel2 = new JPanel();
        JLabel lblSearch = new JLabel("Please enter keywords ");
        final JTextField txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search", searchIcon);
        northPanel1.add(lblSearch);
        northPanel1.add(txtSearch);
        northPanel2.add(btnSearch);  
        northPanel.add(northPanel1, BorderLayout.NORTH);
        northPanel.add(northPanel2, BorderLayout.CENTER); 
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));    
        JPanel centerPanel1 = new JPanel();
        centerPanel1.setLayout(new BoxLayout(centerPanel1, BoxLayout.Y_AXIS));
        centerPanel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Project Search Results"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel centerPanel2 = new JPanel();
        centerPanel2.setLayout(new BoxLayout(centerPanel2, BoxLayout.Y_AXIS));
        centerPanel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Note Search Results"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel centerPanel3 = new JPanel();
        centerPanel3.setLayout(new BoxLayout(centerPanel3, BoxLayout.Y_AXIS));
        centerPanel3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Reference Search Results"),BorderFactory.createEmptyBorder(5,5,5,5)));     
        centerPanel.add(centerPanel1);
        centerPanel.add(centerPanel2);
        centerPanel.add(centerPanel3);
        final JTable projectTable = new JTable();
        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final DefaultTableModel pmodel = new DefaultTableModel();
        projectTable.setModel(pmodel);
        pmodel.setColumnIdentifiers(new String[] {"Name", "Brief Description"}); 
        JScrollPane projectScrollPane = new JScrollPane(projectTable);
        JTable noteTable = new JTable();
        noteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final DefaultTableModel nmodel = new DefaultTableModel();
        noteTable.setModel(nmodel);
        nmodel.setColumnIdentifiers(new String[] {"Date Created", "Text", "Belong to"}); 
        JScrollPane noteScrollPane = new JScrollPane(noteTable);  
        final JTable refTable = new JTable();
        refTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final DefaultTableModel rmodel = new DefaultTableModel();
        refTable.setModel(rmodel);
        rmodel.setColumnIdentifiers(new String[] {"Type", "Author", "Year Created", "Title"});
        JScrollPane refScrollPane = new JScrollPane(refTable);    
        JButton btnProjectDetail = new JButton("Project Details");
        btnProjectDetail.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       if(projectTable.getSelectedRow() == -1) {
                                          JOptionPane.showMessageDialog(searchFrame, "Please choose a project to view details" , "Error", JOptionPane.ERROR_MESSAGE);
                                       }
                                       else {
                                           Project aProject = rm.findAProject(projectTable.getValueAt(projectTable.getSelectedRow(),0).toString());
                                           if  (aProject.getName() == null) {
                                                JOptionPane.showMessageDialog(searchFrame, "The Project can no longer be found" , "Error", JOptionPane.ERROR_MESSAGE);
                                           }
                                           else {
                                                makeProjectDetailFrame(aProject);
                                           }
                                       }
                                   }
                                   catch(IOException ioe) {}
                                }
                            });                      
        JButton btnRefDetail = new JButton("Reference Details");
        btnRefDetail.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       if(refTable.getSelectedRow() == -1) {
                                          JOptionPane.showMessageDialog(mainFrame, "Please choose a reference to view details" , "Error", JOptionPane.ERROR_MESSAGE);
                                       }
                                       else {
                                           boolean foundRef = false;
                                           for(Reference r : rm.getMyReferences()) {
                                               String refType = refTable.getValueAt(refTable.getSelectedRow(),0).toString();
                                               String refAuthor = refTable.getValueAt(refTable.getSelectedRow(),1).toString();
                                               String refYear = refTable.getValueAt(refTable.getSelectedRow(),2).toString();
                                               String refTitle = refTable.getValueAt(refTable.getSelectedRow(),3).toString();
                                               if(refAuthor.equals(r.getAuthor()) && refYear.equals(r.getYearCreated()) && refTitle.equals(r.getTitle())) {
                                                    makeReferenceDetailFrame(refType, r);
                                                    foundRef = true;
                                                    break;
                                               }                                                 
                                           }
                                           if(!foundRef) {
                                               JOptionPane.showMessageDialog(mainFrame, "The Reference can no longer be found" , "Error", JOptionPane.ERROR_MESSAGE);  
                                           }
                                       }
                                   }
                                   catch(IOException ioe) {}
                                }
                            });   
        final JLabel lblNoProject = new JLabel("No Project found with search keyword");
        lblNoProject.setForeground(Color.RED);
        final JLabel lblNoNote = new JLabel("No Note found with search keyword");
        lblNoNote.setForeground(Color.RED);
        final JLabel lblNoRef = new JLabel("No Rerence found with search keyword");
        lblNoRef.setForeground(Color.RED);      
        lblNoProject.setVisible(false);
        lblNoNote.setVisible(false);
        lblNoRef.setVisible(false);        
        JPanel panelBtnProjectDetail = new JPanel();
        panelBtnProjectDetail.setLayout(new FlowLayout());
        panelBtnProjectDetail.add(btnProjectDetail);      
        JPanel panelBtnRefDetail = new JPanel();
        panelBtnRefDetail.setLayout(new FlowLayout());
        panelBtnRefDetail.add(btnRefDetail);      
        JPanel panelLblNoProject = new JPanel();
        panelLblNoProject.setLayout(new FlowLayout());
        panelLblNoProject.add(lblNoProject);     
        JPanel panelLblNoNote = new JPanel();
        panelLblNoNote.setLayout(new FlowLayout());
        panelLblNoNote.add(lblNoNote);   
        JPanel panelLblNoRef = new JPanel();
        panelLblNoRef.setLayout(new FlowLayout());
        panelLblNoRef.add(lblNoRef);    
        centerPanel1.add(projectScrollPane);
        centerPanel1.add(Box.createVerticalStrut(5));
        centerPanel1.add(panelLblNoProject);
        centerPanel1.add(Box.createVerticalStrut(5));
        centerPanel1.add(panelBtnProjectDetail);
        centerPanel1.add(Box.createVerticalStrut(5));
        centerPanel2.add(noteScrollPane);
        centerPanel2.add(Box.createVerticalStrut(5));
        centerPanel2.add(panelLblNoNote);
        centerPanel2.add(Box.createVerticalStrut(5));
        centerPanel3.add(refScrollPane);
        centerPanel3.add(Box.createVerticalStrut(5));
        centerPanel3.add(panelLblNoRef);
        centerPanel3.add(Box.createVerticalStrut(5));
        centerPanel3.add(panelBtnRefDetail);
        centerPanel3.add(Box.createVerticalStrut(5));
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);       
        btnSearch.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       if(txtSearch.getText().trim().equals("")) {
                                            JOptionPane.showMessageDialog(searchFrame, "Please enter keywords to search", "Error", JOptionPane.ERROR_MESSAGE);
                                       }
                                       else {
                                            //pass keyword and table models and labels for dynamic changes in the frame
                                            processSearch(txtSearch.getText().trim(), pmodel, nmodel, rmodel, lblNoProject, lblNoNote, lblNoRef);    
                                       }
                                   }
                                   catch(IOException ioe) {}
                                }
                            });
        searchFrame.pack();
        searchFrame.setVisible(true);
   }
   
   /**
    * process the search request, update controls on frame without reload the form
    * @param keyword keyword to search
    * @param pmodel Table model for Project result
    * @param nmodel Table model for Note result
    * @param rmodel Table model for Reference result
    * @param lblNoProject Message of no search Prjoject found
    * @param lblNoNote Message of no search Note found
    * @param lblNoRef Message of no search Reference found
    * @exception IOException if error occurs when input and output file
    */
   public void processSearch(String keyword, DefaultTableModel pmodel, DefaultTableModel nmodel, DefaultTableModel rmodel, JLabel lblNoProject, JLabel lblNoNote, JLabel lblNoRef) throws IOException
   {
        //remove all rows from last search
        pmodel.getDataVector().removeAllElements(); 
        nmodel.getDataVector().removeAllElements(); 
        rmodel.getDataVector().removeAllElements(); 
        //search and retrieve data 
        ArrayList<Project> searchedProjectList = rm.searchProjects(keyword);
        rm.sortProject(searchedProjectList);
        ArrayList<String> searchedProjectNotes = rm.searchProjectNotes(keyword);
        ArrayList<String> searchedReferenceNotes = rm.searchReferenceNotes(keyword);
        ArrayList<Reference> searchedReferenceList = rm.searchReferences(keyword);
        rm.sortReference(searchedReferenceList);
        if(searchedProjectList.size() == 0) {
            lblNoProject.setVisible(true);
        }
        else {
            //display search result on JTable if found at least 1 row
            lblNoProject.setVisible(false);
            for(Project p : searchedProjectList) {
                pmodel.addRow(new String[] {p.getName(), p.getDescription()});
            }
        }
        if(searchedProjectNotes.size() + searchedReferenceNotes.size() == 0) {
            lblNoNote.setVisible(true);
        }
        else {
            lblNoNote.setVisible(false);
            //each Note returned in a Project has 3 elements in the ArrayList: DateCreated, Text and its Project
            for(int i=0; i<searchedProjectNotes.size(); i=i+3) {
                nmodel.addRow(new String[] {searchedProjectNotes.get(i), searchedProjectNotes.get(i+1), "[Project] " + searchedProjectNotes.get(i+2)});
            }
            //each Note returned in a Reference has 6 elements in the ArrayList: DateCreated, Text and its Reference's type, author, year created and title
            for(int ir=0; ir<searchedReferenceNotes.size(); ir=ir+6) {
                nmodel.addRow(new String[] {searchedReferenceNotes.get(ir), searchedReferenceNotes.get(ir+1), "[" +searchedReferenceNotes.get(ir+2)+ "] " +  searchedReferenceNotes.get(ir+3) + " " + searchedReferenceNotes.get(ir+4) + ", " +searchedReferenceNotes.get(ir+5)});
            }
        }
        if(searchedReferenceList.size() == 0) {
            lblNoRef.setVisible(true);
        }
        else {
            //display search result on JTable if found at least 1 row
            lblNoRef.setVisible(false);
            for(Reference r : searchedReferenceList) {
                rmodel.addRow(new String[] {r.getClass().getName(), r.getAuthor(), r.getYearCreated(), r.getTitle()});
            }
        }
   }
   
   /**
    * make program's New Project frame
    * @exception IOException if error occurs when input and output file
    */
   public void makeNewProjectFrame() throws IOException
   {
        hideAllFrame();
        newProjectFrame = new JFrame("Reference Management System - New Project");  
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;
        newProjectFrame.setPreferredSize(new Dimension(width, height));
        newProjectFrame.setResizable(false);
        makeMenuBar(newProjectFrame);  
        Container contentPane = newProjectFrame.getContentPane(); 
        contentPane.setLayout(new BorderLayout(6,6));
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout(6,6));
        northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Project Information"),BorderFactory.createEmptyBorder(5,5,5,5)));      
        JPanel northPanelInner1 = new JPanel();
        northPanelInner1.setLayout(new BoxLayout(northPanelInner1, BoxLayout.X_AXIS));     
        JLabel lblName = new JLabel("Project Name:");
        final JTextField txtName = new JTextField(30);
        northPanelInner1.add(lblName);
        northPanelInner1.add(Box.createHorizontalStrut(38));
        northPanelInner1.add(txtName);
        northPanelInner1.add(Box.createHorizontalStrut(5)); 
        JPanel northPanelInner2 = new JPanel();
        northPanelInner2.setLayout(new BoxLayout(northPanelInner2, BoxLayout.X_AXIS));
        JLabel lblDesc = new JLabel("Brief Description:");
        final JTextArea txtDesc = new JTextArea(3,25);
        JScrollPane txtDescScroll = new JScrollPane(txtDesc);   
        northPanelInner2.add(lblDesc);
        northPanelInner2.add(Box.createHorizontalStrut(20));
        northPanelInner2.add(txtDescScroll);  
        northPanel.add(northPanelInner1, BorderLayout.NORTH);
        northPanel.add(northPanelInner2, BorderLayout.CENTER);     
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Please choose the references you want to use for this project"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel centerPanel1 = new JPanel();
        centerPanel1.setLayout(new BoxLayout(centerPanel1, BoxLayout.Y_AXIS));
        JPanel centerPanel2 = new JPanel();
        centerPanel2.setLayout(new BoxLayout(centerPanel2, BoxLayout.Y_AXIS));
        JLabel lblUnusedRefList = new JLabel("Unused References");
        lblUnusedRefList.setFont(new Font("Courier New", Font.BOLD, 16));
        lblUnusedRefList.setForeground(Color.RED);
        JLabel lblUsingRefList = new JLabel("Using References");
        lblUsingRefList.setFont(new Font("Courier New", Font.BOLD, 16));
        lblUsingRefList.setForeground(Color.RED);
        final JTable unusedTable = new JTable();
        final DefaultTableModel model = new DefaultTableModel();
        unusedTable.setModel(model);
        unusedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model.setColumnIdentifiers(new String[] {"Type", "Author", "Year", "Title"}); 
        BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
        ArrayList<Reference> myUnusedReferences = Reference.readAllReferences(br);
      
        for (Reference unusedR : myUnusedReferences){
            model.addRow(new String[] {unusedR.getClass().getName(), unusedR.getAuthor(),unusedR.getYearCreated(), unusedR.getTitle()});    
        }
       JScrollPane unusedScrollPane = new JScrollPane(unusedTable);
       final JTable reftable = new JTable();
       final DefaultTableModel rmodel = new DefaultTableModel();
       reftable.setModel(rmodel);
       reftable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       rmodel.setColumnIdentifiers(new String[] {"Type", "Author", "Year", "Title"}); 
       JScrollPane usingScrollPane = new JScrollPane(reftable);
       JPanel btnAddPanel = new JPanel();
       btnAddPanel.setLayout(new FlowLayout());
       JButton btnAdd = new JButton("Add >>");
       btnAddPanel.add(btnAdd);
       btnAdd.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                    if(unusedTable.getSelectedRow() == -1) {
                                       JOptionPane.showMessageDialog(referenceManagementFrame, "Please choose a reference to add to the current reference list" , "Error", JOptionPane.ERROR_MESSAGE); 
                                    }
                                    else {
                                       //remove selected row from unused list and add this row to using list
                                       String type = unusedTable.getValueAt(unusedTable.getSelectedRow(),0).toString();
                                       String author = unusedTable.getValueAt(unusedTable.getSelectedRow(),1).toString();
                                       String year = unusedTable.getValueAt(unusedTable.getSelectedRow(),2).toString();
                                       String title = unusedTable.getValueAt(unusedTable.getSelectedRow(),3).toString();
                                       model.removeRow(unusedTable.getSelectedRow());
                                       rmodel.insertRow(rmodel.getRowCount(), new Object[]{type, author, year, title});
                                    }
                                }
                            });        
       JPanel btnRemovePanel = new JPanel();
       btnRemovePanel.setLayout(new FlowLayout());
       JButton btnRemove = new JButton("<< Remove");
       btnRemovePanel.add(btnRemove);
       btnRemove.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                    if(reftable.getSelectedRow() == -1) {
                                       JOptionPane.showMessageDialog(referenceManagementFrame, "Please choose a reference to remove from the current reference list" , "Error", JOptionPane.ERROR_MESSAGE); 
                                    }
                                    else {
                                       //remove selected row from using list and add this row to ununsed list
                                       String type = reftable.getValueAt(reftable.getSelectedRow(),0).toString();
                                       String author = reftable.getValueAt(reftable.getSelectedRow(),1).toString();
                                       String year = reftable.getValueAt(reftable.getSelectedRow(),2).toString();
                                       String title = reftable.getValueAt(reftable.getSelectedRow(),3).toString();
                                       rmodel.removeRow(reftable.getSelectedRow());
                                       model.insertRow(model.getRowCount(), new Object[]{type, author, year, title});
                                    }
                                }
                            });
       centerPanel1.add(lblUnusedRefList);
       centerPanel2.add(lblUsingRefList);
       centerPanel1.add(unusedScrollPane);
       centerPanel2.add(usingScrollPane);
       centerPanel1.add(btnAddPanel);
       centerPanel2.add(btnRemovePanel);       
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);      
       JPanel southPanel = new JPanel(new BorderLayout());
       southPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Please leave the content field blank if you do not want to add note"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel southPanel1 = new JPanel();
       southPanel1.setLayout(new BoxLayout(southPanel1, BoxLayout.Y_AXIS));
       JPanel southPanel2 = new JPanel();
       southPanel2.setLayout(new BoxLayout(southPanel2, BoxLayout.Y_AXIS));
       JPanel southPanel3 = new JPanel();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       java.util.Date date = new java.util.Date();
       JLabel lblDate = new JLabel("Date Created: ");
       final JLabel dateContent = new JLabel(dateFormat.format(date).toString());
       JLabel lblNoteContent = new JLabel("Note Content:");
       final JTextField txtNoteText = new JTextField(5);
       JButton btnSave = new JButton("Save", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                      Project projIns = new Project();
                                      boolean projNameExisted = projIns.findProjectName(txtName.getText().trim(), br);
                                      boolean refExisted = true;
                                      int countErr =0;
                                      ArrayList<String> refList = new ArrayList<String>();            
                                      if(txtName.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newProjectFrame, "Project Name cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                      }
                                      else if(projNameExisted) {
                                        JOptionPane.showMessageDialog(newProjectFrame, "Project with this name already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                      }
                                      br.close();
                                      //check to ensure all reference entries chosen are exists in data file
                                      for(int i=0; i< reftable.getRowCount(); i++) {
                                        br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                        String refType = reftable.getValueAt(i,0).toString();
                                        String refAuthor = reftable.getValueAt(i,1).toString();
                                        String refYear = reftable.getValueAt(i,2).toString();
                                        String refTitle = reftable.getValueAt(i,3).toString();
                                        refExisted = Reference.checkExistReference(refType, refAuthor, refYear, refTitle, br);
                                        if(!refExisted) {
                                            countErr++;
                                            JOptionPane.showMessageDialog(newProjectFrame, "Reference " + refType + "-" + refAuthor + " " + refYear + " " + refTitle + " has been deleted" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        }
                                        else {
                                            br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                            refList.add(Reference.findReferenceNo(refType, refAuthor, refYear, refTitle, br));
                                        }
                                      }
                                      if(!projNameExisted && (countErr == 0) && !txtName.getText().equals("")) {
                                        rm.addNewProject(txtName.getText(), txtDesc.getText(), refList ,txtNoteText.getText() ,dateContent.getText());
                                        JOptionPane.showMessageDialog(newProjectFrame, "Add new Project successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });    
       southPanel1.add(lblDate);
       southPanel1.add(lblNoteContent);
       southPanel2.add(dateContent);
       southPanel2.add(txtNoteText);
       southPanel3.add(btnSave);
       southPanel.add(southPanel1, BorderLayout.WEST);
       southPanel.add(southPanel2, BorderLayout.CENTER);
       southPanel.add(southPanel3, BorderLayout.SOUTH);
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);
       newProjectFrame.pack();
       newProjectFrame.setVisible(true);
   }
   
   /**
    * make program's Edit Project frame
    * @param aProject Project to be edited
    * @exception IOException if error occurs when input and output file
    */
   public void makeEditProjectFrame(Project aProject) throws IOException
   {
        hideAllFrame();
        editProjectFrame = new JFrame("Reference Management System - Edit Project Information");  
        editProjectFrame.setLocation(400, 200);
        editProjectFrame.setResizable(false);
        makeMenuBar(editProjectFrame);   
        Container contentPane = editProjectFrame.getContentPane(); 
        contentPane.setLayout(new BorderLayout(6,6)); 
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout(6,6));
        westPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Project Information"),BorderFactory.createEmptyBorder(5,5,5,5)));   
        JPanel westPanelInner1 = new JPanel();
        westPanelInner1.setLayout(new BoxLayout(westPanelInner1, BoxLayout.X_AXIS));   
        JLabel lblName = new JLabel("Project Name:");
        final JTextField txtName = new JTextField(30);
        txtName.setText(aProject.getName()); 
        //store the name of the current edited project
        final JLabel lblCurrentName = new JLabel(aProject.getName());
        lblCurrentName.setVisible(false);
        westPanelInner1.add(lblName);
        westPanelInner1.add(lblCurrentName);
        westPanelInner1.add(Box.createHorizontalStrut(38));
        westPanelInner1.add(txtName);
        westPanelInner1.add(Box.createHorizontalStrut(5));  
        JPanel westPanelInner2 = new JPanel();
        westPanelInner2.setLayout(new BoxLayout(westPanelInner2, BoxLayout.X_AXIS));
        JLabel lblDesc = new JLabel("Brief Description:");
        final JTextArea txtDesc = new JTextArea(3,25);
        txtDesc.setText(aProject.getDescription());
        JScrollPane txtDescScroll = new JScrollPane(txtDesc);      
        westPanelInner2.add(lblDesc);
        westPanelInner2.add(Box.createHorizontalStrut(20));
        westPanelInner2.add(txtDescScroll);
        JPanel westPanelInner3 = new JPanel();
        JButton btnSave = new JButton("Save", saveIcon);
        btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                      Project projIns = new Project();
                                      boolean projNameExisted = projIns.validateEditProjectName(lblCurrentName.getText().trim(), txtName.getText().trim(), br);
                                      if(txtName.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editProjectFrame, "Project Name cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                      }
                                      else if(projNameExisted) {
                                        JOptionPane.showMessageDialog(editProjectFrame, "Project with this name already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                      }
                                      br.close();
                                      if(!projNameExisted && !txtName.getText().equals("")) {
                                        rm.updateProject(lblCurrentName.getText(), txtName.getText().trim(), txtDesc.getText().trim());
                                        JOptionPane.showMessageDialog(editProjectFrame, "Edit Project successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        //reload the edited project and redisplay it on ProjectDetails Frame
                                        br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                        Project projIns2 = new Project();
                                        Project editedProject  = projIns2.readProjectByName(txtName.getText().trim(), br);
                                        makeProjectDetailFrame(editedProject);
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });
        
        westPanelInner3.add(btnSave);
        westPanel.add(westPanelInner1, BorderLayout.NORTH);
        westPanel.add(westPanelInner2, BorderLayout.CENTER);
        westPanel.add(westPanelInner3, BorderLayout.SOUTH);   
        contentPane.add(westPanel, BorderLayout.WEST);
        editProjectFrame.pack();
        editProjectFrame.setVisible(true);
   }
   
   /**
    * make program's Project Detail frame
    * @param aProject Project to be viewed details
    * @exception IOException if error occurs when input and output file
    */
   public void makeProjectDetailFrame(Project aProject) throws IOException
   {
       selectedIndex = 0;
       hideAllFrame();
       projectDetailFrame = new JFrame("Reference Management System - Project Details");      
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       int height = screenSize.height * 2 / 3;
       int width = screenSize.width * 2 / 3;
       projectDetailFrame.setPreferredSize(new Dimension(width, height));
       projectDetailFrame.setResizable(false);
       makeMenuBar(projectDetailFrame); 
       Container contentPane = projectDetailFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Project Details"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new BoxLayout(northPanel1, BoxLayout.Y_AXIS));      
       JLabel lblName = new JLabel("Name: ");
       final JLabel nameContent = new JLabel(aProject.getName());
       nameContent.setForeground(Color.blue);
       JLabel lblDescription = new JLabel("Brief Description:");
       JLabel descContent = new JLabel(aProject.getDescription());
       descContent.setFont(new Font("Geneva", Font.PLAIN, 12));
       northPanel1.add(lblName);
       northPanel1.add(nameContent);
       northPanel1.add(lblDescription);
       northPanel1.add(descContent);      
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new BoxLayout(northPanel2, BoxLayout.X_AXIS));
       final Project myProj = aProject;
       JButton btnEditProject = new JButton("Edit Project", editIcon);
       btnEditProject.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {    
                                      BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                      Project projIns = new Project();
                                      boolean projNameExisted = projIns.findProjectName(nameContent.getText(), br);
                                      if(!projNameExisted) {
                                          JOptionPane.showMessageDialog(projectDetailFrame, "Cannot find Project " + nameContent.getText() + ". Project has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                      }
                                      br.close();
                                      if(projNameExisted) {
                                          makeEditProjectFrame(myProj);
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });               
       JButton btnRemoveProject = new JButton("Remove Project", removeIcon);
       btnRemoveProject.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      int prompt = JOptionPane.showConfirmDialog(projectDetailFrame, "Are you sure you want to delete this project?" , "Delete Project", JOptionPane.YES_NO_OPTION);
                                      if (prompt == JOptionPane.YES_OPTION) {
                                        BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                        Project projIns = new Project();
                                        boolean projNameExisted = projIns.findProjectName(nameContent.getText(), br);                                     
                                        if(!projNameExisted) {
                                            JOptionPane.showMessageDialog(projectDetailFrame, "Cannot find Project " + nameContent.getText() + ". Project has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        }
                                        br.close();
                                        if(projNameExisted) {
                                            rm.deleteProject(nameContent.getText());
                                            JOptionPane.showMessageDialog(projectDetailFrame, "Delete Project successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                            makeMainFrame();
                                        }
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });
       northPanel2.add(btnEditProject);
       northPanel2.add(Box.createHorizontalStrut(5));
       northPanel2.add(btnRemoveProject);  
       northPanel.add(northPanel1, BorderLayout.CENTER);
       northPanel.add(northPanel2, BorderLayout.SOUTH);      
       JPanel westPanel = new JPanel();
       westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
       westPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Current References"),BorderFactory.createEmptyBorder(5,5,5,5)));
       final Project myProject = aProject;
       JPanel westPanel1 = new JPanel();
       westPanel1.setLayout(new FlowLayout());
       JButton btnManageRef = new JButton("Manage References", manageIcon);
       btnManageRef.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       makeReferenceManagementFrame(myProject);
                                   }
                                   catch(IOException ioe) {}
                                }
                            });                 
       JButton btnGetBiblio = new JButton("Get BiblioGraphic Entries", bibliographyIcon);
       btnGetBiblio.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       if(myProject.myReferences.size() == 0) {
                                            JOptionPane.showMessageDialog(mainFrame, "There must be at least 1 Reference to make Bibliography" , "Error", JOptionPane.ERROR_MESSAGE);    
                                        }
                                        else {
                                            makeBiblioListFrame(myProject);
                                        }
                                   }
                                   catch(IOException ioe) {
                                       ioe.getMessage();
                                   }  
                                }
                            });
       westPanel1.add(btnManageRef);   
       westPanel1.add(btnGetBiblio);   
       final JTable reftable = new JTable();
       reftable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       final DefaultTableModel rmodel = new DefaultTableModel();
       reftable.setModel(rmodel);
       rmodel.setColumnIdentifiers(new String[] {"Type", "Author", "Year", "Title"}); 
       rm.sortReference(aProject.myReferences);
       for (Reference r : aProject.myReferences) {
           rmodel.addRow(new String[] {r.getClass().getName(), r.getAuthor(),r.getYearCreated(), r.getTitle()});
       } 
       JScrollPane refScrollPane = new JScrollPane(reftable); 
       JButton btnRefDetail = new JButton("Reference Details", detailIcon);
       btnRefDetail.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       if(reftable.getSelectedRow() == -1) {
                                          JOptionPane.showMessageDialog(mainFrame, "Please choose a reference to view details" , "Error", JOptionPane.ERROR_MESSAGE);
                                       }
                                       else {
                                           boolean foundRef = false;
                                           for(Reference r : rm.getMyReferences()) {
                                               String refType = reftable.getValueAt(reftable.getSelectedRow(),0).toString();
                                               String refAuthor = reftable.getValueAt(reftable.getSelectedRow(),1).toString();
                                               String refYear = reftable.getValueAt(reftable.getSelectedRow(),2).toString();
                                               String refTitle = reftable.getValueAt(reftable.getSelectedRow(),3).toString();
                                               if(refAuthor.equals(r.getAuthor()) && refYear.equals(r.getYearCreated()) && refTitle.equals(r.getTitle())) {
                                                    makeReferenceDetailFrame(refType, r);
                                                    foundRef = true;
                                                    break;
                                               }                                                 
                                           }
                                           if(!foundRef) {
                                               JOptionPane.showMessageDialog(mainFrame, "The Reference can no longer be found" , "Error", JOptionPane.ERROR_MESSAGE);   
                                           }
                                       }
                                   }
                                   catch(IOException ioe) {}
                                }
                            });        
       westPanel.add(westPanel1);
       westPanel.add(Box.createVerticalStrut(5));
       westPanel.add(refScrollPane);
       westPanel.add(Box.createVerticalStrut(5));
       westPanel.add(btnRefDetail);     
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Notes"),BorderFactory.createEmptyBorder(5,5,5,5)));   
       //create 2 String arrays, one for DateCreated, one for Text
       final String[] noteData1 = new String[aProject.noOfNote()];
       final String[] noteData2 = new String[aProject.noOfNote()];
       int noteIndex=0;  
       for(Note n : aProject.myNotes) {
            noteData1[noteIndex] = n.getDateCreated();
            noteData2[noteIndex] = n.getText();
            noteIndex++;
       }
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new BoxLayout(centerPanel1, BoxLayout.Y_AXIS));
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new BoxLayout(centerPanel2, BoxLayout.Y_AXIS));
       JPanel centerPanel3 = new JPanel();
       centerPanel3.setLayout(new FlowLayout());    
       JPanel centerPanel4 = new JPanel();
       centerPanel4.setLayout(new BoxLayout(centerPanel4, BoxLayout.Y_AXIS));    
       final Project myProject2 = aProject;     
       JButton btnNewNote = new JButton("Add new Note", addIcon);
       btnNewNote.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       makeNewNoteFrame(myProject2);
                                   }
                                   catch(IOException ioe) {}
                                }
                            });                   
      centerPanel4.add(btnNewNote);
      centerPanel4.add(Box.createVerticalStrut(5));  
       
      if(aProject.noOfNote() !=0) {
        //display data of first Note in list 
        final JLabel lblDate = new JLabel("Date Created: " + noteData1[selectedIndex]);
        JLabel lblNoteContent = new JLabel("Content:");     
        centerPanel1.add(lblDate);
        centerPanel1.add(lblNoteContent);
        final JTextField noteContent = new JTextField(noteData2[selectedIndex]);
        noteContent.setFont(new Font("Courier New", Font.ITALIC, 14));
        JScrollPane scrollNoteContent = new JScrollPane(noteContent);
        noteContent.setEditable(false);
        centerPanel2.add(scrollNoteContent); 
        JButton btnNext = new JButton("Next Note");
        btnNext.addActionListener(new ActionListener() 
        { 
                public void actionPerformed(ActionEvent e) {        
                                   try {
                                       selectedIndex = nextNote(ReferenceManagerGUI.this.selectedIndex, lblDate, noteContent, noteData1, noteData2);
                                    }
                                    catch(IOException ioe) {}
                                }
                            });     
        JButton btnPrevious = new JButton("Previous Note");
        btnPrevious.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       selectedIndex = previousNote(ReferenceManagerGUI.this.selectedIndex, lblDate, noteContent, noteData1, noteData2);
                                   }
                                   catch(IOException ioe) {}
                                }
                            });
       centerPanel3.add(btnNext);
       centerPanel3.add(btnPrevious);  
       JButton btnEditNote = new JButton("Edit Note", editIcon);
       btnEditNote.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                       boolean noteExisted = myProject2.checkExistedNote(myProject2.getName(), lblDate.getText().replaceFirst("Date Created: ", ""), noteContent.getText(), br);
                                       br.close();
                                       if(!noteExisted) {
                                            JOptionPane.showMessageDialog(newProjectFrame, "Cannot find this Note. Note has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE);     
                                       }
                                       else {
                                            makeEditNoteFrame(myProject2, noteContent.getText(), lblDate.getText().replaceFirst("Date Created: ", ""));
                                       }
                                   }
                                   catch(IOException ioe) {}
                                }
                            });                     
       JButton btnDeleteNote = new JButton("Delete Note", removeIcon);
       btnDeleteNote.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      int prompt = JOptionPane.showConfirmDialog(projectDetailFrame, "Are you sure you want to delete this note?" , "Delete Note", JOptionPane.YES_NO_OPTION);
                                      if (prompt == JOptionPane.YES_OPTION) {
                                          BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                          boolean noteExisted = myProject2.checkExistedNote(myProject2.getName(), lblDate.getText().replaceFirst("Date Created: ", ""), noteContent.getText(), br);
                                          br.close();
                                          if(!noteExisted) {
                                               JOptionPane.showMessageDialog(newProjectFrame, "Cannot find this Note. Note has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE);  
                                          }
                                          else {
                                               rm.deleteProjectNote(nameContent.getText(), noteContent.getText(), lblDate.getText().replaceFirst("Date Created: ", ""));
                                               JOptionPane.showMessageDialog(newProjectFrame, "Delete Note successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                               //reload the edited project and redisplay it on ProjectDetail Frame
                                               br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                               Project projIns2 = new Project();
                                               Project myProject  = projIns2.readProjectByName(nameContent.getText(), br);
                                               makeProjectDetailFrame(myProject);
                                          }
                                      }
                                   }
                                   catch(IOException ioe) {}
                                }
                            });     
        centerPanel4.add(btnEditNote);
        centerPanel4.add(Box.createVerticalStrut(5));
        centerPanel4.add(btnDeleteNote);
      } 
       centerPanel.add(centerPanel1, BorderLayout.NORTH);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       centerPanel.add(centerPanel3, BorderLayout.SOUTH);
       centerPanel.add(centerPanel4, BorderLayout.EAST);
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(westPanel, BorderLayout.WEST);
       contentPane.add(centerPanel, BorderLayout.CENTER);  
       projectDetailFrame.pack();
       projectDetailFrame.setVisible(true);    
   }
   
   /**
    * fetch the next Note in the collection of Notes
    * @param selectedIndex current Note index
    * @param jl Label DateCreated
    * @param jt TextFiled Note Text
    * @param noteData1 list of DateCreated
    * @param noteData2 list of Note Text
    * @return index of the current Note
    */
   public int nextNote(int selectedIndex, JLabel jl, JTextField jt, String[] noteData1, String[] noteData2) throws IOException
   {
       //increase current Note index if last Note is not reached, reset controls' values
       if(selectedIndex < noteData1.length -1) {
        selectedIndex = selectedIndex + 1;    
        jl.setText("Date Created: " + noteData1[selectedIndex]);
        jt.setText(noteData2[selectedIndex]);  
       }
       return selectedIndex;
   }
   
   /**
    * return to previous Note in the collection of Notes
    * @param selectedIndex current Note index
    * @param jl Label DateCreated
    * @param jt TextFiled Note Text
    * @param noteData1 list of DateCreated
    * @param noteData2 list of Note Text
    * @return index of the current Note
    */
   public int previousNote(int selectedIndex, JLabel jl, JTextField jt, String[] noteData1, String[] noteData2) throws IOException
   {
       //return to previous Note index if first Note is not reached, reset controls' values
       if(selectedIndex >0) {
        selectedIndex= selectedIndex - 1;   
        jl.setText("Date Created: " + noteData1[selectedIndex]);
        jt.setText(noteData2[selectedIndex]);
       }
       return selectedIndex;
   }
   
   /**
    * display program's Reference Detail frame
    * @param type Type of the reference
    * @param r the Reference object to be viewed details
    * @exception IOException if an error occurs when input and output file
    */
   public void makeReferenceDetailFrame(String type, Reference r) throws IOException
   {
       selectedIndex = 0;
       hideAllFrame();
       referenceDetailFrame = new JFrame("Reference Management System - Reference Details");  
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       int height = screenSize.height * 2 / 3;
       int width = screenSize.width * 2 / 3;
       referenceDetailFrame.setPreferredSize(new Dimension(width, height));
       makeMenuBar(referenceDetailFrame);       
       Container contentPane = referenceDetailFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));     
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));       
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0));       
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");    
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);    
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0));     
       final JLabel contentType = new JLabel(r.getClass().getName());
       final JLabel contentAuthor = new JLabel(r.getAuthor());
       final JLabel contentYearCreated = new JLabel(r.getYearCreated());
       final JLabel contentTitle = new JLabel(r.getTitle());    
       northPanel2.add(contentType);
       northPanel2.add(contentAuthor);
       northPanel2.add(contentYearCreated);
       northPanel2.add(contentTitle);
       JPanel northPanel3 = new JPanel();
       northPanel3.setLayout(new BoxLayout(northPanel3, BoxLayout.Y_AXIS));
       JButton btnEditRef = new JButton("Edit Reference", editIcon);     
       JButton btnRemoveRef = new JButton("Remove Reference", removeIcon);
       btnRemoveRef.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      int prompt = JOptionPane.showConfirmDialog(projectDetailFrame, "Are you sure you want to delete this reference?" , "Delete Reference", JOptionPane.YES_NO_OPTION);
                                      if (prompt == JOptionPane.YES_OPTION) {
                                        BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                        boolean refNameExisted = Reference.checkExistReference(contentType.getText(), contentAuthor.getText(), contentYearCreated.getText(), contentTitle.getText(), br);
                                        if(!refNameExisted) {
                                            JOptionPane.showMessageDialog(projectDetailFrame, "Cannot find Reference. Reference has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        }
                                        br.close();
                                        if(refNameExisted) {
                                            rm.removeReference(contentType.getText(), contentAuthor.getText(), contentYearCreated.getText(), contentTitle.getText());
                                            JOptionPane.showMessageDialog(projectDetailFrame, "Delete Reference successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                            makeMainFrame();
                                        }
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });
       northPanel3.add(btnEditRef);
       northPanel3.add(Box.createVerticalStrut(5));
       northPanel3.add(btnRemoveRef);     
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       northPanel.add(northPanel3, BorderLayout.EAST);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(0,0));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(0,0,0,0)));   
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(7,0,0,0));     
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(7,0,0,0));   
       //Specialized information is displayed based on the type of the Reference
       if(r.getClass().getName().equals("Book")) {
            final Book myBook = (Book)r;
            JLabel lblPublisher = new JLabel("Publisher: ");
            JLabel lblSeries = new JLabel("Series: ");
            JLabel lblEdition = new JLabel("Edition: ");
            JLabel lblPlaceofPublic = new JLabel("Place of Public: ");      
            centerPanel1.add(lblPublisher);
            centerPanel1.add(lblSeries);
            centerPanel1.add(lblEdition);
            centerPanel1.add(lblPlaceofPublic);
            JLabel contentPublisher = new JLabel(myBook.getPublisher());
            JLabel contentSeries = new JLabel(myBook.getSeriesTitle());
            JLabel contentEdition = new JLabel(myBook.getEdition());
            JLabel contentPlaceofPublic = new JLabel(myBook.getPlaceOfPublic());      
            centerPanel2.add(contentPublisher);
            centerPanel2.add(contentSeries);
            centerPanel2.add(contentEdition);
            centerPanel2.add(contentPlaceofPublic);           
            btnEditRef.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {    
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference(contentType.getText(), contentAuthor.getText(), contentYearCreated.getText(),contentTitle.getText(), br);                       
                                      if(!refExisted) {
                                          JOptionPane.showMessageDialog(projectDetailFrame, "Cannot find Reference. Reference has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                      }
                                      br.close();
                                      if(refExisted) {
                                          makeEditBookFrame(myBook);
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });
       }
       if(r.getClass().getName().equals("Thesis")) {
            final Thesis myThesis = (Thesis)r;
            JLabel lblPublisher = new JLabel("Publisher: ");
            JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
            JLabel lblLocation = new JLabel("Location:");       
            centerPanel1.add(lblPublisher);
            centerPanel1.add(lblPlaceofPublic);
            centerPanel1.add(lblLocation);
            JLabel contentPublisher = new JLabel(myThesis.getPublisher());
            JLabel contentPlaceofPublic = new JLabel(myThesis.getPlaceOfPublic());
            JLabel contentLocation = new JLabel(myThesis.getLocation());      
            centerPanel2.add(contentPublisher);
            centerPanel2.add(contentPlaceofPublic);
            centerPanel2.add(contentLocation); 
            btnEditRef.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {    
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference(contentType.getText(), contentAuthor.getText(), contentYearCreated.getText(),contentTitle.getText(), br);
                                      if(!refExisted) {
                                          JOptionPane.showMessageDialog(projectDetailFrame, "Cannot find Reference. Reference has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                      }
                                      br.close();
                                      if(refExisted) {
                                          makeEditThesisFrame(myThesis);
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });
       }
       if(r.getClass().getName().equals("ConferencePaper")) {
            final ConferencePaper myConPaper = (ConferencePaper)r;
            JLabel lblPublisher = new JLabel("Publisher: ");
            JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
            JLabel lblPlaceHeld = new JLabel("Place Held:");
            JLabel lblTimeHeld = new JLabel("Time Held:");
            JLabel lblPageNumber = new JLabel("Page Number:");       
            centerPanel1.add(lblPublisher);
            centerPanel1.add(lblPlaceofPublic);
            centerPanel1.add(lblPlaceHeld);
            centerPanel1.add(lblTimeHeld);
            centerPanel1.add(lblPageNumber);
            JLabel contentPublisher = new JLabel(myConPaper.getPublisher());
            JLabel contentPlaceofPublic = new JLabel(myConPaper.getPlaceOfPublic());
            JLabel contentPlaceHeld = new JLabel(myConPaper.getPlaceHeld());
            JLabel contentTimeHeld = new JLabel(myConPaper.getTimeHeld());
            JLabel contentPageNumber = new JLabel(myConPaper.getPageNumber());
            centerPanel2.add(contentPublisher);
            centerPanel2.add(contentPlaceofPublic);
            centerPanel2.add(contentPlaceHeld);
            centerPanel2.add(contentTimeHeld);
            centerPanel2.add(contentPageNumber);
            btnEditRef.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {    
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference(contentType.getText(), contentAuthor.getText(), contentYearCreated.getText(),contentTitle.getText(), br);
                                      if(!refExisted) {
                                          JOptionPane.showMessageDialog(projectDetailFrame, "Cannot find Reference. Reference has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                      }
                                      br.close();
                                      if(refExisted) {
                                          makeEditConferencePaperFrame(myConPaper);
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });
       }
       if(r.getClass().getName().equals("Journal")) {
            final Journal myJournal = (Journal)r;
            JLabel lblPeriodicalTitle = new JLabel("Periodical Title: ");
            JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
            JLabel lblVolume = new JLabel("Volume:");
            JLabel lblTime = new JLabel("Time Held:");
            JLabel lblPageNumber = new JLabel("Page Number:");     
            centerPanel1.add(lblPeriodicalTitle);
            centerPanel1.add(lblPlaceofPublic);
            centerPanel1.add(lblVolume);
            centerPanel1.add(lblTime);
            centerPanel1.add(lblPageNumber);
            JLabel contentPeriodicalTitle = new JLabel(myJournal.getPeriodicalTitle());
            JLabel contentPlaceofPublic = new JLabel(myJournal.getPlaceOfPublic());
            JLabel contentVolume = new JLabel(myJournal.getVolume());
            JLabel contentTime = new JLabel(myJournal.getTime());
            JLabel contentPageNumber = new JLabel(myJournal.getPageNumber());
            centerPanel2.add(contentPeriodicalTitle);
            centerPanel2.add(contentPlaceofPublic);
            centerPanel2.add(contentVolume);
            centerPanel2.add(contentTime);
            centerPanel2.add(contentPageNumber);
            btnEditRef.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {    
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference(contentType.getText(), contentAuthor.getText(), contentYearCreated.getText(),contentTitle.getText(), br);
                                      if(!refExisted) {
                                          JOptionPane.showMessageDialog(projectDetailFrame, "Cannot find Reference. Reference has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                      }
                                      br.close();
                                      if(refExisted) {
                                          makeEditJournalFrame(myJournal);
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });
       }
       if(r.getClass().getName().equals("OnlineJournal")) {
            final OnlineJournal myOnlineJournal = (OnlineJournal)r;
            JLabel lblPeriodicalTitle = new JLabel("Periodical Title: ");
            JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
            JLabel lblVolume = new JLabel("Volume:");
            JLabel lblTime = new JLabel("Time Held:");
            JLabel lblPageNumber = new JLabel("Page Number:");
            JLabel lblTitleOfDB = new JLabel("Title of DB:");
            JLabel lblFirstViewedDate = new JLabel("First Viewed:");     
            centerPanel1.add(lblPeriodicalTitle);
            centerPanel1.add(lblPlaceofPublic);
            centerPanel1.add(lblVolume);
            centerPanel1.add(lblTime);
            centerPanel1.add(lblPageNumber);
            centerPanel1.add(lblTitleOfDB);
            centerPanel1.add(lblFirstViewedDate);
            JLabel contentPeriodicalTitle = new JLabel(myOnlineJournal.getPeriodicalTitle());
            JLabel contentPlaceofPublic = new JLabel(myOnlineJournal.getPlaceOfPublic());
            JLabel contentVolume = new JLabel(myOnlineJournal.getVolume());
            JLabel contentTime = new JLabel(myOnlineJournal.getTime());
            JLabel contentPageNumber = new JLabel(myOnlineJournal.getPageNumber());
            JLabel contentTitleOfDB = new JLabel(myOnlineJournal.getTitleOfDB());
            JLabel contentFirstViewedDate = new JLabel(myOnlineJournal.getFirstViewedDate());
            centerPanel2.add(contentPeriodicalTitle);
            centerPanel2.add(contentPlaceofPublic);
            centerPanel2.add(contentVolume);
            centerPanel2.add(contentTime);
            centerPanel2.add(contentPageNumber);
            centerPanel2.add(contentTitleOfDB);
            centerPanel2.add(contentFirstViewedDate);
            btnEditRef.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {    
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference(contentType.getText(), contentAuthor.getText(), contentYearCreated.getText(),contentTitle.getText(), br);
                                      if(!refExisted) {
                                          JOptionPane.showMessageDialog(projectDetailFrame, "Cannot find Reference. Reference has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                      }
                                      br.close();
                                      if(refExisted) {
                                          makeEditOnlineJournalFrame(myOnlineJournal);
                                      }
                                   }
                                   catch(Exception ea){}
                                }
                            });
       }     
       if(r.getClass().getName().equals("WebPage")) {
            final WebPage myWebPage = (WebPage)r;
            JLabel lblGroupHosting = new JLabel("Group Hosting: ");
            JLabel lblDateViewed = new JLabel("First Viewed: ");
            JLabel lblURL = new JLabel("URL:");      
            centerPanel1.add(lblGroupHosting);
            centerPanel1.add(lblDateViewed);
            centerPanel1.add(lblURL);
            JLabel contentGroupHosting = new JLabel(myWebPage.getGroupHosting());
            JLabel contentDateViewed = new JLabel(myWebPage.getDateViewed());
            JLabel contentURL = new JLabel(myWebPage.getURL());
            centerPanel2.add(contentGroupHosting);
            centerPanel2.add(contentDateViewed);
            centerPanel2.add(contentURL);
            btnEditRef.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {    
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference(contentType.getText(), contentAuthor.getText(), contentYearCreated.getText(),contentTitle.getText(), br);
                                      if(!refExisted) {
                                          JOptionPane.showMessageDialog(projectDetailFrame, "Cannot find Reference. Reference has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                      }
                                      br.close();
                                      if(refExisted) {
                                          makeEditWebPageFrame(myWebPage);
                                      }
                                   }
                                   catch(Exception ea){}
                                }
                            });
       }
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel eastPanel = new JPanel();
       eastPanel.setLayout(new BorderLayout(6,6));
       eastPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Notes"),BorderFactory.createEmptyBorder(5,5,5,5)));
       final String[] noteData1 = new String[r.noOfNote()];
       final String[] noteData2 = new String[r.noOfNote()];
       int noteIndex=0;
       for(Note n : r.myNotes) {
            noteData1[noteIndex] = n.getDateCreated();
            noteData2[noteIndex] = n.getText();
            noteIndex++;
       }
       JPanel eastPanel1 = new JPanel();
       eastPanel1.setLayout(new BoxLayout(eastPanel1, BoxLayout.Y_AXIS));
       JPanel eastPanel2 = new JPanel();
       eastPanel2.setLayout(new BoxLayout(eastPanel2, BoxLayout.Y_AXIS));
       JPanel eastPanel3 = new JPanel();
       eastPanel3.setLayout(new FlowLayout());       
       JPanel eastPanel4 = new JPanel();
       eastPanel4.setLayout(new BoxLayout(eastPanel4, BoxLayout.Y_AXIS));      
       JButton btnNewNote = new JButton("Add new Note", addIcon);
       final Reference myReference = r;
       btnNewNote.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       makeNewNoteFrame(myReference);
                                   }
                                   catch(IOException ioe) {}
                                }
                            });
       eastPanel4.add(btnNewNote);
       eastPanel4.add(Box.createVerticalStrut(5));
       //if there is note exists, edit note and delete note button are displayed
       if(r.noOfNote() !=0) {
            final JLabel lblDate = new JLabel("Date Created: " + noteData1[selectedIndex]);
            JLabel lblNoteContent = new JLabel("Content:");    
            eastPanel1.add(lblDate);
            eastPanel1.add(lblNoteContent);
            final JTextField noteContent = new JTextField(noteData2[selectedIndex]);
            JScrollPane scrollNoteContent = new JScrollPane(noteContent);
            noteContent.setEditable(false);
            eastPanel2.add(scrollNoteContent);
            JButton btnNext = new JButton("Next Note");
            btnNext.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) {        
                                   try {
                                       selectedIndex = nextNote(ReferenceManagerGUI.this.selectedIndex, lblDate, noteContent, noteData1, noteData2);
                                    }
                                    catch(IOException ioe) {}
                                }
                            });                       
           JButton btnPrevious = new JButton("Previous Note");
           btnPrevious.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       selectedIndex = previousNote(ReferenceManagerGUI.this.selectedIndex, lblDate, noteContent, noteData1, noteData2);
                                   }
                                   catch(IOException ioe) {}
                                }
                            });       
           eastPanel3.add(btnNext);
           eastPanel3.add(btnPrevious);
           final Reference myReference2 = r;
           JButton btnEditNote = new JButton("Edit Note", editIcon);
           btnEditNote.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                       BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                       boolean noteExisted = myReference2.checkExistedNote(myReference2.getClass().getName(), myReference2.getAuthor(), myReference2.getYearCreated(), myReference2.getTitle(), lblDate.getText().replaceFirst("Date Created: ", ""), noteContent.getText(), br);
                                       br.close();
                                       if(!noteExisted) {
                                            JOptionPane.showMessageDialog(referenceDetailFrame, "Cannot find this Note. Note has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE);      
                                       }
                                       else {
                                            makeEditNoteFrame(myReference2, noteContent.getText(), lblDate.getText().replaceFirst("Date Created: ", ""));
                                       }
                                   }
                                   catch(IOException ioe) {}
                                }
                            });  
          JButton btnDeleteNote = new JButton("Delete Note", removeIcon);
          btnDeleteNote.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      int prompt = JOptionPane.showConfirmDialog(projectDetailFrame, "Are you sure you want to delete this note?" , "Delete Note", JOptionPane.YES_NO_OPTION);
                                      if (prompt == JOptionPane.YES_OPTION) {
                                          BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                          boolean noteExisted = myReference2.checkExistedNote(myReference2.getClass().getName(), myReference2.getAuthor(), myReference2.getYearCreated(), myReference2.getTitle(), lblDate.getText().replaceFirst("Date Created: ", ""), noteContent.getText(), br);
                                          br.close();
                                          if(!noteExisted) {
                                               JOptionPane.showMessageDialog(referenceDetailFrame, "Cannot find this Note. Note has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE);      
                                          }
                                          else {
                                               rm.deleteReferenceNote(contentType.getText(), contentAuthor.getText(), contentYearCreated.getText(), contentTitle.getText(), noteContent.getText(), lblDate.getText().replaceFirst("Date Created: ", ""));
                                               JOptionPane.showMessageDialog(projectDetailFrame, "Delete Note successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                               //reload the edited project and redisplay it on ProjectDetail Frame
                                               makeMainFrame();
                                          }
                                      }
                                   }
                                   catch(IOException ioe){}  
                                }
                            });   
          eastPanel4.add(btnEditNote);
          eastPanel4.add(Box.createVerticalStrut(5));
          eastPanel4.add(btnDeleteNote);
      }   
       eastPanel.add(eastPanel1, BorderLayout.NORTH);
       eastPanel.add(eastPanel2, BorderLayout.CENTER);
       eastPanel.add(eastPanel3, BorderLayout.SOUTH);
       eastPanel.add(eastPanel4, BorderLayout.EAST);
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(eastPanel, BorderLayout.EAST);
       referenceDetailFrame.pack();
       referenceDetailFrame.setVisible(true);
   }
   
   /**
    * display program's Thesis edit frame
    * @param myThesis the Thesis object to be edited
    * @exception IOException if an error occurs when input and output file
    */
   public void makeEditThesisFrame(Thesis myThesis) throws IOException
   {
       hideAllFrame();
       editThesisFrame = new JFrame("Reference Management System - Edit Reference");  
       editThesisFrame.setResizable(false);
       makeMenuBar(editThesisFrame);    
       Container contentPane = editThesisFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));    
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));   
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       final JLabel currentAuthor = new JLabel(myThesis.getAuthor());
       final JLabel currentYear = new JLabel(myThesis.getYearCreated());
       final JLabel currentTitle = new JLabel(myThesis.getTitle());
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("Thesis");
       final JTextField txtAuthor = new JTextField(myThesis.getAuthor());
       final JTextField txtYearCreated = new JTextField(myThesis.getYearCreated(), 30);
       final JTextField txtTitle = new JTextField(myThesis.getTitle(), 30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(3,0,0,5));
       JLabel lblPublisher = new JLabel("Publisher: ");
       JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
       JLabel lblLocation = new JLabel("Store Location: ");
       centerPanel1.add(lblPublisher);
       centerPanel1.add(lblPlaceofPublic);
       centerPanel1.add(lblLocation);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(3,0,0,5));
       final JTextField txtPublisher = new JTextField(myThesis.getPublisher(), 30);
       final JTextField txtPlaceofPublic = new JTextField(myThesis.getPlaceOfPublic(), 30);
       final JTextField txtLocation = new JTextField(myThesis.getLocation(), 30);
       centerPanel2.add(txtPublisher);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel2.add(txtLocation);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new FlowLayout());
       JButton btnSave = new JButton("Save Changes", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      //validation of information before call method to update
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.validateEditReference("Thesis", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editThesisFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newThesisFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intYear <= 0) {
                                            JOptionPane.showMessageDialog(newThesisFrame, "Invalid Year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;    
                                        }
                                      }
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(editThesisFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "Thesis");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("Publisher", txtPublisher.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        information.put("Location", txtLocation.getText().trim());
                                        rm.editReference("Thesis", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), information);
                                        JOptionPane.showMessageDialog(editThesisFrame, "Edit Thesis successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });
       southPanel.add(btnSave);
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);
       editThesisFrame.pack();
       editThesisFrame.setVisible(true);  
   }
   
   /**
    * display program's Journal edit frame
    * @param myJournal the Journal object to be edited
    * @exception IOException if an error occurs when input and output file
    */
   public void makeEditJournalFrame(Journal myJournal) throws IOException
   {
       hideAllFrame();
       editJournalFrame = new JFrame("Reference Management System - Edit Reference");  
       editJournalFrame.setResizable(false);
       makeMenuBar(editJournalFrame);    
       Container contentPane = editJournalFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       final JLabel currentAuthor = new JLabel(myJournal.getAuthor());
       final JLabel currentYear = new JLabel(myJournal.getYearCreated());
       final JLabel currentTitle = new JLabel(myJournal.getTitle());
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("Journal");
       final JTextField txtAuthor = new JTextField(myJournal.getAuthor());
       final JTextField txtYearCreated = new JTextField(myJournal.getYearCreated(), 30);
       final JTextField txtTitle = new JTextField(myJournal.getTitle(), 30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));   
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(5,0,0,5));     
       JLabel lblPeriodical = new JLabel("Periodical Title: ");
       JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
       JLabel lblVolume = new JLabel("Volume: ");
       JLabel lblTime = new JLabel("Day and Month(dd/MM): ");
       JLabel lblPageNumber = new JLabel("Page Number: ");
       centerPanel1.add(lblPeriodical);
       centerPanel1.add(lblPlaceofPublic);
       centerPanel1.add(lblVolume);
       centerPanel1.add(lblTime);
       centerPanel1.add(lblPageNumber);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(5,0,0,5));
       final JTextField txtPeriodical = new JTextField(myJournal.getPeriodicalTitle(), 30);
       final JTextField txtPlaceofPublic = new JTextField(myJournal.getPlaceOfPublic(), 30);
       final JTextField txtVolume = new JTextField(myJournal.getVolume(), 30);
       final JTextField txtTime = new JTextField(myJournal.getTime(), 30);
       final JTextField txtPageNumber = new JTextField(myJournal.getPageNumber(), 30);
       centerPanel2.add(txtPeriodical);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel2.add(txtVolume);
       centerPanel2.add(txtTime);
       centerPanel2.add(txtPageNumber);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new FlowLayout());
       JButton btnSave = new JButton("Save Changes", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.validateEditReference("Journal", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to update
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editJournalFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(editJournalFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intYear <= 0) {
                                            JOptionPane.showMessageDialog(editJournalFrame, "Invalid Year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;    
                                        }
                                      }  
                                      if(!txtVolume.getText().trim().equals("")) {
                                        int intVolume = -1;
                                        try {
                                            intVolume = Integer.parseInt(txtVolume.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(editJournalFrame, "Volume must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intVolume <= 0) {
                                        JOptionPane.showMessageDialog(editJournalFrame, "Invalid volume" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      if(!txtTime.getText().trim().equals("")) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
                                        try {
                                            Date d = sdf.parse(txtTime.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(editJournalFrame, "Please enter Day and Month in this format dd/mm" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                      }
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(editJournalFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "Journal");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("PeriodicalTitle", txtPeriodical.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        information.put("Volume", txtVolume.getText().trim());
                                        information.put("Time", txtTime.getText().trim());
                                        information.put("PageNumber", txtPageNumber.getText().trim());
                                        rm.editReference("Journal", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), information);
                                        JOptionPane.showMessageDialog(editJournalFrame, "Edit Journal successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });
       southPanel.add(btnSave);  
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);     
       editJournalFrame.pack();
       editJournalFrame.setVisible(true);
   }
   
   /**
    * display program's OnlineJournal edit frame
    * @param myOnlineJournal the OnlineJournal object to be edited
    * @exception IOException if an error occurs when input and output file
    */
   public void makeEditOnlineJournalFrame(OnlineJournal myOnlineJournal) throws IOException
   {
       hideAllFrame();
       editOnlineJournalFrame = new JFrame("Reference Management System - Edit Reference");  
       editOnlineJournalFrame.setResizable(false);
       makeMenuBar(editOnlineJournalFrame);  
       Container contentPane = editOnlineJournalFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       final JLabel currentAuthor = new JLabel(myOnlineJournal.getAuthor());
       final JLabel currentYear = new JLabel(myOnlineJournal.getYearCreated());
       final JLabel currentTitle = new JLabel(myOnlineJournal.getTitle());
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("OnlineJournal");
       final JTextField txtAuthor = new JTextField(myOnlineJournal.getAuthor());
       final JTextField txtYearCreated = new JTextField(myOnlineJournal.getYearCreated(), 30);
       final JTextField txtTitle = new JTextField(myOnlineJournal.getTitle(), 30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(7,0,0,5));
       JLabel lblPeriodical = new JLabel("Periodical Title: ");
       JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
       JLabel lblVolume = new JLabel("Volume: ");
       JLabel lblTime = new JLabel("Day and Month(dd/MM): ");
       JLabel lblPageNumber = new JLabel("Page Number: ");
       JLabel lblTitleOfDB = new JLabel("Database Title: ");
       JLabel lblFirstViewedDate = new JLabel("First Viewed Date: ");
       centerPanel1.add(lblPeriodical);
       centerPanel1.add(lblPlaceofPublic);
       centerPanel1.add(lblVolume);
       centerPanel1.add(lblTime);
       centerPanel1.add(lblPageNumber);
       centerPanel1.add(lblTitleOfDB);
       centerPanel1.add(lblFirstViewedDate);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(7,0,0,5));
       final JTextField txtPeriodical = new JTextField(myOnlineJournal.getPeriodicalTitle(), 30);
       final JTextField txtPlaceofPublic = new JTextField(myOnlineJournal.getPlaceOfPublic(), 30);
       final JTextField txtVolume = new JTextField(myOnlineJournal.getVolume(), 30);
       final JTextField txtTime = new JTextField(myOnlineJournal.getTime(), 30);
       final JTextField txtPageNumber = new JTextField(myOnlineJournal.getPageNumber(), 30);
       final JTextField txtTitleOfDB = new JTextField(myOnlineJournal.getTitleOfDB() ,30);
       final JTextField txtFirstViewedDate = new JTextField(myOnlineJournal.getFirstViewedDate() ,30);
       centerPanel2.add(txtPeriodical);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel2.add(txtVolume);
       centerPanel2.add(txtTime);
       centerPanel2.add(txtPageNumber);
       centerPanel2.add(txtTitleOfDB);
       centerPanel2.add(txtFirstViewedDate);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new FlowLayout());
       JButton btnSave = new JButton("Save Changes", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.validateEditReference("OnlineJournal", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to update
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editOnlineJournalFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(editOnlineJournalFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intYear <= 0) {
                                            JOptionPane.showMessageDialog(editOnlineJournalFrame, "Invalid Year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;    
                                        }
                                      }  
                                      if(!txtVolume.getText().trim().equals("")) {
                                        int intVolume = -1;
                                        try {
                                            intVolume = Integer.parseInt(txtVolume.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(editOnlineJournalFrame, "Volume must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intVolume <= 0) {
                                        JOptionPane.showMessageDialog(editOnlineJournalFrame, "Invalid volume" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      if(!txtTime.getText().trim().equals(""))
                                      {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
                                        try {
                                            Date d = sdf.parse(txtTime.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(editOnlineJournalFrame, "Please enter Day and Month in this format dd/mm" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                      }
                                      if(!txtFirstViewedDate.getText().trim().equals("")) {
                                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                                        try {
                                            Date d2 = sdf2.parse(txtFirstViewedDate.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(editOnlineJournalFrame, "Please enter First Viewed Date in this format dd/mm/yyyy" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                     }
                                     
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(editOnlineJournalFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "OnlineJournal");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("PeriodicalTitle", txtPeriodical.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        information.put("Volume", txtVolume.getText().trim());
                                        information.put("Time", txtTime.getText().trim());
                                        information.put("PageNumber", txtPageNumber.getText().trim());
                                        information.put("TitleOfDB", txtTitleOfDB.getText().trim());
                                        information.put("FirstViewedDate", txtFirstViewedDate.getText().trim());
                                        rm.editReference("OnlineJournal", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), information);
                                        JOptionPane.showMessageDialog(editOnlineJournalFrame, "Edit OnlineJournal successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });    
       southPanel.add(btnSave);    
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);        
       editOnlineJournalFrame.pack();
       editOnlineJournalFrame.setVisible(true);
   }
   
   /**
    * display program's ConferencePaper edit frame
    * @param myConferencePaper the ConferencePaper object to be edited
    * @exception IOException if an error occurs when input and output file
    */
   public void makeEditConferencePaperFrame(ConferencePaper myConferencePaper) throws IOException
   {
       hideAllFrame();
       editConferencePaperFrame = new JFrame("Reference Management System - Edit Reference");  
       editConferencePaperFrame.setResizable(false);
       makeMenuBar(editConferencePaperFrame);  
       Container contentPane = editConferencePaperFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));   
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       final JLabel currentAuthor = new JLabel(myConferencePaper.getAuthor());
       final JLabel currentYear = new JLabel(myConferencePaper.getYearCreated());
       final JLabel currentTitle = new JLabel(myConferencePaper.getTitle());
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("ConferencePaper");
       final JTextField txtAuthor = new JTextField(myConferencePaper.getAuthor());
       final JTextField txtYearCreated = new JTextField(myConferencePaper.getYearCreated(), 30);
       final JTextField txtTitle = new JTextField(myConferencePaper.getTitle(), 30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);   
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5))); 
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(5,0,0,5));
       JLabel lblPublisher = new JLabel("Publisher: ");
       JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
       JLabel lblPlaceHeld = new JLabel("Place Held: ");
       JLabel lblTimeHeld = new JLabel("Time Held (dd/mm/yyyy): ");
       JLabel lblPageNumber = new JLabel("Page Number: ");
       centerPanel1.add(lblPublisher);
       centerPanel1.add(lblPlaceofPublic);
       centerPanel1.add(lblPlaceHeld);
       centerPanel1.add(lblTimeHeld);
       centerPanel1.add(lblPageNumber);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(5,0,0,5));
       final JTextField txtPublisher = new JTextField(myConferencePaper.getPublisher(), 30);
       final JTextField txtPlaceofPublic = new JTextField(myConferencePaper.getPlaceOfPublic(), 30);
       final JTextField txtPlaceHeld = new JTextField(myConferencePaper.getPlaceHeld(), 30);
       final JTextField txtTimeHeld = new JTextField(myConferencePaper.getTimeHeld(), 30);
       final JTextField txtPageNumber = new JTextField(myConferencePaper.getPageNumber(), 30);
       centerPanel2.add(txtPublisher);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel2.add(txtPlaceHeld);
       centerPanel2.add(txtTimeHeld);
       centerPanel2.add(txtPageNumber);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new FlowLayout());
       JButton btnSave = new JButton("Save Changes", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.validateEditReference("OnlineJournal", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to update
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editConferencePaperFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(editConferencePaperFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intYear <= 0)
                                        {
                                            JOptionPane.showMessageDialog(editConferencePaperFrame, "Invalid Year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;    
                                        }
                                      }
                                         
                                      if(!txtTimeHeld.getText().trim().equals("")) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        try {
                                            Date d = sdf.parse(txtTimeHeld.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(editConferencePaperFrame, "Please enter Time Held in this format dd/mm/yyyy" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                      }
                                      
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(editConferencePaperFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "ConferencePaper");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("Publisher", txtPublisher.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        information.put("PlaceHeld", txtPlaceHeld.getText().trim());
                                        information.put("TimeHeld", txtTimeHeld.getText().trim());
                                        information.put("PageNumber", txtPageNumber.getText().trim());
                                        rm.editReference("ConferencePaper", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), information);
                                        JOptionPane.showMessageDialog(editConferencePaperFrame, "Edit ConferencePaper successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });
       southPanel.add(btnSave);       
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);    
       editConferencePaperFrame.pack();
       editConferencePaperFrame.setVisible(true);
   }
   
   /**
    * display program's WebPage edit frame
    * @param myWebPage the WebPage object to be edited
    * @exception IOException if an error occurs when input and output file
    */
   public void makeEditWebPageFrame(WebPage myWebPage) throws IOException
   {
       hideAllFrame();
       editWebPageFrame = new JFrame("Reference Management System - Edit Reference");  
       editWebPageFrame.setResizable(false);
       makeMenuBar(editWebPageFrame); 
       Container contentPane = editWebPageFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));   
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));    
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));  
       final JLabel currentAuthor = new JLabel(myWebPage.getAuthor());
       final JLabel currentYear = new JLabel(myWebPage.getYearCreated());
       final JLabel currentTitle = new JLabel(myWebPage.getTitle()); 
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("WebPage");
       final JTextField txtAuthor = new JTextField(myWebPage.getAuthor());
       final JTextField txtYearCreated = new JTextField(myWebPage.getYearCreated(), 30);
       final JTextField txtTitle = new JTextField(myWebPage.getTitle(), 30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(3,0,0,5));
       JLabel lblGroupHosting = new JLabel("Group Hosting: ");
       JLabel lblDateViewed = new JLabel("First Viewed Date: ");
       JLabel lblURL = new JLabel("URL Address: ");
       centerPanel1.add(lblGroupHosting);
       centerPanel1.add(lblDateViewed);
       centerPanel1.add(lblURL);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(3,0,0,5));
       final JTextField txtGroupHosting = new JTextField(myWebPage.getGroupHosting(), 30);
       final JTextField txtDateViewed = new JTextField(myWebPage.getDateViewed(), 30);
       final JTextField txtURL = new JTextField(myWebPage.getURL(), 30);
       centerPanel2.add(txtGroupHosting);
       centerPanel2.add(txtDateViewed);
       centerPanel2.add(txtURL);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new FlowLayout());
       JButton btnSave = new JButton("Save Changes", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.validateEditReference("WebPage", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to update
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editBookFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(editBookFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intYear <= 0)
                                        {
                                            JOptionPane.showMessageDialog(editBookFrame, "Invalid Year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;    
                                        }
                                      }
                                         
                                      if(txtDateViewed.getText().trim().equals("")) {
                                            JOptionPane.showMessageDialog(newOnlineJournalFrame, "First Viewed Date cannot be nul" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;           
                                      }
                                      else if(!txtDateViewed.getText().trim().equals("")) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        try {
                                            Date d = sdf.parse(txtDateViewed.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(newOnlineJournalFrame, "Please enter First Viewed Date in this format dd/mm/yyyy" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                      }
                                      
                                      if(txtURL.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newWebPageFrame, "URL cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(newWebPageFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "WebPage");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("GroupHosting", txtGroupHosting.getText().trim());
                                        information.put("DateViewed", txtDateViewed.getText().trim());
                                        information.put("URL", txtURL.getText().trim());
                                        rm.editReference("WebPage", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), information);
                                        JOptionPane.showMessageDialog(editWebPageFrame, "Edit WebPage successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });   
       southPanel.add(btnSave);
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);       
       editWebPageFrame.pack();
       editWebPageFrame.setVisible(true);  
   }
   
   /**
    * display program's Book edit frame
    * @param myBook the Book object to be edited
    * @exception IOException if an error occurs when input and output file
    */
   public void makeEditBookFrame(Book myBook) throws IOException
   {
       hideAllFrame();
       editBookFrame = new JFrame("Reference Management System - Edit Reference");  
       editBookFrame.setResizable(false);
       makeMenuBar(editBookFrame);
       Container contentPane = editBookFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       final JLabel currentAuthor = new JLabel(myBook.getAuthor());
       final JLabel currentYear = new JLabel(myBook.getYearCreated());
       final JLabel currentTitle = new JLabel(myBook.getTitle());
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("Book");
       final JTextField txtAuthor = new JTextField(myBook.getAuthor());
       final JTextField txtYearCreated = new JTextField(myBook.getYearCreated(), 30);
       final JTextField txtTitle = new JTextField(myBook.getTitle(), 30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(4,0,0,5));
       JLabel lblPublisher = new JLabel("Publisher: ");
       JLabel lblSeries = new JLabel("Series Title: ");
       JLabel lblEdition = new JLabel("Edition: ");
       JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
       centerPanel1.add(lblPublisher);
       centerPanel1.add(lblSeries);
       centerPanel1.add(lblEdition);
       centerPanel1.add(lblPlaceofPublic);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(4,0,0,5));
       final JTextField txtPublisher = new JTextField(myBook.getPublisher(), 30);
       final JTextField txtSeries = new JTextField(myBook.getSeriesTitle(), 30);
       final JTextField txtEdition = new JTextField(myBook.getEdition(), 30);
       final JTextField txtPlaceofPublic = new JTextField(myBook.getPlaceOfPublic(), 30);
       centerPanel2.add(txtPublisher);
       centerPanel2.add(txtSeries);
       centerPanel2.add(txtEdition);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new FlowLayout());
       JButton btnSave = new JButton("Save Changes", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.validateEditReference("Book", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to update
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editBookFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(editBookFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intYear <= 0) {
                                            JOptionPane.showMessageDialog(editBookFrame, "Invalid Year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;    
                                        }
                                      }
                                         
                                      if(!txtEdition.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtEdition.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(editBookFrame, "Book Edition must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                      
                                        if(intYear <= 0) {
                                        JOptionPane.showMessageDialog(editBookFrame, "Invalid Book Edition" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(editBookFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "Book");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("Publisher", txtPublisher.getText().trim());
                                        information.put("SeriesTitle", txtSeries.getText().trim());
                                        information.put("Edition", txtEdition.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        rm.editReference("Book", currentAuthor.getText(), currentYear.getText(), currentTitle.getText(), information);
                                        JOptionPane.showMessageDialog(editBookFrame, "Edit Book successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });   
       southPanel.add(btnSave);    
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);     
       editBookFrame.pack();
       editBookFrame.setVisible(true);  
   }
   
   /**
    * display program's new ConferencePaper frame
    * @exception IOException if an error occurs when input and output file
    */
   public void makeNewConferencePaperFrame() throws IOException
   {
       hideAllFrame();
       newConferencePaperFrame = new JFrame("Reference Management System - New Reference");  
       newConferencePaperFrame.setResizable(false);
       makeMenuBar(newConferencePaperFrame);       
       Container contentPane = newConferencePaperFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));  
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("Conference Paper");
       final JTextField txtAuthor = new JTextField(50);
       final JTextField txtYearCreated = new JTextField(30);
       final JTextField txtTitle = new JTextField(30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));    
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(5,0,0,5));
       JLabel lblPublisher = new JLabel("Publisher: ");
       JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
       JLabel lblPlaceHeld = new JLabel("Place Held: ");
       JLabel lblTimeHeld = new JLabel("Time Held (dd/mm/yyyy): ");
       JLabel lblPageNumber = new JLabel("Page Number: ");
       centerPanel1.add(lblPublisher);
       centerPanel1.add(lblPlaceofPublic);
       centerPanel1.add(lblPlaceHeld);
       centerPanel1.add(lblTimeHeld);
       centerPanel1.add(lblPageNumber);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(5,0,0,5));
       final JTextField txtPublisher = new JTextField(30);
       final JTextField txtPlaceofPublic = new JTextField(30);
       final JTextField txtPlaceHeld = new JTextField(30);
       final JTextField txtTimeHeld = new JTextField(30);
       final JTextField txtPageNumber = new JTextField(30);
       centerPanel2.add(txtPublisher);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel2.add(txtPlaceHeld);
       centerPanel2.add(txtTimeHeld);
       centerPanel2.add(txtPageNumber);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new BorderLayout());
       southPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Leave the content field blank if you do not want to add Note"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel southPanel1 = new JPanel();
       southPanel1.setLayout(new BoxLayout(southPanel1, BoxLayout.Y_AXIS));
       JPanel southPanel2 = new JPanel();
       southPanel2.setLayout(new BoxLayout(southPanel2, BoxLayout.Y_AXIS));
       JPanel southPanel3 = new JPanel();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       java.util.Date date = new java.util.Date();
       JLabel lblDate = new JLabel("Date Created: ");
       final JLabel dateContent = new JLabel(dateFormat.format(date).toString());
       JLabel lblNoteContent = new JLabel("Note Content:");
       final JTextField txtNoteText = new JTextField();
       JButton btnSave = new JButton("Save", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference("ConferencePaper", txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to add new
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newConferencePaperFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newConferencePaperFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                      
                                        if(intYear <= 0) {
                                        JOptionPane.showMessageDialog(newConferencePaperFrame, "Invalid year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      
                                      if(!txtTimeHeld.getText().trim().equals("")) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        try {
                                            Date d = sdf.parse(txtTimeHeld.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(newConferencePaperFrame, "Please enter Time Held in this format dd/mm/yyyy" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                      }
                                      
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(newConferencePaperFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "ConferencePaper");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("Publisher", txtPublisher.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        information.put("PlaceHeld", txtPlaceHeld.getText().trim());
                                        information.put("TimeHeld", txtTimeHeld.getText().trim());
                                        information.put("PageNumber", txtPageNumber.getText().trim());
                                        rm.addNewReference(information, dateContent.getText(), txtNoteText.getText().trim());
                                        JOptionPane.showMessageDialog(newConferencePaperFrame, "Add new Conference Paper successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });
       southPanel1.add(lblDate);
       southPanel1.add(lblNoteContent);
       southPanel2.add(dateContent);
       southPanel2.add(txtNoteText);
       southPanel3.add(btnSave);  
       southPanel.add(southPanel1, BorderLayout.WEST);
       southPanel.add(southPanel2, BorderLayout.CENTER);
       southPanel.add(southPanel3, BorderLayout.SOUTH);       
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);       
       newConferencePaperFrame.pack();
       newConferencePaperFrame.setVisible(true);  
   }
   
   /**
    * display program's new Journal frame
    * @exception IOException if an error occurs when input and output file
    */
   public void makeNewJournalFrame() throws IOException
   {
       hideAllFrame();
       newJournalFrame = new JFrame("Reference Management System - New Reference");  
       newJournalFrame.setResizable(false);
       makeMenuBar(newJournalFrame);
       Container contentPane = newJournalFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("Journal");
       final JTextField txtAuthor = new JTextField(50);
       final JTextField txtYearCreated = new JTextField(30);
       final JTextField txtTitle = new JTextField(30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(5,0,0,5));
       JLabel lblPeriodical = new JLabel("Periodical Title: ");
       JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
       JLabel lblVolume = new JLabel("Volume: ");
       JLabel lblTime = new JLabel("Day and Month(dd/MM): ");
       JLabel lblPageNumber = new JLabel("Page Number: ");
       centerPanel1.add(lblPeriodical);
       centerPanel1.add(lblPlaceofPublic);
       centerPanel1.add(lblVolume);
       centerPanel1.add(lblTime);
       centerPanel1.add(lblPageNumber);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(5,0,0,5));
       final JTextField txtPeriodical = new JTextField(30);
       final JTextField txtPlaceofPublic = new JTextField(30);
       final JTextField txtVolume = new JTextField(30);
       final JTextField txtTime = new JTextField(30);
       final JTextField txtPageNumber = new JTextField(30);
       centerPanel2.add(txtPeriodical);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel2.add(txtVolume);
       centerPanel2.add(txtTime);
       centerPanel2.add(txtPageNumber);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new BorderLayout());
       southPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Leave the content field blank if you do not want to add Note"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel southPanel1 = new JPanel();
       southPanel1.setLayout(new BoxLayout(southPanel1, BoxLayout.Y_AXIS));
       JPanel southPanel2 = new JPanel();
       southPanel2.setLayout(new BoxLayout(southPanel2, BoxLayout.Y_AXIS));
       JPanel southPanel3 = new JPanel();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       java.util.Date date = new java.util.Date();
       JLabel lblDate = new JLabel("Date Created: ");
       final JLabel dateContent = new JLabel(dateFormat.format(date).toString());
       JLabel lblNoteContent = new JLabel("Note Content:");
       final JTextField txtNoteText = new JTextField();
       JButton btnSave = new JButton("Save", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference("Journal", txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to add new
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newJournalFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newJournalFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                      
                                        if(intYear <= 0) {
                                        JOptionPane.showMessageDialog(newJournalFrame, "Invalid year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      
                                      if(!txtVolume.getText().trim().equals("")) {
                                        int intVolume = -1;
                                        try {
                                            intVolume = Integer.parseInt(txtVolume.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newJournalFrame, "Volume must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                      
                                        if(intVolume <= 0) {
                                        JOptionPane.showMessageDialog(newJournalFrame, "Invalid volume" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      
                                      if(!txtTime.getText().trim().equals("")) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
                                        try {
                                            Date d = sdf.parse(txtTime.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(newJournalFrame, "Please enter Day and Month in this format dd/mm" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                      }
                                      
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(newJournalFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "Journal");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("PeriodicalTitle", txtPeriodical.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        information.put("Volume", txtVolume.getText().trim());
                                        information.put("Time", txtTime.getText().trim());
                                        information.put("PageNumber", txtPageNumber.getText().trim());
                                        rm.addNewReference(information, dateContent.getText(), txtNoteText.getText().trim());
                                        JOptionPane.showMessageDialog(newJournalFrame, "Add new Journal successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });
       southPanel1.add(lblDate);
       southPanel1.add(lblNoteContent);
       southPanel2.add(dateContent);
       southPanel2.add(txtNoteText);
       southPanel3.add(btnSave);    
       southPanel.add(southPanel1, BorderLayout.WEST);
       southPanel.add(southPanel2, BorderLayout.CENTER);
       southPanel.add(southPanel3, BorderLayout.SOUTH);
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);       
       newJournalFrame.pack();
       newJournalFrame.setVisible(true);  
   }
   
   /**
    * display program's new OnlineJournal frame
    * @exception IOException if an error occurs when input and output file
    */
   public void makeNewOnlineJournalFrame() throws IOException
   {
       hideAllFrame();
       newOnlineJournalFrame = new JFrame("Reference Management System - New Reference");  
       newOnlineJournalFrame.setResizable(false);
       makeMenuBar(newOnlineJournalFrame);
       Container contentPane = newOnlineJournalFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("Online Journal");
       final JTextField txtAuthor = new JTextField(50);
       final JTextField txtYearCreated = new JTextField(30);
       final JTextField txtTitle = new JTextField(30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(7,0,0,5));
       JLabel lblPeriodical = new JLabel("Periodical Title: ");
       JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
       JLabel lblVolume = new JLabel("Volume: ");
       JLabel lblTime = new JLabel("Day and Month(dd/MM): ");
       JLabel lblPageNumber = new JLabel("Page Number: ");
       JLabel lblTitleOfDB = new JLabel("Database Title: ");
       JLabel lblFirstViewedDate = new JLabel("First Viewed Date: ");
       centerPanel1.add(lblPeriodical);
       centerPanel1.add(lblPlaceofPublic);
       centerPanel1.add(lblVolume);
       centerPanel1.add(lblTime);
       centerPanel1.add(lblPageNumber);
       centerPanel1.add(lblTitleOfDB);
       centerPanel1.add(lblFirstViewedDate);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(7,0,0,5));
       final JTextField txtPeriodical = new JTextField(30);
       final JTextField txtPlaceofPublic = new JTextField(30);
       final JTextField txtVolume = new JTextField(30);
       final JTextField txtTime = new JTextField(30);
       final JTextField txtPageNumber = new JTextField(30);
       final JTextField txtTitleOfDB = new JTextField(30);
       final JTextField txtFirstViewedDate = new JTextField(30);
       centerPanel2.add(txtPeriodical);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel2.add(txtVolume);
       centerPanel2.add(txtTime);
       centerPanel2.add(txtPageNumber);
       centerPanel2.add(txtTitleOfDB);
       centerPanel2.add(txtFirstViewedDate);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new BorderLayout());
       southPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Leave the content field blank if you do not want to add Note"),BorderFactory.createEmptyBorder(5,5,5,5)));  
       JPanel southPanel1 = new JPanel();
       southPanel1.setLayout(new BoxLayout(southPanel1, BoxLayout.Y_AXIS));
       JPanel southPanel2 = new JPanel();
       southPanel2.setLayout(new BoxLayout(southPanel2, BoxLayout.Y_AXIS));
       JPanel southPanel3 = new JPanel();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       java.util.Date date = new java.util.Date();
       JLabel lblDate = new JLabel("Date Created: ");
       final JLabel dateContent = new JLabel(dateFormat.format(date).toString());
       JLabel lblNoteContent = new JLabel("Note Content:");
       final JTextField txtNoteText = new JTextField();
       JButton btnSave = new JButton("Save", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference("OnlineJournal", txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to add new
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newOnlineJournalFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newOnlineJournalFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                      
                                        if(intYear <= 0) {
                                        JOptionPane.showMessageDialog(newOnlineJournalFrame, "Invalid year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      
                                      if(!txtVolume.getText().trim().equals("")) {
                                        int intVolume = -1;
                                        try {
                                            intVolume = Integer.parseInt(txtVolume.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newOnlineJournalFrame, "Volume must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                      
                                        if(intVolume <= 0) {
                                        JOptionPane.showMessageDialog(newOnlineJournalFrame, "Invalid volume" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      
                                      if(!txtTime.getText().trim().equals("")) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
                                        try {
                                            Date d = sdf.parse(txtTime.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(newOnlineJournalFrame, "Please enter Day and Month in this format dd/mm" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                      }
                                      if(!txtFirstViewedDate.getText().trim().equals("")) {
                                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                                        try {
                                            Date d2 = sdf2.parse(txtFirstViewedDate.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(newOnlineJournalFrame, "Please enter First Viewed Date in this format dd/mm/yyyy" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                     }
                                     
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(newOnlineJournalFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "OnlineJournal");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("PeriodicalTitle", txtPeriodical.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        information.put("Volume", txtVolume.getText().trim());
                                        information.put("Time", txtTime.getText().trim());
                                        information.put("PageNumber", txtPageNumber.getText().trim());
                                        information.put("TitleOfDB", txtTitleOfDB.getText().trim());
                                        information.put("FirstViewedDate", txtFirstViewedDate.getText().trim());
                                        rm.addNewReference(information, dateContent.getText(), txtNoteText.getText().trim());
                                        JOptionPane.showMessageDialog(newJournalFrame, "Add new Online Journal successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });  
       southPanel1.add(lblDate);
       southPanel1.add(lblNoteContent);
       southPanel2.add(dateContent);
       southPanel2.add(txtNoteText);
       southPanel3.add(btnSave);     
       southPanel.add(southPanel1, BorderLayout.WEST);
       southPanel.add(southPanel2, BorderLayout.CENTER);
       southPanel.add(southPanel3, BorderLayout.SOUTH);       
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);     
       newOnlineJournalFrame.pack();
       newOnlineJournalFrame.setVisible(true);  
   }
   
   /**
    * display program's new WebPage frame
    * @exception IOException if an error occurs when input and output file
    */
   public void makeNewWebPageFrame() throws IOException
   {
       hideAllFrame();
       newWebPageFrame = new JFrame("Reference Management System - New Reference");  
       newWebPageFrame.setResizable(false);
       makeMenuBar(newWebPageFrame);
       Container contentPane = newWebPageFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));  
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("WebPage");
       final JTextField txtAuthor = new JTextField(50);
       final JTextField txtYearCreated = new JTextField(30);
       final JTextField txtTitle = new JTextField(30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));  
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(3,0,0,5));
       JLabel lblGroupHosting = new JLabel("Group Hosting: ");
       JLabel lblDateViewed = new JLabel("First Viewed Date: ");
       JLabel lblURL = new JLabel("URL Address: ");
       centerPanel1.add(lblGroupHosting);
       centerPanel1.add(lblDateViewed);
       centerPanel1.add(lblURL);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(3,0,0,5));
       final JTextField txtGroupHosting = new JTextField(30);
       final JTextField txtDateViewed = new JTextField(30);
       final JTextField txtURL = new JTextField(30);
       centerPanel2.add(txtGroupHosting);
       centerPanel2.add(txtDateViewed);
       centerPanel2.add(txtURL);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new BorderLayout());
       southPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Leave the content field blank if you do not want to add Note"),BorderFactory.createEmptyBorder(5,5,5,5)));  
       JPanel southPanel1 = new JPanel();
       southPanel1.setLayout(new BoxLayout(southPanel1, BoxLayout.Y_AXIS));
       JPanel southPanel2 = new JPanel();
       southPanel2.setLayout(new BoxLayout(southPanel2, BoxLayout.Y_AXIS));
       JPanel southPanel3 = new JPanel();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       java.util.Date date = new java.util.Date();
       JLabel lblDate = new JLabel("Date Created: ");
       final JLabel dateContent = new JLabel(dateFormat.format(date).toString());
       JLabel lblNoteContent = new JLabel("Note Content:");
       final JTextField txtNoteText = new JTextField();
       JButton btnSave = new JButton("Save", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference("WebPage", txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to add new
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newWebPageFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newWebPageFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                      
                                        if(intYear <= 0) {
                                            JOptionPane.showMessageDialog(newWebPageFrame, "Invalid year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      
                                      if(txtDateViewed.getText().trim().equals("")) {
                                            JOptionPane.showMessageDialog(newOnlineJournalFrame, "First Viewed Date cannot be nul" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;           
                                      }
                                      else if(!txtDateViewed.getText().trim().equals("")) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        try {
                                            Date d = sdf.parse(txtDateViewed.getText().trim());
                                        }
                                        catch(ParseException ex) {
                                                JOptionPane.showMessageDialog(newOnlineJournalFrame, "Please enter First Viewed Date in this format dd/mm/yyyy" , "Error", JOptionPane.ERROR_MESSAGE);  
                                                saveOK = false;      
                                        }
                                      }
                                      
                                      if(txtURL.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newWebPageFrame, "URL cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(newWebPageFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "WebPage");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("GroupHosting", txtGroupHosting.getText().trim());
                                        information.put("DateViewed", txtDateViewed.getText().trim());
                                        information.put("URL", txtURL.getText().trim());
                                        rm.addNewReference(information, dateContent.getText(), txtNoteText.getText().trim());
                                        JOptionPane.showMessageDialog(newWebPageFrame, "Add new WebPage successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });
       southPanel1.add(lblDate);
       southPanel1.add(lblNoteContent);
       southPanel2.add(dateContent);
       southPanel2.add(txtNoteText);
       southPanel3.add(btnSave);
       southPanel.add(southPanel1, BorderLayout.WEST);
       southPanel.add(southPanel2, BorderLayout.CENTER);
       southPanel.add(southPanel3, BorderLayout.SOUTH);   
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);      
       newWebPageFrame.pack();
       newWebPageFrame.setVisible(true);  
   }
   
   /**
    * display program's new Thesis frame
    * @exception IOException if an error occurs when input and output file
    */
   public void makeNewThesisFrame() throws IOException
   {
       hideAllFrame();
       newThesisFrame = new JFrame("Reference Management System - New Reference");  
       newThesisFrame.setResizable(false);
       makeMenuBar(newThesisFrame);
       Container contentPane = newThesisFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));     
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("Thesis");
       final JTextField txtAuthor = new JTextField(50);
       final JTextField txtYearCreated = new JTextField(30);
       final JTextField txtTitle = new JTextField(30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));  
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(3,0,0,5));
       JLabel lblPublisher = new JLabel("Publisher: ");
       JLabel lblPlaceofPublic = new JLabel("Place of Public: ");
       JLabel lblLocation = new JLabel("Store Location: ");
       centerPanel1.add(lblPublisher);
       centerPanel1.add(lblPlaceofPublic);
       centerPanel1.add(lblLocation);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(3,0,0,5));
       final JTextField txtPublisher = new JTextField(30);
       final JTextField txtPlaceofPublic = new JTextField(30);
       final JTextField txtLocation = new JTextField(30);
       centerPanel2.add(txtPublisher);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel2.add(txtLocation);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new BorderLayout());
       southPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Leave the content field blank if you do not want to add Note"),BorderFactory.createEmptyBorder(5,5,5,5)));       
       JPanel southPanel1 = new JPanel();
       southPanel1.setLayout(new BoxLayout(southPanel1, BoxLayout.Y_AXIS));
       JPanel southPanel2 = new JPanel();
       southPanel2.setLayout(new BoxLayout(southPanel2, BoxLayout.Y_AXIS));
       JPanel southPanel3 = new JPanel();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       java.util.Date date = new java.util.Date();
       JLabel lblDate = new JLabel("Date Created: ");
       final JLabel dateContent = new JLabel(dateFormat.format(date).toString());
       JLabel lblNoteContent = new JLabel("Note Content:");
       final JTextField txtNoteText = new JTextField();
       JButton btnSave = new JButton("Save", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try
                                   {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference("Thesis", txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to add new
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newThesisFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newThesisFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                      
                                        if(intYear <= 0) {
                                        JOptionPane.showMessageDialog(newThesisFrame, "Invalid year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(newThesisFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "Thesis");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("Publisher", txtPublisher.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        information.put("Location", txtLocation.getText().trim());
                                        rm.addNewReference(information, dateContent.getText(), txtNoteText.getText().trim());
                                        JOptionPane.showMessageDialog(newThesisFrame, "Add new Thesis successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex){}
                                }
                            });
       southPanel1.add(lblDate);
       southPanel1.add(lblNoteContent);
       southPanel2.add(dateContent);
       southPanel2.add(txtNoteText);
       southPanel3.add(btnSave);
       southPanel.add(southPanel1, BorderLayout.WEST);
       southPanel.add(southPanel2, BorderLayout.CENTER);
       southPanel.add(southPanel3, BorderLayout.SOUTH);   
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);  
       newThesisFrame.pack();
       newThesisFrame.setVisible(true);  
   }
   
   /**
    * display program's new Book frame
    * @exception IOException if an error occurs when input and output file
    */
   public void makeNewBookFrame() throws IOException
   {
       hideAllFrame();
       newBookFrame = new JFrame("Reference Management System - New Reference");  
       newBookFrame.setResizable(false);
       makeMenuBar(newBookFrame);
       Container contentPane = newBookFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout(6,6));
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new BorderLayout(6,6));
       northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Common Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel northPanel1 = new JPanel();
       northPanel1.setLayout(new GridLayout(4,0,0,5));
       JLabel lblType = new JLabel("Type: ");
       JLabel lblAuthor = new JLabel("Author: ");
       JLabel lblYearCreated = new JLabel("Year Created: ");
       JLabel lblTitle = new JLabel("Title: ");
       northPanel1.add(lblType);
       northPanel1.add(lblAuthor);
       northPanel1.add(lblYearCreated);
       northPanel1.add(lblTitle);
       JPanel northPanel2 = new JPanel();
       northPanel2.setLayout(new GridLayout(4,0,0,5));
       JLabel lblTypeContent = new JLabel("Book");
       final JTextField txtAuthor = new JTextField(50);
       final JTextField txtYearCreated = new JTextField(30);
       final JTextField txtTitle = new JTextField(30);
       northPanel2.add(lblTypeContent);
       northPanel2.add(txtAuthor);
       northPanel2.add(txtYearCreated);
       northPanel2.add(txtTitle);
       northPanel.add(northPanel1, BorderLayout.WEST);
       northPanel.add(northPanel2, BorderLayout.CENTER);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout(6,6));
       centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Specialized Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new GridLayout(4,0,0,5));
       JLabel lblPublisher = new JLabel("Publisher: ");
       JLabel lblSeries = new JLabel("Series Title: ");
       JLabel lblEdition = new JLabel("Edition: ");
       JLabel lblPlaceOfPublic = new JLabel("Place of Publication: ");
       centerPanel1.add(lblPublisher);
       centerPanel1.add(lblSeries);
       centerPanel1.add(lblEdition);
       centerPanel1.add(lblPlaceOfPublic);
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new GridLayout(4,0,0,5));
       final JTextField txtPublisher = new JTextField(30);
       final JTextField txtSeries = new JTextField(30);
       final JTextField txtEdition = new JTextField(30);
       final JTextField txtPlaceofPublic = new JTextField(30);
       centerPanel2.add(txtPublisher);
       centerPanel2.add(txtSeries);
       centerPanel2.add(txtEdition);
       centerPanel2.add(txtPlaceofPublic);
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel(new BorderLayout());
       southPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Leave the content field blank if you do not want to add Note"),BorderFactory.createEmptyBorder(5,5,5,5)));
       JPanel southPanel1 = new JPanel();
       southPanel1.setLayout(new BoxLayout(southPanel1, BoxLayout.Y_AXIS));
       JPanel southPanel2 = new JPanel();
       southPanel2.setLayout(new BoxLayout(southPanel2, BoxLayout.Y_AXIS));
       JPanel southPanel3 = new JPanel();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       java.util.Date date = new java.util.Date();
       JLabel lblDate = new JLabel("Date Created: ");
       final JLabel dateContent = new JLabel(dateFormat.format(date).toString());
       JLabel lblNoteContent = new JLabel("Note Content:");
       final JTextField txtNoteText = new JTextField();
       JButton btnSave = new JButton("Save", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference("Book", txtAuthor.getText().trim(), txtYearCreated.getText().trim(), txtTitle.getText().trim(), br);
                                      boolean saveOK = true;
                                      //validation of information before call method to add new
                                      if(txtTitle.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newBookFrame, "Title cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      if(!txtYearCreated.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtYearCreated.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newBookFrame, "Year must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intYear <= 0) {
                                        JOptionPane.showMessageDialog(newBookFrame, "Invalid year" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      
                                      if(!txtEdition.getText().trim().equals("")) {
                                        int intYear = -1;
                                        try {
                                            intYear = Integer.parseInt(txtEdition.getText().trim());         
                                        } 
                                        catch(NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(newBookFrame, "Book Edition must be a positive number" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false;      
                                        }
                                        if(intYear <= 0) {
                                        JOptionPane.showMessageDialog(newBookFrame, "Invalid Book Edition" , "Error", JOptionPane.ERROR_MESSAGE);  
                                            saveOK = false; 
                                        }
                                      }
                                      if(refExisted) {
                                        JOptionPane.showMessageDialog(newBookFrame, "Reference with the same combination of Type, Author, Year and Title already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      if(saveOK) {
                                        HashMap information = new HashMap();
                                        information.put("Type", "Book");
                                        information.put("Author", txtAuthor.getText().trim());
                                        information.put("Year", txtYearCreated.getText().trim());
                                        information.put("Title", txtTitle.getText().trim());
                                        information.put("Publisher", txtPublisher.getText().trim());
                                        information.put("SeriesTitle", txtSeries.getText().trim());
                                        information.put("Edition", txtEdition.getText().trim());
                                        information.put("PlaceOfPublic", txtPlaceofPublic.getText().trim());
                                        
                                        rm.addNewReference(information, dateContent.getText(), txtNoteText.getText().trim());
                                        JOptionPane.showMessageDialog(newBookFrame, "Add new Book successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);  
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ex) {}
                                }
                            });   
       southPanel1.add(lblDate);
       southPanel1.add(lblNoteContent);
       southPanel2.add(dateContent);
       southPanel2.add(txtNoteText);
       southPanel3.add(btnSave);    
       southPanel.add(southPanel1, BorderLayout.WEST);
       southPanel.add(southPanel2, BorderLayout.CENTER);
       southPanel.add(southPanel3, BorderLayout.SOUTH);       
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);   
       newBookFrame.pack();
       newBookFrame.setVisible(true);  
   }
   
   /**
    * display program's Reference Management (of a Project) frame
    * @param aProject Project that has reference list being updated
    * @exception IOException if an error occurs when input and output file
    */
   public void makeReferenceManagementFrame(Project aProject) throws IOException
   {
       hideAllFrame();
       referenceManagementFrame = new JFrame("Reference Management System - Manage Reference"); 
       referenceManagementFrame.setResizable(false);
       makeMenuBar(referenceManagementFrame);
       Container contentPane = referenceManagementFrame.getContentPane(); 
       contentPane.setLayout(new BorderLayout());
       JPanel northPanel = new JPanel();
       northPanel.setLayout(new FlowLayout());
       JLabel lblProject = new JLabel("Project Name: ");
       final JLabel lblProjectName = new JLabel(aProject.getName());
       lblProjectName.setForeground(Color.blue); 
       northPanel.add(lblProject);
       northPanel.add(lblProjectName);
       JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new BorderLayout());      
       JPanel centerPanel1 = new JPanel();
       centerPanel1.setLayout(new BoxLayout(centerPanel1, BoxLayout.Y_AXIS));
       JPanel centerPanel2 = new JPanel();
       centerPanel2.setLayout(new BoxLayout(centerPanel2, BoxLayout.Y_AXIS));
       JLabel lblUnusedRefList = new JLabel("Unused References");
       lblUnusedRefList.setFont(new Font("Courier New", Font.BOLD, 16));
       lblUnusedRefList.setForeground(Color.RED);
       JLabel lblUsingRefList = new JLabel("Using References");
       lblUsingRefList.setFont(new Font("Courier New", Font.BOLD, 16));
       lblUsingRefList.setForeground(Color.RED);
       final JTable unusedTable = new JTable();
       final DefaultTableModel model = new DefaultTableModel();
       unusedTable.setModel(model);
       unusedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
       model.setColumnIdentifiers(new String[] {"Type", "Author", "Year", "Title"}); 
       BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
       //load all References from file
       ArrayList<Reference> myUnusedReferences = Reference.readAllReferences(br);
       boolean used=false; 
       //go through all of them, any reference that matches ont of the current reference in use by the project is excluded from the list
       for (Reference unusedR : myUnusedReferences) {
           for (Reference usingR : aProject.myReferences) {
                if(unusedR.getAuthor().equals(usingR.getAuthor()) && unusedR.getYearCreated().equals(usingR.getYearCreated()) && unusedR.getTitle().equals(usingR.getTitle()))
                    used = true;
           }   
           if(!used) {
                model.addRow(new String[] {unusedR.getClass().getName(), unusedR.getAuthor(),unusedR.getYearCreated(), unusedR.getTitle()});
           }
           used = false;
       }
       JScrollPane unusedScrollPane = new JScrollPane(unusedTable);
       final JTable reftable = new JTable();
       final DefaultTableModel rmodel = new DefaultTableModel();
       reftable.setModel(rmodel);
       reftable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       rmodel.setColumnIdentifiers(new String[] {"Type", "Author", "Year", "Title"}); 
       for (Reference r : aProject.myReferences) {
           rmodel.addRow(new String[] {r.getClass().getName(), r.getAuthor(),r.getYearCreated(), r.getTitle()});
       }
       JScrollPane usingScrollPane = new JScrollPane(reftable); 
       JPanel btnAddPanel = new JPanel(new FlowLayout());
       JButton btnAdd = new JButton("Add >>");
       btnAddPanel.add(btnAdd);
       btnAdd.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                    if(unusedTable.getSelectedRow() == -1) {
                                       JOptionPane.showMessageDialog(referenceManagementFrame, "Please choose a reference to add to the current reference list" , "Error", JOptionPane.ERROR_MESSAGE); 
                                    }
                                    else {
                                       String type = unusedTable.getValueAt(unusedTable.getSelectedRow(),0).toString();
                                       String author = unusedTable.getValueAt(unusedTable.getSelectedRow(),1).toString();
                                       String year = unusedTable.getValueAt(unusedTable.getSelectedRow(),2).toString();
                                       String title = unusedTable.getValueAt(unusedTable.getSelectedRow(),3).toString();
                                       model.removeRow(unusedTable.getSelectedRow());
                                       rmodel.insertRow(rmodel.getRowCount(), new Object[]{type, author, year, title});
                                    }
                                }
                            });             
       JPanel btnRemovePanel = new JPanel(new FlowLayout());
       JButton btnRemove = new JButton("<< Remove");
       btnRemovePanel.add(btnRemove);
       btnRemove.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                    if(reftable.getSelectedRow() == -1) {
                                       JOptionPane.showMessageDialog(referenceManagementFrame, "Please choose a reference to remove from the current reference list" , "Error", JOptionPane.ERROR_MESSAGE); 
                                    }
                                    else {
                                       String type = reftable.getValueAt(reftable.getSelectedRow(),0).toString();
                                       String author = reftable.getValueAt(reftable.getSelectedRow(),1).toString();
                                       String year = reftable.getValueAt(reftable.getSelectedRow(),2).toString();
                                       String title = reftable.getValueAt(reftable.getSelectedRow(),3).toString();
                                       rmodel.removeRow(reftable.getSelectedRow());
                                       model.insertRow(model.getRowCount(), new Object[]{type, author, year, title});
                                    }
                                }
                            });
       centerPanel1.add(lblUnusedRefList);
       centerPanel2.add(lblUsingRefList);
       centerPanel1.add(unusedScrollPane);
       centerPanel2.add(usingScrollPane);
       centerPanel1.add(btnAddPanel);
       centerPanel2.add(btnRemovePanel); 
       centerPanel.add(centerPanel1, BorderLayout.WEST);
       centerPanel.add(centerPanel2, BorderLayout.CENTER);
       JPanel southPanel = new JPanel();
       southPanel.setLayout(new BorderLayout()); 
       JPanel southPanel1 = new JPanel();
       southPanel1.setLayout(new FlowLayout());
       JPanel southPanel2 = new JPanel();
       southPanel2.setLayout(new FlowLayout());  
       JButton btnSave = new JButton("Save changes", saveIcon);
       btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                      Project projIns = new Project();
                                      boolean projNameExisted = projIns.findProjectName(lblProjectName.getText().trim(), br);
                                      boolean refExisted = true;
                                      boolean saveOK = true;
                                      int countErr =0;
                                      ArrayList<String> refList = new ArrayList<String>();
                                      if(!projNameExisted) {
                                          JOptionPane.showMessageDialog(newProjectFrame, "Project " + lblProjectName.getText() + " has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                          saveOK = false;
                                      }
                                      br.close();
                                      for(int i=0; i< reftable.getRowCount(); i++) {
                                        br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                        String refType = reftable.getValueAt(i,0).toString();
                                        String refAuthor = reftable.getValueAt(i,1).toString();
                                        String refYear = reftable.getValueAt(i,2).toString();
                                        String refTitle = reftable.getValueAt(i,3).toString();
                                        refExisted = Reference.checkExistReference(refType, refAuthor, refYear, refTitle, br);
                                        if(!refExisted) {
                                            saveOK = false;
                                            JOptionPane.showMessageDialog(newProjectFrame, "Reference " + refType + "-" + refAuthor + " " + refYear + " " + refTitle + " has been deleted or DataFile is corrupted" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        }
                                        else {
                                            br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                            refList.add(Reference.findReferenceNo(refType, refAuthor, refYear, refTitle, br));
                                        }
                                      }
                                      if(saveOK) {
                                        rm.updateReferenceList(lblProjectName.getText(), refList);
                                        JOptionPane.showMessageDialog(newProjectFrame, "Update Reference list successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon);   
                                        //reload the edited project and redisplay it on ProjectDetail Frame
                                        br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                        Project projIns2 = new Project();
                                        Project myProject  = projIns2.readProjectByName(lblProjectName.getText().trim(), br);
                                        makeProjectDetailFrame(myProject);
                                      }
                                   }
                                   catch(Exception ea) {}
                                }
                            });
       southPanel2.add(btnSave);
       southPanel.add(southPanel1, BorderLayout.CENTER);
       southPanel.add(southPanel2, BorderLayout.SOUTH);
       contentPane.add(northPanel, BorderLayout.NORTH);
       contentPane.add(centerPanel, BorderLayout.CENTER);
       contentPane.add(southPanel, BorderLayout.SOUTH);       
       referenceManagementFrame.pack();
       referenceManagementFrame.setVisible(true);  
   }
   
   /**
    * display program's New Note (of a Project) frame
    * @param aProject Project that has new Note being added
    * @exception IOException if an error occurs when input and output file
    */
   public void makeNewNoteFrame(Project aProject) throws IOException
   {
        hideAllFrame();
        newNoteFrame = new JFrame("Reference Management System - New Note");          
        newNoteFrame.setResizable(false);
        makeMenuBar(newNoteFrame); 
        Container contentPane = newNoteFrame.getContentPane(); 
        contentPane.setLayout(new BorderLayout(6,6));
        JPanel northPanel = new JPanel();
        JLabel lblProject = new JLabel("Currently adding new Note for: ");
        final JLabel lblProjectName = new JLabel(aProject.getName());
        lblProjectName.setForeground(Color.blue);
        northPanel.add(lblProject);
        northPanel.add(lblProjectName);   
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout(6,6));
        westPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Note Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel westPanelInner1 = new JPanel();
        westPanelInner1.setLayout(new BoxLayout(westPanelInner1, BoxLayout.X_AXIS));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        JLabel lblName = new JLabel("Date Created:");
        final JLabel lblCurrentDate = new JLabel(dateFormat.format(date));
        lblCurrentDate.setForeground(Color.red);
        westPanelInner1.add(lblName);
        westPanelInner1.add(Box.createHorizontalStrut(5));
        westPanelInner1.add(lblCurrentDate);
        westPanelInner1.add(Box.createHorizontalStrut(5));
        JPanel westPanelInner2 = new JPanel();
        westPanelInner2.setLayout(new BoxLayout(westPanelInner2, BoxLayout.X_AXIS));
        JLabel lblDesc = new JLabel("Content: ");
        final JTextField txtContent = new JTextField(60);
        JScrollPane scrollContent = new JScrollPane(txtContent);
        westPanelInner2.add(lblDesc);
        westPanelInner2.add(Box.createHorizontalStrut(30));
        westPanelInner2.add(scrollContent);
        westPanelInner2.add(Box.createHorizontalStrut(5));
        JPanel westPanelInner3 = new JPanel();
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                      Project projIns = new Project();
                                      //call method to check for the existence of current project
                                      boolean projNameExisted = projIns.findProjectName(lblProjectName.getText().trim(), br);
                                      boolean noteExisted = false;
                                      boolean saveOK = true;
                                      if(txtContent.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newProjectFrame, "Note content cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);
                                        saveOK = false;
                                      }
                                      else if(!projNameExisted) {
                                        JOptionPane.showMessageDialog(newProjectFrame, "Project " + lblProjectName.getText() + " cannot be found. Project has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                      //check combination of fields of new Note with other Notes in data file
                                      noteExisted = projIns.checkExistedNote(lblProjectName.getText(), lblCurrentDate.getText(), txtContent.getText(), br);
                                      br.close();
                                      if(noteExisted) {
                                         JOptionPane.showMessageDialog(newProjectFrame, "Note with the same date and content already existed" , "Error", JOptionPane.ERROR_MESSAGE);   
                                         saveOK = false;
                                      }
                                      if(saveOK) {
                                        rm.saveProjectNote(lblProjectName.getText(), txtContent.getText(), lblCurrentDate.getText());
                                        JOptionPane.showMessageDialog(newProjectFrame, "Add new Note successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon); 
                                        //reload the edited project and redisplay it on ProjectDetail Frame
                                        br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                        Project projIns2 = new Project();
                                        Project myProject  = projIns2.readProjectByName(lblProjectName.getText().trim(), br);
                                        makeProjectDetailFrame(myProject);
                                      }
                                   }
                                   catch(Exception ea){}
                                }
                            }); 
        westPanelInner3.add(btnSave);
        westPanel.add(westPanelInner1, BorderLayout.NORTH);
        westPanel.add(westPanelInner2, BorderLayout.CENTER);
        westPanel.add(westPanelInner3, BorderLayout.SOUTH);    
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(westPanel, BorderLayout.WEST); 
        newNoteFrame.pack();
        newNoteFrame.setVisible(true);  
   }
   
   /**
    * display program's New Note (of a Reference) frame
    * @param aReference Reference that has new Note being added
    * @exception IOException if an error occurs when input and output file
    */
   public void makeNewNoteFrame(Reference aReference) throws IOException
   {
        hideAllFrame();
        newNoteFrame = new JFrame("Reference Management System - New Note");              
        newNoteFrame.setResizable(false);
        makeMenuBar(newNoteFrame); 
        Container contentPane = newNoteFrame.getContentPane(); 
        contentPane.setLayout(new BorderLayout(6,6));
        JPanel northPanel = new JPanel();
        JLabel lblRef = new JLabel("Currently adding new Note for: ");
        final JLabel lblReferenceName = new JLabel(aReference.getAuthor() + " " + aReference.getYearCreated() + " " + aReference.getTitle());
        lblReferenceName.setForeground(Color.blue);
        northPanel.add(lblRef);
        northPanel.add(lblReferenceName);
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout(6,6));
        westPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Note Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel westPanelInner1 = new JPanel();
        westPanelInner1.setLayout(new BoxLayout(westPanelInner1, BoxLayout.X_AXIS));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        JLabel lblName = new JLabel("Date Created:");
        final JLabel lblCurrentDate = new JLabel(dateFormat.format(date));
        lblCurrentDate.setForeground(Color.red);
        westPanelInner1.add(lblName);
        westPanelInner1.add(Box.createHorizontalStrut(5));
        westPanelInner1.add(lblCurrentDate);
        westPanelInner1.add(Box.createHorizontalStrut(5));
        JPanel westPanelInner2 = new JPanel();
        westPanelInner2.setLayout(new BoxLayout(westPanelInner2, BoxLayout.X_AXIS));
        JLabel lblDesc = new JLabel("Content: ");
        final JTextField txtContent = new JTextField(60);
        JScrollPane scrollContent = new JScrollPane(txtContent);
        westPanelInner2.add(lblDesc);
        westPanelInner2.add(Box.createHorizontalStrut(30));
        westPanelInner2.add(scrollContent);
        westPanelInner2.add(Box.createHorizontalStrut(5));
        final Reference myReference = aReference;
        JPanel westPanelInner3 = new JPanel();
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      //check for exsitence of current Reference
                                      boolean refNameExisted = Reference.checkExistReference(myReference.getClass().getName(), myReference.getAuthor(),  myReference.getYearCreated(),  myReference.getTitle() ,br);
                                      boolean noteExisted = false;
                                      boolean saveOK = true;
                                      if(txtContent.getText().equals("")) {
                                        JOptionPane.showMessageDialog(newProjectFrame, "Note content cannot be null" , "Error", JOptionPane.ERROR_MESSAGE);  
                                        saveOK = false;
                                      }
                                      else if(!refNameExisted) {
                                        JOptionPane.showMessageDialog(newProjectFrame, "Reference cannot be found. Reference has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOK = false;
                                      }
                                      br.close();
                                      br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      //check combination of fields of new Note with other Notes in data file
                                      noteExisted = Reference.checkExistedNote(myReference.getClass().getName(), myReference.getAuthor(),  myReference.getYearCreated(),  myReference.getTitle(), lblCurrentDate.getText(), txtContent.getText().trim(), br);
                                      br.close();
                                      if(noteExisted) {
                                         JOptionPane.showMessageDialog(newProjectFrame, "Note with the same date and content already existed" , "Error", JOptionPane.ERROR_MESSAGE); 
                                         saveOK = false;
                                      }
                                      if(saveOK) {
                                        rm.saveReferenceNote(myReference.getClass().getName(), myReference.getAuthor(),  myReference.getYearCreated(),  myReference.getTitle(), txtContent.getText().trim(), lblCurrentDate.getText());
                                        JOptionPane.showMessageDialog(newProjectFrame, "Add new Note successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon); 
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ea){}
                                }
                            });
        westPanelInner3.add(btnSave);      
        westPanel.add(westPanelInner1, BorderLayout.NORTH);
        westPanel.add(westPanelInner2, BorderLayout.CENTER);
        westPanel.add(westPanelInner3, BorderLayout.SOUTH);       
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(westPanel, BorderLayout.WEST);   
        newNoteFrame.pack();
        newNoteFrame.setVisible(true);  
   }
   
   /**
    * display program's Edit Note (of a Project) frame
    * @param aProject Project that has Note being edited
    * @param noteText current Text of the Note
    * @param noteDate current DateCreated of the Note
    * @exception IOException if an error occurs when input and output file
    */
   public void makeEditNoteFrame(Project aProject, String noteText, String noteDate) throws IOException
   {
        hideAllFrame();
        editNoteFrame = new JFrame("Reference Management System - Edit Note");  
        editNoteFrame.setResizable(false);
        makeMenuBar(editNoteFrame);      
        Container contentPane = editNoteFrame.getContentPane(); 
        contentPane.setLayout(new BorderLayout(6,6));
        JPanel northPanel = new JPanel();
        JLabel lblProject = new JLabel("Currently editing Note for Project: ");
        final JLabel lblProjectName = new JLabel(aProject.getName());
        lblProjectName.setForeground(Color.blue);
        northPanel.add(lblProject);
        northPanel.add(lblProjectName);
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout(6,6));
        westPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Note Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel westPanelInner1 = new JPanel();
        westPanelInner1.setLayout(new BoxLayout(westPanelInner1, BoxLayout.X_AXIS));
        final JLabel currentDate = new JLabel(noteDate);
        currentDate.setVisible(false);
        final JLabel currentText = new JLabel(noteText);
        currentText.setVisible(false);
        JLabel lblName = new JLabel("Date Created(dd/MM/yyyy):");
        final JTextField txtCurrentDate = new JTextField(noteDate, 10);
        westPanelInner1.add(currentDate);
        westPanelInner1.add(currentText);
        westPanelInner1.add(lblName);
        westPanelInner1.add(Box.createHorizontalStrut(5));
        westPanelInner1.add(txtCurrentDate);
        westPanelInner1.add(Box.createHorizontalStrut(5));
        JPanel westPanelInner2 = new JPanel();
        westPanelInner2.setLayout(new BoxLayout(westPanelInner2, BoxLayout.X_AXIS));
        JLabel lblDesc = new JLabel("Content: ");
        final JTextField txtContent = new JTextField(noteText, 60);
        JScrollPane scrollContent = new JScrollPane(txtContent);
        westPanelInner2.add(lblDesc);
        westPanelInner2.add(Box.createHorizontalStrut(30));
        westPanelInner2.add(scrollContent);
        westPanelInner2.add(Box.createHorizontalStrut(5));
        JPanel westPanelInner3 = new JPanel();
        JButton btnSave = new JButton("Edit Note", saveIcon);
        btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                      Project projIns = new Project();
                                      boolean projNameExisted = projIns.findProjectName(lblProjectName.getText().trim(), br);
                                      boolean noteExisted = false;
                                      boolean saveOk = true;
                                      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                      if(txtContent.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editProjectFrame, "Note content cannot be null" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOk = false;
                                      }
                                      if(txtCurrentDate.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editProjectFrame, "Date cannot be null" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOk = false;
                                      }
                                      //validate DateCreated input
                                      try {
                                            Date testDate = sdf.parse(txtCurrentDate.getText().trim());    
                                      }
                                      catch(ParseException pe) {
                                          JOptionPane.showMessageDialog(editProjectFrame, "Date must be in the format dd/MM/yyyy" , "Error", JOptionPane.ERROR_MESSAGE); 
                                          saveOk = false;
                                      }   
                                      if(!projNameExisted) {
                                        JOptionPane.showMessageDialog(editProjectFrame, "Project " + lblProjectName.getText() + " cannot be found. Project has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE);
                                        saveOk = false;
                                      }
                                      br.close();
                                      br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                      noteExisted = projIns.validateEditNote(lblProjectName.getText(), currentDate.getText(), currentText.getText(), txtCurrentDate.getText(), txtContent.getText(), br);
                                      br.close();
                                      if(noteExisted) {
                                         JOptionPane.showMessageDialog(editProjectFrame, "Note with the same date and content already existed" , "Error", JOptionPane.ERROR_MESSAGE);   
                                         saveOk = false;
                                      }
                                      if(saveOk) {
                                        rm.editProjectNote(lblProjectName.getText(), currentDate.getText(), currentText.getText(), txtCurrentDate.getText(), txtContent.getText());
                                        JOptionPane.showMessageDialog(editProjectFrame, "Edit Note successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon); 
                                        //reload the edited project and redisplay it on ProjectDetail Frame
                                        br = new BufferedReader(new FileReader(PROJECT_FILE_NAME));
                                        Project projIns2 = new Project();
                                        Project myProject  = projIns2.readProjectByName(lblProjectName.getText().trim(), br);
                                        makeProjectDetailFrame(myProject);
                                      }
                                   }
                                   catch(Exception ea){}
                                }
                            });
        westPanelInner3.add(btnSave);      
        westPanel.add(westPanelInner1, BorderLayout.NORTH);
        westPanel.add(westPanelInner2, BorderLayout.CENTER);
        westPanel.add(westPanelInner3, BorderLayout.SOUTH);       
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(westPanel, BorderLayout.WEST);      
        editNoteFrame.pack();
        editNoteFrame.setVisible(true);  
   }
   
   /**
    * display program's Edit Note (of a Reference) frame
    * @param aReference Reference that has Note being edited
    * @param noteText current Text of the Note
    * @param noteDate current DateCreated of the Note
    * @exception IOException if an error occurs when input and output file
    */
   public void makeEditNoteFrame(Reference aReference, String noteText, String noteDate) throws IOException
   {
        hideAllFrame();
        editNoteFrame = new JFrame("Reference Management System - Edit Note");  
        editNoteFrame.setResizable(false);
        makeMenuBar(editNoteFrame);  
        Container contentPane = editNoteFrame.getContentPane(); 
        contentPane.setLayout(new BorderLayout(6,6));
        JPanel northPanel = new JPanel();
        JLabel lblRef = new JLabel("Currently editing Note for Reference: "); 
        final JLabel lblRefName = new JLabel(aReference.getAuthor() + " " + aReference.getYearCreated() + " " + aReference.getTitle());
        lblRefName.setForeground(Color.blue);
        northPanel.add(lblRef);
        northPanel.add(lblRefName);
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout(6,6));
        westPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Note Information"),BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel westPanelInner1 = new JPanel();
        westPanelInner1.setLayout(new BoxLayout(westPanelInner1, BoxLayout.X_AXIS));
        final JLabel currentDate = new JLabel(noteDate);
        currentDate.setVisible(false);
        final JLabel currentText = new JLabel(noteText);
        currentText.setVisible(false);
        JLabel lblName = new JLabel("Date Created(dd/MM/yyyy):");
        final JTextField txtCurrentDate = new JTextField(noteDate, 10);
        westPanelInner1.add(currentDate);
        westPanelInner1.add(currentText);
        westPanelInner1.add(lblName);
        westPanelInner1.add(Box.createHorizontalStrut(5));
        westPanelInner1.add(txtCurrentDate);
        westPanelInner1.add(Box.createHorizontalStrut(5));
        JPanel westPanelInner2 = new JPanel();
        westPanelInner2.setLayout(new BoxLayout(westPanelInner2, BoxLayout.X_AXIS));
        JLabel lblDesc = new JLabel("Content: ");
        final JTextField txtContent = new JTextField(noteText, 60);
        JScrollPane scrollContent = new JScrollPane(txtContent);
        westPanelInner2.add(lblDesc);
        westPanelInner2.add(Box.createHorizontalStrut(30));
        westPanelInner2.add(scrollContent);
        westPanelInner2.add(Box.createHorizontalStrut(5));
        final Reference myReference = aReference;
        JPanel westPanelInner3 = new JPanel();
        JButton btnSave = new JButton("Edit Note", saveIcon);
        btnSave.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
                                   try {
                                      BufferedReader br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      boolean refExisted = Reference.checkExistReference(myReference.getClass().getName(), myReference.getAuthor(), myReference.getYearCreated(), myReference.getTitle(), br);
                                      boolean noteExisted = false;
                                      boolean saveOk = true;
                                      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                      if(txtContent.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editProjectFrame, "Note content cannot be null" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOk = false;
                                      }
                                      if(txtCurrentDate.getText().equals("")) {
                                        JOptionPane.showMessageDialog(editProjectFrame, "Date cannot be null" , "Error", JOptionPane.ERROR_MESSAGE); 
                                        saveOk = false;
                                      }
                                      else {
                                          try {
                                             //validate DateCreated input
                                            Date testDate = sdf.parse(txtCurrentDate.getText().trim());    
                                        }
                                        catch(ParseException pe) {
                                            JOptionPane.showMessageDialog(editProjectFrame, "Date must be in the format dd/MM/yyyy" , "Error", JOptionPane.ERROR_MESSAGE); 
                                            saveOk = false;
                                        }
                                      }  
                                      if(!refExisted) {
                                        JOptionPane.showMessageDialog(editProjectFrame, "Reference cannot be found. Reference has been deleted or DataFile corrupted" , "Error", JOptionPane.ERROR_MESSAGE);
                                        saveOk = false;
                                      }
                                      br.close();
                                      br = new BufferedReader(new FileReader(REFERENCE_FILE_NAME));
                                      noteExisted = Reference.validateEditNote(myReference.getClass().getName(), myReference.getAuthor(), myReference.getYearCreated(), myReference.getTitle(), currentDate.getText(), currentText.getText(), txtCurrentDate.getText(), txtContent.getText(), br);
                                      br.close();
                                      if(noteExisted) {
                                         JOptionPane.showMessageDialog(editProjectFrame, "Note with the same date and content already existed" , "Error", JOptionPane.ERROR_MESSAGE);   
                                         saveOk = false;
                                      }
                                      if(saveOk) {
                                        rm.editReferenceNote(myReference.getClass().getName(), myReference.getAuthor(), myReference.getYearCreated(), myReference.getTitle(), currentDate.getText(), currentText.getText(), txtCurrentDate.getText(), txtContent.getText());
                                        JOptionPane.showMessageDialog(editProjectFrame, "Edit Note successfully" , "Success", JOptionPane.INFORMATION_MESSAGE, saveIcon); 
                                        makeMainFrame();
                                      }
                                   }
                                   catch(Exception ea){}
                                }
                            });     
        westPanelInner3.add(btnSave);    
        westPanel.add(westPanelInner1, BorderLayout.NORTH);
        westPanel.add(westPanelInner2, BorderLayout.CENTER);
        westPanel.add(westPanelInner3, BorderLayout.SOUTH); 
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(westPanel, BorderLayout.WEST);
        editNoteFrame.pack();
        editNoteFrame.setVisible(true);  
   }
}
