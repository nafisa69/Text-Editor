import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //declaring properties of ext editor
    JFrame frame;
    JMenuBar menubar;
    JMenu file,edit;
    JMenuItem New, Open, Save;
    JMenuItem Cut, Copy, Paste, SelectAll, Close;
    JTextArea ta;

    TextEditor(){
        //Initialize a frame
        frame= new JFrame();
        menubar= new JMenuBar();
        ta = new JTextArea();
        file = new JMenu("File");
        edit = new JMenu("Edit");

        New = new JMenuItem("New Window");
        Open = new JMenuItem("Open File");
        Save = new JMenuItem("Save File");

        //Add action Listeners
        New.addActionListener(this);
        Open.addActionListener(this);
        Save.addActionListener(this);

        file.add(New);
        file.add(Open);
        file.add(Save);

        Cut = new JMenuItem("Cut");
        Copy = new JMenuItem("Copy");
        Paste = new JMenuItem("Paste");
        SelectAll = new JMenuItem("Select All");
        Close = new JMenuItem("Close");
        //Add action Listeners
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        SelectAll.addActionListener(this);
        Close.addActionListener(this);

        edit.add(Cut);
        edit.add(Copy);
        edit.add(Paste);
        edit.add(SelectAll);
        edit.add(Close);

        menubar.add(file);
        menubar.add(edit);


        frame.setJMenuBar(menubar);

        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        panel.add(ta,BorderLayout.CENTER);
        JScrollPane scrollPane= new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //
        panel.add(scrollPane);

        frame.add(panel);
        frame.add(ta);
        frame.setBounds(100,100,900,500);
        frame.setTitle("MyText Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    public static void main(String[] args) {
        TextEditor te = new TextEditor();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Cut){
            //Perform cut operation
            ta.cut();
        }

        if(e.getSource()==Copy){
            //Perform cut operation
            ta.copy();
        }

        if(e.getSource()==Paste){
            //Perform cut operation
            ta.paste();
        }

        if(e.getSource()==SelectAll){
            //Perform cut operation
            ta.selectAll();
        }

        if(e.getSource()==Close){
            //Perform cut operation
            System.exit(0);
        }

        if(e.getSource()==Open){
            //Perform cut operation
            JFileChooser fc = new JFileChooser("C:");
            int chooseOption = fc.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File f =fc.getSelectedFile();
                String fp= f.getPath();

                try{
                    //Initialize fle reader
                    FileReader fileReader= new FileReader(fp);
                    //Initialize bufferReader
                    BufferedReader bufferedReader= new BufferedReader(fileReader);
                    String intermediate;
                    StringBuilder output= new StringBuilder();

                    while((intermediate= bufferedReader.readLine())!=null){
                        output.append(intermediate).append("\n");
                    }
                    ta.setText(output.toString());

                } catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(e.getSource()==Save){
            //Perform cut operation
            JFileChooser fc = new JFileChooser("C:");
            int chooseOption = fc.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                File f = new File(fc.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter= new FileWriter(f);
                    BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                    //write content of file
                    ta.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(e.getSource()==New){
            TextEditor textEditor= new TextEditor();
        }
    }
}
