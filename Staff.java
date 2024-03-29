import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.LinkedList;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.SwingUtilities;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.applet.AudioClip;
import java.applet.Applet;
import java.io.File;
import javax.swing.Timer;
import java.net.MalformedURLException;

class Staff extends JPanel implements MouseListener{

    int vert,horiz,count;
    Timer time;
    LinkedList<Row> rowList;

    public Staff(){
        this.setBackground(Color.gray);
        rowList = new LinkedList<Row>();

//        rowList.add(new Row("Samples/kick.wav",16));

        this.setPreferredSize(new Dimension(400,400));
        this.setSize(400,400);

        for (int i = 0; i<rowList.size(); i++){
            this.add(rowList.get(i));
            rowList.get(i).addMouseListener(this);
        }
    }
    
    public void mousePressed(MouseEvent e){
        if (SwingUtilities.isRightMouseButton(e)){
            this.remove((Row) e.getSource());
            rowList.remove(e.getSource());
            this.revalidate();
            this.repaint();
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    public void mouseEntered(MouseEvent e){
    }

    public void mouseExited(MouseEvent e){

    }
    public void mouseClicked(MouseEvent e){

    }
    
    public void light(int index){
        boolean soloOn = false;
        for (int i = 0; i < rowList.size(); i++){
            if(rowList.get(i).isSoloed()){
                soloOn = true;
                for (int j = 0; j < rowList.size(); j++){
                    if (rowList.get(j).isSoloed())
                        rowList.get(j).light(index);
                    else
                        rowList.get(j).reset();
                 }
                break;
            }
        }
        if(!soloOn){
            for (int i = 0; i < rowList.size(); i++){
                rowList.get(i).light(index);
            }
       
        }
    }

    public void reset(){
        for (int i = 0; i < rowList.size(); i++){
            rowList.get(i).reset();
        }
    }   

    public void addSound(String fn,int length){
        rowList.add(new Row(fn,length));
        this.add(rowList.get(rowList.size()-1));
        rowList.get(rowList.size()-1).addMouseListener(this);
        this.revalidate();
        this.repaint();
    }
  
    public void setLength(int newLength){
        for (int i = 0; i<rowList.size(); i++){
            rowList.get(i).setLength(newLength);
        }
    }

    public int getLength(){
        if (rowList.size() != 0)
            return rowList.get(0).getLength();
        else
            return 0;
    }

    public String dumpString(){
        String dumpString="";
        for (int i = 0; i<rowList.size(); i++){
            dumpString += rowList.get(i).dumpString() + "\n";
        }
        return dumpString;
    }

}
