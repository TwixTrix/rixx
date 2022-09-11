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

    public float jumpBoost = .8f;
    public float jumpImpulse = 2.5f;
    public float slowDownForce  = 0.05f*4f;
    public Vector2f terminalVelocity = new Vector2f(2.1f, 3.1f);

    private static int healthLimit = 1000;
    private static int health = healthLimit;
    private transient float mana = 1000;
    private transient float maxMana = 1000;
    private transient float manaRate = 15f;
    private transient float shieldManaRate = 100f;
    private transient boolean shieldActive = false;



    public transient boolean onGround = false;
    private transient float groundDebounce = 0.0f;
    private transient float groundDebounceTime = 0.1f;
    private transient RigidBody2D rb;
    private transient StateMachine stateMachine;
    private transient float bigJumpBoostFactor = 1.05f;
    private transient float playerWidth ;
    private transient  float jumpTime = 0;
    private transient Vector2f acceleration = new Vector2f();
    private transient Vector2f velocity = new Vector2f();
    private transient int enemyBounce = 0;

    private transient float hurtInvincibilityTimeLeft = 0;
    private transient float hurtInvincibleTime = 1.4f;
    private transient float deadMaxHeight = 0;
    private transient float deadMinHeight = 0;
    private transient boolean deadGoingUp = true;
    private transient float blinkTime = 0.0f;
    private transient SpriteRenderer spr;

    private transient boolean playWinAnimation = false;
    private transient float timeToCastle = 30f;
    private transient float walkTime = 30f;
    private transient boolean sentEvent = false;
    private transient float executiveEndTime = 6f;

    private transient boolean isHoldingRightClick = false;
    private transient float rightClickHoldTime = 0;


    private transient boolean attacking = false;
    private transient float attackTimeLeft = .7f;
    private transient boolean swung = false;

    private transient boolean isDead = false;
    private transient float deathTimer = 5f;

    private  transient float levelBeginTimer = .3f;
    private transient  GameObject shield;

    private transient boolean facingRight = false;
    private transient boolean facingRightOfClick = false;

    private transient float sprintSpeed = 4f;
    private transient boolean sprinting = false;
    private transient float sprintCoolDown =0f ;
    private transient float sprintTime = .3f;




    @Override
    public void start()
    {
        this.spr = gameObject.getComponent(SpriteRenderer.class);
        this.rb = gameObject.getComponent(RigidBody2D.class);
        this.stateMachine = gameObject.getComponent(StateMachine.class);
        this.rb.setGravityScale(0.0f);
        this.healthLimit = playerHealthLimitLoad();
        this.health = playerHealthLoad();
        this.playerWidth  = gameObject.transform.scale.x;



    }

    public void update(float dt)
    {
        levelBeginTimer-=dt;

        if(levelBeginTimer > 0)
        {
            return;
        }

        if(isDead)
        {
            deathTimer -=dt;
            if(deathTimer <= 0)
            {
                Window.changeScene(new LevelSceneInitializer(), Window.LevelLoad());
            }
            return;
        }


        DebugDraw.draw();
        hurtInvincibilityTimeLeft -=dt;






        if(KeyListener.isKeyPressed(GLFW_KEY_RIGHT) || KeyListener.isKeyPressed(GLFW_KEY_D))
        {
            if(!attacking)
            {
                this.gameObject.transform.scale.x = playerWidth;
            }
            this.acceleration.x = walkSpeed;
            facingRight = true;

            if(this.velocity.x < 0)
            {
                this.stateMachine.trigger("startRunning");
                this.velocity.x += slowDownForce;
            }
            else
            {
                this.stateMachine.trigger("startRunning");
            }
        } else if(KeyListener.isKeyPressed(GLFW_KEY_LEFT) || KeyListener.isKeyPressed(GLFW_KEY_A))
        {
            if(!attacking)
            {
                this.gameObject.transform.scale.x = -playerWidth;
            }

            this.acceleration.x = -walkSpeed;
            facingRight = false;

            if(this.velocity.x > 0)
            {
                this.stateMachine.trigger("startRunning");
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



        if(MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_1) && !attacking)
        {
            stateMachine.trigger("jumpFall");
            stateMachine.trigger("fallRun");
            stateMachine.trigger("runAttack");
            stateMachine.trigger("idleAttack");
            attacking = true;
            facingRightOfClick = facingRight;



        }

            if(shield != null)
            {
                shield.transform.position.set(this.gameObject.transform.position.x, this.gameObject.transform.position.y + 0.08f);
            }

         if(MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_2) && mana > 0)
        {
            if(!isHoldingRightClick)
            {
                 shield = Prefabs.heroShield(new Vector2f(this.gameObject.transform.position.x, this.gameObject.transform.position.y));
                 Window.getScene().addGameObjectToScene(shield);
                 shieldActive = true;
                 removeMana(100);
            }
            isHoldingRightClick = true;
            rightClickHoldTime += dt;
            mana -=dt * shieldManaRate;
        }
        else{
            if(isHoldingRightClick || mana< 0 )
            {
                shield.destroy();
                shieldActive = false;
            }
            isHoldingRightClick = false;
            rightClickHoldTime = 0;

        }

        if(!swung && attackTimeLeft <.3f && attacking)
        {
            swung = true;
            Vector2f position = new Vector2f(this.gameObject.transform.position.x , this.gameObject.transform.position.y);
            if(facingRightOfClick)
            {
                position.add(.4f,.05f);
            }
            else {
                position.sub(.4f,-.05f);
            }

            Window.getScene().addGameObjectToScene(Prefabs.PlayerAttackBox(position , .8f, .85f , 100 , .1f));

        }

        if(attacking && attackTimeLeft <= 0)
        {
            stateMachine.trigger("attackRun");
            attacking = false;
            swung = false;
            attackTimeLeft = .55f;
        }else if(attacking)
        {
            attackTimeLeft -=dt;
        }


        checkOnGround();
        if(KeyListener.isKeyPressed(GLFW_KEY_SPACE) && (jumpTime > 0 || onGround || groundDebounce > 0))
        {
            if((onGround || groundDebounce > 0) && jumpTime <= 0)
            {
                AssetPool.getSound("assets/sounds/jump-small.ogg").play();
                jumpTime = .7f ;
                this.velocity.y = jumpImpulse;
                stateMachine.trigger("startRunning");
                stateMachine.trigger("runJump");
            }
            else if(jumpTime > 0)
            {
                jumpTime -= dt;
                this.velocity.y = ((jumpTime / .078f) * jumpBoost);

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

        if(this.velocity.y <=0)
        {
            stateMachine.trigger("jumpFall");
        }
        if(this.velocity.y == 0)
        {
            stateMachine.trigger("fallRun");
        }




        this.velocity.x += this.acceleration.x * dt;
        this.velocity.y += this.acceleration.y * dt;
        this.velocity.x = Math.max(Math.min(this.velocity.x, this.terminalVelocity.x), -this.terminalVelocity.x) ;
        this.velocity.y = Math.max(Math.min(this.velocity.y, this.terminalVelocity.y), -this.terminalVelocity.y);

       if(sprintCoolDown <= 0)
       {
           if(KeyListener.isKeyPressed(GLFW_KEY_LEFT_SHIFT) && !sprinting )
           {

               sprinting = true;
               //mana -= 50f;
               if(facingRight)
               {
                   this.velocity.x = sprintSpeed;
               }
               else
               {
                   this.velocity.x = -sprintSpeed;
               }
           }
           else if(sprinting && sprintTime > 0)
           {
               if(facingRight)
               {
                   this.velocity.x = sprintSpeed;
               }
               else
               {
                   this.velocity.x = -sprintSpeed;
               }
               sprintTime -=dt;
           }
           else if(sprinting )
           {
               sprinting = false;
               sprintTime = .3f;
               sprintCoolDown = 1f;

           }
       }
       else
       {
           sprintCoolDown -=dt;
       }


        this.rb.setVelocity(this.velocity);
        this.rb.setAngularVelocity(0);


    }

    public void checkOnGround()
    {

        float innerPlayerWidth = this.gameObject.getComponent(PillboxCollider.class).width * 0.47f;
        float yVal = -0.33f;
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
        if(collidingObject.getComponent(gluttonyAI.class) != null)
        {
            this.rb.setVelocityX(0);
        }

    }

    @Override
    public void preSolve(GameObject obj, Contact contact , Vector2f contactNormal)
    {

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
        stateMachine.trigger("jumpFall");
        stateMachine.trigger("fallRun");
        stateMachine.trigger("startRunning");
        stateMachine.trigger("attackRun");
        stateMachine.trigger("die");
        isDead = true;
        this.rb.setBodyType(BodyType.Static);
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

        if( shieldActive) return;
        if(isHurtInvincible())return; ;
        health -= damageAmount;
        AssetPool.getSound("assets/sounds/player/hit.ogg").play();
        if(health <= 0)
        {
            health = 0;
            die();
            return;
        }


        hurtInvincibilityTimeLeft = hurtInvincibleTime;


    }

    public int getHealthLimit()
    {
        return healthLimit;
    }

    public int getHealth()
    {
        return health;
    }
    public float getMana()
    {
        return mana;
    }
    public float getManaLimit()
    {
        return maxMana;
    }

    public void heal(int healAmount)
    {
        health += healAmount;
        if(health >healthLimit){
            health = healthLimit;
        }
    }

    public void replenishMana(float amount)
    {
        mana += amount;
        if(mana> maxMana)
        {
            heal((int)((mana - maxMana)/5));
            mana = maxMana;
        }
    }

    public void removeMana(float amount)
    {

        mana -=amount;
        if(mana < 0)
        {
            damage(-(int)mana);
            mana = 0;
        }
    }

    public boolean isFacingRight()
    {
        return facingRight;
    }



}
