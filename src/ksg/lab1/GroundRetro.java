package ksg.lab1;

import ksg.common.GameObject;
import ksg.common.Vector3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.GL2;
import java.awt.image.BufferedImage;

/**
 @author MK
 */
public class GroundRetro extends GameObject
{
    protected float size = 1000;
    protected Texture texture;
    protected BufferedImage displacementMap;
    protected float red=1, green=1, blue=1;

    public GroundRetro(Vector3 position, Texture texture, BufferedImage displacementMap)
    {
        super();
        setPosition(position);
        this.texture = texture;
        this.displacementMap = displacementMap;
    }

    public GroundRetro(float x, float y, float z, Texture texture, BufferedImage displacementMap)
    {
        this( new Vector3(x,y,z), texture, displacementMap );
    }

    private float getHeight(int xx, int zz, int cols, int rows, float maxHeight)
    {
        if (displacementMap == null) return 0.0f;

        // szukamy pixel
        int imgX = (int) ((float) xx / (cols - 1) * (displacementMap.getWidth() - 1));
        int imgY = (int) ((float) zz / (rows - 1) * (displacementMap.getHeight() - 1));

        int rgb = displacementMap.getRGB(imgX, imgY);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;


        return ((r + g + b) / (3.0f * 255.0f)) * maxHeight;
    }

    @Override
    public void draw(GL2 gl)
    {
        gl.glPushMatrix();
        gl.glTranslatef(position.x, position.y, position.z);
        update(gl);

        texture.enable(gl);
        texture.bind(gl);

        gl.glBegin(GL2.GL_QUADS);
        gl.glNormal3f(0, 1, 0);
        gl.glColor3f(red, green, blue);

        int cols = 100;
        int rows = 100;
        float maxHeight = 100.0f;

        float stepX = size / (cols - 1);
        float stepZ = size / (rows - 1);

        for (int zz = 0; zz < rows - 1; zz++)
        {
            for (int xx = 0; xx < cols - 1; xx++)
            {

                float x1 = -size / 2.0f + xx * stepX;
                float x2 = x1 + stepX;
                float z1 = -size / 2.0f + zz * stepZ;
                float z2 = z1 + stepZ;

                float y1 = getHeight(xx, zz, cols, rows, maxHeight);
                float y2 = getHeight(xx + 1, zz, cols, rows, maxHeight);
                float y3 = getHeight(xx + 1, zz + 1, cols, rows, maxHeight);
                float y4 = getHeight(xx,zz + 1, cols, rows, maxHeight);


                float u1 = ((float) xx / (cols - 1));
                float u2 = ((float) (xx + 1) / (cols - 1));
                float v1 = ((float) zz / (rows - 1));
                float v2 = ((float) (zz + 1) / (rows - 1));

                gl.glTexCoord2f(u1, v1); gl.glVertex3f(x1, y1, z1);
                gl.glTexCoord2f(u2, v1); gl.glVertex3f(x2, y2, z1);
                gl.glTexCoord2f(u2, v2); gl.glVertex3f(x2, y3, z2);
                gl.glTexCoord2f(u1, v2); gl.glVertex3f(x1, y4, z2);
            }
        }

        gl.glEnd();

        texture.disable(gl);
        gl.glPopMatrix();
    }
}