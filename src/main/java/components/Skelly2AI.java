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
import util.AssetPool;
import util.RMath;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;

public class Skelly2AI extends Component{

    private transient GameObject player;
    private transient boolean facingRight;
    private transient RigidBody2D rb;
    private transient float speed = 1.0f;
    private transient Vector2f velocity;
    private transient StateMachine stateMachine;
    private transient float skellyWidth;
    private transient boolean swinging = false;
    private transient float attackTimeLeft = 1.6f;
    private transient boolean attacked = false;
    private transient Vector2f attackPos;

    private transient boolean usingShield = false;
    private transient float shieldTimeLeft = 1.6f;

    private transient Vector2f diff;


    private transient int health = 400;
    private transient boolean dead = false ;
    private transient float deathTimer = 2f;




    @Override
    public void start()
    {
        player = Window.getScene().getGameObjectWith(PlayerController.class);
        rb = this.gameObject.getComponent(RigidBody2D.class);
        stateMachine = this.gameObject.getComponent(StateMachine.class);
        skellyWidth = this.gameObject.transform.scale.x;
        velocity = new Vector2f();
        facingRight = RMath.randomBoolean();




    }



    @Override
    public void update(float dt)
    {
        if(dead)
        {

            deathTimer -=dt;
            if(deathTimer <= 0)
            {
                this.gameObject.destroy();
            }
            return;
        }




        if(KeyListener.keyBeginPress(GLFW_KEY_L))
        {
            hit(1000);
        }


        if(player == null)
        {
            return;
        }

        if(player.getComponent(PlayerController.class).isDead())
        {
          //  stateMachine.trigger("attackWalk");
          //  stateMachine.trigger("stopShield");
          //  stateMachine.trigger("stopWalking");
            return;
        }

        diff = new Vector2f(this.gameObject.transform.position.x, this.gameObject.transform.position.y).sub(player.transform.position.x, player.transform.position.y);
        if(Math.abs(diff.x) > 6f || Math.abs(diff.y) > 2f)
        {
            return;
        }



        if(diff.x > .2f)
        {


            this.gameObject.transform.scale.x = -this.skellyWidth;
            facingRight = false;
            this.rb.setVelocityX(-speed);

        }
        else if(diff.x < -.2f)
        {
            this.gameObject.transform.scale.x = this.skellyWidth;
            facingRight = false;
            this.rb.setVelocityX(speed);
        }


        if(diff.x < 0 && diff.x >= -.7f && !swinging && !usingShield)
        {

            if(RMath.randomBoolean())
            {
                this.gameObject.getComponent(RigidBody2D.class).setBodyType(BodyType.Static);
                stateMachine.trigger("attack");
                swinging = true;
            }
            else
            {
                stateMachine.trigger("shield");
                usingShield = true;
                this.rb.setBodyType(BodyType.Static);
            }

        }
        else if( diff.x >= 0 && diff.x <= .7f && !swinging && !usingShield)
        {
            if(RMath.randomBoolean())
            {
                this.rb.setBodyType(BodyType.Static);
                stateMachine.trigger("attack");
                swinging = true;
            }
            else
            {
                stateMachine.trigger("shield");
                usingShield = true;
                this.rb.setBodyType(BodyType.Static);
            }



        }


        if(swinging )
        {
            attackTimeLeft -=dt;
        } else if(usingShield)
        {
            shieldTimeLeft -= dt;
        }

        if(usingShield && shieldTimeLeft <= 0)
        {
            usingShield = false;
            shieldTimeLeft = 1.6f;
            stateMachine.trigger("stopShield");
            this.rb.setBodyType(BodyType.Dynamic);
        }
        if(swinging && !attacked && attackTimeLeft < .6f)
        {
            attacked = true;
            if(diff.x >= 0)
            {
                attackPos = new Vector2f(this.gameObject.transform.position.x, this.gameObject.transform.position.y);
                attackPos.add(-.55f,0);
            }
            else
            {
                attackPos = new Vector2f(this.gameObject.transform.position.x, this.gameObject.transform.position.y);
                attackPos.add(.55f,0);
            }
            Window.getScene().addGameObjectToScene(Prefabs.MobAttackBox(attackPos, 1.28f , .75f, 100 , .2f));
            AssetPool.getSound("assets/sounds/mobs/swing.ogg").play();
        }
        else if(swinging && attackTimeLeft <= 0)
        {
            swinging = false;
            attacked = false;
            attackTimeLeft = 1.6f;
            stateMachine.trigger("attackWalk");
            this.rb.setBodyType(BodyType.Dynamic);
        }














    }


    public void hit(int amount)
    {
        if(usingShield)
        {
            return;
        }
        health -= amount;
        if(health <= 0)
        {
            die();
        }
    }

    @Override
    public void beginCollision(GameObject obj, Contact contact , Vector2f contactNormal)
    {
        if(!dead && obj.getComponent(PlayerController.class) != null )
        {
            obj.getComponent(PlayerController.class).damage(50);
        }
        else
        {
            contact.setEnabled(false);
        }
    }




    public void die()
    {
        dead = true;
        if(!swinging)
        {
            stateMachine.trigger("die");
        }
        else if(swinging)
        {
            stateMachine.trigger("attackDeath");
        }
        this.rb.setBodyType(BodyType.Static);
        this.rb.setIsSensor();

        if(player != null)
        {
            player.getComponent(PlayerController.class).replenishMana(100);
        }


    }
}
