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
package fi.vm.kapa.rova.notification.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Predicate;

public final class NotificationDTOUtil {
    
    private NotificationDTOUtil()  {}

    public static Comparator<NotificationDTO> notificationEndDateComparator() {
        return new Comparator<NotificationDTO>() {
            @Override
            public int compare(NotificationDTO dto1, NotificationDTO dto2) {
                return Long.compare(dto1.getEndDate().getTime(), dto2.getEndDate().getTime());
            }
        };
    }

    public static Predicate<NotificationDTO> isNotificationActive() {
        LocalDateTime now = getLocalDateTime(null);
        return p -> now.isBefore(getLocalDateTime(p.getEndDate()))
                && (now.isAfter(getLocalDateTime(p.getStartDate())) || now.isEqual(getLocalDateTime(p.getStartDate())));
    }
    
    public static Predicate<NotificationDTO> isNotificationNew() {
        return p -> getLocalDateTime(null).isBefore(getLocalDateTime(p.getStartDate()));
    }
    
    private static LocalDateTime getLocalDateTime(Date date) {
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime time = LocalDateTime.now(zone);
        if (date != null) {
            Instant instant = Instant.ofEpochMilli(date.getTime());
            time = LocalDateTime.ofInstant(instant, zone);
        }
        return time;
    }
}
