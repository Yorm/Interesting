
package newpackage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImgBreak {
    private BufferedImage image;
    private int width;
    private int height;
    public Color[][] pixels;
    private String[] ascii={".",",","`","\"","*","+","-",";","&","$","&","#","@","=","№"};//15
    private String[][] chars;
    
    public ImgBreak(String name) throws IOException  {
        this.image = ImageIO.read(new File("src/main/java/img/"+name));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        
        Color[][] p = new Color[width][height];
        for(int w=0;w<this.width;w++){
            for(int h=0;h<this.height;h++)
                p[h][w] = getPixelColor(h,w);
        }
        pixels=p;
        chars=new String[width][height];
    }
    
    public void imgToAscii(){  
        for(int w=0;w<this.width;w++){
            for(int h=0;h<this.height;h++)
                chars[h][w] = asciiSwitch(pixels[h][w]);
        }
        for(int w=0;w<this.width;w++){
            for(int h=0;h<this.height;h++)
                System.out.print(chars[h][w]+" ");
            System.out.println("\n");
        }
        asciiWrite();
    }
    
    private String asciiSwitch(Color color){
        switch(color.getRed()/10){
            case 0: return ascii[0];
            case 1:return ascii[1];
            case 2:return ascii[1];
            case 3:return ascii[1];
            case 4:return ascii[2];
            case 5:return ascii[2];
            case 6:return ascii[2];
            case 7:return ascii[3];
            case 8:return ascii[3];
            case 9:return ascii[4];
            case 10:return ascii[4];
            case 11:return ascii[5];
            case 12:return ascii[5];
            case 13:return ascii[6];
            case 14:return ascii[6];
            case 15:return ascii[7];
            case 16:return ascii[7];
            case 17:return ascii[8];
            case 18:return ascii[8];
            case 19:return ascii[9];
            case 20:return ascii[9];
            case 21:return ascii[10];
            case 22:return ascii[10];
            case 23:return ascii[11];
            case 24:return ascii[12];
            case 25:return ascii[13];
            default:return ascii[0];
        }
    }
    
    public void asciiWrite(){
        try(FileWriter writer = new FileWriter("notes.txt", false))
        { 
            writer.write("hello"
                    + "\n");
            for(int w=0;w<this.width;w++){
                for(int h=0;h<this.height;h++)
                    writer.append(chars[h][w]);
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
    }
    
    public void inBlackWhite(){
        for(int w=0;w<this.width;w++){
            for(int h=0;h<this.height;h++){
                int c = Math.round((pixels[h][w].getRed()+pixels[h][w].getGreen()+pixels[h][w].getBlue())/3);
                pixels[h][w] = new Color(c,c,c);
                imgWrite(h,w,pixels[h][w]);
            }
        }
        
    }
    
    public void imgWrite(int x,int y, Color color) {
        image.setRGB(x, y, color.getRGB());
        try {
            ImageIO.write(image, "png", new File("image.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Color getPixelColor(int x, int y) {
        Object colorData = image.getRaster().getDataElements(x, y, null);
        int argb = image.getColorModel().getRGB(colorData);
        return new Color(argb, true);
    }
}
