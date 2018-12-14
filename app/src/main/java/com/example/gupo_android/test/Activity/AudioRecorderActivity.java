package com.example.gupo_android.test.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gupo_android.test.Adapter.AudioRecorderAdapter;
import com.example.gupo_android.test.Adapter.ChatAdapter;
import com.example.gupo_android.test.Adapter.Holder.ChatFrameHolder;
import com.example.gupo_android.test.Bean.ChatBean;
import com.example.gupo_android.test.R;
import com.example.gupo_android.test.Util.PermissionCheckUtil;
import com.example.gupo_android.test.View.Rong.IExtensionClickListener;
import com.example.gupo_android.test.View.Rong.emoticon.AndroidEmoji;
import com.example.gupo_android.test.View.Rong.manager.AudioPlayManager;
import com.example.gupo_android.test.View.Rong.manager.AudioRecordManager;
import com.example.gupo_android.test.View.Rong.model.Conversation;
import com.example.gupo_android.test.View.Rong.plugin.IPluginModule;
import com.example.gupo_android.test.View.RongExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AudioRecorderActivity extends AppCompatActivity implements IExtensionClickListener {



    List<ChatBean> holders;
    ChatAdapter audioRecorderAdapter;
    RongExtension rongExtension;
    private String mTargetId = "16205";
    private Conversation.ConversationType mConversationType;

    private float mLastTouchY;
    private boolean mUpDirection;
    private float mOffsetLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);
        AndroidEmoji.init(this);
        this.mOffsetLimit = 70.0F * getResources().getDisplayMetrics().density;
        rongExtension = findViewById(R.id.rc_extension);
        holders = new ArrayList<>();
        audioRecorderAdapter = new ChatAdapter(this, holders);
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendQueryParameter("targetId", mTargetId).build();
        mConversationType = Conversation.ConversationType.PRIVATE;
        rongExtension.setConversation(mConversationType, mTargetId);
        rongExtension.setExtensionClickListener(this);
    }


    @Override
    public void onSendToggleClick(View var1, String var2) {
        Log.i("whx","var2:"+var2);
    }

    @Override
    public void onImageResult(List<Uri> var1, boolean var2) {
        Log.i("whx","图片");
    }

    @Override
    public void onLocationResult(double var1, double var3, String var5, Uri var6) {

    }

    @Override
    public void onSwitchToggleClick(View var1, ViewGroup var2) {

    }

    @Override
    public void onVoiceInputToggleTouch(View var1, MotionEvent event) {
        Log.i("whx","语音输入");
        String[] permissions = new String[]{"android.permission.RECORD_AUDIO"};
        if (!PermissionCheckUtil.checkPermissions(this, permissions) && event.getAction() == 0) {
            PermissionCheckUtil.requestPermissions(this, permissions, 100);
        } else {
            if (event.getAction() == 0) {
                if (AudioPlayManager.getInstance().isPlaying()) {
                    AudioPlayManager.getInstance().stopPlay();
                }

                if (!AudioPlayManager.getInstance().isInNormalMode(this)) {
                    Toast.makeText(this, this.getString(R.string.rc_voip_occupying), Toast.LENGTH_SHORT).show();
                    return;
                }

                AudioRecordManager.getInstance(this).startRecord(var1.getRootView(), mConversationType, this.mTargetId);
                this.mLastTouchY = event.getY();
                this.mUpDirection = false;
                ((Button)var1).setText(R.string.rc_audio_input_hover);
            } else if (event.getAction() == 2) {
                if (this.mLastTouchY - event.getY() > this.mOffsetLimit && !this.mUpDirection) {
                    AudioRecordManager.getInstance(this).willCancelRecord();
                    this.mUpDirection = true;
                    ((Button)var1).setText(R.string.rc_audio_input);
                } else if (event.getY() - this.mLastTouchY > -this.mOffsetLimit && this.mUpDirection) {
                    AudioRecordManager.getInstance(this).continueRecord();
                    this.mUpDirection = false;
                    ((Button)var1).setText(R.string.rc_audio_input_hover);
                }
            } else if (event.getAction() == 1 || event.getAction() == 3) {
                AudioRecordManager.getInstance(this).stopRecord();
                ((Button)var1).setText(R.string.rc_audio_input);
            }

            if (this.mConversationType.equals(Conversation.ConversationType.PRIVATE)) {
                //发送语音
                //RongIMClient.getInstance().sendTypingStatus(this.mConversationType, this.mTargetId, "RC:VcMsg");
            }

        }
    }

    @Override
    public void onEmoticonToggleClick(View var1, ViewGroup var2) {

    }

    @Override
    public void onPluginToggleClick(View var1, ViewGroup var2) {

    }

    @Override
    public void onMenuClick(int var1, int var2) {

    }

    @Override
    public void onEditTextClick(EditText var1) {
        Log.i("whx","输入框点击");
    }

    @Override
    public boolean onKey(View var1, int var2, KeyEvent var3) {
        return false;
    }

    @Override
    public void onExtensionCollapsed() {

    }

    @Override
    public void onExtensionExpanded(int var1) {

    }

    @Override
    public void onPluginClicked(IPluginModule var1, int var2) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
