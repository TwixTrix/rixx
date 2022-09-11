package components;

import Rixx.Camera;
import Rixx.GameObject;
import Rixx.Window;
import org.joml.Vector2f;

public class gluttonyEmpty extends  Component{
    private transient GameObject gluttony;
    private transient Vector2f position;
    @Override
    public void start()
    {
        gluttony = Window.getScene().getGameObjectWith(gluttonyAI.class);
        position = new Vector2f(gluttony.transform.position.x + 1.25f, gluttony.transform.position.y + .5f);
        this.gameObject.transform.position.set(position);
        this.gameObject.transform.zIndex = 50;
    }

    @Override
    public void update(float dt)
    {
        position = new Vector2f(gluttony.transform.position.x + 1.25f, gluttony.transform.position.y + .5f);
        this.gameObject.transform.position.set(position);
    }
}
