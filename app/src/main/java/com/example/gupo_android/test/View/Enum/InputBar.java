//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Enum;

public class InputBar {
    public InputBar() {
    }

    public static enum Type {
        TYPE_DEFAULT,
        TYPE_CS_ROBOT,
        TYPE_CS_HUMAN,
        TYPE_CS_ROBOT_FIRST,
        TYPE_CS_HUMAN_FIRST;

        private Type() {
        }
    }

    public static enum Style {
        STYLE_SWITCH_CONTAINER_EXTENSION(291),
        STYLE_SWITCH_CONTAINER(288),
        STYLE_CONTAINER_EXTENSION(35),
        STYLE_EXTENSION_CONTAINER(800),
        STYLE_CONTAINER(32);

        int v;

        private Style(int v) {
            this.v = v;
        }

        public static InputBar.Style getStyle(int v) {
            InputBar.Style result = null;
            InputBar.Style[] var2 = values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                InputBar.Style style = var2[var4];
                if (style.v == v) {
                    result = style;
                    break;
                }
            }

            return result;
        }
    }
}
