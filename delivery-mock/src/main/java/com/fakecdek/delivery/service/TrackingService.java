package com.fakecdek.delivery.service;


import com.fakecdek.deliverymockclient.dto.TrackLinkDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrackingService {

    public TrackLinkDto generateTrackLink() {
        String link = UUID.randomUUID().toString();
        return new TrackLinkDto(link);
    }
}
