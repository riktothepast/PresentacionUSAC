package com.fiveotwo.core;

import static playn.core.PlayN.assets;
import playn.core.Image;
import playn.core.Surface;
/*
 * 2012, 502Studios 
 * This sources code is given "as is", you can use it as you want as long you give credit.
 */
public class Tile {
  public Image Texture;
  public String data;
  public Animation anim;
  public float waittime,time;
  public AnimationPlayer amp=new AnimationPlayer();
  public int Collision;
  public static int Width = 30;
  public static  int Height = 30;
  public static Vector2 Size = new Vector2(Width, Height);
  public boolean cantrack=false;
  /*Collisions
   * 0 pasable
   * 1 impasable
   * 2 treasure
   * 3 skull
   * 4 box
   */
  public Tile(Image texture, int collision) {
	  if(texture!=null){
    anim = new Animation(texture, 6f,30f, true);
    Collision = collision;
    amp.PlayAnimation(anim);
    waittime=10f;
	}
  } 

  public void Draw(Surface surf, Vector2 position){
	  if(anim!=null){
		amp.Draw(0.5f, surf, position, false);}
	}
  
  public void Update(){
	  if(anim!=null){
	  amp.PlayAnimation(anim);
	  }
	  if(Collision==3){
		  if((time>=waittime)&&!cantrack){
			  cantrack=true;
			  FormSkull();
		  }else{
			  time++;
		  }
	  }
  }
  
  public void FormSkull(){
	  anim=new Animation(assets().getImage("images/skull.png"), 6f,30f, true);
  }

}
