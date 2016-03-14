package bean;
import java.util.Date;

/**
 *
 * @author Siwei Jiao
 */
public class ChatMsgBean {
    private int chatLog_id;
    private String msgFrom;
    private String msgTo;
    private Date dateTime;
    private String msgContent;

    /**
     * @return the chatLog_id
     */
    public int getChatLog_id() {
        return chatLog_id;
    }

    /**
     * @param chatLog_id the chatLog_id to set
     */
    public void setChatLog_id(int chatLog_id) {
        this.chatLog_id = chatLog_id;
    }

    /**
     * @return the msgFrom
     */
    public String getMsgFrom() {
        return msgFrom;
    }

    /**
     * @param msgFrom the msgFrom to set
     */
    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom;
    }

    /**
     * @return the msgTo
     */
    public String getMsgTo() {
        return msgTo;
    }

    /**
     * @param msgTo the msgTo to set
     */
    public void setMsgTo(String msgTo) {
        this.msgTo = msgTo;
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the msgContent
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * @param msgContent the msgContent to set
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
