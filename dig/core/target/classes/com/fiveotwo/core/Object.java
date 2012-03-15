package com.fiveotwo.core;

import playn.core.Image;

public class Object {

	  public Image Texture;
	  public int Type;
	  public static int Width = 30;
	  public static  int Height = 30;
	  
	public Object(Image texture, int type){
		this.Texture=texture;
		this.Type=type;
	}
	
}
