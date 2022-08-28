package components;


import Rixx.KeyListener;
import Rixx.MouseListener;
import Rixx.Window;
import Rixx.sceneMusic;
import org.joml.Vector2f;
import scenes.LevelSceneInitializer;
import util.AssetPool;

import java.io.FileWriter;
import java.io.IOException;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class NewSaveButton extends Component{

    private Vector2f position;
    private float width;
    private float height;


    public NewSaveButton( float width , float height)
    {

        this.width = width;
        this.height = height;

    }


    @Override
    public void update(float dt)
    {
        this.position = gameObject.transform.position;
        float mouseX = MouseListener.getWorldX();
        float mouseY = MouseListener.getWorldY();
        if(MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_LEFT) && mouseX <= width/2 + position.x && mouseX >= position.x - width/2  && mouseY <= position.y + height/1 && mouseY >= position.y - height/1)
        {
            Window.newSave();
            Window.changeScene(new LevelSceneInitializer(), Window.LevelLoad() );
            sceneMusic.playMusic("assets/sounds/main-theme-overworld.ogg");
        }

    }








}
