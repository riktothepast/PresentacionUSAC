package com.fiveotwo.core;

import playn.core.Image;

/* Una hoja de sprites, 
 * todas los sprites vienen en una imagen horizontal, 
 */

public class Animation {
	Image texture;
	float frameTime;
	public float frameWidth;
    boolean isLooping;

  public Image Texture() {
    return this.texture;
  }
  
  // cuanto dura cada animacion?.
  public float FrameTime() {
    return frameTime;
  }

  /* Se terminaron los cuadros de animacion
     debe volver a iniciar?*/
  public boolean IsLooping() {
    return this.isLooping;
  }

  // el numero de cuadros de animacion.
  public float FrameCount() {
    return Texture().width() / FrameWidth();
  }

  // la alnchura de la animacion, el valor ingresado.
  public float FrameWidth() {
    return this.frameWidth;
  }

  // la altura de la animacion, asumimos que es el alto de la imagen.
  public float FrameHeight() {
    return Texture().height();
  }

  public Animation(Image texture, float frameTime, float frameWidth, boolean isLooping) {
    this.texture = texture;
    this.frameTime = frameTime;
    this.isLooping = isLooping;
    this.frameWidth = frameWidth;
  }
  
}
