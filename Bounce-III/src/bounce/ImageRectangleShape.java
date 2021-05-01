package bounce;

import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class ImageRectangleShape extends RectangleShape{
    Image image = null;
    public ImageRectangleShape(int deltaX, int deltaY, Image image) {
        super(0,0,deltaX,deltaY,image.getWidth(null), image.getHeight(null));
        this.image = image;

    }
    public static Image makeImage(String imageFileName, int hapeWidth) {
        File file = new File(imageFileName);
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        double scaleFactor = (double)hapeWidth / ((double)image.getWidth(null));
        int newHeight = (int)((double)image.getHeight(null) * scaleFactor);
        Image newImage = image.getScaledInstance(hapeWidth, newHeight, Image.SCALE_DEFAULT);
        return newImage;
    }
    public void doPaint(Painter painter) {
        painter.drawImage(this.image,_x,_y,_width,_height);

    }
}
