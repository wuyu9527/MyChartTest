//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Rong.model;

import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;

import com.example.gupo_android.test.View.Rong.common.RLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MessageContent implements Parcelable {
    private static final String TAG = "MessageContent";
    private UserInfo userInfo;
    private MentionedInfo mentionedInfo;

    protected MessageContent() {
    }

    public MessageContent(byte[] data) {
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo info) {
        this.userInfo = info;
    }

    public MentionedInfo getMentionedInfo() {
        return this.mentionedInfo;
    }

    public void setMentionedInfo(MentionedInfo info) {
        this.mentionedInfo = info;
    }

    public JSONObject getJSONUserInfo() {
        if (this.getUserInfo() != null && this.getUserInfo().getUserId() != null) {
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("id", this.getUserInfo().getUserId());
                if (!TextUtils.isEmpty(this.getUserInfo().getName())) {
                    jsonObject.put("name", this.getUserInfo().getName());
                }

                if (this.getUserInfo().getPortraitUri() != null) {
                    jsonObject.put("portrait", this.getUserInfo().getPortraitUri());
                }
            } catch (JSONException var3) {
                RLog.e("MessageContent", "JSONException " + var3.getMessage());
            }

            return jsonObject;
        } else {
            return null;
        }
    }

    public UserInfo parseJsonToUserInfo(JSONObject jsonObj) {
        UserInfo info = null;
        String id = jsonObj.optString("id");
        String name = jsonObj.optString("name");
        String icon = jsonObj.optString("portrait");
        if (TextUtils.isEmpty(icon)) {
            icon = jsonObj.optString("icon");
        }

        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(name)) {
            Uri portrait = icon != null ? Uri.parse(icon) : null;
            info = new UserInfo(id, name, portrait);
        }

        return info;
    }

    public JSONObject getJsonMentionInfo() {
        if (this.getMentionedInfo() == null) {
            return null;
        } else {
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("type", this.getMentionedInfo().getType().getValue());
                if (this.getMentionedInfo().getMentionedUserIdList() == null) {
                    jsonObject.put("userIdList", (Object)null);
                } else {
                    JSONArray jsonArray = new JSONArray();
                    Iterator var3 = this.getMentionedInfo().getMentionedUserIdList().iterator();

                    while(var3.hasNext()) {
                        String userId = (String)var3.next();
                        jsonArray.put(userId);
                    }

                    jsonObject.put("userIdList", jsonArray);
                }

                jsonObject.put("mentionedContent", this.getMentionedInfo().getMentionedContent());
            } catch (JSONException var5) {
                RLog.e("MessageContent", "JSONException " + var5.getMessage());
            }

            return jsonObject;
        }
    }

    public MentionedInfo parseJsonToMentionInfo(JSONObject jsonObject) {
        MentionedInfo.MentionedType type = MentionedInfo.MentionedType.valueOf(jsonObject.optInt("type"));
        JSONArray userList = jsonObject.optJSONArray("userIdList");
        String mentionContent = jsonObject.optString("mentionedContent");
        if (type.equals(MentionedInfo.MentionedType.NONE)) {
            return null;
        } else {
            MentionedInfo mentionedInfo;
            if (type.equals(MentionedInfo.MentionedType.ALL)) {
                mentionedInfo = new MentionedInfo(type, (List)null, mentionContent);
            } else {
                List<String> list = new ArrayList();
                if (userList == null || userList.length() <= 0) {
                    return null;
                }

                try {
                    for(int i = 0; i < userList.length(); ++i) {
                        list.add((String)userList.get(i));
                    }
                } catch (JSONException var8) {
                    var8.printStackTrace();
                }

                mentionedInfo = new MentionedInfo(type, list, mentionContent);
            }

            return mentionedInfo;
        }
    }

    public List<String> getSearchableWord() {
        return null;
    }

    public abstract byte[] encode();
}
