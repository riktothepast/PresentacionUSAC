package com.fiveotwo.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import com.fiveotwo.core.Dig;

public class DigHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("dig/");
    PlayN.run(new Dig());
  }
}
