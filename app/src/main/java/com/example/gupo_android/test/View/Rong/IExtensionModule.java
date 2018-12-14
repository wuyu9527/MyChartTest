//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Rong;

import com.example.gupo_android.test.View.Rong.emoticon.IEmoticonTab;
import com.example.gupo_android.test.View.Rong.model.Conversation;
import com.example.gupo_android.test.View.Rong.model.Message;
import com.example.gupo_android.test.View.Rong.plugin.IPluginModule;
import com.example.gupo_android.test.View.RongExtension;

import java.util.List;

public interface IExtensionModule {
    void onInit(String var1);

    void onConnect(String var1);

    void onAttachedToExtension(RongExtension var1);

    void onDetachedFromExtension();

    void onReceivedMessage(Message var1);

    List<IPluginModule> getPluginModules(Conversation.ConversationType var1);

    List<IEmoticonTab> getEmoticonTabs();

    void onDisconnect();
}
