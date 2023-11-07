
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;


public abstract class Entity {
    protected float x, y;
    protected  int width, height;
    protected  Rectangle2D.Float hitbox;

    public Entity(float x, float y, int witdh, int height) {
        this.x = x;
        this.y = y;
        this.width = witdh;
        this.height = height;
    }
    protected void drawHitBox(Graphics g){
       //Debug hitbox
       g.setColor(Color.BLUE);
       g.drawRect((int)hitbox.x,(int)hitbox.y, (int)hitbox.width,(int) hitbox.height);
    }
    protected void initHitBox(float x, float y , float width, float height){
        hitbox = new Rectangle2D.Float(x,y,width, height);
    }
//    public void updateHitBox(){
//        hitbox.x = (int)x;
//        hitbox.y = (int)y;
//    }
    public Rectangle2D.Float getHitBox(){
        return hitbox;
    }
}
