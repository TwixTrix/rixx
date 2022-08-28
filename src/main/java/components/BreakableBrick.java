package components;

import util.AssetPool;

public class BreakableBrick extends Block{

    @Override
    void playerHit(PlayerController playerController) {

            AssetPool.getSound("assets/sounds/break_block.ogg").play();
            gameObject.destroy();

    }
}
