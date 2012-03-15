package com.fiveotwo.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.fiveotwo.core.Dig;

public class DigJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("com/fiveotwo/resources");
    PlayN.run(new Dig());
  }
}
