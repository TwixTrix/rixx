package Rixx;

import components.*;
import org.joml.Vector2f;
import physics2D.components.Box2DCollider;
import physics2D.components.CircleCollider;
import physics2D.components.PillboxCollider;
import physics2D.components.RigidBody2D;
import physics2D.enums.BodyType;
import util.AssetPool;



public class Prefabs {

    public static GameObject generateSpriteObject(Sprite sprite, float sizeX, float sizeY) {
        GameObject block = Window.getScene().createGameObject("Sprite_Object_Gen");
        block.transform.scale.x = sizeX ;
        block.transform.scale.y= sizeY;
        SpriteRenderer renderer = new SpriteRenderer();
        renderer.setSprite(sprite);
        block.addComponent(renderer);

        return block;
    }
    public static GameObject generateMario() {
        Spritesheet playerSprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");
        Spritesheet bigPlayerSprites = AssetPool.getSpritesheet("assets/images/bigSpritesheet.png");
        GameObject mario = generateSpriteObject(playerSprites.getSprite(0), 0.25f, 0.25f);

        // Little mario animations
        AnimationState run = new AnimationState();
        run.title = "Run";
        float defaultFrameTime = 0.2f;
        run.addFrame(playerSprites.getSprite(0), defaultFrameTime);
        run.addFrame(playerSprites.getSprite(2), defaultFrameTime);
        run.addFrame(playerSprites.getSprite(3), defaultFrameTime);
        run.addFrame(playerSprites.getSprite(2), defaultFrameTime);
        run.setLoop(true);

        AnimationState switchDirection = new AnimationState();
        switchDirection.title = "Switch Direction";
        switchDirection.addFrame(playerSprites.getSprite(4), 0.1f);
        switchDirection.setLoop(false);

        AnimationState idle = new AnimationState();
        idle.title = "Idle";
        idle.addFrame(playerSprites.getSprite(0), 0.1f);
        idle.setLoop(false);

        AnimationState jump = new AnimationState();
        jump.title = "Jump";
        jump.addFrame(playerSprites.getSprite(5), 0.1f);
        jump.setLoop(false);

        // Big mario animations
        AnimationState bigRun = new AnimationState();
        bigRun.title = "BigRun";
        bigRun.addFrame(bigPlayerSprites.getSprite(0), defaultFrameTime);
        bigRun.addFrame(bigPlayerSprites.getSprite(1), defaultFrameTime);
        bigRun.addFrame(bigPlayerSprites.getSprite(2), defaultFrameTime);
        bigRun.addFrame(bigPlayerSprites.getSprite(3), defaultFrameTime);
        bigRun.addFrame(bigPlayerSprites.getSprite(2), defaultFrameTime);
        bigRun.addFrame(bigPlayerSprites.getSprite(1), defaultFrameTime);
        bigRun.setLoop(true);

        AnimationState bigSwitchDirection = new AnimationState();
        bigSwitchDirection.title = "Big Switch Direction";
        bigSwitchDirection.addFrame(bigPlayerSprites.getSprite(4), 0.1f);
        bigSwitchDirection.setLoop(false);

        AnimationState bigIdle = new AnimationState();
        bigIdle.title = "BigIdle";
        bigIdle.addFrame(bigPlayerSprites.getSprite(0), 0.1f);
        bigIdle.setLoop(false);

        AnimationState bigJump = new AnimationState();
        bigJump.title = "BigJump";
        bigJump.addFrame(bigPlayerSprites.getSprite(5), 0.1f);
        bigJump.setLoop(false);

        // Fire mario animations
        int fireOffset = 21;
        AnimationState fireRun = new AnimationState();
        fireRun.title = "FireRun";
        fireRun.addFrame(bigPlayerSprites.getSprite(fireOffset + 0), defaultFrameTime);
        fireRun.addFrame(bigPlayerSprites.getSprite(fireOffset + 1), defaultFrameTime);
        fireRun.addFrame(bigPlayerSprites.getSprite(fireOffset + 2), defaultFrameTime);
        fireRun.addFrame(bigPlayerSprites.getSprite(fireOffset + 3), defaultFrameTime);
        fireRun.addFrame(bigPlayerSprites.getSprite(fireOffset + 2), defaultFrameTime);
        fireRun.addFrame(bigPlayerSprites.getSprite(fireOffset + 1), defaultFrameTime);
        fireRun.setLoop(true);

        AnimationState fireSwitchDirection = new AnimationState();
        fireSwitchDirection.title = "Fire Switch Direction";
        fireSwitchDirection.addFrame(bigPlayerSprites.getSprite(fireOffset + 4), 0.1f);
        fireSwitchDirection.setLoop(false);

        AnimationState fireIdle = new AnimationState();
        fireIdle.title = "FireIdle";
        fireIdle.addFrame(bigPlayerSprites.getSprite(fireOffset + 0), 0.1f);
        fireIdle.setLoop(false);

        AnimationState fireJump = new AnimationState();
        fireJump.title = "FireJump";
        fireJump.addFrame(bigPlayerSprites.getSprite(fireOffset + 5), 0.1f);
        fireJump.setLoop(false);

        AnimationState die = new AnimationState();
        die.title = "Die";
        die.addFrame(playerSprites.getSprite(6), 0.1f);
        die.setLoop(false);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(run);
        stateMachine.addState(idle);
        stateMachine.addState(switchDirection);
        stateMachine.addState(jump);
        stateMachine.addState(die);

        stateMachine.addState(bigRun);
        stateMachine.addState(bigIdle);
        stateMachine.addState(bigSwitchDirection);
        stateMachine.addState(bigJump);

        stateMachine.addState(fireRun);
        stateMachine.addState(fireIdle);
        stateMachine.addState(fireSwitchDirection);
        stateMachine.addState(fireJump);

        stateMachine.setDefaultState(idle.title);
        stateMachine.addState(run.title, switchDirection.title, "switchDirection");
        stateMachine.addState(run.title, idle.title, "stopRunning");
        stateMachine.addState(run.title, jump.title, "jump");
        stateMachine.addState(switchDirection.title, idle.title, "stopRunning");
        stateMachine.addState(switchDirection.title, run.title, "startRunning");
        stateMachine.addState(switchDirection.title, jump.title, "jump");
        stateMachine.addState(idle.title, run.title, "startRunning");
        stateMachine.addState(idle.title, jump.title, "jump");
        stateMachine.addState(jump.title, idle.title, "stopJumping");

        stateMachine.addState(bigRun.title, bigSwitchDirection.title, "switchDirection");
        stateMachine.addState(bigRun.title, bigIdle.title, "stopRunning");
        stateMachine.addState(bigRun.title, bigJump.title, "jump");
        stateMachine.addState(bigSwitchDirection.title, bigIdle.title, "stopRunning");
        stateMachine.addState(bigSwitchDirection.title, bigRun.title, "startRunning");
        stateMachine.addState(bigSwitchDirection.title, bigJump.title, "jump");
        stateMachine.addState(bigIdle.title, bigRun.title, "startRunning");
        stateMachine.addState(bigIdle.title, bigJump.title, "jump");
        stateMachine.addState(bigJump.title, bigIdle.title, "stopJumping");

        stateMachine.addState(fireRun.title, fireSwitchDirection.title, "switchDirection");
        stateMachine.addState(fireRun.title, fireIdle.title, "stopRunning");
        stateMachine.addState(fireRun.title, fireJump.title, "jump");
        stateMachine.addState(fireSwitchDirection.title, fireIdle.title, "stopRunning");
        stateMachine.addState(fireSwitchDirection.title, fireRun.title, "startRunning");
        stateMachine.addState(fireSwitchDirection.title, fireJump.title, "jump");
        stateMachine.addState(fireIdle.title, fireRun.title, "startRunning");
        stateMachine.addState(fireIdle.title, fireJump.title, "jump");
        stateMachine.addState(fireJump.title, fireIdle.title, "stopJumping");

        stateMachine.addState(run.title, bigRun.title, "powerup");
        stateMachine.addState(idle.title, bigIdle.title, "powerup");
        stateMachine.addState(switchDirection.title, bigSwitchDirection.title, "powerup");
        stateMachine.addState(jump.title, bigJump.title, "powerup");
        stateMachine.addState(bigRun.title, fireRun.title, "powerup");
        stateMachine.addState(bigIdle.title, fireIdle.title, "powerup");
        stateMachine.addState(bigSwitchDirection.title, fireSwitchDirection.title, "powerup");
        stateMachine.addState(bigJump.title, fireJump.title, "powerup");

        stateMachine.addState(bigRun.title, run.title, "damage");
        stateMachine.addState(bigIdle.title, idle.title, "damage");
        stateMachine.addState(bigSwitchDirection.title, switchDirection.title, "damage");
        stateMachine.addState(bigJump.title, jump.title, "damage");
        stateMachine.addState(fireRun.title, bigRun.title, "damage");
        stateMachine.addState(fireIdle.title, bigIdle.title, "damage");
        stateMachine.addState(fireSwitchDirection.title, bigSwitchDirection.title, "damage");
        stateMachine.addState(fireJump.title, bigJump.title, "damage");

        stateMachine.addState(run.title, die.title, "die");
        stateMachine.addState(switchDirection.title, die.title, "die");
        stateMachine.addState(idle.title, die.title, "die");
        stateMachine.addState(jump.title, die.title, "die");
        stateMachine.addState(bigRun.title, run.title, "die");
        stateMachine.addState(bigSwitchDirection.title, switchDirection.title, "die");
        stateMachine.addState(bigIdle.title, idle.title, "die");
        stateMachine.addState(bigJump.title, jump.title, "die");
        stateMachine.addState(fireRun.title, bigRun.title, "die");
        stateMachine.addState(fireSwitchDirection.title, bigSwitchDirection.title, "die");
        stateMachine.addState(fireIdle.title, bigIdle.title, "die");
        stateMachine.addState(fireJump.title, bigJump.title, "die");
        mario.addComponent(stateMachine);

        PillboxCollider pb = new PillboxCollider();

        pb.width = 0.39f;
        pb.height = 0.31f;
        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setContinuousCollision(false);
        rb.setFixedRotation(true);
        rb.setMass(25.0f);


        mario.addComponent(rb);
        mario.addComponent(pb);
        mario.addComponent(new PlayerController());
        mario.transform.zIndex = 1;

        return mario;
    }
    public static GameObject generateQuestionBlock() {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject questionBlock = generateSpriteObject(items.getSprite(0),0.25f, 0.25f );

        AnimationState flicker = new AnimationState();
        flicker.title  = "Flicker";
        float defaultFrameTime  = 0.23f;
        flicker.addFrame(items.getSprite(0) , 0.57f);
        flicker.addFrame(items.getSprite(1) , defaultFrameTime);
        flicker.addFrame(items.getSprite(2) , defaultFrameTime);
        flicker.setLoop(true);

        AnimationState inactive = new AnimationState();
        inactive.title = "Inactive";
        inactive.addFrame(items.getSprite(3) , 0.1f);
        inactive.setLoop(false);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(flicker);
        stateMachine.addState(inactive);
        stateMachine.setDefaultState(flicker.title);
        stateMachine.addState(flicker.title ,inactive.title, "setInactive");
        questionBlock.addComponent(stateMachine);
        questionBlock.addComponent(new QuestionBlock());

       RigidBody2D rb = new RigidBody2D();
       rb.setBodyType(BodyType.Static);
       questionBlock.addComponent(rb);
        Box2DCollider box2D = new Box2DCollider();
        box2D.setHalfSize(new Vector2f(0.25f,0.25f));
        questionBlock.addComponent(box2D);
        questionBlock.addComponent(new Ground());


        return questionBlock;
    }

    public static GameObject generateBlockCoin()
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject coin = generateSpriteObject(items.getSprite(7),0.25f, 0.25f );

        AnimationState coinFlip = new AnimationState();
        coinFlip.title  = "Flicker";
        float defaultFrameTime  = 0.23f;
        coinFlip.addFrame(items.getSprite(7) , 0.57f);
        coinFlip.addFrame(items.getSprite(8) , defaultFrameTime);
        coinFlip.addFrame(items.getSprite(9) , defaultFrameTime);
        coinFlip.setLoop(true);



        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(coinFlip);
        stateMachine.setDefaultState(coinFlip.title);
        coin.addComponent(stateMachine);
        coin.addComponent(new QuestionBlock());

       coin.addComponent(new BlockCoin());


        return coin;
    }

    public static GameObject generateMushroom()
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject mushroom = generateSpriteObject(items.getSprite(10),0.25f, 0.25f );

        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setFixedRotation(true);
        rb.setContinuousCollision(false);
        mushroom.addComponent(rb);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(0.14f);
        mushroom.addComponent(circleCollider);
        mushroom.addComponent(new MushroomAI());

        return mushroom;
    }

    public static GameObject generateFlower()
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject flower = generateSpriteObject(items.getSprite(20),0.25f, 0.25f );

        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Static);
        rb.setFixedRotation(true);
        rb.setContinuousCollision(false);
        flower.addComponent(rb);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(0.14f);
        flower.addComponent(circleCollider);
        flower.addComponent(new Flower());

        return flower;
    }

    public static GameObject generateGoomba()
    {
        Spritesheet sprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");
        GameObject goomba = generateSpriteObject(sprites.getSprite(14),0.25f, 0.25f );

        AnimationState walk = new AnimationState();
        walk.title  = "Walk";
        float defaultFrameTime  = 0.23f;
        walk.addFrame(sprites.getSprite(14) , defaultFrameTime);
        walk.addFrame(sprites.getSprite(15) , defaultFrameTime);
        walk.setLoop(true);

        AnimationState squashed = new AnimationState();
        squashed.title = "Squashed";
        squashed.addFrame(sprites.getSprite(16),0.1f);
        squashed.setLoop(false);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(walk);
        stateMachine.addState(squashed);
        stateMachine.setDefaultState(walk.title);
        stateMachine.addState(walk.title, squashed.title, "SquashMe");
        goomba.addComponent(stateMachine);

        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setMass(0.1f);
        rb.setFixedRotation(true);
        goomba.addComponent(rb);
        CircleCollider circle  = new CircleCollider();
        circle.setRadius(0.22f);
        goomba.addComponent(circle);

        goomba.addComponent( new GoombaAI());

        return goomba;
    }

    public static GameObject generateAttackBug()
    {
        Spritesheet sprites = AssetPool.getSpritesheet("assets/images/SpriteSheets/bug.png");
        GameObject bug = generateSpriteObject(sprites.getSprite(0),0.25f, 0.25f );



        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setMass(0.1f);
        rb.setFixedRotation(true);
        bug.addComponent(rb);
        CircleCollider circle  = new CircleCollider();
        circle.setRadius(0.11f);
        bug.addComponent(circle);

        bug.addComponent( new AttackBugAI());

        return bug;
    }

    public static GameObject generatePipe(Direction direction)
    {
        Spritesheet pipes = AssetPool.getSpritesheet("assets/images/SpriteSheets/pipes.png");
        int index = direction == Direction.Down ? 0: direction == Direction.Up ? 1 : direction == Direction.Right ? 2 : direction == Direction.Left ? 3 : -1;
        assert index != -1 : "invalid pipe direciton";
        GameObject pipe = generateSpriteObject(pipes.getSprite(index),0.5f, 0.5f );

        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Static);
        rb.setFixedRotation(true);
        rb.setContinuousCollision(true);
        pipe.addComponent(rb);

        Box2DCollider box2DCollider = new Box2DCollider();
        box2DCollider.setHalfSize(new Vector2f(0.5f, 0.5f));
        pipe.addComponent(box2DCollider);
        pipe.addComponent(new Pipe(direction));
        pipe.addComponent(new Ground());


        return pipe;
    }

    public static GameObject generateTurtle()
    {
        Spritesheet sprites = AssetPool.getSpritesheet("assets/images/SpriteSheets/turtle.png");
        GameObject turtle = generateSpriteObject(sprites.getSprite(0),0.25f, 0.35f );

        AnimationState walk = new AnimationState();
        walk.title  = "Walk";
        float defaultFrameTime  = 0.23f;
        walk.addFrame(sprites.getSprite(0) , defaultFrameTime);
        walk.addFrame(sprites.getSprite(1) , defaultFrameTime);
        walk.setLoop(true);

        AnimationState squashed = new AnimationState();
        squashed.title = "TurtleShellSpin";
        squashed.addFrame(sprites.getSprite(2),0.1f);
        squashed.setLoop(false);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(walk);
        stateMachine.addState(squashed);
        stateMachine.setDefaultState(walk.title);
        stateMachine.addState(walk.title, squashed.title, "SquashMe");
        turtle.addComponent(stateMachine);

        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setMass(0.1f);
        rb.setFixedRotation(true);
        turtle.addComponent(rb);
        CircleCollider circle  = new CircleCollider();
        circle.setRadius(0.13f);
        circle.setOffset(new Vector2f(0,-0.05f));
        turtle.addComponent(circle);

        turtle.addComponent(new TurtleAI());

        return turtle;
    }

    public static GameObject generateFlagtop()
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject flagtop = generateSpriteObject(items.getSprite(6),0.25f, 0.25f );

        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setFixedRotation(true);
        rb.setBodyType(BodyType.Static);
        rb.setContinuousCollision(false);
        flagtop.addComponent(rb);

        Box2DCollider box2DCollider = new Box2DCollider();
        box2DCollider.setHalfSize(new Vector2f(0.1f, 0.25f));
        box2DCollider.setOffset(new Vector2f(-0.075f, 0));
        flagtop.addComponent(box2DCollider);
        flagtop.addComponent(new FlagPole(true));

        return flagtop;
    }

    public static GameObject generateFlagPole()
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject flagtop = generateSpriteObject(items.getSprite(33),0.25f, 0.25f );

        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setFixedRotation(true);
        rb.setBodyType(BodyType.Static);
        rb.setContinuousCollision(false);
        flagtop.addComponent(rb);

        Box2DCollider box2DCollider = new Box2DCollider();
        box2DCollider.setHalfSize(new Vector2f(0.1f, 0.25f));
        box2DCollider.setOffset(new Vector2f(-0.075f, 0));
        flagtop.addComponent(box2DCollider);
        flagtop.addComponent(new FlagPole(false));




        return flagtop;
    }

    public static GameObject generateStartButton()
    {

        GameObject button = generateSpriteObject(AssetPool.getSpritesheet("assets/images/start.png").getSprite(0), 1f,1f);
        button.addComponent(new MenuStart(1f,.5f));

        return button;
    }

    public static GameObject generateNewSaveButton()
    {

        GameObject button = generateSpriteObject(AssetPool.getSpritesheet("assets/images/newSave.png").getSprite(0), 1f,1f);
        button.addComponent(new NewSaveButton(1f,.5f));

        return button;
    }

    public static GameObject generateFireball(Vector2f position)
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject fireball = generateSpriteObject(items.getSprite(32),0.18f, 0.18f );
        fireball.transform.position = position;

        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setFixedRotation(true);
        rb.setContinuousCollision(false);
        fireball.addComponent(rb);

     CircleCollider circleCollider = new CircleCollider();
     circleCollider.setRadius(.08f);
     fireball.addComponent(circleCollider);
     fireball.addComponent(new Fireball());

        return fireball;
    }

    public static GameObject generateFlameBall()
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject fireball = generateSpriteObject(items.getSprite(32),0.18f, 0.18f );


        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setFixedRotation(true);
        rb.setContinuousCollision(false);
        fireball.addComponent(rb);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(.08f);
        fireball.addComponent(circleCollider);
        fireball.addComponent(new FlameBall());

        return fireball;
    }

    public static GameObject generateEmptyHealthBar()
    {
        Spritesheet empty  = AssetPool.getSpritesheet("assets/images/SpriteSheets/empty.png");
        GameObject bar = generateSpriteObject(empty.getSprite(0), 0.5f, 0.1f);
        bar.addComponent(new EmptyBar());
        return bar;
    }
    public static  GameObject generateHealthBar()
    {
        Spritesheet empty  = AssetPool.getSpritesheet("assets/images/SpriteSheets/health.png");
        GameObject bar = generateSpriteObject(empty.getSprite(0), 0.5f, 0.1f);
        bar.addComponent(new HealthBar());
        return bar;
    }
    public static GameObject generateEmptyManaBar()
    {
        Spritesheet empty  = AssetPool.getSpritesheet("assets/images/SpriteSheets/empty.png");
        GameObject bar = generateSpriteObject(empty.getSprite(0), 0.5f, 0.1f);
        bar.addComponent(new emptyManaBar());
        return bar;
    }
    public static  GameObject generateManaBar()
    {
        Spritesheet empty  = AssetPool.getSpritesheet("assets/images/SpriteSheets/mana.png");
        GameObject bar = generateSpriteObject(empty.getSprite(0), 0.5f, 0.1f);
        bar.addComponent(new ManaBar());
        return bar;
    }

    public static GameObject generateSkelly()
    {
        Spritesheet skelatons = AssetPool.getSpritesheet("assets/images/SpriteSheets/Skelaton.png");
        // Little mario animations
        GameObject skelly = generateSpriteObject(skelatons.getSprite(0), 1.5f, 1.5f );


        AnimationState run = new AnimationState();
        run.title = "Run";
        float defaultFrameTime = 0.175f;
        run.addFrame(skelatons.getSprite(26), defaultFrameTime);
        run.addFrame(skelatons.getSprite(27), defaultFrameTime);
        run.addFrame(skelatons.getSprite(28), defaultFrameTime);
        run.addFrame(skelatons.getSprite(29), defaultFrameTime);
        run.addFrame(skelatons.getSprite(30), defaultFrameTime);
        run.addFrame(skelatons.getSprite(31), defaultFrameTime);
        run.addFrame(skelatons.getSprite(32), defaultFrameTime);
        run.addFrame(skelatons.getSprite(33), defaultFrameTime);
        run.addFrame(skelatons.getSprite(34), defaultFrameTime);
        run.addFrame(skelatons.getSprite(35), defaultFrameTime);
        run.addFrame(skelatons.getSprite(36), defaultFrameTime);
        run.addFrame(skelatons.getSprite(37), defaultFrameTime);
        run.setLoop(true);


        AnimationState idle = new AnimationState();
        idle.title = "Idle";
        idle.addFrame(skelatons.getSprite(39), defaultFrameTime);
        idle.addFrame(skelatons.getSprite(40), defaultFrameTime);
        idle.addFrame(skelatons.getSprite(41), defaultFrameTime);
        idle.addFrame(skelatons.getSprite(42), defaultFrameTime);
        idle.setLoop(true);


        AnimationState die = new AnimationState();
        die.title = "Die";
        die.addFrame(skelatons.getSprite(13), defaultFrameTime);
        die.addFrame(skelatons.getSprite(14), defaultFrameTime);
        die.addFrame(skelatons.getSprite(15), defaultFrameTime);
        die.addFrame(skelatons.getSprite(16), defaultFrameTime);
        die.addFrame(skelatons.getSprite(17), defaultFrameTime);
        die.addFrame(skelatons.getSprite(18), defaultFrameTime);
        die.addFrame(skelatons.getSprite(19), defaultFrameTime);
        die.addFrame(skelatons.getSprite(20), defaultFrameTime);
        die.addFrame(skelatons.getSprite(21), defaultFrameTime);
        die.addFrame(skelatons.getSprite(22), defaultFrameTime);
        die.addFrame(skelatons.getSprite(23), defaultFrameTime);
        die.addFrame(skelatons.getSprite(24), defaultFrameTime);
        die.addFrame(skelatons.getSprite(25), defaultFrameTime);
        die.setLoop(false);

        AnimationState attack = new AnimationState();
        attack.title = "attack";
        attack.addFrame(skelatons.getSprite(0),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(1),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(2),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(3),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(4),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(5),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(6),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(7),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(8),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(9),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(10),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(11),defaultFrameTime);
        attack.addFrame(skelatons.getSprite(12),defaultFrameTime);
        attack.setLoop(true);


        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(attack);
        stateMachine.addState(run);
        stateMachine.addState(idle);
        stateMachine.addState(die);


        stateMachine.setDefaultState(idle.title);
        stateMachine.addState(run.title, idle.title, "stopRunning");
        stateMachine.addState(idle.title, run.title, "startRunning");
        stateMachine.addState(idle.title, attack.title, "startAttacking");
        stateMachine.addState(attack.title, idle.title, "stopAttacking");
        stateMachine.addState(attack.title, die.title, "attackingDeath");
        stateMachine.addState(run.title, die.title, "runningDeath");
        stateMachine.addState(idle.title, die.title,"idleDeath");

        skelly.addComponent(stateMachine);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(.35f);
        skelly.addComponent(circleCollider);
        RigidBody2D rb = new RigidBody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setContinuousCollision(false);
        rb.setFixedRotation(true);
        rb.setMass(.01f);
        skelly.addComponent(rb);

        skelly.addComponent(new SkellyAI());
        return skelly;
    }


    public static GameObject generateBearded()
    {
        Spritesheet walkSprite = AssetPool.getSpritesheet("assets/images/townMobs/bearded-walk.png");
        Spritesheet idleSprite = AssetPool.getSpritesheet("assets/images/townMobs/bearded-idle.png");
        GameObject man = generateSpriteObject(idleSprite.getSprite(0),0.625f, 0.734375f );

        AnimationState walk = new AnimationState();
        walk.title  = "Walk";
        float defaultFrameTime  = 0.18f;
        walk.addFrame(walkSprite.getSprite(0),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(1),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(2),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(3),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(4),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(5),defaultFrameTime );
        walk.setLoop(true);

        AnimationState idle = new AnimationState();
        idle.title = "walk";
        idle.addFrame(idleSprite.getSprite(0),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(1),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(2),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(3),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(4),defaultFrameTime );
        idle.setLoop(true);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(walk);
        stateMachine.addState(idle);
        stateMachine.setDefaultState(idle.title);
        stateMachine.addState(idle.title, walk.title, "startWalking");
        stateMachine.addState(walk.title, idle.title ,"stopWalking");


        man.addComponent(stateMachine);
        man.transform.zIndex = 5;
        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(.22f);
        circleCollider.setOffset(new Vector2f(0,-.1f));
        man.addComponent(circleCollider);
        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setMass(.011f);
        rigidBody2D.setContinuousCollision(false);
        rigidBody2D.setFixedRotation(true);
        man.addComponent(rigidBody2D);
        man.addComponent(new beardedAI());



        return man;
    }

    public static GameObject generateHatMan()
    {
        Spritesheet walkSprite = AssetPool.getSpritesheet("assets/images/townMobs/hat-man-walk.png");
        Spritesheet idleSprite = AssetPool.getSpritesheet("assets/images/townMobs/hat-man-idle.png");
        GameObject man = generateSpriteObject(idleSprite.getSprite(0),0.609375f, 0.8125f );

        AnimationState walk = new AnimationState();
        walk.title  = "Walk";
        float defaultFrameTime  = 0.18f;
        walk.addFrame(walkSprite.getSprite(0),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(1),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(2),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(3),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(4),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(5),defaultFrameTime );
        walk.setLoop(true);

        AnimationState idle = new AnimationState();
        idle.title = "walk";
        idle.addFrame(idleSprite.getSprite(0),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(1),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(2),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(3),defaultFrameTime );
        idle.setLoop(true);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(walk);
        stateMachine.addState(idle);
        stateMachine.setDefaultState(idle.title);
        stateMachine.addState(idle.title, walk.title, "startWalking");
        stateMachine.addState(walk.title, idle.title ,"stopWalking");


        man.addComponent(stateMachine);
        CircleCollider circleCollider = new CircleCollider();
        man.transform.zIndex = 5;
        circleCollider.setRadius(.22f);
        circleCollider.setOffset(new Vector2f(0,-.16f));
        man.addComponent(circleCollider);
        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setMass(.011f);
        rigidBody2D.setContinuousCollision(false);
        rigidBody2D.setFixedRotation(true);
        man.addComponent(rigidBody2D);
        man.addComponent(new hatManAI());



        return man;
    }

    public static GameObject generateWoman()
    {
        Spritesheet walkSprite = AssetPool.getSpritesheet("assets/images/townMobs/woman-walk.png");
        Spritesheet idleSprite = AssetPool.getSpritesheet("assets/images/townMobs/woman-idle.png");
        GameObject man = generateSpriteObject(idleSprite.getSprite(0),0.578125f, 0.71875f );

        AnimationState walk = new AnimationState();
        walk.title  = "Walk";
        float defaultFrameTime  = 0.18f;
        walk.addFrame(walkSprite.getSprite(0),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(1),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(2),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(3),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(4),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(5),defaultFrameTime );

        walk.setLoop(true);

        AnimationState idle = new AnimationState();
        idle.title = "walk";
        idle.addFrame(idleSprite.getSprite(0),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(1),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(2),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(3),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(4),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(5),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(6),defaultFrameTime );
        idle.setLoop(true);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(walk);
        stateMachine.addState(idle);
        stateMachine.setDefaultState(idle.title);
        stateMachine.addState(idle.title, walk.title, "startWalking");
        stateMachine.addState(walk.title, idle.title ,"stopWalking");


        man.addComponent(stateMachine);
        CircleCollider circleCollider = new CircleCollider();
        man.transform.zIndex = 5;
        circleCollider.setRadius(.2f);
        circleCollider.setOffset(new Vector2f(0,-.12f));
        man.addComponent(circleCollider);
        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setMass(.011f);
        rigidBody2D.setContinuousCollision(false);
        rigidBody2D.setFixedRotation(true);
        man.addComponent(rigidBody2D);
        man.addComponent(new womanAI());



        return man;
    }


    public static GameObject generateOldMan()
    {
        Spritesheet walkSprite = AssetPool.getSpritesheet("assets/images/townMobs/oldman-walk.png");
        Spritesheet idleSprite = AssetPool.getSpritesheet("assets/images/townMobs/oldman-idle.png");
        GameObject man = generateSpriteObject(idleSprite.getSprite(0),0.609375f, 0.8125f );

        AnimationState walk = new AnimationState();
        walk.title  = "Walk";
        float defaultFrameTime  = 0.24f;
        walk.addFrame(walkSprite.getSprite(0),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(1),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(2),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(3),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(4),defaultFrameTime );
        walk.addFrame(walkSprite.getSprite(5),defaultFrameTime );

        walk.setLoop(true);

        AnimationState idleForward = new AnimationState();
        idleForward.title = "idleForward";
        idleForward.addFrame(idleSprite.getSprite(0),defaultFrameTime );
        idleForward.addFrame(idleSprite.getSprite(1),defaultFrameTime );
        idleForward.addFrame(idleSprite.getSprite(2),defaultFrameTime );
        idleForward.addFrame(idleSprite.getSprite(3),defaultFrameTime );
        idleForward.addFrame(idleSprite.getSprite(4),defaultFrameTime );
        idleForward.addFrame(idleSprite.getSprite(5),defaultFrameTime );
        idleForward.addFrame(idleSprite.getSprite(6),defaultFrameTime );
        idleForward.addFrame(idleSprite.getSprite(7),defaultFrameTime );
        idleForward.setLoop(false);


        AnimationState idle = new AnimationState();
        idle.title = "idleBack";
        idle.addFrame(idleSprite.getSprite(7),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(6),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(5),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(4),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(3),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(2),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(1),defaultFrameTime );
        idle.addFrame(idleSprite.getSprite(0),defaultFrameTime );
        idle.setLoop(false);



        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(walk);
        stateMachine.addState(idle);
        stateMachine.addState(idleForward);
        stateMachine.setDefaultState(walk.title);
        stateMachine.addState(idleForward.title, idle.title, "idleLeft");
        stateMachine.addState(idleForward.title, walk.title, "startWalking");
        stateMachine.addState(walk.title, idleForward.title ,"stopWalking");
        stateMachine.addState(idle.title, walk.title, "rewalk");


        man.addComponent(stateMachine);
        CircleCollider circleCollider = new CircleCollider();
        man.transform.zIndex = 5;
        circleCollider.setRadius(.3f);
        circleCollider.setOffset(new Vector2f(0,-.08f));
        man.addComponent(circleCollider);
        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setContinuousCollision(false);
        rigidBody2D.setFixedRotation(true);
        man.addComponent(rigidBody2D);
        man.addComponent(new oldManAI());



        return man;
    }



    public static GameObject generateBloodMoonTower()
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/redMoonTower.png");
        GameObject tower = generateSpriteObject(items.getSprite(0),1.5625f, 2.1875f );

       RigidBody2D rigidBody2D = new RigidBody2D();
       rigidBody2D.setBodyType(BodyType.Static);
       rigidBody2D.setIsSensor();
       Box2DCollider box2DCollider = new Box2DCollider();
       box2DCollider.setHalfSize(new Vector2f(1.5f, 2f));

       AnimationState anim = new AnimationState();
       anim.title = "animation";
       float defaultTime = .2f;
       anim.addFrame(items.getSprite(0),defaultTime);
       anim.addFrame(items.getSprite(1),defaultTime);
       anim.addFrame(items.getSprite(2),defaultTime);
       anim.addFrame(items.getSprite(3),defaultTime);
       anim.addFrame(items.getSprite(4),defaultTime);
       anim.addFrame(items.getSprite(5),defaultTime);
       anim.addFrame(items.getSprite(6),defaultTime);
       anim.addFrame(items.getSprite(7),defaultTime);
       anim.addFrame(items.getSprite(8),defaultTime);
       anim.addFrame(items.getSprite(9),defaultTime);
       anim.addFrame(items.getSprite(10),defaultTime);
       anim.setLoop(true);

       StateMachine stateMachine = new StateMachine();
       stateMachine.addState(anim);
       stateMachine.setDefaultState(anim.title);





       tower.addComponent(rigidBody2D);
       tower.addComponent(box2DCollider);
       tower.addComponent(stateMachine);
       tower.addComponent(new BloodTower());


        return tower;
    }

    public static GameObject generateEyeFlyer()
    {
        Spritesheet attackSprite = AssetPool.getSpritesheet("assets/images/mobs/fly/Attack.png");
        Spritesheet deathSprite = AssetPool.getSpritesheet("assets/images/mobs/fly/Death.png");
        Spritesheet flightSprite = AssetPool.getSpritesheet("assets/images/mobs/fly/Flight.png");
        Spritesheet hitSprite = AssetPool.getSpritesheet("assets/images/mobs/fly/Hit.png");

        GameObject fly = generateSpriteObject(attackSprite.getSprite(1),2.34375f,2.34375f );
        fly.transform.zIndex = 5;

        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setFixedRotation(true);
        rigidBody2D.setContinuousCollision(false);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(.35f);

        StateMachine stateMachine = new StateMachine();
        AnimationState attack = new AnimationState();
        attack.title = "attack";
        float defaultFrameTime = .15f;
        attack.addFrame(attackSprite.getSprite(0),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(1),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(2),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(3),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(4),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(5),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(6),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(7),defaultFrameTime);
        attack.setLoop(true);
        stateMachine.addState(attack);

        AnimationState death = new AnimationState();
        death.title = "death";
        death.addFrame(deathSprite.getSprite(0),defaultFrameTime);
        death.addFrame(deathSprite.getSprite(1),defaultFrameTime);
        death.addFrame(deathSprite.getSprite(2),defaultFrameTime);
        death.addFrame(deathSprite.getSprite(3),defaultFrameTime);
        death.setLoop(false);
        stateMachine.addState(death);

        AnimationState hit = new AnimationState();
        hit.title = "hit";
        hit.addFrame(hitSprite.getSprite(0),defaultFrameTime);
        hit.addFrame(hitSprite.getSprite(1),defaultFrameTime);
        hit.addFrame(hitSprite.getSprite(2),defaultFrameTime);
        hit.addFrame(hitSprite.getSprite(3),defaultFrameTime);
        hit.setLoop(true);
        stateMachine.addState(hit);

        AnimationState flight = new AnimationState();
        flight.title = "flight";
        flight.addFrame(flightSprite.getSprite(0),defaultFrameTime);
        flight.addFrame(flightSprite.getSprite(1),defaultFrameTime);
        flight.addFrame(flightSprite.getSprite(2),defaultFrameTime);
        flight.addFrame(flightSprite.getSprite(3),defaultFrameTime);
        flight.addFrame(flightSprite.getSprite(4),defaultFrameTime);
        flight.addFrame(flightSprite.getSprite(5),defaultFrameTime);
        flight.addFrame(flightSprite.getSprite(6),defaultFrameTime);
        flight.addFrame(flightSprite.getSprite(7),defaultFrameTime);
        flight.setLoop(true);
        stateMachine.addState(flight);
        stateMachine.setDefaultState(flight.title);

        stateMachine.addState(flight.title , attack.title, "attack");
        stateMachine.addState(attack.title , flight.title, "attackFly");
        stateMachine.addState(hit.title, flight.title, "hitFly");
        stateMachine.addState(flight.title , hit.title, "hit");
        stateMachine.addState(flight.title , death.title, "die");


        fly.addComponent(rigidBody2D);
        fly.addComponent(circleCollider);
        fly.addComponent(stateMachine);
        fly.addComponent(new EyeFlyAI());


        return  fly;
    }


    public static GameObject generateSkellyShield()
    {
        Spritesheet attackSprite = AssetPool.getSpritesheet("assets/images/mobs/skelly/Attack.png");
        Spritesheet deathSprite = AssetPool.getSpritesheet("assets/images/mobs/skelly/Death.png");
        Spritesheet walkSprite = AssetPool.getSpritesheet("assets/images/mobs/skelly/Walk.png");
        Spritesheet idleSprite = AssetPool.getSpritesheet("assets/images/mobs/skelly/Idle.png");
        Spritesheet ShieldSprite = AssetPool.getSpritesheet("assets/images/mobs/skelly/Shield.png");
        Spritesheet hitSprite = AssetPool.getSpritesheet("assets/images/mobs/skelly/Hit.png");

        GameObject fly = generateSpriteObject(attackSprite.getSprite(1),2.34375f,2.34375f );
        fly.transform.zIndex = 5;

        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setFixedRotation(true);
        rigidBody2D.setContinuousCollision(false);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(.36f);
        circleCollider.setOffset(new Vector2f(.01f,-.03f));

        StateMachine stateMachine = new StateMachine();
        AnimationState attack = new AnimationState();
        attack.title = "attack";
        float defaultFrameTime = .2f;
        attack.addFrame(attackSprite.getSprite(0),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(1),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(2),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(3),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(4),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(5),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(6),defaultFrameTime);
        attack.addFrame(attackSprite.getSprite(7),defaultFrameTime);
        attack.setLoop(true);
        stateMachine.addState(attack);

        AnimationState shield = new AnimationState();
        shield.title = "shield";
        shield.addFrame(ShieldSprite.getSprite(0),defaultFrameTime  *2);
        shield.addFrame(ShieldSprite.getSprite(1),defaultFrameTime * 2);
        shield.addFrame(ShieldSprite.getSprite(2),defaultFrameTime * 2);
        shield.addFrame(ShieldSprite.getSprite(3),defaultFrameTime * 2);
        shield.setLoop(true);
        stateMachine.addState(shield);

        AnimationState idle = new AnimationState();
        idle.title = "idle";
        idle.addFrame(idleSprite.getSprite(0),defaultFrameTime);
        idle.addFrame(idleSprite.getSprite(1),defaultFrameTime);
        idle.addFrame(idleSprite.getSprite(2),defaultFrameTime);
        idle.addFrame(idleSprite.getSprite(3),defaultFrameTime);
        idle.setLoop(true);
        stateMachine.addState(shield);


        AnimationState death = new AnimationState();
        death.title = "death";
        death.addFrame(deathSprite.getSprite(0),defaultFrameTime);
        death.addFrame(deathSprite.getSprite(1),defaultFrameTime);
        death.addFrame(deathSprite.getSprite(2),defaultFrameTime);
        death.addFrame(deathSprite.getSprite(3),defaultFrameTime);
        death.setLoop(false);
        stateMachine.addState(death);

        AnimationState hit = new AnimationState();
        hit.title = "hit";
        hit.addFrame(hitSprite.getSprite(0),defaultFrameTime);
        hit.addFrame(hitSprite.getSprite(1),defaultFrameTime);
        hit.addFrame(hitSprite.getSprite(2),defaultFrameTime);
        hit.addFrame(hitSprite.getSprite(3),defaultFrameTime);
        hit.setLoop(true);
        stateMachine.addState(hit);

        AnimationState walk = new AnimationState();
        walk.title = "walk";
        walk.addFrame(walkSprite.getSprite(0),defaultFrameTime);
        walk.addFrame(walkSprite.getSprite(1),defaultFrameTime);
        walk.addFrame(walkSprite.getSprite(2),defaultFrameTime);
        walk.addFrame(walkSprite.getSprite(3),defaultFrameTime);

        walk.setLoop(true);
        stateMachine.addState(walk);
        stateMachine.setDefaultState(walk.title);

        stateMachine.addState(walk.title , attack.title, "attack");
        stateMachine.addState(attack.title , walk.title, "attackWalk");
        stateMachine.addState(idle.title, walk.title, "walk");
        stateMachine.addState(walk.title, idle.title, "stopWalking");
        stateMachine.addState(hit.title, walk.title, "hitWalk");
        stateMachine.addState(walk.title , hit.title, "hit");
        stateMachine.addState(walk.title , death.title, "die");
        stateMachine.addState(attack.title , death.title, "attackDeath");
        stateMachine.addState(walk.title, shield.title, "shield");
        stateMachine.addState(shield.title, walk.title, "stopShield");
        stateMachine.addState(shield.title, attack.title, "shieldToAttack");


        fly.addComponent(rigidBody2D);
        fly.addComponent(circleCollider);
        fly.addComponent(stateMachine);
        fly.addComponent(new Skelly2AI());


        return  fly;
    }


    public static GameObject MobAttackBox(Vector2f position ,float width, float height, int damageAmount, float time)
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject box = generateSpriteObject(items.getSprite(0),width,height);
        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Static);
        rigidBody2D.isSensor();
        rigidBody2D.setContinuousCollision( true);
        rigidBody2D.setFixedRotation(true);
        Box2DCollider box2DCollider = new Box2DCollider();
        box2DCollider.setHalfSize(new Vector2f(width,height));

        box.transform.position.x = position.x;
        box.transform.position.y = position.y;

        box.addComponent(rigidBody2D);
        box.addComponent(box2DCollider);
        box.addComponent(new MobAttackBox(time, damageAmount));
        return box;
    }
    public static GameObject PlayerAttackBox(Vector2f position ,float width, float height, int damageAmount, float time)
    {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
        GameObject box = generateSpriteObject(items.getSprite(0),width,height);
        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Static);
        rigidBody2D.isSensor();
        rigidBody2D.setContinuousCollision( true);
        rigidBody2D.setFixedRotation(true);
        Box2DCollider box2DCollider = new Box2DCollider();
        box2DCollider.setHalfSize(new Vector2f(width,height));

        box.transform.position.x = position.x;
        box.transform.position.y = position.y;

        box.addComponent(rigidBody2D);
        box.addComponent(box2DCollider);
        box.addComponent(new playerAttackBox(time, damageAmount));
        return box;
    }

    public static GameObject generateHero()
    {
        Spritesheet attack1 = AssetPool.getSpritesheet("assets/images/player/Attack1.png");
        Spritesheet attack2 = AssetPool.getSpritesheet("assets/images/player/Attack2.png");
        Spritesheet attack3 = AssetPool.getSpritesheet("assets/images/player/Attack3.png");
        Spritesheet death = AssetPool.getSpritesheet("assets/images/player/Death.png");
        Spritesheet fall = AssetPool.getSpritesheet("assets/images/player/Fall.png");
        Spritesheet idle = AssetPool.getSpritesheet("assets/images/player/Idle.png");
        Spritesheet jump = AssetPool.getSpritesheet("assets/images/player/Jump.png");
        Spritesheet run = AssetPool.getSpritesheet("assets/images/player/Run.png");
        Spritesheet hit = AssetPool.getSpritesheet("assets/images/player/hit.png");



        GameObject hero = generateSpriteObject(idle.getSprite(0),2.53125f,2.53125f );
        hero.transform.zIndex = 10;

        float defaultFrameTime = .1f;

        AnimationState attackAnimation1 = new AnimationState();
        attackAnimation1.title = "attack1";
        attackAnimation1.addFrame(attack1.getSprite(0),defaultFrameTime);
        attackAnimation1.addFrame(attack1.getSprite(1),defaultFrameTime);
        attackAnimation1.addFrame(attack1.getSprite(2),defaultFrameTime);
        attackAnimation1.addFrame(attack1.getSprite(3),defaultFrameTime);
        attackAnimation1.addFrame(attack1.getSprite(4),defaultFrameTime);
        attackAnimation1.addFrame(attack1.getSprite(5),defaultFrameTime);
        attackAnimation1.addFrame(attack1.getSprite(6),defaultFrameTime);
        attackAnimation1.setLoop(false);


        AnimationState attackAnimation2 = new AnimationState();
        attackAnimation2.title = "attack2";
        attackAnimation2.addFrame(attack2.getSprite(0),defaultFrameTime);
        attackAnimation2.addFrame(attack2.getSprite(1),defaultFrameTime);
        attackAnimation2.addFrame(attack2.getSprite(2),defaultFrameTime);
        attackAnimation2.addFrame(attack2.getSprite(3),defaultFrameTime);
        attackAnimation2.addFrame(attack2.getSprite(4),defaultFrameTime);
        attackAnimation2.addFrame(attack2.getSprite(5),defaultFrameTime);
        attackAnimation2.addFrame(attack2.getSprite(6),defaultFrameTime);
        //attackAnimation2.addFrame(attack2.getSprite(0),.05f);
        attackAnimation2.setLoop(true);

        AnimationState attackAnimation3 = new AnimationState();
        attackAnimation3.title = "attack3";
        attackAnimation3.addFrame(attack3.getSprite(0),defaultFrameTime);
        attackAnimation3.addFrame(attack3.getSprite(1),defaultFrameTime);
        attackAnimation3.addFrame(attack3.getSprite(2),defaultFrameTime);
        attackAnimation3.addFrame(attack3.getSprite(3),defaultFrameTime);
        attackAnimation3.addFrame(attack3.getSprite(4),defaultFrameTime);
        attackAnimation3.addFrame(attack3.getSprite(5),defaultFrameTime);
        attackAnimation3.addFrame(attack3.getSprite(6),defaultFrameTime);
        attackAnimation3.addFrame(attack3.getSprite(7),defaultFrameTime);
        attackAnimation3.setLoop(false);
        defaultFrameTime = .15f;

        AnimationState deathAnimation = new AnimationState();
        deathAnimation.title = "death";
        deathAnimation.addFrame(death.getSprite(0),defaultFrameTime);
        deathAnimation.addFrame(death.getSprite(1),defaultFrameTime);
        deathAnimation.addFrame(death.getSprite(2),defaultFrameTime);
        deathAnimation.addFrame(death.getSprite(3),defaultFrameTime);
        deathAnimation.addFrame(death.getSprite(4),defaultFrameTime);
        deathAnimation.addFrame(death.getSprite(5),defaultFrameTime);
        deathAnimation.addFrame(death.getSprite(6),defaultFrameTime);
        deathAnimation.setLoop(false);



        AnimationState fallAnimation = new AnimationState();
        fallAnimation.title = "fall";
        fallAnimation.addFrame(fall.getSprite(0),defaultFrameTime);
        fallAnimation.addFrame(fall.getSprite(1),defaultFrameTime);
        fallAnimation.addFrame(fall.getSprite(2),defaultFrameTime);
        fallAnimation.setLoop(true);

        AnimationState jumpAnimation = new AnimationState();
        jumpAnimation.title = "jump";
        jumpAnimation.addFrame(jump.getSprite(0),defaultFrameTime);
        jumpAnimation.addFrame(jump.getSprite(1),defaultFrameTime);
        jumpAnimation.addFrame(jump.getSprite(2),defaultFrameTime);
        jumpAnimation.setLoop(true);

        AnimationState hitAnimation = new AnimationState();
        hitAnimation.title = "hit";
        hitAnimation.addFrame(hit.getSprite(0),defaultFrameTime);
        hitAnimation.addFrame(hit.getSprite(1),defaultFrameTime);
        hitAnimation.addFrame(hit.getSprite(2),defaultFrameTime);
        hitAnimation.setLoop(false);


        AnimationState idleAnimation = new AnimationState();
        idleAnimation.title = "idle";
        idleAnimation.addFrame(idle.getSprite(0),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(1),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(2),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(3),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(4),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(5),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(6),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(7),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(8),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(9),defaultFrameTime);
        idleAnimation.setLoop(true);

        AnimationState runAnimation = new AnimationState();
        runAnimation.title = "run";
        runAnimation.addFrame(run.getSprite(0),defaultFrameTime);
        runAnimation.addFrame(run.getSprite(1),defaultFrameTime);
        runAnimation.addFrame(run.getSprite(2),defaultFrameTime);
        runAnimation.addFrame(run.getSprite(3),defaultFrameTime);
        runAnimation.addFrame(run.getSprite(4),defaultFrameTime);
        runAnimation.addFrame(run.getSprite(5),defaultFrameTime);
        runAnimation.addFrame(run.getSprite(6),defaultFrameTime);
        runAnimation.addFrame(run.getSprite(7),defaultFrameTime);
        runAnimation.setLoop(true);


        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(attackAnimation1);
        stateMachine.addState(attackAnimation2);
        stateMachine.addState(attackAnimation3);
        stateMachine.addState(deathAnimation);
        stateMachine.addState(fallAnimation);
        stateMachine.addState(hitAnimation);
        stateMachine.addState(idleAnimation);
        stateMachine.addState(jumpAnimation);
        stateMachine.addState(runAnimation);
        stateMachine.setDefaultState(runAnimation.title);

        stateMachine.addState(runAnimation.title, jumpAnimation.title, "runJump");
        stateMachine.addState(jumpAnimation.title, fallAnimation.title, "jumpFall");
        stateMachine.addState(fallAnimation.title, runAnimation.title, "fallRun");
        stateMachine.addState(runAnimation.title,idleAnimation.title, "stopRunning");
        stateMachine.addState(idleAnimation.title, runAnimation.title, "startRunning");
        stateMachine.addState(runAnimation.title, attackAnimation1.title, "runAttack");
        stateMachine.addState(idleAnimation.title, attackAnimation1.title, "idleAttack");
        stateMachine.addState(attackAnimation1.title, runAnimation.title, "attackRun");
        stateMachine.addState(runAnimation.title , deathAnimation.title, "die");

        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setContinuousCollision(false);
        rigidBody2D.setFixedRotation(true);
        rigidBody2D.setMass(25f);

        PillboxCollider pillboxCollider = new PillboxCollider();
        pillboxCollider.height = 1f;
        pillboxCollider.width = .5f;




        hero.addComponent(stateMachine);
        hero.addComponent(rigidBody2D);
        hero.addComponent(pillboxCollider);
        hero.addComponent(new PlayerController());







        return hero;
    }



    public static GameObject heroShield(Vector2f position)
    {
        Spritesheet shieldImage = AssetPool.getSpritesheet("assets/images/player/shield.png");
        GameObject shield = generateSpriteObject(shieldImage.getSprite(0) , 1.25f , 1.5f);
        shield.transform.position.x = position.x;
        shield.transform.position.y = position.y + .08f;
        shield.transform.zIndex = 10;

        AnimationState animationState = new AnimationState();
        animationState.title = "flicker";
        float defaultFrameTime = .08f;
        animationState.addFrame(shieldImage.getSprite(0), defaultFrameTime);
        animationState.addFrame(shieldImage.getSprite(1), defaultFrameTime);
        animationState.addFrame(shieldImage.getSprite(2), defaultFrameTime);
        animationState.setLoop(true);
        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(animationState);
        stateMachine.setDefaultState(animationState.title);
        shield.addComponent(stateMachine);
        return shield;
    }

    public static GameObject generateFireWorm()
    {
        Spritesheet attack = AssetPool.getSpritesheet("assets/images/mobs/worm/Attack.png");
        Spritesheet death = AssetPool.getSpritesheet("assets/images/mobs/worm/Death.png");
        Spritesheet hit = AssetPool.getSpritesheet("assets/images/mobs/worm/Hit.png");
        Spritesheet idle = AssetPool.getSpritesheet("assets/images/mobs/worm/idle.png");
        Spritesheet walk = AssetPool.getSpritesheet("assets/images/mobs/worm/walk.png");

        GameObject fireWorm = generateSpriteObject(attack.getSprite(0),1.40625f,1.40625f );
        fireWorm.transform.zIndex = 5;

        AnimationState attackAnimation = new AnimationState();
        attackAnimation.title = "attackAnimation";
        float defaultFrameTime = .1f;
        attackAnimation.addFrame(attack.getSprite(0),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(1),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(2),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(3),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(4),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(5),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(6),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(7),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(8),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(9),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(10),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(11),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(12),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(13),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(14),defaultFrameTime );
        attackAnimation.addFrame(attack.getSprite(15),defaultFrameTime );
        attackAnimation.setLoop(true);

        defaultFrameTime = .2f;

        AnimationState deathAnimation = new AnimationState();
        deathAnimation.title = "deathAnimation";
        deathAnimation.addFrame(death.getSprite(0),defaultFrameTime );
        deathAnimation.addFrame(death.getSprite(1),defaultFrameTime );
        deathAnimation.addFrame(death.getSprite(2),defaultFrameTime );
        deathAnimation.addFrame(death.getSprite(3),defaultFrameTime );
        deathAnimation.addFrame(death.getSprite(4),defaultFrameTime );
        deathAnimation.addFrame(death.getSprite(5),defaultFrameTime );
        deathAnimation.addFrame(death.getSprite(6),defaultFrameTime );
        deathAnimation.addFrame(death.getSprite(7),defaultFrameTime );
        deathAnimation.setLoop(false);

        AnimationState hitAnimation = new AnimationState();
        hitAnimation.title = "hitAnimation";
        hitAnimation.addFrame(hit.getSprite(0),defaultFrameTime);
        hitAnimation.addFrame(hit.getSprite(1),defaultFrameTime);
        hitAnimation.addFrame(hit.getSprite(2),defaultFrameTime);
        hitAnimation.setLoop(true);

        AnimationState idleAnimation = new AnimationState();
        idleAnimation.title = "idleAnimation";
        idleAnimation.addFrame(idle.getSprite(0),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(1),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(2),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(3),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(4),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(5),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(6),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(7),defaultFrameTime);
        idleAnimation.addFrame(idle.getSprite(8),defaultFrameTime);
        idleAnimation.setLoop(true);


        AnimationState walkAnimation = new AnimationState();
        walkAnimation.title = "walkAnimation";
        walkAnimation.addFrame(walk.getSprite(0),defaultFrameTime);
        walkAnimation.addFrame(walk.getSprite(1),defaultFrameTime);
        walkAnimation.addFrame(walk.getSprite(2),defaultFrameTime);
        walkAnimation.addFrame(walk.getSprite(3),defaultFrameTime);
        walkAnimation.addFrame(walk.getSprite(4),defaultFrameTime);
        walkAnimation.addFrame(walk.getSprite(5),defaultFrameTime);
        walkAnimation.addFrame(walk.getSprite(6),defaultFrameTime);
        walkAnimation.addFrame(walk.getSprite(7),defaultFrameTime);
        walkAnimation.addFrame(walk.getSprite(8),defaultFrameTime);
        walkAnimation.setLoop(true);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(attackAnimation);
        stateMachine.addState(deathAnimation);
        stateMachine.addState(hitAnimation);
        stateMachine.addState(idleAnimation);
        stateMachine.addState(walkAnimation);
        stateMachine.setDefaultState(walkAnimation.title);

        stateMachine.addState(idleAnimation.title , walkAnimation.title, "idleWalk");
        stateMachine.addState(attackAnimation.title, walkAnimation.title, "attackWalk");
        stateMachine.addState(walkAnimation.title , attackAnimation.title, "walkAttack");
        stateMachine.addState(walkAnimation.title , idleAnimation.title, "walkIdle");
        stateMachine.addState(walkAnimation.title , deathAnimation.title, "die");
        stateMachine.addState(attackAnimation.title , deathAnimation.title, "attackDie");

        fireWorm.addComponent(stateMachine);

        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setMass(22);
        rigidBody2D.setFixedRotation(true);
        rigidBody2D.setContinuousCollision(false);

        fireWorm.addComponent(rigidBody2D);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(.25f);
        circleCollider.setOffset(new Vector2f(0,.05f));

        fireWorm.addComponent(circleCollider);


        fireWorm.addComponent(new fireWormAI());



        return fireWorm;








    }



    public static GameObject generateFireWormFireball(Vector2f position)
    {
        Spritesheet move = AssetPool.getSpritesheet("assets/images/mobs/worm/Move.png");
        Spritesheet explosion = AssetPool.getSpritesheet("assets/images/mobs/worm/Explosion.png");

        GameObject fireball = generateSpriteObject(move.getSprite(3),0.71875f, 0.71875f );


        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setMass(1);
        rigidBody2D.setContinuousCollision(false);
        rigidBody2D.setFixedRotation(true);
        rigidBody2D.setIsSensor();

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(.15f);

        float defaultFrameTime = .15f;
        AnimationState moveAnimation =new AnimationState();
        moveAnimation.title = "moveAnimation";
        moveAnimation.addFrame(move.getSprite(0),defaultFrameTime );
        moveAnimation.addFrame(move.getSprite(1),defaultFrameTime );
        moveAnimation.addFrame(move.getSprite(2),defaultFrameTime );
        moveAnimation.addFrame(move.getSprite(3),defaultFrameTime );
        moveAnimation.addFrame(move.getSprite(4),defaultFrameTime );
        moveAnimation.addFrame(move.getSprite(5),defaultFrameTime );
        moveAnimation.setLoop(true);


        AnimationState explodeAnimation =new AnimationState();
       explodeAnimation.title = "explodeAnimation";
       explodeAnimation.addFrame(explosion.getSprite(0),defaultFrameTime );
       explodeAnimation.addFrame(explosion.getSprite(1),defaultFrameTime );
       explodeAnimation.addFrame(explosion.getSprite(2),defaultFrameTime );
       explodeAnimation.addFrame(explosion.getSprite(3),defaultFrameTime );
       explodeAnimation.addFrame(explosion.getSprite(4),defaultFrameTime );
       explodeAnimation.addFrame(explosion.getSprite(5),defaultFrameTime );
       explodeAnimation.addFrame(explosion.getSprite(6),defaultFrameTime );
       explodeAnimation.setLoop(false);

       StateMachine stateMachine = new StateMachine();
       stateMachine.addState(moveAnimation);
       stateMachine.addState(explodeAnimation);

       stateMachine.setDefaultState(moveAnimation.title);

       stateMachine.addState(moveAnimation.title , explodeAnimation.title, "explode");

       fireball.addComponent(rigidBody2D);
       fireball.addComponent(circleCollider);
       fireball.addComponent(stateMachine);
       fireball.addComponent(new fireWormFireballAI(position));

       return fireball;





    }


    public static  GameObject generateGluttony()
    {
        Spritesheet mob = AssetPool.getSpritesheet("assets/images/mobs/gluttony.png");
        GameObject gluttony = generateSpriteObject(mob.getSprite(0),2.1875f * 2f , 1.453125f * 2f );

        float defaultFrameTime = .2f;

        AnimationState attack = new AnimationState();
        attack.title = "attack";
        attack.addFrame(mob.getSprite(16) , defaultFrameTime);
        attack.addFrame(mob.getSprite(17) , defaultFrameTime);
        attack.addFrame(mob.getSprite(18) , defaultFrameTime);
        attack.addFrame(mob.getSprite(19) , defaultFrameTime);
        attack.addFrame(mob.getSprite(20) , defaultFrameTime);
        attack.addFrame(mob.getSprite(21) , defaultFrameTime);
        attack.addFrame(mob.getSprite(22) , defaultFrameTime);
        attack.addFrame(mob.getSprite(23) , defaultFrameTime);
        attack.addFrame(mob.getSprite(24) , defaultFrameTime);
        attack.addFrame(mob.getSprite(25) , defaultFrameTime);
        attack.setLoop(false);

        AnimationState cast = new AnimationState();
        cast.title = "cast";
        cast.addFrame(mob.getSprite(39) , defaultFrameTime);
        cast.addFrame(mob.getSprite(40) , defaultFrameTime);
        cast.addFrame(mob.getSprite(41) , defaultFrameTime);
        cast.addFrame(mob.getSprite(42) , defaultFrameTime);
        cast.addFrame(mob.getSprite(43) , defaultFrameTime);
        cast.addFrame(mob.getSprite(44) , defaultFrameTime);
        cast.addFrame(mob.getSprite(45) , defaultFrameTime);
        cast.addFrame(mob.getSprite(46) , defaultFrameTime);
        cast.addFrame(mob.getSprite(47) , defaultFrameTime);
        cast.setLoop(false);

        AnimationState death = new AnimationState();
        death.title = "death";
        death.addFrame(mob.getSprite(29), defaultFrameTime);
        death.addFrame(mob.getSprite(30), defaultFrameTime);
        death.addFrame(mob.getSprite(31), defaultFrameTime);
        death.addFrame(mob.getSprite(32), defaultFrameTime);
        death.addFrame(mob.getSprite(33), defaultFrameTime);
        death.addFrame(mob.getSprite(34), defaultFrameTime);
        death.addFrame(mob.getSprite(35), defaultFrameTime);
        death.addFrame(mob.getSprite(36), defaultFrameTime);
        death.addFrame(mob.getSprite(37), defaultFrameTime);
        death.addFrame(mob.getSprite(38), defaultFrameTime);
        death.setLoop(false);

        AnimationState hurt = new AnimationState();
        hurt.title = "hurt";
        hurt.addFrame(mob.getSprite(26),defaultFrameTime);
        hurt.addFrame(mob.getSprite(27),defaultFrameTime);
        hurt.addFrame(mob.getSprite(28),defaultFrameTime);
        hurt.setLoop(false);

        AnimationState idle = new AnimationState();
        idle.title = "idle";
        idle.addFrame(mob.getSprite(0),defaultFrameTime);
        idle.addFrame(mob.getSprite(1),defaultFrameTime);
        idle.addFrame(mob.getSprite(2),defaultFrameTime);
        idle.addFrame(mob.getSprite(3),defaultFrameTime);
        idle.addFrame(mob.getSprite(4),defaultFrameTime);
        idle.addFrame(mob.getSprite(5),defaultFrameTime);
        idle.addFrame(mob.getSprite(6),defaultFrameTime);
        idle.addFrame(mob.getSprite(7),defaultFrameTime);
        idle.setLoop(true);

        AnimationState walk = new AnimationState();
        walk.title = "walk";
        walk.addFrame(mob.getSprite(8), defaultFrameTime);
        walk.addFrame(mob.getSprite(9), defaultFrameTime);
        walk.addFrame(mob.getSprite(10), defaultFrameTime);
        walk.addFrame(mob.getSprite(11), defaultFrameTime);
        walk.addFrame(mob.getSprite(12), defaultFrameTime);
        walk.addFrame(mob.getSprite(13), defaultFrameTime);
        walk.addFrame(mob.getSprite(14), defaultFrameTime);
        walk.addFrame(mob.getSprite(15), defaultFrameTime);
        walk.setLoop(true);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(attack);
        stateMachine.addState(cast);
        stateMachine.addState(death);
        stateMachine.addState(hurt);
        stateMachine.addState(idle);
        stateMachine.addState(walk);
        stateMachine.setDefaultState(idle.title);

        stateMachine.addState(idle.title , walk.title, "idleWalk");
        stateMachine.addState(walk.title , idle.title , "walkIdle");
        stateMachine.addState(idle.title , attack.title, "idleAttack");
        stateMachine.addState(walk.title , attack.title, "walkAttack");
        stateMachine.addState(attack.title, idle.title,"attackIdle");
        stateMachine.addState(attack.title, death.title, "attackDeath");
        stateMachine.addState(cast.title , death.title, "castDeath");
        stateMachine.addState(idle.title, death.title, "idleDeath");
        stateMachine.addState(walk.title , death.title, "walkDeath");
        stateMachine.addState(attack.title, hurt.title, "attackHurt");
        stateMachine.addState(idle.title, hurt.title, "idleHurt");
        stateMachine.addState(walk.title , hurt.title, "walkHurt");
        stateMachine.addState(cast.title , idle.title, "castIdle");
        stateMachine.addState(idle.title , cast.title, "idleCast");
        stateMachine.addState(walk.title, cast.title, "walkCast");

        gluttony.addComponent(stateMachine);

        RigidBody2D rigidBody2D = new RigidBody2D();
        rigidBody2D.setBodyType(BodyType.Dynamic);
        rigidBody2D.setFixedRotation(true);
        rigidBody2D.setContinuousCollision(false);
        gluttony.addComponent(rigidBody2D);

        PillboxCollider pillboxCollider = new PillboxCollider();
        pillboxCollider.width = 1.9f;
        pillboxCollider.height = 3.25f;
        pillboxCollider.offset = new Vector2f(1.2f , -.36f);
        gluttony.addComponent(pillboxCollider);

        gluttony.addComponent(new gluttonyAI());
        return gluttony;



    }

    public static GameObject generateGluttonySpell()
    {
        Spritesheet mob = AssetPool.getSpritesheet("assets/images/mobs/gluttony.png");
        GameObject spell = generateSpriteObject(mob.getSprite(48),2.1875f * 2f , 1.453125f * 2f );

        AnimationState animationState  = new AnimationState();
        animationState.title = "spell";
        float defaultFrameTime = .2f;
        animationState.addFrame(mob.getSprite(48),defaultFrameTime);
        animationState.addFrame(mob.getSprite(49),defaultFrameTime);
        animationState.addFrame(mob.getSprite(50),defaultFrameTime);
        animationState.addFrame(mob.getSprite(51),defaultFrameTime);
        animationState.addFrame(mob.getSprite(52),defaultFrameTime);
        animationState.addFrame(mob.getSprite(53),defaultFrameTime);
        animationState.addFrame(mob.getSprite(54),defaultFrameTime);
        animationState.addFrame(mob.getSprite(55),defaultFrameTime);
        animationState.addFrame(mob.getSprite(56),defaultFrameTime);
        animationState.addFrame(mob.getSprite(57),defaultFrameTime);
        animationState.addFrame(mob.getSprite(58),defaultFrameTime);
        animationState.addFrame(mob.getSprite(59),defaultFrameTime);
        animationState.addFrame(mob.getSprite(60),defaultFrameTime);
        animationState.addFrame(mob.getSprite(61),defaultFrameTime);
        animationState.addFrame(mob.getSprite(62),defaultFrameTime);
        animationState.addFrame(mob.getSprite(63),defaultFrameTime);
        animationState.setLoop(false);
        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(animationState);
        stateMachine.setDefaultState(animationState.title);

        spell.addComponent(stateMachine);
        spell.addComponent( new gluttonySpell());
        return  spell;


    }

    public static GameObject generateGluttonyHealth()
    {
        Spritesheet empty = AssetPool.getSpritesheet("assets/images/healthBars/gluttonyHealth.png");
        GameObject bar = generateSpriteObject(empty.getSprite(0), .5f , .2f);
        bar.addComponent( new gluttonyHealth());
        return bar;
    }

    public static GameObject generateEmptyGluttonyHealthBar()
    {
        Spritesheet empty = AssetPool.getSpritesheet("assets/images/healthBars/gluttonyEmptyBar.png");
        GameObject bar = generateSpriteObject(empty.getSprite(0), .5f , .2f);
        bar.addComponent( new gluttonyEmpty());
        return bar;
    }







}
