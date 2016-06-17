package fi.vm.kapa.rova.admin.model;

import java.util.Date;
import java.util.Set;

import fi.vm.kapa.rova.ui.Channel;

public class NotificationDTO {
    
    private long id;
    
    private String text;
    
    private Set<Channel> channels;
    
    private Date startDate;
    
    private Date endDate;
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
