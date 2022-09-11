package components;

import Rixx.GameObject;
import Rixx.KeyListener;
import Rixx.Prefabs;
import Rixx.Window;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2D.Physics2D;
import physics2D.components.RigidBody2D;
import physics2D.enums.BodyType;
import scenes.Scene;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;

public class SkellyAI extends Component{
    private transient Vector2f location;
    private transient Vector2f playerLocation;
    private transient GameObject player;
    private transient boolean facingRight;
    private transient RigidBody2D rb;
    private transient float speed = 1.0f;
    private transient Vector2f velocity;
    private transient StateMachine stateMachine;
    private transient float skellyWidth;
    private transient boolean onGround;
    private transient boolean attacked = false;
    private transient float timeLeft = 2.3f;
    private transient boolean hit = false;


    private transient int health;
    private transient boolean dead ;
    private transient boolean dying;
    private transient float deathTimer = 2.6f;


    @Override
    public void start()
    {
        player = Window.getScene().getGameObjectWith(PlayerController.class);
        rb = this.gameObject.getComponent(RigidBody2D.class);
        stateMachine = this.gameObject.getComponent(StateMachine.class);
        skellyWidth = this.gameObject.transform.scale.x;
        velocity = new Vector2f();
        facingRight = true;
        health  = 300;
        dead = false;
        dying = false;
        if(player == null)
        {
            return;
        }

        playerLocation = new Vector2f(player.transform.position.x, player.transform.position.y);

    }



    @Override
    public void update(float dt)
    {
        if(dying)
        {
            deathTimer -= dt;
            this.rb.setBodyType(BodyType.Static);
            this.rb.setIsSensor();
            if(deathTimer < 0)
            {
                this.gameObject.destroy();
            }
            return;
        }

        if(KeyListener.isKeyPressed(GLFW_KEY_L))
        {
            die();
        }




        if(player != null && rb != null)
        {
            playerLocation = new Vector2f(player.transform.position.x, player.transform.position.y);
            float distance = playerLocation.x - this.gameObject.transform.position.x;
            float distanceY = playerLocation.y - this.gameObject.transform.position.y;




            if(distance > 0)
            {
                facingRight = true;
                this.gameObject.transform.scale.x = skellyWidth;
            }
            else if ( distance < 0)
            {
                facingRight = false;
                this.gameObject.transform.scale.x = -skellyWidth;
            }


            if(attacked )
            {
                if(dead)
                {
                    stateMachine.trigger("attackingDeath");
                    dying = true;
                    return;
                }
                timeLeft -= dt;
                if(timeLeft < 0)
                {
                    stateMachine.trigger("stopAttacking");

                    attacked = false;
                    timeLeft = 2.3f;
                    hit = false;
                    this.rb.setBodyType(BodyType.Dynamic);


                }

                if(timeLeft < 1.7f && !hit && timeLeft > .9f)
                {
                    hit  = true;
                    Window.getScene().addGameObjectToScene(Prefabs.MobAttackBox(new Vector2f(this.gameObject.transform.position.x , this.gameObject.transform.position.y), 1.25f, 1.25f, 100 , .5f));
                }
                return;
            }


            if(Math.abs(distance) > 7 || Math.abs(distanceY) > 1f)
            {
                stateMachine.trigger("stopRunning");
                velocity.x = 0;
                if(dead )
                {
                    stateMachine.trigger("idleDeath");
                    dying = true;
                    return;
                }
                return;
            }





            if(distance > .5f)
            {
                facingRight = true;
                stateMachine.trigger("startRunning");
                this.gameObject.transform.scale.x = skellyWidth;
                velocity.x = speed;
                if(dead  )
                {
                    stateMachine.trigger("runningDeath");
                    dying = true;
                    return;
                }
            }
            else if ( distance < -.5f)
            {
                facingRight = false;
                stateMachine.trigger("startRunning");
                this.gameObject.transform.scale.x = -skellyWidth;
                velocity.x = -speed;
                if(dead )
                {
                    stateMachine.trigger("runningDeath");
                    dying = true;
                    return;
                }
            }

            else if(!attacked )
            {
                stateMachine.trigger("stopRunning");
                stateMachine.trigger("startAttacking");
                velocity.x= 0;
                attacked = true;
                this.rb.setBodyType(BodyType.Static);
                if(dead )
                {
                    stateMachine.trigger("attackingDeath");
                    dying = true;
                    return;
                }


            }
            else
            {
                velocity.x = 0;
            }





            this.rb.setVelocityX(velocity.x);
        }

    }

    @Override
    public void beginCollision(GameObject obj, Contact contact , Vector2f contactNormal) {
        if ( !dying && obj.getComponent(PlayerController.class) != null) {
            obj.getComponent(PlayerController.class).damage(50);
        }


    }

    public void damaged(int amount)
    {
        health -= amount;
        if(health <= 0)
        {
            die();
        }
    }




    public void die()
    {
        dead = true;

    }


}
