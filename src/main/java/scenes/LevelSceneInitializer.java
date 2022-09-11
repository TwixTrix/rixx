package scenes;

import Rixx.*;
import components.*;


import renderer.DebugDraw;
import util.AssetPool;



public class LevelSceneInitializer extends SceneInitializer {




    public LevelSceneInitializer() {

    }

    @Override
    public void init(Scene scene) {


        Spritesheet sprites = AssetPool.getSpritesheet("assets/images/SpriteSheets/decorationsAndBlocks.png");



        GameObject cameraObject = scene.createGameObject("GameCamera");
        cameraObject.addComponent(new GameCamera(scene.camera()));
        cameraObject.start();
        scene.addGameObjectToScene(cameraObject);






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
        AssetPool.addSpritesheet("assets/images/SpriteSheets/start.png" , new Spritesheet(AssetPool.getTexture("assets/images/start.png"), 100,100,1,0));
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
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bricks6.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bricks6.png"),
                        8, 8, 6, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/bridge.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/bridge.png"),
                        112, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/SpriteSheets/pillars.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/pillars.png"),
                        16, 64, 6, 0));
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
                        32, 34, 1, 0));
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


        //town
        AssetPool.addSpritesheet("assets/images/town/barrel.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/barrel.png"),
                        24, 30, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/crate.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/crate.png"),
                        39, 35, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/crate-stack.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/crate-stack.png"),
                        73, 68, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/ground.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/ground.png"),
                        16, 48, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/ground-b.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/ground-b.png"),
                        16, 48, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/ground-corner.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/ground-corner.png"),
                        48, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/ground-right-corner.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/ground-right-corner.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/ground-shadow.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/ground-shadow.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/ground-wall.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/ground-wall.png"),
                        32, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/ground-wall-b.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/ground-wall-b.png"),
                        48, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/ground-wood-legs.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/ground-wood-legs.png"),
                        32, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/house-a.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/house-a.png"),
                        168, 183, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/house-b.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/house-b.png"),
                        210, 224, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/house-c.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/house-c.png"),
                        221, 183, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/left-corner.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/left-corner.png"),
                        32, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/left-roof.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/left-roof.png"),
                        64, 80, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/left-slope.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/left-slope.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/right-corner.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/right-corner.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/right-roof.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/right-roof.png"),
                        32, 80, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/right-slope.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/right-slope.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/roof.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/roof.png"),
                        16, 80, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/shadow.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/shadow.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/sign.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/sign.png"),
                        37, 45, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/slope.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/slope.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/stairs.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/stairs.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/stairs-left.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/stairs-left.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/stairs-mirror.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/stairs-mirror.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/stairs-mirror-left.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/stairs-mirror-left.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/stairs-mirror-right.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/stairs-mirror-right.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/stairs-right.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/stairs-right.png"),
                        16, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/street-lamp.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/street-lamp.png"),
                        35, 108, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/top-left-wood.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/top-left-wood.png"),
                        32, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/top-right-wood.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/top-right-wood.png"),
                        32, 32, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/top-wood.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/top-wood.png"),
                        16, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/wagon.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/wagon.png"),
                        93, 75, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/wall.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/wall.png"),
                        16, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/wall-b.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/wall-b.png"),
                        16, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/well.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/well.png"),
                        65, 65, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/window.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/window.png"),
                        32, 64, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/window-bars.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/window-bars.png"),
                        32, 64, 1, 0));
        AssetPool.addSpritesheet("assets/images/town/wood-legs.png",
                new Spritesheet(AssetPool.getTexture("assets/images/town/wood-legs.png"),
                        32, 16, 1, 0));
        AssetPool.addSpritesheet("assets/images/townMobs/bearded-idle.png",
                new Spritesheet(AssetPool.getTexture("assets/images/townMobs/bearded-idle.png"),
                        40, 47, 5, 0));
        AssetPool.addSpritesheet("assets/images/townMobs/bearded-walk.png",
                new Spritesheet(AssetPool.getTexture("assets/images/townMobs/bearded-walk.png"),
                        40, 47, 6, 0));
        AssetPool.addSpritesheet("assets/images/townMobs/hat-man-idle.png",
                new Spritesheet(AssetPool.getTexture("assets/images/townMobs/hat-man-idle.png"),
                        39, 52, 4, 0));
        AssetPool.addSpritesheet("assets/images/townMobs/hat-man-walk.png",
                new Spritesheet(AssetPool.getTexture("assets/images/townMobs/hat-man-walk.png"),
                        39, 52, 6, 0));
        AssetPool.addSpritesheet("assets/images/townMobs/oldman-idle.png",
                new Spritesheet(AssetPool.getTexture("assets/images/townMobs/oldman-idle.png"),
                        34, 42, 8, 0));
        AssetPool.addSpritesheet("assets/images/townMobs/oldman-walk.png",
                new Spritesheet(AssetPool.getTexture("assets/images/townMobs/oldman-walk.png"),
                        34, 42, 12, 0));
        AssetPool.addSpritesheet("assets/images/townMobs/woman-idle.png",
                new Spritesheet(AssetPool.getTexture("assets/images/townMobs/woman-idle.png"),
                        37, 46, 7, 0));
        AssetPool.addSpritesheet("assets/images/townMobs/woman-walk.png",
                new Spritesheet(AssetPool.getTexture("assets/images/townMobs/woman-walk.png"),
                        37, 46, 6, 0));





        //Red moon tower
        AssetPool.addSpritesheet("assets/images/redMoonTower.png",
                new Spritesheet(AssetPool.getTexture("assets/images/redMoonTower.png"),
                        100, 140, 11, 0));

        //Skelly
        AssetPool.addSpritesheet("assets/images/SpriteSheets/Skelaton.png",
                new Spritesheet(AssetPool.getTexture("assets/images/SpriteSheets/Skelaton.png"),
                        64, 64, 65, 0));
        //EyeFly
        AssetPool.addSpritesheet("assets/images/mobs/fly/Attack.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/fly/Attack.png"),
                        150, 150, 8, 0));
        AssetPool.addSpritesheet("assets/images/mobs/fly/Death.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/fly/Death.png"),
                        150, 150, 4, 0));
        AssetPool.addSpritesheet("assets/images/mobs/fly/Flight.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/fly/Flight.png"),
                        150, 150, 8, 0));
        AssetPool.addSpritesheet("assets/images/mobs/fly/Hit.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/fly/Hit.png"),
                        150, 150, 4, 0));

        //skelly2
        AssetPool.addSpritesheet("assets/images/mobs/skelly/Attack.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/skelly/Attack.png"),
                        150, 150, 8, 0));
        AssetPool.addSpritesheet("assets/images/mobs/skelly/Death.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/skelly/Death.png"),
                        150, 150, 4, 0));
        AssetPool.addSpritesheet("assets/images/mobs/skelly/Hit.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/skelly/Hit.png"),
                        150, 150, 4, 0));
        AssetPool.addSpritesheet("assets/images/mobs/skelly/Shield.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/skelly/Shield.png"),
                        150, 150, 4, 0));
        AssetPool.addSpritesheet("assets/images/mobs/skelly/Walk.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/skelly/Walk.png"),
                        150, 150, 4, 0));
        AssetPool.addSpritesheet("assets/images/mobs/skelly/Idle.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/skelly/Idle.png"),
                        150, 150, 4, 0));

        //fire worm
        AssetPool.addSpritesheet("assets/images/mobs/worm/Attack.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/worm/Attack.png"),
                        90, 90, 16, 0));
        AssetPool.addSpritesheet("assets/images/mobs/worm/Death.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/worm/Death.png"),
                        90, 90, 8, 0));
        AssetPool.addSpritesheet("assets/images/mobs/worm/Hit.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/worm/Hit.png"),
                        90, 90, 3, 0));
        AssetPool.addSpritesheet("assets/images/mobs/worm/idle.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/worm/idle.png"),
                        90, 90, 9, 0));
        AssetPool.addSpritesheet("assets/images/mobs/worm/walk.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/worm/walk.png"),
                        90, 90, 9, 0));

        //fireball worm
        AssetPool.addSpritesheet("assets/images/mobs/worm/Move.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/worm/Move.png"),
                        46, 46, 6, 0));
        AssetPool.addSpritesheet("assets/images/mobs/worm/Explosion.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/worm/Explosion.png"),
                        46, 46, 7, 0));

        //gluttony
        AssetPool.addSpritesheet("assets/images/mobs/gluttony.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mobs/gluttony.png"),
                        140, 93, 64, 0));
        AssetPool.addSpritesheet("assets/images/healthBars/gluttonyEmptyBar.png",
                new Spritesheet(AssetPool.getTexture("assets/images/healthBars/gluttonyEmptyBar.png"),
                        200, 100, 1, 0));
        AssetPool.addSpritesheet("assets/images/healthBars/gluttonyHealth.png",
                new Spritesheet(AssetPool.getTexture("assets/images/healthBars/gluttonyHealth.png"),
                        200, 100, 1, 0));

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


        //mobs
        AssetPool.addSound("assets/sounds/mobs/bite-small.ogg" , false);
        AssetPool.addSound("assets/sounds/mobs/swing.ogg" , false);

        //player
        AssetPool.addSpritesheet("assets/images/player/Attack1.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/Attack1.png"),
                        162, 162, 7, 0));
        AssetPool.addSpritesheet("assets/images/player/Attack2.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/Attack2.png"),
                        162, 162, 7, 0));
        AssetPool.addSpritesheet("assets/images/player/Attack3.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/Attack3.png"),
                        162, 162, 8, 0));
        AssetPool.addSpritesheet("assets/images/player/Death.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/Death.png"),
                        162, 162, 7, 0));
        AssetPool.addSpritesheet("assets/images/player/Fall.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/Fall.png"),
                        162, 162, 3, 0));
        AssetPool.addSpritesheet("assets/images/player/hit.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/hit.png"),
                        162, 162, 3, 0));
        AssetPool.addSpritesheet("assets/images/player/Idle.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/Idle.png"),
                        162, 162, 10, 0));
        AssetPool.addSpritesheet("assets/images/player/Fall.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/Fall.png"),
                        128, 128, 3, 0));
        AssetPool.addSpritesheet("assets/images/player/Jump.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/Jump.png"),
                        162, 162, 3, 0));
        AssetPool.addSpritesheet("assets/images/player/Run.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/Run.png"),
                        162, 162, 8, 0));
        AssetPool.addSpritesheet("assets/images/player/shield.png",
                new Spritesheet(AssetPool.getTexture("assets/images/player/shield.png"),
                        128, 128, 4, 0));


        AssetPool.addSound("assets/sounds/player/hit.ogg",false);



        //town music
        AssetPool.addSound("assets/sounds/town/rpg_village02__loop.ogg", true);

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
    public void imgui()
    {

    }






}