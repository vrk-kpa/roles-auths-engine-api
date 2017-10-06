/**
 * The MIT License
 * Copyright (c) 2016 Population Register Centre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fi.vm.kapa.rova.vare.model;

import java.util.Date;

/**
 * 
 * Value object that is used for 1. propagating new values from vare-ui and vare(currentLogin and authUser) and 2. return vare -> vare-ui
 * the updated values (same as in 1. but also lastSeen, isFirstLogin).
 * 
 * @author Thomas Isomäki
 *
 */
public class UserDataDTO {
    private boolean isFirstLogin;
    private Date lastSeen;
    private Date currLogin;
    private String authUserId;
    
    public boolean isFirstLogin() {
        return isFirstLogin;
    }
    public void setFirstLogin(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }
    public Date getLastSeen() {
        return lastSeen;
    }
    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }
    public Date getCurrLogin() {
        return currLogin;
    }
    public void setCurrLogin(Date currLogin) {
        this.currLogin = currLogin;
    }
    public String getAuthUserId() {
        return authUserId;
    }
    public void setAuthUserId(String authUserId) {
        this.authUserId = authUserId;
    }
    
    
    
}