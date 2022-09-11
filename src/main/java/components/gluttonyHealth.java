package components;

import Rixx.Camera;
import Rixx.GameObject;
import Rixx.Window;
import org.joml.Vector2f;

public class gluttonyHealth extends Component{
    private transient GameObject gluttony;
    private transient Vector2f position;
    private transient float length = .5f;
    private transient float height = .2f;

    @Override
    public void start()
    {
        gluttony = Window.getScene().getGameObjectWith(gluttonyAI.class);
        position = new Vector2f(gluttony.transform.position.x + 1.25f, gluttony.transform.position.y + .5f);
        this.gameObject.transform.position.set(position);
        this.gameObject.transform.zIndex = 49;

    }

    @Override
    public void update(float dt)
    {
        if(Window.getScene().getGameObjectWith(PlayerController.class) != null)
        {
            PlayerController player = Window.getScene().getGameObjectWith(PlayerController.class).getComponent(PlayerController.class);
            float scale = (float)gluttony.getComponent(gluttonyAI.class).getHealth() / gluttony.getComponent(gluttonyAI.class).getMaxHealth() * .5f;
            this.gameObject.transform.scale.x = scale;


        }



        float offsetScale = this.gameObject.transform.scale.x / 0.5f;
        float offsetX = -0.25f + offsetScale * .5f/2f;
        position = new Vector2f((gluttony.transform.position.x + 1.25f) + offsetX, gluttony.transform.position.y + .5f);
        this.gameObject.transform.position.set(position);


    }
}
