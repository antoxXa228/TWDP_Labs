package ksg.lab2;

import com.jogamp.opengl.util.texture.Texture;
import java.util.Random;
import com.jogamp.opengl.GL2;
import ksg.common.Colour;
import ksg.common.Vector3;
import ksg.common.GameObject;
import ksg.lab2.Weather;
import ksg.lab2.Global;

/**
 @author MK
 */

// Klasa reprezentująca pojedynczą cząsteczkę dymu.
public class SmokeParticle extends GameObject
{
    protected Texture texture;
    protected Colour colour;
    protected float size = 60f;
    protected Vector3 moveSpeed; // prędkość ruchu cząsteczki
    protected float energy = 1.0f; // malejąca energia cząsteczki
    protected float fadeOutSpeed = 0.004f, fadeInSpeed = 0.04f; // prędkość pojawiania się i znikania cząsteczki
    protected boolean isFadingAway; // informacja, czy cząsteczka już znika, czy dopiero się pojawia
    protected Random rand = new Random();

    public SmokeParticle(Vector3 position, Texture texture, Colour colour)
    {
        super();
        setPosition(position);
        this.texture = texture;
        setColour( new Colour(colour) );

        // Zadanie 2: początkowy stały rozrzut w bok
        float angle = (float) (rand.nextDouble() * 2.0 * Math.PI);
        float sideSpeed = 0.3f + rand.nextFloat() * 0.7f;
        moveSpeed = new Vector3((float) Math.cos(angle) * sideSpeed, 1.5f, (float) Math.sin(angle) * sideSpeed);

        isFadingAway = false;
    }

    public SmokeParticle(Vector3 position, Texture texture)
    {
        this(position, texture, new Colour(1,1,1,1));
    }

    void setColour(Colour colour)
    {
        this.colour = colour;
    }

    Colour getColour()
    {
        return this.colour;
    }

    @Override
    public void update(GL2 gl) // aktualizacja niektórych parametrów cząsteczki
    {
        float dt = (float) Global.timeScaler;

        // Zadanie 2a & 5: skalowanie cyklu życia względem czasu
        if (!isFadingAway)
        {
            energy += fadeInSpeed * dt;
            if (energy > 1f)
            {
                energy = 1f;
                isFadingAway = true;
            }
        }
        else
        {
            energy -= fadeOutSpeed * dt;
        }

        colour.alpha = Math.max(0.0f, Math.min(1.0f, energy * 0.4f));
        gl.glColor4f(colour.red, colour.green, colour.blue, colour.alpha);

        // Zadanie 2b & 2c
        float expansion = 1.0f + (1.0f - energy) * 1.5f;
        float jitterX = (rand.nextFloat() - 0.5f) * 0.2f;
        float jitterZ = (rand.nextFloat() - 0.5f) * 0.2f;

        // zadanie 3
        Vector3 wind = Weather.getWind();

        // Zadanie 5
        Vector3 step = new Vector3(
                (moveSpeed.x * expansion + jitterX + wind.x * 0.25f) * dt,
                (moveSpeed.y + wind.y * 0.25f) * dt,
                (moveSpeed.z * expansion + jitterZ + wind.z * 0.25f) * dt
        );

        move(step);
    }
}