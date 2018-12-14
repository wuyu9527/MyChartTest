//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.gupo_android.test.View.Rong;

import android.content.Context;
import org.json.JSONObject;

public class NativeObject {

    interface SetOfflineMessageDurationListener {
        void onSuccess(long var1);

        void onError(int var1);
    }

    public static class ConnectionEntry {
        private String host;
        private int port;
        private int netType;
        private int duration;
        private int error = -1;

        public ConnectionEntry() {
        }

        public String getHost() {
            return this.host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return this.port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public int getNetType() {
            return this.netType;
        }

        public void setNetType(int netType) {
            this.netType = netType;
        }

        public int getDuration() {
            return this.duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getError() {
            return this.error;
        }

        public void setError(int error) {
            this.error = error;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o != null && this.getClass() == o.getClass()) {
                NativeObject.ConnectionEntry that = (NativeObject.ConnectionEntry)o;
                if (this.port != that.port) {
                    return false;
                } else {
                    return this.host != null ? this.host.equals(that.host) : that.host == null;
                }
            } else {
                return false;
            }
        }

        public int hashCode() {
            int result = this.host != null ? this.host.hashCode() : 0;
            result = 31 * result + this.port;
            return result;
        }
    }

    public static class ReceiptInfo {
        private byte[] targetId;
        private long timestamp;

        public ReceiptInfo() {
        }

        public byte[] getTargetId() {
            return this.targetId;
        }

        public void setTargetId(byte[] targetId) {
            this.targetId = targetId;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class AccountInfo {
        private byte[] accountId;
        private byte[] accountName;
        private byte[] accountUri;
        private byte[] extra;
        private int accountType;

        public AccountInfo() {
        }

        public byte[] getAccountId() {
            return this.accountId;
        }

        public void setAccountId(byte[] accountId) {
            this.accountId = accountId;
        }

        public byte[] getAccountName() {
            return this.accountName;
        }

        public void setAccountName(byte[] accountName) {
            this.accountName = accountName;
        }

        public byte[] getAccountUri() {
            return this.accountUri;
        }

        public void setAccountUri(byte[] accountUri) {
            this.accountUri = accountUri;
        }

        public byte[] getExtra() {
            return this.extra;
        }

        public void setExtra(byte[] extra) {
            this.extra = extra;
        }

        public int getAccountType() {
            return this.accountType;
        }

        public void setAccountType(int accountType) {
            this.accountType = accountType;
        }
    }

    public static class DiscussionInfo {
        private String discussionId;
        private String discussionName;
        private String adminId;
        private String userIds;
        private int inviteStatus;

        public DiscussionInfo() {
        }

        public String getDiscussionId() {
            return this.discussionId;
        }

        public void setDiscussionId(String discussionId) {
            this.discussionId = discussionId;
        }

        public String getDiscussionName() {
            return this.discussionName;
        }

        public void setDiscussionName(byte[] data) {
            this.discussionName = new String(data);
        }

        public String getAdminId() {
            return this.adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getUserIds() {
            return this.userIds;
        }

        public void setUserIds(String userIds) {
            this.userIds = userIds;
        }

        public int getInviteStatus() {
            return this.inviteStatus;
        }

        public void setInviteStatus(int inviteStatus) {
            this.inviteStatus = inviteStatus;
        }
    }

    public static class Conversation {
        private int conversationType;
        private String targetId;
        private String conversationTitle;
        private boolean isTop;
        private String draft;
        private int unreadMessageCount;
        private String objectName;
        private int messageId;
        private int readStatus;
        private int receiveStatus;
        private int sentStatus;
        private long ReceivedTime;
        private long sentTime;
        private String senderUserId;
        private String senderName;
        private boolean messageDirection;
        private String messageContent;
        private boolean blockPush;
        private long lastTime;
        private String userId;
        private String userName;
        private String userPortrait;
        private byte[] content;
        private String extra;
        private String portraitUrl;
        private String UId;
        private int mentionCount;
        private int matchCount;

        public Conversation(String jsonObj) {
        }

        public Conversation() {
        }

        public int getMatchCount() {
            return this.matchCount;
        }

        public void setMatchCount(int matchCount) {
            this.matchCount = matchCount;
        }

        public String getUId() {
            return this.UId;
        }

        public void setUId(String UId) {
            this.UId = UId;
        }

        public long getSentTime() {
            return this.sentTime;
        }

        public void setSentTime(long sentTime) {
            this.sentTime = sentTime;
        }

        public String getSenderUserId() {
            return this.senderUserId;
        }

        public void setSenderUserId(String senderUserId) {
            this.senderUserId = senderUserId;
        }

        public boolean isMessageDirection() {
            return this.messageDirection;
        }

        public void setMessageDirection(boolean messageDirection) {
            this.messageDirection = messageDirection;
        }

        public void setIsTop(boolean isTop) {
            this.isTop = isTop;
        }

        public int getConversationType() {
            return this.conversationType;
        }

        public void setConversationType(int conversationType) {
            this.conversationType = conversationType;
        }

        public String getTargetId() {
            return this.targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getConversationTitle() {
            return this.conversationTitle;
        }

        public void setConversationTitle(byte[] conversationTitle) {
            this.conversationTitle = new String(conversationTitle);
        }

        public boolean isTop() {
            return this.isTop;
        }

        public void setTop(boolean isTop) {
            this.isTop = isTop;
        }

        public String getDraft() {
            return this.draft;
        }

        public void setDraft(String draft) {
            this.draft = draft;
        }

        public int getUnreadMessageCount() {
            return this.unreadMessageCount;
        }

        public void setUnreadMessageCount(int unreadMessageCount) {
            this.unreadMessageCount = unreadMessageCount;
        }

        public String getObjectName() {
            return this.objectName;
        }

        public void setObjectName(String objectName) {
            this.objectName = objectName;
        }

        public int getMessageId() {
            return this.messageId;
        }

        public void setMessageId(int messageId) {
            this.messageId = messageId;
        }

        public int getReceiveStatus() {
            return this.receiveStatus;
        }

        public void setReceiveStatus(int receiveStatus) {
            this.receiveStatus = receiveStatus;
        }

        public int getSentStatus() {
            return this.sentStatus;
        }

        public void setSentStatus(int sentStatus) {
            this.sentStatus = sentStatus;
        }

        public long getReceivedTime() {
            return this.ReceivedTime;
        }

        public void setReceivedTime(long receivedTime) {
            this.ReceivedTime = receivedTime;
        }

        public String getSenderName() {
            return this.senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getMessageContent() {
            return this.messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }

        public boolean isBlockPush() {
            return this.blockPush;
        }

        public void setBlockPush(boolean blockPush) {
            this.blockPush = blockPush;
        }

        public long getLastTime() {
            return this.lastTime;
        }

        public void setLastTime(long lastTime) {
            this.lastTime = lastTime;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPortrait() {
            return this.userPortrait;
        }

        public void setUserPortrait(String userPortrait) {
            this.userPortrait = userPortrait;
        }

        public int getReadStatus() {
            return this.readStatus;
        }

        public void setReadStatus(int readStatus) {
            this.readStatus = readStatus;
        }

        public byte[] getContent() {
            return this.content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

        public String getExtra() {
            return this.extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public String getPortraitUrl() {
            return this.portraitUrl;
        }

        public void setPortraitUrl(String portraitUrl) {
            this.portraitUrl = portraitUrl;
        }

        public int getMentionCount() {
            return this.mentionCount;
        }

        public void setMentionCount(int mentionCount) {
            this.mentionCount = mentionCount;
        }
    }

    public static class UserInfo {
        private String userId;
        private int categoryId;
        private String userName;
        private String url;
        private String accountExtra;
        private long joinTime;

        public UserInfo() {
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getCategoryId() {
            return this.categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAccountExtra() {
            return this.accountExtra;
        }

        public void setAccountExtra(String accountExtra) {
            this.accountExtra = accountExtra;
        }

        public long getJoinTime() {
            return this.joinTime;
        }

        public void setJoinTime(long joinTime) {
            this.joinTime = joinTime;
        }
    }

    public static class Message {
        private int conversationType;
        private String targetId;
        private int messageId;
        private boolean messageDirection;
        private String senderUserId;
        private int readStatus;
        private int sentStatus;
        private long receivedTime;
        private long sentTime;
        private String objectName;
        private byte[] content;
        private String extra;
        private String pushContent;
        private String UId;
        private String readReceiptInfo;

        public Message(JSONObject jsonObj) {
            this.conversationType = jsonObj.optInt("conversation_category");
            this.targetId = jsonObj.optString("target_id");
            this.messageId = jsonObj.optInt("id");
            this.messageDirection = jsonObj.optBoolean("message_direction");
            this.senderUserId = jsonObj.optString("sender_user_id");
            this.readStatus = jsonObj.optInt("read_status");
            this.sentStatus = jsonObj.optInt("send_status");
            this.receivedTime = jsonObj.optLong("receive_time");
            this.sentTime = jsonObj.optLong("send_time");
            this.objectName = jsonObj.optString("object_name");
            this.content = jsonObj.optString("content").getBytes();
            this.extra = jsonObj.optString("extra");
            this.pushContent = jsonObj.optString("push");
        }

        public Message() {
        }

        public String getUId() {
            return this.UId;
        }

        public void setUId(String UId) {
            this.UId = UId;
        }

        public String getPushContent() {
            return this.pushContent;
        }

        public void setPushContent(String pushContent) {
            this.pushContent = pushContent;
        }

        public int getConversationType() {
            return this.conversationType;
        }

        public void setConversationType(int conversationType) {
            this.conversationType = conversationType;
        }

        public String getTargetId() {
            return this.targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public int getMessageId() {
            return this.messageId;
        }

        public void setMessageId(int messageId) {
            this.messageId = messageId;
        }

        public boolean getMessageDirection() {
            return this.messageDirection;
        }

        public void setMessageDirection(boolean messageDirection) {
            this.messageDirection = messageDirection;
        }

        public int getReadStatus() {
            return this.readStatus;
        }

        public void setReadStatus(int readStatus) {
            this.readStatus = readStatus;
        }

        public int getSentStatus() {
            return this.sentStatus;
        }

        public void setSentStatus(int sentStatus) {
            this.sentStatus = sentStatus;
        }

        public long getReceivedTime() {
            return this.receivedTime;
        }

        public void setReceivedTime(long receivedTime) {
            this.receivedTime = receivedTime;
        }

        public long getSentTime() {
            return this.sentTime;
        }

        public void setSentTime(long sentTime) {
            this.sentTime = sentTime;
        }

        public String getObjectName() {
            return this.objectName;
        }

        public void setObjectName(String objectName) {
            this.objectName = objectName;
        }

        public byte[] getContent() {
            return this.content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

        public String getExtra() {
            return this.extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public String getSenderUserId() {
            return this.senderUserId;
        }

        public void setSenderUserId(String senderUserId) {
            this.senderUserId = senderUserId;
        }

        public String getReadReceiptInfo() {
            return this.readReceiptInfo;
        }

        public void setReadReceiptInfo(String readReceiptInfo) {
            this.readReceiptInfo = readReceiptInfo;
        }
    }

    public interface UserStatusListener {
        void onStatusReceived(String var1, String var2);
    }

    public interface ChatroomInfoListener {
        void OnSuccess(int var1, NativeObject.UserInfo[] var2);

        void OnError(int var1);
    }

    public interface HistoryMessageListener {
        void onReceived(NativeObject.Message[] var1, long var2);

        void onError(int var1);
    }

    public interface GetUserDataListener {
        void OnSuccess(String var1);

        void OnError(int var1);
    }

    public interface AccountInfoListener {
        void onReceived(NativeObject.AccountInfo[] var1);

        void OnError(int var1);
    }

    public interface TokenListener {
        void OnError(int var1, String var2);
    }

    public interface SetBlacklistListener {
        void OnSuccess(String var1);

        void OnError(int var1);
    }

    public interface BizAckListener {
        void operationComplete(int var1, int var2);
    }

    public interface ExceptionListener {
        void onError(int var1, int var2, String var3);
    }

    public interface DiscussionInfoListener {
        void onReceived(NativeObject.DiscussionInfo var1);

        void OnError(int var1);
    }

    public interface SetPushSettingListener {
        void onSuccess(long var1);

        void onError(int var1);
    }

    public interface PushSettingListener {
        void OnSuccess(String var1, int var2);

        void OnError(int var1);
    }

    public abstract static class ReceiveMessageListener {
        public ReceiveMessageListener() {
        }

        public abstract void onReceived(NativeObject.Message var1, int var2, boolean var3, boolean var4, int var5);

        protected NativeObject.Message getNewMessage() {
            return new NativeObject.Message();
        }
    }

    public interface NativeLogInfoListener {
        void OnLogInfo(String var1, boolean var2);
    }

    public interface GetSearchableWordListener {
        byte[] getSearchableWord(String var1, byte[] var2);
    }

    public interface CreateDiscussionCallback {
        void OnSuccess(String var1);

        void OnError(int var1);
    }

    public interface PublishAckListener {
        void operationComplete(int var1, String var2, long var3);
    }

    public interface ConnectionCollectionListener {
        void OnComplete(NativeObject.ConnectionEntry var1);
    }

    public interface ConnectAckCallback {
        void operationComplete(int var1, String var2, int var3, short var4, String var5);
    }
}
