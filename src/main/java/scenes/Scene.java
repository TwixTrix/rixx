package scenes;


import Rixx.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import components.*;
import imgui.ImGui;
import org.joml.Vector2f;
import physics2D.Physics2D;
import renderer.Renderer;
import util.AssetPool;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.lwjgl.glfw.GLFW.*;

public class Scene {

    private Renderer renderer ;
    private Camera camera;
    private boolean isRunning ;
    private List<GameObject> gameObjects;
    private List<GameObject> pendingObjects;
    private Physics2D physics2D;
    public String fileName;
    public boolean paused = false;

    private transient GameObject pauseMenu;


    private SceneInitializer sceneInitializer;

    public Scene(SceneInitializer sceneInitializer, String fileName)
    {
        this.sceneInitializer = sceneInitializer;
        this.physics2D = new Physics2D();
        this.renderer = new Renderer();
        this.gameObjects = new ArrayList<>();
        this.pendingObjects = new ArrayList<>();
        this.isRunning = false;
        this.fileName = fileName;
    }
    public Scene(SceneInitializer sceneInitializer)
    {
        this.sceneInitializer = sceneInitializer;
        this.physics2D = new Physics2D();
        this.renderer = new Renderer();
        this.gameObjects = new ArrayList<>();
        this.pendingObjects = new ArrayList<>();
        this.isRunning = false;
        this.fileName = "menu.txt";
    }

    public Physics2D getPhysics2D() {
        return physics2D;
    }

    public void init(){
            this.camera = new Camera(new Vector2f(0, 0));
            this.sceneInitializer.loadResources(this);
            this.sceneInitializer.init(this);




    }

    public void start()
    {
       for(int i = 0; i <gameObjects.size(); i++ )
       {
           GameObject go = gameObjects.get(i);
        go.start();
        this.renderer.add(go);
        this.physics2D.add(go);
       }
       isRunning = true;


    }

    public void addGameObjectToScene(GameObject go)
    {
        if(!isRunning)
        {
            gameObjects.add(go);
        }
        else
        {
            pendingObjects.add(go);

        }
    }

    public void destroy()
    {
        for(GameObject go: gameObjects)
        {
            go.destroy();
        }
    }

    public <T extends Component> GameObject getGameObjectWith(Class<T> clazz)
    {
        for(GameObject go : gameObjects)
        {
            if(go.getComponent(clazz) != null)
            {
                return go;
            }
        }
        return null;
    }

    public List<GameObject> getGameObjects()
    {
        return this.gameObjects;
    }

    public GameObject getGameObject(int gameObjectID)
    {
        Optional<GameObject> result = this.gameObjects.stream().filter(gameObject -> gameObject.getUid() ==gameObjectID).findFirst();
        return result.orElse(null);
    }

    public GameObject getGameObject(String gameObjectName)
    {
        Optional<GameObject> result = this.gameObjects.stream().filter(gameObject -> gameObject.name.equals(gameObjectName)).findFirst();
        return result.orElse(null);
    }

    public void editorUpdate(float dt)
    {
        if(KeyListener.isKeyPressed(GLFW_KEY_F12))
        {
            delete();
        }

        this.camera.adjustProjection();

        for (int i = 0; i < gameObjects.size(); i++ ) {
            GameObject go = gameObjects.get(i);
            go.editorUpdate(dt);

            if(go.isDead())
            {
                gameObjects.remove(i);
                this.renderer.destroyGameObject(go);
                this.physics2D.destroyGameObject(go);
                i--;

            }
        }

        for(GameObject go : pendingObjects)
        {
            gameObjects.add(go);
            go.start();
            this.renderer.add(go);
            this.physics2D.add(go);
        }
        pendingObjects.clear();
    }

    public void update(float dt)
    {
        if(KeyListener.keyBeginPress(GLFW_KEY_ESCAPE) && !paused && this.fileName != "menu.txt"){
            paused = true;
            pauseMenu = Prefabs.generateSpriteObject(AssetPool.getSpritesheet("assets/images/SpriteSheets/start.png").getSprite(0), 1,1);
            pauseMenu.transform.zIndex = 1000;
            this.renderer.add(pauseMenu);
            pauseMenu.transform.position.set(camera.position.x + 3, camera.position.y + 1.5);
            pauseMenu.getComponent(SpriteRenderer.class).start();
            return;

        }



        if(paused )
        {
            //TODO: fix pause button box, use sprite half width instead of .5
            if( KeyListener.keyBeginPress(GLFW_KEY_ESCAPE) || ( MouseListener.mouseButtonDown(GLFW_MOUSE_BUTTON_LEFT) && MouseListener.getWorldX() <= .5+ pauseMenu.transform.position.x && MouseListener.getWorldX() >= pauseMenu.transform.position.x - .5 && MouseListener.getWorldY() <= pauseMenu.transform.position.y + .5 && MouseListener.getWorldY() >= pauseMenu.transform.position.y - .5))
            {
                paused = false;
                pauseMenu.destroy();
                this.renderer.destroyGameObject(pauseMenu);

            }
            pauseMenu.update(dt);
            return;
        }

        this.camera.adjustProjection();
        this.physics2D.update(dt);

        for (int i = 0; i < gameObjects.size(); i++ ) {
            GameObject go = gameObjects.get(i);
            go.update(dt);

            if(go.isDead())
            {
                gameObjects.remove(i);
                this.renderer.destroyGameObject(go);
                this.physics2D.destroyGameObject(go);
                i--;

            }
        }
        for(GameObject go : pendingObjects)
        {
            gameObjects.add(go);
            go.start();
            this.renderer.add(go);
            this.physics2D.add(go);
        }
        pendingObjects.clear();
    }
    public  void render()
    {
        this.renderer.render();
    }

    public Camera camera()
    {
        return this.camera;
    }



    public void imgui()
    {
        this.sceneInitializer.imgui();
    }
    public GameObject createGameObject(String name)
    {
        GameObject go = new GameObject(name);
        go.addComponent(new Transform());
        go.transform = go.getComponent(Transform.class);
        return go;
    }

    public void save()
    {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentDeserializer())
                .registerTypeAdapter(GameObject.class, new GameObjectDeseralizer())
                .enableComplexMapKeySerialization()
                .create();
        try{
            FileWriter writer = new FileWriter(fileName);
            List<GameObject> objsToSerialize = new ArrayList<>();
            for(GameObject obj : this.gameObjects)
            {
                if(obj.doSerialization())
                {
                    objsToSerialize.add(obj);
                }
            }
            writer.write(gson.toJson(objsToSerialize));
            writer.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void load(String newFile)
    {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentDeserializer())
                .registerTypeAdapter(GameObject.class, new GameObjectDeseralizer())
                .enableComplexMapKeySerialization()
                .create();

        fileName = newFile;
      String inFile = "";
      try{
          inFile = new String(Files.readAllBytes(Paths.get(newFile)));

      }catch (IOException e)
      {

          e.printStackTrace();
      }

      if(!inFile.equals(""))
      {
          int maxGoId = -1;
          int maxCompId = -1;
          GameObject[] objs = gson.fromJson(inFile, GameObject[].class );
          for(int i  = 0; i< objs.length; i++)
          {
              addGameObjectToScene(objs[i]);

              for(Component c: objs[i].getAllComponents() )
              {
                    if(c.getUid() > maxCompId)
                    {
                        maxCompId = c.getUid();
                    }
              }
              if(objs[i].getUid() > maxGoId){
                  maxGoId = objs[i].getUid();
              }
          }

          maxGoId++;
          maxCompId++;

          GameObject.init(maxGoId);
          Component.init(maxCompId);



      }
    }

    public void delete()
    {

        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write("");
            writer.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
