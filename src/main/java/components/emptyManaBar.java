package components;

import Rixx.Camera;
import Rixx.Window;
import org.joml.Vector2f;

public class emptyManaBar extends Component{
    private transient Camera camera;
    private transient Vector2f position;
    @Override
    public void start()
    {
        camera = Window.getScene().camera();
        position = new Vector2f(camera.position.x + .5f, camera.position.y + 2.5f);
        this.gameObject.transform.position.set(position);
        this.gameObject.transform.zIndex = 10;
    }

    @Override
    public void update(float dt)
    {
        position = new Vector2f(camera.position.x + .5f, camera.position.y + 2.5f);
        this.gameObject.transform.position.set(position);
    }

}
