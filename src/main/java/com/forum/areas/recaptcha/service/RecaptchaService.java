package com.forum.areas.recaptcha.service;

public interface RecaptchaService {
    String verifyRecaptcha(String userIpAddress, String gRecaptchaResponse);
}
