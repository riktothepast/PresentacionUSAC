package com.fiveotwo.core;
/*
 * 2012, 502Studios 
 * This sources code is given "as is", you can use it as you want as long you give credit.
 */
import playn.core.*;
import static playn.core.PlayN.*;

public class Dig implements Game {
    Map mapa= new Map();
    public static final AssetWatcher watcher = new AssetWatcher();

  @Override
  public void init() {
    // create and add background image layer    
	graphics().setSize(600, 600);
    mapa.Init();
    watcher.start();
  }

  @Override
  public void paint(float alpha) {
    // the background automatically paints itself, so no need to do anything here!
    if(watcher.isDone()) mapa.Draw(alpha);
  }

  @Override
  public void update(float delta) {
        if(watcher.isDone())	  mapa.Update(delta);
  }

  @Override
  public int updateRate() {
    return 90;
  }
}
