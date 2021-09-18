import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class XOGui {
    public static void main(String[] args) {
        Game XOGame = new Game();
        XOGame.setVisible(true);
    }
}
class Game extends JFrame implements MouseListener{ //สร้าง Game extends(สืบทอด)จาก JFrame
    int NSize = 5,ScreenX=500,ScreenY=500+37;
    String[][] boardArray = new String[NSize][NSize];
    String player="X";
    JPanel p1 = new JPanel();
    boolean WinStatus = false;
    int BlockSizeX = ScreenX/NSize,BlockSizeY = (ScreenY-37)/NSize;
    public Game(){ //constuctor
        StartBoard();
        setTitle("XO Game");
        setSize(ScreenX,ScreenY);
        setLocationRelativeTo(null); //ทำให้อยู่กลางจอ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//เมื่อมีการคลิกที่ปุ่ม close จะเป็นการปิดโปรแกรม
        setVisible(true);
        addMouseListener(this);
        add(p1);
    }
    
    public void paint(Graphics g){   
        if(WinStatus){
            ChangPlayer();
            g.setFont(new Font("Ubuntu", Font.PLAIN, ScreenX/10));
            g.setColor(Color.WHITE);
            g.drawRect(0,0,ScreenX,ScreenY);
            g.fillRect(0,0,ScreenX,ScreenY);
            g.setColor(Color.BLACK);
            String Wintext = player + " is WINNER"; 
            g.drawString(Wintext, 20, ScreenY/2);
            System.out.println(player+" is WIN");
        }
        else{
            int FontSize = 30;
            g.setFont(new Font("Ubuntu", Font.PLAIN, FontSize));
            for(int r=0; r<NSize; r++){
                for(int c=0; c<NSize; c++){
                    g.drawRect(c*BlockSizeX,(r*BlockSizeY)+37,BlockSizeX,BlockSizeY);
                    g.drawString(String.valueOf(boardArray[r][c]), c*BlockSizeX+(BlockSizeX*2/5), 37+((r+1)*BlockSizeY)-(BlockSizeY*2/5));
                }
            }
        }
    }
    public void mouseClicked(MouseEvent e) {
        boolean IsEmpty;
        int x = (int) Math.floor(e.getX()/BlockSizeX);
        int y = (int) Math.floor((e.getY()-37)/BlockSizeY);
        IsEmpty = boardArray[y][x]==" " ? true : false;
        if(IsEmpty){
            boardArray[y][x] = player;
            repaint();
            IsWin(y,x);
            ChangPlayer();
        }
        /*System.out.println(e.getY()+"|"+e.getX());
        System.out.println(y+"|"+x);
        for(int r=0; r<NSize; r++){
            for(int c=0; c<NSize; c++){
                System.out.print(boardArray[r][c]+"|");                
            }
            System.out.print("\n");
        }
        System.out.println("----------------");*/
    }
    public void ChangPlayer(){
        player = player=="X"? "O" : "X";
    }
    public void StartBoard(){
        for(int i = 0; i < NSize; i++) {
            for(int j = 0; j < NSize; j++) {
                boardArray[i][j] = " ";
            }
        }
    }
    public void IsWin(int row,int Col){
        boolean IsEqua,Buffer;
        //นอน
        IsEqua=true;
        for(int i = 0; i < NSize-1; i++) {
            Buffer = (boardArray[row][i] == boardArray[row][i+1]) && (boardArray[row][i] != " ") ? true : false;
            IsEqua = IsEqua & Buffer;
        }
        if(IsEqua){
            WinStatus = true;
            repaint();
        }
        //ตั้ง
        IsEqua=true;
        for(int i = 0; i < NSize-1; i++) {
            Buffer = (boardArray[i][Col] == boardArray[i+1][Col]) && (boardArray[i][Col] != " ") ? true : false;
            IsEqua = IsEqua & Buffer;
        }
        if(IsEqua){
            WinStatus = true;
            repaint();
        }
        //ซ้ายไปขวา
        IsEqua=true;
        for(int i = 0; i < NSize-1; i++) {
            Buffer = (boardArray[i][i] == boardArray[i+1][i+1]) && (boardArray[i][i] != " ") ? true : false;
            IsEqua = IsEqua & Buffer;
        }
        if(IsEqua){
            WinStatus = true;
            repaint();
        }
        //ขวาไปซ้าย
        IsEqua=true;
        int c=NSize-1;
        for(int i = 0; i < NSize-1; i++) {
            Buffer = (boardArray[i][c] == boardArray[i+1][c-1]) && (boardArray[i][c] != " ") ? true : false;
            IsEqua = IsEqua & Buffer;
            c--;
        }
        if(IsEqua){
            WinStatus = true;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}