package com.planme.main.oauth2.enums;

public class OAuth2Config {

    public enum SocialType{
        GOOGLE("google"),
        KAKAO("kakao"),;

        private final String socialName;
        SocialType(String socialName){
            this.socialName = socialName;
        }

        public String getSocialName(){
            return socialName;
        }
    }

}
