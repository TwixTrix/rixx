package components;

import Rixx.GameObject;
import Rixx.MouseListener;
import Rixx.Window;
import org.jbox2d.common.MathUtils;
import org.joml.Vector2f;
import physics2D.components.RigidBody2D;

public class FlameBall extends Component{
    private transient Vector2f mouseCoords;
    private static transient int flameCount = 0;
    private transient float timeLeft = 4;
    private transient boolean sent = false;
    private transient GameObject player;
    private transient Vector2f playerLocation;
    private transient Vector2f fireLocation;
    private transient Vector2f diffLocation;
    private transient Vector2f adjustedVector;
    private transient RigidBody2D rb;
    private transient float timer = 6.0f;




    @Override
    public void start()
    {

        rb = this.gameObject.getComponent(RigidBody2D.class);
        mouseCoords = MouseListener.getWorld();
        player =  Window.getScene().getGameObjectWith(PlayerController.class);
        playerLocation = new Vector2f(player.transform.position.x, player.transform.position.y);
        diffLocation = mouseCoords.sub(playerLocation);
        adjustedVector = new Vector2f(diffLocation.x, diffLocation.y).normalize();
        fireLocation = playerLocation.add(adjustedVector);





        gameObject.transform.position.set(fireLocation);
        rb.setPosition(fireLocation);
        flameCount++;


    }

    @Override
    public void update(float dt)
    {

        if(sent)
        {
            rb.setVelocity(new Vector2f(adjustedVector.x, adjustedVector.y).mul(2));

            timer -= dt;
            if(timer < 0)
            {
                this.gameObject.destroy();
                flameCount --;
            }



            return;
        }

        mouseCoords = MouseListener.getWorld();
        playerLocation = new Vector2f(player.transform.position.x, player.transform.position.y);
        diffLocation = mouseCoords.sub(playerLocation);
        adjustedVector = new Vector2f(diffLocation.x, diffLocation.y).normalize();
        fireLocation = playerLocation.add(adjustedVector);


        gameObject.transform.position.set(fireLocation);
        rb.setPosition(fireLocation);



    }

    public void send()
    {
        sent = true;
    }

    public static boolean canSpawn()
    {
        return flameCount == 0;
    }

    public static void zero()
    {
        flameCount = 0;
    }

    public boolean isSent() {
        return sent;
    }

    public static int getFlameCount(){
        return flameCount;
    }
}
