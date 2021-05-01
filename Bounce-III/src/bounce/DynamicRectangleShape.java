package bounce;

import java.awt.Color;
public class DynamicRectangleShape extends RectangleShape{
    boolean status = true;
    int count = 0;
    Color color;
    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x,y,deltaX,deltaY,width,height);
        this.color = Color.red;
    }
    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
        super(x,y,deltaX,deltaY,width,height);
        this.color = color;
    }
    
    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
        super(x,y,deltaX,deltaY,width,height, text);
        this.color = color;
    }
    public void doPaint(Painter painter) {
        if(_x+_deltaX<=0 | _x+_deltaX+_width>=500 && _y+_deltaY+_height<=500){
            this.status = true;
        }

        else if(_y+_deltaY<=0|_y+_deltaY+_height>=478 &&_x+_deltaX+_width<=500){
            this.status = false;
        }
        else if (_x+_deltaX==0 && _y+_deltaY==0) {
            this.status = false;
        }
        else if ( _x+_deltaX+_width==500 && _y+_height+_deltaY==478){
            this.status = false;
        }
        else if (_x+_deltaX+_width==500 && _y+_deltaY==0){
            this.status=false;
        }
        else if (_y+_height+_deltaY==478 && _x==0){
            this.status=false;
        }

        if(status | count == 0){
            painter.setColor(this.color);
            painter.fillRect(_x,_y,_width,_height);
            count++;
        } else {
            painter.setColor(Color.black);
            painter.drawRect(_x,_y,_width,_height);
        }

        painter.setColor(Color.black);
    }

    public void move(int width, int height) {
        super.move(width, height);
    }

}