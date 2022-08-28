package components;

import Rixx.*;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import physics2D.Physics2D;
import physics2D.RaycastInfo;
import physics2D.components.PillboxCollider;
import physics2D.components.RigidBody2D;
import physics2D.enums.BodyType;
import renderer.DebugDraw;
import scenes.LevelEditorSceneInitializer;
import scenes.LevelSceneInitializer;
import scenes.Scene;
import util.AssetPool;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.glfw.GLFW.*;

public class PlayerController extends Component{


    public float walkSpeed = 1.9f;
    public float jumpBoost = 1.0f;
    public float jumpImpulse = 3.0f;
    public float slowDownForce  = 0.05f;
    public Vector2f terminalVelocity = new Vector2f(2.1f, 3.1f);

    private static int healthLimit = 100;
    private static int health = healthLimit;



    public transient boolean onGround = false;
    private transient float groundDebounce = 0.0f;
    private transient float groundDebounceTime = 0.1f;
    private transient RigidBody2D rb;
    private transient StateMachine stateMachine;
    private transient float bigJumpBoostFactor = 1.05f;
    private transient float playerWidth = 0.25f;
    private transient  int jumpTime = 0;
    private transient Vector2f acceleration = new Vector2f();
    private transient Vector2f velocity = new Vector2f();
    private transient boolean isDead = false;
    private transient int enemyBounce = 0;

    private transient float hurtInvincibilityTimeLeft = 0;
    private transient float hurtInvincibleTime = 1.4f;
    private transient float deadMaxHeight = 0;
    private transient float deadMinHeight = 0;
    private transient boolean deadGoingUp = true;
    private transient float blinkTime = 0.0f;
    private transient SpriteRenderer spr;

    private transient boolean playWinAnimation = false;
    private transient float timeToCastle = 4.5f;
    private transient float walkTime = 2.2f;
    private transient boolean sentEvent = false;
    private transient float executiveEndTime = 6f;

    private transient boolean isHoldingRightClick = false;
    private transient float rightClickHoldTime = 0;


    @Override
    public void start()
    {
        this.spr = gameObject.getComponent(SpriteRenderer.class);
        this.rb = gameObject.getComponent(RigidBody2D.class);
        this.stateMachine = gameObject.getComponent(StateMachine.class);
        this.rb.setGravityScale(0.0f);
        this.healthLimit = playerHealthLimitLoad();
        this.health = playerHealthLoad();



    }

    public void update(float dt)
    {


        if(health <=0 && !isDead)
        {
            die();
        }


        if(playWinAnimation)
        {
            executiveEndTime -= dt;
            if(executiveEndTime <= 0 || timeToCastle <= 0)
            {
                nextLevel();
            }
            checkOnGround();
            if(!onGround)
            {
                gameObject.transform.scale.x = -0.25f;
                gameObject.transform.position.y -= dt;
                stateMachine.trigger("stopRunning");
                stateMachine.trigger("stopJumping");
            }
            else
            {
                if(this.walkTime > 0)
                {
                    this.gameObject.transform.scale.x = 0.25f;
                    this.gameObject.transform.position.x += dt;
                    stateMachine.trigger("startRunning");

                }
                if(!AssetPool.getSound("assets/sounds/stage_clear.ogg").isPlaying())
                {
                    AssetPool.getSound("assets/sounds/stage_clear.ogg").play();
                }
                timeToCastle -= dt;
                walkTime -=dt;


            }


            return;
        }


        if(isDead)
        {
            if(this.gameObject.transform.position.y < deadMaxHeight && deadGoingUp)
            {
                this.gameObject.transform.position.y += dt * walkSpeed / 2.0f;
            }
            else if(this.gameObject.transform.position.y > deadMaxHeight && deadGoingUp)
            {
                this.deadGoingUp = false;
            }
            else if(!deadGoingUp && this.gameObject.transform.position.y > deadMinHeight)
            {
                this.rb.setBodyType(BodyType.Kinematic);
                this.acceleration.y  = Window.getPhysics().getGravity().y * 0.7f;
                this.velocity.y += this.acceleration.y * dt;
                this.velocity.y  = Math.max(Math.min(this.velocity.y, this.terminalVelocity.y), -this.terminalVelocity.y);
                this.rb.setVelocity(this.velocity);
                this.rb.setAngularVelocity(0);
            } else if(!deadGoingUp && this.gameObject.transform.position.y <= deadMinHeight)
            {
                Fireball.fireballCount = 0;
                Window.changeScene(new LevelSceneInitializer());
            }
            return;
        }

        if(hurtInvincibilityTimeLeft > 0)
        {
            hurtInvincibilityTimeLeft -=dt;
            blinkTime-= dt;


            if(blinkTime <= 0)
            {
                blinkTime = 0.2f;
                if(spr.getColor().w == 1 )
                {
                    spr.setColor(new Vector4f(1,1,1,0));
                }else
                {
                    spr.setColor(new Vector4f(1,1,1,1));
                }
            }else
            {
                if(spr.getColor().w == 0)
                {
                    spr.setColor(new Vector4f(1,1,1,1));
                }
            }
        }

        if(KeyListener.isKeyPressed(GLFW_KEY_RIGHT) || KeyListener.isKeyPressed(GLFW_KEY_D))
        {
            this.gameObject.transform.scale.x = playerWidth;
            this.acceleration.x = walkSpeed;

            if(this.velocity.x < 0)
            {
                this.stateMachine.trigger("switchDirection");
                this.velocity.x += slowDownForce;
            }
            else
            {
                this.stateMachine.trigger("startRunning");
            }
        } else if(KeyListener.isKeyPressed(GLFW_KEY_LEFT) || KeyListener.isKeyPressed(GLFW_KEY_A))
        {
            this.gameObject.transform.scale.x = -playerWidth;
            this.acceleration.x = -walkSpeed;

            if(this.velocity.x > 0)
            {
                this.stateMachine.trigger("switchDirection");
                this.velocity.x -= slowDownForce;
            }
            else
            {
                this.stateMachine.trigger("startRunning");
            }
        }else
        {
            this.acceleration.x = 0;
            if(this.velocity.x > 0)
            {
                this.velocity.x = Math.max(0, this.velocity.x - slowDownForce);
            }
            else if(this.velocity.x < 0)
            {
                this.velocity.x = Math.min(0, this.velocity.x + slowDownForce);
            }

            if(this.velocity.x == 0)
            {
                this.stateMachine.trigger("stopRunning");
            }
        }

        if(KeyListener.keyBeginPress(GLFW_KEY_E) && Fireball.canSpawn())
        {
            Vector2f position = new Vector2f(this.gameObject.transform.position).add(this.gameObject.transform.scale.x >0 ? new Vector2f(0.26f, 0) : new Vector2f(-0.26f, 0));
            GameObject fireball = Prefabs.generateFireball(position);
            fireball.getComponent(Fireball.class).goingRight = this.gameObject.transform.scale.x >0;
            Window.getScene().addGameObjectToScene(fireball);

        }

        if(MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_2) && FlameBall.canSpawn())
        {
            GameObject flameBall = Prefabs.generateFlameBall();
            Window.getScene().addGameObjectToScene(flameBall);
            isHoldingRightClick = true;
            heal(100);

        }
        else if(MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_2))
        {
            isHoldingRightClick = true;
            rightClickHoldTime += dt;
        }
        else{
            if(isHoldingRightClick )
            {
                Window.getScene().getGameObjectWith(FlameBall.class).getComponent(FlameBall.class).send();
            }
            isHoldingRightClick = false;

        }


        checkOnGround();
        if(KeyListener.isKeyPressed(GLFW_KEY_SPACE) && (jumpTime > 0 || onGround || groundDebounce > 0))
        {
            if((onGround || groundDebounce > 0) && jumpTime == 0)
            {
                AssetPool.getSound("assets/sounds/jump-small.ogg").play();
                jumpTime = 112 ;
                this.velocity.y = jumpImpulse;
            }
            else if(jumpTime > 0)
            {
                jumpTime --;
                this.velocity.y = ((jumpTime / 2.2f) * jumpBoost);
            }
            else
            {
                this.velocity.y = 0;
            }
            groundDebounce = 0;

        }
        else if(enemyBounce > 0)
        {
            enemyBounce--;
            this.velocity.y = ((enemyBounce / 2.2f) * jumpBoost);
        }
        else if(!onGround)
        {
            if(this.jumpTime > 0)
            {
                this.velocity.y *= 0.35f;
                this.jumpTime = 0;
            }
            groundDebounce -=dt;
            this.acceleration.y = Window.getPhysics().getGravity().y * 0.7f;
        }
        else
        {
            this.velocity.y = 0;
            this.acceleration.y = 0;
            groundDebounce = groundDebounceTime;
        }



        this.velocity.x += this.acceleration.x * dt;
        this.velocity.y += this.acceleration.y * dt;
        this.velocity.x = Math.max(Math.min(this.velocity.x, this.terminalVelocity.x), -this.terminalVelocity.x) ;
        this.velocity.y = Math.max(Math.min(this.velocity.y, this.terminalVelocity.y), -this.terminalVelocity.y);
        this.rb.setVelocity(this.velocity);
        this.rb.setAngularVelocity(0);

        if(!onGround)
        {
            stateMachine.trigger("jump");
        }
        else
        {
            stateMachine.trigger("stopJumping");
        }
    }

    public void checkOnGround()
    {

        float innerPlayerWidth = this.playerWidth * 0.6f;
        float yVal = -0.14f;
      onGround = Physics2D.checkOnGround(this.gameObject, innerPlayerWidth, yVal );



    }

    public void setPosition(Vector2f newPos)
    {
        this.gameObject.transform.position.set(newPos);
        this.rb.setPosition(newPos);

    }

    public void powerup()
    {
        //TODO add other phases

        stateMachine.trigger("powerup");
    }

    @Override
    public void beginCollision(GameObject collidingObject, Contact contact, Vector2f contactNormal)
    {
        if(isDead) return;

        if(collidingObject.getComponent(Ground.class) != null)
        {
            if(Math.abs(contactNormal.x) > 0.8f)
            {
                this.velocity.x = 0;
            }
            else if(contactNormal.y > 0.8f)
            {
                this.velocity.y = 0;
                this.acceleration.y = 0;
                jumpTime = 0;
            }
        }

    }

    public void enemyBounce()
    {
        //TODO at dt, for diffrent frame rates
        this.enemyBounce = 50;
    }

    public void beginFlameDrag()
    {

    }

    public boolean isDead()
    {
        return  this.isDead;
    }

    public boolean isHurtInvincible()
    {
        return this.hurtInvincibilityTimeLeft > 0 || playWinAnimation;
    }

    public boolean isInvincible()
    {
        return  this.hurtInvincibilityTimeLeft > 0 || playWinAnimation;
    }



    public void die()
    {
        this.stateMachine.trigger("die");
        this.velocity.set(0,0);
        this.acceleration.set(0,0);
        this.rb.setVelocity(new Vector2f());
        this.isDead = true;
        this.rb.setIsSensor();
        AssetPool.getSound("assets/sounds/mario_die.ogg").play();
        FlameBall.zero();
        deadMaxHeight = this.gameObject.transform.position.y + 0.3f;
        this.rb.setBodyType(BodyType.Static);
        if(gameObject.transform.position.y > 0)
        {
            deadMinHeight = -0.25f;
        }

    }

    public boolean hasWon()
    {
        return false;
    }

    public void nextLevel()
    {
        playerStatsSave();
        Fireball.fireballCount = 0;
        Window.incrementLevel();
        Window.LevelSave();
        Window.changeScene(new LevelSceneInitializer(), Window.LevelLoad());

    }

    public void playWinAnimation(GameObject flagPole)
    {
        if(!playWinAnimation)
        {
            playWinAnimation = true;
            velocity.zero();
            acceleration.zero();
            rb.setVelocity(velocity);
            rb.setIsSensor();
            rb.setBodyType(BodyType.Static);
            gameObject.transform.position.x = flagPole.transform.position.x;
            AssetPool.getSound("assets/sounds/flagpole.ogg").play();
            FlameBall.zero();
        }
    }


    public static void playerStatsSave()
    {

        try{
            FileWriter writer = new FileWriter("PlayerStats.txt");
            writer.write("Health Limit =" + healthLimit + ",Health =" + health + "," );
            writer.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    public static int playerHealthLimitLoad()
    {


        try{
            String  path = new String(Files.readAllBytes(Paths.get("PlayerStats.txt")));

            int start = path.indexOf("Health Limit =") + "Health Limit =".length();
            int end = path.indexOf(",", start);
            try{
                return Integer.valueOf(path.substring(start,end));
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
            }

        }catch (IOException e)
        {

            e.printStackTrace();
        }
        return 100;
    }
    public static int playerHealthLoad()
    {


        try{
            String  path = new String(Files.readAllBytes(Paths.get("PlayerStats.txt")));

            int start = path.indexOf("Health =") + "Health =".length();
            int end = path.indexOf(",", start);

            try{
                return Integer.valueOf(path.substring(start,end));
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
            }

        }catch (IOException e)
        {

            e.printStackTrace();
        }
        return 100;
    }

    public void damage(int damageAmount)
    {
        if(health <=0 || isHoldingRightClick) return;
        health -= damageAmount;


    }

    public int getHealthLimit()
    {
        return healthLimit;
    }

    public int getHealth()
    {
        return health;
    }

    public void heal(int healAmount)
    {
        health += healAmount;
        if(health >healthLimit){
            health = healthLimit;
        }
    }


}
