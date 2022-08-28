package components;

import Rixx.Camera;
import Rixx.Window;
import org.joml.Vector2f;

public class HealthBar extends  Component{

    private transient Camera camera;
    private transient Vector2f position;
    private transient float length = .5f;
    private transient float height = .1f;

    @Override
    public void start()
    {
        camera = Window.getScene().camera();
        position = new Vector2f(camera.position.x + .5f, camera.position.y + 2.5f);
        this.gameObject.transform.position.set(position);
        this.gameObject.transform.zIndex = 9;

    }

    @Override
    public void update(float dt)
    {
        if(Window.getScene().getGameObjectWith(PlayerController.class) != null)
        {
            PlayerController player = Window.getScene().getGameObjectWith(PlayerController.class).getComponent(PlayerController.class);
            float scale = (float)player.getHealth() / player.getHealthLimit() * .5f;
            this.gameObject.transform.scale.x = scale;


        }



        float offsetScale = this.gameObject.transform.scale.x / 0.5f;
        float offsetX = -0.25f + offsetScale * .5f/2f;
        position = new Vector2f((camera.position.x + .5f) + offsetX, camera.position.y + 2.5f);
        this.gameObject.transform.position.set(position);


    }
}
