package scenes;

import Rixx.*;
import components.*;
import imgui.ImGui;
import imgui.ImVec2;
import org.joml.Vector2f;
import org.joml.Vector3f;
import physics2D.components.Box2DCollider;
import physics2D.components.RigidBody2D;
import physics2D.components.StairCollider;
import physics2D.enums.BodyType;
import physics2Dtmp.PhysicsSystem2D;
import physics2Dtmp.primitives.Circle;
import physics2Dtmp.rigidbody.Rigidbody2D;
import renderer.DebugDraw;

import util.AssetPool;

import java.io.File;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.Collection;
import java.util.Collections;

public class LevelEditorSceneInitializer extends SceneInitializer {

    private Spritesheet sprites;

   private GameObject levelEditorStuff ;





    public LevelEditorSceneInitializer() {

    }

    @Override
    public void init(Scene scene) {


        sprites = AssetPool.getSpritesheet("assets/images/SpriteSheets/decorationsAndBlocks.png");
        Spritesheet gizmos = AssetPool.getSpritesheet("assets/images/SpriteSheets/gizmos.png");


        levelEditorStuff = scene.createGameObject("LevelEditor");
        levelEditorStuff.setNoSerialize();
        levelEditorStuff.addComponent(new MouseControls());
        levelEditorStuff.addComponent(new KeyControls());
        levelEditorStuff.addComponent(new GridLines());
        levelEditorStuff.addComponent(new EditorCamera(scene.camera()));
        scene.addGameObjectToScene(levelEditorStuff);





    }
    @Override
    public void loadResources(Scene scene) {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/images/SpriteSheets/decorationsAndBlocks.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/decorationsAndBlocks.png"),
                        16, 16, 81, 0));
        AssetPool.addSpritesheet("assets/images/spritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                        16, 16, 26, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/turtle.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/turtle.png"),
                        16, 24, 4, 0));
        AssetPool.addSpritesheet("assets/images/bigSpritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/images/bigSpritesheet.png"),
                        16, 32, 42, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/pipes.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/pipes.png"),
                        32, 32, 4, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/items.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/items.png"),
                        16, 16, 43, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/gizmos.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/gizmos.png"),
                        24, 48, 3, 0));
        AssetPool.addSpritesheet("assets/images/start.png",
                new Spritesheet(AssetPool.getTexture("assets/images/start.png"),
                        100, 100, 1, 0));
        AssetPool.addSpritesheet("assets/images/newSave.png",
                new Spritesheet(AssetPool.getTexture("assets/images/newSave.png"),
                        400, 400, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bricks.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bricks.png"),
                        16, 16, 40, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bricks2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bricks2.png"),
                        32, 32, 11, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bricks3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bricks3.png"),
                        32, 16, 5, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bricks4.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bricks4.png"),
                        16, 16, 6, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bricks5.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bricks5.png"),
                        16, 8, 6, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/pillars.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/pillars.png"),
                        16, 64, 6, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bricks6.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bricks6.png"),
                        8, 8, 6, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bridge.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bridge.png"),
                        112, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/pillars2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/pillars2.png"),
                        16, 32, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/pillars3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/pillars3.png"),
                        28, 12, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/pillars4.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/pillars4.png"),
                        28, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/roof.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/roof.png"),
                        144, 40, 1, 0));
        //dark
        AssetPool.addSpritesheet("assets/images/SpriteSheets/dark.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/dark.png"),
                        32, 32, 24, 0));
        //Twilight
        AssetPool.addSpritesheet("assets/images/SpriteSheets/twilight.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/twilight.png"),
                        16, 16, 78, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/twilightPillar.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/twilightPillar.png"),
                        64, 112, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/twilight2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/twilight2.png"),
                        46, 12, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/twilight3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/twilight3.png"),
                        44, 11, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/twilight4.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/twilight4.png"),
                        16, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/twilight5.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/twilight5.png"),
                        47, 21, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/twilightSpikes.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/twilightSpikes.png"),
                        48, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/twilightSpikes2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/twilightSpikes2.png"),
                        16, 16, 1, 0));
        // church
        AssetPool.addSpritesheet("assets/images/SpriteSheets/churchBrick.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/churchBrick.png"),
                        32, 32, 3, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/churchBrick2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/churchBrick2.png"),
                        16, 16, 5, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/churchDistance.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/churchDistance.png"),
                        32, 64, 3, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/churchFloor.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/churchFloor.png"),
                        48, 41, 3, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/churchFloor2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/churchFloor2.png"),
                        32, 69, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/churchFloor3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/churchFloor3.png"),
                        32, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/churchFloor4.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/churchFloor4.png"),
                        32, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/churchRailing.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/churchRailing.png"),
                        80, 52, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/churchStairs.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/churchStairs.png"),
                        32, 32, 2, 0));
        //rocky
        AssetPool.addSpritesheet("assets/images/SpriteSheets/rockCrystal.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/rockCrystal.png"),
                        25, 25, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/rockCrystal2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/rockCrystal2.png"),
                        26, 20, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/rockPlant.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/rockPlant.png"),
                        32, 24, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/rockPlant2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/rockPlant2.png"),
                        35, 26, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/rocks.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/rocks.png"),
                        19, 96, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/rocks2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/rocks2.png"),
                        32, 64, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/rocks3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/rocks3.png"),
                        33, 64, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/rocks4.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/rocks4.png"),
                        16, 16, 1, 0));
        //cemetery
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery.png"),
                        32, 48, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery2.png"),
                        64, 41, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery3.png"),
                        32, 30, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery4.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery4.png"),
                        32, 96, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery5.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery5.png"),
                        24, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery6.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery6.png"),
                        32, 32, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery7.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery7.png"),
                        32, 48, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery8.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery8.png"),
                        48, 41, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery9.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery9.png"),
                        16, 41, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery10.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery10.png"),
                        32, 32, 4, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery11.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery11.png"),
                        10, 16, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeteryLargeBush.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeteryLargeBush.png"),
                        76, 65, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeterySmallBush.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeterySmallBush.png"),
                        34, 29, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeteryStatue.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeteryStatue.png"),
                        63, 75, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeteryStone.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeteryStone.png"),
                        27, 39, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeteryStone2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeteryStone2.png"),
                        27, 40, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeteryStone3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeteryStone3.png"),
                        27, 33, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeteryStone4.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeteryStone4.png"),
                        19, 38, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeteryTree.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeteryTree.png"),
                        166, 117, 1, 0));

        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeteryTree2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeteryTree2.png"),
                        166, 117, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemeteryTree3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemeteryTree3.png"),
                        176, 171, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cemetery12.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cemetery12.png"),
                        32, 32, 1, 0));

        //castle 1

        AssetPool.addSpritesheet("assets/images/SpriteSheets/castleBricks.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/castleBricks.png"),
                        32, 32, 8, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/castleBricks2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/castleBricks2.png"),
                        16, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/castleBricks3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/castleBricks3.png"),
                        16, 64, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/castleBricks4.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/castleBricks4.png"),
                        32, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/castleBricks5.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/castleBricks5.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/castleStairs.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/castleStairs.png"),
                        16, 31, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/castleStairs2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/castleStairs2.png"),
                        48, 32, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/castleStairs3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/castleStairs3.png"),
                        34, 32, 2, 0));

        //castle 2
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick.png"),
                        16, 16, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick2.png"),
                        96, 48, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick3.png"),
                        48, 21, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick4.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick4.png"),
                        16, 21, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick5.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick5.png"),
                        32, 38, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick6.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick6.png"),
                        16, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick7.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick7.png"),
                        32, 72, 3, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick8.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick8.png"),
                        32, 38, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick9.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick9.png"),
                        32, 22, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick10.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick10.png"),
                        32, 30, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick11.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick11.png"),
                        160, 81, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick12.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick12.png"),
                        26, 32, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick13.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick13.png"),
                        32, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cBrick14.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cBrick14.png"),
                        16, 64, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cDoor.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cDoor.png"),
                        48, 64, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cStairs.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cStairs.png"),
                        16, 25, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cStairs2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cStairs2.png"),
                        32, 41, 2, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/cStairs3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/cStairs3.png"),
                        32, 64, 2, 0));

        //health
        AssetPool.addSpritesheet("assets/images/SpriteSheets/empty.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/empty.png"),
                        100, 20, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/health.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/health.png"),
                        100, 20, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/mana.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/mana.png"),
                        100, 20, 1, 0));

        //skelaton
        AssetPool.addSpritesheet("assets/images/SpriteSheets/Skelaton.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/Skelaton.png"),
                        64, 64, 65, 0));


        //bug sprite
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bug.png", new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bug.png"), 32, 32, 255, 15));

        AssetPool.getTexture("assets/images/blendImage2.png");

        AssetPool.addSound("assets/sounds/main-theme-overworld.ogg", true);
        AssetPool.addSound("assets/sounds/flagpole.ogg", false);
        AssetPool.addSound("assets/sounds/break_block.ogg", false);
        AssetPool.addSound("assets/sounds/bump.ogg", false);
        AssetPool.addSound("assets/sounds/coin.ogg", false);
        AssetPool.addSound("assets/sounds/gameover.ogg", false);
        AssetPool.addSound("assets/sounds/jump-small.ogg", false);
        AssetPool.addSound("assets/sounds/mario_die.ogg", false);
        AssetPool.addSound("assets/sounds/pipe.ogg", false);
        AssetPool.addSound("assets/sounds/powerup.ogg", false);
        AssetPool.addSound("assets/sounds/powerup_appears.ogg", false);
        AssetPool.addSound("assets/sounds/stage_clear.ogg", false);
        AssetPool.addSound("assets/sounds/stomp.ogg", false);
        AssetPool.addSound("assets/sounds/kick.ogg", false);
        AssetPool.addSound("assets/sounds/invincible.ogg", false);

        for (GameObject g : scene.getGameObjects()) {
            if (g.getComponent(SpriteRenderer.class) != null) {
                SpriteRenderer spr = g.getComponent(SpriteRenderer.class);
                if (spr.getTexture() != null) {
                    spr.setTexture(AssetPool.getTexture(spr.getTexture().getFilepath()));
                }
            }
            if (g.getComponent(StateMachine.class) != null) {
                StateMachine stateMachine = g.getComponent(StateMachine.class);
                stateMachine.refreshTextures();
                }

        }
    }





    @Override
    public void imgui() {


        ImGui.begin("Level Editor Stuff");
        levelEditorStuff.imgui();
        ImGui.end();

        ImGui.begin("Objects");
        if(ImGui.beginTabBar("WindowTabBar")) {
            if(ImGui.beginTabItem("bricks")) {


                ImVec2 windowPos = new ImVec2();
                ImGui.getWindowPos(windowPos);
                ImVec2 windowSize = new ImVec2();
                ImGui.getWindowSize(windowSize);
                ImVec2 itemSpacing = new ImVec2();
                ImGui.getStyle().getItemSpacing(itemSpacing);


                Spritesheet bricks  = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks.png");
                float windowX2 = windowPos.x + windowSize.x;
                for (int i = 0; i < 38; i++) {



                    Sprite sprite = bricks.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.25f, 0.25f);
                        //TODO REMOVE
                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        if(i == 12 )
                        {
                            object.transform.zIndex =1;
                            StairCollider b2d = new StairCollider();
                            b2d.setHalfSize(new Vector2f(0.25f, 0.25f));
                            b2d.setFacingRight(true);
                            object.addComponent(b2d);
                            object.addComponent(new Ground());
                            levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                        }
                        else if( i == 3)
                        {
                            object.transform.zIndex =1;
                            StairCollider b2d = new StairCollider();
                            b2d.setHalfSize(new Vector2f(0.25f, 0.25f));
                            b2d.setFacingRight(false);
                            object.transform.scale.x = -object.transform.scale.x;
                            object.addComponent(b2d);
                            object.addComponent(new Ground());
                            levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                        }
                        else{
                            object.transform.zIndex = 1;
                            Box2DCollider b2d = new Box2DCollider();
                            b2d.setHalfSize(new Vector2f(0.25f, 0.25f));
                            object.addComponent(b2d);
                            object.addComponent(new Ground());
                            levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                        }

                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet bricks2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks2.png");
                for (int i = 6; i < 10; i++) {



                    Sprite sprite = bricks2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.5f, 0.5f);
                        //TODO REMOVE
                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(0.5f, 0.5f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);


                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet bricks3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks3.png");
                for (int i = 0; i < 5; i++) {



                    Sprite sprite = bricks3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.5f, 0.25f);
                        //TODO REMOVE
                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(0.5f, 0.25f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);


                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet bricks4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks4.png");
                for (int i = 0; i < 6; i++) {



                    Sprite sprite = bricks4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.25f, 0.25f);
                        //TODO REMOVE
                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(0.25f, 0.25f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);


                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet bricks5 = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks5.png");
                for (int i = 0; i < 6; i++) {



                    Sprite sprite = bricks5.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.25f, 0.125f);
                        //TODO REMOVE
                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(0.25f, 0.125f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);


                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet bricks6 = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks6.png");
                for (int i = 0; i < 6; i++) {



                    Sprite sprite = bricks6.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.125f, 0.125f);
                        //TODO REMOVE
                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(0.125f, 0.125f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);


                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet bridge = AssetPool.getSpritesheet("assets/images/SpriteSheets/bridge.png");
                for (int i = 0; i < 1; i++) {



                    Sprite sprite = bridge.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 1.75f, .5f);
                        //TODO REMOVE
                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(1.75f, .5f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);


                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet roof = AssetPool.getSpritesheet("assets/images/SpriteSheets/roof.png");
                for (int i = 0; i < 1; i++) {



                    Sprite sprite = roof.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 2.25f, .625f);
                        //TODO REMOVE
                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(2.25f, .625f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);


                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }




                ImGui.endTabItem();
            }

            if(ImGui.beginTabItem("Decoration Blocks"))
            {
                ImVec2 windowPos = new ImVec2();
                ImGui.getWindowPos(windowPos);
                ImVec2 windowSize = new ImVec2();
                ImGui.getWindowSize(windowSize);
                ImVec2 itemSpacing = new ImVec2();
                ImGui.getStyle().getItemSpacing(itemSpacing);

                Spritesheet bricks  = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks.png");
                float windowX2 = windowPos.x + windowSize.x;
                for (int i  = 0; i < 38; i++) {





                    Sprite sprite = bricks.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.25f, 0.25f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet bricks2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks2.png");
                for (int i  = 0; i < 6; i++) {





                    Sprite sprite = bricks2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.5f, 0.5f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet bricks3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks3.png");
                for (int i  = 0; i < 5; i++) {

                    Sprite sprite = bricks3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.5f, 0.25f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet bricks4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/bricks4.png");
                for (int i  = 0; i < 5; i++) {

                    Sprite sprite = bricks4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.25f, 0.25f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet pillars = AssetPool.getSpritesheet("assets/images/SpriteSheets/pillars.png");
                for (int i = 0; i < 6; i++) {



                    Sprite sprite = pillars.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, 1f);
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);


                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet pillars2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/pillars2.png");
                for (int i = 0; i < 2; i++) {



                    Sprite sprite = pillars2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .5f);
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);


                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet pillars3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/pillars3.png");
                for (int i  = 0; i < 1; i++) {





                    Sprite sprite = pillars3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.4375f, 0.1875f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet pillars4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/pillars4.png");
                for (int i  = 0; i < 1; i++) {





                    Sprite sprite = pillars4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();

                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.4375f, 0.25f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }


                ImGui.endTabItem();
            }
            if(ImGui.beginTabItem("dark")) {
                ImVec2 windowPos = new ImVec2();
                ImGui.getWindowPos(windowPos);
                ImVec2 windowSize = new ImVec2();
                ImGui.getWindowSize(windowSize);
                ImVec2 itemSpacing = new ImVec2();
                ImGui.getStyle().getItemSpacing(itemSpacing);
                float windowX2 = windowPos.x + windowSize.x;

                Spritesheet twilight = AssetPool.getSpritesheet("assets/images/SpriteSheets/dark.png");
                for (int i = 0; i < 23; i++) {


                    Sprite sprite = twilight.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, 0.5f);
                        //TODO REMOVE
                        if(i == 0 || i == 1 || i == 2 || i == 4 || i == 8 || i ==10 || i ==12 || i == 16 || i ==17 || i == 18 || i == 20)
                        {
                            object.transform.zIndex = 1;
                            RigidBody2D rb = new RigidBody2D();
                            rb.setBodyType(BodyType.Static);
                            object.addComponent(rb);
                            Box2DCollider b2d = new Box2DCollider();
                            b2d.setHalfSize(new Vector2f(.5f, .5f));
                            object.addComponent(b2d);
                            object.addComponent(new Ground());
                        }
                        else if(i == 11 || i ==19)
                        {
                            object.addComponent(new BackgroundTile());
                        }

                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }


                ImGui.endTabItem();
            }
            if(ImGui.beginTabItem("twilight")) {
                ImVec2 windowPos = new ImVec2();
                ImGui.getWindowPos(windowPos);
                ImVec2 windowSize = new ImVec2();
                ImGui.getWindowSize(windowSize);
                ImVec2 itemSpacing = new ImVec2();
                ImGui.getStyle().getItemSpacing(itemSpacing);
                float windowX2 = windowPos.x + windowSize.x;

                Spritesheet dark = AssetPool.getSpritesheet("assets/images/SpriteSheets/twilight.png");
                for (int i = 0; i < 78; i++) {


                    Sprite sprite = dark.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, 0.25f);
                        //TODO REMOVE
                        if(i == 12 || i == 16 || i == 19 || i== 23 || i == 45 || i == 49 || i == 50 || i == 51 || i == 52 || i ==53 ||i ==54 || i==62 || i==63 || i ==64 || i == 65 || i == 74 || i  == 75)
                        {
                            object.addComponent(new BackgroundTile());
                        }
                        else
                        {
                            object.transform.zIndex = 1;
                            RigidBody2D rb = new RigidBody2D();
                            rb.setBodyType(BodyType.Static);
                            object.addComponent(rb);
                            Box2DCollider b2d = new Box2DCollider();
                            b2d.setHalfSize(new Vector2f(.25f, .25f));
                            object.addComponent(b2d);
                            object.addComponent(new Ground());
                        }

                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet  twilightPillar = AssetPool.getSpritesheet("assets/images/SpriteSheets/twilightPillar.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = twilightPillar.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 1f, 1.75f);

                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet  twilight2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/twilight2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = twilight2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .71875f, .1875f);

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.71875f, .1875f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet  twilight3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/twilight3.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = twilight3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .6875f, .171875f);

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.71875f, .171875f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet  twilight4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/twilight4.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = twilight4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .25f);

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.25f, .25f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet  twilight5 = AssetPool.getSpritesheet("assets/images/SpriteSheets/twilight5.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = twilight5.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .75f, .328125f);

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.75f, .328125f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet  twilightSpikes = AssetPool.getSpritesheet("assets/images/SpriteSheets/twilightSpikes.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = twilightSpikes.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .75f, .25f);

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.75f, .25f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet  twilightSpikes2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/twilightSpikes2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = twilightSpikes2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .25f);

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.25f, .25f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }


                ImGui.endTabItem();
            }
            if(ImGui.beginTabItem("church")) {
                ImVec2 windowPos = new ImVec2();
                ImGui.getWindowPos(windowPos);
                ImVec2 windowSize = new ImVec2();
                ImGui.getWindowSize(windowSize);
                ImVec2 itemSpacing = new ImVec2();
                ImGui.getStyle().getItemSpacing(itemSpacing);
                float windowX2 = windowPos.x + windowSize.x;


                Spritesheet churchBrick = AssetPool.getSpritesheet("assets/images/SpriteSheets/churchBrick.png");
                for (int i = 0; i < 3; i++) {


                    Sprite sprite = churchBrick.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, 0.5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .5f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet churchBrick2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/churchBrick2.png");
                for (int i = 0; i < 5; i++) {


                    Sprite sprite = churchBrick2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, 0.25f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.25f, .25f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet churchDistance = AssetPool.getSpritesheet("assets/images/SpriteSheets/churchDistance.png");
                for (int i = 0; i < 3; i++) {


                    Sprite sprite = churchDistance.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, 1.0f);
                        //TODO REMOVE



                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet churchFloor = AssetPool.getSpritesheet("assets/images/SpriteSheets/churchFloor.png");
                for (int i = 0; i < 3; i++) {


                    Sprite sprite = churchFloor.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .75f, 0.640625f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.75f, .4f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet churchfloor2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/churchFloor2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = churchfloor2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, 1.078125f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .9f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet churchfloor3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/churchFloor3.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = churchfloor3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .5f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet churchfloor4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/churchFloor4.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = churchfloor4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .5f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet churchRailing = AssetPool.getSpritesheet("assets/images/SpriteSheets/churchRailing.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = churchRailing.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 1.25f, .8215f);
                        //TODO REMOVE



                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet churchStairs = AssetPool.getSpritesheet("assets/images/SpriteSheets/churchStairs.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = churchStairs.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        object.addComponent(new Ground());


                        if( i == 0 ) {
                            StairCollider b2d = new StairCollider();
                            b2d.setHalfSize(new Vector2f(.5f, .5f));

                            b2d.setFacingRight(false);
                            object.addComponent(b2d);
                        }
                        else if( i == 1)
                        {
                            StairCollider b2d = new StairCollider();
                            b2d.setHalfSize(new Vector2f(.5f, .5f));
                            b2d.setFacingRight(true);
                            object.addComponent(b2d);

                        }




                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }


                ImGui.endTabItem();
            }

            if(ImGui.beginTabItem("rocks")) {
                ImVec2 windowPos = new ImVec2();
                ImGui.getWindowPos(windowPos);
                ImVec2 windowSize = new ImVec2();
                ImGui.getWindowSize(windowSize);
                ImVec2 itemSpacing = new ImVec2();
                ImGui.getStyle().getItemSpacing(itemSpacing);
                float windowX2 = windowPos.x + windowSize.x;


                Spritesheet rockCrystal = AssetPool.getSpritesheet("assets/images/SpriteSheets/rockCrystal.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = rockCrystal.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.390625f, 0.390625f);
                        //TODO REMOVE




                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet rockCrystal2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/rockCrystal2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = rockCrystal2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 0.40625f, 0.3125f);
                        //TODO REMOVE




                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet  rockPlant = AssetPool.getSpritesheet("assets/images/SpriteSheets/rockPlant.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = rockPlant.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .375f);
                        //TODO REMOVE



                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet rockPlant2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/rockPlant2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = rockPlant2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .546875f, .40625f);
                        //TODO REMOVE



                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet rocks = AssetPool.getSpritesheet("assets/images/SpriteSheets/rocks.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = rocks.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .296875f, 1.5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.23f, 1.45f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet rocks2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/rocks2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = rocks2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, 1f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, 1f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet rock3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/rocks3.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = rock3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .515625f, 1f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        b2d.setHalfSize(new Vector2f(.515625f, 1f));
                        if( i == 0)
                        {
                            b2d.setFacingRight(false);
                        }
                        else if( i == 1)
                        {
                            b2d.setFacingRight(true);
                        }
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet rocks4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/rocks4.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = rocks4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .25f);
                        //TODO REMOVE



                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }






                ImGui.endTabItem();
            }




            if(ImGui.beginTabItem("cemetery")) {
                ImVec2 windowPos = new ImVec2();
                ImGui.getWindowPos(windowPos);
                ImVec2 windowSize = new ImVec2();
                ImGui.getWindowSize(windowSize);
                ImVec2 itemSpacing = new ImVec2();
                ImGui.getStyle().getItemSpacing(itemSpacing);
                float windowX2 = windowPos.x + windowSize.x;

                Spritesheet cemetery = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cemetery.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite ,.5f, .75f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        if(i == 0)
                        {
                            b2d.setFacingRight(false);
                        }else
                        {
                            b2d.setFacingRight(true);
                        }

                        b2d.setHalfSize(new Vector2f(.5f, .8f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemetery2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemetery2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 1f, .640625f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(1f, .33f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet cemetery3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery3.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemetery3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .46875f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .2f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemetery4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery4.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemetery4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, 1.5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .5f));
                        b2d.setOffset(new Vector2f(0,-.58f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemetery5 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery5.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemetery5.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .375f, .5f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemetery6 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery6.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cemetery6.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        if(i == 0)
                        {
                            b2d.setFacingRight(false);
                        }
                        else
                        {
                            b2d.setFacingRight(true);
                        }
                        b2d.setHalfSize(new Vector2f(.5f, .5f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemetery7 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery7.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemetery7.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .75f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .5f));
                        b2d.setOffset(new Vector2f(0,-.15f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }


                Spritesheet cemetery8 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery8.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cemetery8.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .75f, .640625f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();

                        if(i == 0)
                        {
                            b2d.setFacingRight(false);
                        }else
                        {
                            b2d.setFacingRight(true);
                        }
                        b2d.setHalfSize(new Vector2f(.75f, .35f));
                        b2d.setOffset(new Vector2f(0,-.07f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemetery9 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery9.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemetery9.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .60625f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.25f, .48f));
                        b2d.setOffset(new Vector2f(0,-.06f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemetery10 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery10.png");
                for (int i = 0; i < 4; i++) {


                    Sprite sprite = cemetery10.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .4f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemetery11 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery11.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cemetery11.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .15625f, .25f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet cemetery12 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemetery12.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemetery12.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .5f);
                        //TODO REMOVE

                       object.addComponent(new BackgroundTile());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemeteryLargeBush = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeteryLargeBush.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeteryLargeBush.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 1.1875f, 1.015625f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemeterySmallBush = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeterySmallBush.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeterySmallBush.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .53125f, .453125f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemeteryStatue = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeteryStatue.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeteryStatue.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .984375f, 1.171875f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemeteryStone = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeteryStone.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeteryStone.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .421875f, .609375f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet cemeteryStone2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeteryStone2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeteryStone2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .421875f, .625f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet cemeteryStone3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeteryStone3.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeteryStone3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .421875f, .515625f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet cemeteryStone4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeteryStone4.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeteryStone4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .296875f, .59375f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemeteryTree = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeteryTree.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeteryTree.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 2.59375f, 1.828125f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet cemeteryTree2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeteryTree2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeteryTree2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 2.59375f, 1.828125f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cemeteryTree3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cemeteryTree3.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cemeteryTree3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 2.75f, 2.671875f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }


                ImGui.endTabItem();
            }


            if(ImGui.beginTabItem("castle1")) {
                ImVec2 windowPos = new ImVec2();
                ImGui.getWindowPos(windowPos);
                ImVec2 windowSize = new ImVec2();
                ImGui.getWindowSize(windowSize);
                ImVec2 itemSpacing = new ImVec2();
                ImGui.getStyle().getItemSpacing(itemSpacing);
                float windowX2 = windowPos.x + windowSize.x;


                Spritesheet castleBricks = AssetPool.getSpritesheet("assets/images/SpriteSheets/castleBricks.png");
                for (int i = 0; i < 8; i++) {


                    Sprite sprite = castleBricks.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .48f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet castleBricks2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/castleBricks2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = castleBricks2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .25f);
                        //TODO REMOVE




                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet castleBricks3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/castleBricks3.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = castleBricks3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, 1f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.25f, .98f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet castleBricks4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/castleBricks4.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = castleBricks4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .48f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet castleBricks5 = AssetPool.getSpritesheet("assets/images/SpriteSheets/castleBricks5.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = castleBricks5.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .48f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet castleStairs = AssetPool.getSpritesheet("assets/images/SpriteSheets/castleStairs.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = castleStairs.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .485375f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        if(i == 0)
                        {
                            b2d.setFacingRight(true);
                        }
                        else
                        {
                            b2d.setFacingRight(false);
                        }
                        b2d.setHalfSize(new Vector2f(.25f, .47f));
                        b2d.setOffset(new Vector2f(0,.1f));


                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet castleStairs2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/castleStairs2.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = castleStairs2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .75f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        if(i == 0)
                        {
                            b2d.setFacingRight(false);
                        }
                        else
                        {
                            b2d.setFacingRight(true);
                        }
                        b2d.setHalfSize(new Vector2f(.75f, .46f));



                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet castleStairs3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/castleStairs3.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = castleStairs3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .53125f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        if(i == 0)
                        {
                            b2d.setFacingRight(false);
                        }
                        else
                        {
                            b2d.setFacingRight(true);
                        }
                        b2d.setHalfSize(new Vector2f(.53125f, .5f));



                        object.addComponent(b2d);
                        object.addComponent(new Ground());


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }




                ImGui.endTabItem();
            }


            if(ImGui.beginTabItem("castle2")) {
                ImVec2 windowPos = new ImVec2();
                ImGui.getWindowPos(windowPos);
                ImVec2 windowSize = new ImVec2();
                ImGui.getWindowSize(windowSize);
                ImVec2 itemSpacing = new ImVec2();
                ImGui.getStyle().getItemSpacing(itemSpacing);
                float windowX2 = windowPos.x + windowSize.x;

                Spritesheet cBrick = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cBrick.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .25f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.25f, .23f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick2.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cBrick2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 1.5f, .75f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick3.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cBrick3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .75f, .38125f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.75f, .3f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick4 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick4.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cBrick4.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .34375f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.25f, .29f));
                        b2d.setOffset(new Vector2f(0,-.03f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick5 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick5.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cBrick5.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .59375f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .51f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick6 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick6.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cBrick6.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .25f);
                        //TODO REMOVE
                        object.addComponent(new BackgroundTile());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick7 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick7.png");
                for (int i = 0; i < 3; i++) {


                    Sprite sprite = cBrick7.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, 1.125f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, 1.12f));
                        b2d.setOffset(new Vector2f(0,-.1f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick8 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick8.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cBrick8.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .59375f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .51f));
                        b2d.setFacingRight(false);
                        b2d.setOffset(new Vector2f(0,.2f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick9 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick9.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cBrick9.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .34375f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .29f));
                        b2d.setOffset(new Vector2f(0,-.04f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick10 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick10.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cBrick10.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .46875f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .37f));
                        b2d.setOffset(new Vector2f(0,-.14f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick11 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick11.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cBrick11.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, 2.5f, 1.265625f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(2.5f, 1.2f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }
                Spritesheet cBrick12 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick12.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cBrick12.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .40625f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.40625f, .49f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick13 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick13.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cBrick13.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .5f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        Box2DCollider b2d = new Box2DCollider();
                        b2d.setHalfSize(new Vector2f(.5f, .47f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cBrick14 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cBrick14.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cBrick14.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, 1f);
                        //TODO REMOVE

                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cDoor = AssetPool.getSpritesheet("assets/images/SpriteSheets/cDoor.png");
                for (int i = 0; i < 1; i++) {


                    Sprite sprite = cDoor.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .75f, 1f);
                        //TODO REMOVE


                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cStairs = AssetPool.getSpritesheet("assets/images/SpriteSheets/cStairs.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cStairs.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .25f, .390625f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        if(i == 0)
                        {
                            b2d.setFacingRight(false);
                        }
                        else if( i ==1)
                        {
                            b2d.setFacingRight(true);
                        }
                        b2d.setHalfSize(new Vector2f(.25f, .39f));
                        b2d.setOffset(new Vector2f(0, .03f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cStairs2 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cStairs2.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cStairs2.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, .640625f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        if(i == 0)
                        {
                            b2d.setFacingRight(false);
                        }
                        else if( i ==1)
                        {
                            b2d.setFacingRight(true);
                        }
                        b2d.setHalfSize(new Vector2f(.5f, .625f));
                        b2d.setOffset(new Vector2f(0,-.03f));

                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }

                Spritesheet cStairs3 = AssetPool.getSpritesheet("assets/images/SpriteSheets/cStairs3.png");
                for (int i = 0; i < 2; i++) {


                    Sprite sprite = cStairs3.getSprite(i);
                    float spriteWidth = sprite.getWidth() * 4;
                    float spriteHeight = sprite.getHeight() * 4;
                    int id = sprite.getTexId();
                    Vector2f[] texCoords = sprite.getTexCoords();


                    ImGui.pushID(i);
                    if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                        GameObject object = Prefabs.generateSpriteObject(sprite, .5f, 1f);
                        //TODO REMOVE

                        object.transform.zIndex = 1;
                        RigidBody2D rb = new RigidBody2D();
                        rb.setBodyType(BodyType.Static);
                        object.addComponent(rb);
                        StairCollider b2d = new StairCollider();
                        if(i == 0)
                        {
                            b2d.setFacingRight(false);
                        }
                        else if( i ==1)
                        {
                            b2d.setFacingRight(true);
                        }
                        b2d.setHalfSize(new Vector2f(.5f, .98f));
                        b2d.setOffset(new Vector2f(0, .273f));
                        object.addComponent(b2d);
                        object.addComponent(new Ground());
                        levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                    }
                    ImGui.popID();

                    ImVec2 lastButtonPos = new ImVec2();
                    ImGui.getItemRectMax(lastButtonPos);
                    float lastButtonX2 = lastButtonPos.x;
                    float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
                    if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                        ImGui.sameLine();
                    }
                }



                ImGui.endTabItem();
            }


            if(ImGui.beginTabItem("Prefabs"))
            {
                int uID  =0;
                Spritesheet playerSprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");
                Sprite sprite = playerSprites.getSprite(0);
                float spriteWidth = sprite.getWidth() * 4;
                float spriteHeight = sprite.getHeight() * 4;
                int id = sprite.getTexId();
                Vector2f[] texCoords = sprite.getTexCoords();

                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateMario();
                    object.transform.zIndex= 2;
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();

                Spritesheet items = AssetPool.getSpritesheet("assets/images/SpriteSheets/items.png");
                 sprite = items.getSprite(0);

                id = sprite.getTexId();
                 texCoords = sprite.getTexCoords();
                 ImGui.pushID(uID++);


                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateQuestionBlock();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();

                sprite = playerSprites.getSprite(14);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateGoomba();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();

                //turtles
                Spritesheet turtle = AssetPool.getSpritesheet("assets/images/SpriteSheets/turtle.png");
                sprite = turtle.getSprite(0);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateTurtle();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();
                //win pole top

                sprite  = items.getSprite(6);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateFlagtop();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();
                // win pole
                sprite  = items.getSprite(33);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateFlagPole();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();

                //start button

                sprite  = AssetPool.getSpritesheet("assets/images/start.png").getSprite(0);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateStartButton();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();

                //new save button

                sprite  = AssetPool.getSpritesheet("assets/images/newSave.png").getSprite(0);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateNewSaveButton();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();

                //Health system
                sprite  = AssetPool.getSpritesheet("assets/images/SpriteSheets/empty.png").getSprite(0);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateEmptyHealthBar();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();


                sprite  = AssetPool.getSpritesheet("assets/images/SpriteSheets/health.png").getSprite(0);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateHealthBar();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();




                //attack bug

                sprite  = AssetPool.getSpritesheet("assets/images/SpriteSheets/bug.png").getSprite(0);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateAttackBug();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();


                // skelly
                sprite  = AssetPool.getSpritesheet("assets/images/SpriteSheets/Skelaton.png").getSprite(0);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generateSkelly();
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();




                //Pipes

                Spritesheet pipes = AssetPool.getSpritesheet("assets/images/SpriteSheets/pipes.png");
                sprite = pipes.getSprite(0);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generatePipe(Direction.Down);
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();

                sprite = pipes.getSprite(1);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generatePipe(Direction.Up);
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();

                sprite = pipes.getSprite(2);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generatePipe(Direction.Right);
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();
                ImGui.sameLine();

                sprite = pipes.getSprite(3);
                id = sprite.getTexId();
                texCoords = sprite.getTexCoords();
                ImGui.pushID(uID++);
                if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                    GameObject object = Prefabs.generatePipe(Direction.Left);
                    levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
                }
                ImGui.popID();


                ImGui.endTabItem();
            }


            if(ImGui.beginTabItem("Sounds"))
            {
                Collection<Sound> sounds = AssetPool.getAllSounds();
                for(Sound sound : sounds)
                {
                    File tmp = new File(sound.getFilepath());
                    if(ImGui.button(tmp.getName()))
                    {
                        if(!sound.isPlaying())
                        {
                            System.out.println("hit");
                            sound.play();
                        }
                        else
                        {
                            sound.stop();
                        }
                    }
                    if(ImGui.getContentRegionAvailX() > 100)
                    {
                        ImGui.sameLine();
                    }
                }
                ImGui.endTabItem();
            }
            ImGui.endTabBar();
        }

        ImGui.end();
    }
}